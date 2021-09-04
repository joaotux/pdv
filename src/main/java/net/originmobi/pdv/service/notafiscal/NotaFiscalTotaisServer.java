package net.originmobi.pdv.service.notafiscal;

import net.originmobi.pdv.model.NotaFiscalTotais;
import net.originmobi.pdv.repository.notafiscal.NotaFiscalTotaisRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotaFiscalTotaisServer {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private NotaFiscalTotaisRepository totais;

    public List<NotaFiscalTotais> lista() {
        return totais.findAll();
    }

    public NotaFiscalTotais cadastro(NotaFiscalTotais total) {
        return totais.save(total);
    }

    public void atualiza(Long codNota, NotaFiscalTotais total) {
		logger.info("codnota {}", codNota);
		logger.info("totais {}", total.getCodigo());

        String[] array = totais.calcula(codNota).split(",");

        Double totalProdutos = Double.valueOf(array[0]);
        Double totalIcms = Double.valueOf(array[1]);
        Double totalPis = Double.valueOf(array[2]);
        Double totalCofins = Double.valueOf(array[3]);
        Double totalIpi = Double.valueOf(array[4]);
        Double totalNota = totalProdutos + totalIpi;

        total.setVBc(totalProdutos);
        total.setVProd(totalProdutos);
        total.setVIcms(totalIcms);
        total.setVPis(totalPis);
        total.setVCofins(totalCofins);
        total.setVIpi(totalIpi);
        total.setVNf(totalNota);

        try {
            totais.save(total);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new RuntimeException("Erro ao salvar item na nota, chame o suporte");
        }
    }

}
