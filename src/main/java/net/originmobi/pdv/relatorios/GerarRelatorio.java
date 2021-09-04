package net.originmobi.pdv.relatorios;

import net.originmobi.pdv.utilitarios.ConexaoJDBC;
import net.sf.jasperreports.engine.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Map;

public class GerarRelatorio {
	private String contexto;

	private final Logger logger = LoggerFactory.getLogger(getClass());

	public void gerar(String relatorio, HttpServletResponse resposta, Map<String, Object> parametros) {

		try {
			contexto = new File(".").getCanonicalPath();
		} catch (IOException e1) {
			e1.printStackTrace();
			logger.error("Erro ao pegar path da aplicação");
		}

		String jrxml = contexto.replace("/bin", "") + "/webapps/pdv/WEB-INF/classes/relatorios/" + relatorio;

		ConexaoJDBC conexao = new ConexaoJDBC();
		DataSource dataSource = conexao.abre();

		JasperReport report;
		JasperPrint print = null;

		try {
			report = JasperCompileManager.compileReport(jrxml);

			print = JasperFillManager.fillReport(report, parametros, dataSource.getConnection());

			dataSource.getConnection().close();
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		
		try {
			OutputStream saida = resposta.getOutputStream();
			
			resposta.setContentType("application/pdf");
			
			JasperExportManager.exportReportToPdfStream(print, saida);
			
			saida.flush();
			saida.close();
		} catch (Exception e) {
			logger.error(e.getMessage());
		}

	}
}
