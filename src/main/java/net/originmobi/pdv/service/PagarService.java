package net.originmobi.pdv.service;

import net.originmobi.pdv.enumerado.caixa.EstiloLancamento;
import net.originmobi.pdv.enumerado.caixa.TipoLancamento;
import net.originmobi.pdv.model.*;
import net.originmobi.pdv.repository.PagarRepository;
import net.originmobi.pdv.singleton.Aplicacao;
import net.originmobi.pdv.utilitarios.DataAtual;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class PagarService {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private PagarRepository pagarRepo;

    @Autowired
    private PagarParcelaService pagarParcelaServ;

    @Autowired
    private FornecedorService fornecedores;

    @Autowired
    private CaixaService caixas;

    @Autowired
    private UsuarioService usuarios;

    @Autowired
    private CaixaLancamentoService lancamentos;

    public List<Pagar> listar() {
        return pagarRepo.findAll();
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public String cadastrar(Long codFornecedor, Double valor, String obs, LocalDate vencimento, PagarTipo tipo) {
        LocalDate dataAtual = LocalDate.now();
        DataAtual dataTime = new DataAtual();

        obs = obs.isEmpty() ? tipo.getDescricao() : obs;

        Optional<Fornecedor> fornecedorOptional = fornecedores.busca(codFornecedor);
        if (!fornecedorOptional.isPresent()) {
            logger.error("Fornecedor não encontrado");
            throw new RuntimeException("Fornecedor não encontrado");
        }

        Fornecedor fornecedor = fornecedorOptional.get();

        Pagar pagar = new Pagar(obs, valor, dataAtual, fornecedor, tipo);

        try {
            pagarRepo.save(pagar);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new RuntimeException("Erro ao lançar despesa, chame o suporte");
        }

        try {
            pagarParcelaServ.cadastrar(valor, valor, 0, dataTime.dataAtualTimeStamp(), vencimento, pagar);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new RuntimeException("Erro ao lançar despesa, chame o suporte");
        }

        return "Despesa lançada com sucesso";
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public String quitar(Long codparcela, Double vlPago, Double vldesc, Double vlacre, Long codCaixa) {

        Optional<PagarParcela> parcelaOptional = pagarParcelaServ.busca(codparcela);

        if (!parcelaOptional.isPresent()) {
            logger.error("Parcela não encontrada");
            throw new RuntimeException("Parcela não encontrada");
        }

        PagarParcela parcela = parcelaOptional.get();

        DecimalFormat df = new DecimalFormat("#0.00");

        if (vlPago > Double.parseDouble(df.format(parcela.getValorRestante()).replace(",", ".")))
            throw new RuntimeException("Valor de pagamento inválido");

        Double vlquitado = (vlPago + vlacre) + parcela.getValorPago();
        Double vlRestante = (parcela.getValorRestante() - (vlPago + vldesc));
        Double vlDesconto = parcela.getValorDesconto() + vldesc;
        Double vlAcrescimo = parcela.getValorAcrescimo() + vlacre;

        vlRestante = vlRestante < 0 ? 0.0 : vlRestante;

        int quitado = Double.parseDouble(df.format(vlRestante).replace(",", ".")) <= 0 ? 1 : 0;

        DataAtual dataAtual = new DataAtual();

        parcela.setValorPago(vlquitado);
        parcela.setValorRestante(vlRestante);
        parcela.setValorDesconto(vlDesconto);
        parcela.setValorAcrescimo(vlAcrescimo);
        parcela.setQuitado(quitado);
        parcela.setDataPagamento(dataAtual.dataAtualTimeStamp());

        try {
            pagarParcelaServ.merger(parcela);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new RuntimeException("Ocorreu um erro ao realizar o pagamento, chame o suporte");
        }

        Aplicacao aplicacao = Aplicacao.getInstancia();

        Usuario usuario = usuarios.buscaUsuario(aplicacao.getUsuarioAtual());
        Optional<Caixa> caixaOptional = caixas.busca(codCaixa);

        if (!caixaOptional.isPresent()) {
            logger.error("Caixa não encontrado");
            throw new RuntimeException("Caixa não encontrado");
        }

        Caixa caixa = caixaOptional.get();

        if (vlPago + vlacre > caixa.getValorTotal())
            throw new RuntimeException("Saldo insuficiente para realizar este pagamento");

        try {
            CaixaLancamento lancamento = new CaixaLancamento("Referente a pagamento de despesas", vlPago + vlacre,
                    TipoLancamento.PAGAMENTO, EstiloLancamento.SAIDA, caixa, usuario);

            // vincula a parcela do pagar ao caixa_lancametno
            lancamento.setParcelaPagar(parcela);

            lancamentos.lancamento(lancamento);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new RuntimeException("Ocorreu um erro ao realizar o pagamento, chame o suporte");
        }

        return "Pagamento realizado com sucesso";
    }

}
