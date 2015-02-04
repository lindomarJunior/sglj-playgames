package br.com.cliente_crud.bean;

import java.io.Serializable;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

import br.com.cliente_crud.entity.Cliente;
import br.com.cliente_crud.entity.Evento;
import br.com.cliente_crud.entity.Jogo;
import br.com.cliente_crud.entity.Plataforma;
import br.com.cliente_crud.entity.Usuario;
import br.com.cliente_crud.entity.Utilizacao;
import br.com.cliente_crud.entity.Videogame;
import br.com.cliente_crud.service.ClienteService;
import br.com.cliente_crud.service.EventoService;
import br.com.cliente_crud.service.JogoService;
import br.com.cliente_crud.service.PlataformaService;
import br.com.cliente_crud.service.UsuarioService;
import br.com.cliente_crud.service.UtilizacaoService;
import br.com.cliente_crud.service.VideogameService;
import br.com.cliente_crud.util.UtilData;

@ManagedBean(name = "utilizacaoBean")
@ViewScoped
public class UtilizacaoBean implements Serializable {
	@EJB
	private ClienteService clienteService;
	@EJB
	private UtilizacaoService utilizacaoService;
	@EJB
	private EventoService eventoService;
	@EJB
	private UsuarioService usuarioService;
	@EJB
	private PlataformaService plataformaService;
	@EJB
	private JogoService jogoService;
	@EJB
	private VideogameService videogameService;
	@Inject
	private Usuario usuario;
	@Inject
	private Cliente cliente;
	@Inject
	private Evento evento;
	@Inject
	private Utilizacao utilizacao;
	@Inject
	private Plataforma plataforma;
	@Inject
	private Jogo jogo;
	private Jogo jogoAtual;
	@Inject
	private Videogame videogame;
	private List<Utilizacao> ListaUtilizacaoEspera;
	private List<Utilizacao> ListaUtilizacaoAndamento = new ArrayList<Utilizacao>();
	private List<Plataforma> listaPlataforma;
	private List<Jogo> listaJogo;
	private List<Videogame> listaVideogameDisponiveis;
	private final Integer ativado = 1;
	private final Integer desativado = 0;
	private Long tempoAdicional;

	// ///////////////////////////////////////////////////////////////////////////

	/**
	 * Método utilizado para incluir utilização
	 */
	public void incluirUtilizacao() {
		setCliente(clienteService.consultarCliente(getCliente().getCpf()));
		setEvento(eventoService.consultar((Class<Evento>) getEvento()
				.getClass(), 1));
		setUsuario(usuarioService.consultar((Class<Usuario>) getUsuario()
				.getClass(), 7));

		getUtilizacao().setCliente(getCliente());
		getUtilizacao().setEvento(getEvento());
		getUtilizacao().setUsuario(getUsuario());
		listarPlataforma();
	}

	/**
	 * Método responsável por continuar a inclusão de utilização
	 * 
	 * @throws SQLException
	 */
	public void continuarInclusao() throws SQLException {
		//incluindo jogo
		setJogo(findJogo(getJogo().getId(), getListaJogo()));
		getJogo().setDisponibilidade(0);
		jogoService.atualizar(getJogo());
		setListaJogo(new ArrayList<Jogo>());
		getListaJogo().add(getJogo());
		getUtilizacao().setJogos(getListaJogo());
		
		getUtilizacao().setStatusEspera(ativado);
		utilizacaoService.incluir(getUtilizacao());
		listarUtilizacaoEspera();
	}

	/**
	 * Método resposável por dar início as utilizações em espera
	 * 
	 * @throws SQLException
	 */
	public void iniciarUtilizacao() throws SQLException {

		// inseri videogame na utilização
		for (Videogame videogame : getListaVideogameDisponiveis()) {
			if (videogame
					.getPlataforma()
					.getId()
					.equals(getUtilizacao().getJogos().get(0).getPlataforma()
							.getId())) {
				videogame.setDisponibilidade(0);
				videogameService.atualizar(videogame);
				getUtilizacao().setVideogame(videogame);
				break;
			}
		}

		Calendar horaInicio = Calendar.getInstance();

		getUtilizacao().setHoraInicio(horaInicio);
		getUtilizacao().setStatusEspera(desativado);
		getUtilizacao().setStatusAtivo(ativado);
		utilizacaoService.atualizar(getUtilizacao());
		listarVideogameDisponivel();
		getListaUtilizacaoEspera().remove(getUtilizacao());
		getListaUtilizacaoAndamento().add(getUtilizacao());
	}

