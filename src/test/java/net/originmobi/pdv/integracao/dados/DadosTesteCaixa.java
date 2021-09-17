package net.originmobi.pdv.integracao.dados;


import net.originmobi.pdv.enumerado.caixa.CaixaTipo;
import net.originmobi.pdv.model.Caixa;
import net.originmobi.pdv.utilitarios.CaixaFactory;
import org.springframework.util.LinkedMultiValueMap;

public class DadosTesteCaixa {

    public static Caixa caixaCompleto(){
        return CaixaFactory.createValidCaixaToBeClosed(CaixaTipo.CAIXA);
    }

    public static Caixa caixaFechado() {
        return CaixaFactory.createValidCaixa(CaixaTipo.CAIXA);
    }

    public static LinkedMultiValueMap<String, String> requestCaixaCadastroCompleto(){
        LinkedMultiValueMap<String, String> request = new LinkedMultiValueMap<>();
        Caixa caixa = caixaCompleto();
        request.add("descricao", caixa.getDescricao());
        request.add("tipo", caixa.getTipo().toString());
        request.add("valorAbertura", caixa.getValorAbertura().toString());
        request.add("agencia","agencia");
        request.add("conta", "conta");
        return request;
    }
}
