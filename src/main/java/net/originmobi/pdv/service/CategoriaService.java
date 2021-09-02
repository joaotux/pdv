package net.originmobi.pdv.service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.originmobi.pdv.model.Categoria;
import net.originmobi.pdv.repository.CategoriaRepository;

@Service
public class CategoriaService {

	private LocalDate dataAtual = LocalDate.now();

	@Autowired
	private CategoriaRepository categorias;

	public void cadastrar(Categoria categoria) {
		categoria.setDataCadastro(Date.valueOf(dataAtual));
		categorias.save(categoria);
	}

	public List<Categoria> lista() {
		return categorias.findAll();
	}

	public Optional<Categoria> busca(Long codigo) {
		return categorias.findById(codigo);
	}

}
