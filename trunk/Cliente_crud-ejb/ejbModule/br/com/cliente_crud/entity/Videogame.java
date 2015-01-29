package br.com.cliente_crud.entity;

import javax.persistence.*;

@Entity
@Table(name="Videogame")
public class Videogame {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID_VIDEOGAME")
	private Integer id;
	
	@Column(name="REFERENCIA_MODELO_VIDEOGAME")
	private Long referenciaModelo;
	
	@Column(name="DISPONIBILIDADE_VIDEOGAME")
	private Integer disponibilidade;
	
	//relacionamento com Plataforma
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

	public Long getReferenciaModelo() {
		return referenciaModelo;
	}

	public void setReferenciaModelo(Long referenciaModelo) {
		this.referenciaModelo = referenciaModelo;
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
