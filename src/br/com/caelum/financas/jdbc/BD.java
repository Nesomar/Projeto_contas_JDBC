package br.com.caelum.financas.jdbc;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BD {

	private Connection connection;
	
	private static final Logger Log = Logger.getLogger(BD.class.getName());

	public BD(Connection connection) {
		this.connection = connection;
	}

	public void geraTabelaContas() {

		try (Statement statement = this.connection.createStatement()) {
			String schema = " CREATE TABLE Conta ( id SERIAL NOT NULL, titular VARCHAR(256), numero VARCHAR(256), "
					+ "banco VARCHAR(256), agencia VARCHAR(256),  PRIMARY KEY(id))";
			statement.execute(schema);
		} catch (SQLException e) {
			Log.log(Level.INFO, "Table Conta já existe", e);
		}

	}

}
