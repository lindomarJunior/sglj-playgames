package br.com.cliente_crud.bean;

import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

import br.com.cliente_crud.entity.Plataforma;
import br.com.cliente_crud.entity.Jogo;
import br.com.cliente_crud.service.PlataformaService;
import br.com.cliente_crud.service.JogoService;

@ManagedBean(name="jogoBean")
@ViewScoped
public class JogoBean {
	@EJB
	JogoService jogoService;
	@EJB
	PlataformaService plataformaService;
	@Inject
	Jogo jogo;
	@Inject
	Plataforma plataforma;

	List<Plataforma> plataformas;
	List<Jogo> jogos;
	
	Calendar dataCalendar = Calendar.getInstance();

	// //////////////////////////////////////////////////////////////

	/**
	 * M�todo respons�vel por inserir Jogo
	 * 
	 * @throws SQLException
	 */
	public void incluirJogo() throws SQLException {
		getJogo().setPlataforma(getPlataforma());
		getJogo().setDisponibilidade(1);
		jogoService.incluir(getJogo());
		listarJogo();
		limparJogo();
	}

	/**
	 * M�todo respons�vel por excluir Jogo
	 */
	public void excluirJogo() {
		jogoService.excluir(getJogo());
		listarJogo();
	}

	/**
	 * M�todo utilizado para listar Jogo
	 * 
	 * @return
	 */
	public void listarJogo() {
		setJogos(jogoService.listar((Class<Jogo>) jogo
				.getClass()));
	}

	/**
	 * M�todo utilizado para consultar Jogo
	 * 
	 * @return
	 */
	public void consultarJogo() {
		setJogo(jogoService
				.consultar((Class<Jogo>) jogo.getClass(),
						getJogo().getId()));
	}

	/**
	 * M�todo utilizado para alterar Jogo
	 * 
	 * @throws SQLException
	 */
	public void atualizarJogo() throws SQLException {
		jogoService.atualizar(getJogo());
		listarJogo();
	}

	/**
	 * M�todo utilizado para reiniciar formul�rio
	 */
	public void limparJogo() {

		setJogo(new Jogo());
		setPlataforma(new Plataforma());
		listarPlataforma();
	}
	
	/**
	 * M�todo utilizado para listar plataforma
	 */
	public void listarPlataforma(){
		setPlataformas(plataformaService.listar((Class<Plataforma>) plataforma.getClass()));
	}

	// //////////////////////////////////////////////////////////////////////////////

	public Jogo getJogo() {
		return jogo;
	}

	public void setJogo(Jogo jogo) {
		this.jogo = jogo;
	}

	public List<Jogo> getJogos() {
		return jogos;
	}

	public void setJogos(List<Jogo> jogos) {
		this.jogos = jogos;
	}

	public Plataforma getPlataforma() {
		return plataforma;
	}

	public void setPlataforma(Plataforma plataforma) {
		this.plataforma = plataforma;
	}

	public List<Plataforma> getPlataformas() {
		return plataformas;
	}

	public void setPlataformas(List<Plataforma> plataformas) {
		this.plataformas = plataformas;
	}
	
	public Date getDataLan() {
		try {
			return jogo.getDataLancamento().getTime();
		} catch (Exception e) {
			return null;
		}		
	}
	
public void setDataLan(Date dataLan) {
		
		dataCalendar.setTime(dataLan);
		jogo.setDataLancamento(dataCalendar);
	}
}
