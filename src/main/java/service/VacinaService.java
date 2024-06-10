package service;

import java.util.ArrayList;
import java.util.List;

import exception.ControleVacinasException;
import model.entity.Vacina;
import model.entity.Vacinacao;
import model.repository.VacinaRepository;
import model.repository.VacinacaoRepository;
import model.seletor.VacinaSeletor;

public class VacinaService {

	private VacinaRepository repository = new VacinaRepository();

	public Vacina salvar(Vacina novaVacina) {
		return repository.salvar(novaVacina);
	}

	public boolean atualizar(Vacina vacinaEditada) {
		return repository.alterar(vacinaEditada);
	}

	public boolean excluir(int id) throws ControleVacinasException {
		VacinacaoRepository vacinacaoRepository = new VacinacaoRepository();
		ArrayList<Vacinacao> aplicacoesDaVacina = vacinacaoRepository.consultarPorIdVacina(id);

		if (aplicacoesDaVacina.size() > 0) {
			throw new ControleVacinasException("Vacina não pode ser excluída, pois já foi aplicada");
		}

		return repository.excluir(id);
	}

	public Vacina consultarPorId(int id) {
		return repository.consultarPorId(id);
	}

	public List<Vacina> consultarTodas() {
		return repository.consultarTodos();
	}

	public List<Vacina> consultarComFiltros(VacinaSeletor seletor) {
		return repository.consultarComFiltros(seletor);
	}

	public int contarTodosRegistro(VacinaSeletor seletor) {
		return this.repository.contarTotalDeRegistro(seletor);
	}
	
	public int contarPaginas(VacinaSeletor seletor) {
		return this.repository.contarPaginas(seletor);
	}
}