package br.com.cliente_crud.entity;

import java.util.Calendar;

import javax.persistence.*;

@Entity
@Table(name="jogo")
public class Jogo {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID_JOGO")
	private Integer id;
	
	@Column(name="TITULO_JOGO")
	private String titulo;
	
	@Column(name="GENERO_JOGO")
	private Integer genero;
	
	@Column(name="FAIXA_ETARIA_JOGO")
	private Integer faixaEtaria;
	
	@Column(name="DATA_LANCAMENTO_JOGO")
	private Calendar dataLancamento;
	
	@Column(name="DESCRICAO_JOGO")
	private String descricao;
	
	@Column(name="DISPONIBILIDADE_JOGO")
	private Integer disponibilidade;
	
	@ManyToOne
	@JoinColumn(name="ID_PLATAFORMA",referencedColumnName="ID_PLATAFORMA")
	private Plataforma plataforma;
	
	//gets and sets
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public Integer getGenero() {
		return genero;
	}

	public void setGenero(Integer genero) {
		this.genero = genero;
	}

	public Integer getFaixaEtaria() {
		return faixaEtaria;
	}

	public void setFaixaEtaria(Integer faixaEtaria) {
		this.faixaEtaria = faixaEtaria;
	}

	public Calendar getDataLancamento() {
		return dataLancamento;
	}

	public void setDataLancamento(Calendar dataLancamento) {
		this.dataLancamento = dataLancamento;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Integer getDisponibilidade() {
		return disponibilidade;
	}

	public void setDisponibilidade(Integer disponibilidade) {
		this.disponibilidade = disponibilidade;
	}

	public Plataforma getPlataforma() {
		return plataforma;
	}

	public void setPlataforma(Plataforma plataforma) {
		this.plataforma = plataforma;
	}
}
