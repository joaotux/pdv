package net.originmobi.pdv.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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

import net.originmobi.pdv.filter.AjusteFilter;
import net.originmobi.pdv.model.Ajuste;
import net.originmobi.pdv.model.Produto;
import net.originmobi.pdv.service.AjusteProdutoService;
import net.originmobi.pdv.service.AjusteService;
import net.originmobi.pdv.service.ProdutoService;

@Controller
@RequestMapping("/ajustes")
public class AjusteController {

	private static final String AJUSTE_FORM = "ajuste/form";
	private static final String AJUSTE_LIST = "ajuste/list";

	@Autowired
	private AjusteService ajustes;

	@Autowired
	private ProdutoService produtos;

	@Autowired
	private AjusteProdutoService ajusteProdutos;

	@GetMapping
	public ModelAndView lista(@ModelAttribute("filterAjuste") AjusteFilter filter, Pageable pageable, Model model) {
		ModelAndView mv = new ModelAndView(AJUSTE_LIST);
		Page<Ajuste> lista = ajustes.lista(pageable, filter);
		mv.addObject("ajustes", lista);
		
		model.addAttribute("qtdpaginas", lista.getTotalPages());
		model.addAttribute("pagAtual", lista.getPageable().getPageNumber());
		model.addAttribute("proxPagina", lista.getPageable().next().getPageNumber());
		model.addAttribute("pagAnterior", lista.getPageable().previousOrFirst().getPageNumber());
		model.addAttribute("hasNext", lista.hasNext());
		model.addAttribute("hasPrevious", lista.hasPrevious());
		
		return mv;
	}

	@RequestMapping(method = RequestMethod.POST)
	public @ResponseBody String cadastra(UriComponentsBuilder builder) {
		UriComponents uri = builder.path("/ajustes/").build();

		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(uri.toUri());

		Long codigo = ajustes.novo();

		return headers + codigo.toString();
	}

	@GetMapping("{codigo}")
	public ModelAndView form(@PathVariable("codigo") Ajuste ajuste) {
		ModelAndView mv = new ModelAndView(AJUSTE_FORM);
		mv.addObject("ajuste", ajuste);
		mv.addObject("produtosAjuste", ajusteProdutos.listaProdutosAjuste(ajuste.getCodigo()));
		return mv;
	}
	
	@RequestMapping(value = "/addproduto", method = RequestMethod.POST)
	public @ResponseBody String addProduto(@RequestParam Map<String, String> request) {
		Long codajuste = Long.decode(request.get("codajuste"));
		Long codprod = Long.decode(request.get("codprod"));
		int qtd_alterar = Integer.parseInt(request.get("qtd_alterar"));
		
		return ajusteProdutos.addProduto(codajuste, codprod, qtd_alterar);
	}
	
	@RequestMapping(value = "/processar", method = RequestMethod.POST)
	public @ResponseBody String processar(@RequestParam Map<String, String> request) {
		Long codajuste = Long.decode(request.get("codajuste"));
		String obs = request.get("obs");
		
		return ajustes.processar(codajuste, obs);
	}
	
	@RequestMapping(value = "/cancelar/{codigo}", method = RequestMethod.DELETE)
	public @ResponseBody String remover(@PathVariable("codigo") Ajuste ajuste, UriComponentsBuilder builder) {
		UriComponents component = builder.path("/ajustes").build();
		
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(component.toUri());
		
		ajustes.remover(ajuste);
		
		return component.toString();
	}
	
	@RequestMapping(value = "/remove/item", method = RequestMethod.DELETE)
	public @ResponseBody String removeItem(@RequestParam Map<String, String> request) {
		Long codajuste = Long.decode(request.get("codajuste"));
		Long coditem = Long.decode(request.get("coditem"));
		
		return ajusteProdutos.removeProduto(codajuste, coditem);
	}

	@ModelAttribute("produtos")
	public List<Produto> produtos() {
		return produtos.listar();
	}
}
