package net.originmobi.pdv.service.notafiscal;

import net.originmobi.pdv.model.Cst;
import net.originmobi.pdv.model.NotaFiscalItemImposto;
import net.originmobi.pdv.model.TributacaoRegra;
import net.originmobi.pdv.repository.notafiscal.NotaFiscalItemImpostoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NotaFiscalItemImpostoService {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private NotaFiscalItemImpostoRepository impostos;

    public void cadastro(NotaFiscalItemImposto imposto) {
        impostos.save(imposto);
    }

    public NotaFiscalItemImposto merger(Long codimposto, char origin, String cstCofins, int modBcIcms, Double bcIcms,
                                        Double aliqIcms, Double vlIcms, String cstPis, Double bcPis, Double pis,
                                        Double vlPis, Double bcCofins, Double aliqCofins, Double vlCofins, int cst,
                                        int cstIpi, Double vbcIpi, Double pIpi, Double vIpi) {

        String x = Character.toString(origin);
        Integer orig = Integer.parseInt(x);

        int vlCstCofins = Integer.parseInt(cstCofins);
        int vlCstPis = Integer.parseInt(cstPis);

        NotaFiscalItemImposto imposto = new NotaFiscalItemImposto(orig, vlCstCofins, modBcIcms, bcIcms, aliqIcms,
                vlIcms, vlCstPis, bcPis, pis, vlPis, bcCofins, aliqCofins, vlCofins, cst, cstIpi, vbcIpi, pIpi,
                vIpi);

        // se o código for diferente de null, se trata de uma atualização
        if (codimposto != null)
            imposto.setCodigo(codimposto);

        try {
            impostos.save(imposto);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new RuntimeException("Erro ao lançar impostos na nota, chame o suporte");
        }

        return imposto;
    }

    public NotaFiscalItemImposto calcula(Long codimposto, Double vlTotal, TributacaoRegra regra, char origin,
                                         int modBcIcms) {
        int cstCsosn = Integer.parseInt(regra.getCstCsosn().getCodCstCsosn());
        Cst cstCofins = regra.getCstCofins();
        String cstPis = regra.getCstPis().getCodCst();

        Double aliqIcms = regra.getAliqIcms();
        Double vlIcms = (vlTotal * aliqIcms) / 100;

        Double pis = regra.getPis();
        Double vlPis = (vlTotal * pis) / 100;

        Double aliqCofins = regra.getCofins();
        Double vlCofins = (vlTotal * aliqCofins) / 100;

        int cstIpi = Integer.parseInt(regra.getCstIpi().getCst());
        Double aliqIPI = regra.getAliqIpi();
        Double vlIPI = (vlTotal * aliqIPI) / 100;

        // cadastra os impostos do produto
        NotaFiscalItemImposto imposto = null;
        try {
            imposto = merger(codimposto, origin, cstCofins.getCodCst(), modBcIcms, vlTotal, aliqIcms, vlIcms, cstPis,
                    vlTotal, pis, vlPis, vlTotal, aliqCofins, vlCofins, cstCsosn, cstIpi, vlTotal, aliqIPI, vlIPI);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new RuntimeException("erro ao calcular os impostos da nota");
        }

        return imposto;
    }

}
