package br.com.cliente_crud.relatorio;

public class RelatorioPerfilCliente {
	private String jogo;
	private String plataforma;
	private Integer genero;
	private Long tempo;
	
	//construtores
	public RelatorioPerfilCliente(Number genero) {
		this.genero = new Integer(genero.toString());
	}
	
	public RelatorioPerfilCliente(String jogo, String plataforma, Long tempo) {
		this.jogo = jogo;
		this.plataforma = plataforma;
		this.tempo = tempo;
	}
	
	public RelatorioPerfilCliente(String jogo, String plataforma) {
		this.jogo = jogo;
		this.plataforma = plataforma;
	}
	
/////////////////////////////////////////////////////////////////////////////
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
	
	public Integer getGenero() {
		return genero;
	}

	public void setGenero(Integer genero) {
		this.genero = genero;
	}

	public Long getTempo() {
		return tempo;
	}

	public void setTempo(Long tempo) {
		this.tempo = tempo;
	}
}
