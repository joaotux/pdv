package net.originmobi.pdv.utilitarios;

import net.originmobi.pdv.enumerado.ajuste.AjusteStatus;
import net.originmobi.pdv.model.Ajuste;

import java.sql.Date;

public class AjusteFactory {

    public static Ajuste createValidAjuste(){

        Ajuste ajuste = new Ajuste();
        ajuste.setCodigo(10L);
        ajuste.setDataCadastro(new Date(System.currentTimeMillis()));
        ajuste.setObservacao("Ajuste valido");
        ajuste.setUsuario("gerente");
        ajuste.setStatus(AjusteStatus.APROCESSAR);
        ajuste.setProdutos(AjusteProdutoFactory.createListAjusteProdutoValid());
        return ajuste;
    }

    public static Ajuste createValidAjusteProcessado(){

        Ajuste ajuste = new Ajuste();
        ajuste.setCodigo(10L);
        ajuste.setDataCadastro(new Date(System.currentTimeMillis()));
        ajuste.setObservacao("Ajuste valido processado");
        ajuste.setUsuario("gerente");
        ajuste.setStatus(AjusteStatus.PROCESSADO);
        ajuste.setProdutos(AjusteProdutoFactory.createListAjusteProdutoValid());
        return ajuste;
    }
}
