package net.originmobi.pdv.xml.nfe;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.crypto.dsig.*;
import javax.xml.crypto.dsig.dom.DOMSignContext;
import javax.xml.crypto.dsig.keyinfo.KeyInfo;
import javax.xml.crypto.dsig.keyinfo.KeyInfoFactory;
import javax.xml.crypto.dsig.keyinfo.X509Data;
import javax.xml.crypto.dsig.spec.C14NMethodParameterSpec;
import javax.xml.crypto.dsig.spec.TransformParameterSpec;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;
import java.security.InvalidAlgorithmParameterException;
import java.security.KeyStore;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;

public class AssinaXML {
	private static final String NFE = "NFe";

	private PrivateKey privateKey;
	private KeyInfo keyInfo;

	private final Logger logger = LoggerFactory.getLogger(getClass());

	public String assinaXML(String xml) {
		String path = "";

		try {
			path = new File(".").getCanonicalPath();
		} catch (Exception e) {
			logger.error(e.getMessage());
		}

		String caminhoCertificado = path + "/" + "src/main/resources/certificado/certificado.pfx";
		String senhaCertificado = "spcbrasil";

		String xmlAssinado = "";
		try {
			xmlAssinado = assinarEnviNFe(xml, caminhoCertificado, senhaCertificado);
			logger.info(xmlAssinado);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		}

		return xmlAssinado;
	}

	private String assinarEnviNFe(String xmlEnviNFe, String caminhoCertificado, String senhaCertificado)
			throws Exception {
		Document document = documentFactory(xmlEnviNFe);
		XMLSignatureFactory signatureFactory = XMLSignatureFactory.getInstance("DOM");
		ArrayList<Transform> transformList = signatureFactory(signatureFactory);
		loadCertificates(caminhoCertificado, senhaCertificado, signatureFactory);

		for (int i = 0; i < document.getDocumentElement().getElementsByTagName(NFE).getLength(); i++) {
			assinarNFe(signatureFactory, transformList, privateKey, keyInfo, document, i);
		}

		return outputXML(document);
	}

	private Document documentFactory(String xml) throws SAXException, IOException, ParserConfigurationException {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		factory.setAttribute(XMLConstants.ACCESS_EXTERNAL_DTD, "");
		factory.setAttribute(XMLConstants.ACCESS_EXTERNAL_SCHEMA, "");
		factory.setNamespaceAware(true);
		return factory.newDocumentBuilder().parse(new ByteArrayInputStream(xml.getBytes()));
	}

	private ArrayList<Transform> signatureFactory(XMLSignatureFactory signatureFactory)
			throws NoSuchAlgorithmException, InvalidAlgorithmParameterException {
		ArrayList<Transform> transformList = new ArrayList<>();
		TransformParameterSpec tps = null;
		Transform envelopedTransform = signatureFactory.newTransform(Transform.ENVELOPED, tps);
		Transform c14NTransform = signatureFactory.newTransform("http://www.w3.org/TR/2001/REC-xml-c14n-20010315", tps);

		transformList.add(envelopedTransform);
		transformList.add(c14NTransform);
		return transformList;
	}

	private void loadCertificates(String certificado, String senha, XMLSignatureFactory signatureFactory)
			throws Exception {

		KeyStore ks = KeyStore.getInstance("pkcs12");
		try (InputStream entrada = new FileInputStream(certificado);) {
			ks.load(entrada, senha.toCharArray());
		} catch (IOException e) {
			throw new Exception("Senha do Certificado Digital incorreta ou Certificado inválido.");
		}

		KeyStore.PrivateKeyEntry pkEntry = null;
		Enumeration<String> aliasesEnum = ks.aliases();
		while (aliasesEnum.hasMoreElements()) {
			String alias = aliasesEnum.nextElement();
			if (ks.isKeyEntry(alias)) {
				pkEntry = (KeyStore.PrivateKeyEntry) ks.getEntry(alias,
						new KeyStore.PasswordProtection(senha.toCharArray()));
				privateKey = pkEntry.getPrivateKey();
				break;
			}
		}

		X509Certificate cert = pkEntry != null ? (X509Certificate) pkEntry.getCertificate() : null;
		if (cert != null) {
			logger.info("Data Certificado {}", cert.getNotAfter());
		}

		KeyInfoFactory keyInfoFactory = signatureFactory.getKeyInfoFactory();
		List<X509Certificate> x509Content = new ArrayList<>();

		x509Content.add(cert);
		X509Data x509Data = keyInfoFactory.newX509Data(x509Content);
		keyInfo = keyInfoFactory.newKeyInfo(Collections.singletonList(x509Data));
	}

	private void assinarNFe(XMLSignatureFactory fac, ArrayList<Transform> transformList, PrivateKey privateKey,
			KeyInfo ki, Document document, int indexNFe) throws Exception {

		NodeList elements = document.getElementsByTagName("infNFe");
		org.w3c.dom.Element el = (org.w3c.dom.Element) elements.item(indexNFe);
		String id = el.getAttribute("Id");
		el.setIdAttribute("Id", true);

		Reference ref = fac.newReference("#" + id, fac.newDigestMethod(DigestMethod.SHA1, null), transformList, null,
				null);

		SignedInfo si = fac.newSignedInfo(
				fac.newCanonicalizationMethod(CanonicalizationMethod.INCLUSIVE, (C14NMethodParameterSpec) null),
				fac.newSignatureMethod(SignatureMethod.RSA_SHA1, null), Collections.singletonList(ref));

		XMLSignature signature = fac.newXMLSignature(si, ki);

		DOMSignContext dsc = new DOMSignContext(privateKey,
				document.getDocumentElement().getElementsByTagName(NFE).item(indexNFe));
		signature.sign(dsc);
	}

	private String outputXML(Document doc) throws TransformerException {
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		TransformerFactory tf = TransformerFactory.newInstance();
		tf.setAttribute(XMLConstants.ACCESS_EXTERNAL_DTD, "");
		tf.setAttribute(XMLConstants.ACCESS_EXTERNAL_STYLESHEET, "");

		Transformer trans = tf.newTransformer();
		trans.transform(new DOMSource(doc), new StreamResult(os));
		String xml = os.toString();
		if ((xml != null) && (!"".equals(xml))) {
			xml = xml.replace("\\r\\n", "");
			xml = xml.replace(" standalone=\"no\"", "");
		}
		return xml;
	}

}
