package br.com.caelum.financas.jdbc;

import java.sql.Connection;
import java.util.List;
import java.util.logging.Logger;

import br.com.caelum.financas.modelo.Conta;

public class TesteJDBCPostgreSQL {

	public static void main(String[] args) {
		
		try (Connection connection = new ConnectionFactory().getConnectionPostgreSQL()){
			
			Conta conta = new Conta();
			conta.setTitular("Joao Ferreira");
			conta.setBanco("Itau");
			conta.setAgencia("0063");
			conta.setNumero("432561");
			
			new BD(connection).geraTabelaContas();

			connection.setAutoCommit(false);

			ContaDAO dao = new ContaDAO(connection);
			dao.adiciona(conta);

			List<Conta> contas = dao.lista();

			for (Conta c : contas) {
				System.out.println(c.getTitular());
			}

			connection.commit();
		} catch (Exception e) {
			// TODO: handle exception
			Logger.getLogger("erro");
		}
	}
}
