package net.originmobi.pdv.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.originmobi.pdv.model.TituloTipo;
import net.originmobi.pdv.repository.TituloTipoRepository;

@Service
public class TituloTipoService {

	@Autowired
	private TituloTipoRepository tipos;

	public List<TituloTipo> lista() {
		return tipos.findAll();
	}

}
