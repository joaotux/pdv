package net.originmobi.pdv.service;

import net.originmobi.pdv.model.Caixa;
import net.originmobi.pdv.model.Transferencia;
import net.originmobi.pdv.model.Usuario;
import net.originmobi.pdv.repository.TransferenciaRepository;
import net.originmobi.pdv.singleton.Aplicacao;
import net.originmobi.pdv.utilitarios.DataAtual;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TransferenciaService {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private TransferenciaRepository transferencias;

    @Autowired
    private UsuarioService usuarios;

    @Autowired
    private CaixaService caixas;

    public String cadastrar(Double valor, Long origem, Long destino) {
        Aplicacao aplicacao = Aplicacao.getInstancia();
        DataAtual dataAtual = new DataAtual();

        Usuario usuario = usuarios.buscaUsuario(aplicacao.getUsuarioAtual());

        Optional<Caixa> caixaOrigemOptional = caixas.busca(origem);
        Optional<Caixa> caixaDestinoOptional = caixas.busca(destino);

        if (caixaOrigemOptional.equals(caixaDestinoOptional))
            throw new RuntimeException("Destino é inválido");

        if (!caixaOrigemOptional.isPresent() || caixaOrigemOptional.map(Caixa::getDataFechamento).isPresent())
            throw new RuntimeException("Conta origem não esta aberta, verifique");

        if (!caixaDestinoOptional.isPresent() || caixaDestinoOptional.map(Caixa::getDataFechamento).isPresent())
            throw new RuntimeException("Conta destino não esta aberta, verifique");

        Caixa caixaOrigem = caixaOrigemOptional.get();
        Caixa caixaDestino = caixaDestinoOptional.get();

        if (caixaOrigem.getValorTotal() < valor)
            throw new RuntimeException("Saldo insuficiente para realizar a transferência");

        Transferencia transferencia = new Transferencia(valor, dataAtual.dataAtualTimeStamp(), caixaOrigem,
                caixaDestino, usuario, "Transferencia para o " + caixaDestino.getDescricao() + " "
                + caixaDestino.getCodigo());

        try {
            transferencias.save(transferencia);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new RuntimeException("Erro ao realizar a transferencia, chame o suporte");
        }

        return "Transferência realizada com sucesso";
    }

}
