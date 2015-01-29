package br.com.cliente_crud.dao.impl;

import java.io.Serializable;
import java.util.List;
import java.sql.SQLException;

import javax.persistence.EntityManager;

import br.com.cliente_crud.dao.DAO;

/**
 * @author Lindomar Junior
 *
 */
public class DAOImpl<T, I extends Serializable> implements DAO<T, I> {
	
	protected EntityManager entityManager;
	
	
	public DAOImpl(EntityManager entity) {
		this.entityManager = entity;
	}
	
	@Override
	public void incluir(T to) throws SQLException{
		entityManager.persist(to);		
	}

	@Override
	public void excluir(T to) {
		entityManager.remove(entityManager.merge(to));
	}

	@Override
	public T consultar(Class<T> classe, I pk) {

		return entityManager.find(classe, pk);
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> Listar(Class<T> classe) {
		
		return entityManager.createQuery(("FROM " + classe.getSimpleName())).getResultList();
	}

	@Override
	public void atualizar(T to) throws SQLException{
		entityManager.merge(to);
		
	}

}
