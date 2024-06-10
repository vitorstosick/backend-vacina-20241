package service;

import java.util.ArrayList;

import exception.ControleVacinasException;
import model.entity.Carro;
import model.repository.CarroRepository;
import model.seletor.CarroSeletor;

public class CarroService {

	CarroRepository carroRepository = new CarroRepository();
	
	public ArrayList<Carro> consultadorComFiltros(CarroSeletor seletor) throws ControleVacinasException {
		verificarFiltors(seletor);
		
		return carroRepository.consultadorComFiltros(seletor);
	}

	private void verificarFiltors(CarroSeletor seletor) throws ControleVacinasException {
		if (seletor.getModelo() == null && seletor.getNomeMontadora() == null && seletor.getPlaca() == null &&
		        seletor.getAnoInicial() == null && seletor.getAnoFinal() == null && 
		        seletor.getValorInicial() == null && seletor.getValorFinal() == null) {
			throw new ControleVacinasException("Preencha pelo menos um filtro!");
		    }
	}

}
