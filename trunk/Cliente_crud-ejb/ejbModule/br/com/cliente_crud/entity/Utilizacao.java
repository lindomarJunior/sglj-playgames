package br.com.cliente_crud.entity;

import java.util.Calendar;
import java.util.List;

import javax.persistence.*;

import org.hibernate.annotations.IndexColumn;

@Entity
@Table(name = "utilizacao")
public class Utilizacao {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_UTILIZACAO")
	private Integer id;

	@Column(name = "QTD_TEMPO_SOLICITADO_UTILIZACAO")
	private Long qtdTempoSolicitado;

	@Column(name = "QTD_TEMPO_UTILIZADO_UTILIZACAO")
	private Long qtdTempoUtilizado;

	@Column(name = "DATA_UTILIZACAO_UTILIZACAO")
	private Calendar dataUtilizacao;

	@Column(name = "HORA_INICIO_UTILIZACAO")
	private Calendar horaInicio;

	@Column(name = "HORA_TERMINO_UTILIZACAO")
	private Calendar horaTermino;

	@Column(name = "STATUS_ATIVO_UTILIZACAO")
	private Integer statusAtivo;

	@Column(name = "STATUS_ESPERA_UTILIZACAO")
	private Integer statusEspera;

	@Column(name = "VALOR_UTILIZACAO_UTILIZACAO")
	private Float valor;
	
	@Transient
	private Long tempoRestante;

	@ManyToOne
	@JoinColumn(name = "ID_EVENTO", referencedColumnName = "ID_EVENTO")
	private Evento evento;

	@ManyToOne
	@JoinColumn(name = "ID_VIDEOGAME", referencedColumnName = "ID_VIDEOGAME")
	private Videogame videogame;

	@ManyToOne
	@JoinColumn(name = "ID_CLIENTE", referencedColumnName = "ID_CLIENTE")
	private Cliente cliente;

	@ManyToOne
	@JoinColumn(name = "ID_USUARIO", referencedColumnName = "ID_USUARIO")
	private Usuario usuario;
	
	@OneToMany(mappedBy="utilizacao", fetch=FetchType.EAGER)
	@IndexColumn(name="ID_PAUSA")
	private List<Pausa> pausas;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "UTILIZACAO_JOGO", joinColumns = { @JoinColumn(name = "ID_UTILIZACAO", nullable = false, updatable = false) }, inverseJoinColumns = { @JoinColumn(name = "ID_JOGO", nullable = false, updatable = true) })
	private List<Jogo> jogos;
	
	// //////////////////////////////////////////////////////////////////////
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Long getQtdTempoSolicitado() {
		return qtdTempoSolicitado;
	}

	public void setQtdTempoSolicitado(Long qtdTempoSolicitado) {
		this.qtdTempoSolicitado = qtdTempoSolicitado;
	}

	public Long getQtdTempoUtilizado() {
		return qtdTempoUtilizado;
	}

	public void setQtdTempoUtilizado(Long qtdTempoUtilizado) {
		this.qtdTempoUtilizado = qtdTempoUtilizado;
	}

	public Calendar getDataUtilizacao() {
		return dataUtilizacao;
	}

	public void setDataUtilizacao(Calendar dataUtilizacao) {
		this.dataUtilizacao = dataUtilizacao;
	}

	public Calendar getHoraInicio() {
		return horaInicio;
	}

	public void setHoraInicio(Calendar horaInicio) {
		this.horaInicio = horaInicio;
	}

	public Calendar getHoraTermino() {
		return horaTermino;
	}

	public void setHoraTermino(Calendar horaTermino) {
		this.horaTermino = horaTermino;
	}

	public Integer getStatusAtivo() {
		return statusAtivo;
	}

	public void setStatusAtivo(Integer statusAtivo) {
		this.statusAtivo = statusAtivo;
	}

	public Integer getStatusEspera() {
		return statusEspera;
	}

	public void setStatusEspera(Integer statusEspera) {
		this.statusEspera = statusEspera;
	}

	public Float getValor() {
		return valor;
	}

	public void setValor(Float valor) {
		this.valor = valor;
	}

	public Evento getEvento() {
		return evento;
	}

	public void setEvento(Evento evento) {
		this.evento = evento;
	}

	public Videogame getVideogame() {
		return videogame;
	}

	public void setVideogame(Videogame videogame) {
		this.videogame = videogame;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<Jogo> getJogos() {
		return jogos;
	}

	public void setJogos(List<Jogo> jogos) {
		this.jogos = jogos;
	}

	public Long getTempoRestante() {
		return tempoRestante;
	}

	public void setTempoRestante(Long tempoRestante) {
		this.tempoRestante = tempoRestante;
	}

	public List<Pausa> getPausas() {
		return pausas;
	}

	public void setPausas(List<Pausa> pausas) {
		this.pausas = pausas;
	}
}
