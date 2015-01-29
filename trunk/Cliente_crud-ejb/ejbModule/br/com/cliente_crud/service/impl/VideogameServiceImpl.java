package br.com.cliente_crud.service.impl;

import java.sql.SQLException;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.cliente_crud.dao.VideogameDAO;
import br.com.cliente_crud.dao.impl.DAOImpl;
import br.com.cliente_crud.dao.impl.VideogameDAOImpl;
import br.com.cliente_crud.entity.Videogame;
import br.com.cliente_crud.service.VideogameService;

@Stateless
public class VideogameServiceImpl implements VideogameService {
	
	List<Videogame> listaVideogame;
	
	private DAOImpl<Videogame, Integer> daoGenerico; 
	private VideogameDAOImpl videogameDAO;
	
	@PersistenceContext
	protected EntityManager entityManager;

	@Override
	public void incluir(Videogame to) throws SQLException {
		
		daoGenerico = new DAOImpl<Videogame, Integer>(entityManager);
		daoGenerico.incluir(to);
	}

	@Override
	public void excluir(Videogame to) {
		daoGenerico = new DAOImpl<Videogame, Integer>(entityManager);
		daoGenerico.excluir(to);
	}

	@Override
	public Videogame consultar(Class<Videogame> classe, Integer pk) {
		daoGenerico = new DAOImpl<Videogame, Integer>(entityManager);
		return daoGenerico.consultar(classe, pk);
	}
	
	@Override
	public List<Videogame> listar(Class<Videogame> classe) {
		
		daoGenerico = new DAOImpl<Videogame, Integer>(entityManager);
		return (List<Videogame>)daoGenerico.Listar(classe);
	}
	
	@Override
	public void atualizar(Videogame to) throws SQLException {
		daoGenerico = new DAOImpl<Videogame, Integer>(entityManager);
		daoGenerico.atualizar(to);
		
	}

	@Override
	public List<Videogame> listarVideogamesDisponiveis() {
		
		videogameDAO = new VideogameDAOImpl(entityManager);		
		return videogameDAO.listarVideogameDisponivel();
	}

}
