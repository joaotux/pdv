package net.originmobi.pdv.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.originmobi.pdv.model.Parcela;
import net.originmobi.pdv.model.RecebimentoParcela;
import net.originmobi.pdv.repository.RecebimentoParcelaRepository;

@Service
public class RecebimentoParcelaService {

	@Autowired
	private RecebimentoParcelaRepository recebimentoparcelas;

	public List<RecebimentoParcela> lista() {
		return recebimentoparcelas.findAll();
	}

	public List<Parcela> parcelasDoReceber(Long recebimento) {
		return recebimentoparcelas.parcelasDoRecebimento(recebimento);
	}

}
