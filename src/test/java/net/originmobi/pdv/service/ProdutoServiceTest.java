package net.originmobi.pdv.service;

import net.originmobi.pdv.enumerado.EntradaSaida;
import net.originmobi.pdv.model.Produto;
import net.originmobi.pdv.model.VendaProduto;
import net.originmobi.pdv.repository.ProdutoRepository;
import net.originmobi.pdv.utilitarios.ProdutoFactory;
import net.originmobi.pdv.utilitarios.VendaProdutoFactory;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
public class ProdutoServiceTest {

    @InjectMocks
    private ProdutoService produtoService;
    @Mock
    private ProdutoRepository produtoRepositoryMock;
    @Mock
    private VendaProdutoService vendaProdutoServiceMock;

    @BeforeEach
    void setUp() {

        BDDMockito.when(vendaProdutoServiceMock.buscaQtdProduto(ArgumentMatchers.anyLong()))
                .thenReturn(VendaProdutoFactory.createListVendaProdutosQTD());

        BDDMockito.when(produtoRepositoryMock.findAll())
                .thenReturn(ProdutoFactory.createListProdutoValid());
        BDDMockito.when(produtoRepositoryMock.findByCodigoIn(ArgumentMatchers.anyLong()))
                .thenReturn(ProdutoFactory.createProdutoValid());
        BDDMockito.when(produtoRepositoryMock.findById(ArgumentMatchers.anyLong()))
                .thenReturn(Optional.of(ProdutoFactory.createProdutoValid()));
        BDDMockito.when(produtoRepositoryMock.produtosVendaveis())
                .thenReturn(ProdutoFactory.createListProdutoValid());

        BDDMockito.doNothing().when(produtoRepositoryMock).insere(
                ArgumentMatchers.anyLong(), ArgumentMatchers.anyLong(),
                ArgumentMatchers.anyLong(), ArgumentMatchers.anyInt(),
                ArgumentMatchers.anyString(), ArgumentMatchers.anyDouble(),
                ArgumentMatchers.anyDouble(), ArgumentMatchers.any(java.sql.Date.class),
                ArgumentMatchers.anyString(), ArgumentMatchers.anyString(),
                ArgumentMatchers.anyString(), ArgumentMatchers.anyInt(),
                ArgumentMatchers.any(java.sql.Date.class), ArgumentMatchers.anyString(),
                ArgumentMatchers.anyString(), ArgumentMatchers.anyLong(),
                ArgumentMatchers.anyLong(), ArgumentMatchers.anyString());

        BDDMockito.doNothing().when(produtoRepositoryMock).atualiza(
                ArgumentMatchers.anyLong(), ArgumentMatchers.anyLong(),
                ArgumentMatchers.anyLong(), ArgumentMatchers.anyLong(),
                ArgumentMatchers.anyInt(), ArgumentMatchers.anyString(),
                ArgumentMatchers.anyDouble(), ArgumentMatchers.anyDouble(),
                ArgumentMatchers.any(java.sql.Date.class), ArgumentMatchers.anyString(),
                ArgumentMatchers.anyString(), ArgumentMatchers.anyString(),
                ArgumentMatchers.anyInt(), ArgumentMatchers.anyString(),
                ArgumentMatchers.anyString(), ArgumentMatchers.anyLong(),
                ArgumentMatchers.anyLong(), ArgumentMatchers.anyString());

        BDDMockito.doNothing().when(produtoRepositoryMock).movimentaEstoque(ArgumentMatchers.anyLong(),
                ArgumentMatchers.anyString(), ArgumentMatchers.anyInt(),
                ArgumentMatchers.anyString(), ArgumentMatchers.any(java.sql.Date.class));
    }
    @Test
    @DisplayName("teste do metodo listar")
    public void listar(){

        Long expectedCod = ProdutoFactory.createListProdutoValid().get(0).getCodigo();
        List<Produto> produtos = produtoService.listar();
        Long cod = produtos.get(0).getCodigo();
        Assertions.assertThat(produtos)
                .isNotNull()
                .isNotEmpty()
                .hasSize(1);

        assertEquals(expectedCod, cod);

    }

