package net.originmobi.pdv.integracao;

import net.originmobi.pdv.integracao.dados.DadosTesteCaixa;
import net.originmobi.pdv.model.Caixa;
import net.originmobi.pdv.repository.CaixaRepository;
import net.originmobi.pdv.repository.UsuarioRepository;
import net.originmobi.pdv.utilitarios.UsuarioFactory;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.util.LinkedMultiValueMap;

import java.util.Collections;
import java.util.Optional;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-integrationtest.properties")
class CaixaControllerIntegracaoTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    private CaixaRepository caixaRepositoryMock;

    @MockBean
    private UsuarioRepository usuarioRepositoryMock;

    @Test
    @WithMockUser(roles = "LISTAR_CAIXA")
    void testeListarCaixas() throws Exception {
        Mockito.when(caixaRepositoryMock.listaCaixas())
                .thenReturn(Collections.singletonList(DadosTesteCaixa.caixaCompleto()));

        mockMvc.perform(MockMvcRequestBuilders.get("/caixa"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().attribute("caixas", Matchers.hasSize(1)));
    }

    @Test
    @WithMockUser(roles = "LISTAR_CAIXA")
    void testeCadastroCaixaValido() throws Exception {
        LinkedMultiValueMap<String, String> request = DadosTesteCaixa.requestCaixaCadastroCompleto();

        Mockito.when(caixaRepositoryMock.save(Mockito.any()))
                .thenAnswer(invocation -> {
                    Caixa caixa = invocation.getArgument(0);
                    caixa.setCodigo(1L);
                    return caixa;
                });

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/caixa")
                        .params(request))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers
                        .content()
                        .string("{Location=[http://localhost/caixa/gerenciar/]}1"));
    }

    @Test
    @WithMockUser(roles = "FECHAR_CAIXA")
    void testeFecharCaixa() throws Exception {
        LinkedMultiValueMap<String, String> request = new LinkedMultiValueMap<>();
        request.add("caixa", Long.toString(1L));
        request.add("senha", "123");

        Mockito.when(caixaRepositoryMock.findById(Mockito.any()))
                .thenReturn(Optional.of(DadosTesteCaixa.caixaCompleto()));

        Mockito.when(usuarioRepositoryMock.findByUserEquals(ArgumentMatchers.anyString()))
                .thenReturn((UsuarioFactory.createUserValid()));

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/caixa/fechar")
                        .params(request))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("Caixa fechado com sucesso"));
    }

    @Test
    @WithMockUser(roles = "FECHAR_CAIXA")
    void testeFecharCaixaJaFechado() throws Exception {
        LinkedMultiValueMap<String, String> request = new LinkedMultiValueMap<>();
        request.add("caixa", Long.toString(1L));
        request.add("senha", "123");

        Mockito.when(caixaRepositoryMock.findById(Mockito.any()))
                .thenReturn(Optional.of(DadosTesteCaixa.caixaFechado()));

        Mockito.when(usuarioRepositoryMock.findByUserEquals(ArgumentMatchers.anyString()))
                .thenReturn((UsuarioFactory.createUserValid()));

        mockMvc.perform(MockMvcRequestBuilders
                .post("/caixa/fechar")
                .params(request))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("Caixa já esta fechado"));
    }

}
