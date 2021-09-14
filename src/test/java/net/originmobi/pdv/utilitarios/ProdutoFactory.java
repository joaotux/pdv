package net.originmobi.pdv.utilitarios;

import net.originmobi.pdv.enumerado.Ativo;
import net.originmobi.pdv.enumerado.produto.ProdutoBalanca;
import net.originmobi.pdv.enumerado.produto.ProdutoControleEstoque;
import net.originmobi.pdv.enumerado.produto.ProdutoSubstTributaria;
import net.originmobi.pdv.enumerado.produto.ProdutoVendavel;
import net.originmobi.pdv.model.Produto;
import net.originmobi.pdv.model.ProdutoEstoque;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class ProdutoFactory {

    public static Produto createProdutoValid (){
        Produto produto = new Produto();
        produto.setCodigo(0L);
        produto.setDescricao("This is a Product description");
        produto.setValorVenda(100.00);
        produto.setValorCusto(50.00);
        produto.setValorBalanca(100.00);
        produto.setDataValidade(new Date(System.currentTimeMillis()));
        produto.setDataCadastro(new Date(System.currentTimeMillis()));
        produto.setAtivo(Ativo.ATIVO);
        produto.setUnidade("Unidade");
        produto.setSubtributaria(ProdutoSubstTributaria.SIM);
        produto.setControlaEstoque(ProdutoControleEstoque.SIM);
        produto.setEstoque(createEstoque());
        produto.setBalanca(ProdutoBalanca.SIM);
        produto.setVendavel(ProdutoVendavel.SIM);
        produto.setNcm("N C M");
        produto.setCest("C E S T");
        return  produto;
    }

    public static Produto createProdutoValidToUpdate (){
        Produto produto = new Produto();
        produto.setCodigo(10L);
        produto.setDescricao("This is a updated Product description ");
        produto.setValorVenda(100.00);
        produto.setValorCusto(50.00);
        produto.setValorBalanca(100.00);
        produto.setDataValidade(new Date(System.currentTimeMillis()));
        produto.setDataCadastro(new Date(System.currentTimeMillis()));
        produto.setAtivo(Ativo.ATIVO);
        produto.setUnidade("Unidade");
        produto.setNcm("N C M");
        produto.setCest("C E S T");
        produto.setSubtributaria(ProdutoSubstTributaria.SIM);
        produto.setControlaEstoque(ProdutoControleEstoque.SIM);
        produto.setEstoque(createEstoque());
        produto.setBalanca(ProdutoBalanca.SIM);
        produto.setVendavel(ProdutoVendavel.SIM);
        return  produto;
    }
    public static List<Produto> createListProdutoValid(){
        List<Produto> produtos = new ArrayList<>();
        produtos.add(createProdutoValid());
        return produtos;
    }

    public static  ProdutoEstoque createEstoque () {

        ProdutoEstoque produtoEstoque = new ProdutoEstoque();
        produtoEstoque.setCodigo(10L);
        produtoEstoque.setQtd(100);
        return  produtoEstoque;
    }
}
