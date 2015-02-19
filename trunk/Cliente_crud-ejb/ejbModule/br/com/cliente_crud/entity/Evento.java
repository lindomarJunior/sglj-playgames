package br.com.cliente_crud.entity;

import java.util.Calendar;

import javax.persistence.*;

@Entity
@Table(name="evento")
public class Evento {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID_EVENTO")
	private Integer id;
	
	@Column(name="NOME_EVENTO")
	private String nome;
	
	@Column(name="DATA_INICIO_EVENTO")
	private Calendar dataInicio;
	
	@Column(name="DATA_TERMINO_EVENTO")
	private Calendar dataTermino;
	
	@Column(name="DESCRICAO_EVENTO")
	private String descricao;

	//////////////////////////////////////////////
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Calendar getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(Calendar dataInicio) {
		this.dataInicio = dataInicio;
	}

	public Calendar getDataTermino() {
		return dataTermino;
	}

	public void setDataTermino(Calendar dataTermino) {
		this.dataTermino = dataTermino;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
}
