package model.repository;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.entity.Pessoa;
import model.repository.Banco;
import model.repository.BaseRepository;
import model.seletor.PessoaSeletor;

public class PessoaRepository implements BaseRepository<Pessoa> {

	@Override
	public Pessoa salvar(Pessoa novaPessoa) {
		String sql = " INSERT INTO pessoa (nome, cpf, sexo, id_pais, " + "		               data_nascimento, tipo) "
				+ " VALUES(?, ?, ?, ?, ?, ?) ";
		Connection conexao = Banco.getConnection();
		PreparedStatement stmt = Banco.getPreparedStatementWithPk(conexao, sql);

		try {
			stmt.setString(1, novaPessoa.getNome());
			stmt.setString(2, novaPessoa.getCpf());
			stmt.setString(3, novaPessoa.getSexo() + "");
			stmt.setInt(4, novaPessoa.getPaisOrigem().getId());
			stmt.setDate(5, Date.valueOf(novaPessoa.getDataNascimento()));
			stmt.setInt(6, novaPessoa.getTipo());

			stmt.execute();
			ResultSet resultado = stmt.getGeneratedKeys();
			if (resultado.next()) {
				novaPessoa.setId(resultado.getInt(1));
			}
		} catch (SQLException e) {
			System.out.println("Erro ao salvar nova pessoa");
			System.out.println("Erro: " + e.getMessage());
		}

		return novaPessoa;
	}

