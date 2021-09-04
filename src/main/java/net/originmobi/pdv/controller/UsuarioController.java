package net.originmobi.pdv.controller;

import net.originmobi.pdv.model.GrupoUsuario;
import net.originmobi.pdv.model.Pessoa;
import net.originmobi.pdv.model.Usuario;
import net.originmobi.pdv.service.GrupoUsuarioService;
import net.originmobi.pdv.service.PessoaService;
import net.originmobi.pdv.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/usuario")
public class UsuarioController {

	private static final String USUARIO_LIST = "usuario/list";

	private static final String USUARIO_FORM = "usuario/form";
	public static final String CODIGO_GRU = "codigoGru";
	public static final String CODIGO_USU = "codigoUsu";

	@Autowired
	private UsuarioService usuarios;

	@Autowired
	private PessoaService pessoas;

	@Autowired
	private GrupoUsuarioService gruposUsuario;

	@GetMapping("/form")
	public ModelAndView form() {
		ModelAndView mv = new ModelAndView(USUARIO_FORM);
		mv.addObject("usuario", new Usuario());
		return mv;
	}

	@GetMapping
	public ModelAndView lista() {
		ModelAndView mv = new ModelAndView(USUARIO_LIST);
		mv.addObject("usuarios", usuarios.lista());
		return mv;
	}

	@PostMapping
	public String cadastrar(@Validated Usuario usuario, Errors errors, RedirectAttributes attributes) {
		if (errors.hasErrors())
			return USUARIO_FORM;

		String mensagem = "";

		try {
			mensagem = usuarios.cadastrar(usuario);
			attributes.addFlashAttribute("mensagem", mensagem);
		} catch (Exception e) {
			e.getStackTrace();
		}

		return "redirect:/usuario/form";
	}

	@GetMapping("{codigo}")
	public ModelAndView editar(@PathVariable("codigo") Usuario usuario) {
		ModelAndView mv = new ModelAndView(USUARIO_FORM);
		mv.addObject(usuario);
		mv.addObject("grupos", gruposUsuario.buscaGrupos(usuario));
		return mv;
	}

	@PostMapping("/addgrupo")
	public @ResponseBody String addGrupo(@RequestParam Map<String, String> request) {

		if (request.get(CODIGO_GRU).length() == 0)
			return "grupo vazio";

		Long codUsu = Long.decode(request.get(CODIGO_USU));
		Long codGru = Long.decode(request.get(CODIGO_GRU));

		return usuarios.addGrupo(codUsu, codGru);
	}

	@PutMapping("/removegrupo")
	public @ResponseBody String removeGrupo(@RequestParam Map<String, String> request) {
		Long codUsu = Long.decode(request.get(CODIGO_USU));
		Long codGru = Long.decode(request.get(CODIGO_GRU));

		return usuarios.removeGrupo(codUsu, codGru);
	}

	@GetMapping("/teste")
	public @ResponseBody String teste() {
		return "tudo ok";
	}

	@ModelAttribute("pessoas")
	public List<Pessoa> pessoas() {
		return pessoas.lista();
	}

	@ModelAttribute("todosGrupos")
	public List<GrupoUsuario> todosGrupos() {
		return gruposUsuario.lista();
	}

}
