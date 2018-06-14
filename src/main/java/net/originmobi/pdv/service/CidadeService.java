package net.originmobi.pdv.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.originmobi.pdv.model.Cidade;
import net.originmobi.pdv.repository.CidadeRepository;

@Service
public class CidadeService {

	@Autowired
	private CidadeRepository cidades;

	public List<Cidade> lista() {
		return cidades.findAll();
	}

	public Optional<Cidade> busca(Long codcidade) {
		return cidades.findById(codcidade);
	}

}
