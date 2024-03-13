package model.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.entities.Pessoa;

public class PessoaRepository implements BaseRepository {

	@Override
	public Pessoa salvar(Pessoa novaPessoa) {
		String query = "INSERT INTO pessoa (nome, data_nascimento, sexo, cpf, tipo) VALUES (?, ?, ?, ?, ?)";
		Connection conn = Banco.getConnection();
		PreparedStatement ptsmt = Banco.getPreparedStatement(conn, query);
		try {
			ptsmt.setString(1, novaPessoa.getNome());
			ptsmt.setObject(2, novaPessoa.getDataNascimento());
			ptsmt.setString(3, novaPessoa.getSexo());
			ptsmt.setString(4, novaPessoa.getCpf());
			ptsmt.setString(5, novaPessoa.getTipo());
			ptsmt.execute();

			ResultSet resultado = ptsmt.getGeneratedKeys();
			if (resultado.next()) {
				novaPessoa.setId(resultado.getInt(1));
			}
		} catch (SQLException erro) {
			System.out.println("Erro ao cadastrar nova pessoa.");
			System.out.println("ERROR" + erro.getMessage());
		} finally {
			Banco.closePreparedStatement(ptsmt);
			Banco.closeConnection(conn);
		}

		return novaPessoa;
	}

	@Override
	public boolean excluir(int id) {
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		boolean excluido = false;
		
		String query = "DELETE FROM pessoa WHERE ID = " + id;
		
		try {
			if(stmt.executeUpdate(query) == 1) {
				excluido = true;
			}
		} catch (SQLException erro) {
			System.out.println("erro ao executar excluir()");
			System.out.println("erro: " + erro.getMessage());
		} finally {
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);
		}
		return excluido;
	}

	@Override
	public boolean alterar(Object entidade) {
		return false;
	}

	@Override
	public Object consultarPorId(int id) {
		return null;
	}

	@Override
	public ArrayList<Pessoa> consultarTodos() {
		ArrayList<Pessoa> pessoas = new ArrayList<>();
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		ResultSet resultado = null;

		String query = "SELECT * FROM pessoa";

		try {
			resultado = stmt.executeQuery(query);
			while (resultado.next()) {
				Pessoa pessoa = new Pessoa();
				pessoa.setId(Integer.parseInt(resultado.getString("ID")));
				pessoa.setNome(resultado.getString("NOME"));
				pessoa.setDataNascimento(resultado.getDate("DATA_NASCIMENTO").toLocalDate());
				pessoa.setSexo(resultado.getString("SEXO"));
				pessoa.setCpf(resultado.getString("CPF"));
				pessoa.setTipo(resultado.getString("TIPO"));
				pessoas.add(pessoa);
			}
		} catch (SQLException erro) {
			System.out.println("erro ao executar consultarTodo()");
			System.out.println("erro: " + erro.getMessage());
		} finally {
			Banco.closeResultSet(resultado);
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);
		}

		return pessoas;
	}

	@Override
	public Object salvar(Object novaEntidade) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public boolean verificarCpf(Pessoa pessoa) {
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		ResultSet resultado = null;
		boolean retorno = false;
		
		String query = "SELECT cpf FROM pessoa WHERE cpf = '" + pessoa.getCpf() + "'";
		
		try {
			resultado = stmt.executeQuery(query);
			if(resultado.next()) {
				retorno = true;
			}
		} catch (SQLException erro) {
			System.out.println("erro ao executar verificarCpf(");
			System.out.println("erro: " + erro.getMessage());
		} finally {
			Banco.closeResultSet(resultado);
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);
		}
		
		return retorno;
	}

}
