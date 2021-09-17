package net.originmobi.pdv.integracao;

import com.fasterxml.jackson.databind.ObjectMapper;
import net.originmobi.pdv.controller.VendaController;
import net.originmobi.pdv.enumerado.VendaSituacao;
import net.originmobi.pdv.enumerado.caixa.CaixaTipo;
import net.originmobi.pdv.integracao.dados.DadosTesteVenda;
import net.originmobi.pdv.model.Receber;
import net.originmobi.pdv.model.Venda;
import net.originmobi.pdv.repository.*;
import net.originmobi.pdv.utilitarios.CaixaFactory;
import net.originmobi.pdv.utilitarios.UsuarioFactory;
import net.originmobi.pdv.utilitarios.VendaFactory;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.*;

import static net.originmobi.pdv.utilitarios.JsonParser.asJsonString;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-integrationtest.properties")
public class VendaControllerIntegracaoTest {

    @Autowired
    private VendaController vendaController;

    @MockBean
    private CaixaRepository caixaRepository;

    @MockBean
    private VendaRepository vendaRepository;

    @MockBean
    private VendaProdutosRepository vendaProdutosRepository;

    @MockBean
    private PagamentoTipoRespository pagamentoTipoRespository;

    @MockBean
    private TituloRepository tituloRepository;

    @MockBean
    private UsuarioRepository usuarioRepository;

    @MockBean
    private CaixaLancamentoRepository caixaLancamentoRepository;

    @MockBean
    private ReceberRepository receberRepository;

    @Mock
    private Model model;

    @Autowired
    MockMvc mockMvc;

    @Test
    @WithMockUser(roles = "VISUALIZAR_PEDIDO_ABERTO")
    public void testaListarPedidos() throws Exception {
        Mockito.when(vendaRepository.findBySituacaoEquals(Mockito.any(), Mockito.any())).thenReturn(DadosTesteVenda.pageVendaCompleto());
        Mockito.when(vendaRepository.findByCodigoIn( Mockito.any(), Mockito.any())).thenReturn(DadosTesteVenda.pageVendaCompleto());
        mockMvc.perform(MockMvcRequestBuilders.get("/venda/status/ABERTA"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().attribute("vendas", Matchers.notNullValue()));
    }

    @Test
    @WithMockUser(roles = "ENTRAR_NO_SISTEMA")
    public void testarAbrirVenda() throws Exception {
        Mockito.when(vendaRepository.save(Mockito.any())).thenReturn(VendaFactory.createVendaValid());
        String request = asJsonString(VendaFactory.createVendaValid());

       mockMvc.perform(MockMvcRequestBuilders
                .post("/venda")
                    .content(asJsonString(request))
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isFound())
                .andExpect(MockMvcResultMatchers.view().name("redirect:/venda/"));
    }

    @Test
//    @WithMockUser(roles = "ENTRAR_NO_SISTEMA")
    public void testarBuscaVenda() throws Exception {
        Mockito.when(vendaProdutosRepository.findByProdutosDaVenda(Mockito.any())).thenReturn(new ArrayList<>());
        //todo  não está encontrando endpoint
        ResultActions result = mockMvc.perform(
                MockMvcRequestBuilders.get("/venda/1").param("venda", asJsonString(VendaFactory.createVendaValid())))
                .andExpect(MockMvcResultMatchers.status().isFound());
        System.out.println(result);
    }

    @Test
    @WithMockUser(roles = "INSERIR_PRODUTO_VENDA")
    public void testAddProdutoVenda() throws Exception {
        Mockito.when(vendaRepository.verificaSituacao(Mockito.any())).thenReturn("ABERTA");
        mockMvc.perform(MockMvcRequestBuilders.post("/venda/addproduto")
            .param("codigoPro", "1")
            .param("codigoVen", "3")
            .param("valorBalanca", "300"))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(content().string("ok"));
    }

    @Test
    @WithMockUser(roles = "REMOVER_PRODUTO_VENDA")
    public void testarRemoverProdutoVenda() throws Exception {
        Mockito.when(vendaRepository.findByCodigoEquals(1L)).thenReturn(VendaFactory.createVendaValid());
        mockMvc.perform(MockMvcRequestBuilders.post("/venda/removeproduto")
                    .param("posicaoPro", "1")
                    .param("codigoVen", "1"))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(content().string("Venda fechada"));
    }

    @Test
    @WithMockUser(roles = "GERAR_VENDA")
    public void testarGerarVenda() throws Exception{
        Venda vendaAberta = VendaFactory.createVendaValid();
        vendaAberta.setSituacao(VendaSituacao.ABERTA);
        Mockito.when(vendaRepository.findByCodigoEquals(1L)).thenReturn(vendaAberta);
        Mockito.when(pagamentoTipoRespository.findByCodigoIn(Mockito.any())).thenReturn(DadosTesteVenda.pagamentoTipoCompleto());
        Mockito.when(tituloRepository.findById(Mockito.any())).thenReturn(DadosTesteVenda.tituloCompleto());
        Mockito.when(caixaRepository.caixaAberto()).thenReturn(Optional.of(CaixaFactory.createValidCaixaToBeClosed(CaixaTipo.CAIXA)));
        Mockito.when(usuarioRepository.findByUserEquals(Mockito.anyString())).thenReturn(UsuarioFactory.createUserValid());
        Mockito.when(receberRepository.save(Mockito.any())).thenAnswer(invocation -> {
            Receber receber = invocation.getArgument(0);
            receber.setCodigo(1L);
            return receber;
        });

        LinkedMultiValueMap<String, String> request = new LinkedMultiValueMap<>();
        request.add("venda", "1");
        request.add("pagamentotipo", "2");
        request.add("valorProdutos", "140");
        request.add("valorDesconto", "0");
        request.add("valorAcrescimo", "12");
        request.add("valores", "140");
        request.add("titulos", "1");
        mockMvc.perform(MockMvcRequestBuilders.post("/venda/fechar")
                .params(request))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(content().string("Venda finalizada com sucesso"));

    }

}
