package br.dao;

import java.util.List;

public interface IDAO<T> {

	public void atualizar(T obj);
	public void excluir(T obj);
	public List<T> listar();
	public T buscarPorId(int id);
	
}
