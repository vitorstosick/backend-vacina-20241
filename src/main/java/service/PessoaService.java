package service;

import exception.ControleVacinasException;
import model.entities.Pessoa;
import model.repository.PessoaRepository;
import java.util.ArrayList;

public class PessoaService {

	private PessoaRepository pessoaRepository = new PessoaRepository();

	public Pessoa salvar(Pessoa entity) throws ControleVacinasException {

		if (entity.getNome() != null && !entity.getNome().isEmpty() && entity.getDataNascimento() != null
				&& entity.getSexo() != null && !entity.getSexo().isEmpty() && entity.getCpf() != null
				&& !entity.getCpf().isEmpty() && entity.getTipo() != null && !entity.getTipo().isEmpty()) {

			if (pessoaRepository.verificarCpf(entity)) {
				throw new ControleVacinasException("Erro ao cadastrar: CPF já cadastrado!");

			} else {
				pessoaRepository.salvar(entity);

			}

		} else {
			throw new ControleVacinasException("Preencha todos os campos!");
		}

		return entity;

	}

	public boolean excluir(int id) {
		return this.pessoaRepository.excluir(id);
	}

	public ArrayList<Pessoa> listarTodos() {
		return this.pessoaRepository.consultarTodos();
	}

}
