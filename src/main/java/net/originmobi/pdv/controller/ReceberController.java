package net.originmobi.pdv.controller;

import net.originmobi.pdv.filter.ClienteFilter;
import net.originmobi.pdv.model.Parcela;
import net.originmobi.pdv.model.Pessoa;
import net.originmobi.pdv.model.Receber;
import net.originmobi.pdv.service.ParcelaService;
import net.originmobi.pdv.service.PessoaService;
import net.originmobi.pdv.service.RecebimentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/receber")
@SessionAttributes("filtrocli")
public class ReceberController {

	private static final String RECEBER_LIST = "receber/list";

	private static final String RECEBER_FORM = "receber/form";

	@Autowired
	private PessoaService pessoas;

	@Autowired
	private ParcelaService parcelas;

	@Autowired
	private RecebimentoService recebimentos;

	@GetMapping("/form")
	public ModelAndView form() {
		ModelAndView mv = new ModelAndView(RECEBER_FORM);
		mv.addObject("receber", new Receber());
		return mv;
	}

	@ModelAttribute("filtrocli")
	public ClienteFilter inicializerFilter() {
		return new ClienteFilter();
	}

	@GetMapping
	public ModelAndView list(@ModelAttribute("filtrocli") ClienteFilter filter) {
		ModelAndView mv = new ModelAndView(RECEBER_LIST);
		List<Parcela> pagina = parcelas.lista(filter);
		mv.addObject("parcelas", pagina);
		mv.addObject("totalReceber", parcelas.totalReceberCliente(filter));

		return mv;
	}

	@PostMapping("/parcelaReceber")
	public @ResponseBody String receber(@RequestParam Map<String, String> request) {
		Long parcela = Long.decode(request.get("receber"));
		String vltotalPago = request.get("vltotalPago").replace(",", ".");
		String acre = request.get("vlacre").replace(",", ".");
		String desc = request.get("vldesc").replace(",", ".");

		Double totalPago = vltotalPago.isEmpty() ? 0.00 : Double.parseDouble(vltotalPago);
		Double acrescimo = acre.isEmpty() ? 0.00 : Double.parseDouble(acre);
		Double desconto = desc.isEmpty() ? 0.00 : Double.parseDouble(desc);

		return parcelas.receber(parcela, totalPago, acrescimo, desconto);
	}

	@GetMapping("/parcelas")
	public @ResponseBody String recebimento(@RequestParam Map<String, String> request, UriComponentsBuilder b) {
		Long codpes = Long.decode(request.get("codpessoa"));
		String[] arrayParcelas = request.get("parcelas").replace(", ", " ").split(" ");
		
		UriComponents builder = b.path("/recebimento/").build();
		
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(builder.toUri());
		
		String retorno = recebimentos.abrirRecebimento(codpes, arrayParcelas);
		
		return headers.toString() + retorno;
	}

	@ModelAttribute("pessoas")
	public List<Pessoa> pessoas() {
		return pessoas.lista();
	}
}
