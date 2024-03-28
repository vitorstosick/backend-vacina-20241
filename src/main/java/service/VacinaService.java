package service;

import java.util.List;

import exception.ControleVacinasException;
import model.entities.Vacina;
import model.entities.Vacinacao;
import model.repository.VacinaRepository;

public class VacinaService {

	private VacinaRepository repository = new VacinaRepository();
	private Vacinacao vacinacao = new Vacinacao();

	public Vacina salvar(Vacina novaVacina) {
		return repository.salvar(novaVacina);
	}

	public boolean atualizar(Vacina vacinaEditada) {
		return repository.alterar(vacinaEditada);
	}

	public boolean excluir(int id) throws ControleVacinasException {
		
		if(vacinacao.getVacina() == null || vacinacao.getVacina().getId() == 0) {
			throw new ControleVacinasException("Vacina já foi aplicada, portanto não pode ser excluida.");
		}
		
		return repository.excluir(id);
	}

	public Vacina consultarPorId(int id) {
		return repository.consultarPorId(id);
	}

	public List<Vacina> consultarTodas() {
		return repository.consultarTodos();
	}
	
}