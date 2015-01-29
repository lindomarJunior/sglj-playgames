package br.com.cliente_crud.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.cliente_crud.dao.UtilizacaoDAO;
import br.com.cliente_crud.entity.Jogo;
import br.com.cliente_crud.entity.Utilizacao;

public class UtilizacaoDAOImpl implements UtilizacaoDAO {

	protected EntityManager entityManager;

	public UtilizacaoDAOImpl(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	public List<Jogo> listarJogoPorPlataforma(Integer idPlataforma) {
		String hql = "FROM Jogo as jogo WHERE jogo.plataforma.id = :idPlataforma";
		Query query = entityManager.createQuery(hql);
		return (List<Jogo>) query.setParameter("idPlataforma", idPlataforma).getResultList();
	}

	@Override
	public List<Utilizacao> listarUtilizacaoEspera(Integer status) {
		String hql = "FROM Utilizacao as util WHERE util.statusEspera = :status";
		Query query = entityManager.createQuery(hql);
		return (List<Utilizacao>) query.setParameter("status", status).getResultList();
	}

	@Override
	public List<Utilizacao> listarUtilizacaoAndamento(Integer status) {
		String hql = "FROM Utilizacao as util WHERE util.statusAtivo = :status";
		Query query = entityManager.createQuery(hql);
		return (List<Utilizacao>) query.setParameter("status", status).getResultList();
	}

}
