package br.com.cliente_crud.service;

import java.sql.SQLException;
import java.util.List;

import javax.ejb.Local;

import br.com.cliente_crud.entity.Evento;

@Local
public interface EventoService {
	
	/**
	 * Inclui a entidade
	 * @param to
	 * @throws SQLException 
	 */
	public void incluir(Evento to) throws SQLException;
	
	/**
	 * Exclui a entidade
	 * @param to
	 */
	public void excluir(Evento to);
	
	/**
	 * Consulta a entidade
	 * @param classe
	 * @param pk
	 * @return
	 */
	public Evento consultar(Class<Evento> classe, Integer pk);
	
	/**
	 * Lista as entidades
	 * @param classe
	 * @return
	 */
	public List<Evento> listar(Class<Evento> classe);
	
	/**
	 * Atualiza as entidades
	 * @param to
	 */
	public void atualizar(Evento to) throws SQLException;
}
