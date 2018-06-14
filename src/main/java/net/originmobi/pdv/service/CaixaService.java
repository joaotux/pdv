package net.originmobi.pdv.service;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import net.originmobi.pdv.enumerado.caixa.CaixaTipo;
import net.originmobi.pdv.enumerado.caixa.EstiloLancamento;
import net.originmobi.pdv.enumerado.caixa.TipoLancamento;
import net.originmobi.pdv.filter.BancoFilter;
import net.originmobi.pdv.filter.CaixaFilter;
import net.originmobi.pdv.model.Caixa;
import net.originmobi.pdv.model.CaixaLancamento;
import net.originmobi.pdv.model.Usuario;
import net.originmobi.pdv.repository.CaixaRepository;
import net.originmobi.pdv.singleton.Aplicacao;

@Service
public class CaixaService {

	private String descricao;
	private Usuario usuario;

	@Autowired
	private CaixaRepository caixas;

	@Autowired
	private UsuarioService usuarios;

	@Autowired
	private CaixaLancamentoService lancamentos;

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public Long cadastro(Caixa caixa) {

		if (caixa.getTipo().equals(CaixaTipo.CAIXA) && caixaIsAberto())
			throw new RuntimeException("Existe caixa de dias anteriores em aberto, favor verifique");

		// caso o valor de abertura seja null, modifica o mesmo para 0.0, esse valor é
		// adicionado tambem no valor_total
		Double vlbertura = caixa.getValor_abertura() == null ? 0.0 : caixa.getValor_abertura();
		caixa.setValor_abertura(vlbertura);

		if (caixa.getValor_abertura() < 0)
			throw new RuntimeException("Valor informado é inválido");

		Aplicacao aplicacao = Aplicacao.getInstancia();
		usuario = usuarios.buscaUsuario(aplicacao.getUsuarioAtual());

		if (caixa.getTipo().equals(CaixaTipo.CAIXA))
			descricao = caixa.getDescricao().isEmpty() ? "Caixa diário" : caixa.getDescricao();
		else if (caixa.getTipo().equals(CaixaTipo.COFRE))
			descricao = caixa.getDescricao().isEmpty() ? "Cofre" : caixa.getDescricao();
		else if (caixa.getTipo().equals(CaixaTipo.BANCO))
			descricao = caixa.getDescricao().isEmpty() ? "Banco" : caixa.getDescricao();

		LocalDate dataAtual = LocalDate.now();

		caixa.setDescricao(descricao);
		caixa.setUsuario(usuario);
		caixa.setData_cadastro(java.sql.Date.valueOf(dataAtual));

		// se for BANCO, limpa os valores especiais de agencia e conta
		if (caixa.getTipo().equals(CaixaTipo.BANCO)) {
			System.out.println("agencia " + caixa.getAgencia());
			System.out.println("conta " + caixa.getConta());
			caixa.setAgencia(caixa.getAgencia().replaceAll("\\D", ""));
			caixa.setConta(caixa.getConta().replaceAll("\\D", ""));
		}

		try {
			caixas.save(caixa);
		} catch (Exception e) {
			e.getStackTrace();
			throw new RuntimeException("Erro no processo de abertura, chame o suporte técnico");
		}

		if (caixa.getValor_abertura() > 0) {
			try {

				String observacao = caixa.getTipo().equals(CaixaTipo.CAIXA) ? "Abertura de caixa"
						: caixa.getTipo().equals(CaixaTipo.COFRE) ? "Abertura de cofre" : "Abertura de banco";

				CaixaLancamento lancamento = new CaixaLancamento(observacao, caixa.getValor_abertura(),
						TipoLancamento.SALDOINICIAL, EstiloLancamento.ENTRADA, caixa, usuario);

				lancamentos.lancamento(lancamento);

			} catch (Exception e) {
				e.getStackTrace();
				throw new RuntimeException("Erro no processo, chame o suporte");
			}
		} else {
			// se não for realizado o lançamento de caixa então joga o valor total do caixa
			// para 0.0
			caixa.setValor_total(0.0);
		}

		return caixa.getCodigo();
	}

	public String fechaCaixa(Long caixa, String senha) {

		Aplicacao aplicacao = Aplicacao.getInstancia();
		Usuario usuario = usuarios.buscaUsuario(aplicacao.getUsuarioAtual());

		BCryptPasswordEncoder decode = new BCryptPasswordEncoder();

		if (senha.equals(""))
			return "Favor, informe a senha";

		if (decode.matches(senha, usuario.getSenha())) {

			// busca caixa atual
			Optional<Caixa> caixaAtual = caixas.findById(caixa);

			if (caixaAtual.map(Caixa::getData_fechamento).isPresent())
				throw new RuntimeException("Caixa já esta fechado");

			Double valorTotal = !caixaAtual.map(Caixa::getValor_total).isPresent() ? 0.0
					: caixaAtual.map(Caixa::getValor_total).get();

			Timestamp dataHoraAtual = new Timestamp(System.currentTimeMillis());
			caixaAtual.get().setData_fechamento(dataHoraAtual);
			caixaAtual.get().setValor_fechamento(valorTotal);

			try {
				caixas.save(caixaAtual.get());
			} catch (Exception e) {
				throw new RuntimeException("Ocorreu um erro ao fechar o caixa, chame o suporte");
			}

			return "Caixa fechado com sucesso";

		} else {
			return "Senha incorreta, favor verifique";
		}
	}

	public boolean caixaIsAberto() {
		return caixas.caixaAberto().isPresent();
	}

	public List<Caixa> listaTodos() {
		return caixas.findByCodigoOrdenado();
	}

	public List<Caixa> listarCaixas(CaixaFilter filter) {
		if (filter.getData_cadastro() != null) {
			if (!filter.getData_cadastro().equals("")) {
				filter.setData_cadastro(filter.getData_cadastro().replace("/", "-"));
				return caixas.buscaCaixasPorDataAbertura(Date.valueOf(filter.getData_cadastro()));
			}
		}
		
		return caixas.listaCaixasAbertos();
	}

	public Optional<Caixa> caixaAberto() {
		return caixas.caixaAberto();
	}

	public List<Caixa> caixasAbertos() {
		return caixas.caixasAbertos();
	}

	public Optional<Caixa> busca(Long codigo) {
		return caixas.findById(codigo);
	}

	// pega o caixa aberto do usuário informado
	public Optional<Caixa> buscaCaixaUsuario(String usuario) {
		Usuario usu = usuarios.buscaUsuario(usuario);
		Optional<Caixa> caixaOptional = Optional.ofNullable(caixas.findByCaixaAbertoUsuario(usu.getCodigo()));
		return caixaOptional;
	}

	public List<Caixa> listaBancos() {
		return caixas.buscaBancos(CaixaTipo.BANCO);
	}

	public List<Caixa> listaCaixasAbertosTipo(CaixaTipo tipo) {
		return caixas.buscaCaixaTipo(tipo);
	}

	public List<Caixa> listaBancosAbertosTipoFilterBanco(CaixaTipo tipo, BancoFilter filter) {
		if (filter.getData_cadastro() != null) {
			if (!filter.getData_cadastro().equals("")) {
				filter.setData_cadastro(filter.getData_cadastro().replace("/", "-"));
				return caixas.buscaCaixaTipoData(tipo, Date.valueOf(filter.getData_cadastro()));
			}
		}

		return caixas.buscaCaixaTipo(CaixaTipo.BANCO);
	}

}
