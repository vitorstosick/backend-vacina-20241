package model.repository;

import java.util.ArrayList;

import model.entities.Pessoa;

public interface BaseRepository<T> {

	public T salvar(T novaEntidade);

	public boolean excluir(int id);

	public boolean alterar(T entidade);

	public T consultarPorId(int id);

	public ArrayList<T> consultarTodos();
}
