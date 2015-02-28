package br.com.cliente_crud.service.impl;

import java.sql.SQLException;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.cliente_crud.dao.impl.DAOImpl;
import br.com.cliente_crud.entity.Historico;
import br.com.cliente_crud.service.HistoricoService;

@Stateless
public class HistoricoServiceImpl implements HistoricoService {

	private DAOImpl<Historico, Integer> daoGenerico;

	@PersistenceContext
	protected EntityManager entityManager;

	@Override
	public void incluir(Historico to) throws SQLException {

		daoGenerico = new DAOImpl<Historico, Integer>(entityManager);
		daoGenerico.incluir(to);
	}

	@Override
	public void excluir(Historico to) {
		daoGenerico = new DAOImpl<Historico, Integer>(entityManager);
		daoGenerico.excluir(to);
	}

	@Override
	public Historico consultar(Class<Historico> classe, Integer pk) {
		daoGenerico = new DAOImpl<Historico, Integer>(entityManager);
		return daoGenerico.consultar(classe, pk);
	}

	@Override
	public List<Historico> listar(Class<Historico> classe) {

		daoGenerico = new DAOImpl<Historico, Integer>(entityManager);
		return (List<Historico>) daoGenerico.Listar(classe);
	}

	@Override
	public void atualizar(Historico to) throws SQLException {
		daoGenerico = new DAOImpl<Historico, Integer>(entityManager);
		daoGenerico.atualizar(to);

	}

}
