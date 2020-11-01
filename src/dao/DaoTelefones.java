package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import beans.BeanTelefone;
import connection.SingleConnection;

public class DaoTelefones {

	private Connection connection;

	public DaoTelefones() {
		connection = SingleConnection.getConnection(); // ------> Chama a conecção estabelecida no sistema
	}

	public void salvar(BeanTelefone telefone) {

		try {

			String sql = "insert into telefone(numero, tipo, usuario) values (?, ?, ?)";
			PreparedStatement insert = connection.prepareStatement(sql);
			insert.setString(1, telefone.getNumero());
			insert.setString(2, telefone.getTipo());
			insert.setLong(3, telefone.getUsuario());
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

	public List<BeanTelefone> listar(Long user) throws Exception {

		List<BeanTelefone> listar = new ArrayList<BeanTelefone>();

		String sql = "select * from telefone where usuario = " + user;

		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet resultSet = statement.executeQuery();

		while (resultSet.next()) {

			BeanTelefone beanTelefone = new BeanTelefone();
			beanTelefone.setId(resultSet.getLong("id"));
			beanTelefone.setNumero(resultSet.getString("numero"));
			beanTelefone.setTipo(resultSet.getString("tipo"));
			beanTelefone.setUsuario(resultSet.getLong("usuario"));

			listar.add(beanTelefone);
		}

		return listar;

	}

	public void delete(String id) {

		try {

			String sql = "delete from telefone where id = '" + id + "'";
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

}
