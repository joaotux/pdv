package net.originmobi.pdv.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import net.originmobi.pdv.enumerado.EntradaSaida;
import net.originmobi.pdv.model.CFOP;
import net.originmobi.pdv.model.Cst;
import net.originmobi.pdv.model.CstCsosn;
import net.originmobi.pdv.model.CstIPI;
import net.originmobi.pdv.model.Estado;
import net.originmobi.pdv.model.Tributacao;
import net.originmobi.pdv.service.CfopService;
import net.originmobi.pdv.service.CstCsosnService;
import net.originmobi.pdv.service.CstService;
import net.originmobi.pdv.service.EstadoService;
import net.originmobi.pdv.service.TributacaoRegraService;
import net.originmobi.pdv.service.TributacaoService;
import net.originmobi.pdv.service.notafiscal.CstIpiService;

@Controller
@RequestMapping("/tributacao")
public class TributacaoController {

	private static final String TRIBUTACAO_FORM = "tributacao/form";

	@Autowired
	private TributacaoService tributacoes;

	@Autowired
	private TributacaoRegraService regras;

	@Autowired
	private CstService csts;

	@Autowired
	private CfopService cfops;

	@Autowired
	private EstadoService ufs;

	@Autowired
	private CstCsosnService cstCsosn;
	
	@Autowired
	private CstIpiService cstipiServer;
	
	@GetMapping("/form")
	public ModelAndView form() {
		ModelAndView mv = new ModelAndView(TRIBUTACAO_FORM);
		mv.addObject("tributacao", new Tributacao());
		return mv;
	}

	@GetMapping
	public ModelAndView lista() {
		ModelAndView mv = new ModelAndView("tributacao/list");
		mv.addObject("tributacoes", tributacoes.lista());
		return mv;
	}

	@GetMapping("{codigo}")
	public ModelAndView busca(@PathVariable("codigo") Tributacao tributacao) {
		ModelAndView mv = new ModelAndView(TRIBUTACAO_FORM);
		mv.addObject("tributacao", tributacao);
		mv.addObject("regras_saida", regras.lista(tributacao.getCodigo(), EntradaSaida.SAIDA));
		mv.addObject("regras_entrada", regras.lista(tributacao.getCodigo(), EntradaSaida.ENTRADA));
		return mv;
	}

	@PostMapping
	public String cadastro(@Validated Tributacao tributacao, Errors errors, RedirectAttributes attributes) {
		if (errors.hasErrors())
			return TRIBUTACAO_FORM;

		String codTributacao = null;
		
		try {
			codTributacao = tributacoes.cadastro(tributacao);
			attributes.addFlashAttribute("mensagem", "Tributação cadastrada com sucesso");
		} catch (Exception e) {
			attributes.addFlashAttribute("mensagem", "Erro ao tentar cadastrada tributação, chame o suporte");
			System.out.println(e.getStackTrace());
		}

		if (codTributacao.equals("sem empresa")) {
			attributes.addFlashAttribute("mensagemErro", "Nenhuma empresa cadastrada, verifique");
			attributes.addFlashAttribute("mensagem", "");
			return "redirect:/tributacao/form";
		}

		return "redirect:/tributacao/" + codTributacao;
	}

	@ModelAttribute("tipos")
	public List<EntradaSaida> entradaSaida() {
		return Arrays.asList(EntradaSaida.values());
	}

	@ModelAttribute("csts")
	public List<Cst> cests() {
		return csts.lista();
	}

	@ModelAttribute("cfops")
	public List<CFOP> cfops() {
		return cfops.lista();
	}

	@ModelAttribute("ufs")
	public List<Estado> estados() {
		return ufs.lista();
	}

	@ModelAttribute("cstcsosn")
	public List<CstCsosn> cstcsosn() {
		return cstCsosn.lista();
	}
	
	@ModelAttribute("cstipi")
	public List<CstIPI> cstIpi() {
		return cstipiServer.lista();
	}
}