	@Override
	public boolean excluir(int id) {
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		boolean excluiu = false;
		String query = "DELETE FROM pessoa WHERE id = " + id;
		try {
			if (stmt.executeUpdate(query) == 1) {
				excluiu = true;
			}
		} catch (SQLException erro) {
			System.out.println("Erro ao excluir pessoa");
			System.out.println("Erro: " + erro.getMessage());
		} finally {
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);
		}
		return excluiu;
	}

	@Override
	public boolean alterar(Pessoa pessoaEditada) {
		boolean alterou = false;
		String query = " UPDATE pessoa " + " SET nome=?, cpf=?, sexo=?, id_pais=?, " + " data_nascimento=?, tipo=? "
				+ " WHERE id=? ";
		Connection conn = Banco.getConnection();
		PreparedStatement stmt = Banco.getPreparedStatementWithPk(conn, query);
		try {
			stmt.setString(1, pessoaEditada.getNome());
			stmt.setString(2, pessoaEditada.getCpf());
			stmt.setString(3, pessoaEditada.getSexo() + "");
			stmt.setInt(4, pessoaEditada.getPaisOrigem().getId());
			stmt.setDate(5, Date.valueOf(pessoaEditada.getDataNascimento()));
			stmt.setInt(6, pessoaEditada.getTipo());

			stmt.setInt(7, pessoaEditada.getId());
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
				pessoa.setId(resultado.getInt("ID"));
				pessoa.setNome(resultado.getString("NOME"));
				pessoa.setCpf(resultado.getString("CPF"));
				pessoa.setSexo(resultado.getString("SEXO").charAt(0));
				pessoa.setDataNascimento(resultado.getDate("DATA_NASCIMENTO").toLocalDate());
				pessoa.setTipo(resultado.getInt("TIPO"));

				PaisRepository paisRepository = new PaisRepository();
				pessoa.setPaisOrigem(paisRepository.consultarPorId(resultado.getInt("ID_PAIS")));

				// TODO comentado durante a prova para evitar confusões
				// VacinacaoRepository vacinacaoRepository = new VacinacaoRepository();
				// pessoa.setVacinacoes(vacinacaoRepository.consultarPorIdPessoa(pessoa.getId()));
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
	public ArrayList<Pessoa> consultarTodos() {
		ArrayList<Pessoa> pessoas = new ArrayList<>();
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);

		ResultSet resultado = null;
		String query = " SELECT * FROM pessoa";

		try {
			resultado = stmt.executeQuery(query);
			while (resultado.next()) {
				Pessoa pessoa = new Pessoa();
				pessoa.setId(resultado.getInt("ID"));
				pessoa.setNome(resultado.getString("NOME"));
				pessoa.setCpf(resultado.getString("CPF"));
				pessoa.setSexo(resultado.getString("SEXO").charAt(0));
				pessoa.setDataNascimento(resultado.getDate("DATA_NASCIMENTO").toLocalDate());
				pessoa.setTipo(resultado.getInt("TIPO"));

				PaisRepository paisRepository = new PaisRepository();
				pessoa.setPaisOrigem(paisRepository.consultarPorId(resultado.getInt("ID_PAIS")));

				pessoas.add(pessoa);
			}
		} catch (SQLException erro) {
			System.out.println("Erro ao consultar todas as pessoas");
			System.out.println("Erro: " + erro.getMessage());
		} finally {
			Banco.closeResultSet(resultado);
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);
		}
		return pessoas;
	}

	public boolean cpfJaCadastrado(String cpf) {
		boolean cpfJaUtilizado = false;

		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		String query = " SELECT count(id) FROM pessoa WHERE cpf = " + cpf;

		try {
			ResultSet resultado = stmt.executeQuery(query);
			cpfJaUtilizado = (resultado.getInt(1) > 0);
		} catch (SQLException e) {
			System.out.println("Erro ao consultar CPF. Causa: " + e.getMessage());
		}

		return cpfJaUtilizado;
	}

	public ArrayList<Pessoa> listarPorPerquisador() {
		ArrayList<Pessoa> listaPessoa = new ArrayList<Pessoa>();

		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		ResultSet resultado = null;
		String query = "SELECT * FROM pessoa WHERE tipo = '" + 1 + "'";
		try {
			resultado = stmt.executeQuery(query);
			while (resultado.next()) {
				Pessoa pessoaEntity = new Pessoa();
				pessoaEntity.setId(resultado.getInt("id"));
				pessoaEntity.setNome(resultado.getString("nome"));
				pessoaEntity.setDataNascimento(resultado.getDate("data_nascimento").toLocalDate());
				pessoaEntity.setSexo(resultado.getString("sexo").charAt(0));
				pessoaEntity.setCpf(resultado.getString("cpf"));
				pessoaEntity.setTipo(resultado.getInt("tipo"));
				PaisRepository paisRepository = new PaisRepository();
				pessoaEntity.setPaisOrigem(paisRepository.consultarPorId(resultado.getInt("id")));
				// VacinacaoRepository repository = new VacinacaoRepository();
				// pessoaEntity.setTodasVacinas(repository.consultarTodasVacinasPorPessoa(pessoaEntity.getId()));
				listaPessoa.add(pessoaEntity);
			}

		} catch (SQLException e) {

			System.out.println("Erro ao consultar todas os pesquisadores.");
			System.out.println("ERRO: " + e.getMessage());
		} finally {

			Banco.closeResultSet(resultado);
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);
		}

		return listaPessoa;
	}
	
	public ArrayList<Pessoa> consultarPorFiltro(PessoaSeletor seletor) {

		ArrayList<Pessoa> pessoas = new ArrayList<Pessoa>();
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		ResultSet resultado = null;
		boolean primeiro = true;

		String query = " select pe.* from pessoa pe " + " inner join pais p on pe.id = p.id ";

		if (seletor.getNomePessoa() != null) {
			if (primeiro) {
				query += " where ";
			} else {
				query += " and ";
			}

			query += " upper(pe.nome) like upper  ('%" + seletor.getNomePessoa() + "%')";
			primeiro = false;

		}

		if (seletor.getNomePais() != null) {
			if (primeiro) {
				query += " where ";
			} else {
				query += " and ";
			}

			query += " upper(p.nome) like upper('%" + seletor.getNomePais() + "%')";

		}

		if (seletor.getDataNascimento() != null) {
			if (primeiro) {
				query += " and ";
			}
			query += "pe.data_nascimento = " + seletor.getDataNascimento();
			primeiro = false;
		}
			try {
				resultado = stmt.executeQuery(query);
				while (resultado.next()) {
					Pessoa pessoaEntity = new Pessoa();
					pessoaEntity.setId(resultado.getInt("id"));
					pessoaEntity.setNome(resultado.getString("nome"));
					pessoaEntity.setDataNascimento(resultado.getDate("data_nascimento").toLocalDate());
					pessoaEntity.setSexo(resultado.getString("sexo").charAt(0));
					pessoaEntity.setCpf(resultado.getString("cpf"));
					pessoaEntity.setTipo(resultado.getInt("tipo"));
					PaisRepository paisRepository = new PaisRepository();
					pessoaEntity.setPaisOrigem(paisRepository.consultarPorId(resultado.getInt("id")));
					// VacinacaoRepository repository = new VacinacaoRepository();
					// pessoaEntity.setTodasVacinas(repository.consultarTodasVacinasPorPessoa(pessoaEntity.getId()));
					pessoas.add(pessoaEntity);
				}

			} catch (SQLException e) {
				System.out.println("ERRO AO CONSULTAR TODAS AS PESSOAS SELETO!");
				System.out.println("ERRO: " + e.getMessage());
			} finally {
				Banco.closeResultSet(resultado);
				Banco.closeStatement(stmt);
				Banco.closeConnection(conn);
			}

			return pessoas;

		

	}

}