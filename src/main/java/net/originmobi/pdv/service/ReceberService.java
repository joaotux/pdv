package net.originmobi.pdv.service;

import net.originmobi.pdv.model.Receber;
import net.originmobi.pdv.repository.ReceberRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

@Service
public class ReceberService {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private ReceberRepository receberRepo;

    public Receber cadastrar(Receber receber) {
        try {
            return receberRepo.save(receber);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new RuntimeException();
        }
    }

    public void lancaReceber(String observacao, Double valorTotal, Double valorRecebido, Double valorDesconto,
                             Double valorAcrescimo, Double valorRestante, int quitado, int sequencia,
                             Timestamp dataCadastro, String dataVencimento, Long pessoaCodigo) {
        receberRepo.lancaReceber(observacao, valorTotal, valorRecebido, valorDesconto, valorAcrescimo,
                valorRestante, quitado, sequencia, dataCadastro, dataVencimento, pessoaCodigo);
    }

    public String totalAReceber() {
        return receberRepo.totalAReceber();
    }

}
