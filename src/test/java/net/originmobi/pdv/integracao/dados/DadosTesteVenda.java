package net.originmobi.pdv.integracao.dados;

import net.originmobi.pdv.model.PagamentoTipo;
import net.originmobi.pdv.model.Titulo;
import net.originmobi.pdv.model.TituloTipo;
import net.originmobi.pdv.model.Venda;
import net.originmobi.pdv.utilitarios.VendaFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.Collections;
import java.util.Optional;

public class DadosTesteVenda {

    public static Page<Venda> pageVendaCompleto(){
        Pageable pageable = PageRequest.of(1, 1);
        return new PageImpl<>(Collections.singletonList(VendaFactory.createVendaValid()), pageable, 1);
    }

    public static PagamentoTipo pagamentoTipoCompleto(){
        PagamentoTipo tipo = new PagamentoTipo();
        tipo.setQtdParcelas(1);
        tipo.setCodigo(1L);
        tipo.setDescricao("A vista");
        tipo.setFormaPagamento("00");
        return tipo;
    }

    public static Optional<Titulo> tituloCompleto() {
        Titulo titulo = new Titulo();
        titulo.setCodigo(1L);
        titulo.setDescricao("DINHEIRO");
        titulo.setMaquina(null);

        TituloTipo tituloTipo = new TituloTipo();
        tituloTipo.setCodigo(20L);
        tituloTipo.setDescricao("DINHEIRO");
        tituloTipo.setSigla("deb");
        titulo.setTipo(tituloTipo);

        return Optional.of(titulo);
    }
}
