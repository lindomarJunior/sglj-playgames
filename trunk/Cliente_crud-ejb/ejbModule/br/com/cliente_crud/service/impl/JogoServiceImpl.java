package br.com.cliente_crud.service.impl;

import java.sql.SQLException;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.cliente_crud.dao.impl.DAOImpl;
import br.com.cliente_crud.entity.Jogo;
import br.com.cliente_crud.service.JogoService;

@Stateless
public class JogoServiceImpl implements JogoService {

	private DAOImpl<Jogo, Integer> daoGenerico; 
	
	@PersistenceContext
	protected EntityManager entityManager;

	@Override
	public void incluir(Jogo to) throws SQLException {
		
		daoGenerico = new DAOImpl<Jogo, Integer>(entityManager);
		daoGenerico.incluir(to);
	}

	@Override
	public void excluir(Jogo to) {
		daoGenerico = new DAOImpl<Jogo, Integer>(entityManager);
		daoGenerico.excluir(to);
	}

	@Override
	public Jogo consultar(Class<Jogo> classe, Integer pk) {
		daoGenerico = new DAOImpl<Jogo, Integer>(entityManager);
		return daoGenerico.consultar(classe, pk);
	}
	
	@Override
	public List<Jogo> listar(Class<Jogo> classe) {
		
		daoGenerico = new DAOImpl<Jogo, Integer>(entityManager);
		return (List<Jogo>)daoGenerico.Listar(classe);
	}
	
	@Override
	public void atualizar(Jogo to) throws SQLException {
		daoGenerico = new DAOImpl<Jogo, Integer>(entityManager);
		daoGenerico.atualizar(to);
		
	}

}
