package model.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
		return false;
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
	public ArrayList consultarTodos() {
		return null;
	}

	@Override
	public Object salvar(Object novaEntidade) {
		// TODO Auto-generated method stub
		return null;
	}

}
