package net.originmobi.pdv.utilitarios;

import net.originmobi.pdv.enumerado.produto.ProdutoBalanca;
import net.originmobi.pdv.model.Produto;

import java.util.ArrayList;
import java.util.List;

public class ProdutoFactory {

    public static Produto createProdutoValid (){
        Produto produto = new Produto();
        produto.setCodigo(10L);
        produto.setDescricao("This is a Product description");
        produto.setValorVenda(100.00);
        produto.setBalanca(ProdutoBalanca.SIM);
        return  produto;
    }
    public static List<Produto> createListProdutoValid(){
        List<Produto> produtos = new ArrayList<>();
        produtos.add(createProdutoValid());
        return produtos;
    }
}
