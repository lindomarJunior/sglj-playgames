package br.com.cliente_crud.entity;

import javax.persistence.*;

@Entity
@Table(name="plataforma")
public class Plataforma {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID_PLATAFORMA")
	private Integer id;
	
	@Column(name="NOME_PLATAFORMA")
	private String nome;
	
	@Column(name="MARCA_PLATAFORMA")
	private String marca;
	
	@Column(name="VALOR_HORA_PLATAFORMA")
	private Float valorHora;
	
	//gets and sets
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

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public Float getValorHora() {
		return valorHora;
	}

	public void setValorHora(Float valorHora) {
		this.valorHora = valorHora;
	}
}
