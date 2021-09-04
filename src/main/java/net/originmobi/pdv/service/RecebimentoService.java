package net.originmobi.pdv.service;

import com.ibm.icu.text.DecimalFormat;
import net.originmobi.pdv.enumerado.TituloTipo;
import net.originmobi.pdv.enumerado.caixa.EstiloLancamento;
import net.originmobi.pdv.enumerado.caixa.TipoLancamento;
import net.originmobi.pdv.model.*;
import net.originmobi.pdv.repository.RecebimentoRepository;
import net.originmobi.pdv.service.cartao.CartaoLancamentoService;
import net.originmobi.pdv.singleton.Aplicacao;
import net.originmobi.pdv.utilitarios.DataAtual;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RecebimentoService {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private RecebimentoRepository recebimentos;

    @Autowired
    private PessoaService pessoas;

    @Autowired
    private RecebimentoParcelaService receParcelas;

    @Autowired
    private ParcelaService parcelas;

    @Autowired
    private CaixaService caixas;

    @Autowired
    private UsuarioService usuarios;

    @Autowired
    private CaixaLancamentoService lancamentos;

    @Autowired
    private TituloService titulos;

    @Autowired
    private CartaoLancamentoService cartaoLancamentos;

    public String abrirRecebimento(Long codpes, String[] arrayParcelas) {
        List<Parcela> lista = new ArrayList<>();

        DataAtual dataAtual = new DataAtual();
        double vlTotal = 0.0;

        for (String arrayParcela : arrayParcelas) {
            Parcela parcela = parcelas.busca(Long.decode(arrayParcela));

            if (parcela.getQuitado() == 1)
                throw new RuntimeException("Parcela " + parcela.getCodigo() + " já esta quitada, verifique.");

            if (!parcela.getReceber().getPessoa().getCodigo().equals(codpes))
                throw new RuntimeException("A parcela " + parcela.getCodigo() + " não pertence ao cliente selecionado");

            try {
                lista.add(parcela);

                vlTotal = vlTotal + parcela.getValorRestante();

            } catch (Exception e) {
                logger.error(e.getMessage(), e);
                throw new RuntimeException("Erro ao receber, chame o suporte");
            }
        }

        Optional<Pessoa> pessoa = pessoas.buscaPessoa(codpes);

        if (!pessoa.isPresent())
            throw new RuntimeException("Cliente não encontrado");

        Recebimento recebimento = new Recebimento(vlTotal, dataAtual.dataAtualTimeStamp(), pessoa.get(), lista);

        try {
            recebimentos.save(recebimento);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new RuntimeException("Erro ao receber, chame o suporte");
        }

        return recebimento.getCodigo().toString();
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public String receber(Long codreceber, Double vlrecebido, Double vlacrescimo, Double vldesconto, Long codtitulo) {
        Optional<Recebimento> recebimentoOptional = recebimentos.findById(codreceber);

        if (codtitulo == null || codtitulo == 0)
            throw new RuntimeException("Selecione um título para realizar o recebimento");

        Optional<Titulo> tituloOptional = titulos.busca(codtitulo);
        if (!tituloOptional.isPresent()) {
            throw new RuntimeException("Titulo não encontrado");
        }

        Titulo titulo = tituloOptional.get();

        if (!recebimentoOptional.isPresent()) {
            throw new RuntimeException("Recebimento não encontrado");
        }

        Recebimento recebimento = recebimentoOptional.get();

        if (recebimento.getDataProcessamento() != null)
            throw new RuntimeException("Recebimento já esta fechado");

        // vincula o titulo ao recebimento
        recebimento.setTitulo(titulo);

        DecimalFormat formata = new DecimalFormat("0.00");
        Double vlrecebimento = Double
                .valueOf(formata.format(recebimento.getValorTotal()).replace(",", "."));

        if (vlrecebido > vlrecebimento)
            throw new RuntimeException("Valor de recebimento é superior aos títulos");

        List<Parcela> listParcelas = receParcelas.parcelasDoReceber(codreceber);

        if (listParcelas.isEmpty())
            throw new RuntimeException("Recebimento não possue parcelas");

        if (vlrecebido <= 0.0)
            throw new RuntimeException("Valor de recebimento inválido");

        // guarda o valor do lançamento de caixa
        Double vllancamento = vlrecebido;

        // verifica cada parcela que veio e realiza o seu recebimento individual
        trataRecebimentoDeParcela(vlrecebido, listParcelas);

        Aplicacao aplicacao = new Aplicacao();
        Usuario usuario = usuarios.buscaUsuario(aplicacao.getUsuarioAtual());

        // pega a sigla do titulo
        String sigla = titulo.getTipo().getSigla();

        // verifica se é um lançamento do tipo cartão para lançar o cartao_lancamento
        trataLancamentoCartao(codreceber, titulo, recebimento, vllancamento, usuario, sigla);

        try {
            DataAtual dataAtual = new DataAtual();

            recebimento.setValorRecebido(vllancamento);
            recebimento.setValorAcrescimo(vlacrescimo);
            recebimento.setValorDesconto(vldesconto);
            recebimento.setDataProcessamento(dataAtual.dataAtualTimeStamp());

            recebimentos.save(recebimento);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new RuntimeException("Ocorreu um erro ao realizar o recebimento, chame o suporte");
        }

        return "Recebimento realizado com sucesso";
    }

    private void trataLancamentoCartao(Long codreceber, Titulo titulo, Recebimento recebimento, Double vllancamento, Usuario usuario, String sigla) {
        if (sigla.equals(TituloTipo.CARTDEB.toString()) || sigla.equals(TituloTipo.CARTCRED.toString())) {
            cartaoLancamentos.lancamento(vllancamento, titulo);

        } else {
            Optional<Caixa> caixaOptional = caixas.caixaAberto();

            if (!caixaOptional.isPresent()) {
                throw new RuntimeException("Caixa não encontrado");
            }

            Caixa caixa = caixaOptional.get();

            CaixaLancamento lancamento = new CaixaLancamento("Referente ao recebimento " + codreceber,
                    vllancamento, TipoLancamento.RECEBIMENTO, EstiloLancamento.ENTRADA, caixa, usuario);

            // vincula o recebimento ao caixa_lancamento
            lancamento.setRecebimento(recebimento);

            // Faz um caixa lançamento no valor do receber;
            try {
                lancamentos.lancamento(lancamento);
            } catch (Exception e) {
                logger.error(e.getMessage(), e);
                throw new RuntimeException("Ocorreu um erro ao realizar o recebimento, chame o suporte");
            }
        }
    }

    private void trataRecebimentoDeParcela(Double vlrecebido, List<Parcela> listParcelas) {
        for (Parcela listParcela : listParcelas) {

            if (vlrecebido > 0) {
                double vlsobra = vlrecebido - listParcela.getValorRestante();
                vlsobra = vlsobra < 0 ? 0 : vlsobra;

                double vlquitado = vlsobra - vlrecebido;
                vlquitado = vlquitado < 0 ? vlquitado * -1 : vlquitado;

                vlrecebido = vlsobra;

                Long parcela = listParcela.getCodigo();

                try {
                    parcelas.receber(parcela, vlquitado, 0.00, 0.00);
                } catch (Exception e) {
                    logger.error(e.getMessage(), e);
                    throw new RuntimeException("Ocorreu um erro ao realizar o recebimento, chame o suporte");
                }
            }

        }
    }

    public String remover(Long codigo) {
        Optional<Recebimento> recebimento = recebimentos.findById(codigo);

        if (recebimento.map(Recebimento::getDataProcessamento).isPresent())
            throw new RuntimeException("Esse recebimento não pode ser removido, pois ele já esta processado");

        try {
            recebimentos.deleteById(codigo);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new RuntimeException("Erro ao remover orçamento, chame o suporte");
        }

        return "removido com sucesso";
    }

}
