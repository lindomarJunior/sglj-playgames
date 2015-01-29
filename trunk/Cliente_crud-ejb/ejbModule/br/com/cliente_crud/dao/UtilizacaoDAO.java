package br.com.cliente_crud.dao;

import java.util.List;

import br.com.cliente_crud.entity.Jogo;
import br.com.cliente_crud.entity.Utilizacao;

public interface UtilizacaoDAO {
	
	/**
	 * Listar Jogos pela plataforma
	 * @param idPlataforma
	 * @return
	 */
	public List<Jogo> listarJogoPorPlataforma(Integer idPlataforma);
	
	/**
	 * @param status
	 * @return
	 */
	public List<Utilizacao> listarUtilizacaoEspera(Integer status);
	
	/**
	 * @param status
	 * @return
	 */
	public List<Utilizacao> listarUtilizacaoAndamento(Integer status);
}
