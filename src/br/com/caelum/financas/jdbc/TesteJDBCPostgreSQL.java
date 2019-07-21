package br.com.caelum.financas.jdbc;

import java.sql.Connection;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import br.com.caelum.financas.modelo.Conta;

public class TesteJDBCPostgreSQL {

	private static final Logger Log = Logger.getLogger(TesteJDBCPostgreSQL.class.getName());
	
	public static void main(String[] args) {
		
		try (Connection connection = new ConnectionFactory().getConnectionPostgreSQL()){
			
			Conta conta = new Conta();
			conta.setTitular("Beltrano Silva");
			conta.setBanco("Bradesco");
			conta.setAgencia("0063");
			conta.setNumero("432561");
			
			new BD(connection).geraTabelaContas();

			connection.setAutoCommit(false);

			ContaDAO dao = new ContaDAO(connection);
			dao.adicionar(conta);

			List<Conta> contas = dao.listar();

			for (Conta c : contas) {
				System.out.println(c.getTitular());
			}

			connection.commit();
		} catch (Exception e) {
			Log.log(Level.SEVERE, "Algo inesperado aconteceu.", e);
		}
	}
}
