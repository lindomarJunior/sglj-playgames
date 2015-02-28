package br.com.cliente_crud.service.impl;

import java.sql.SQLException;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.cliente_crud.dao.impl.ClienteDAOImpl;
import br.com.cliente_crud.dao.impl.DAOImpl;
import br.com.cliente_crud.entity.Usuario;
import br.com.cliente_crud.service.UsuarioService;

@Stateless
public class UsuarioServiceImpl implements UsuarioService{
	
	private DAOImpl<Usuario, Integer> daoGenerico; 
	private ClienteDAOImpl clienteDAOImpl;
	
	@PersistenceContext
	protected EntityManager entityManager;

	@Override
	public void incluir(Usuario to) throws SQLException {
		
		daoGenerico = new DAOImpl<Usuario, Integer>(entityManager);
		daoGenerico.incluir(to);
	}

	@Override
	public void excluir(Usuario to) {
		daoGenerico = new DAOImpl<Usuario, Integer>(entityManager);
		daoGenerico.excluir(to);
	}

	@Override
	public Usuario consultar(Class<Usuario> classe, Integer pk) {
		daoGenerico = new DAOImpl<Usuario, Integer>(entityManager);
		return daoGenerico.consultar(classe, pk);
	}
	
	@Override
	public List<Usuario> listar(Class<Usuario> classe) {
		
		daoGenerico = new DAOImpl<Usuario, Integer>(entityManager);
		return (List<Usuario>)daoGenerico.Listar(classe);
	}
	
	@Override
	public void atualizar(Usuario to) throws SQLException {
		daoGenerico = new DAOImpl<Usuario, Integer>(entityManager);
		daoGenerico.atualizar(to);
		
	}

	@Override
	public Usuario consultarUsuario(String login, String senha) {
		clienteDAOImpl = new ClienteDAOImpl(entityManager);
		return clienteDAOImpl.consultarUsuario(login, senha);
	}
	

}
