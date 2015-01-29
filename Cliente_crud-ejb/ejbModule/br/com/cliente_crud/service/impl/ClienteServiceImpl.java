package br.com.cliente_crud.service.impl;

import java.sql.SQLException;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.cliente_crud.dao.ClienteDAO;
import br.com.cliente_crud.dao.impl.ClienteDAOImpl;
import br.com.cliente_crud.dao.impl.DAOImpl;
import br.com.cliente_crud.entity.Cliente;
import br.com.cliente_crud.service.ClienteService;

@Stateless
public class ClienteServiceImpl implements ClienteService {

	private DAOImpl<Cliente, Integer> daoGenerico;
	private ClienteDAOImpl clienteDAO;

	@PersistenceContext
	protected EntityManager entityManager;

	@Override
	public void incluir(Cliente to) throws SQLException {

		daoGenerico = new DAOImpl<Cliente, Integer>(entityManager);
		daoGenerico.incluir(to);
	}

	@Override
	public void excluir(Cliente to) {
		daoGenerico = new DAOImpl<Cliente, Integer>(entityManager);
		daoGenerico.excluir(to);
	}

	@Override
	public Cliente consultar(Class<Cliente> classe, Integer pk) {
		daoGenerico = new DAOImpl<Cliente, Integer>(entityManager);
		return daoGenerico.consultar(classe, pk);
	}

	@Override
	public List<Cliente> listar(Class<Cliente> classe) {

		daoGenerico = new DAOImpl<Cliente, Integer>(entityManager);
		return (List<Cliente>) daoGenerico.Listar(classe);
	}

	@Override
	public void atualizar(Cliente to) throws SQLException {
		daoGenerico = new DAOImpl<Cliente, Integer>(entityManager);
		daoGenerico.atualizar(to);

	}
	
	@Override
	public Cliente consultarCliente(Long cpf){
		clienteDAO = new ClienteDAOImpl(entityManager);
		return clienteDAO.consultarCliente(cpf);
	}
}
