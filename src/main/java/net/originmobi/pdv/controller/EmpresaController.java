package net.originmobi.pdv.controller;

import net.originmobi.pdv.model.Cidade;
import net.originmobi.pdv.model.RegimeTributario;
import net.originmobi.pdv.model.TipoAmbiente;
import net.originmobi.pdv.service.CidadeService;
import net.originmobi.pdv.service.EmpresaService;
import net.originmobi.pdv.service.RegimeTributarioService;
import net.originmobi.pdv.service.TipoAmbienteServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/empresa")
public class EmpresaController {

	private static final String EMPRESA_FORM = "empresa/form";

	@Autowired
	private EmpresaService empresas;

	@Autowired
	private RegimeTributarioService regimesTributarios;

	@Autowired
	private CidadeService cidades;

	@Autowired
	private TipoAmbienteServer ambientes;

	@GetMapping
	public ModelAndView form() {
		ModelAndView mv = new ModelAndView(EMPRESA_FORM);
		mv.addObject("empresa", empresas.verificaEmpresaCadastrada());
		return mv;
	}

	@PostMapping
	public String cadastra(@RequestParam Map<String, String> request, RedirectAttributes attributes) {
		String strCodigo = request.get("codigo");
		String nome = request.get("nome");
		String nomeFantasia = request.get("nome_fantasia");
		String cnpj = request.get("cnpj");
		String ie = request.get("ie");
		String vlSerie = request.get("parametro.serie_nfe");
		Long codRegime = Long.decode(request.get("regime_tributario"));

		Long codcidade = Long.decode(request.get("endereco.cidade"));
		String strCodEnde = request.get("endereco.codigo");
		String rua = request.get("endereco.rua");
		String bairro = request.get("endereco.bairro");
		String numero = request.get("endereco.numero");
		String cep = request.get("endereco.cep");
		String referencia = request.get("endereco.referencia");
		String tipoAmbiente = request.get("parametro.ambiente");
		String aliqCred = request.get("parametro.pCredSN").replace(",", ".");

		int ambiente = tipoAmbiente.isEmpty() ? 0 : Integer.parseInt(tipoAmbiente);
		int serie = vlSerie.isEmpty() ? 0 : Integer.parseInt(vlSerie);
		Long codigo = strCodigo.isEmpty() ? null : Long.decode(strCodigo);
		Long codendereco = strCodEnde.isEmpty() ? null : Long.decode(strCodEnde);
		Double aliqCalcCredito = aliqCred.isEmpty() ? 0.0 : Double.parseDouble(aliqCred);

		String mensagem = empresas.merger(codigo, nome, nomeFantasia, cnpj, ie, serie, ambiente, codRegime, codendereco,
				codcidade, rua, bairro, numero, cep, referencia, aliqCalcCredito);
		attributes.addFlashAttribute("mensagem", mensagem);

		return "redirect:/empresa";
	}

	@ModelAttribute("regimeTributario")
	private List<RegimeTributario> regimeTributario() {
		return regimesTributarios.lista();
	}

	@ModelAttribute("cidades")
	private List<Cidade> cidades() {
		return cidades.lista();
	}

	@ModelAttribute("ambientes")
	private List<TipoAmbiente> ambientes() {
		return ambientes.lista();
	}

}
