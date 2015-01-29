package br.com.cliente_crud.dao.impl;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.cliente_crud.dao.ClienteDAO;
import br.com.cliente_crud.entity.Cliente;

public class ClienteDAOImpl implements ClienteDAO {

	protected EntityManager entityManager;

	public ClienteDAOImpl(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	public Cliente consultarCliente(Long cpf) {
		String hql = "FROM Cliente WHERE cpf = :cpf";
		Query query = entityManager.createQuery(hql);
		Cliente cliente = (Cliente) query.setParameter("cpf", cpf)
				.getSingleResult();
		return cliente;
	}

}
