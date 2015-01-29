package br.com.cliente_crud.service.impl;

import java.sql.SQLException;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.cliente_crud.dao.impl.DAOImpl;
import br.com.cliente_crud.dao.impl.UtilizacaoDAOImpl;
import br.com.cliente_crud.entity.Jogo;
import br.com.cliente_crud.entity.Utilizacao;
import br.com.cliente_crud.service.UtilizacaoService;

@Stateless
public class UtilizacaoServiceImpl implements UtilizacaoService {

	private DAOImpl<Utilizacao, Integer> daoGenerico;
	private UtilizacaoDAOImpl utilizacaoDAO;

	@PersistenceContext
	protected EntityManager entityManager;

	@Override
	public void incluir(Utilizacao to) throws SQLException {

		daoGenerico = new DAOImpl<Utilizacao, Integer>(entityManager);
		daoGenerico.incluir(to);
	}

	@Override
	public void excluir(Utilizacao to) {
		daoGenerico = new DAOImpl<Utilizacao, Integer>(entityManager);
		daoGenerico.excluir(to);
	}

	@Override
	public Utilizacao consultar(Class<Utilizacao> classe, Integer pk) {
		daoGenerico = new DAOImpl<Utilizacao, Integer>(entityManager);
		return daoGenerico.consultar(classe, pk);
	}

	@Override
	public List<Utilizacao> listar(Class<Utilizacao> classe) {

		daoGenerico = new DAOImpl<Utilizacao, Integer>(entityManager);
		return (List<Utilizacao>) daoGenerico.Listar(classe);
	}

	@Override
	public void atualizar(Utilizacao to) throws SQLException {
		daoGenerico = new DAOImpl<Utilizacao, Integer>(entityManager);
		daoGenerico.atualizar(to);

	}

	@Override
	public List<Jogo> consultarJogoPorPlataforma(Integer idPlataforma) {	
		utilizacaoDAO = new UtilizacaoDAOImpl(entityManager);
		return utilizacaoDAO.listarJogoPorPlataforma(idPlataforma);
	}

	@Override
	public List<Utilizacao> listarUtilizacaoEspera(Integer status) {
		utilizacaoDAO = new UtilizacaoDAOImpl(entityManager);
		return utilizacaoDAO.listarUtilizacaoEspera(status);
	}

	@Override
	public List<Utilizacao> listarUtilizacaoAndamento(Integer status) {
		utilizacaoDAO = new UtilizacaoDAOImpl(entityManager);
		return utilizacaoDAO.listarUtilizacaoAndamento(status);
	}

}
