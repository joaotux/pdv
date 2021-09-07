package net.originmobi.pdv.service;

import net.originmobi.pdv.model.Venda;
import net.originmobi.pdv.model.VendaProduto;
import net.originmobi.pdv.repository.VendaProdutosRepository;
import net.originmobi.pdv.utilitarios.VendaFactory;
import net.originmobi.pdv.utilitarios.VendaProdutoFactory;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

@ExtendWith(SpringExtension.class)
public class VendaProdutoServiceTest {

    @InjectMocks
    private VendaProdutoService vendaProdutoService;

    @Mock
    private VendaProdutosRepository vendaProdutosRepositoryMock;

    @BeforeEach
    void setUp(){

        BDDMockito.when(vendaProdutosRepositoryMock.save(ArgumentMatchers.any(VendaProduto.class)))
                .thenReturn(VendaProdutoFactory.createVendaProdutoValid());

        BDDMockito.when(vendaProdutosRepositoryMock.findByCodigoIn(ArgumentMatchers.anyLong()))
                .thenReturn(VendaProdutoFactory.createVendaProdutoValid());

        BDDMockito.when(vendaProdutosRepositoryMock.findByProdutosDaVenda(ArgumentMatchers.anyLong()))
                .thenReturn(VendaProdutoFactory.createListVendaProdutos());

        BDDMockito.when(vendaProdutosRepositoryMock.buscaQtdProduto(ArgumentMatchers.anyLong()))
                .thenReturn(VendaProdutoFactory.createListVendaProdutosQTD());


        BDDMockito.doNothing().when(vendaProdutosRepositoryMock).delete(ArgumentMatchers.any(VendaProduto.class));

        BDDMockito.doNothing().when(vendaProdutosRepositoryMock).removeProduto(ArgumentMatchers.anyLong());
    }

    @Test
    @DisplayName("Teste do metodo Salvar")
    public void SalvaVendaProduto(){
        Assertions.assertThatCode(() -> vendaProdutoService.salvar(VendaProdutoFactory.createVendaProdutoValid())).doesNotThrowAnyException();
    }

    @Test
    @DisplayName("Teste do metodo busca")
    public void busca(){
        Long expectedVendaProduto = VendaProdutoFactory.createVendaProdutoValid().getCodigo();
        VendaProduto vendaProduto = vendaProdutoService.busca(VendaProdutoFactory.createVendaProdutoValid().getCodigo());
        assertNotNull(vendaProduto);
        assertEquals(expectedVendaProduto, vendaProduto.getCodigo());

    }

    @Test
    @DisplayName("Teste do metodo listaProduto que retorna uma lista de objetos de itens vendidos")
    public void listaProdutoByCodigoVenda() {
        List<Object> prodList = vendaProdutoService.listaProdutosVenda(VendaFactory.createVendaValid());
        Assertions.assertThat(prodList)
                .isNotNull()
                .isNotEmpty()
                .hasSize(1);
    }
    @Test
    @DisplayName("Teste do metodo buscaQtdProduto")
    public void buscaQtdProduto (){
        Venda venda = VendaFactory.createVendaValid();
        List<Object[]> list = vendaProdutoService.buscaQtdProduto(venda.getCodigo());
        Assertions.assertThat(list)
                .isNotNull()
                .isNotEmpty()
                .hasSize(1);
    }
    @Test
    @DisplayName("Teste do metodo remove")
    public void remove (){
        Assertions.assertThatCode(() ->vendaProdutoService.remove(VendaProdutoFactory.createVendaProdutoValid()))
                .doesNotThrowAnyException();
    }


    @Test
    @DisplayName("Teste do metodo removeProduto")
    public void removeProduto (){
        Assertions.assertThatCode(() ->vendaProdutoService.removeProduto(VendaProdutoFactory.createVendaProdutoValid().getCodigo()))
                .doesNotThrowAnyException();
    }
}
