package net.originmobi.pdv.service;

import net.originmobi.pdv.enumerado.EntradaSaida;
import net.originmobi.pdv.model.TributacaoRegra;
import net.originmobi.pdv.repository.TributacaoRegraRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class TributacaoRegraService {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private TributacaoRegraRepository regras;

    public List<TributacaoRegra> lista() {
        return regras.findAll();
    }

    public List<TributacaoRegra> lista(Long codtributacao, EntradaSaida tipo) {
        return regras.findByTributacaoCodigoAndTipoEquals(codtributacao, tipo);
    }

    public String merger(Long codregra, Long codtribu, String tipo, String uf, String cfop, String cstCsosn,
                         String cstpis, String cstcofins, String pis, String cofins, String aliqIpi, String aliqIcms,
                         String cstIpi) {
        LocalDate dataAtual = LocalDate.now();

        if (codregra == 0) {
            try {
                regras.cadastrar(
                        codtribu, tipo, uf, cfop, cstCsosn, cstpis, cstcofins, pis, cofins, aliqIpi, aliqIcms,
                        cstIpi, Date.valueOf(dataAtual)
                );
            } catch (Exception e) {
                logger.error(e.getMessage(), e);
                return "Erro ao tentar adicionar regra, chame o suporte";
            }
        } else {
            try {
                regras.update(
                        codregra, codtribu, tipo, uf, cfop, cstCsosn, cstpis, cstcofins, pis, cofins, aliqIpi,
                        aliqIcms, cstIpi
                );
            } catch (Exception e) {
                logger.error(e.getMessage(), e);
                return "Erro ao tentar alterar regra, chame o suporte";
            }

            return "Regra alterada com sucesso";
        }

        return "Regra adicionada com sucesso";
    }

    public String remover(Long codigo) {
        try {
            regras.deleteById(codigo);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new RuntimeException("Erro ao tentar remover a regra, chame o suporte");
        }

        return "Regra removida com sucesso";
    }

    public TributacaoRegra busca(Long codigo) {

        Optional<TributacaoRegra> tributacaoRegraOptional = regras.findById(codigo);

        if (!tributacaoRegraOptional.isPresent()) {
            throw new RuntimeException("Regra de Tributação não encontrada");
        }

        return tributacaoRegraOptional.get();
    }

}
