package net.originmobi.pdv.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.originmobi.pdv.model.Permissoes;
import net.originmobi.pdv.repository.PermissoesRespository;

@Service
public class PermissoesService {

	@Autowired
	private PermissoesRespository permissoes;

	public List<Permissoes> lista() {
		return permissoes.findAll();
	}
	
	public List<Permissoes> lista(Long codGrupo) {
		return permissoes.listaPermissoesDoGrupo(codGrupo);
	}
}
