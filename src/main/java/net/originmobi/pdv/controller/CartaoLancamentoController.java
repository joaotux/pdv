package net.originmobi.pdv.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import net.originmobi.pdv.enumerado.cartao.CartaoSituacao;
import net.originmobi.pdv.enumerado.cartao.CartaoTipo;
import net.originmobi.pdv.filter.CartaoFilter;
import net.originmobi.pdv.model.cartao.CartaoLancamento;
import net.originmobi.pdv.service.cartao.CartaoLancamentoService;

@Controller
@RequestMapping("/cartaolancamentos")
public class CartaoLancamentoController {

	private static final String CARTAO_LIST = "cartao/list";

	@Autowired
	private CartaoLancamentoService cartaoLancamentos;

	@GetMapping
	public ModelAndView lista(@ModelAttribute("filterCartao") CartaoFilter filter) {
		ModelAndView mv = new ModelAndView(CARTAO_LIST);
		mv.addObject("cartoes", cartaoLancamentos.listar(filter));
		return mv;
	}

	@RequestMapping(value = "{codigo}", method = RequestMethod.PUT)
	public @ResponseBody String processar(@PathVariable("codigo") CartaoLancamento cartaoLancamento) {
		return cartaoLancamentos.processar(cartaoLancamento);
	}

	@RequestMapping(value = "/antecipar/{codigo}", method = RequestMethod.PUT)
	public @ResponseBody String antecipar(@PathVariable("codigo") CartaoLancamento cartaoLancamento) {
		return cartaoLancamentos.antecipar(cartaoLancamento);
	}

	@ModelAttribute("situacoes")
	public List<CartaoSituacao> situacoes() {
		return Arrays.asList(CartaoSituacao.values());
	}

	@ModelAttribute("tipos")
	public List<CartaoTipo> tipos() {
		return Arrays.asList(CartaoTipo.values());
	}
}
