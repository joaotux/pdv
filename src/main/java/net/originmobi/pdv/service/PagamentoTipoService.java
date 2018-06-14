package net.originmobi.pdv.service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.originmobi.pdv.model.PagamentoTipo;
import net.originmobi.pdv.repository.PagamentoTipoRespository;

@Service
public class PagamentoTipoService {

	@Autowired
	private PagamentoTipoRespository pagamentotipo;

	private LocalDate dataAtual = LocalDate.now();

	public void cadastrar(PagamentoTipo tipo) {
		tipo.setData_cadastro(Date.valueOf(dataAtual));
		
		String[] quantidade = tipo.getFormaPagamento().replaceAll("/", " ").split(" ");
		
		tipo.setQtd_parcelas(quantidade.length);

		pagamentotipo.save(tipo);
	}

	public List<PagamentoTipo> listar() {
		return pagamentotipo.findAll();
	}

	public PagamentoTipo busca(Long codigo) {
		return pagamentotipo.findByCodigoIn(codigo);
	}

	public String qtdParcelas(Long codigo) {
		String qtd = pagamentotipo.quantidadeParcelar(codigo);
		return qtd;
	}

}
