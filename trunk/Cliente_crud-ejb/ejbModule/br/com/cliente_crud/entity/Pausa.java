package br.com.cliente_crud.entity;

import java.util.Calendar;

import javax.persistence.*;

@Entity
@Table(name="pausa")
public class Pausa {
		
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID_PAUSA")
	private Integer id;
	
	@Column(name="HORA_PAUSA")
	private Calendar horaPausa;
	
	@Column(name="HORA_RETORNO_PAUSA")
	private Calendar horaRetorno;
	
	@ManyToOne
	@JoinColumn(name = "ID_UTILIZACAO", referencedColumnName = "ID_UTILIZACAO")
	private Utilizacao utilizacao;

	////////////////////////////////////////////
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Calendar getHoraPausa() {
		return horaPausa;
	}

	public void setHoraPausa(Calendar horaPausa) {
		this.horaPausa = horaPausa;
	}

	public Calendar getHoraRetorno() {
		return horaRetorno;
	}

	public void setHoraRetorno(Calendar horaRetorno) {
		this.horaRetorno = horaRetorno;
	}

	public Utilizacao getUtilizacao() {
		return utilizacao;
	}

	public void setUtilizacao(Utilizacao utilizacao) {
		this.utilizacao = utilizacao;
	}
}
