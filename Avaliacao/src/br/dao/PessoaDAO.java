package br.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import br.model.Pessoa;
import br.util.JpaUtil;

public class PessoaDAO implements IDAO<Pessoa> {

	private EntityManager em = JpaUtil.getEntityManager();

	@Override
	public void atualizar(Pessoa obj) {
		em.getTransaction().begin();
		try {
			em.merge(obj);
			em.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			em.getTransaction().rollback();
		}
	}

	@Override
	public void excluir(Pessoa obj) {
		em.getTransaction().begin();
		try {
			em.remove(obj);
			em.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			em.getTransaction().rollback();
		}
	}

	@Override
	public List<Pessoa> listar() {
		return em.createQuery("select p from Pessoa AS p ", Pessoa.class)
				.getResultList();
	}

	@Override
	public Pessoa buscarPorId(int id) {
		return em.find(Pessoa.class, id);
	}

	public Pessoa buscarPorNome(String valor) {
		List<Pessoa> lista = new ArrayList<Pessoa>();
		lista = em.createQuery(
						"SELECT p FROM Pessoa AS p WHERE p.pessoa = :nome")
				.setParameter("nome", valor).getResultList();
		return lista.get(0);
	}

}
