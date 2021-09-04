package net.originmobi.pdv.xml.nfe;

import com.thoughtworks.xstream.converters.Converter;
import com.thoughtworks.xstream.converters.MarshallingContext;
import com.thoughtworks.xstream.converters.UnmarshallingContext;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import net.originmobi.pdv.model.NotaFiscal;
import net.originmobi.pdv.service.notafiscal.NotaFiscalService;
import org.apache.commons.lang.StringUtils;

import java.text.DecimalFormat;
import java.util.Random;

public class ConversorXmlNfe implements Converter {

	private String chaveNfeRetorno = "";

	@Override
	public boolean canConvert(Class type) {
		return type.equals(NotaFiscal.class);
	}

	@Override
	public void marshal(Object source, HierarchicalStreamWriter writer, MarshallingContext context) {
		NotaFiscal notaFiscal = (NotaFiscal) source;

		DecimalFormat formato = new DecimalFormat("#0.00");

		String ufEmissor = notaFiscal.getEmissor().getEndereco().getCidade().getEstado().getCodigoUF();
		String cnpjEmissor = notaFiscal.getEmissor().getCnpj().replaceAll("\\D", "");
		int tipoRegime = notaFiscal.getEmissor().getRegimeTributario().getTipoRegime();

		// gera cNF
		int codAleatorio = 10000000 + new Random().nextInt() * 89999999;

		// add zeros a esqueda na sequencia
		String serie = StringUtils.leftPad(String.valueOf(notaFiscal.getEmissor().getParametro().getSerieNfe()), 3,
				"0");
		// add zeros a esqueda no numero da nota
		String numeroNf = StringUtils.leftPad(String.valueOf(notaFiscal.getNumero()), 9, "0");

		String cNF = String.valueOf(codAleatorio);

		// cria chave acesso
		String chaveNfe = ufEmissor + "1805" + cnpjEmissor + "55" + serie + numeroNf + 1 + cNF;

		NotaFiscalService nfService = new NotaFiscalService();

		// gera digito verificador
		Integer cDV = nfService.geraDV(chaveNfe);

		chaveNfeRetorno = chaveNfe + cDV;

		writer.addAttribute("xmlns", "http://www.portalfiscal.inf.br/nfe");
		writer.addAttribute("versao", "3.10");

		writer.startNode("idLote");
		context.convertAnother(1);
		writer.endNode();

		writer.startNode("indSinc");
		context.convertAnother(0);
		writer.endNode();

		writer.startNode("NFe");

		writer.addAttribute("xmlns", "http://www.portalfiscal.inf.br/nfe");

		writer.startNode("infNFe");

		writer.addAttribute("Id", "NFe" + chaveNfe + cDV);
		writer.addAttribute("versao", "3.10");

		writer.startNode("ide");

		writer.startNode("cUF");
		context.convertAnother(ufEmissor);
		writer.endNode();

		writer.startNode("cNF");
		context.convertAnother(cNF);
		writer.endNode();

		writer.startNode("natOp");
		context.convertAnother(notaFiscal.getNaturezaOperacao());
		writer.endNode();

		writer.startNode("indPag");
		context.convertAnother(0);
		writer.endNode();

		writer.startNode("mod");
		context.convertAnother(notaFiscal.getModelo());
		writer.endNode();

		writer.startNode("serie");
		context.convertAnother(notaFiscal.getSerie());
		writer.endNode();

		writer.startNode("nNF");
		context.convertAnother(notaFiscal.getNumero());
		writer.endNode();

		writer.startNode("dhEmi");
		context.convertAnother("2018-05-03T08:10:00-04:00");
		writer.endNode();

		writer.startNode("dhSaiEnt");
		context.convertAnother("2018-05-03T08:10:00-04:00");
		writer.endNode();

		writer.startNode("tpNF");
		context.convertAnother(notaFiscal.getTipo().ordinal());
		writer.endNode();

		writer.startNode("idDest");
		context.convertAnother(1);
		writer.endNode();

		writer.startNode("cMunFG");
		context.convertAnother(notaFiscal.getEmissor().getEndereco().getCidade().getCodigoMunicipio());
		writer.endNode();

		writer.startNode("tpImp");
		context.convertAnother(1);
		writer.endNode();

		writer.startNode("tpEmis");
		context.convertAnother(1);
		writer.endNode();

		writer.startNode("cDV");
		context.convertAnother(cDV);
		writer.endNode();

		writer.startNode("tpAmb");
		context.convertAnother(notaFiscal.getTipoAmbiente());
		writer.endNode();

		writer.startNode("finNFe");
		context.convertAnother(notaFiscal.getFinalidade().getTipo());
		writer.endNode();

		writer.startNode("indFinal");
		context.convertAnother(0); // 0 -- normal, 1 -- consumidor final
		writer.endNode();

		writer.startNode("indPres");
		context.convertAnother(1);
		writer.endNode();

		writer.startNode("procEmi");
		context.convertAnother(0);
		writer.endNode();

		writer.startNode("verProc");
		context.convertAnother("0.0.1");
		writer.endNode();

		writer.endNode(); // fim nod ide

		writer.startNode("emit");

		writer.startNode("CNPJ");
		context.convertAnother(cnpjEmissor);
		writer.endNode();

		writer.startNode("xNome");
		context.convertAnother(notaFiscal.getEmissor().getNome());
		writer.endNode();

		writer.startNode("xFant");
		context.convertAnother(notaFiscal.getEmissor().getNomeFantasia());
		writer.endNode();

		writer.startNode("enderEmit");

		writer.startNode("xLgr");
		context.convertAnother(notaFiscal.getEmissor().getEndereco().getRua());
		writer.endNode();

		writer.startNode("nro");
		context.convertAnother(notaFiscal.getEmissor().getEndereco().getNumero());
		writer.endNode();

		writer.startNode("xCpl");
		context.convertAnother(notaFiscal.getEmissor().getEndereco().getReferencia());
		writer.endNode();

		writer.startNode("xBairro");
		context.convertAnother(notaFiscal.getEmissor().getEndereco().getBairro());
		writer.endNode();

		writer.startNode("cMun");
		context.convertAnother(notaFiscal.getEmissor().getEndereco().getCidade().getCodigoMunicipio());
		writer.endNode();

		writer.startNode("xMun");
		context.convertAnother(notaFiscal.getEmissor().getEndereco().getCidade().getNome());
		writer.endNode();

		writer.startNode("UF");
		context.convertAnother(notaFiscal.getEmissor().getEndereco().getCidade().getEstado().getSigla());
		writer.endNode();

		writer.startNode("CEP");
		context.convertAnother(notaFiscal.getEmissor().getEndereco().getCep());
		writer.endNode();

		writer.startNode("cPais");
		context.convertAnother(
				notaFiscal.getEmissor().getEndereco().getCidade().getEstado().getPais().getCodigoPais());
		writer.endNode();

		writer.startNode("xPais");
		context.convertAnother(notaFiscal.getEmissor().getEndereco().getCidade().getEstado().getPais().getNome());
		writer.endNode();

		writer.endNode(); // fim not <enderEmit>

		writer.startNode("IE");
		context.convertAnother(notaFiscal.getEmissor().getIe());
		writer.endNode();

		writer.startNode("CRT");
		context.convertAnother(notaFiscal.getEmissor().getRegimeTributario().getTipoRegime());
		writer.endNode();

		writer.endNode(); // fim nod <emit>

		writer.startNode("dest");

		writer.startNode("CPF");
		context.convertAnother(notaFiscal.getDestinatario().getCpfcnpj().replaceAll("\\D", ""));
		writer.endNode();

		writer.startNode("xNome");
		if (notaFiscal.getEmissor().getParametro().getAmbiente() == 2)
			context.convertAnother("NF-E EMITIDA EM AMBIENTE DE HOMOLOGACAO - SEM VALOR FISCAL");
		else
			context.convertAnother(notaFiscal.getDestinatario().getNome());
		writer.endNode();

		writer.startNode("enderDest");

		writer.startNode("xLgr");
		context.convertAnother(notaFiscal.getDestinatario().getEndereco().getRua());
		writer.endNode();

		writer.startNode("nro");
		context.convertAnother(notaFiscal.getDestinatario().getEndereco().getNumero());
		writer.endNode();

		writer.startNode("xCpl");
		context.convertAnother(notaFiscal.getDestinatario().getEndereco().getReferencia());
		writer.endNode();

		writer.startNode("xBairro");
		context.convertAnother(notaFiscal.getDestinatario().getEndereco().getBairro());
		writer.endNode();

		writer.startNode("cMun");
		context.convertAnother(notaFiscal.getDestinatario().getEndereco().getCidade().getCodigoMunicipio());
		writer.endNode();

		writer.startNode("xMun");
		context.convertAnother(notaFiscal.getDestinatario().getEndereco().getCidade().getNome());
		writer.endNode();

		writer.startNode("UF");
		context.convertAnother(notaFiscal.getDestinatario().getEndereco().getCidade().getEstado().getSigla());
		writer.endNode();

		writer.startNode("CEP");
		context.convertAnother(notaFiscal.getDestinatario().getEndereco().getCep());
		writer.endNode();

		writer.startNode("cPais");
		context.convertAnother(
				notaFiscal.getDestinatario().getEndereco().getCidade().getEstado().getPais().getCodigoPais());
		writer.endNode();

		writer.startNode("xPais");
		context.convertAnother(notaFiscal.getDestinatario().getEndereco().getCidade().getEstado().getPais().getNome());
		writer.endNode();

		writer.startNode("fone");
		context.convertAnother(notaFiscal.getDestinatario().getTelefone().get(0).getFone());
		writer.endNode();

		writer.endNode(); // fim node <enderDest>

		writer.startNode("indIEDest");
		context.convertAnother(2);
		writer.endNode();

		writer.endNode(); // fim nod <dest>

		for (int i = 0; i < notaFiscal.getItens().size(); i++) {
			writer.startNode("det");
			writer.addAttribute("nItem", String.valueOf(i + 1));

			writer.startNode("prod");

			writer.startNode("cProd");
			context.convertAnother(notaFiscal.getItens().get(i).getCodigo());
			writer.endNode();

			writer.startNode("cEAN");
			context.convertAnother("");
			writer.endNode();

			writer.startNode("xProd");
			context.convertAnother("teste " + (i + 1));
			writer.endNode();

			writer.startNode("NCM");
			context.convertAnother(21050010);
			writer.endNode();

			writer.startNode("CFOP");
			context.convertAnother(notaFiscal.getItens().get(i).getCfop());
			writer.endNode();

			writer.startNode("uCom");
			context.convertAnother(notaFiscal.getItens().get(i).getUnidadeTribu());
			writer.endNode();

			writer.startNode("qCom");
			context.convertAnother(notaFiscal.getItens().get(i).getQtd());
			writer.endNode();

			writer.startNode("vUnCom");
			context.convertAnother(notaFiscal.getItens().get(i).getVUniTribu());
			writer.endNode();

			writer.startNode("vProd");
			context.convertAnother(formato.format(notaFiscal.getItens().get(i).getVlTotal()).replace(",", "."));
			writer.endNode();

			writer.startNode("cEANTrib");
			context.convertAnother("");
			writer.endNode();

			writer.startNode("uTrib");
			context.convertAnother(notaFiscal.getItens().get(i).getUnidadeTribu());
			writer.endNode();

			writer.startNode("qTrib");
			context.convertAnother(notaFiscal.getItens().get(i).getQtdTribu());
			writer.endNode();

			writer.startNode("vUnTrib");
			context.convertAnother(notaFiscal.getItens().get(i).getVUniTribu());
			writer.endNode();

			writer.startNode("indTot");
			context.convertAnother(1);
			writer.endNode();

			writer.endNode(); // fim nod<prod>

			writer.startNode("imposto");

			writer.startNode("ICMS");

			DecimalFormat formato2 = new DecimalFormat("00");

			String modeloICMS = "";
			if (tipoRegime == 1)
				modeloICMS = "ICMSSN";
			else
				modeloICMS = "ICMS";

			writer.startNode(modeloICMS + formato2.format(notaFiscal.getItens().get(i).getImpostos().getCst()));

			writer.startNode("orig");
			context.convertAnother(notaFiscal.getItens().get(i).getImpostos().getOrig());
			writer.endNode();

			if (tipoRegime == 1) {
				writer.startNode("CSOSN");
				context.convertAnother(notaFiscal.getItens().get(i).getImpostos().getCst());
				writer.endNode();

				writer.startNode("pCredSN");
				context.convertAnother(2);
				writer.endNode();

				writer.startNode("vCredICMSSN");
				context.convertAnother((notaFiscal.getItens().get(i).getVlTotal() * 2) / 100);
				writer.endNode();
			} else {
				writer.startNode("CST");
				context.convertAnother(formato2.format(notaFiscal.getItens().get(i).getImpostos().getCst()));
				writer.endNode();

				writer.startNode("modBC");
				context.convertAnother(notaFiscal.getItens().get(i).getImpostos().getModBc());
				writer.endNode();

				writer.startNode("vBC");
				context.convertAnother(
						formato.format(notaFiscal.getItens().get(i).getImpostos().getVBc()).replace(",", "."));
				writer.endNode();

				writer.startNode("pICMS");
				context.convertAnother(
						formato.format(notaFiscal.getItens().get(i).getImpostos().getPIcms()).replace(",", "."));
				writer.endNode();

				writer.startNode("vICMS");
				context.convertAnother(
						formato.format(notaFiscal.getItens().get(i).getImpostos().getVIcms()).replace(",", "."));
				writer.endNode();
			}

			writer.endNode(); // fim nod ICMS + CST

			writer.endNode(); // fim nod ICMS

			writer.startNode("PIS");

			writer.startNode("PISAliq");

			writer.startNode("CST");
			context.convertAnother(formato2.format(notaFiscal.getItens().get(i).getImpostos().getCstPis()));
			writer.endNode();

			writer.startNode("vBC");
			context.convertAnother(
					formato.format(notaFiscal.getItens().get(i).getImpostos().getVbcPis()).replace(",", "."));
			writer.endNode();

			writer.startNode("pPIS");
			context.convertAnother(
					formato.format(notaFiscal.getItens().get(i).getImpostos().getPPis()).replace(",", "."));
			writer.endNode();

			writer.startNode("vPIS");
			context.convertAnother(
					formato.format(notaFiscal.getItens().get(i).getImpostos().getVPis()).replace(",", "."));
			writer.endNode();

			writer.endNode(); // fim nod PISAliq

			writer.endNode(); // fim nod PIS

			writer.startNode("COFINS");

			writer.startNode("COFINSAliq");

			writer.startNode("CST");
			context.convertAnother(formato2.format(notaFiscal.getItens().get(i).getImpostos().getCstCofins()));
			writer.endNode();

			writer.startNode("vBC");
			context.convertAnother(
					formato.format(notaFiscal.getItens().get(i).getImpostos().getVbcCofins()).replace(",", "."));
			writer.endNode();

			writer.startNode("pCOFINS");
			context.convertAnother(
					formato.format(notaFiscal.getItens().get(i).getImpostos().getPCofins()).replace(",", "."));
			writer.endNode();

			writer.startNode("vCOFINS");
			context.convertAnother(
					formato.format(notaFiscal.getItens().get(i).getImpostos().getVCofins()).replace(",", "."));
			writer.endNode();

			writer.endNode(); // fim nod COFINS

			writer.endNode(); // fim nod COFINSAliq

			writer.endNode(); // fim nod imposto

			writer.endNode(); // fim nod <det>
		} // fim for dos itens

		writer.startNode("total");

		writer.startNode("ICMSTot");

		String vBc = formato.format(notaFiscal.getTotais().getVBc()).replace(",", ".");

		if (tipoRegime == 1)
			vBc = "0.00";

		writer.startNode("vBC");
		context.convertAnother(vBc);
		writer.endNode();

		writer.startNode("vICMS");
		context.convertAnother(formato.format(notaFiscal.getTotais().getVIcms()).replace(",", "."));
		writer.endNode();

		writer.startNode("vICMSDeson");
		context.convertAnother("0.00");
		writer.endNode();

		writer.startNode("vBCST");
		context.convertAnother("0.00");
		writer.endNode();

		writer.startNode("vST");
		context.convertAnother("0.00");
		writer.endNode();

		writer.startNode("vProd");
		context.convertAnother(formato.format(notaFiscal.getTotais().getVProd()).replace(",", "."));
		writer.endNode();

		writer.startNode("vFrete");
		context.convertAnother(formato.format(notaFiscal.getTotais().getVFrete()).replace(",", "."));
		writer.endNode();

		writer.startNode("vSeg");
		context.convertAnother(formato.format(notaFiscal.getTotais().getVSeg()).replace(",", "."));
		writer.endNode();

		writer.startNode("vDesc");
		context.convertAnother(formato.format(notaFiscal.getTotais().getVDesc()).replace(",", "."));
		writer.endNode();

		writer.startNode("vII");
		context.convertAnother(formato.format(notaFiscal.getTotais().getVIi()).replace(",", "."));
		writer.endNode();

		writer.startNode("vIPI");
		context.convertAnother(formato.format(notaFiscal.getTotais().getVIpi()).replace(",", "."));
		writer.endNode();

		writer.startNode("vPIS");
		context.convertAnother(formato.format(notaFiscal.getTotais().getVPis()).replace(",", "."));
		writer.endNode();

		writer.startNode("vCOFINS");
		context.convertAnother(formato.format(notaFiscal.getTotais().getVCofins()).replace(",", "."));
		writer.endNode();

		writer.startNode("vOutro");
		context.convertAnother(formato.format(notaFiscal.getTotais().getVOutros()).replace(",", "."));
		writer.endNode();

		writer.startNode("vNF");
		context.convertAnother(formato.format(notaFiscal.getTotais().getVNf()).replace(",", "."));
		writer.endNode();

		writer.endNode(); // fim nod ICMSTot

		writer.endNode(); // fim nod total

		writer.startNode("transp");

		writer.startNode("modFrete");
		context.convertAnother(notaFiscal.getFreteTipo().getTipo());
		writer.endNode();

		writer.endNode(); // fim nod transp

		writer.endNode(); // fim nod <infNFe>

		writer.endNode(); // fim nod NFe

	}

	public String retornaChaveNfe() {
		return chaveNfeRetorno;
	}

	@Override
	public Object unmarshal(HierarchicalStreamReader reader, UnmarshallingContext context) {
		return null;
	}

}