	/**
	 * 
	 */
	public void cancelarUtilizacao() {
		utilizacaoService.excluir(getUtilizacao());
	}
	
	/**
	 * 
	 */
	public void visualizarUtilizacao(){
		getUtilizacao().setQtdTempoUtilizado(
				calcularTempoUtilizado(getUtilizacao()));
		
		setListaJogo(utilizacaoService
				.consultarJogoPorPlataforma(getUtilizacao().getVideogame()
						.getPlataforma().getId()));
		
		for (Jogo jogo : getUtilizacao().getJogos()) {
			if(jogo.getDisponibilidade().equals(0)){
				jogoAtual = jogo;
				break;
			}
		}
	}

	/**
	 * 
	 */
	public void pausarUtilizacao() {
		
		getUtilizacao().setQtdTempoUtilizado(
				calcularTempoUtilizado(getUtilizacao()));
		
		setListaJogo(utilizacaoService
				.consultarJogoPorPlataforma(getUtilizacao().getVideogame()
						.getPlataforma().getId()));
		
		for (Jogo jogo : getUtilizacao().getJogos()) {
			if(jogo.getDisponibilidade().equals(0)){
				jogoAtual = jogo;
				break;
			}
		}

		getListaJogo().remove(findJogo(jogoAtual.getId(), getListaJogo()));
	}
	
	/**
	 * @throws SQLException 
	 * 
	 */
	public void alterarPausa() throws SQLException{
		
		//Troca de jogo		
		if(!getJogoAtual().getId().equals(getJogo().getId())){
			setJogo(findJogo(getJogo().getId(), getListaJogo()));
			getJogo().setDisponibilidade(0);		
			jogoAtual.setDisponibilidade(1);
			
			jogoService.atualizar(getJogo());
			jogoService.atualizar(jogoAtual);
			getUtilizacao().getJogos().add(getJogo());		
			utilizacaoService.atualizar(getUtilizacao());
		}
		
		
		//Adicionando mais tempo
		if(!getTempoAdicional().equals(new Long(0))){
			getUtilizacao().setQtdTempoSolicitado(getUtilizacao().getQtdTempoSolicitado() + getTempoAdicional());
			setTempoAdicional(null);
			utilizacaoService.atualizar(getUtilizacao());
		}
		
	}
	/**
	 * Método utilizado para quando terminar o tempo da utilização
	 */
	public void terminarUtilizacao(Utilizacao utilizacao) {
		setUtilizacao(utilizacaoService.consultar(
				(Class<Utilizacao>) utilizacao.getClass(), utilizacao.getId()));
		getUtilizacao().setQtdTempoUtilizado(
				calcularTempoUtilizado(getUtilizacao()));
	}

	/**
	 * @throws SQLException
	 */
	public void ecerrarUtilizacao() throws SQLException {

		Calendar horaTermino = Calendar.getInstance();

		getUtilizacao().getVideogame().setDisponibilidade(1);
		videogameService.atualizar(getUtilizacao().getVideogame());

		getUtilizacao().setQtdTempoUtilizado(
				calcularTempoUtilizado(getUtilizacao()));
		getUtilizacao().setHoraTermino(horaTermino);
		getUtilizacao().setStatusAtivo(desativado);
		utilizacaoService.atualizar(getUtilizacao());
	}

	/**
	 * Método responsável por listar utilização
	 */
	public void listarUtilizacaoEspera() {
		setListaUtilizacaoEspera(utilizacaoService
				.listarUtilizacaoEspera(ativado));
	}

	/**
	 * Método responsável por listar utilização
	 */
	public void listarUtilizacaoAndamento() {
		setListaUtilizacaoAndamento(utilizacaoService
				.listarUtilizacaoAndamento(ativado));

		// cacula tempo que falta apartir do banco de dados
		for (Utilizacao utilizacao : getListaUtilizacaoAndamento()) {
			utilizacao.setQtdTempoSolicitado(calcularTempoRestante(utilizacao));
		}
	}

