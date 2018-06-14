package net.originmobi.pdv.utilitarios;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.jdbc.datasource.DriverManagerDataSource;

public class ConexaoJDBC {
	private DataSource conexao;

	public DataSource abre() {
		
		try {
			conexao = new DriverManagerDataSource("jdbc:mysql://localhost/pdv", "root", "123456");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return conexao;
	}
	
	public void fecha() {
		try {
			conexao.getConnection().close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
