package model.repository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.entity.Carro;
import model.entity.Montadora;
import model.entity.Pessoa;
import model.entity.Vacina;
import model.seletor.CarroSeletor;

public class CarroRepository {

	public ArrayList<Carro> consultadorComFiltros(CarroSeletor seletor) {
		ArrayList<Carro> carros = new ArrayList<>();
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);

		ResultSet resultado = null;
		String query = " select c.* from carro c " + " inner join montadora m on c.id_montadora = m.id_montadora";

		boolean primeiro = true;
		if (seletor.getModelo() != null) {
			if (primeiro) {
				query += " WHERE ";
			} else {
				query += " AND ";
			}
			query += "upper(c.modelo) LIKE UPPER('%" + seletor.getModelo() + "%')";
			primeiro = false;
		}

		if (seletor.getNomeMontadora() != null) {
			if (primeiro) {
				query += " WHERE ";
			} else {
				query += " AND ";
			}
			query += " upper(m.nome) LIKE UPPER('%" + seletor.getNomeMontadora() + "%')";
		}

		if (seletor.getPlaca() != null) {
			if (primeiro) {
				query += " where ";
			} else {
				query += " and ";
			}

			query += " upper (c.placa) like upper('%" + seletor.getPlaca() + "%')";
		}

		if ((seletor.getValorInicial()) != null && (seletor.getValorFinal() != null)) {
			if (!primeiro) {
				query += " and ";
			}
			query += " c.valor BETWEEN " + seletor.getValorInicial() + " and " + seletor.getValorFinal();
			primeiro = false;
		}

		if ((seletor.getAnoInicial()) != null && (seletor.getAnoFinal() != null)) {
			if (!primeiro) {
				query += " and ";
			}
			query += " c.ano BETWEEN " + seletor.getAnoInicial() + " and " + seletor.getAnoFinal();
			primeiro = false;
		}

		try {
			resultado = stmt.executeQuery(query);
			MontadoraRepository montadoraRepository = new MontadoraRepository();
			while (resultado.next()) {
				Carro carro = new Carro();
				carro.setId(Integer.parseInt(resultado.getString("ID_CARRO")));
				carro.setModelo(resultado.getString("MODELO"));
				carro.setPlaca(resultado.getString("PLACA"));
				carro.setAno(resultado.getInt("ANO"));
				carro.setValor(resultado.getDouble("VALOR"));
				Montadora montadora = montadoraRepository.consultarPorId(resultado.getInt("ID_MONTADORA"));
				carro.setIdMontadora(montadora.getId());
				carros.add(carro);
			}
		} catch (SQLException erro) {
			System.out.println("Erro ao consultar todas os carros");
			System.out.println("Erro: " + erro.getMessage());
		} finally {
			Banco.closeResultSet(resultado);
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);
		}
		return carros;
	}
}
