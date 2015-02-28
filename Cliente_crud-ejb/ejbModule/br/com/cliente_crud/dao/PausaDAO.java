package br.com.cliente_crud.dao;

import java.util.List;

import br.com.cliente_crud.entity.Pausa;

public interface PausaDAO {
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
