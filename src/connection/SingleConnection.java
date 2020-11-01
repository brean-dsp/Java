package connection;

/** Responsavel por fazer a conexão com o banco de dadosHelena2012**/

import java.sql.Connection;
import java.sql.DriverManager;

public class SingleConnection {
	
	private static String banco = "jdbc:postgresql://localhost:5432/portfoliojsp?autoReconnect=true";
	private static String password = "Helena2012";
	private static String user = "postgres";
	private static Connection connection = null;
	
	static {
		conectar();
	}
	
	public SingleConnection() {
		conectar();
	}
	
	private static void conectar(){
		try {
			
			if(connection == null) {
				Class.forName("org.postgresql.Driver");
				connection = DriverManager.getConnection(banco, user, password);
				connection.setAutoCommit(false);
			}
			
		}catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Erro ao conectar com o banco de dados");
		}
	}

	public static Connection getConnection() {
		return connection;
	}
	
}

