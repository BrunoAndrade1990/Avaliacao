package br.dao;

import java.util.List;

import javax.persistence.EntityManager;

import br.model.OrcamentoItem;
import br.util.JpaUtil;

public class OrcamentoItemDAO implements IDAO<OrcamentoItem>{

	private EntityManager em = JpaUtil.getEntityManager();
	
	@Override
	public void atualizar(OrcamentoItem obj) {
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
	public void excluir(OrcamentoItem obj) {
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
	public List<OrcamentoItem> listar() {
		return em.createQuery("select o from Orcamento AS o ", OrcamentoItem.class).getResultList();
	}

	@Override
	public OrcamentoItem buscarPorId(int id) {
		return em.find(OrcamentoItem.class, id);
	}

}
