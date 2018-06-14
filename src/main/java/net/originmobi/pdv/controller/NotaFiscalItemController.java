package net.originmobi.pdv.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import net.originmobi.pdv.enumerado.notafiscal.NotaFiscalTipo;
import net.originmobi.pdv.service.notafiscal.NotaFiscalItemService;

@Controller
@RequestMapping("/notafiscalitem")
public class NotaFiscalItemController {
	
	@Autowired
	private NotaFiscalItemService itens;
	
	@RequestMapping(method = RequestMethod.POST)
	public @ResponseBody String insereItemNota(@RequestParam Map<String, String> request) {
		Long prod = Long.decode(request.get("codprod"));
		Long codnota = Long.decode(request.get("nota"));
		int qtd = Integer.parseInt(request.get("qtd"));
		String tipo = request.get("tipo");
		
		NotaFiscalTipo tipoNota = tipo.equals("ENTRADA") ? NotaFiscalTipo.ENTRADA : NotaFiscalTipo.SAIDA; 
		
		itens.insere(prod, codnota, qtd, tipoNota);
		
		return "ok";
	}
	
	@RequestMapping(method = RequestMethod.DELETE)
	public @ResponseBody String remove(@RequestParam Map<String, String> request) {
		Long notaitem = Long.decode(request.get("notaitem"));
		Long nota = Long.decode(request.get("nota"));
		
		itens.remove(notaitem, nota);
		
		return "ok";
	}

}
