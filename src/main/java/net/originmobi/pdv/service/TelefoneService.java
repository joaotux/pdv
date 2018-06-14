package net.originmobi.pdv.service;

import java.sql.Date;
import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.originmobi.pdv.model.Telefone;
import net.originmobi.pdv.repository.TelefoneRepository;

@Service
public class TelefoneService {

	@Autowired
	private TelefoneRepository telefones;

	LocalDate dataAtual = LocalDate.now();

	public Telefone cadastrar(Telefone telefone) {
		telefone.setData_cadastro(Date.valueOf(dataAtual));
		try {
			telefones.save(telefone);
		} catch (Exception e) {
			e.getStackTrace();
		}

		return telefone;
	}

	public Telefone telefoneCodigo(Long codigo) {
		return telefones.findByCodigoIn(codigo);
	}
}
