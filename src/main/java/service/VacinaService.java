package service;

import java.util.List;

import model.entities.Vacina;
import model.repository.VacinaRepository;

public class VacinaService {

	private VacinaRepository repository = new VacinaRepository();

	public Vacina salvar(Vacina novaVacina) {
		return repository.salvar(novaVacina);
	}

	public boolean atualizar(Vacina vacinaEditada) {
		return repository.alterar(vacinaEditada);
	}

	public boolean excluir(int id) {
		return repository.excluir(id);
	}

	public Vacina consultarPorId(int id) {
		return repository.consultarPorId(id);
	}

	public List<Vacina> consultarTodas() {
		return repository.consultarTodos();
	}
}