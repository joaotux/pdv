package net.originmobi.pdv.relatorios;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import net.originmobi.pdv.utilitarios.ConexaoJDBC;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;

public class GerarRelatorio {
	private String contexto;
	private ConexaoJDBC conexao;

	public void gerar(String relatorio, HttpServletResponse resposta, Map<String, Object> parametros) {

		try {
			contexto = new File(".").getCanonicalPath();
		} catch (IOException e1) {
			e1.printStackTrace();
			System.out.println("Erro ao pegar path da aplicação");
		}

		String jrxml = contexto.toString().replace("/bin", "") + "/webapps/pdv/WEB-INF/classes/relatorios/" + relatorio;
		
		conexao = new ConexaoJDBC();
		DataSource dataSource = conexao.abre();

		JasperReport report;
		JasperPrint print = null;

		try {
			report = JasperCompileManager.compileReport(jrxml);

			print = JasperFillManager.fillReport(report, parametros, dataSource.getConnection());

			dataSource.getConnection().close();
		} catch (Exception e) {
			System.out.println(e);
		}
		
		try {
			OutputStream saida = resposta.getOutputStream();
			
			resposta.setContentType("application/pdf");
			
			JasperExportManager.exportReportToPdfStream(print, saida);
			
			saida.flush();
			saida.close();
		} catch (Exception e) {
			System.out.println(e);
		}

	}
}
