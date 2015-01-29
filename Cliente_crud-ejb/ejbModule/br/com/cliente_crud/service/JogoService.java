package br.com.cliente_crud.service;

import java.sql.SQLException;
import java.util.List;

import javax.ejb.Local;

import br.com.cliente_crud.entity.Jogo;

@Local
public interface JogoService {
	
	/**
	 * Inclui a entidade
	 * @param to
	 * @throws SQLException 
	 */
	public void incluir(Jogo to) throws SQLException;
	
	/**
	 * Exclui a entidade
	 * @param to
	 */
	public void excluir(Jogo to);
	
	/**
	 * Consulta a entidade
	 * @param classe
	 * @param pk
	 * @return
	 */
	public Jogo consultar(Class<Jogo> classe, Integer pk);
	
	/**
	 * Lista as entidades
	 * @param classe
	 * @return
	 */
	public List<Jogo> listar(Class<Jogo> classe);
	
	/**
	 * Atualiza as entidades
	 * @param to
	 */
	public void atualizar(Jogo to) throws SQLException;
	
}
