package com.br.neoestudo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TestaConexao {

	public static void main(String[] args) throws SQLException {
		
		Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/loja_virtual", "postgres", "1234");
		System.out.println("Abrindo a conexão com o banco.");
		
		Statement statement = connection.createStatement();
		ResultSet produtos = statement.executeQuery("SELECT * FROM Produto");
		
		while (produtos.next()) {
			System.out.println("Nome do Produto:" + produtos.getString("nome"));
			
		}
		connection.close();
	}
}
