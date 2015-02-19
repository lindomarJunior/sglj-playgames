package br.com.cliente_crud.service.impl;

import java.sql.SQLException;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.cliente_crud.dao.impl.DAOImpl;
import br.com.cliente_crud.dao.impl.UtilizacaoJogoDAOImpl;
import br.com.cliente_crud.entity.UtilizacaoJogo;
import br.com.cliente_crud.service.UtilizacaoJogoService;

@Stateless
public class UtilizacaoJogoServiceImpl implements UtilizacaoJogoService {

private DAOImpl<UtilizacaoJogo, Integer> daoGenerico; 
	
	@PersistenceContext
	protected EntityManager entityManager;

	@Override
	public void incluir(UtilizacaoJogo to) throws SQLException {
		
		daoGenerico = new DAOImpl<UtilizacaoJogo, Integer>(entityManager);
		daoGenerico.incluir(to);
	}

	@Override
	public void excluir(UtilizacaoJogo to) {
		daoGenerico = new DAOImpl<UtilizacaoJogo, Integer>(entityManager);
		daoGenerico.excluir(to);
	}

	@Override
	public UtilizacaoJogo consultar(Class<UtilizacaoJogo> classe, Integer pk) {
		daoGenerico = new DAOImpl<UtilizacaoJogo, Integer>(entityManager);
		return daoGenerico.consultar(classe, pk);
	}
	
	@Override
	public List<UtilizacaoJogo> listar(Class<UtilizacaoJogo> classe) {
		
		daoGenerico = new DAOImpl<UtilizacaoJogo, Integer>(entityManager);
		return (List<UtilizacaoJogo>)daoGenerico.Listar(classe);
	}
	
	@Override
	public void atualizar(UtilizacaoJogo to) throws SQLException {
		daoGenerico = new DAOImpl<UtilizacaoJogo, Integer>(entityManager);
		daoGenerico.atualizar(to);
		
	}

	@Override
	public UtilizacaoJogo consultarUtilizacaoJogo(Integer idJogo,
			Integer idUtilizacao) {
		UtilizacaoJogoDAOImpl utilizacaoJogoDAOImpl = new UtilizacaoJogoDAOImpl(entityManager);
		return utilizacaoJogoDAOImpl.consultarUtilizacaoJogo(idJogo, idUtilizacao);
	}

}
