package net.originmobi.pdv.service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.originmobi.pdv.enumerado.EntradaSaida;
import net.originmobi.pdv.model.TributacaoRegra;
import net.originmobi.pdv.repository.TributacaoRegraRepository;

@Service
public class TributacaoRegraService {

	@Autowired
	private TributacaoRegraRepository regras;

	public List<TributacaoRegra> lista() {
		return regras.findAll();
	}

	public List<TributacaoRegra> lista(Long codtributacao, EntradaSaida tipo) {
		return regras.findByTributacaoCodigoAndTipoEquals(codtributacao, tipo);
	}

	public String merger(Long codregra, Long codtribu, String tipo, String uf, String cfop, String cst_csosn,
			String cstpis, String cstcofins, String pis, String cofins, String aliq_ipi, String aliq_icms, String cst_ipi ) {
		LocalDate dataAtual = LocalDate.now();

		if (codregra == 0) {
			try {
				regras.cadastrar(codtribu, tipo, uf, cfop, cst_csosn, cstpis, cstcofins, pis, cofins, aliq_ipi, aliq_icms, cst_ipi,
						Date.valueOf(dataAtual));
			} catch (Exception e) {
				System.out.println(e.getStackTrace());
				return "Erro ao tentar adicionar regra, chame o suporte";
			}
		} else {
			try {
				regras.update(codregra, codtribu, tipo, uf, cfop, cst_csosn, cstpis, cstcofins, pis, cofins, aliq_ipi, aliq_icms, cst_ipi);
			} catch (Exception e) {
				System.out.println(e.getStackTrace());
				return "Erro ao tentar alterar regra, chame o suporte";
			}
			
			return "Regra alterada com sucesso";
		}

		return "Regra adicionada com sucesso";
	}

	public String remover(Long codigo) {
		try {
			regras.deleteById(codigo);
		} catch (Exception e) {
			System.out.println(e.getStackTrace());
			throw new RuntimeException("Erro ao tentar remover a regra, chame o suporte");
		}

		return "Regra removida com sucesso";
	}

	public TributacaoRegra busca(Long codigo) {
		return regras.findById(codigo).get();
	}

}
