package br.com.cliente_crud.service;

import java.sql.SQLException;
import java.util.List;

import javax.ejb.Local;

import br.com.cliente_crud.entity.UtilizacaoJogo;

@Local
public interface UtilizacaoJogoService {
	/**
	 * Inclui a entidade
	 * @param to
	 * @throws SQLException 
	 */
	public void incluir(UtilizacaoJogo to) throws SQLException;
	
	/**
	 * Exclui a entidade
	 * @param to
	 */
	public void excluir(UtilizacaoJogo to);
	
	/**
	 * Consulta a entidade
	 * @param classe
	 * @param pk
	 * @return
	 */
	public UtilizacaoJogo consultar(Class<UtilizacaoJogo> classe, Integer pk);
	
	/**
	 * Lista as entidades
	 * @param classe
	 * @return
	 */
	public List<UtilizacaoJogo> listar(Class<UtilizacaoJogo> classe);
	
	/**
	 * Atualiza as entidades
	 * @param to
	 */
	public void atualizar(UtilizacaoJogo to) throws SQLException;
	
	/**
	 * @param idJogo
	 * @param idUtilizacao
	 * @return
	 */
	public UtilizacaoJogo consultarUtilizacaoJogo(Integer idJogo,
			Integer idUtilizacao);
}
