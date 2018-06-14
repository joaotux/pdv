package net.originmobi.pdv.service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.originmobi.pdv.model.Endereco;
import net.originmobi.pdv.repository.EnderecoRepository;

@Service
public class EnderecoService {

	@Autowired
	private EnderecoRepository enderecos;

	LocalDate dataAtual = LocalDate.now();

	public List<Endereco> lista() {
		return enderecos.findAll();
	}

	public Endereco cadastrar(Endereco endereco) {
		endereco.setData_cadastro(Date.valueOf(dataAtual));

		try {
			enderecos.save(endereco);
		} catch (Exception e) {
			e.getStackTrace();
		}

		return endereco;
	}

	public Endereco enderecoCodigo(Long codigo) {
		return enderecos.findByCodigoIn(codigo);
	}

	public void update(Long codigo, Long codcidade, String rua, String bairro, String numero, String cep,
			String referencia) {
		enderecos.update(codigo, codcidade, rua, bairro, numero, cep, referencia);
	}
}
