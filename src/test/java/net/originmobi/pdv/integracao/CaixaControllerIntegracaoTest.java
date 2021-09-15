package net.originmobi.pdv.integracao;

import net.originmobi.pdv.controller.CaixaController;
import net.originmobi.pdv.filter.CaixaFilter;
import net.originmobi.pdv.integracao.dados.DadosTesteCaixa;
import net.originmobi.pdv.model.Caixa;
import net.originmobi.pdv.repository.CaixaRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@TestPropertySource(locations = "classpath:application-integrationtest.properties")
class CaixaControllerIntegracaoTest {

    @Autowired
    private CaixaController caixaController;

    @MockBean
    private CaixaRepository caixaRepositoryMock;

    @Test
    @WithMockUser(roles = "LISTAR_CAIXAS")
    void testeListarCaixas() {
        CaixaFilter filtro = new CaixaFilter();
        Mockito.when(caixaRepositoryMock.listaCaixas())
                .thenReturn(Collections.singletonList(DadosTesteCaixa.caixaCompleto()));
        ModelAndView resultado = caixaController.lista(filtro);
        List<?> caixas = (List<?>) resultado.getModel().get("caixas");

        Assertions.assertEquals(1, caixas.size());
    }

    @Test
    @WithMockUser(roles = {"LISTAR_CAIXAS", "ACESSAR_CAIXA",})
    void testeCadastroCaixaValido() {
        HashMap<String, String> request = DadosTesteCaixa.requestCaixaCadastroCompleto();

        Mockito.when(caixaRepositoryMock.save(Mockito.any()))
                .thenAnswer(invocation -> {
                    Caixa caixa = invocation.getArgument(0);
                    caixa.setCodigo(1L);
                    return caixa;
                });

        String cadastro = caixaController.cadastro(request, UriComponentsBuilder.newInstance());

        Assertions.assertEquals("{Location=[/caixa/gerenciar/]}1", cadastro);
    }

}
