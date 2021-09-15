package net.originmobi.pdv.integracao;

import net.originmobi.pdv.controller.CaixaController;
import net.originmobi.pdv.filter.CaixaFilter;
import net.originmobi.pdv.integracao.dados.DadosTesteCaixa;
import net.originmobi.pdv.repository.CaixaRepository;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.servlet.ModelAndView;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-integrationtest.properties")
class CaixaControllerIntegracaoTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private CaixaController caixaControllerMock;

    @MockBean
    private CaixaRepository caixaRepositoryMock;

    @Test
    @WithMockUser(roles="LISTAR_CAIXAS")
    void testeListarCaixas(){
        CaixaFilter filtro = new CaixaFilter();
        Mockito.when(caixaRepositoryMock.listaCaixas()).thenReturn(Collections.singletonList(DadosTesteCaixa.caixaCompleto()));
        ModelAndView resultado = caixaControllerMock.lista(filtro);
        List<?> caixas = (List<?>) resultado.getModel().get("caixas");

        Assertions.assertEquals(1, caixas.size());
    }

    @Test
    @WithMockUser(roles="LISTAR_CAIXAS")
    void testeCadastroCaixaValido() throws Exception {
        HashMap<String, String> request = DadosTesteCaixa.requestCaixaCadastroCompleto();
        mvc.perform(post("/caixa", request))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(model().attribute("Location", Matchers.notNullValue()));
    }

}
