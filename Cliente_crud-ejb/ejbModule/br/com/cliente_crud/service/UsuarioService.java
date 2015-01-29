package br.com.cliente_crud.service;

import java.sql.SQLException;
import java.util.List;

import javax.ejb.Local;

import br.com.cliente_crud.entity.Usuario;


@Local
public interface UsuarioService {
	
	/**
	 * Inclui a entidade
	 * @param to
	 * @throws SQLException 
	 */
	public void incluir(Usuario to) throws SQLException;
	
	/**
	 * Exclui a entidade
	 * @param to
	 */
	public void excluir(Usuario to);
	
	/**
	 * Consulta a entidade
	 * @param classe
	 * @param pk
	 * @return
	 */
	public Usuario consultar(Class<Usuario> classe, Integer pk);
	
	/**
	 * Lista as entidades
	 * @param classe
	 * @return
	 */
	public List<Usuario> listar(Class<Usuario> classe);
	
	/**
	 * Atualiza as entidades
	 * @param to
	 */
	public void atualizar(Usuario to) throws SQLException;
	
}
