package net.originmobi.pdv.service.notafiscal;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.originmobi.pdv.model.NotaFiscalTotais;
import net.originmobi.pdv.repository.notafiscal.NotaFiscalTotaisRepository;

@Service
public class NotaFiscalTotaisServer {

	@Autowired
	private NotaFiscalTotaisRepository totais;

	public List<NotaFiscalTotais> lista() {
		return totais.findAll();
	}

	public NotaFiscalTotais cadastro(NotaFiscalTotais total) {
		return totais.save(total);
	}

	public void atualiza(Long codNota, NotaFiscalTotais total) {
		System.out.println("codnota " + codNota);
		System.out.println("totais " + total.getCodigo());
		String[] array = totais.calcula(codNota).split(",");
		
		Double totalProdutos = Double.valueOf(array[0]);
		Double totalIcms = Double.valueOf(array[1]);
		Double totalPis = Double.valueOf(array[2]);
		Double totalCofins = Double.valueOf(array[3]);
		Double totalIpi = Double.valueOf(array[4]);
		Double totalNota = totalProdutos + totalIpi;

		total.setVBc(totalProdutos);
		total.setVProd(totalProdutos);
		total.setVIcms(totalIcms);
		total.setVPis(totalPis);
		total.setVCofins(totalCofins);
		total.setVIpi(totalIpi);
		total.setVNf(totalNota);

		try {
			totais.save(total);
		} catch (Exception e) {
			System.out.println(e);
			throw new RuntimeException("Erro ao salvar item na nota, chame o suporte");
		}
	}

}
