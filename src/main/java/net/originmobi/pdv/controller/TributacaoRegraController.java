package net.originmobi.pdv.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import net.originmobi.pdv.model.TributacaoRegra;
import net.originmobi.pdv.service.TributacaoRegraService;

@Controller
@RequestMapping("/regras")
public class TributacaoRegraController {

	@Autowired
	private TributacaoRegraService regras;

	@RequestMapping(method = RequestMethod.POST)
	public @ResponseBody String salvar(@RequestParam Map<String, String> request) {
		Long codtribu = Long.decode(request.get("codigo"));
		String tipo = request.get("tipo");
		String uf = request.get("uf");
		String cfop = request.get("cfop");
		String cst_csosn = request.get("cst_csosn");
		String cstpis = request.get("cstpis");
		String cstcofins = request.get("cstcofins");
		String pis = request.get("pis").replace(",", ".");
		String cofins = request.get("cofins").replace(",", ".");
		String aliq_icms = request.get("aliq_icms").isEmpty() ? "0" : request.get("aliq_icms").replace(",", ".");
		String cst_ipi = request.get("cstipi").isEmpty() ? "0" : request.get("cstipi");
		String aliq_ipi = request.get("aliq_ipi").isEmpty() ? "0" : request.get("aliq_ipi").replace(",", ".");

		Long codRegra = request.get("codigo_regra").isEmpty() ? 0L : Long.decode(request.get("codigo_regra"));

		return regras.merger(codRegra, codtribu, tipo, uf, cfop, cst_csosn, cstpis, cstcofins, pis, cofins, aliq_ipi,
				aliq_icms, cst_ipi);
	}

	@RequestMapping(value = "/{codigo}", method = RequestMethod.DELETE)
	public @ResponseBody String excluir(@PathVariable("codigo") Long codigo) {
		return regras.remover(codigo);
	}

	@RequestMapping(value = "/{codigo}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody TributacaoRegra editar(@PathVariable("codigo") Long codigo) {
		TributacaoRegra regra = regras.busca(codigo);
		return regra;
	}

}
