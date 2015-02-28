package br.com.cliente_crud.service.impl;

import java.sql.SQLException;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.cliente_crud.dao.PausaDAO;
import br.com.cliente_crud.dao.impl.DAOImpl;
import br.com.cliente_crud.dao.impl.PausaDAOImpl;
import br.com.cliente_crud.entity.Pausa;
import br.com.cliente_crud.service.PausaService;

@Stateless
public class PausaServiceImpl implements PausaService {

	private DAOImpl<Pausa, Integer> daoGenerico; 
	private PausaDAOImpl pausaDAOImpl;
	
	@PersistenceContext
	protected EntityManager entityManager;

	@Override
	public void incluir(Pausa to) throws SQLException {
		
		daoGenerico = new DAOImpl<Pausa, Integer>(entityManager);
		daoGenerico.incluir(to);
	}

	@Override
	public void excluir(Pausa to) {
		daoGenerico = new DAOImpl<Pausa, Integer>(entityManager);
		daoGenerico.excluir(to);
	}

	@Override
	public Pausa consultar(Class<Pausa> classe, Integer pk) {
		daoGenerico = new DAOImpl<Pausa, Integer>(entityManager);
		return daoGenerico.consultar(classe, pk);
	}
	
	@Override
	public List<Pausa> listar(Class<Pausa> classe) {
		
		daoGenerico = new DAOImpl<Pausa, Integer>(entityManager);
		return (List<Pausa>)daoGenerico.Listar(classe);
	}
	
	@Override
	public void atualizar(Pausa to) throws SQLException {
		daoGenerico = new DAOImpl<Pausa, Integer>(entityManager);
		daoGenerico.atualizar(to);
		
	}
	
	public Pausa listarPausaPorUtilizacao(Integer idUtilizacao){
		pausaDAOImpl = new PausaDAOImpl(entityManager);
		return pausaDAOImpl.listarPausaPorUtilizacao(idUtilizacao);
	}

	@Override
	public List<Pausa> listarTodasPausaPorUtilizacao(Integer idUtilizacao) {
		pausaDAOImpl = new PausaDAOImpl(entityManager);
		return pausaDAOImpl.listarTodasPausaPorUtilizacao(idUtilizacao);
	}

	@Override
	public List<Pausa> listarPausasAnterioresPorUtilizacao(Integer idUtilizacao) {
		pausaDAOImpl = new PausaDAOImpl(entityManager);
		return pausaDAOImpl.listarPausasAnterioresPorUtilizacao(idUtilizacao);
	}

}
