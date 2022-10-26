package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Aluno;

public class AlunoDao  implements Dao<Aluno>{
	
	private Connection conexao;
	
	public AlunoDao(Connection conexao) {
		this.conexao = conexao;
	}
	
	@Override
	public boolean inserir(Aluno aluno) {
		System.out.println(aluno);
		String sql = "INSERT INTO Aluno(nome, cpf, fone) VALUES (?, ?, ?)";
		
		PreparedStatement cmd;
		
		try {
			cmd = conexao.prepareStatement(sql);
			cmd.setString(1, aluno.getNome());
			cmd.setString(2, aluno.getCpf());
			cmd.setString(3, aluno.getFone());
			/*
			 * Retorna 1 ou 0
			 * Retorna o número de linhas afetadas: 1
			 */
			int retorno = cmd.executeUpdate();
			//fecha o Statement
			cmd.close();
			return retorno > 0;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	@Override
	public boolean atualizar(Aluno aluno) {
		String sql = "UPDATE Aluno SET nome=?, cpf=?, fone=? WHERE id=?";
		
		PreparedStatement cmd;
		
		try {
			cmd = conexao.prepareStatement(sql);
			cmd.setString(1, aluno.getNome());
			cmd.setString(2, aluno.getCpf());
			cmd.setString(3, aluno.getFone());
			cmd.setLong(4, aluno.getId());
			/*
			 * Retorna 1 ou 0
			 * Retorna o número de linhas afetadas: 1
			 */
			int retorno = cmd.executeUpdate();
			//fecha o Statement
			cmd.close();
			return retorno > 0;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	@Override
	public Aluno buscar(long id) {
		String sql = "SELECT * FROM  Aluno WHERE id=?";
		Aluno aluno = null;
		
		try {
			PreparedStatement cmd = conexao.prepareStatement(sql);
			cmd.setLong(1, id);
			ResultSet rs = cmd.executeQuery();
			//Verifica se o Resultset retornou pelo menos uma lista
			if(rs.next()) {
				//pega a coluna nome e salva na variável nome
				String nome = rs.getString("nome");
				String cpf = rs.getString("cpf");
				String fone = rs.getString("fone");
				aluno = new Aluno(nome, cpf, fone);
			}
				cmd.close();
	
		} catch (SQLException e) {
			e.printStackTrace();
			aluno = null;
		}
		return aluno;
	}
	@Override
	public boolean apagar(long id) {
		String sql = "DELETE FROM Aluno WHERE id=?";
		
		try {
			PreparedStatement cmd = conexao.prepareStatement(sql);
			cmd.setLong(1, id);
			int retorno = cmd.executeUpdate(); // 0 ou > 0
			cmd.close();
			return retorno > 0;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	@Override
	public List<Aluno> listar() {
		String sql = "SELECT * FROM Aluno ORDER BY id";
		List<Aluno> lista = new ArrayList<>();
		
		try {
			PreparedStatement cmd = conexao.prepareStatement(sql);
			ResultSet rs = cmd.executeQuery();
			
			//verifica se o ResultSet retornou pelo menos uma linha
			while(rs.next()) {
				long id = rs.getLong("id");
				String nome = rs.getString("nome");
				String cpf = rs.getString("cpf");
				String fone = rs.getString("fone");
				
				Aluno aluno = new Aluno(nome, cpf, fone);
				aluno.setId(id);
				
				lista.add(aluno);
			}
			cmd.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lista;
	}
	
}
