package net.originmobi.pdv.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.originmobi.pdv.model.TipoAmbiente;
import net.originmobi.pdv.repository.TipoAmbienteRepository;

@Service
public class TipoAmbienteServer {

	@Autowired
	private TipoAmbienteRepository ambientes;

	public List<TipoAmbiente> lista() {
		return ambientes.findAll();
	}

}
