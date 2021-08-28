package net.originmobi.pdv.service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import net.originmobi.pdv.enumerado.EntradaSaida;
import net.originmobi.pdv.enumerado.ajuste.AjusteStatus;
import net.originmobi.pdv.filter.AjusteFilter;
import net.originmobi.pdv.model.Ajuste;
import net.originmobi.pdv.repository.AjusteRepository;
import net.originmobi.pdv.singleton.Aplicacao;

@Service
public class AjusteService {

	@Autowired
	private AjusteRepository ajustes;

	@Autowired
	private ProdutoService produtos;

	LocalDate dataAtual;

	public Page<Ajuste> lista(Pageable pageable, AjusteFilter filter) {
		if(filter.getCodigo() != null)
			return ajustes.lista(filter.getCodigo(), pageable);
		
		return ajustes.lista(pageable);
	}

	public Optional<Ajuste> busca(Long codigo) {
		return ajustes.findById(codigo);
	}

	public Long novo() {
		dataAtual = LocalDate.now();
		Aplicacao aplicacao = Aplicacao.getInstancia();

		Ajuste ajuste = new Ajuste(AjusteStatus.APROCESSAR, aplicacao.getUsuarioAtual(), Date.valueOf(dataAtual));
		return ajustes.save(ajuste).getCodigo();
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public String processar(Long codajuste, String obs) {
		dataAtual = LocalDate.now();
		Optional<Ajuste> ajuste = ajustes.findById(codajuste);

		if (ajuste.map(Ajuste::getStatus).get().equals(AjusteStatus.PROCESSADO))
			throw new RuntimeException("Ajuste já processado");

		for (int i = 0; i < ajuste.get().getProdutos().size(); i++) {
			Long codprod = ajuste.get().getProdutos().get(i).getProduto().getCodigo();
			int qtd_alteracao = ajuste.get().getProdutos().get(i).getQtd_alteracao();

			EntradaSaida tipo = qtd_alteracao > 0 ? EntradaSaida.ENTRADA : EntradaSaida.SAIDA;
			String origem_operacao = "Referente ao ajuste de estoque " + codajuste;

			try {
				produtos.ajusteEstoque(codprod, qtd_alteracao, tipo, origem_operacao, Date.valueOf(dataAtual));
			} catch (Exception e) {
				System.out.println(e);
				throw new RuntimeException("Erro ao tentar processar o ajuste, chame o suporte");
			}
		}

		ajuste.get().setStatus(AjusteStatus.PROCESSADO);
		ajuste.get().setObservacao(obs);
		ajuste.get().setDataProcessamento(Date.valueOf(dataAtual));
		try {
			ajustes.save(ajuste.get());
		} catch (Exception e) {
			throw new RuntimeException("Erro ao tentar processar o ajuste, chame o suporte");
		}

		return "Ajuste realizado com sucesso";
	}

	public void remover(Ajuste ajuste) {
		
		if(ajuste.getStatus().equals(AjusteStatus.PROCESSADO))
			throw new RuntimeException("O ajuste já esta processado");
		
		try {
			ajustes.deleteById(ajuste.getCodigo());
		} catch (Exception e) {
			throw new RuntimeException("Erro ao tentar cancelar o ajuste");
		}
	}

}
