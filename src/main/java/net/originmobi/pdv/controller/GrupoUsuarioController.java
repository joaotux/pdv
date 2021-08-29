package net.originmobi.pdv.controller;

import net.originmobi.pdv.model.GrupoUsuario;
import net.originmobi.pdv.model.Permissoes;
import net.originmobi.pdv.service.GrupoUsuarioService;
import net.originmobi.pdv.service.PermissoesService;
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
@RequestMapping("/grupousuario")
public class GrupoUsuarioController {

	private static final String GRUPOUSUARIO_FORM = "grupousuario/form";

	private static final String GRUPOUSUARIO_LIST = "grupousuario/list";

	@Autowired
	private GrupoUsuarioService gruposUsuarios;

	@Autowired
	private PermissoesService permissoes;

	@GetMapping
	public ModelAndView lista() {
		ModelAndView mv = new ModelAndView(GRUPOUSUARIO_LIST);
		mv.addObject("grupos", gruposUsuarios.lista());
		return mv;
	}

	@GetMapping("/form")
	public ModelAndView form() {
		ModelAndView mv = new ModelAndView(GRUPOUSUARIO_FORM);
		mv.addObject("grupousu", new GrupoUsuario());
		return mv;
	}

	@PostMapping
	public String cadastro(@Validated GrupoUsuario grupoUsuario, Errors errors, RedirectAttributes attributes) {
		if (errors.hasErrors())
			return GRUPOUSUARIO_FORM;

		gruposUsuarios.merge(grupoUsuario, attributes);

		return "redirect:/grupousuario";
	}

	@GetMapping("{codigo}")
	public ModelAndView editar(@PathVariable("codigo") GrupoUsuario grupoUsuario) {
		ModelAndView mv = new ModelAndView(GRUPOUSUARIO_FORM);
		mv.addObject("grupousu", grupoUsuario);
		mv.addObject("permissoesUsu", permissoes.lista(grupoUsuario.getCodigo()));
		return mv;
	}

	@GetMapping("/remove/{codigo}")
	public String remover(@PathVariable("codigo") Long codigo, RedirectAttributes attributes) {

		return gruposUsuarios.remove(codigo, attributes);
	}
	
	@PostMapping("/addpermissao")
	public @ResponseBody String addPermissao(@RequestParam Map<String, String> request) {
		Long codgrupo = Long.decode(request.get("grupo"));
		Long codpermissao = Long.decode(request.get("permissao"));
		
		return gruposUsuarios.addPermissao(codgrupo, codpermissao);
	}
	
	@DeleteMapping("/remove/")
	public @ResponseBody String removePermissao(@RequestParam Map<String, String> request) {
		Long codigo = Long.decode(request.get("codigo"));
		Long codgrupo = Long.decode(request.get("codgrupo"));
		
		return gruposUsuarios.removePermissao(codigo, codgrupo);
	}

	@ModelAttribute("permissoes")
	public List<Permissoes> permissoes() {
		return permissoes.lista();
	}

}
