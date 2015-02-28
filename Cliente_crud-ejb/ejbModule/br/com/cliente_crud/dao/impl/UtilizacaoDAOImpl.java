package br.com.cliente_crud.dao.impl;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.cliente_crud.dao.UtilizacaoDAO;
import br.com.cliente_crud.entity.Jogo;
import br.com.cliente_crud.entity.Utilizacao;
import br.com.cliente_crud.relatorio.RelatorioPerfilCliente;
import br.com.cliente_crud.relatorio.RelatorioPerfilClientela;

public class UtilizacaoDAOImpl implements UtilizacaoDAO {

	protected EntityManager entityManager;

	public UtilizacaoDAOImpl(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	public List<Jogo> listarJogoPorPlataforma(Integer idPlataforma) {
		String hql = "FROM Jogo as jogo WHERE jogo.plataforma.id = :idPlataforma";
		Query query = entityManager.createQuery(hql);
		return (List<Jogo>) query.setParameter("idPlataforma", idPlataforma)
				.getResultList();
	}

	@Override
	public List<Utilizacao> listarUtilizacaoEspera(Integer status) {
		String hql = "FROM Utilizacao as util WHERE util.statusEspera = :status";
		Query query = entityManager.createQuery(hql);
		return (List<Utilizacao>) query.setParameter("status", status)
				.getResultList();
	}

	@Override
	public List<Utilizacao> listarUtilizacaoAndamento(Integer status) {
		String hql = "FROM Utilizacao as util WHERE util.statusAtivo = :status";
		Query query = entityManager.createQuery(hql);
		return (List<Utilizacao>) query.setParameter("status", status)
				.getResultList();
	}

	@Override
	public List<RelatorioPerfilClientela> gerarRelatorioJogosMaisUtilizados(
			Calendar dataInicial, Calendar dataFinal) {
		String hql = "SELECT new br.com.cliente_crud.relatorio.RelatorioPerfilClientela(jogo.titulo, jogo.plataforma.nome, SUM(utilizacao_jogo.qtdTempoUtilizacao)) "
				+ "FROM UtilizacaoJogo as utilizacao_jogo, Jogo as jogo, Utilizacao as utilizacao "
				+ "WHERE jogo.id = utilizacao_jogo.idJogo AND utilizacao.id = utilizacao_jogo.idUtilizacao "
				+ "AND utilizacao.horaTermino BETWEEN :dataInicial AND :dataFinal "
				+ "GROUP by jogo.titulo, jogo.plataforma.nome "
				+ "ORDER BY SUM(utilizacao_jogo.qtdTempoUtilizacao) DESC LIMIT 10";
		Query query = entityManager.createQuery(hql);
		query.setParameter("dataInicial", dataInicial);
		query.setParameter("dataFinal", dataFinal);
		return (List<RelatorioPerfilClientela>) query.getResultList();
	}

	@Override
	public List<RelatorioPerfilClientela> gerarRelatorioPlataformasMaisUtilizadas(
			Calendar dataInicial, Calendar dataFinal) {
		String hql = "SELECT new br.com.cliente_crud.relatorio.RelatorioPerfilClientela(util.videogame.plataforma.nome, SUM(util.qtdTempoUtilizado)) "
				+ "FROM Utilizacao as util "
				+ "WHERE util.horaTermino BETWEEN :dataInicial AND :dataFinal "
				+ "GROUP BY util.videogame.plataforma.nome "
				+ "ORDER BY SUM(util.qtdTempoUtilizado) DESC LIMIT 10";
		Query query = entityManager.createQuery(hql);
		query.setParameter("dataInicial", dataInicial);
		query.setParameter("dataFinal", dataFinal);
		return (List<RelatorioPerfilClientela>) query.getResultList();
	}

	@Override
	public List<RelatorioPerfilClientela> gerarRelatorioRankingUtilizacao(
			Calendar dataInicial, Calendar dataFinal) {
		String hql = "SELECT new br.com.cliente_crud.relatorio.RelatorioPerfilClientela(utilizacao.cliente.nome, SUM(utilizacao.qtdTempoUtilizado), SUM(utilizacao.valor)) "
				+ "FROM Utilizacao as utilizacao "
				+ "WHERE utilizacao.valor is not null "
				+ "AND utilizacao.horaTermino BETWEEN :dataInicial AND :dataFinal "
				+ "GROUP BY utilizacao.cliente.nome";
		Query query = entityManager.createQuery(hql);
		query.setParameter("dataInicial", dataInicial);
		query.setParameter("dataFinal", dataFinal);
		return (List<RelatorioPerfilClientela>) query.getResultList();
	}

	@Override
	public RelatorioPerfilClientela gerarRelatorioFaturamentoComparativoClienteNovo(Calendar dataParam) {
		Calendar data = Calendar.getInstance();
		data.setTime(dataParam.getTime());
		data.set(Calendar.DAY_OF_MONTH, 1);
		SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
		String dataInicial = format1.format(data.getTime());
		data.add(Calendar.MONTH, 1);
		String dataFinal = format1.format(data.getTime());
		
		String hql = "SELECT new br.com.cliente_crud.relatorio.RelatorioPerfilClientela(SUM(utilizacao.valor)) "
				+ "FROM Utilizacao as utilizacao "
				+ "WHERE utilizacao.horaInicio Between str_to_date(:dataInicial,'%Y-%m-%d') AND str_to_date(:dataFinal,'%Y-%m-%d') "
				+ "AND utilizacao.cliente.dataRegistro Between str_to_date(:dataInicial,'%Y-%m-%d') AND str_to_date(:dataFinal,'%Y-%m-%d')";
		Query query = entityManager.createQuery(hql);
		query.setParameter("dataInicial", dataInicial);
		query.setParameter("dataFinal", dataFinal);
		try {
			return (RelatorioPerfilClientela) query.getSingleResult();
		} catch (Exception e) {
			return null;
		}

	}

	@Override
	public RelatorioPerfilClientela gerarRelatorioFaturamentoComparativoClienteAntigo(Calendar dataParam) {
		Calendar data = Calendar.getInstance();
		data.setTime(dataParam.getTime());
		data.set(Calendar.DAY_OF_MONTH, 1);
		SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
		String dataInicial = format1.format(data.getTime());
		data.add(Calendar.MONTH, 1);
		String dataFinal = format1.format(data.getTime());
		
		String hql = "SELECT new br.com.cliente_crud.relatorio.RelatorioPerfilClientela(SUM(utilizacao.valor)) "
				+ "FROM Utilizacao as utilizacao "
				+ "WHERE utilizacao.horaInicio Between str_to_date(:dataInicial,'%Y-%m-%d') AND str_to_date(:dataFinal,'%Y-%m-%d') "
				+ "AND utilizacao.cliente.dataRegistro < str_to_date(:dataInicial,'%Y-%m-%d')";
		Query query = entityManager.createQuery(hql);
		query.setParameter("dataInicial", dataInicial);
		query.setParameter("dataFinal", dataFinal);
		try {
			return (RelatorioPerfilClientela) query.getSingleResult();
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public List<RelatorioPerfilClientela> gerarRelatorioJogosMaisUtilizadosPorCliente(
			Integer idCliente) {
		String hql = "SELECT new br.com.cliente_crud.relatorio.RelatorioPerfilClientela(jogo.titulo, jogo.plataforma.nome, SUM(utilizacao_jogo.qtdTempoUtilizacao)) "
				+ "FROM UtilizacaoJogo as utilizacao_jogo, Jogo as jogo, Utilizacao as utilizacao "
				+ "WHERE jogo.id = utilizacao_jogo.idJogo AND utilizacao.id = utilizacao_jogo.idUtilizacao AND utilizacao.cliente.id = :idCliente "
				+ "GROUP by jogo.titulo, jogo.plataforma.nome "
				+ "ORDER BY SUM(utilizacao_jogo.qtdTempoUtilizacao) DESC";
		Query query = entityManager.createQuery(hql);
		query.setParameter("idCliente", idCliente);
		return (List<RelatorioPerfilClientela>) query.getResultList();
	}

	@Override
	public List<RelatorioPerfilClientela> gerarRelatorioUtilizacaoPorEvento(
			Integer idCliente) {
		String hql = "SELECT new br.com.cliente_crud.relatorio.RelatorioPerfilClientela(util.evento.nome, SUM(util.qtdTempoUtilizado)) "
				+ "FROM Utilizacao as util "
				+ "WHERE util.cliente.id = :idCliente "
				+ "GROUP BY util.evento.nome "
				+ "ORDER BY SUM(util.qtdTempoUtilizado) DESC";
		Query query = entityManager.createQuery(hql);
		query.setParameter("idCliente", idCliente);
		return (List<RelatorioPerfilClientela>) query.getResultList();
	}

	@Override
	public List<RelatorioPerfilCliente> consultarGenerosMaisUtilizados(
			Integer idCliente) {
		String hql = "SELECT new br.com.cliente_crud.relatorio.RelatorioPerfilCliente(jogo.genero) "
				+ "FROM UtilizacaoJogo as utilizacao_jogo, Jogo as jogo, Utilizacao as utilizacao "
				+ "WHERE jogo.id = utilizacao_jogo.idJogo AND utilizacao.id = utilizacao_jogo.idUtilizacao AND utilizacao.cliente.id = :idCliente "
				+ "GROUP by jogo.genero "
				+ "ORDER BY SUM(utilizacao_jogo.qtdTempoUtilizacao) DESC LIMIT 3";
		Query query = entityManager.createQuery(hql);
		query.setParameter("idCliente", idCliente);
		return (List<RelatorioPerfilCliente>) query.getResultList();
	}

	@Override
	public List<RelatorioPerfilCliente> consultarGenerosMaisUtilizadosPelaClientela() {
		String hql = "SELECT new br.com.cliente_crud.relatorio.RelatorioPerfilCliente(jogo.genero) "
				+ "FROM UtilizacaoJogo as utilizacao_jogo, Jogo as jogo "
				+ "WHERE jogo.id = utilizacao_jogo.idJogo "
				+ "GROUP by jogo.genero "
				+ "ORDER BY SUM(utilizacao_jogo.qtdTempoUtilizacao) DESC LIMIT 3";
		Query query = entityManager.createQuery(hql);
		return (List<RelatorioPerfilCliente>) query.getResultList();
	}

	@Override
	public List<RelatorioPerfilCliente> gerarRelatorioJogosMaisUtilizadosPorGeneros(
			Integer genero1, Integer genero2, Integer genero3,
			Integer idCliente, Integer faixaEtaria) {
		String hql = "SELECT new br.com.cliente_crud.relatorio.RelatorioPerfilCliente(jogo.titulo, jogo.plataforma.nome, SUM(utilizacao_jogo.qtdTempoUtilizacao)) "
				+ "FROM UtilizacaoJogo as utilizacao_jogo, Jogo as jogo, Utilizacao as utilizacao "
				+ "WHERE jogo.id = utilizacao_jogo.idJogo AND utilizacao.id = utilizacao_jogo.idUtilizacao "
				+ "AND jogo.genero in (:genero1, :genero2, :genero3) AND utilizacao.cliente.id != :idCliente AND jogo.faixaEtaria <= :faixaEtaria "
				+ "GROUP by jogo.titulo, jogo.plataforma.nome "
				+ "ORDER BY SUM(utilizacao_jogo.qtdTempoUtilizacao) DESC LIMIT 10";
		Query query = entityManager.createQuery(hql);
		query.setParameter("genero1", genero1);
		query.setParameter("genero2", genero2);
		query.setParameter("genero3", genero3);
		query.setParameter("idCliente", idCliente);
		query.setParameter("faixaEtaria", faixaEtaria);
		return (List<RelatorioPerfilCliente>) query.getResultList();
	}

	@Override
	public List<RelatorioPerfilCliente> consultarJogosLançamentos(
			List<String> listaNomeJogos, Integer faixaEtaria) {
		String hql = "SELECT new br.com.cliente_crud.relatorio.RelatorioPerfilCliente(jogo.titulo, jogo.plataforma.nome) "
				+ "FROM Jogo as jogo "
				+ "WHERE jogo.titulo not in(:listaNomeJogos) AND jogo.faixaEtaria <= :faixaEtaria "
				+ "ORDER BY jogo.dataLancamento DESC LIMIT 10";
		Query query = entityManager.createQuery(hql);
		query.setParameter("listaNomeJogos", listaNomeJogos);
		query.setParameter("faixaEtaria", faixaEtaria);
		return (List<RelatorioPerfilCliente>) query.getResultList();
	}
}
