package net.originmobi.pdv.controller;

import net.originmobi.pdv.model.Recebimento;
import net.originmobi.pdv.model.Titulo;
import net.originmobi.pdv.service.RecebimentoService;
import net.originmobi.pdv.service.TituloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/recebimento")
public class RecebimentoController {

	private static final String RECEBIMENTO_FORM = "recebimento/form";
	
	@Autowired
	private RecebimentoService recebimentos;
	
	@Autowired
	private TituloService titulos;
	
	@GetMapping("/{codigo}")
	public ModelAndView form(@PathVariable("codigo") Recebimento recebimento) {
		ModelAndView mv = new ModelAndView(RECEBIMENTO_FORM);
		mv.addObject("recebimento", recebimento);
		return mv;
	}
	
	@PostMapping
	public @ResponseBody String receber(@RequestParam Map<String, String> request){
		Long codreceber = Long.decode(request.get("receber"));
		
		String titulo = request.get("titulo");
		Long codtitulo = titulo.isEmpty() ? 0L : Long.decode(titulo);
		
		String recebido = request.get("vlrecebido").replace(",", ".");
		String desconto = request.get("desconto").replace(",", ".");
		String acrescimo = request.get("acrescimo").replace(",", ".");
		
		Double vlrecebido = recebido.isEmpty() ? 0.0 : Double.parseDouble(recebido);
		Double vldesconto = desconto.isEmpty() ? 0.0 : Double.parseDouble(desconto);
		Double vlacrescimo = acrescimo.isEmpty() ? 0.0 : Double.parseDouble(acrescimo);
		
		String mensagem = "";
		
		mensagem = recebimentos.receber(codreceber, vlrecebido, vlacrescimo, vldesconto, codtitulo);
		
		return mensagem;
	}
	
	@PutMapping("{codigo}")
	public @ResponseBody String remove(@RequestParam("codigo") Long codigo){
		
		recebimentos.remover(codigo);
		
		return "http://localhost:8080/receber";
	}
	
	@ModelAttribute("titulos")
	public List<Titulo> titulos() {
		return titulos.lista();
	}
	
}
