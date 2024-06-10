package model.repository;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import model.entity.Montadora;
import model.entity.Pessoa;
import model.entity.Vacina;

public class MontadoraRepository {

	public Montadora inserir(Montadora novaMontadora) {
		String sql = " INSERT INTO montadora (nome, pais, presidente, data_fundacao) "
				+ " VALUES(?, ?, ?, ?) ";
		Connection conexao = Banco.getConnection();
		PreparedStatement stmt = Banco.getPreparedStatementWithPk(conexao, sql);

		try {
			stmt.setString(1, novaMontadora.getNome());
			stmt.setString(2, novaMontadora.getPais());
			stmt.setString(3, novaMontadora.getPresidente());
			stmt.setDate(4, Date.valueOf(novaMontadora.getDataFundacao()));
	
			stmt.execute();
			ResultSet resultado = stmt.getGeneratedKeys();
			if (resultado.next()) {
				novaMontadora.setId(resultado.getInt(1));
			}
		} catch (SQLException e) {
			System.out.println("Erro ao salvar nova montadora");
			System.out.println("Erro: " + e.getMessage());
		}

		return novaMontadora;
	}
	
	public Montadora consultarPorId(int id) {
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);

		Montadora montadora = null;
		ResultSet resultado = null;
		String query = " SELECT * FROM montadora WHERE id_montadora = " + id;

		try {
			resultado = stmt.executeQuery(query);
			if (resultado.next()) {
				montadora = new Montadora();
				montadora.setId(Integer.parseInt(resultado.getString("ID_MONTADORA")));
				montadora.setNome(resultado.getString("NOME"));
				montadora.setPais(resultado.getString("PAIS"));
				montadora.setPresidente(resultado.getString("PRESIDENTE"));
				montadora.setDataFundacao(resultado.getDate("DATA_FUNDACAO").toLocalDate());
			}
		} catch (SQLException erro) {
			System.out.println("Erro ao consultar montadora com o id: " + id);
			System.out.println("Erro: " + erro.getMessage());
		} finally {
			Banco.closeResultSet(resultado);
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);
		}
		return montadora;
	}
}
