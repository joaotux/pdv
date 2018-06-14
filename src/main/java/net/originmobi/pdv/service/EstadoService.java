package net.originmobi.pdv.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.originmobi.pdv.model.Estado;
import net.originmobi.pdv.repository.EstadoRepository;

@Service
public class EstadoService {

	@Autowired
	private EstadoRepository estados;

	public List<Estado> lista() {
		return estados.findAll();
	}

}
