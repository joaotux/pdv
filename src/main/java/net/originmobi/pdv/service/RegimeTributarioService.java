package net.originmobi.pdv.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.originmobi.pdv.model.RegimeTributario;
import net.originmobi.pdv.repository.RegimeTributarioRepository;

@Service
public class RegimeTributarioService {

	@Autowired
	private RegimeTributarioRepository regimestributarios;

	public List<RegimeTributario> lista() {
		return regimestributarios.findAll();
	}

	public Optional<RegimeTributario> busca(Long codigo) {
		return regimestributarios.findById(codigo);
	}

}
