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
@RequestMapping("/venda/relatorio")
public class VendaRelatorioController {
	
	GerarRelatorio relatorio;

	@GetMapping("/controle/{codigo}")
	public void controle(@PathVariable("codigo") Integer codigo, HttpServletResponse response) {
		Map<String, Object> parametros = new HashMap<>();
		parametros.put("codvenda", codigo);

		relatorio = new GerarRelatorio();
		relatorio.gerar("controle.jrxml", response, parametros);
	}
	
	@GetMapping("/comanda/{codigo}")
	public @ResponseBody String comanda(@PathVariable("codigo") Integer codigo, HttpServletResponse response) {
		Map<String, Object> parametros = new HashMap<>();
		parametros.put("codvenda", codigo);
		
		relatorio = new GerarRelatorio();
		relatorio.gerar("comanda.jrxml", response, parametros);
		
		return "ok";
	} 
}
