package service;

import exception.ControleVacinasException;
import model.entities.Pais;
import model.repository.PaisRepository;

public class PaisService {

	private PaisRepository repository = new PaisRepository();
	
	public Pais salvar(Pais novo) throws ControleVacinasException {
		return repository.salvar(novo);
	}
	
	public Pais consultarPorId(int id) {
		return repository.consultarPorId(id);
	}
}