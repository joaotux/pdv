package net.originmobi.pdv.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import net.originmobi.pdv.model.Grupo;
import net.originmobi.pdv.service.GrupoService;

@Controller
@RequestMapping("/grupo")
public class GrupoController {

	private static final String GRUPO_LIST = "grupo/list";
	private static final String GRUPO_FORM = "grupo/form";
	@Autowired
	private GrupoService grupos;

	@GetMapping("/form")
	public ModelAndView form() {
		ModelAndView mv = new ModelAndView(GRUPO_FORM);
		mv.addObject("grupo", new Grupo());
		return mv;
	}

	@GetMapping
	public ModelAndView lista() {
		ModelAndView mv = new ModelAndView(GRUPO_LIST);
		mv.addObject("grupos", grupos.lista());
		return mv;
	}

	@GetMapping("{codigo}")
	public ModelAndView editar(@PathVariable("codigo") Grupo grupo) {
		ModelAndView mv = new ModelAndView(GRUPO_FORM);
		mv.addObject("grupo", grupo);
		return mv;
	}

	@PostMapping
	public String salvar(@Validated Grupo grupo, Errors errors, RedirectAttributes attributes) {
		if (errors.hasErrors())
			return GRUPO_FORM;
		
		try {
			grupos.cadastrar(grupo);
			attributes.addFlashAttribute("mensagem", "Grupo salvo com sucesso");
		} catch (Exception e) {
			System.out.println("Erro ao cadastrar Grupo " + e);
		}

		return "redirect:/grupo/form";
	}

}
