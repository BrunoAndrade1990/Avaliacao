package br.dao;

import java.util.List;

import javax.persistence.EntityManager;

import br.model.Servico;
import br.util.JpaUtil;

public class ServicoDAO implements IDAO<Servico> {
	private EntityManager em = JpaUtil.getEntityManager();

	@Override
	public void atualizar(Servico obj) {
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
	public void excluir(Servico obj) {
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
	public List<Servico> listar() {
		return em.createQuery("select s from Servico AS s ", Servico.class)
				.getResultList();
	}

	@Override
	public Servico buscarPorId(int id) {
		return em.find(Servico.class, id);
	}
}
