package net.originmobi.pdv.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import net.originmobi.pdv.enumerado.notafiscal.NotaFiscalTipo;
import net.originmobi.pdv.model.FreteTipo;
import net.originmobi.pdv.model.NotaFiscal;
import net.originmobi.pdv.model.Pessoa;
import net.originmobi.pdv.model.Produto;
import net.originmobi.pdv.service.PessoaService;
import net.originmobi.pdv.service.ProdutoService;
import net.originmobi.pdv.service.notafiscal.FreteTipoService;
import net.originmobi.pdv.service.notafiscal.NotaFiscalItemService;
import net.originmobi.pdv.service.notafiscal.NotaFiscalService;

@Controller
@RequestMapping("/notafiscal")
public class NotaFiscalController {

	private static final String NOTAFISCAL_LIST = "notafiscal/list";

	private static final String NOTAFISCAL_FORM = "notafiscal/form";

	@Autowired
	private NotaFiscalService notasFiscais;

	@Autowired
	private PessoaService pessoas;
	
	@Autowired
	private ProdutoService produtos;
	
	@Autowired
	private NotaFiscalItemService itens;
	
	@Autowired
	private FreteTipoService fretes;

	@GetMapping("/form")
	public ModelAndView form() {
		ModelAndView mv = new ModelAndView(NOTAFISCAL_FORM);
		mv.addObject("nota", new NotaFiscal());
		return mv;
	}

	@RequestMapping(method = RequestMethod.POST)
	public @ResponseBody String criaNota(@RequestParam Map<String, String> request, UriComponentsBuilder b) {
		UriComponents uri = b.path("/notafiscal/").build();
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(uri.toUri());
		
		
		if(request.get("natureza_operacao").isEmpty())
			throw new RuntimeException("Favor, informe a natureza da operação");
		
		if(request.get("destinatario").isEmpty())
			throw new RuntimeException("Favor, informe o destinatário");
		
		
		Long coddesti = Long.decode(request.get("destinatario"));
		String natureza = request.get("natureza_operacao");
		String tipo = request.get("tipo");
		
		NotaFiscalTipo notaTipo = tipo.equals(NotaFiscalTipo.ENTRADA) ? NotaFiscalTipo.ENTRADA : NotaFiscalTipo.SAIDA;

		String codigo = null;
		codigo = notasFiscais.cadastrar(coddesti, natureza, notaTipo);

		return headers.toString() + codigo;
	}

	@GetMapping("{codigo}")
	public ModelAndView busca(@PathVariable("codigo") NotaFiscal notaFiscal) {
		ModelAndView mv = new ModelAndView(NOTAFISCAL_FORM);
		mv.addObject("nota", notaFiscal);
		mv.addObject("itens", itens.buscaItensNota(notaFiscal.getCodigo()));
		return mv;
	}
	
	@RequestMapping(value = "{codigo}", method = RequestMethod.POST)
	public @ResponseBody String emitir(@PathVariable("codigo") NotaFiscal notaFiscal) {
		
		notasFiscais.emitir(notaFiscal);
		
		return "ok";
	}
	
	@GetMapping
	public ModelAndView lista() {
		System.out.println("veio aqui");
		ModelAndView mv = new ModelAndView(NOTAFISCAL_LIST);
		mv.addObject("notas", notasFiscais.lista());
		return mv;
	}

	@ModelAttribute("destinatarios")
	public List<Pessoa> pessoas() {
		return pessoas.lista();
	}

	@ModelAttribute("tipos")
	public List<NotaFiscalTipo> tipos() {
		return Arrays.asList(NotaFiscalTipo.values());
	}
	
	@ModelAttribute("produtos")
	public List<Produto> produtos() {
		return produtos.listar();
	}
	
	@ModelAttribute("fretes")
	public List<FreteTipo> frete() {
		return fretes.lista();
	}
}
