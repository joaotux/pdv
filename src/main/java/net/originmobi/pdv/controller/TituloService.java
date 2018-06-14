package net.originmobi.pdv.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.originmobi.pdv.model.Titulo;
import net.originmobi.pdv.repository.TituloRepository;

@Service
public class TituloService {

	@Autowired
	private TituloRepository titulos;

	public List<Titulo> lista() {
		return titulos.findAll();
	}

	public Optional<Titulo> busca(Long codigo) {
		return titulos.findById(codigo);
	}

	public void cadastro(Titulo titulo) {
		try {
			titulos.save(titulo);
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public String remover(Long codigo) {
		try {
			titulos.deleteById(codigo);
		} catch (Exception e) {
			System.out.println(e);
			throw new RuntimeException("Erro ao tentar remover este registro, chame o suporte");
		}

		return "Registro removido com sucesso";
	}

}
