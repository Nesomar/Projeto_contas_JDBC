package br.com.caelum.financas.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import br.com.caelum.financas.modelo.Conta;

public class ContaDAO {

	private Connection connection;
	
	private static final Logger Log = Logger.getLogger(ContaDAO.class.getName());

	public ContaDAO(Connection connection) {
		this.connection = connection;
	}

	public void adicionar(Conta conta) {

		try {
			PreparedStatement ps = this.connection
					.prepareStatement("insert into Conta (titular, banco, agencia, numero) values (?,?,?,?)");
			ps.setString(1, conta.getTitular());
			ps.setString(2, conta.getBanco());
			ps.setString(3, conta.getAgencia());
			ps.setString(4, conta.getNumero());
			ps.execute();
			ps.close();
		} catch (SQLException e) {
			Log.log(Level.SEVERE, "Erro ao adicionar valores na tabela conta", e);
			throw new RuntimeException(e);
		}
	}

	public void alterar(Conta conta) {

		try {
			PreparedStatement ps = this.connection
					.prepareStatement("update Conta set titular=?, banco=?, agencia=?, numero=? where id=?");
			ps.setString(1, conta.getTitular());
			ps.setString(2, conta.getBanco());
			ps.setString(3, conta.getAgencia());
			ps.setString(4, conta.getNumero());
			ps.setInt(5, conta.getId());
			ps.execute();
			ps.close();
		} catch (SQLException e) {
			Log.log(Level.SEVERE, "Erro ao alterar valores na tabela conta", e);
			throw new RuntimeException(e);
		}
	}

	public void remover(Conta conta) {

		try {
			PreparedStatement ps = this.connection
					.prepareStatement("delete from Conta where id=?");
			ps.setInt(1, conta.getId());
			ps.execute();
			ps.close();
		} catch (SQLException e) {
			Log.log(Level.SEVERE, "Erro ao remover valores na tabela conta", e);
			throw new RuntimeException(e);
		}
	}

	public Conta procurar(Integer id) {

		try {
			PreparedStatement ps = this.connection
					.prepareStatement("select titular, banco, numero, agencia from Conta where id = ?");
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if (!rs.next()) {
				return null;
			}
			Conta conta = new Conta();
			conta.setTitular(rs.getString("titular"));
			conta.setBanco(rs.getString("banco"));
			conta.setNumero(rs.getString("numero"));
			conta.setAgencia(rs.getString("agencia"));
			ps.close();
			return conta;
		} catch (SQLException e) {
			Log.log(Level.SEVERE, "Erro ao procura valores na tabela conta", e);
			throw new RuntimeException(e);
		}
	}

	public List<Conta> listarPaginada(int primeiro, int quantidade) {

		try {
			PreparedStatement ps = this.connection
					.prepareStatement("select titular, banco, numero, agencia from Conta limit ?,? ");
			ps.setInt(1, primeiro);
			ps.setInt(2, quantidade);

			ResultSet rs = ps.executeQuery();

			List<Conta> lista = new ArrayList<Conta>();

			while (rs.next()) {
				Conta conta = new Conta();
				conta.setTitular(rs.getString("titular"));
				conta.setBanco(rs.getString("banco"));
				conta.setNumero(rs.getString("numero"));
				conta.setAgencia(rs.getString("agencia"));
				lista.add(conta);
			}

			ps.close();
			return lista;
		} catch (SQLException e) {
			Log.log(Level.SEVERE, "Erro ao listaPaginada valores na tabela conta", e);
			throw new RuntimeException(e);
		}
	}

	public List<Conta> listar() {

		try {
			PreparedStatement ps = this.connection
					.prepareStatement("select titular, banco, numero, agencia from Conta");
			ResultSet rs = ps.executeQuery();

			List<Conta> lista = new ArrayList<Conta>();

			while (rs.next()) {
				Conta conta = new Conta();
				conta.setTitular(rs.getString("titular"));
				conta.setBanco(rs.getString("banco"));
				conta.setNumero(rs.getString("numero"));
				conta.setAgencia(rs.getString("agencia"));
				lista.add(conta);
			}

			ps.close();
			return lista;
		} catch (SQLException e) {
			Log.log(Level.SEVERE, "Erro ao listar valores na tabela conta", e);
			throw new RuntimeException(e);
		}
	}

	public List<Conta> procurarPeloNome(String nome) {

		try {
			PreparedStatement ps = this.connection
					.prepareStatement("select titular, banco, numero, agencia from Conta where titular like ?");
			ps.setString(1, "%" + nome + "%");
			ResultSet rs = ps.executeQuery();

			List<Conta> lista = new ArrayList<Conta>();

			while (rs.next()) {
				Conta conta = new Conta();
				conta.setTitular(rs.getString("titular"));
				conta.setBanco(rs.getString("banco"));
				conta.setNumero(rs.getString("numero"));
				conta.setAgencia(rs.getString("agencia"));
				lista.add(conta);
			}

			ps.close();
			return lista;
		} catch (SQLException e) {
			Log.log(Level.SEVERE, "Erro ao procuraPeloNome valores na tabela conta", e);
			throw new RuntimeException(e);
		}
	}

}
