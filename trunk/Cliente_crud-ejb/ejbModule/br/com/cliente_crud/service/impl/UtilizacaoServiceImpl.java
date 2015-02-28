package br.com.cliente_crud.service.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.cliente_crud.dao.impl.DAOImpl;
import br.com.cliente_crud.dao.impl.UtilizacaoDAOImpl;
import br.com.cliente_crud.entity.Cliente;
import br.com.cliente_crud.entity.Jogo;
import br.com.cliente_crud.entity.Utilizacao;
import br.com.cliente_crud.relatorio.RelatorioPerfilCliente;
import br.com.cliente_crud.relatorio.RelatorioPerfilClientela;
import br.com.cliente_crud.service.UtilizacaoService;
import br.com.cliente_crud.util.UtilDate;

@Stateless
public class UtilizacaoServiceImpl implements UtilizacaoService {

	private DAOImpl<Utilizacao, Integer> daoGenerico;
	private UtilizacaoDAOImpl utilizacaoDAO;

	@PersistenceContext
	protected EntityManager entityManager;

	@Override
	public void incluir(Utilizacao to) throws SQLException {

		daoGenerico = new DAOImpl<Utilizacao, Integer>(entityManager);
		daoGenerico.incluir(to);
	}

	@Override
	public void excluir(Utilizacao to) {
		daoGenerico = new DAOImpl<Utilizacao, Integer>(entityManager);
		daoGenerico.excluir(to);
	}

	@Override
	public Utilizacao consultar(Class<Utilizacao> classe, Integer pk) {
		daoGenerico = new DAOImpl<Utilizacao, Integer>(entityManager);
		return daoGenerico.consultar(classe, pk);
	}

	@Override
	public List<Utilizacao> listar(Class<Utilizacao> classe) {

		daoGenerico = new DAOImpl<Utilizacao, Integer>(entityManager);
		return (List<Utilizacao>) daoGenerico.Listar(classe);
	}

	@Override
	public void atualizar(Utilizacao to) throws SQLException {
		daoGenerico = new DAOImpl<Utilizacao, Integer>(entityManager);
		daoGenerico.atualizar(to);

	}

	@Override
	public List<Jogo> consultarJogoPorPlataforma(Integer idPlataforma) {
		utilizacaoDAO = new UtilizacaoDAOImpl(entityManager);
		return utilizacaoDAO.listarJogoPorPlataforma(idPlataforma);
	}

	@Override
	public List<Utilizacao> listarUtilizacaoEspera(Integer status) {
		utilizacaoDAO = new UtilizacaoDAOImpl(entityManager);
		return utilizacaoDAO.listarUtilizacaoEspera(status);
	}

	@Override
	public List<Utilizacao> listarUtilizacaoAndamento(Integer status) {
		utilizacaoDAO = new UtilizacaoDAOImpl(entityManager);
		return utilizacaoDAO.listarUtilizacaoAndamento(status);
	}

	@Override
	public List<RelatorioPerfilClientela> gerarRelatorioJogosMaisUtilizados(
			Calendar dataInicial, Calendar dataFinal) {
		utilizacaoDAO = new UtilizacaoDAOImpl(entityManager);
		return utilizacaoDAO.gerarRelatorioJogosMaisUtilizados(dataInicial,
				dataFinal);
	}

	@Override
	public List<RelatorioPerfilClientela> gerarRelatorioPlataformasMaisUtilizadas(
			Calendar dataInicial, Calendar dataFinal) {
		utilizacaoDAO = new UtilizacaoDAOImpl(entityManager);
		return utilizacaoDAO.gerarRelatorioPlataformasMaisUtilizadas(
				dataInicial, dataFinal);
	}

	@Override
	public List<RelatorioPerfilClientela> gerarRelatorioRankingUtilizacao(
			Calendar dataInicial, Calendar dataFinal) {
		utilizacaoDAO = new UtilizacaoDAOImpl(entityManager);
		List<RelatorioPerfilClientela> listaRelatorioPerfilClientela = utilizacaoDAO
				.gerarRelatorioRankingUtilizacao(dataInicial, dataFinal);
		return listaRelatorioPerfilClientela;
	}

