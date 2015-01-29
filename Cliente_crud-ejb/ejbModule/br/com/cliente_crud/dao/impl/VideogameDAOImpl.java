package br.com.cliente_crud.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.cliente_crud.dao.VideogameDAO;
import br.com.cliente_crud.entity.Videogame;

public class VideogameDAOImpl implements VideogameDAO {

	protected EntityManager entityManager;

	public VideogameDAOImpl(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	public List<Videogame> listarVideogameDisponivel() {
		String hql = "SELECT vid FROM Videogame vid "
				+ "WHERE vid.disponibilidade = 1 "
				+ "ORDER BY vid.plataforma.nome, vid.id";
		Query query = entityManager.createQuery(hql);
		return (List<Videogame>) query.getResultList();
	}

}
