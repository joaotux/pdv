package net.originmobi.pdv.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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

import net.originmobi.pdv.enumerado.caixa.CaixaTipo;
import net.originmobi.pdv.enumerado.caixa.EstiloLancamento;
import net.originmobi.pdv.enumerado.caixa.TipoLancamento;
import net.originmobi.pdv.filter.CaixaFilter;
import net.originmobi.pdv.model.Caixa;
import net.originmobi.pdv.model.CaixaLancamento;
import net.originmobi.pdv.model.Usuario;
import net.originmobi.pdv.service.CaixaLancamentoService;
import net.originmobi.pdv.service.CaixaService;
import net.originmobi.pdv.service.UsuarioService;
import net.originmobi.pdv.singleton.Aplicacao;

@Controller
@RequestMapping("/caixa")
public class CaixaController {

	private static final String CAIXA_GERENCIAR = "caixa/gerenciar";

	private static final String CAIXA_LIST = "caixa/list";

	private static final String CAIXA_FORM = "caixa/form";

	@Autowired
	private CaixaService caixas;

	@Autowired
	private CaixaLancamentoService lancamentos;

	@Autowired
	private UsuarioService usuarios;

	@GetMapping("/form")
	public ModelAndView form() {
		ModelAndView mv = new ModelAndView(CAIXA_FORM);
		mv.addObject(new Caixa());
		return mv;
	}

	@GetMapping
	public ModelAndView lista(@ModelAttribute("filterCaixa") CaixaFilter filter) {
		ModelAndView mv = new ModelAndView(CAIXA_LIST);
		mv.addObject("caixas", caixas.listarCaixas(filter));
		return mv;
	}

	@RequestMapping(method = RequestMethod.POST)
	public @ResponseBody String cadastro(@RequestParam Map<String, String> request, UriComponentsBuilder b) {
		String descricao = request.get("descricao");
		String tipo = request.get("tipo");
		String vlAbertura = request.get("valor_abertura");
		String agencia = request.get("agencia");
		String conta = request.get("conta");
		
		Double valor_abertura = vlAbertura.isEmpty() ? 0.0 : Double.valueOf(vlAbertura.replaceAll("\\.", "").replace(",", "."));
		CaixaTipo caixa_tipo = CaixaTipo.valueOf(tipo);
		
		Caixa caixa = new Caixa();
		caixa.setDescricao(descricao);
		caixa.setTipo(caixa_tipo);
		caixa.setValor_abertura(valor_abertura);
		caixa.setAgencia(agencia);
		caixa.setConta(conta);
		
		UriComponents uri = b.path("/caixa/gerenciar/").build();
		
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(uri.toUri());
		
		Long codCaixa = caixas.cadastro(caixa);

		return headers.toString() + codCaixa;
	}

	@SuppressWarnings("deprecation")
	@GetMapping("/gerenciar/{codigo}")
	public ModelAndView gerenciar(@PathVariable("codigo") Caixa caixa) {
		ModelAndView mv = new ModelAndView(CAIXA_GERENCIAR);
		mv.addObject("caixa", caixa);
		mv.addObject("lancamento", new CaixaLancamento());
		mv.addObject("lancamentos", lancamentos.lancamentosDoCaixa(caixa));
		return mv;
	}

	@RequestMapping(value = "/lancamento/suprimento", method = RequestMethod.POST)
	public @ResponseBody String fazSuprimento(@RequestParam Map<String, String> request) {
		Double valor = Double.valueOf(request.get("valor").replace(",", "."));
		String observacao = request.get("obs");
		Long codCaixa = Long.decode(request.get("caixa"));

		String retorno = "";

		try {
			Optional<Caixa> caixa = caixas.busca(codCaixa);
			Aplicacao aplicacao = Aplicacao.getInstancia();
			Usuario usuario = usuarios.buscaUsuario(aplicacao.getUsuarioAtual());

			CaixaLancamento lancamento = new CaixaLancamento(observacao, valor, TipoLancamento.SUPRIMENTO,
					EstiloLancamento.ENTRADA, caixa.get(), usuario);

			retorno = lancamentos.lancamento(lancamento);
		} catch (Exception e) {
			e.getStackTrace();
		}

		return retorno;
	}

	@RequestMapping(value = "/lancamento/sangria", method = RequestMethod.POST)
	public @ResponseBody String fazSangria(@RequestParam Map<String, String> request) {
		Double valor = Double.valueOf(request.get("valor").replace(",", "."));
		String observacao = request.get("obs");
		Long codCaixa = Long.decode(request.get("caixa"));

		String retorno = "";

		try {
			Optional<Caixa> caixa = caixas.busca(codCaixa);
			Aplicacao aplicacao = Aplicacao.getInstancia();
			Usuario usuario = usuarios.buscaUsuario(aplicacao.getUsuarioAtual());

			CaixaLancamento lancamento = new CaixaLancamento(observacao, valor, TipoLancamento.SANGRIA,
					EstiloLancamento.SAIDA, caixa.get(), usuario);
			retorno = lancamentos.lancamento(lancamento);
		} catch (Exception e) {
			e.getStackTrace();
		}

		return retorno;
	}

	@RequestMapping(value = "/fechar", method = RequestMethod.POST)
	public @ResponseBody String fecha(@RequestParam Map<String, String> request) {
		Long caixa = Long.decode(request.get("caixa"));
		String senha = request.get("senha");
		
		String mensagem = "";
		try {
			mensagem = caixas.fechaCaixa(caixa, senha);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		return mensagem;
	}
	
	@ModelAttribute("usuarioAtual")
	public String usuarioAtual() {
		Aplicacao aplicacao = Aplicacao.getInstancia();
		return aplicacao.getUsuarioAtual();
	}

	@ModelAttribute("caixatipo")
	public List<CaixaTipo> caixatipo() {
		return Arrays.asList(CaixaTipo.values());
	}

	@ModelAttribute("destinos")
	public List<Caixa> destinos() {
		return caixas.caixasAbertos();
	}
}
