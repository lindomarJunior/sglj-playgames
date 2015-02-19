package br.com.cliente_crud.dao.impl;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.cliente_crud.dao.UtilizacaoJogoDAO;
import br.com.cliente_crud.entity.UtilizacaoJogo;

public class UtilizacaoJogoDAOImpl implements UtilizacaoJogoDAO {

	protected EntityManager entityManager;

	public UtilizacaoJogoDAOImpl(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	public UtilizacaoJogo consultarUtilizacaoJogo(Integer idJogo,
			Integer idUtilizacao) {

		String hql = "FROM UtilizacaoJogo as utilJogo WHERE utilJogo.idJogo = :idJogo AND utilJogo.idUtilizacao = :idUtilizacao";
		Query query = entityManager.createQuery(hql);
		query.setParameter("idJogo", idJogo);
		query.setParameter("idUtilizacao", idUtilizacao);
		return (UtilizacaoJogo) query.getSingleResult();
	}
}
