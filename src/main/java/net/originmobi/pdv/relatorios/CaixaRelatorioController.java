package net.originmobi.pdv.relatorios;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/caixa/relatorio")
public class CaixaRelatorioController {
	
	private GerarRelatorio relatorio;
	
	@GetMapping("/caixa/{codigo}")
	public @ResponseBody String caixa(@PathVariable("codigo") Integer codigo, HttpServletResponse response) {
		Map<String, Object> parametros = new HashMap<>();
		parametros.put("codcaixa", codigo);

		relatorio = new GerarRelatorio();
		relatorio.gerar("caixa.jrxml", response, parametros);
		
		return "ok";
	}
}
