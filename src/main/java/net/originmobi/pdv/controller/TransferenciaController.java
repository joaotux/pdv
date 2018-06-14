package net.originmobi.pdv.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import net.originmobi.pdv.service.TransferenciaService;

@Controller
@RequestMapping("/transferencia")
public class TransferenciaController {

	@Autowired
	private TransferenciaService transferencias;
	
	@RequestMapping(method = RequestMethod.POST)
	public @ResponseBody String transferencia(@RequestParam Map<String, String> request) {
		Double valor = Double.valueOf(request.get("valor").replace(",", "."));
		Long origem = Long.decode(request.get("origem"));
		Long destino = Long.decode(request.get("destino"));
		String obs = request.get("obs");
		
		return transferencias.cadastrar(valor, origem, destino, obs);
		
	}
	
}
