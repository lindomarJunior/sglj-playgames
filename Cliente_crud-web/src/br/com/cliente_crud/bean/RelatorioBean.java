package br.com.cliente_crud.bean;

import java.io.Serializable;
import java.util.Calendar;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.CartesianChartModel;
import org.primefaces.model.chart.CategoryAxis;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.LineChartModel;
import org.primefaces.model.chart.LineChartSeries;

import br.com.cliente_crud.entity.Cliente;
import br.com.cliente_crud.relatorio.RelatorioPerfilCliente;
import br.com.cliente_crud.relatorio.RelatorioPerfilClientela;
import br.com.cliente_crud.service.UtilizacaoService;

@RequestScoped
@Named
public class RelatorioBean implements Serializable {

	@EJB
	private UtilizacaoService utilizacaoService;
	private List<RelatorioPerfilClientela> relatorioJogosMaisUtilizados;
	private List<RelatorioPerfilClientela> relatorioPlataformasMaisUtilizados;
	private List<RelatorioPerfilClientela> relatorioRankingUtilizacao;
	private List<RelatorioPerfilCliente> relatorioSugestaoJogos;
	private LineChartModel lineModel1;
	private LineChartModel lineModel2;
	private Cliente cliente;

	/**
	 * 
	 */
	public void gerarPerfilClientela() {
		/*
		 * gerarRelatorioJogosMaisUtilizados();
		 * gerarRelatorioPlataformasMaisUtilizadas();
		 * gerarRelatorioRankingUtilizacao();
		 */
		gerarRelatorioFaturamento();
	}

	/**
	 * 
	 */
	public void gerarPerfilCliente() {
		gerarJogosMaisUtilizadosPorCliente();
		gerarUtilizacaoPorEvento();
		sugerirJogos();
	}

	/**
	 * 
	 */
	private void gerarRelatorioJogosMaisUtilizados() {
		setRelatorioJogosMaisUtilizados(utilizacaoService
				.gerarRelatorioJogosMaisUtilizados());
	}

	/**
	 * 
	 */
	private void gerarRelatorioPlataformasMaisUtilizadas() {
		setRelatorioPlataformasMaisUtilizados(utilizacaoService
				.gerarRelatorioPlataformasMaisUtilizadas());
	}

	/**
	 * 
	 */
	private void gerarRelatorioRankingUtilizacao() {
		setRelatorioRankingUtilizacao(utilizacaoService
				.gerarRelatorioRankingUtilizacao());
	}

	/**
	 * 
	 */
	private void gerarRelatorioFaturamento() {
		utilizacaoService.gerarRelatorioFaturamento(Calendar.getInstance(),
				Calendar.getInstance());
	}

	/**
	 * 
	 */
	private void gerarJogosMaisUtilizadosPorCliente() {
		setRelatorioJogosMaisUtilizados(utilizacaoService
				.gerarRelatorioJogosMaisUtilizadosPorCliente(getCliente()
						.getId()));
	}

	/**
	 * 
	 */
	private void gerarUtilizacaoPorEvento() {
		setRelatorioPlataformasMaisUtilizados(utilizacaoService
				.gerarRelatorioUtilizacaoPorEvento(getCliente().getId()));
	}

	private void sugerirJogos() {
		setRelatorioSugestaoJogos(utilizacaoService.sugerirJogos(getCliente()));
	}

	@PostConstruct
	public void init() {
		createLineModels();
	}

	public LineChartModel getLineModel1() {
		return lineModel1;
	}

	public LineChartModel getLineModel2() {
		return lineModel2;
	}

	private void createLineModels() {
		lineModel1 = initLinearModel();
		lineModel1.setTitle("Linear Chart");
		lineModel1.setLegendPosition("e");
		Axis yAxis = lineModel1.getAxis(AxisType.Y);
		yAxis.setMin(0);
		yAxis.setMax(10);

		lineModel2 = initCategoryModel();
		lineModel2.setLegendPosition("e");
		lineModel2.setShowPointLabels(true);
		lineModel2.getAxes().put(AxisType.X, new CategoryAxis("Years"));
		yAxis = lineModel2.getAxis(AxisType.Y);
		yAxis.setLabel("Births");
		yAxis.setMin(0);
		yAxis.setMax(200);
	}

	private LineChartModel initLinearModel() {
		LineChartModel model = new LineChartModel();

		LineChartSeries series1 = new LineChartSeries();
		series1.setLabel("Series 1");

		series1.set(1, 2);
		series1.set(2, 1);
		series1.set(3, 3);
		series1.set(4, 6);
		series1.set(5, 8);

		LineChartSeries series2 = new LineChartSeries();
		series2.setLabel("Series 2");

		series2.set(1, 6);
		series2.set(2, 3);
		series2.set(3, 2);
		series2.set(4, 7);
		series2.set(5, 9);

		model.addSeries(series1);
		model.addSeries(series2);

		return model;
	}

	private LineChartModel initCategoryModel() {
		LineChartModel model = new LineChartModel();

		ChartSeries boys = new ChartSeries();
		boys.setLabel("Boys");
		boys.set("2004", 120);
		boys.set("2005", 100);
		boys.set("2006", 44);
		boys.set("2007", 150);
		boys.set("2008", 25);

		ChartSeries girls = new ChartSeries();
		girls.setLabel("Girls");
		girls.set("2004", 52);
		girls.set("2005", 60);
		girls.set("2006", 110);
		girls.set("2007", 90);
		girls.set("2008", 120);

		model.addSeries(boys);
		model.addSeries(girls);

		return model;
	}

	// /////////////////////////////////////////////////////////////////////
	public List<RelatorioPerfilClientela> getRelatorioJogosMaisUtilizados() {
		return relatorioJogosMaisUtilizados;
	}

	public void setRelatorioJogosMaisUtilizados(
			List<RelatorioPerfilClientela> relatorioJogosMaisUtilizados) {
		this.relatorioJogosMaisUtilizados = relatorioJogosMaisUtilizados;
	}

	public List<RelatorioPerfilClientela> getRelatorioPlataformasMaisUtilizados() {
		return relatorioPlataformasMaisUtilizados;
	}

	public void setRelatorioPlataformasMaisUtilizados(
			List<RelatorioPerfilClientela> relatorioPlataformasMaisUtilizados) {
		this.relatorioPlataformasMaisUtilizados = relatorioPlataformasMaisUtilizados;
	}

	public List<RelatorioPerfilClientela> getRelatorioRankingUtilizacao() {
		return relatorioRankingUtilizacao;
	}

	public void setRelatorioRankingUtilizacao(
			List<RelatorioPerfilClientela> relatorioRankingUtilizacao) {
		this.relatorioRankingUtilizacao = relatorioRankingUtilizacao;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public List<RelatorioPerfilCliente> getRelatorioSugestaoJogos() {
		return relatorioSugestaoJogos;
	}

	public void setRelatorioSugestaoJogos(
			List<RelatorioPerfilCliente> relatorioSugestaoJogos) {
		this.relatorioSugestaoJogos = relatorioSugestaoJogos;
	}

}
