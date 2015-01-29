package br.com.cliente_crud.service;

import java.sql.SQLException;
import java.util.List;

import javax.ejb.Local;

import br.com.cliente_crud.entity.Videogame;

@Local
public interface VideogameService {
	/**
	 * Inclui a entidade
	 * @param to
	 * @throws SQLException 
	 */
	public void incluir(Videogame to) throws SQLException;
	
	/**
	 * Exclui a entidade
	 * @param to
	 */
	public void excluir(Videogame to);
	
	/**
	 * Consulta a entidade
	 * @param classe
	 * @param pk
	 * @return
	 */
	public Videogame consultar(Class<Videogame> classe, Integer pk);
	
	/**
	 * Lista as entidades
	 * @param classe
	 * @return
	 */
	public List<Videogame> listar(Class<Videogame> classe);
	
	/**
	 * Lista os videogames disponiveis
	 * @param classe
	 * @return
	 */
	public List<Videogame> listarVideogamesDisponiveis();
	
	/**
	 * Atualiza as entidades
	 * @param to
	 */
	public void atualizar(Videogame to) throws SQLException;
}
