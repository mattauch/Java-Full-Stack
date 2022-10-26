package model;
//JDBC - Java DataBase Connection

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {

	private static String urlConexao = "jdbc:mysql://localhost:3306/escola?userSSL=false&userTimezone=true&serverTimezone=UTC";
	
	private static String usuario = "root";
	
	private static String senha = "050822";
	
	private static Connection conexao = null;
	
	public static boolean conectar() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conexao = DriverManager.getConnection(urlConexao, usuario, senha);
			
		} catch (ClassNotFoundException e) {
			System.out.println("Driver do banco Mysql não encontrado.");
			e.printStackTrace();
			return false;
			
		} catch (SQLException e) {
			System.out.println("Falha de conexão com o banco.");
			e.printStackTrace();
			return false;
			
		}
		
		System.out.println("Conectado com sucesso.");
		return true;
	}
	
	public static Connection getConexao() {
		return conexao;
	}
	
	public static void fecharConexao() {
		try {
			conexao.close();
			System.out.println("Desconectado.");
			
		} catch (SQLException e) {
			System.out.println("Falha ao fechar a conexão com o banco de dados.");
			e.printStackTrace();
		}
	}
}
