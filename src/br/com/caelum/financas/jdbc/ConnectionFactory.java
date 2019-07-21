package br.com.caelum.financas.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConnectionFactory {

	private static final Logger Log = Logger.getLogger(ConnectionFactory.class.getName());
	
	public Connection getConnectionMySQL() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			return DriverManager.getConnection("jdbc:mysql://localhost/contas",
					"root", "");
		} catch (SQLException e) {
			Log.log(Level.SEVERE, "Erro ao conectar com o mysql", e);
			throw new RuntimeException(e);
		} catch (ClassNotFoundException e) {
			Log.log(Level.SEVERE, "Drive JDBC mysql não localizado", e);
			throw new RuntimeException(e);
		}
	}

	public Connection getConnectionHSQLDB() {
		try {
			Class.forName("org.hsqldb.jdbcDriver");
			return DriverManager.getConnection("jdbc:hsqldb:contas", "sa", "");
		} catch (SQLException e) {
			Log.log(Level.SEVERE, "Erro ao conectar com o hsqldb", e);
			throw new RuntimeException(e);
		} catch (ClassNotFoundException e) {
			Log.log(Level.SEVERE, "Drive JDBC hsqldb não localizado", e);
			throw new RuntimeException(e);
		}

	}
	
	public Connection getConnectionPostgreSQL() {
		try {
			Class.forName("org.postgresql.Driver");
			return DriverManager.getConnection("jdbc:postgresql://localhost:5432/contas", "postgres", "1234");
		} catch (SQLException e) {
			Log.log(Level.SEVERE, "Erro ao conectar com o postgresql", e);
			throw new RuntimeException(e);
		} catch (ClassNotFoundException e) {
			Log.log(Level.SEVERE, "Drive JDBC postgres não localizado", e);
			throw new RuntimeException(e);
		}

	}

}
