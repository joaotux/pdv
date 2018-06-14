package net.originmobi.pdv.service;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import net.originmobi.pdv.filter.PagarParcelaFilter;
import net.originmobi.pdv.model.Pagar;
import net.originmobi.pdv.model.PagarParcela;
import net.originmobi.pdv.repository.PagarParcelaRespository;

@Service
public class PagarParcelaService {

	@Autowired
	private PagarParcelaRespository parcelas;

	public void cadastrar(Double vltotal, Double vlrestante, int quitado, Timestamp cadastro, LocalDate vencimento,
			Pagar pagar) {
		try {
			parcelas.geraParcela(vltotal, vlrestante, 0.0, 0.0, 0.0, quitado, cadastro, vencimento, pagar);
		} catch (Exception e) {
			e.getStackTrace();
			throw new RuntimeException();
		}
	}

	public PagarParcela merger(PagarParcela parcela) {
		return parcelas.save(parcela);
	}

	public Page<PagarParcela> lista(PagarParcelaFilter filter, Pageable pageable) {
		if (filter.getNome() == null || filter.getNome().equals(""))
			return parcelas.listaOrdenada(pageable);
		else
			return parcelas.listaOrdenada(filter.getNome(), pageable);
	}

	public Optional<PagarParcela> busca(Long codigo) {
		return parcelas.findById(codigo);
	}

	public String totalParagarAberto() {
		return parcelas.valorDespesasAbertas();
	}

}
