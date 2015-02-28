package br.com.cliente_crud.dao.impl;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.cliente_crud.dao.ClienteDAO;
import br.com.cliente_crud.entity.Cliente;
import br.com.cliente_crud.entity.Usuario;

public class ClienteDAOImpl implements ClienteDAO {

	protected EntityManager entityManager;

	public ClienteDAOImpl(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	public Cliente consultarCliente(String cpf) {
		String hql = "FROM Cliente WHERE cpf = :cpf";
		Query query = entityManager.createQuery(hql);
		Cliente cliente = (Cliente) query.setParameter("cpf", cpf)
				.getSingleResult();
		return cliente;
	}

	@Override
	public Usuario consultarUsuario(String login, String senha) {
		try {
			String hql = "FROM Usuario WHERE login = :login AND senha = :senha";
			Query query = entityManager.createQuery(hql);
			query.setParameter("login", login);
			query.setParameter("senha", senha);
			Usuario usuario = (Usuario) query.getSingleResult();
			return usuario;
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
	}

}
