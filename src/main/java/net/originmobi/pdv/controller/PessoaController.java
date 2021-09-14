package net.originmobi.pdv.controller;

import net.originmobi.pdv.enumerado.PessoaTipo;
import net.originmobi.pdv.enumerado.TelefoneTipo;
import net.originmobi.pdv.filter.PessoaFilter;
import net.originmobi.pdv.model.Cidade;
import net.originmobi.pdv.model.Endereco;
import net.originmobi.pdv.model.Pessoa;
import net.originmobi.pdv.model.Telefone;
import net.originmobi.pdv.service.CidadeService;
import net.originmobi.pdv.service.EnderecoService;
import net.originmobi.pdv.service.PessoaService;
import net.originmobi.pdv.service.TelefoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.text.ParseException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/pessoa")
public class PessoaController {

	private static final String PESSOA_LISTA = "pessoa/list";
	private static final String PESSOA_FORM = "pessoa/form";

	@Autowired
	private PessoaService pessoas;

	@Autowired
	private CidadeService cidades;

	@Autowired
	private EnderecoService enderecos;

	@Autowired
	private TelefoneService telefones;

	@GetMapping("/form")
	public ModelAndView form() {
		ModelAndView mv = new ModelAndView(PESSOA_FORM);
		mv.addObject(new Pessoa());
		mv.addObject(new Endereco());
		mv.addObject(new Telefone());
		return mv;
	}

	@GetMapping
	public ModelAndView listar(@ModelAttribute("filter") PessoaFilter filter) {
		ModelAndView mv = new ModelAndView(PESSOA_LISTA);
		mv.addObject("pessoas", pessoas.filter(filter));
		return mv;
	}

	@PostMapping
	public @ResponseBody String cadastrar(@RequestParam Map<String, String> request, RedirectAttributes attributes)
			throws ParseException {
		// Pessoa
		String stCodPessoa = request.get("codigo") != null ? request.get("codigo") : "";
		String nome = request.get("nome");
		String apelido = request.get("apelido");
		String cpfcnpj = request.get("cpfcnpj").replaceAll("\\D", "");
		String dataNascimento = request.get("dataNascimento");
		String observacao = request.get("observacao");
		
		// Endereço
		String stCodEndereco = request.get("codendereco") != null ? request.get("codendereco") : "";
		Long codcidade = Long.decode(request.get("cidade"));
		String rua = request.get("rua");
		String bairro = request.get("bairro");
		String numero = request.get("numero");
		String cep = request.get("cep");
		String referencia = request.get("referencia");

		// Fone
		String stCodFone = request.get("codfone") != null ? request.get("codfone") : "";
		String fone = request.get("fone").replaceAll("\\D", "");
		String tipo = request.get("tipo");

		Long codpessoa = stCodPessoa.isEmpty() ? 0L : Long.decode(stCodPessoa);
		Long codendereco = stCodEndereco.isEmpty() ? 0L : Long.decode(stCodEndereco);
		Long codfone = stCodFone.isEmpty() ? 0L : Long.decode(stCodFone);

		return pessoas.cadastrar(codpessoa, nome, apelido, cpfcnpj, dataNascimento, observacao, codendereco, codcidade, rua,
				bairro, numero, cep, referencia, codfone, fone, tipo, attributes);
	}

	@GetMapping("{codigo}")
	public ModelAndView edite(@PathVariable("codigo") Pessoa pessoa) {
		ModelAndView mv = new ModelAndView(PESSOA_FORM);
		mv.addObject(pessoa);
		mv.addObject("endereco", enderecos.enderecoCodigo(pessoa.getEndereco().getCodigo()));
		mv.addObject("telefone", telefones.telefoneCodigo(pessoa.getTelefone().get(0).getCodigo()));
		return mv;
	}

	@PutMapping("/{codigo}")
	public @ResponseBody Pessoa busca(@PathVariable("codigo") Long codigo) {
		return pessoas.busca(codigo);
	}

	@ModelAttribute("tipoPessoa")
	public List<PessoaTipo> pessoaTipo() {
		return Arrays.asList(PessoaTipo.values());
	}

	@ModelAttribute("cidades")
	public List<Cidade> cidades() {
		return cidades.lista();
	}

	@ModelAttribute("telefoneTipo")
	public List<TelefoneTipo> telefoneTipo() {
		return Arrays.asList(TelefoneTipo.values());
	}

}
