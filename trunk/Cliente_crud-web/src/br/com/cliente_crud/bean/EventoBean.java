package br.com.cliente_crud.bean;

import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

import br.com.cliente_crud.entity.Evento;
import br.com.cliente_crud.service.EventoService;

@ManagedBean(name="eventoBean")
@ViewScoped
public class EventoBean {
		
	@EJB
	private EventoService eventoService;
	
	@Inject
	private Evento evento;
	private List<Evento> eventos;
	private Calendar dataCalendar = Calendar.getInstance();

////////////////////////////////////////////////////////////////	
	
	/**
	 * Método responsável por inserir Evento
	 * @throws SQLException
	 */
	public void incluirEvento() throws SQLException{
		eventoService.incluir(getEvento());
		listarEvento();
		limparEvento();
	}
	
	/**
	 * Método responsável por excluir Evento
	 */
	public void excluirEvento(){
		eventoService.excluir(getEvento());
		listarEvento();
	}
	
	/**
	 * Método utilizado para listar Evento
	 * @return
	 */
	public void listarEvento(){
		setEventos(eventoService.listar((Class<Evento>) evento.getClass()));
	}
	
	/**
	 * Método utilizado para consultar Evento
	 * @return
	 */
	public void consultarEvento(){
		setEvento(eventoService.consultar((Class<Evento>) evento.getClass(), getEvento().getId()));
	}
	
	/**
	 * Método utilizado para alterar Evento
	 * @throws SQLException
	 */
	public void atualizarEvento() throws SQLException{
		eventoService.atualizar(getEvento());
		listarEvento();
	}
	
	public void limparEvento() {
		
		setEvento(new Evento());
		
	}
////////////////////////////////////////////////////////////////////////////////
	
	public Evento getEvento() {
		return evento;
	}

	public void setEvento(Evento evento) {
		this.evento = evento;
	}
	
	public List<Evento> getEventos() {
		return eventos;
	}

	public void setEventos(List<Evento> eventos) {
		this.eventos = eventos;
	}

	public Date getDataInicio() {
		try {
			return evento.getDataInicio().getTime();
		} catch (Exception e) {
			return null;
		}		
	}

	public void setDataInicio(Date dataInicio) {
		
		dataCalendar.setTime(dataInicio);
		evento.setDataInicio(dataCalendar);
	}
	
	public Date getDataTermino() {
		try {
			return evento.getDataTermino().getTime();
		} catch (Exception e) {
			return null;
		}		
	}

	public void setDataTermino(Date dataTermino) {
		
		dataCalendar.setTime(dataTermino);
		evento.setDataTermino(dataCalendar);
	}
}
