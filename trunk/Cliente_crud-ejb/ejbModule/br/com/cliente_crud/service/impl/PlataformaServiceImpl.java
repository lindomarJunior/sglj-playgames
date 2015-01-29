package br.com.cliente_crud.service.impl;

import java.sql.SQLException;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.cliente_crud.dao.impl.DAOImpl;
import br.com.cliente_crud.entity.Plataforma;
import br.com.cliente_crud.service.PlataformaService;

@Stateless
public class PlataformaServiceImpl implements PlataformaService {

private DAOImpl<Plataforma, Integer> daoGenerico; 
	
	@PersistenceContext
	protected EntityManager entityManager;

	@Override
	public void incluir(Plataforma to) throws SQLException {
		
		daoGenerico = new DAOImpl<Plataforma, Integer>(entityManager);
		daoGenerico.incluir(to);
	}

	@Override
	public void excluir(Plataforma to) {
		daoGenerico = new DAOImpl<Plataforma, Integer>(entityManager);
		daoGenerico.excluir(to);
	}

	@Override
	public Plataforma consultar(Class<Plataforma> classe, Integer pk) {
		daoGenerico = new DAOImpl<Plataforma, Integer>(entityManager);
		return daoGenerico.consultar(classe, pk);
	}
	
	@Override
	public List<Plataforma> listar(Class<Plataforma> classe) {
		
		daoGenerico = new DAOImpl<Plataforma, Integer>(entityManager);
		return (List<Plataforma>)daoGenerico.Listar(classe);
	}
	
	@Override
	public void atualizar(Plataforma to) throws SQLException {
		daoGenerico = new DAOImpl<Plataforma, Integer>(entityManager);
		daoGenerico.atualizar(to);
		
	}

}
