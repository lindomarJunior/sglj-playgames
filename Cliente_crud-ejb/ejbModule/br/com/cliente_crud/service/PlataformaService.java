package br.com.cliente_crud.service;

import java.sql.SQLException;
import java.util.List;

import javax.ejb.Local;

import br.com.cliente_crud.entity.Plataforma;

@Local
public interface PlataformaService {
	/**
	 * Inclui a entidade
	 * @param to
	 * @throws SQLException 
	 */
	public void incluir(Plataforma to) throws SQLException;
	
	/**
	 * Exclui a entidade
	 * @param to
	 */
	public void excluir(Plataforma to);
	
	/**
	 * Consulta a entidade
	 * @param classe
	 * @param pk
	 * @return
	 */
	public Plataforma consultar(Class<Plataforma> classe, Integer pk);
	
	/**
	 * Lista as entidades
	 * @param classe
	 * @return
	 */
	public List<Plataforma> listar(Class<Plataforma> classe);
	
	/**
	 * Atualiza as entidades
	 * @param to
	 */
	public void atualizar(Plataforma to) throws SQLException;
}
