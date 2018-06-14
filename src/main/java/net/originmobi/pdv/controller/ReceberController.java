package net.originmobi.pdv.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import net.originmobi.pdv.filter.ClienteFilter;
import net.originmobi.pdv.model.Parcela;
import net.originmobi.pdv.model.Pessoa;
import net.originmobi.pdv.model.Receber;
import net.originmobi.pdv.service.ParcelaService;
import net.originmobi.pdv.service.PessoaService;
import net.originmobi.pdv.service.RecebimentoService;

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

	@SuppressWarnings("deprecation")
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

	@RequestMapping(value = "/parcelaReceber", method = RequestMethod.POST)
	public @ResponseBody String receber(@RequestParam Map<String, String> request) {
		Long parcela = Long.decode(request.get("receber"));
		String vltotalPago = request.get("vltotalPago").replace(",", ".");
		String acre = request.get("vlacre").replace(",", ".");
		String desc = request.get("vldesc").replace(",", ".");

		Double totalPago = vltotalPago.isEmpty() ? 0.00 : Double.valueOf(vltotalPago);
		Double acrescimo = acre.isEmpty() ? 0.00 : Double.valueOf(acre);
		Double desconto = desc.isEmpty() ? 0.00 : Double.valueOf(desc);

		String mensagem = null;

		mensagem = parcelas.receber(parcela, totalPago, acrescimo, desconto);

		return mensagem;
	}

	@RequestMapping(value = "/parcelas", method = RequestMethod.GET)
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
