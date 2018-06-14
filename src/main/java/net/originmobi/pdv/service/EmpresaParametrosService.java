package net.originmobi.pdv.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.originmobi.pdv.model.EmpresaParametro;
import net.originmobi.pdv.repository.EmpresaParametrosRepository;

@Service
public class EmpresaParametrosService {

	@Autowired
	private EmpresaParametrosRepository parametros;

	public List<EmpresaParametro> lista() {
		return parametros.findAll();
	}

	public void cadastro(EmpresaParametro parametro) {
		parametros.save(parametro);
	}

}
