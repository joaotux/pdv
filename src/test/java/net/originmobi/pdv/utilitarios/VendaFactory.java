package net.originmobi.pdv.utilitarios;

import net.originmobi.pdv.enumerado.VendaSituacao;
import net.originmobi.pdv.model.Venda;

import java.sql.Timestamp;

public class VendaFactory {

    public static Venda createVendaValid(){
        Venda venda = new Venda();
        venda.setCodigo(10L);
        venda.setUsuario(UsuarioFactory.createUserValid());
        venda.setDataCadastro(new Timestamp(System.currentTimeMillis()));
        venda.setSituacao(VendaSituacao.FECHADA);
        venda.setObservacao("CONCLUIDA");
        venda.setValorProdutos(100.00);
        venda.setDataFinalizado(new Timestamp(System.currentTimeMillis()));
        venda.setValorTotal(1000.00);
        venda.setProduto(ProdutoFactory.createListProdutoValid());
        return venda;

    }

}
