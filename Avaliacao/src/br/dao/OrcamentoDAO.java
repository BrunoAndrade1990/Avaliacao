package br.dao;

import java.util.List;

import javax.persistence.EntityManager;

import br.model.Orcamento;
import br.util.JpaUtil;

public class OrcamentoDAO implements IDAO<Orcamento> {

	private EntityManager em = JpaUtil.getEntityManager();

	@Override
	public void atualizar(Orcamento obj) {
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
	public void excluir(Orcamento obj) {
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
	public List<Orcamento> listar() {
		return em.createQuery("select o from Orcamento AS o ", Orcamento.class).getResultList();
	}

	@Override
	public Orcamento buscarPorId(int id) {
		return em.find(Orcamento.class, id);
	}

}
