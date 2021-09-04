package net.originmobi.pdv.service;

import net.originmobi.pdv.enumerado.EntradaSaida;
import net.originmobi.pdv.enumerado.produto.ProdutoControleEstoque;
import net.originmobi.pdv.enumerado.produto.ProdutoSubstTributaria;
import net.originmobi.pdv.filter.ProdutoFilter;
import net.originmobi.pdv.model.Produto;
import net.originmobi.pdv.repository.ProdutoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {

    private final Logger logger = LoggerFactory.getLogger(getClass());
    private final LocalDate dataAtual = LocalDate.now();
    @Autowired
    private ProdutoRepository produtos;
    @Autowired
    private VendaProdutoService vendaProdutos;

    public List<Produto> listar() {
        return produtos.findAll();
    }

    public List<Produto> listaProdutosVendaveis() {
        return produtos.produtosVendaveis();
    }

    public Produto busca(Long codigoProduto) {
        return produtos.findByCodigoIn(codigoProduto);
    }

    public Optional<Produto> buscaProduto(Long codigo) {
        return produtos.findById(codigo);
    }

    public Page<Produto> filter(ProdutoFilter filter, Pageable pageable) {
        String descricao = filter.getDescricao() == null ? "%" : filter.getDescricao();
        return produtos.findByDescricaoContaining(descricao, pageable);
    }

    public String merger(Long codprod, Long codforne, Long codcategoria, Long codgrupo, int balanca, String descricao,
                         Double valorCusto, Double valorVenda, java.util.Date dataValidade, String controleEstoque,
                         String situacao, String unitario, ProdutoSubstTributaria subtribu, String ncm, String cest,
                         Long tributacao, Long modbc, String vendavel) {

        if (codprod == 0) {
            try {
                produtos.insere(codforne, codcategoria, codgrupo, balanca, descricao, valorCusto, valorVenda,
                        dataValidade, controleEstoque, situacao, unitario, subtribu.ordinal(), Date.valueOf(dataAtual),
                        ncm, cest, tributacao, modbc, vendavel);
            } catch (Exception e) {
                logger.error(e.getMessage(), e);
                return "Erro a cadastrar produto, chame o suporte";
            }
        } else {

            try {
                produtos.atualiza(codprod, codforne, codcategoria, codgrupo, balanca, descricao, valorCusto, valorVenda,
                        dataValidade, controleEstoque, situacao, unitario, subtribu.ordinal(), ncm, cest, tributacao,
                        modbc, vendavel);

                return "Produto atualizado com sucesso";
            } catch (Exception e) {
                logger.error(e.getMessage(), e);
                return "Erro a atualizar produto, chame o suporte";
            }

        }

        return "Produdo cadastrado com sucesso";
    }

    @SuppressWarnings("static-access")
    public void movimentaEstoque(Long codvenda, EntradaSaida tipo) {
        List<Object[]> resultado = vendaProdutos.buscaQtdProduto(codvenda);

        for (Object[] objects : resultado) {
            Long codprod = Long.decode(objects[0].toString());
            int qtd = Integer.parseInt(objects[1].toString());

            Produto produto = produtos.findByCodigoIn(codprod);

            if (produto.getControlaEstoque().equals(ProdutoControleEstoque.SIM)) {

                // estoque atual do produto
                int qtdEstoque = produtos.saldoEstoque(codprod);
                String origemOperacao = "Venda " + codvenda.toString();

                if (qtd <= qtdEstoque) {
                    produtos.movimentaEstoque(codprod, tipo.SAIDA.toString(), qtd, origemOperacao,
                            Date.valueOf(dataAtual));
                } else {
                    throw new RuntimeException(
                            "O produto de código " + codprod + " não tem estoque suficiente, verifique");
                }
            } else {
                logger.warn("Produto não controla estoque");
            }
        }

    }

    public void ajusteEstoque(Long codprod, int qtd, EntradaSaida tipo, String origemOperacao, Date dataMovimentacao) {
        Produto produto = produtos.findByCodigoIn(codprod);

        if (produto.getControlaEstoque().equals(ProdutoControleEstoque.NAO))
            throw new RuntimeException("O produto de código " + codprod + " não controla estoque, verifique");

        produtos.movimentaEstoque(codprod, tipo.toString(), qtd, origemOperacao, dataMovimentacao);

    }

}
