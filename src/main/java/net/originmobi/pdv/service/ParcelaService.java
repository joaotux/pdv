package net.originmobi.pdv.service;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.originmobi.pdv.filter.ClienteFilter;
import net.originmobi.pdv.model.Parcela;
import net.originmobi.pdv.model.Receber;
import net.originmobi.pdv.repository.ParcelaRepository;
import net.originmobi.pdv.utilitarios.DataAtual;

@Service
public class ParcelaService {

	private final Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private ParcelaRepository parcelas;

	public void gerarParcela(Double total, Double desconto, Double acrescimo, Double recebido, Double restante,
			Receber receber, int quitado, int sequencia, Timestamp cadastro, Date vencimento) {
		parcelas.gerarparcela(total, desconto, acrescimo, recebido, restante, receber, quitado, sequencia, cadastro,
				vencimento);
	}

	public List<Parcela> lista() {
		return parcelas.findAll();
	}

	public List<Parcela> lista(ClienteFilter filter) {
			return parcelas.buscaReceberDaPessoaCodigo(filter.getCodigo(), filter.getPago());
	}
	
	public String totalReceberCliente(ClienteFilter filter) {
		return parcelas.somaTotaAReceberPessoaCodigo(filter.getCodigo(), filter.getPago());
	}

	public Parcela busca(Long codigo) {
		return parcelas.findByCodigoEquals(codigo);
	}

	public String receber(Long parcela, Double totalPago, Double acrescimo, Double desconto) {
		DataAtual dataAtual = new DataAtual();

		Optional<Parcela> parcelaAtualOptional = parcelas.findById(parcela);

		if (!parcelaAtualOptional.isPresent()) {
			throw new RuntimeException("Parcela não encontrada");
		}

		Parcela parcelaAtual = parcelaAtualOptional.get();

		if (parcelaAtual.getQuitado() == 1)
			return "Parcela " + parcela + " já esta quitada";

		DecimalFormat df = new DecimalFormat("#.00");

		Double vlRecebido = (totalPago + acrescimo) + parcelaAtual.getValorRecebido();
		double vlRestante = parcelaAtual.getValorRestante() - (totalPago + desconto);
		Double vlDesconto = parcelaAtual.getValorDesconto() + desconto;
		Double vlAcrescimo = parcelaAtual.getValorAcrescimo() + acrescimo;

		vlRestante = vlRestante < 0 ? 0.00 : vlRestante;
		int quitado = Double.parseDouble(df.format(vlRestante).replace(",", ".")) <= 0 ? 1 : 0;

		try {
			parcelas.receber(vlDesconto, vlAcrescimo, vlRecebido, vlRestante, quitado, dataAtual.dataAtualTimeStamp(),
					parcela);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new RuntimeException();
		}

		return "ok";
	}

}
