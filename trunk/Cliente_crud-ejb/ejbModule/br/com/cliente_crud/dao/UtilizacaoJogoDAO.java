package br.com.cliente_crud.dao;

import br.com.cliente_crud.entity.UtilizacaoJogo;

public interface UtilizacaoJogoDAO {
	/**
	 * @param idJogo
	 * @param idUtilizacao
	 * @return
	 */
	public UtilizacaoJogo consultarUtilizacaoJogo(Integer idJogo,
			Integer idUtilizacao);
}
