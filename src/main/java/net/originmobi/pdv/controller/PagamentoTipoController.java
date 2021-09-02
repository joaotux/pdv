package net.originmobi.pdv.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import net.originmobi.pdv.model.PagamentoTipo;
import net.originmobi.pdv.service.PagamentoTipoService;

@Controller
@RequestMapping("/pagamentotipo")
public class PagamentoTipoController {

	private static final String PAGAMENTOTIPO_FORM = "pagamentotipo/form";
	private static final String PAGAMENTOTIPO_LIST = "pagamentotipo/list";

	@Autowired
	private PagamentoTipoService pagamentotipo;

	@GetMapping("/form")
	public ModelAndView form() {
		ModelAndView mv = new ModelAndView(PAGAMENTOTIPO_FORM);
		mv.addObject("tipopagamento", new PagamentoTipo());
		return mv;
	}

	@PostMapping
	public String cadastrar(@Validated PagamentoTipo pagamentoTipo, Errors errors, RedirectAttributes attributes) {
		if (errors.hasErrors())
			return PAGAMENTOTIPO_FORM;

		try {
			pagamentotipo.cadastrar(pagamentoTipo);
			attributes.addFlashAttribute("mensagem", "Forma de Pagamento salva com sucesso");
		} catch (Exception e) {
			e.getStackTrace();
		}

		return "redirect:/pagamentotipo/form";
	}
	
	@GetMapping("/qtdparcelas/{codigo}")
	public @ResponseBody String qtdParcelas(@PathVariable("codigo") Long codigo) {
		return pagamentotipo.qtdParcelas(codigo);
	}

	@GetMapping
	public ModelAndView lista() {
		ModelAndView mv = new ModelAndView(PAGAMENTOTIPO_LIST);
		mv.addObject("formaspagamento", pagamentotipo.listar());
		return mv;
	}

	@GetMapping("{codigo}")
	public ModelAndView editar(@PathVariable("codigo") PagamentoTipo pagamentoTipo) {
		ModelAndView mv = new ModelAndView(PAGAMENTOTIPO_FORM);
		mv.addObject("tipopagamento", pagamentoTipo);
		return mv;
	}

}