	@Override
	public List<RelatorioPerfilClientela> gerarRelatorioFaturamento(
			Calendar dataInicio, Calendar dataFinal) {
		utilizacaoDAO = new UtilizacaoDAOImpl(entityManager);
		List<RelatorioPerfilClientela> listaRelatorioPerfilClientela = new ArrayList<RelatorioPerfilClientela>();
		RelatorioPerfilClientela relatorioPerfilClientela;

		dataInicio.set(Calendar.DAY_OF_MONTH, 1);
		dataFinal.set(Calendar.DAY_OF_MONTH, 2);


		while (dataInicio.before(dataFinal)) {

			// Busca faturamento novos clientes
			relatorioPerfilClientela = utilizacaoDAO
					.gerarRelatorioFaturamentoComparativoClienteNovo(dataInicio);
			// verifica se faturamento veio null
			if (relatorioPerfilClientela == null) {
				relatorioPerfilClientela = new RelatorioPerfilClientela();
				relatorioPerfilClientela.setFaturamentoNovosClientes((float) 0);
			}
			// Busca faturamento velhos clientes
			Float faturamentoClienteAntigo = utilizacaoDAO
					.gerarRelatorioFaturamentoComparativoClienteAntigo(
							dataInicio).getFaturamentoAntigosClientes();
			// Verifica se faturamento veio nulo
			if (faturamentoClienteAntigo == null) {
				faturamentoClienteAntigo = new Float(0);
			}
			relatorioPerfilClientela
					.setFaturamentoAntigosClientes(faturamentoClienteAntigo);

			relatorioPerfilClientela.setMes(new Integer(dataInicio.get(Calendar.MONTH)+1));
			listaRelatorioPerfilClientela.add(relatorioPerfilClientela);
			dataInicio.add(Calendar.MONTH, 1);
		}

		return listaRelatorioPerfilClientela;
		
	}

	@Override
	public List<RelatorioPerfilClientela> gerarRelatorioJogosMaisUtilizadosPorCliente(
			Integer idCliente) {
		utilizacaoDAO = new UtilizacaoDAOImpl(entityManager);
		return utilizacaoDAO
				.gerarRelatorioJogosMaisUtilizadosPorCliente(idCliente);
	}

	@Override
	public List<RelatorioPerfilClientela> gerarRelatorioUtilizacaoPorEvento(
			Integer idCliente) {
		utilizacaoDAO = new UtilizacaoDAOImpl(entityManager);
		return utilizacaoDAO.gerarRelatorioUtilizacaoPorEvento(idCliente);
	}

	@Override
	public List<RelatorioPerfilCliente> sugerirJogos(Cliente cliente) {
		List<RelatorioPerfilCliente> listaRelatorioPerfilCliente;

		utilizacaoDAO = new UtilizacaoDAOImpl(entityManager);

		// verifica os 3 generos mais utilizados pelo cliente
		listaRelatorioPerfilCliente = utilizacaoDAO
				.consultarGenerosMaisUtilizados(cliente.getId());

		// caso o cliente n�o tenha 3 generos mais utilizados
		if (listaRelatorioPerfilCliente.size() < 3) {
			List<RelatorioPerfilCliente> listaRelatorioPerfilClientela = utilizacaoDAO
					.consultarGenerosMaisUtilizadosPelaClientela();
			for (RelatorioPerfilCliente relatorioPerfilClientela : listaRelatorioPerfilClientela) {
				if (listaRelatorioPerfilCliente.size() == 3) {
					break;
				} else {
					listaRelatorioPerfilCliente.add(relatorioPerfilClientela);
				}
			}
		}

		// gera sugest�o de jogos5
		listaRelatorioPerfilCliente = utilizacaoDAO
				.gerarRelatorioJogosMaisUtilizadosPorGeneros(
						listaRelatorioPerfilCliente.get(0).getGenero(),
						listaRelatorioPerfilCliente.get(1).getGenero(),
						listaRelatorioPerfilCliente.get(2).getGenero(),
						cliente.getId(),
						UtilDate.calcularIdade(cliente.getDataNasc()));

		// Caso a lista n�o conter 10 jogos
		if (listaRelatorioPerfilCliente.size() < 10) {

			List<String> listaNomeJogos = new ArrayList<String>();

			// Popula lista de jogos que n�o devem ser consultados
			for (RelatorioPerfilCliente relatorioPerfilCliente : listaRelatorioPerfilCliente) {
				listaNomeJogos.add(relatorioPerfilCliente.getJogo());
			}

			// consulta Jogos lan�amentos passando como parametro os jogos que
			// n�o devem ser consultados
			List<RelatorioPerfilCliente> listaJogosLancamentos = utilizacaoDAO
					.consultarJogosLan�amentos(listaNomeJogos,
							UtilDate.calcularIdade(cliente.getDataNasc()));

			// popula a lista de jogos sugeridos at� que tenham 10 jogos
			for (RelatorioPerfilCliente JogoLancamento : listaJogosLancamentos) {
				if (listaRelatorioPerfilCliente.size() == 10) {
					break;
				} else {
					listaRelatorioPerfilCliente.add(JogoLancamento);
				}
			}
		}

		return listaRelatorioPerfilCliente;
	}
}
