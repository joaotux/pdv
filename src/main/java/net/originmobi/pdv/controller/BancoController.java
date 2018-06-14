package net.originmobi.pdv.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import net.originmobi.pdv.enumerado.caixa.CaixaTipo;
import net.originmobi.pdv.filter.BancoFilter;
import net.originmobi.pdv.service.CaixaService;

@Controller
@RequestMapping("/banco")
public class BancoController {
	
	private static final String BANCO_LIST = "banco/list";
	
	@Autowired
	private CaixaService caixas;
	
	@GetMapping
	public ModelAndView listar(@ModelAttribute("filterBanco") BancoFilter filter) {
		ModelAndView mv = new ModelAndView(BANCO_LIST);
		mv.addObject("bancos", caixas.listaBancosAbertosTipoFilterBanco(CaixaTipo.BANCO, filter));
		return mv;
	}

}
