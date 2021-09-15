package net.originmobi.pdv.integracao.dados;


import net.originmobi.pdv.enumerado.caixa.CaixaTipo;
import net.originmobi.pdv.model.Caixa;
import net.originmobi.pdv.utilitarios.CaixaFactory;
import java.util.HashMap;

public class DadosTesteCaixa {

    public static Caixa caixaCompleto(){
        return CaixaFactory.createValidCaixa(CaixaTipo.CAIXA);
    }

    public static HashMap<String, String> requestCaixaCadastroCompleto(){
        HashMap<String, String> request = new HashMap<>();
        Caixa caixa = caixaCompleto();
        request.put("descricao", caixa.getDescricao());
        request.put("tipo", caixa.getTipo().toString());
        request.put("valor_abertura", caixa.getValorAbertura().toString());
        request.put("agencia", caixa.getAgencia());
        request.put("conta", caixa.getConta());
        return request;
    }

    public static HashMap<String, String> requestCaixaSuprimentoCompleto(){
        HashMap<String, String> request = new HashMap<>();
        Caixa caixa = caixaCompleto();
        request.put("valor", "350.30");
        request.put("observacao", "Caixa Teste");
        request.put("caixa", "1");
        return request    ;
    }
}
