package net.originmobi.pdv.utilitarios;

import net.originmobi.pdv.enumerado.ajuste.AjusteStatus;
import net.originmobi.pdv.model.Ajuste;
import net.originmobi.pdv.model.AjusteProduto;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class AjusteProdutoFactory {
    public static AjusteProduto createAjusteProdutoValid(){

        AjusteProduto ajusteProduto = new AjusteProduto();
        ajusteProduto.setCodigo(10L);
        ajusteProduto.setEstoqueAtual(100);
        ajusteProduto.setQtdAlteracao(1);
        ajusteProduto.setProduto(ProdutoFactory.createProdutoValid());
        ajusteProduto.setAjuste(createValidAjusteToProduto());
        return  ajusteProduto;
    }

    public static List<AjusteProduto> createListAjusteProdutoValid(){
        List<AjusteProduto> ajusteProdutos = new ArrayList<>();
        ajusteProdutos.add(createAjusteProdutoValid());
        return  ajusteProdutos;
    }

    public static Ajuste createValidAjusteToProduto(){

        Ajuste ajuste = new Ajuste();
        ajuste.setCodigo(10L);
        ajuste.setDataCadastro(new Date(System.currentTimeMillis()));
        ajuste.setObservacao("Ajuste valido");
        ajuste.setUsuario("gerente");
        ajuste.setStatus(AjusteStatus.APROCESSAR);
        return ajuste;
    }
}
