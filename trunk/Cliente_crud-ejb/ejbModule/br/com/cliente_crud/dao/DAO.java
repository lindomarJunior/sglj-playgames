package br.com.cliente_crud.dao;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

/**
 * @author Lindomar Junior
 *
 * @param <T> ToAbstrato
 * @param <PK> ToAbstrato contem as chaves
 */
public interface DAO<T, I extends Serializable>{
	
	/**
	 * Inclui a entidade
	 * @param to
	 * @throws SQLException 
	 */
	public void incluir(T to) throws SQLException;
	
	/**
	 * Exclui a entidade
	 * @param to
	 */
	public void excluir(T to);
	
	/**
	 * Consulta a entidade
	 * @param classe
	 * @param pk
	 * @return
	 */
	public T consultar(Class<T> classe, I pk);
	
	/**
	 * Lista as entidades
	 * @param classe
	 * @return
	 */
	public List<T> Listar(Class<T> classe);
	
	/**
	 * Atualiza as entidades
	 * @param to
	 */
	public void atualizar(T to) throws SQLException;
}
