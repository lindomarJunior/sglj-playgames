package br.com.cliente_crud.bean;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.CategoryAxis;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.HorizontalBarChartModel;
import org.primefaces.model.chart.LineChartModel;

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
	private List<RelatorioPerfilClientela> relatorioFaturamentoComparativo;
	private LineChartModel lineModel2;
	private Cliente cliente;
	private Date dataInicial;
	private Date dataFinal;
	private HorizontalBarChartModel graficoJogosMaisUtilizado;
	private HorizontalBarChartModel graficoPlataformasMaisUtilizado;
	private String relatorio;

	@PostConstruct
	public void init() {

		if (dataInicial != null) {
			setarDatas();
		}

		try {
			gerarPerfilClientela();
		} catch (Exception e) {
			// TODO: handle exception
		}

		try {

			relatorio = recuperarRelatorio();
			gerarGrafico(relatorio);
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	/**
	 * 
	 */
	public void gerarPerfilClientela() {
		Calendar dataInicial = Calendar.getInstance();
		Calendar dataFinal = Calendar.getInstance();

		dataFinal.getTime();

		dataInicial.setTime(recuperarDatas("dataInicial"));
		dataFinal.setTime(recuperarDatas("dataFinal"));
		dataFinal.set(Calendar.HOUR, 23);
		dataFinal.set(Calendar.MINUTE, 59);
		dataFinal.set(Calendar.SECOND, 59);

		gerarRelatorioJogosMaisUtilizados(dataInicial, dataFinal);
		gerarRelatorioPlataformasMaisUtilizadas(dataInicial, dataFinal);
		gerarRelatorioRankingUtilizacao(dataInicial, dataFinal);
		gerarRelatorioFaturamento(dataInicial, dataFinal);
		createLineModels();
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
	 * @param relatorio
	 */
	public void escolherGrafico(String relatorio) {
		FacesContext fc = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) fc.getExternalContext().getSession(
				false);
		session.setAttribute("relatorio", relatorio);
	}

	/**
	 * @return
	 */
	private String recuperarRelatorio() {
		FacesContext context = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) context.getExternalContext()
				.getSession(false);
		String relatorio = (String) session.getAttribute("relatorio");
		return relatorio;
	}

	/**
	 * 
	 */
	private void setarDatas() {
		FacesContext fc = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) fc.getExternalContext().getSession(
				false);
		session.setAttribute("dataInicial", dataInicial);
		session.setAttribute("dataFinal", dataFinal);
	}

	/**
	 * @param data
	 * @return
	 */
	private Date recuperarDatas(String data) {
		FacesContext context = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) context.getExternalContext()
				.getSession(false);
		Date dataRetorno = (Date) session.getAttribute(data);
		return dataRetorno;
	}

	private void gerarGrafico(String relatorio) {
		if (relatorio != null) {
			if (relatorio.equals("jogos")) {
				createGraficoJogosMaisUtilizado();
			} else if (relatorio.equals("plataformas")) {
				createGraficoPlataformasMaisUtilizado();
			}
		}
	}

	/**
	 * 
	 */
	private void gerarRelatorioJogosMaisUtilizados(Calendar dataInicial,
			Calendar dataFinal) {
		setRelatorioJogosMaisUtilizados(utilizacaoService
				.gerarRelatorioJogosMaisUtilizados(dataInicial, dataFinal));
		
		try {
			gerarRelatorioIreport(getRelatorioJogosMaisUtilizados());
		} catch (JRException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 
	 */
	private void gerarRelatorioPlataformasMaisUtilizadas(Calendar dataInicial,
			Calendar dataFinal) {
		setRelatorioPlataformasMaisUtilizados(utilizacaoService
				.gerarRelatorioPlataformasMaisUtilizadas(dataInicial, dataFinal));
	}

	/**
	 * 
	 */
	private void gerarRelatorioRankingUtilizacao(Calendar dataInicial,
			Calendar dataFinal) {
		setRelatorioRankingUtilizacao(utilizacaoService
				.gerarRelatorioRankingUtilizacao(dataInicial, dataFinal));
	}

	/**
	 * 
	 */
	private void gerarRelatorioFaturamento(Calendar dataInicial,
			Calendar dataFinal) {
		setRelatorioFaturamentoComparativo(utilizacaoService
				.gerarRelatorioFaturamento(dataInicial, dataFinal));
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

	/**
	 * 
	 */
	private void sugerirJogos() {
		setRelatorioSugestaoJogos(utilizacaoService.sugerirJogos(getCliente()));
	}

	/**
	 * Grafico jogos mais utilizados
	 */
	private void createGraficoJogosMaisUtilizado() {
		graficoJogosMaisUtilizado = new HorizontalBarChartModel();
		ChartSeries relatorio = new ChartSeries();

		for (RelatorioPerfilClientela rel : getRelatorioJogosMaisUtilizados()) {
			relatorio.set(rel.getJogo(), rel.getTempo());
		}

		graficoJogosMaisUtilizado.addSeries(relatorio);
		graficoJogosMaisUtilizado.setStacked(true);
		Axis xAxis = graficoJogosMaisUtilizado.getAxis(AxisType.X);
		xAxis.setLabel("Horas");
		xAxis.setMin(0);
		Axis yAxis = graficoJogosMaisUtilizado.getAxis(AxisType.Y);
		yAxis.setLabel("Jogo");
	}

	/**
	 * Grafico plataformas mais utilizados
	 */
	private void createGraficoPlataformasMaisUtilizado() {
		graficoPlataformasMaisUtilizado = new HorizontalBarChartModel();
		ChartSeries relatorio = new ChartSeries();

		for (RelatorioPerfilClientela rel : getRelatorioPlataformasMaisUtilizados()) {
			relatorio.set(rel.getPlataforma(), rel.getTempo());
		}

		graficoPlataformasMaisUtilizado.addSeries(relatorio);
		graficoPlataformasMaisUtilizado.setStacked(true);
		Axis xAxis = graficoPlataformasMaisUtilizado.getAxis(AxisType.X);
		xAxis.setLabel("Horas");
		xAxis.setMin(0);
		Axis yAxis = graficoPlataformasMaisUtilizado.getAxis(AxisType.Y);
		yAxis.setLabel("Plataforma");
	}

	public LineChartModel getLineModel2() {
		return lineModel2;
	}

	private void createLineModels() {

		lineModel2 = initCategoryModel();
		lineModel2.setLegendPosition("e");
		lineModel2.setShowPointLabels(true);
		lineModel2.getAxes().put(AxisType.X, new CategoryAxis("Mês"));
		Axis yAxis = lineModel2.getAxis(AxisType.Y);
		yAxis = lineModel2.getAxis(AxisType.Y);
		yAxis.setLabel("Faturamento R$");
		yAxis.setMin(0);
		yAxis.setMax(200);
	}

	private LineChartModel initCategoryModel() {
		LineChartModel model = new LineChartModel();

		ChartSeries boys = new ChartSeries();
		boys.setLabel("Antigos Clientes");

		for (RelatorioPerfilClientela relatorioPerfilClientela : getRelatorioFaturamentoComparativo()) {
			boys.set(relatorioPerfilClientela.getMes().toString(),
					relatorioPerfilClientela.getFaturamentoAntigosClientes());
		}

		ChartSeries girls = new ChartSeries();
		girls.setLabel("Novos Clientes");

		for (RelatorioPerfilClientela relatorioPerfilClientela : getRelatorioFaturamentoComparativo()) {
			girls.set(relatorioPerfilClientela.getMes().toString(),
					relatorioPerfilClientela.getFaturamentoNovosClientes());
		}

		model.addSeries(boys);
		model.addSeries(girls);

		return model;
	}
	
	public void gerarRelatorioIreport(List<RelatorioPerfilClientela> relatorioLista) throws JRException{
		JasperReport pathjrxml = JasperCompileManager.compileReport("relatorioTeste.jrxml");
		JasperPrint printReport = JasperFillManager.fillReport(pathjrxml, null, new JRBeanCollectionDataSource(relatorioLista));
		JasperExportManager.exportReportToPdfFile(printReport, "RelatorioUser.pdf");
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

	public List<RelatorioPerfilClientela> getRelatorioFaturamentoComparativo() {
		return relatorioFaturamentoComparativo;
	}

	public void setRelatorioFaturamentoComparativo(
			List<RelatorioPerfilClientela> relatorioFaturamentoComparativo) {
		this.relatorioFaturamentoComparativo = relatorioFaturamentoComparativo;
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

	public HorizontalBarChartModel getGraficoJogosMaisUtilizados() {
		return graficoJogosMaisUtilizado;
	}

	public HorizontalBarChartModel getGraficoPlataformasMaisUtilizado() {
		return graficoPlataformasMaisUtilizado;
	}

	public HorizontalBarChartModel getGraficoBarras() {

		if (relatorio.equals("jogos")) {
			return graficoJogosMaisUtilizado;
		} else if (relatorio.equals("plataformas")) {
			return graficoPlataformasMaisUtilizado;
		}

		return null;
	}

	public Date getDataInicial() {
		return dataInicial;
	}

	public void setDataInicial(Date dataInicial) {
		this.dataInicial = dataInicial;
	}

	public Date getDataFinal() {
		return dataFinal;
	}

	public void setDataFinal(Date dataFinal) {
		this.dataFinal = dataFinal;
	}

}
