package net.originmobi.pdv.xml.nfe;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import net.originmobi.pdv.model.NotaFiscal;
import net.originmobi.pdv.service.notafiscal.NotaFiscalService;

@Component
public class GeraXmlNfe {
	
	@Autowired
	private NotaFiscalService nfServer;

	/*
	 * Recebe uma notafiscal e retorna a chave de acesso da mesma
	 */
	public String gerarXML(NotaFiscal notaFiscal) {
		XStream valor = new XStream(new DomDriver());
		ConversorXmlNfe conversor = new ConversorXmlNfe();
		AssinaXML assina = new AssinaXML();
		
		valor.registerConverter(conversor);
		
		valor.alias("enviNFe", NotaFiscal.class);
		
		String xml = valor.toXML(notaFiscal);
		
		xml = assina.assinaXML(xml);
		
		//pega a chave da nfe
		String chaveNfe = conversor.retornaChaveNfe();
		
		nfServer = new NotaFiscalService();
		
		if(notaFiscal.getChaveAcesso() != null) {
			nfServer.removeXml(notaFiscal.getChaveAcesso());
		}
		
		nfServer.salvaXML(xml, chaveNfe);
		
		return chaveNfe;
	}
}

