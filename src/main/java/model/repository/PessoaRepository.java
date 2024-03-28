package model.repository;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.entities.Pessoa;

public class PessoaRepository implements BaseRepository<Pessoa> {

	@Override
	public Pessoa salvar(Pessoa novaPessoa) {
		String query = "INSERT INTO pessoa (nome, data_nascimento, sexo, cpf, tipo) VALUES (?, ?, ?, ?, ?)";
		Connection conn = Banco.getConnection();
		PreparedStatement ptsmt = Banco.getPreparedStatementWithPk(conn, query);
		try {
			ptsmt.setString(1, novaPessoa.getNome());
			ptsmt.setObject(2, novaPessoa.getDataNascimento());
			ptsmt.setString(3, novaPessoa.getSexo());
			ptsmt.setString(4, novaPessoa.getCpf());
			ptsmt.setInt(5, novaPessoa.getTipo());
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

		String query = "DELETE FROM pessoa WHERE id = " + id;

		try {
			if (stmt.executeUpdate(query) == 1) {
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
				pessoa.setId(Integer.parseInt(resultado.getString("id")));
				pessoa.setNome(resultado.getString("nome"));
				pessoa.setDataNascimento(resultado.getDate("data_nascimento").toLocalDate());
				pessoa.setSexo(resultado.getString("sexo"));
				pessoa.setCpf(resultado.getString("cpf"));
				pessoa.setTipo(resultado.getInt("tipo"));
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

	public boolean verificarCpf(Pessoa pessoa) {
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		ResultSet resultado = null;
		boolean retorno = false;

		String query = "SELECT cpf FROM pessoa WHERE cpf = '" + pessoa.getCpf() + "'";

		try {
			resultado = stmt.executeQuery(query);
			if (resultado.next()) {
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

	@Override
	public Pessoa consultarPorId(int id) {
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);

		Pessoa pessoa = null;
		ResultSet resultado = null;
		String query = " SELECT * FROM pessoa WHERE id = " + id;

		try {
			resultado = stmt.executeQuery(query);
			if (resultado.next()) {
				pessoa = new Pessoa();
				pessoa.setId(resultado.getInt("id"));
				pessoa.setNome(resultado.getString("nome"));
				pessoa.setCpf(resultado.getString("cpf"));
				pessoa.setSexo(resultado.getString("sexo"));
				pessoa.setDataNascimento(resultado.getDate("data_nascimento").toLocalDate());
				pessoa.setTipo(resultado.getInt("tipo"));
			}
		} catch (SQLException erro) {
			System.out.println("Erro ao consultar pessoa com o id: " + id);
			System.out.println("Erro: " + erro.getMessage());
		} finally {
			Banco.closeResultSet(resultado);
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);
		}
		return pessoa;
	}

	@Override
	public boolean alterar(Pessoa pessoaEditada) {
		boolean alterou = false;
		String query = " UPDATE pessoa " + " SET nome=?, cpf=?, sexo=?, data_nascimento=?, tipo=? "
				+ " WHERE id=? ";
		Connection conn = Banco.getConnection();
		PreparedStatement stmt = Banco.getPreparedStatementWithPk(conn, query);
		try {
			stmt.setString(1, pessoaEditada.getNome());
			stmt.setString(2, pessoaEditada.getCpf());
			stmt.setString(3, pessoaEditada.getSexo() + "");
			stmt.setDate(4, Date.valueOf(pessoaEditada.getDataNascimento()));
			stmt.setInt(5, pessoaEditada.getTipo());

			stmt.setInt(6, pessoaEditada.getId());
			alterou = stmt.executeUpdate() > 0;
		} catch (SQLException erro) {
			System.out.println("Erro ao atualizar pessoa");
			System.out.println("Erro: " + erro.getMessage());
		} finally {
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);
		}
		return alterou;
	}
}
