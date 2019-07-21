package com.br.neoestudo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Logger;

public class TestaConexao {

	public static void main(String[] args) {

		try (Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/loja_virtual",
				"postgres", "1234"); Statement statement = connection.createStatement()) {
			System.out.println("Abrindo a conexão com o banco.");
			ResultSet produtos = statement.executeQuery("SELECT * FROM Produto");

			while (produtos.next()) {
				System.out.println("Nome do Produto:" + produtos.getString("nome"));

			}
		} catch (SQLException e) {
			Logger.getLogger("Erro na conexão");
		}
	}
}
