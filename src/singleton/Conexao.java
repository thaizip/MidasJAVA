package singleton;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
	private static Conexao conexao;
	private String driver = "com.mysql.jdbc.Driver";
	private String url = "jdbc:mysql://localhost/midas_java";
	private String usuario = "root";
	private String senha = "";
	
	public Connection getConexao() {
		try {
			Class.forName(driver);
			return DriverManager.getConnection(url, usuario, senha);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			return null;
		}		
	}
	
	public static synchronized Conexao getInstancia() {
		if (conexao == null) {
			conexao = new Conexao();
		}
		return conexao;
	}
}
