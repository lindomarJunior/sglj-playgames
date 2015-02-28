package br.com.cliente_crud.service;

import java.sql.SQLException;
import java.util.Calendar;
import java.util.List;

import javax.ejb.Local;

import br.com.cliente_crud.entity.Cliente;
import br.com.cliente_crud.entity.Jogo;
import br.com.cliente_crud.entity.Utilizacao;
import br.com.cliente_crud.relatorio.RelatorioPerfilCliente;
import br.com.cliente_crud.relatorio.RelatorioPerfilClientela;

@Local
public interface UtilizacaoService {
	/**
	 * Inclui a entidade
	 * @param to
	 * @throws SQLException 
	 */
	public void incluir(Utilizacao to) throws SQLException;
	
	/**
	 * Exclui a entidade
	 * @param to
	 */
	public void excluir(Utilizacao to);
	
	/**
	 * Consulta a entidade
	 * @param classe
	 * @param pk
	 * @return
	 */
	public Utilizacao consultar(Class<Utilizacao> classe, Integer pk);
	
	/**
	 * Lista as entidades
	 * @param classe
	 * @return
	 */
	public List<Utilizacao> listar(Class<Utilizacao> classe);
	
	/**
	 * Atualiza as entidades
	 * @param to
	 */
	public void atualizar(Utilizacao to) throws SQLException;
	
	/**
	 * @param idPlataforma
	 * @return
	 */
	public List<Jogo> consultarJogoPorPlataforma(Integer idPlataforma);
	/**
	 * Lista as utilizações pelo status espera
	 * @param classe
	 * @return
	 */
	public List<Utilizacao> listarUtilizacaoEspera(Integer status);
	
	/**
	 * Lista as utilizações pelo status ativo
	 * @param classe
	 * @return
	 */
	public List<Utilizacao> listarUtilizacaoAndamento(Integer status);
	
	/**
	 * @return
	 */
	public List<RelatorioPerfilClientela> gerarRelatorioJogosMaisUtilizados(Calendar dataInicial, Calendar dataFinal);
	
	/**
	 * @return
	 */
	public List<RelatorioPerfilClientela> gerarRelatorioPlataformasMaisUtilizadas(Calendar dataInicial, Calendar dataFinal);
	
	/**
	 * @return
	 */
	public List<RelatorioPerfilClientela> gerarRelatorioRankingUtilizacao(Calendar dataInicial, Calendar dataFinal);
	
	/**
	 * @return
	 */
	public List<RelatorioPerfilClientela> gerarRelatorioFaturamento(Calendar dataInicio, Calendar dataFinal);
		
	/**
	 * @param idCliente
	 * @return
	 */
	public List<RelatorioPerfilClientela> gerarRelatorioJogosMaisUtilizadosPorCliente(Integer idCliente);
	
	/**
	 * @param idCliente
	 * @return
	 */
	public List<RelatorioPerfilClientela> gerarRelatorioUtilizacaoPorEvento(Integer idCliente);
	
	/**
	 * @param idCliente
	 * @return
	 */
	public List<RelatorioPerfilCliente> sugerirJogos(Cliente cliente);
}