	/**
	 * @param utilizacao
	 * @return
	 */
	private Long calcularTempoRestante(Utilizacao utilizacao) {
		Calendar tempoAtual = Calendar.getInstance();
		Calendar tempoFinal = (Calendar) utilizacao.getHoraInicio().clone();
		Integer tempoSolicitado = Integer.valueOf(utilizacao
				.getQtdTempoSolicitado().toString());
		tempoFinal.add(Calendar.SECOND, tempoSolicitado);
		Long tempoRestante = (long) UtilData.diferencaEmSegundos(
				tempoAtual.getTime(), tempoFinal.getTime());

		return tempoRestante;
	}

	/**
	 * @param utilizacao
	 * @return
	 */
	private Long calcularTempoUtilizado(Utilizacao utilizacao) {
		Long tempoSolicitado = utilizacao.getQtdTempoSolicitado();
		Long tempoRestante = calcularTempoRestante(utilizacao);

		if (tempoRestante < 0) {
			return tempoSolicitado;
		} else if (tempoRestante == 0) {
			return tempoRestante;
		}

		Long tempoUtilizado = tempoSolicitado - tempoRestante;
		return tempoUtilizado;
	}
	
	private Jogo findJogo(Integer idJogo, List<Jogo> listaJogo){
		for (Jogo jogo : listaJogo) {
			if(jogo.getId().equals(idJogo)){
				return jogo;
			}
		}
		return null;
	}

	/**
	 * Método responsável por listar plataforma
	 */
	public void listarPlataforma() {
		setListaPlataforma(plataformaService
				.listar((Class<Plataforma>) plataforma.getClass()));
	}

	/**
	 * Método utilizado para listar Jogo
	 */
	public void listarJogo() {
		setListaJogo(utilizacaoService
				.consultarJogoPorPlataforma(getPlataforma().getId()));
	}

	/**
	 * Método utilizado para listar videogame disponivel
	 */
	public void listarVideogameDisponivel() {
		setListaVideogameDisponiveis(videogameService
				.listarVideogamesDisponiveis());
	}

	// ////////////////////////////////////////////////////////////////////////////////
	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Utilizacao getUtilizacao() {
		return utilizacao;
	}

	public void setUtilizacao(Utilizacao utilizacao) {
		this.utilizacao = utilizacao;
	}

	public Evento getEvento() {
		return evento;
	}

	public void setEvento(Evento evento) {
		this.evento = evento;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<Utilizacao> getListaUtilizacaoEspera() {
		return ListaUtilizacaoEspera;
	}

	public void setListaUtilizacaoEspera(List<Utilizacao> ListaUtilizacaoEspera) {
		this.ListaUtilizacaoEspera = ListaUtilizacaoEspera;
	}

	public List<Plataforma> getListaPlataforma() {
		return listaPlataforma;
	}

	public void setListaPlataforma(List<Plataforma> listaPlataforma) {
		this.listaPlataforma = listaPlataforma;
	}

	public Plataforma getPlataforma() {
		return plataforma;
	}

	public Long getTempoAdicional() {
		return tempoAdicional;
	}

	public void setTempoAdicional(Long tempoAdicional) {
		this.tempoAdicional = tempoAdicional;
	}

	public void setPlataforma(Plataforma plataforma) {
		this.plataforma = plataforma;
	}

	public Jogo getJogo() {
		return jogo;
	}

	public void setJogo(Jogo jogo) {
		this.jogo = jogo;
	}

	public List<Jogo> getListaJogo() {
		return listaJogo;
	}

	public void setListaJogo(List<Jogo> listaJogo) {
		this.listaJogo = listaJogo;
	}

	public List<Utilizacao> getListaUtilizacaoAndamento() {
		return ListaUtilizacaoAndamento;
	}

	public void setListaUtilizacaoAndamento(
			List<Utilizacao> listaUtilizacaoAndamento) {
		ListaUtilizacaoAndamento = listaUtilizacaoAndamento;
	}

	public Videogame getVideogame() {
		return videogame;
	}

	public void setVideogame(Videogame videogame) {
		this.videogame = videogame;
	}

	public List<Videogame> getListaVideogameDisponiveis() {
		return listaVideogameDisponiveis;
	}

	public void setListaVideogameDisponiveis(
			List<Videogame> listaVideogameDisponiveis) {
		this.listaVideogameDisponiveis = listaVideogameDisponiveis;
	}

	public Jogo getJogoAtual() {
		return jogoAtual;
	}

	public void setJogoAtual(Jogo jogoAtual) {
		this.jogoAtual = jogoAtual;
	}
}
