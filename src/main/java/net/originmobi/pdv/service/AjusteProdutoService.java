package net.originmobi.pdv.service;

import net.originmobi.pdv.enumerado.ajuste.AjusteStatus;
import net.originmobi.pdv.model.Ajuste;
import net.originmobi.pdv.model.AjusteProduto;
import net.originmobi.pdv.model.Produto;
import net.originmobi.pdv.repository.AjusteProdutoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AjusteProdutoService {

    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private AjusteProdutoRepository ajusteprodutos;
    @Autowired
    private ProdutoService produtos;
    @Autowired
    private AjusteService ajustes;

    public List<AjusteProduto> listaProdutosAjuste(Long codAjuste) {
        return ajusteprodutos.findByAjusteCodigoEquals(codAjuste);
    }

    public int buscaProdAjust(Long codAjuste, Long codProd) {
        return ajusteprodutos.buscaProdAjuste(codAjuste, codProd);
    }

    public String addProduto(Long codajuste, Long codprod, int qtdAlteracao) {
        Optional<Ajuste> ajuste = ajustes.busca(codajuste);

        ajuste.map(Ajuste::getStatus).ifPresent(ajusteStatus -> {
            if (ajusteStatus.equals(AjusteStatus.PROCESSADO))
                throw new RuntimeException("Ajuste já esta processado");
        });

        Produto produto = produtos.busca(codprod);
        int estoqueAqual = produto.getEstoque().getQtd();

        int tem = buscaProdAjust(codajuste, codprod);

        if (tem > 0)
            throw new RuntimeException("Este produto já existe neste ajuste");

        if (qtdAlteracao == 0)
            throw new RuntimeException("Quantidade inválido");

        int novoEstoque = estoqueAqual + qtdAlteracao;

        try {
            ajusteprodutos.insereProduto(codajuste, codprod, estoqueAqual, qtdAlteracao, novoEstoque);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new RuntimeException("Erro ao tentar inserir produto no ajuste, chame o suporte");
        }

        return "Ajuste processado com sucesso";
    }

    public String removeProduto(Long codajuste, Long coditem) {
        Optional<Ajuste> ajuste = ajustes.busca(codajuste);

        ajuste.map(Ajuste::getStatus).ifPresent(ajusteStatus -> {
            if (ajusteStatus.equals(AjusteStatus.PROCESSADO))
                throw new RuntimeException("Ajuste já esta processado");
        });

        try {
            ajusteprodutos.removeProduto(codajuste, coditem);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new RuntimeException("Erro ao tentar remover produto do ajuste, chame o suporte");
        }

        return "Produto removido com sucesso";
    }

}
