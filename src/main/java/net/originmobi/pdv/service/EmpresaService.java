package net.originmobi.pdv.service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import net.originmobi.pdv.model.Cidade;
import net.originmobi.pdv.model.Empresa;
import net.originmobi.pdv.model.EmpresaParametro;
import net.originmobi.pdv.model.Endereco;
import net.originmobi.pdv.model.RegimeTributario;
import net.originmobi.pdv.repository.EmpresaParametrosRepository;
import net.originmobi.pdv.repository.EmpresaRepository;

@Service
public class EmpresaService {

	@Autowired
	private EmpresaRepository empresas;

	@Autowired
	private EmpresaParametrosRepository parametros;

	@Autowired
	private RegimeTributarioService regimes;

	@Autowired
	private CidadeService cidades;

	@Autowired
	private EnderecoService enderecos;

	public void cadastro(Empresa empresa) {

		try {
			empresas.save(empresa);
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public Optional<Empresa> verificaEmpresaCadastrada() {
		Optional<Empresa> empresa = empresas.buscaEmpresaCadastrada();

		if (empresa.isPresent())
			return empresa;

		Optional<Empresa> empresaOptiona = Optional.empty();

		return empresaOptiona;
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public String merger(Long codigo, String nome, String nome_fantasia, String cnpj, String ie, int serie,
			int ambiente, Long codRegime, Long codendereco, Long codcidade, String rua, String bairro, String numero,
			String cep, String referencia, Double aliqCalcCredito) {

		if (codigo != null) {
			try {
				empresas.update(codigo, nome, nome_fantasia, cnpj, ie, codRegime);
			} catch (Exception e) {
				System.out.println(e);
				return "Erro ao salvar dados da empresa, chame o suporte";
			}

			try {
				parametros.update(serie, ambiente, aliqCalcCredito);
			} catch (Exception e) {
				System.out.println(e);
				return "Erro ao salvar dados da empresa, chame o suporte";
			}

			try {
				enderecos.update(codendereco, codcidade, rua, bairro, numero, cep, referencia);
			} catch (Exception e) {
				System.out.println(e);
				return "Erro ao salvar dados da empresa, chame o suporte";
			}
		} else {
			EmpresaParametro parametro = new EmpresaParametro();

			try {
				parametro.setAmbiente(ambiente);
				parametro.setSerie_nfe(serie);
				parametro.setpCredSN(aliqCalcCredito);
				parametros.save(parametro);
			} catch (Exception e) {
				System.out.println(e);
				return "Erro ao salvar dados da empresa, chame o suporte";
			}

			Optional<RegimeTributario> tributario = regimes.busca(codRegime);
			Optional<Cidade> cidade = cidades.busca(codcidade);

			LocalDate dataAtual = LocalDate.now();
			Endereco endereco = new Endereco(rua, bairro, numero, cep, referencia, Date.valueOf(dataAtual),
					cidade.get());

			try {
				enderecos.cadastrar(endereco);
			} catch (Exception e) {
				System.out.println(e);
				return "Erro ao salvar dados da empresa, chame o suporte";
			}

			try {
				Empresa empresa = new Empresa(nome, nome_fantasia, cnpj, ie, tributario.get(), endereco, parametro);
				empresas.save(empresa);
			} catch (Exception e) {
				System.out.println(e);
				return "Erro ao salvar dados da empresa, chame o suporte";
			}
		}

		return "Empresa salva com sucesso";
	}

}
