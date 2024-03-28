package service;

import exception.ControleVacinasException;
import model.entities.Pessoa;
import model.repository.PessoaRepository;
import model.repository.VacinacaoRepository;
import java.util.ArrayList;

public class PessoaService {

	private PessoaRepository pessoaRepository = new PessoaRepository();
	private VacinacaoRepository vacinacaoRepository = new VacinacaoRepository();

	public Pessoa salvar(Pessoa entity) throws ControleVacinasException {

		if (entity.getNome() != null && !entity.getNome().isEmpty() && entity.getDataNascimento() != null
				&& entity.getSexo() != null && !entity.getSexo().isEmpty() && entity.getCpf() != null
				&& !entity.getCpf().isEmpty()) {

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

	public boolean excluir(int id) throws ControleVacinasException {

		if (vacinacaoRepository.consultarPorId(id).getIdPessoa() == pessoaRepository.consultarPorId(id).getId()) {
			throw new ControleVacinasException(
					"Pessoa não pode ser exclui, pois já recebeu ao menos uma dose de vacina.");
		}

		return this.pessoaRepository.excluir(id);
	}

	public Pessoa consultarPorId(int id) {
		return this.pessoaRepository.consultarPorId(id);
	}

	public ArrayList<Pessoa> listarTodos() {
		return this.pessoaRepository.consultarTodos();
	}

	public boolean atualizar(Pessoa pessoaEditada) throws ControleVacinasException {
		return this.pessoaRepository.alterar(pessoaEditada);
	}

}
