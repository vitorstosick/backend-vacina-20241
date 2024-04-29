package service;

import java.util.ArrayList;

import exception.ControleVacinasException;
import model.entity.Pais;
import model.repository.PaisRepository;

public class PaisService {

	private PaisRepository repository = new PaisRepository();
	
	public Pais salvar(Pais novo) throws ControleVacinasException {
		return repository.salvar(novo);
	}
	
	public Pais consultarPorId(int id) {
		return repository.consultarPorId(id);
	}
	
	public ArrayList<Pais> consultarTodos() {
		return repository.consultarTodos();
	}
}