package net.originmobi.pdv.service;

import net.originmobi.pdv.model.PagamentoTipo;
import net.originmobi.pdv.repository.PagamentoTipoRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@Service
public class PagamentoTipoService {

    private final LocalDate dataAtual = LocalDate.now();

    @Autowired
    private PagamentoTipoRespository pagamentotipo;

    public void cadastrar(PagamentoTipo tipo) {
        tipo.setDataCadastro(Date.valueOf(dataAtual));

        String[] quantidade = tipo.getFormaPagamento().replace("/", " ").split(" ");

        tipo.setQtdParcelas(quantidade.length);

        pagamentotipo.save(tipo);
    }

    public List<PagamentoTipo> listar() {
        return pagamentotipo.findAll();
    }

    public PagamentoTipo busca(Long codigo) {
        return pagamentotipo.findByCodigoIn(codigo);
    }

    public String qtdParcelas(Long codigo) {
        return pagamentotipo.quantidadeParcelar(codigo);
    }

}