    @Test
    @DisplayName("teste do metodo listaProdutoVendaveis")
    public void listaProdutosVendaveis(){

        Long expectedCod = ProdutoFactory.createListProdutoValid().get(0).getCodigo();
        String expectedResp = String.valueOf(ProdutoFactory.createProdutoValid().getVendavel());

        List<Produto> produtos = produtoService.listaProdutosVendaveis();

        Long cod = produtos.get(0).getCodigo();
        String resp = String.valueOf(produtos.get(0).getVendavel());
        Assertions.assertThat(produtos)
                .isNotNull()
                .isNotEmpty()
                .hasSize(1);
        assertEquals(expectedResp, resp);
        assertEquals(expectedCod, cod);
    }

    @Test
    @DisplayName("teste do metodo busca")
    public void busca(){
        Long expectedCod = ProdutoFactory.createProdutoValid().getCodigo();
        Produto produto = produtoService.busca(ProdutoFactory.createProdutoValid().getCodigo());
        Long cod = produto.getCodigo();
        assertEquals(expectedCod, cod);
    }

    @Test
    @DisplayName("teste do metodo buscaProduto")
    public void buscaProduto(){
        Long expectedCod = ProdutoFactory.createProdutoValid().getCodigo();
        Optional<Produto> produto = produtoService.buscaProduto(ProdutoFactory.createProdutoValid().getCodigo());
        Long cod = produto.get().getCodigo();
        assertEquals(expectedCod, cod);
    }

    @Test
    @DisplayName("teste do metodo merger inserido um produto")
    public void mergerInsereProduto(){
        Produto prod = ProdutoFactory.createProdutoValid();
        String expectedMsg = "Produdo cadastrado com sucesso";
        String msg = produtoService.merger(prod.getCodigo(), 10L,10L,
                10L,10, prod.getDescricao(), prod.getValorCusto(), prod.getValorVenda(),
                prod.getDataValidade(), String.valueOf(prod.getControlaEstoque()), "",
                prod.getUnidade(), prod.getSubtributaria(), prod.getNcm(), prod.getCest(), 10L,
                10L, String.valueOf(prod.getVendavel()));
        assertEquals(expectedMsg, msg);
    }

    @Test
    @DisplayName("teste do metodo merger atualizando um produto")
    public void mergerAtualizaProduto(){

        Produto prod = ProdutoFactory.createProdutoValidToUpdate();
        String expectedMsg = "Produto atualizado com sucesso";
        String msg = produtoService.merger(prod.getCodigo(), 10L,10L,
                10L,10, prod.getDescricao(), prod.getValorCusto(), prod.getValorVenda(),
                prod.getDataValidade(), String.valueOf(prod.getControlaEstoque()), "",
                prod.getUnidade(), prod.getSubtributaria(), prod.getNcm(), prod.getCest(), 10L,
                10L, String.valueOf(prod.getVendavel()));
        assertEquals(expectedMsg, msg);
    }

    @Test
    @DisplayName("teste do metodo movimentaEstoque")
    public void movimentaEstoque(){
        BDDMockito.when(produtoRepositoryMock.saldoEstoque(ArgumentMatchers.anyLong()))
                .thenReturn(100);
        VendaProduto vendaProduto = VendaProdutoFactory.createVendaProdutoValid();
        Assertions.assertThatCode(() -> produtoService.movimentaEstoque(vendaProduto.getCodigo(), EntradaSaida.SAIDA)).doesNotThrowAnyException();
    }

    @Test
    @DisplayName("teste do metodo AjsuteEstoque")
    public void AjsuteEstoque(){
        Produto produto = ProdutoFactory.createProdutoValid();
        Assertions.assertThatCode(() -> produtoService.ajusteEstoque(produto.getCodigo(), 10,  EntradaSaida.SAIDA, "Venda", new Date(System.currentTimeMillis()))).doesNotThrowAnyException();
    }

}
