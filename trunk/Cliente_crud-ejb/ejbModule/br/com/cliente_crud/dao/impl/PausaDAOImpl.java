package br.com.cliente_crud.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.cliente_crud.dao.PausaDAO;
import br.com.cliente_crud.entity.Pausa;

public class PausaDAOImpl implements PausaDAO {

	protected EntityManager entityManager;

	public PausaDAOImpl(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	public Pausa listarPausaPorUtilizacao(Integer idUtilizacao) {
		try {
			String hql = "FROM Pausa as pausa "
					+ "WHERE pausa.horaRetorno is null AND pausa.utilizacao.id = :idUtilizacao ";
			Query query = entityManager.createQuery(hql);
			query.setParameter("idUtilizacao", idUtilizacao);
			return (Pausa) query.getSingleResult();
		} catch (Exception e) {
			return null;
		}
	}
	
	@Override
	public List<Pausa> listarTodasPausaPorUtilizacao(Integer idUtilizacao) {
		try {
			String hql = "FROM Pausa as pausa "
					+ "WHERE pausa.utilizacao.id = :idUtilizacao ";
			Query query = entityManager.createQuery(hql);
			query.setParameter("idUtilizacao", idUtilizacao);
			return (List<Pausa>) query.getResultList();
		} catch (Exception e) {
			return null;
		}
	}
	
	@Override
	public List<Pausa> listarPausasAnterioresPorUtilizacao(Integer idUtilizacao) {
		try {
			String hql = "FROM Pausa as pausa "
					+ "WHERE pausa.horaRetorno is not null AND pausa.utilizacao.id = :idUtilizacao ";
			Query query = entityManager.createQuery(hql);
			query.setParameter("idUtilizacao", idUtilizacao);
			return (List<Pausa>) query.getResultList();
		} catch (Exception e) {
			return null;
		}
	}

}
