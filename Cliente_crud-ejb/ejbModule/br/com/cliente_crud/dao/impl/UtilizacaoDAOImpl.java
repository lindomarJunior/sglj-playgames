package br.com.cliente_crud.dao.impl;

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
	public List<RelatorioPerfilClientela> gerarRelatorioJogosMaisUtilizados() {
		String hql = "SELECT new br.com.cliente_crud.relatorio.RelatorioPerfilClientela(jogo.titulo, jogo.plataforma.nome, SUM(utilizacao_jogo.qtdTempoUtilizacao)) "
				+ "FROM UtilizacaoJogo as utilizacao_jogo, Jogo as jogo "
				+ "WHERE jogo.id = utilizacao_jogo.idJogo "
				+ "GROUP by jogo.titulo, jogo.plataforma.nome";
		Query query = entityManager.createQuery(hql);
		return (List<RelatorioPerfilClientela>) query.getResultList();
	}

	@Override
	public List<RelatorioPerfilClientela> gerarRelatorioPlataformasMaisUtilizadas() {
		String hql = "SELECT new br.com.cliente_crud.relatorio.RelatorioPerfilClientela(util.videogame.plataforma.nome, SUM(util.qtdTempoUtilizado)) "
				+ "FROM Utilizacao as util "
				+ "GROUP BY util.videogame.plataforma.nome";
		Query query = entityManager.createQuery(hql);
		return (List<RelatorioPerfilClientela>) query.getResultList();
	}

	@Override
	public List<RelatorioPerfilClientela> gerarRelatorioRankingUtilizacao() {
		String hql = "SELECT new br.com.cliente_crud.relatorio.RelatorioPerfilClientela(utilizacao.cliente.nome, SUM(utilizacao.qtdTempoUtilizado), SUM(utilizacao.valor)) "
				+ "FROM Utilizacao as utilizacao "
				+ "GROUP BY utilizacao.cliente.nome";
		Query query = entityManager.createQuery(hql);
		return (List<RelatorioPerfilClientela>) query.getResultList();
	}

	@Override
	public RelatorioPerfilClientela gerarRelatorioFaturamentoComparativoClienteNovo(
			Integer mes) {
		String hql = "SELECT new br.com.cliente_crud.relatorio.RelatorioPerfilClientela(SUM(utilizacao.valor)) "
				+ "FROM Utilizacao as utilizacao "
				+ "WHERE MONTH(utilizacao.horaInicio) = :mes AND MONTH(utilizacao.cliente.dataRegistro) = :mes";
		Query query = entityManager.createQuery(hql);
		query.setParameter("mes", mes);

		try {
			return (RelatorioPerfilClientela) query.getSingleResult();
		} catch (Exception e) {
			return null;
		}

	}

	@Override
	public RelatorioPerfilClientela gerarRelatorioFaturamentoComparativoClienteAntigo(
			Integer mes) {
		String hql = "SELECT new br.com.cliente_crud.relatorio.RelatorioPerfilClientela(SUM(utilizacao.valor)) "
				+ "FROM Utilizacao as utilizacao "
				+ "WHERE MONTH(utilizacao.horaInicio) = :mes AND MONTH(utilizacao.cliente.dataRegistro) < :mes";
		Query query = entityManager.createQuery(hql);
		query.setParameter("mes", mes);
		return (RelatorioPerfilClientela) query.getSingleResult();
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
	public List<RelatorioPerfilCliente> consultarJogosLançamentos(List<String> listaNomeJogos, Integer faixaEtaria) {
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
