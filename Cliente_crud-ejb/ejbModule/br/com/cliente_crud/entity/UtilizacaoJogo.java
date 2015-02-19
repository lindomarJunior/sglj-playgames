package br.com.cliente_crud.entity;

import javax.persistence.*;

@Entity
@Table(name="utilizacao_jogo")
public class UtilizacaoJogo {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID_UTILIZACAO_JOGO")
	private Integer id;
	
	@Column(name="QTD_TEMPO_UTILIZADO")
	private Long qtdTempoUtilizacao;
	
	@Column(name="ID_JOGO")
	private Integer idJogo;
	
	@Column(name="ID_UTILIZACAO")
	private Integer idUtilizacao;

	////////////////////////////////////
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Long getQtdTempoUtilizacao() {
		return qtdTempoUtilizacao;
	}

	public void setQtdTempoUtilizacao(Long qtdTempoUtilizacao) {
		this.qtdTempoUtilizacao = qtdTempoUtilizacao;
	}

	public Integer getIdJogo() {
		return idJogo;
	}

	public void setIdJogo(Integer idJogo) {
		this.idJogo = idJogo;
	}

	public Integer getIdUtilizacao() {
		return idUtilizacao;
	}

	public void setIdUtilizacao(Integer idUtilizacao) {
		this.idUtilizacao = idUtilizacao;
	}	
}
