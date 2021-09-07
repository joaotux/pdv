package net.originmobi.pdv.utilitarios;

import net.originmobi.pdv.enumerado.caixa.CaixaTipo;
import net.originmobi.pdv.model.Caixa;
import net.originmobi.pdv.model.Usuario;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CaixaFactory {

    public static List<Caixa> createValidListCaixa (CaixaTipo tipo) {
        Caixa caixa = createValidCaixaToBeClosed(tipo);
        List<Caixa> caixas = new ArrayList<>();
        caixas.add(caixa);
        return caixas;
    }

    public static Caixa createValidCaixaToBeClosed (CaixaTipo tipo) {
        Usuario user = UsuarioFactory.createUserValid();
        Caixa caixa = new Caixa();
        caixa.setCodigo(11L);
        caixa.setDescricao("descricao do caixa");
        caixa.setTipo(tipo);
        caixa.setValorAbertura(0.00);
        caixa.setValorTotal(0.00);
        caixa.setDataCadastro(new Date(System.currentTimeMillis()));
        caixa.setUsuario(user);
        return  caixa;
    }
    public static Caixa createValidCaixa (CaixaTipo tipo) {
        Usuario user = UsuarioFactory.createUserValid();
        Caixa caixa = new Caixa();
        caixa.setCodigo(11L);
        caixa.setDescricao("descricao do caixa");
        caixa.setTipo(tipo);
        caixa.setValorAbertura(0.00);
        caixa.setValorTotal(0.00);
        caixa.setDataCadastro(new Date(System.currentTimeMillis()));
        caixa.setDataFechamento(new Timestamp(System.currentTimeMillis()));
        caixa.setUsuario(user);
        return  caixa;
    }
}
