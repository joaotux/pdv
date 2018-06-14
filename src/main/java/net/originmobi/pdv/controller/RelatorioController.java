package net.originmobi.pdv.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import net.originmobi.pdv.relatorios.GerarRelatorio;

@Controller
@RequestMapping("/relatorio")
public class RelatorioController {
	
	@GetMapping
	public String relatorio(HttpServletResponse response) {
		GerarRelatorio relatorio = new GerarRelatorio();
		Map<String, Object> parametros = new HashMap<>();
		parametros.put("codvenda", 1);
		
		relatorio.gerar("comanda.jrxml", response, parametros);
		
		return "relatorio";
	}

}
