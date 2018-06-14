package net.originmobi.pdv.service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.originmobi.pdv.model.Empresa;
import net.originmobi.pdv.model.Tributacao;
import net.originmobi.pdv.repository.TributacaoRepository;

@Service
public class TributacaoService {

	@Autowired
	private TributacaoRepository tributacoes;

	@Autowired
	private EmpresaService empresas;

	public String cadastro(Tributacao tributacao) {
		LocalDate dataAtual = LocalDate.now();

		Tributacao tribut = null;

		Optional<Empresa> empresa = empresas.verificaEmpresaCadastrada();

		if (!empresa.isPresent())
			return "sem empresa";

		try {
			tributacao.setEmpresa(empresa.get());
			tributacao.setData_cadastro(Date.valueOf(dataAtual));
			tribut = tributacoes.save(tributacao);
		} catch (Exception e) {
			System.out.println("Erro " + e.getMessage());
		}

		return tribut.getCodigo().toString();
	}

	public List<Tributacao> lista() {
		return tributacoes.findAll();
	}

}
