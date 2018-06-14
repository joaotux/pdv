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

import net.originmobi.pdv.model.Categoria;
import net.originmobi.pdv.service.CategoriaService;

@Controller
@RequestMapping("/categoria")
public class CategoriaController {

	private static final String CATEGORIA_LIST = "categoria/list";

	private static final String CATEGORIA_FORM = "categoria/form";

	@Autowired
	private CategoriaService categorias;

	@GetMapping
	public ModelAndView lista() {
		ModelAndView mv = new ModelAndView(CATEGORIA_LIST);
		mv.addObject("categorias", categorias.lista());
		return mv;
	}

	@GetMapping("/form")
	public ModelAndView form() {
		ModelAndView mv = new ModelAndView(CATEGORIA_FORM);
		mv.addObject("categoria", new Categoria());
		return mv;
	}
	
	@GetMapping("{codigo}")
	public ModelAndView editar(@PathVariable("codigo") Categoria categoria){
		ModelAndView mv = new ModelAndView(CATEGORIA_FORM);
		mv.addObject(categoria);
		return mv;
	} 

	@PostMapping
	public String cadastrar(@Validated Categoria categoria, Errors errors, RedirectAttributes attributes) {
		if (errors.hasErrors()) 
			return CATEGORIA_FORM;

		try {
			categorias.cadastrar(categoria);
			attributes.addFlashAttribute("mensagem", "Categoria salva com sucesso");
		} catch (Exception e) {
			System.out.println("Erro ao cadastrar Categoria " + e);
		}
		return "redirect:/categoria/form";
	}

}
