package net.originmobi.pdv.service.cartao;

import net.originmobi.pdv.enumerado.TituloTipo;
import net.originmobi.pdv.enumerado.caixa.EstiloLancamento;
import net.originmobi.pdv.enumerado.caixa.TipoLancamento;
import net.originmobi.pdv.enumerado.cartao.CartaoSituacao;
import net.originmobi.pdv.enumerado.cartao.CartaoTipo;
import net.originmobi.pdv.filter.CartaoFilter;
import net.originmobi.pdv.model.Caixa;
import net.originmobi.pdv.model.CaixaLancamento;
import net.originmobi.pdv.model.Titulo;
import net.originmobi.pdv.model.Usuario;
import net.originmobi.pdv.model.cartao.CartaoLancamento;
import net.originmobi.pdv.model.cartao.MaquinaCartao;
import net.originmobi.pdv.repository.cartao.CartaoLancamentoRepository;
import net.originmobi.pdv.service.CaixaLancamentoService;
import net.originmobi.pdv.service.UsuarioService;
import net.originmobi.pdv.singleton.Aplicacao;
import net.originmobi.pdv.utilitarios.DataAtual;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@Service
public class CartaoLancamentoService {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private CartaoLancamentoRepository repository;

    @Autowired
    private CaixaLancamentoService caixaLancamentos;

    @Autowired
    private UsuarioService usuarios;

    public void lancamento(Double vlParcela, Titulo titulo) {
        Double taxa = 0.0;
        Double vlTaxa;
        double vlLiqParcela;

        Double taxaAnte;
        Double vlTaxaAnte;
        double vlLiqAnt;

        CartaoTipo tipo = null;
        int dias = 0;

        // verifica se é debito ou crédito e pega os valores corretos do titulo
        if (titulo.getTipo().getSigla().equals(TituloTipo.CARTDEB.toString())) {
            taxa = titulo.getMaquina().getTaxaDebito();
            dias = titulo.getMaquina().getDiasDebito();
            tipo = CartaoTipo.DEBITO;

        } else if (titulo.getTipo().getSigla().equals(TituloTipo.CARTCRED.toString())) {
            taxa = titulo.getMaquina().getTaxaCredito();
            dias = titulo.getMaquina().getDiasCredito();
            tipo = CartaoTipo.CREDITO;
        }

        vlTaxa = (vlParcela * taxa) / 100;
        vlLiqParcela = vlParcela - vlTaxa;

        taxaAnte = titulo.getMaquina().getTaxaAntecipacao();
        vlTaxaAnte = (vlParcela * taxaAnte) / 100;
        vlLiqAnt = vlParcela - vlTaxaAnte;

        MaquinaCartao maquinaCartao = titulo.getMaquina();

        DataAtual data = new DataAtual();
        LocalDate dataAtual = LocalDate.now();
        String dataRecebimento = data.dataAtualIncrementa(dias);

        CartaoLancamento lancamento = new CartaoLancamento(vlParcela, taxa, vlTaxa, vlLiqParcela, taxaAnte,
                vlTaxaAnte, vlLiqAnt, maquinaCartao, tipo, CartaoSituacao.APROCESSAR,
                Date.valueOf(dataRecebimento), Date.valueOf(dataAtual));

        try {
            repository.save(lancamento);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }

    }

    public List<CartaoLancamento> listar(CartaoFilter filter) {
        String situacao = filter.getSituacao() == null ? "%" : filter.getSituacao().toString();
        String tipo = filter.getTipo() == null ? "%" : filter.getTipo().toString();
        String dataRecebimento = filter.getDataRecebimento() == null || filter.getDataRecebimento().isEmpty() ? "%"
                : filter.getDataRecebimento().replace("/", "-");
        return repository.buscaLancamentos(situacao, tipo, dataRecebimento);
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public String processar(CartaoLancamento cartaoLancamento) {

        if (cartaoLancamento.getSituacao().equals(CartaoSituacao.PROCESSADO))
            throw new RuntimeException("Registro já processado");

        if (cartaoLancamento.getSituacao().equals(CartaoSituacao.ANTECIPADO))
            throw new RuntimeException("Registro já foi antecipado");

        Double valor = cartaoLancamento.getVlLiqParcela();
        TipoLancamento tipo = TipoLancamento.RECEBIMENTO;
        EstiloLancamento estilo = EstiloLancamento.ENTRADA;
        Caixa banco = cartaoLancamento.getMaquinaCartao().getBanco();

        Aplicacao aplicacao = Aplicacao.getInstancia();
        Usuario usuario = usuarios.buscaUsuario(aplicacao.getUsuarioAtual());

        CaixaLancamento lancamento = new CaixaLancamento("Referênte a processamento de cartão", valor, tipo, estilo,
                banco, usuario);

        try {
            caixaLancamentos.lancamento(lancamento);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao tentar realizar o processamento, chame o suporte");
        }

        try {
            cartaoLancamento.setSituacao(CartaoSituacao.PROCESSADO);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao tentar realizar o processamento, chame o suporte");
        }

        return "Processamento realizado com sucesso";
    }

    public String antecipar(CartaoLancamento cartaoLancamento) {
        if (cartaoLancamento.getSituacao().equals(CartaoSituacao.PROCESSADO))
            throw new RuntimeException("Registro já processado");

        if (cartaoLancamento.getSituacao().equals(CartaoSituacao.ANTECIPADO))
            throw new RuntimeException("Registro já foi antecipado");

        Double valor = cartaoLancamento.getVlLiqAntecipacao();
        TipoLancamento tipo = TipoLancamento.RECEBIMENTO;
        EstiloLancamento estilo = EstiloLancamento.ENTRADA;
        Caixa banco = cartaoLancamento.getMaquinaCartao().getBanco();

        Aplicacao aplicacao = Aplicacao.getInstancia();
        Usuario usuario = usuarios.buscaUsuario(aplicacao.getUsuarioAtual());

        CaixaLancamento lancamento = new CaixaLancamento(
                "Referênte a antecipação de cartão código " + cartaoLancamento.getCodigo(), valor, tipo, estilo, banco,
                usuario);

        try {
            caixaLancamentos.lancamento(lancamento);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao tentar realizar a antecipação, chame o suporte");
        }

        try {
            cartaoLancamento.setSituacao(CartaoSituacao.ANTECIPADO);
            repository.save(cartaoLancamento);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao tentar realizar a antecipação, chame o suporte");
        }

        return "Antecipação realizada com sucesso";
    }

}
