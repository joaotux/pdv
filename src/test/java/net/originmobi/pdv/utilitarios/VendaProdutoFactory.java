package net.originmobi.pdv.utilitarios;

import net.originmobi.pdv.model.VendaProduto;
import java.util.ArrayList;
import java.util.List;

public class VendaProdutoFactory {

    public static VendaProduto createVendaProdutoValid(){
        VendaProduto vendaProduto = new VendaProduto();
        vendaProduto.setCodigo(10L);
        vendaProduto.setProduto(10L);
        vendaProduto.setVenda(10L);
        vendaProduto.setValorBalanca(12.00);
        return vendaProduto;
    }

    public  static List<Object> createListVendaProdutos(){
        List<Object> vendaProdutos = new ArrayList<>();
        VendaProduto vendaProduto = createVendaProdutoValid();
        vendaProdutos.add(vendaProduto);
        return vendaProdutos;
    }

    public static List<Object[]> createListVendaProdutosQTD () {

        List<Object[]> resultado = new ArrayList<>();
        Object[] obj = {10L, 100};
        resultado.add(obj);
        return  resultado;
    }
}
