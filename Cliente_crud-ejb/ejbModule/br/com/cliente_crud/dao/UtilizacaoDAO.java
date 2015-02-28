package br.com.cliente_crud.dao;

import java.util.Calendar;
import java.util.List;

import br.com.cliente_crud.entity.Jogo;
import br.com.cliente_crud.entity.Utilizacao;
import br.com.cliente_crud.relatorio.RelatorioPerfilCliente;
import br.com.cliente_crud.relatorio.RelatorioPerfilClientela;

public interface UtilizacaoDAO {

	/**
	 * Listar Jogos pela plataforma
	 * 
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
	public RelatorioPerfilClientela gerarRelatorioFaturamentoComparativoClienteNovo(
			Calendar dataParam);

	/**
	 * @return
	 */
	public RelatorioPerfilClientela gerarRelatorioFaturamentoComparativoClienteAntigo(
			Calendar dataParam);

	/**
	 * @return
	 */
	public List<RelatorioPerfilClientela> gerarRelatorioJogosMaisUtilizadosPorCliente(
			Integer idCliente);

	/**
	 * @return
	 */
	public List<RelatorioPerfilClientela> gerarRelatorioUtilizacaoPorEvento(
			Integer idCliente);

	/**
	 * @param idCliente
	 * @return
	 */
	public List<RelatorioPerfilCliente> consultarGenerosMaisUtilizados(
			Integer idCliente);
	
	/**
	 * @param idCliente
	 * @return
	 */
	public List<RelatorioPerfilCliente> consultarGenerosMaisUtilizadosPelaClientela();

	/**
	 * @param genero1
	 * @param genero2
	 * @param genero3
	 * @return
	 */
	public List<RelatorioPerfilCliente> gerarRelatorioJogosMaisUtilizadosPorGeneros(
			Integer genero1, Integer genero2, Integer genero3, Integer idCliente, Integer faixaEtaria);
		
	/**
	 * @param listaNomeJogos
	 * @param faixaEtaria
	 * @return
	 */
	public List<RelatorioPerfilCliente> consultarJogosLançamentos(List<String> listaNomeJogos, Integer faixaEtaria);
}
