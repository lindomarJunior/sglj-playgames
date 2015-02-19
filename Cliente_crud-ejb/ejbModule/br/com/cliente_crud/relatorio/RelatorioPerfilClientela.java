package br.com.cliente_crud.relatorio;

public class RelatorioPerfilClientela {

	private String jogo;
	private String plataforma;
	private String cliente;
	private Long tempo;
	private Float faturamentoNovosClientes;
	private Float faturamentoAntigosClientes;
	private Float valorGasto;
	private Integer mes;
	
	//construtores
	public RelatorioPerfilClientela() {
		super();
	}
	
	public RelatorioPerfilClientela(String jogo, String plataforma, Long tempo) {
		this.jogo = jogo;
		this.plataforma = plataforma;
		this.tempo = tempo;
	}
	
	public RelatorioPerfilClientela(String jogo, String plataforma) {
		this.jogo = jogo;
		this.plataforma = plataforma;
	}
	
	public RelatorioPerfilClientela(String plataforma, Long tempo) {
		this.plataforma = plataforma;
		this.tempo = tempo;
	}
	
	public RelatorioPerfilClientela(String cliente, Long tempo, Number valorGasto) {
		this.cliente = cliente;
		this.tempo = tempo;
		this.valorGasto = new Float(valorGasto.toString());
	}
	
	public RelatorioPerfilClientela(Number faturamentoNovosClientes) {
		this.faturamentoNovosClientes = new Float(faturamentoNovosClientes.toString());
	}
	
	
	// ////////////////////////////////////////
	public String getJogo() {
		return jogo;
	}

	public void setJogo(String jogo) {
		this.jogo = jogo;
	}

	public String getPlataforma() {
		return plataforma;
	}

	public void setPlataforma(String plataforma) {
		this.plataforma = plataforma;
	}

	public String getCliente() {
		return cliente;
	}

	public void setCliente(String cliente) {
		this.cliente = cliente;
	}

	public Long getTempo() {
		return tempo;
	}

	public void setTempo(Long tempo) {
		this.tempo = tempo;
	}

	public Float getFaturamentoNovosClientes() {
		return faturamentoNovosClientes;
	}

	public void setFaturamentoNovosClientes(Float faturamentoNovosClientes) {
		this.faturamentoNovosClientes = faturamentoNovosClientes;
	}

	public Float getFaturamentoAntigosClientes() {
		return faturamentoAntigosClientes;
	}

	public void setFaturamentoAntigosClientes(Float faturamentoAntigosClientes) {
		this.faturamentoAntigosClientes = faturamentoAntigosClientes;
	}

	public Float getValorGasto() {
		return valorGasto;
	}

	public void setValorGasto(Float valorGasto) {
		this.valorGasto = valorGasto;
	}

	public Integer getMes() {
		return mes;
	}

	public void setMes(Integer mes) {
		this.mes = mes;
	}
}
