package net.originmobi.pdv.service;

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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


@Service
public class CaixaService {

    private final Logger logger = LoggerFactory.getLogger(getClass());

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
        Double vlbertura = caixa.getValorAbertura() == null ? 0.0 : caixa.getValorAbertura();
        caixa.setValorAbertura(vlbertura);

        if (caixa.getValorAbertura() < 0)
            throw new RuntimeException("Valor informado é inválido");

        Aplicacao aplicacao = Aplicacao.getInstancia();
        Usuario usuario = usuarios.buscaUsuario(aplicacao.getUsuarioAtual());
        String descricao = caixa.getDescricao().isEmpty() ? getTipoFormatado(caixa) : caixa.getDescricao();

        LocalDate dataAtual = LocalDate.now();

        caixa.setDescricao(descricao);
        caixa.setUsuario(usuario);
        caixa.setDataCadastro(java.sql.Date.valueOf(dataAtual));

        // se for BANCO, limpa os valores especiais de agencia e conta
        if (caixa.getTipo().equals(CaixaTipo.BANCO)) {
            logger.info("agencia {}", caixa.getAgencia());
            logger.info("conta {}", caixa.getConta());
            caixa.setAgencia(caixa.getAgencia().replaceAll("\\D", ""));
            caixa.setConta(caixa.getConta().replaceAll("\\D", ""));
        }

        try {
            caixa = caixas.save(caixa);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new RuntimeException("Erro no processo de abertura, chame o suporte técnico");
        }

        if (caixa.getValorAbertura() > 0) {
            try {

                String observacao = "Abertura de " + getTipoFormatado(caixa);

                CaixaLancamento lancamento = new CaixaLancamento(observacao, caixa.getValorAbertura(),
                        TipoLancamento.SALDOINICIAL, EstiloLancamento.ENTRADA, caixa, usuario);

                lancamentos.lancamento(lancamento);

            } catch (Exception e) {
                e.getStackTrace();
                throw new RuntimeException("Erro no processo, chame o suporte");
            }
        } else {
            // se não for realizado o lançamento de caixa então joga o valor total do caixa
            // para 0.0
            caixa.setValorTotal(0.0);
        }

        return caixa.getCodigo();
    }

    private String getTipoFormatado(Caixa caixa) {
        if (caixa.getTipo().equals(CaixaTipo.CAIXA)) {
            return "Caixa";
        }
        return caixa.getTipo().equals(CaixaTipo.COFRE) ? "Cofre" : "Banco";
    }

    public String fechaCaixa(Long caixa, String senha) {

        Aplicacao aplicacao = Aplicacao.getInstancia();
        Usuario usuario = usuarios.buscaUsuario(aplicacao.getUsuarioAtual());

        BCryptPasswordEncoder decoder = new BCryptPasswordEncoder();

        if (senha.equals(""))
            return "Favor, informe a senha";

        if (decoder.matches(senha, usuario.getSenha())) {

            // busca caixa atual
            Optional<Caixa> caixaAtualOptional = caixas.findById(caixa);

            if (!caixaAtualOptional.isPresent()) {
                throw new RuntimeException("Caixa não encontrado");
            }
            Caixa caixaAtual = caixaAtualOptional.get();

            if (caixaAtual.getDataFechamento() != null)
                throw new RuntimeException("Caixa já esta fechado");

            Double valorTotal = caixaAtual.getValorTotal() == null ? 0.0
                    : caixaAtual.getValorTotal();

            Timestamp dataHoraAtual = new Timestamp(System.currentTimeMillis());
            caixaAtual.setDataFechamento(dataHoraAtual);
            caixaAtual.setValorFechamento(valorTotal);

            try {
                caixas.save(caixaAtual);
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
        if (filter.getDataCadastro() != null && !filter.getDataCadastro().equals("")) {
            filter.setDataCadastro(filter.getDataCadastro().replace("/", "-"));
            return caixas.buscaCaixasPorDataAbertura(Date.valueOf(filter.getDataCadastro()));
        }

        return caixas.listaCaixas();
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
        return Optional.ofNullable(caixas.findByCaixaAbertoUsuario(usu.getCodigo()));
    }

    public List<Caixa> listaBancos() {
        return caixas.buscaBancos(CaixaTipo.BANCO);
    }

    public List<Caixa> listaCaixasAbertosTipo(CaixaTipo tipo) {
        return caixas.buscaCaixaTipo(tipo);
    }

    public List<Caixa> listaBancosAbertosTipoFilterBanco(CaixaTipo tipo, BancoFilter filter) {
        if (filter.getDataCadastro() != null && !filter.getDataCadastro().equals("")) {
            filter.setDataCadastro(filter.getDataCadastro().replace("/", "-"));
            return caixas.buscaCaixaTipoData(tipo, Date.valueOf(filter.getDataCadastro()));
        }

        return caixas.buscaCaixaTipo(CaixaTipo.BANCO);
    }

}
