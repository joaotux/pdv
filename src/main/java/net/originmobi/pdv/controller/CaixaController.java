package net.originmobi.pdv.controller;

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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.regex.Pattern;

@Controller
@RequestMapping("/caixa")
public class CaixaController {

	private static final String CAIXA_GERENCIAR = "caixa/gerenciar";

	private static final String CAIXA_LIST = "caixa/list";

	private static final String CAIXA_FORM = "caixa/form";
	public static final String CAIXA = "caixa";

	Logger logger = LoggerFactory.getLogger(CaixaController.class);

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

	@PostMapping
	public @ResponseBody String cadastro(@RequestParam Map<String, String> request, UriComponentsBuilder b) {
		String descricao = request.get("descricao");
		String tipo = request.get("tipo");
		String vlAbertura = request.get("valorAbertura");
		String agencia = request.get("agencia");
		String conta = request.get("conta");

        double valorAbertura = 0.0;
        if (!vlAbertura.isEmpty()) {
            String valorTratado = Pattern.compile("\\.").matcher(vlAbertura)
                    .replaceAll("")
                    .replace(',', '.');
            valorAbertura = Double.parseDouble(valorTratado);
        }
		CaixaTipo caixaTipo = CaixaTipo.valueOf(tipo);

		Caixa caixa = new Caixa();
		caixa.setDescricao(descricao);
		caixa.setTipo(caixaTipo);
		caixa.setValorAbertura(valorAbertura);
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
		mv.addObject(CAIXA, caixa);
		mv.addObject("lancamento", new CaixaLancamento());
		mv.addObject("lancamentos", lancamentos.lancamentosDoCaixa(caixa));
		return mv;
	}

	@PostMapping("/lancamento/suprimento")
	public @ResponseBody String fazSuprimento(@RequestParam Map<String, String> request) {
		Double valor = Double.valueOf(request.get("valor").replace(",", "."));
		String observacao = request.get("obs");
		Long codCaixa = Long.decode(request.get(CAIXA));

		String retorno = "";

		try {
			Optional<Caixa> caixa = caixas.busca(codCaixa);
			if (caixa.isPresent()) {
				Aplicacao aplicacao = Aplicacao.getInstancia();
				Usuario usuario = usuarios.buscaUsuario(aplicacao.getUsuarioAtual());
				CaixaLancamento lancamento = new CaixaLancamento(observacao, valor, TipoLancamento.SUPRIMENTO,
						EstiloLancamento.ENTRADA, caixa.get(), usuario);

				retorno = lancamentos.lancamento(lancamento);
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
		}

		return retorno;
	}

	@PostMapping("/lancamento/sangria")
	public @ResponseBody String fazSangria(@RequestParam Map<String, String> request) {
		Double valor = Double.valueOf(request.get("valor").replace(",", "."));
		String observacao = request.get("obs");
		Long codCaixa = Long.decode(request.get(CAIXA));

		String retorno = "";

		try {
			Optional<Caixa> caixa = caixas.busca(codCaixa);
			if (caixa.isPresent()) {
				Aplicacao aplicacao = Aplicacao.getInstancia();
				Usuario usuario = usuarios.buscaUsuario(aplicacao.getUsuarioAtual());

				CaixaLancamento lancamento = new CaixaLancamento(observacao, valor, TipoLancamento.SANGRIA,
						EstiloLancamento.SAIDA, caixa.get(), usuario);
				retorno = lancamentos.lancamento(lancamento);
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
		}

		return retorno;
	}

	@PostMapping("/fechar")
	public @ResponseBody String fecha(@RequestParam Map<String, String> request) {
		Long caixa = Long.decode(request.get(CAIXA));
		String senha = request.get("senha");

		String mensagem = "";
		try {
			mensagem = caixas.fechaCaixa(caixa, senha);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return e.getMessage();
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
