package br.com.cliente_crud.service.impl;

import java.sql.SQLException;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.cliente_crud.dao.impl.DAOImpl;
import br.com.cliente_crud.entity.Evento;
import br.com.cliente_crud.service.EventoService;

@Stateless
public class EventoServiceImpl implements EventoService {

	private DAOImpl<Evento, Integer> daoGenerico;

	@PersistenceContext
	protected EntityManager entityManager;

	@Override
	public void incluir(Evento to) throws SQLException {

		daoGenerico = new DAOImpl<Evento, Integer>(entityManager);
		daoGenerico.incluir(to);
	}

	@Override
	public void excluir(Evento to) {
		daoGenerico = new DAOImpl<Evento, Integer>(entityManager);
		daoGenerico.excluir(to);
	}

	@Override
	public Evento consultar(Class<Evento> classe, Integer pk) {
		daoGenerico = new DAOImpl<Evento, Integer>(entityManager);
		return daoGenerico.consultar(classe, pk);
	}

	@Override
	public List<Evento> listar(Class<Evento> classe) {

		daoGenerico = new DAOImpl<Evento, Integer>(entityManager);
		return (List<Evento>) daoGenerico.Listar(classe);
	}

	@Override
	public void atualizar(Evento to) throws SQLException {
		daoGenerico = new DAOImpl<Evento, Integer>(entityManager);
		daoGenerico.atualizar(to);

	}

}
