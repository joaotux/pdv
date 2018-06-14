package net.originmobi.pdv.service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.originmobi.pdv.filter.FornecedorFilter;
import net.originmobi.pdv.model.Fornecedor;
import net.originmobi.pdv.repository.FornecedorRepository;

@Service
public class FornecedorService {

	@Autowired
	private FornecedorRepository fornecedores;

	LocalDate dataAtual = LocalDate.now();

	public String cadastrar(Fornecedor fornecedor) {
		String cnpj = fornecedor.getCnpj().replaceAll("\\D", "");

		if (fornecedor.getCodigo() != null) {
			Fornecedor dadosFornecedor = fornecedores.findByCodigoIn(fornecedor.getCodigo());

			dadosFornecedor.getEndereco().setCidade(fornecedor.getEndereco().getCidade());
			dadosFornecedor.getEndereco().setRua(fornecedor.getEndereco().getRua());
			dadosFornecedor.getEndereco().setCep(fornecedor.getEndereco().getCep());
			dadosFornecedor.getEndereco().setBairro(fornecedor.getEndereco().getBairro());
			dadosFornecedor.getEndereco().setNumero(fornecedor.getEndereco().getNumero());
			dadosFornecedor.getEndereco().setReferencia(fornecedor.getEndereco().getReferencia());

			dadosFornecedor.getTelefone().get(0).setFone(fornecedor.getTelefone().get(0).getFone());
			dadosFornecedor.getTelefone().get(0).setTipo(fornecedor.getTelefone().get(0).getTipo());

			dadosFornecedor.setCnpj(cnpj);
			dadosFornecedor.setNome(fornecedor.getNome());
			dadosFornecedor.setNome_fantasia(fornecedor.getNome_fantasia());
			dadosFornecedor.setInscricao_estadual(fornecedor.getInscricao_estadual());
			dadosFornecedor.setAtivo(fornecedor.getAtivo());
			dadosFornecedor.setObservacao(fornecedor.getObservacao());

			fornecedores.save(dadosFornecedor);
		} else {

			if (fornecedores.findByCnpjIn(fornecedor.getCnpj()) == null) {
				fornecedor.setCnpj(cnpj);
				fornecedor.setData_cadastro(Date.valueOf(dataAtual));
				fornecedores.save(fornecedor);
			} else {
				return "CNPJ já cadastrado";
			}
		}

		return "Fornecedor salvo com sucesso";
	}

	public List<Fornecedor> lista() {
		return fornecedores.findAll();
	}

	public List<Fornecedor> busca(FornecedorFilter filter) {
		String nome = filter.getNome() == null ? "%" : filter.getNome();
		return fornecedores.findByNomeContaining(nome);
	}

	public Optional<Fornecedor> busca(Long codigo) {
		return fornecedores.findById(codigo);
	}

}
