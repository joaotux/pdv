package net.originmobi.pdv.service.notafiscal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.originmobi.pdv.model.Cst;
import net.originmobi.pdv.model.NotaFiscalItemImposto;
import net.originmobi.pdv.model.TributacaoRegra;
import net.originmobi.pdv.repository.notafiscal.NotaFiscalItemImpostoRepository;

@Service
public class NotaFiscalItemImpostoService {

	@Autowired
	private NotaFiscalItemImpostoRepository impostos;

	public void cadastro(NotaFiscalItemImposto imposto) {
		impostos.save(imposto);
	}

	public NotaFiscalItemImposto merger(Long codimposto, char origin, String cst_cofins, int modBcIcms, Double bc_icms,
			Double aliq_icms, Double vlIcms, String cst_pis, Double bc_pis, Double pis, Double vlPis, Double bc_cofins,
			Double aliqCofins, Double vlCofins, int cst, int cst_ipi, Double vbc_ipi, Double p_ipi, Double v_ipi) {

		String x = Character.toString(origin);
		Integer orig = Integer.parseInt(x);

		int vlCst_cofins = Integer.parseInt(cst_cofins);
		int vlCst_pis = Integer.parseInt(cst_pis);

		NotaFiscalItemImposto imposto = new NotaFiscalItemImposto(orig, vlCst_cofins, modBcIcms, bc_icms, aliq_icms,
				vlIcms, vlCst_pis, bc_pis, pis, vlPis, bc_cofins, aliqCofins, vlCofins, cst, cst_ipi, vbc_ipi, p_ipi,
				v_ipi);

		// se o código for diferente de null, se trata de uma atualização
		if (codimposto != null)
			imposto.setCodigo(codimposto);

		try {
			impostos.save(imposto);
		} catch (Exception e) {
			System.out.println(e);
			throw new RuntimeException("Erro ao lançar impostos na nota, chame o suporte");
		}

		return imposto;
	}

	public NotaFiscalItemImposto calcula(Long codimposto, Double vlTotal, TributacaoRegra regra, char origin,
			int modBcIcms) {
		int cst_csosn = Integer.parseInt(regra.getCst_csosn().getCst_csosn());
		Cst cst_cofins = regra.getCst_cofins();
		String cst_pis = regra.getCst_pis().getCst();
		
		Double bc_icms = vlTotal;
		Double aliq_icms = regra.getAliq_icms();
		Double vlIcms = (vlTotal * aliq_icms) / 100;

		Double bc_pis = vlTotal;
		Double pis = regra.getPis();
		Double vlPis = (bc_pis * pis) / 100;

		Double bc_cofins = vlTotal;
		Double aliqCofins = regra.getCofins();
		Double vlCofins = (bc_cofins * aliqCofins) / 100;

		int cst_ipi = Integer.parseInt(regra.getCst_ipi().getCst());
		Double bcIPI = vlTotal;
		Double aliqIPI = regra.getAliq_ipi();
		Double vlIPI = (bcIPI * aliqIPI) / 100;

		// cadastra os impostos do produto
		NotaFiscalItemImposto imposto = null;
		try {
			imposto = merger(codimposto, origin, cst_cofins.getCst(), modBcIcms, bc_icms, aliq_icms, vlIcms, cst_pis,
					bc_pis, pis, vlPis, bc_cofins, aliqCofins, vlCofins, cst_csosn, cst_ipi, bcIPI, aliqIPI, vlIPI);
		} catch (Exception e) {
			System.out.println(e);
			throw new RuntimeException("erro ao calcular os impostos da nota");
		}

		return imposto;
	}

}
