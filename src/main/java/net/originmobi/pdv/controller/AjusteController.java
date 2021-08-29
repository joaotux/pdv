package net.originmobi.pdv.controller;

import net.originmobi.pdv.filter.AjusteFilter;
import net.originmobi.pdv.model.Ajuste;
import net.originmobi.pdv.model.Produto;
import net.originmobi.pdv.service.AjusteProdutoService;
import net.originmobi.pdv.service.AjusteService;
import net.originmobi.pdv.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/ajustes")
public class AjusteController {

	private static final String AJUSTE_FORM = "ajuste/form";
	private static final String AJUSTE_LIST = "ajuste/list";
	public static final String COD_AJUSTE = "codajuste";
	public static final String COD_PROD = "codprod";
	public static final String QTD_ALTERAR = "qtd_alterar";
	public static final String COD_ITEM = "coditem";

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

	@PostMapping
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

	@PostMapping("/addproduto")
	public @ResponseBody String addProduto(@RequestParam Map<String, String> request) {
		Long codajuste = Long.decode(request.get(COD_AJUSTE));
		Long codprod = Long.decode(request.get(COD_PROD));
		int qtdAlterar = Integer.parseInt(request.get(QTD_ALTERAR));
		
		return ajusteProdutos.addProduto(codajuste, codprod, qtdAlterar);
	}

	@PostMapping("/processar")
	public @ResponseBody String processar(@RequestParam Map<String, String> request) {
		Long codajuste = Long.decode(request.get(COD_AJUSTE));
		String obs = request.get("obs");
		
		return ajustes.processar(codajuste, obs);
	}
	
	@DeleteMapping("/cancelar/{codigo}")
	public @ResponseBody String remover(@PathVariable("codigo") Ajuste ajuste, UriComponentsBuilder builder) {
		UriComponents component = builder.path("/ajustes").build();
		
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(component.toUri());
		
		ajustes.remover(ajuste);
		
		return component.toString();
	}
	
	@DeleteMapping("/remove/item")
	public @ResponseBody String removeItem(@RequestParam Map<String, String> request) {
		Long codajuste = Long.decode(request.get(COD_AJUSTE));
		Long coditem = Long.decode(request.get(COD_ITEM));
		
		return ajusteProdutos.removeProduto(codajuste, coditem);
	}

	@ModelAttribute("produtos")
	public List<Produto> produtos() {
		return produtos.listar();
	}
}
