package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import beans.Beanportfolio;
import connection.SingleConnection;

public class DaoUsuario {

	private Connection connection;

	public DaoUsuario() {
		connection = SingleConnection.getConnection(); // ------> Chama a conecção estabelecida no sistema
	}

	public void salvar(Beanportfolio usuario) {

		try {

			String sql = "insert into usuario(login, senha, nome, telefone, cep, rua, bairro, cidade, estado, ibge, fotobase64, contenttype) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement insert = connection.prepareStatement(sql);
			insert.setString(1, usuario.getLogin());
			insert.setString(2, usuario.getSenha());
			insert.setString(3, usuario.getNome());
			insert.setString(4, usuario.getTelefone());
			insert.setString(5, usuario.getCep());
			insert.setString(6, usuario.getRua());
			insert.setString(7, usuario.getBairro());
			insert.setString(8, usuario.getCidade());
			insert.setString(9, usuario.getEstado());
			insert.setString(10, usuario.getIbge());
			insert.setString(11, usuario.getFotoBase64());
			insert.setString(12, usuario.getContentType());
			insert.execute();
			connection.commit();

		} catch (Exception e) {
			e.printStackTrace();
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
	}

	public List<Beanportfolio> listar() throws Exception {

		List<Beanportfolio> listar = new ArrayList<Beanportfolio>();

		String sql = "select * from usuario";

		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet resultSet = statement.executeQuery();

		while (resultSet.next()) {

			Beanportfolio beanPortfolio = new Beanportfolio();
			beanPortfolio.setId(resultSet.getLong("id"));
			beanPortfolio.setLogin(resultSet.getString("login"));
			beanPortfolio.setSenha(resultSet.getString("senha"));
			beanPortfolio.setNome(resultSet.getString("nome"));
			beanPortfolio.setTelefone(resultSet.getString("telefone"));
			beanPortfolio.setCep(resultSet.getString("cep"));
			beanPortfolio.setRua(resultSet.getString("rua"));
			beanPortfolio.setBairro(resultSet.getString("bairro"));
			beanPortfolio.setCidade(resultSet.getString("cidade"));
			beanPortfolio.setEstado(resultSet.getString("estado"));
			beanPortfolio.setIbge(resultSet.getString("ibge"));
			beanPortfolio.setFotoBase64(resultSet.getString("fotobase64"));
			beanPortfolio.setContentType(resultSet.getString("contenttype"));
			
			

			listar.add(beanPortfolio);
		}

		return listar;

	}

	public void delete(String id) {

		try {

			String sql = "delete from usuario where id = '" + id + "'";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.execute();

			connection.commit();
		} catch (Exception e) {
			e.printStackTrace();
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
	}

	public Beanportfolio consultar(String id) throws Exception {

		String sql = "select * from usuario where id = '" + id + "'";

		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		ResultSet resultSet = preparedStatement.executeQuery();

		if (resultSet.next()) {

			Beanportfolio beanPortfolio = new Beanportfolio();
			beanPortfolio.setId(resultSet.getLong("id"));
			beanPortfolio.setLogin(resultSet.getString("login"));
			beanPortfolio.setSenha(resultSet.getString("senha"));
			beanPortfolio.setNome(resultSet.getString("nome"));
			beanPortfolio.setTelefone(resultSet.getString("telefone"));
			beanPortfolio.setCep(resultSet.getString("cep"));
			beanPortfolio.setRua(resultSet.getString("rua"));
			beanPortfolio.setBairro(resultSet.getString("bairro"));
			beanPortfolio.setCidade(resultSet.getString("cidade"));
			beanPortfolio.setEstado(resultSet.getString("estado"));
			beanPortfolio.setIbge(resultSet.getString("ibge"));
			beanPortfolio.setFotoBase64(resultSet.getString("fotobase64"));
			beanPortfolio.setContentType(resultSet.getString("contenttype"));

			return beanPortfolio;
		}

		return null;
	}

	public boolean validarLogin(String login) throws Exception {

		String sql = "select count(1) as qtd from usuario where login = '" + login + "'";

		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		ResultSet resultSet = preparedStatement.executeQuery();

		if (resultSet.next()) {

			return resultSet.getInt("qtd") <= 0;
		}

		return false;
	}

	public boolean validarSenha(String senha) throws Exception {

		String sql = "select count(1) as qtd from usuario where senha = '" + senha + "'";

		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		ResultSet resultSet = preparedStatement.executeQuery();

		if (resultSet.next()) {

			return resultSet.getInt("qtd") <= 0;
		}

		return false;
	}

	public void atualizar(Beanportfolio usuario) {

		try {

			String sql = "update usuario set login = ?, senha = ?, nome = ?, telefone = ?, cep = ?, rua = ?, bairro = ?, cidade = ?, estado = ?, ibge = ? where id = "
					+ usuario.getId();

			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, usuario.getLogin());
			preparedStatement.setString(2, usuario.getSenha());
			preparedStatement.setString(3, usuario.getNome());
			preparedStatement.setString(4, usuario.getTelefone());
			preparedStatement.setString(5, usuario.getCep());
			preparedStatement.setString(6, usuario.getRua());
			preparedStatement.setString(7, usuario.getBairro());
			preparedStatement.setString(8, usuario.getCidade());
			preparedStatement.setString(9, usuario.getEstado());
			preparedStatement.setString(10, usuario.getIbge());
			preparedStatement.executeUpdate();
			connection.commit();

		} catch (Exception e) {
			e.printStackTrace();
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}

		}
	}

}
