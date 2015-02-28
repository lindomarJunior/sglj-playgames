package br.com.cliente_crud.service;

import java.sql.SQLException;
import java.util.List;

import javax.ejb.Local;

import br.com.cliente_crud.entity.Pausa;

@Local
public interface PausaService {
	/**
	 * Inclui a entidade
	 * @param to
	 * @throws SQLException 
	 */
	public void incluir(Pausa to) throws SQLException;
	
	/**
	 * Exclui a entidade
	 * @param to
	 */
	public void excluir(Pausa to);
	
	/**
	 * Consulta a entidade
	 * @param classe
	 * @param pk
	 * @return
	 */
	public Pausa consultar(Class<Pausa> classe, Integer pk);
	
	/**
	 * Lista as entidades
	 * @param classe
	 * @return
	 */
	public List<Pausa> listar(Class<Pausa> classe);
	
	/**
	 * Atualiza as entidades
	 * @param to
	 */
	public void atualizar(Pausa to) throws SQLException;
	
	/**
	 * @param idUtilizacao
	 * @return
	 */
	public Pausa listarPausaPorUtilizacao(Integer idUtilizacao);
	
	/**
	 * @param idUtilizacao
	 * @return
	 */
	public List<Pausa> listarTodasPausaPorUtilizacao(Integer idUtilizacao);
	
	/**
	 * @param idUtilizacao
	 * @return
	 */
	public List<Pausa> listarPausasAnterioresPorUtilizacao(Integer idUtilizacao);
}
