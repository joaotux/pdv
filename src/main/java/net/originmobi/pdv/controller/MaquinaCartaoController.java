package net.originmobi.pdv.controller;

import net.originmobi.pdv.enumerado.caixa.CaixaTipo;
import net.originmobi.pdv.model.Caixa;
import net.originmobi.pdv.model.cartao.MaquinaCartao;
import net.originmobi.pdv.service.CaixaService;
import net.originmobi.pdv.service.cartao.MaquinaCartaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/maquinacartao")
public class MaquinaCartaoController {

	private static final String MAQUINACARTAO_LIST = "maquinacartao/list";

	private static final String MAQUINA_FORM = "maquinacartao/form";

	@Autowired
	private MaquinaCartaoService maquinas;

	@Autowired
	private CaixaService caixas;

	@GetMapping("/form")
	public ModelAndView form() {
		ModelAndView mv = new ModelAndView(MAQUINA_FORM);
		mv.addObject("maquinacart", new MaquinaCartao());
		return mv;
	}

	@GetMapping
	public ModelAndView lista() {
		ModelAndView mv = new ModelAndView(MAQUINACARTAO_LIST);
		mv.addObject("maquinas", maquinas.lista());
		return mv;
	}

	@PostMapping
	public String cadastro(@Validated MaquinaCartao maquina, Errors errors, RedirectAttributes attributes) {
		if (errors.hasErrors())
			return MAQUINA_FORM;

		maquinas.cadastrar(maquina);
		attributes.addFlashAttribute("mensagem", "Máquina salva com sucesso");

		return "redirect:/maquinacartao/form";
	}

	@GetMapping("{codigo}")
	public ModelAndView editar(@PathVariable("codigo") MaquinaCartao maquinaCartao) {
		ModelAndView mv = new ModelAndView(MAQUINA_FORM);
		mv.addObject("maquinacart", maquinaCartao);
		return mv;
	}

	@DeleteMapping("{codigo}")
	public @ResponseBody String editar(@PathVariable("codigo") Long codigo) {
		maquinas.remove(codigo);

		return "Máquina removida com sucesso";
	}
	
	@GetMapping(value = "listaJson", produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<MaquinaCartao> listaMaquinasJson() {
		return maquinas.lista();
	}

	@ModelAttribute("bancos")
	public List<Caixa> listaBancos() {
		return caixas.listaCaixasAbertosTipo(CaixaTipo.BANCO);
	}
}
