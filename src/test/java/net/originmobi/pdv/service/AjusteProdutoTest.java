package net.originmobi.pdv.service;

import net.originmobi.pdv.model.AjusteProduto;
import net.originmobi.pdv.repository.AjusteProdutoRepository;
import net.originmobi.pdv.utilitarios.AjusteFactory;
import net.originmobi.pdv.utilitarios.AjusteProdutoFactory;
import net.originmobi.pdv.utilitarios.ProdutoFactory;
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

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
public class AjusteProdutoTest {

    @InjectMocks
    private AjusteProdutoService ajusteProdutoServiceMock;

    @Mock
    private AjusteProdutoRepository ajusteProdutoRepositoryMock;
    @Mock
    private AjusteService ajusteServiceMock;
    @Mock
    private ProdutoService produtoServiceMock;

    @BeforeEach
    void setUp (){

        BDDMockito.when(ajusteProdutoRepositoryMock.findByAjusteCodigoEquals(ArgumentMatchers.anyLong()))
                .thenReturn((AjusteProdutoFactory.createListAjusteProdutoValid()));

        BDDMockito.when(ajusteProdutoRepositoryMock.buscaProdAjuste(ArgumentMatchers.anyLong(), ArgumentMatchers.anyLong()))
                .thenReturn(0);

        BDDMockito.when(ajusteServiceMock.busca(ArgumentMatchers.anyLong()))
                .thenReturn(Optional.of(AjusteFactory.createValidAjuste()));

        BDDMockito.when(produtoServiceMock.busca(ArgumentMatchers.anyLong()))
                .thenReturn(ProdutoFactory.createProdutoValid());

        BDDMockito.doNothing().when(ajusteProdutoRepositoryMock).insereProduto(ArgumentMatchers.anyLong(),
                ArgumentMatchers.anyLong(), ArgumentMatchers.anyInt(), ArgumentMatchers.anyInt(), ArgumentMatchers.anyInt());

        BDDMockito.doNothing().when(ajusteProdutoRepositoryMock).removeProduto(ArgumentMatchers.anyLong(), ArgumentMatchers.anyLong());
    }

    @Test
    @DisplayName("teste do metodo listaProdutosAjuste")
    public  void listaProdutosAjuste (){
        Long expectedCodigo = AjusteProdutoFactory.createAjusteProdutoValid().getAjuste().getCodigo();
        List<AjusteProduto> ajusteProdutos = ajusteProdutoServiceMock.listaProdutosAjuste(expectedCodigo);
        Assertions.assertThat(ajusteProdutos)
                .isNotNull()
                .isNotEmpty()
                .hasSize(1);

        Assertions.assertThat(ajusteProdutos.get(0).getAjuste().getCodigo()).isEqualTo(expectedCodigo);

    }

    @Test
    @DisplayName("teste do metodo  buscaProdAjust")
    public  void  buscaProdAjust(){
        int expectedQtd = 0;
        AjusteProduto ajusteProduto = AjusteProdutoFactory.createAjusteProdutoValid();
        int qtd = ajusteProdutoServiceMock.buscaProdAjust(ajusteProduto.getAjuste().getCodigo(), ajusteProduto.getProduto().getCodigo());
        assertEquals(qtd, expectedQtd);
    }

    @Test
    @DisplayName("teste do metodo addProduto")
    public  void addProduto(){
        String expectedResposta = "Ajuste processado com sucesso";
        AjusteProduto ajusteProduto = AjusteProdutoFactory.createAjusteProdutoValid();
        String resposta = ajusteProdutoServiceMock.addProduto(ajusteProduto.getAjuste().getCodigo(),
                ajusteProduto.getProduto().getCodigo(), 55);
        assertEquals(expectedResposta, resposta);


    }

    @Test
    @DisplayName("teste do metodo removeProduto")
    public  void removeProduto(){
        AjusteProduto ajusteProduto = AjusteProdutoFactory.createAjusteProdutoValid();
        String expectedResposta = "Produto removido com sucesso";
        String resposta = ajusteProdutoServiceMock.removeProduto(ajusteProduto.getAjuste().getCodigo(), ajusteProduto.getCodigo());
        assertEquals(expectedResposta, resposta);
    }
}
