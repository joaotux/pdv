package net.originmobi.pdv.service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.originmobi.pdv.model.Grupo;
import net.originmobi.pdv.repository.GrupoRepository;

@Service
public class GrupoService {

	@Autowired
	private GrupoRepository grupos;

	private LocalDate dataAtual = LocalDate.now();

	public void cadastrar(Grupo grupo) {
		grupo.setData_cadastro(Date.valueOf(dataAtual));
		grupos.save(grupo);
	}

	public List<Grupo> lista() {
		return grupos.findAll();
	}
	
	public Optional<Grupo> busca(Long codigo) {
		return grupos.findById(codigo);
	}

}
