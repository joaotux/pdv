package net.originmobi.pdv.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.originmobi.pdv.model.PagarTipo;
import net.originmobi.pdv.repository.PagarTipoRepository;

@Service
public class PagarTipoService {

	@Autowired
	private PagarTipoRepository pagartipos;

	public List<PagarTipo> lista() {
		return pagartipos.findAll();
	}
	
	public Optional<PagarTipo> busca(Long codigo) {
		return pagartipos.findById(codigo);
	}

}
