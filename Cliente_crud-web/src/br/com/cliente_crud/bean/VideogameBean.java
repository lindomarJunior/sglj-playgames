package br.com.cliente_crud.bean;

import java.sql.SQLException;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

import br.com.cliente_crud.entity.Plataforma;
import br.com.cliente_crud.entity.Videogame;
import br.com.cliente_crud.service.PlataformaService;
import br.com.cliente_crud.service.VideogameService;

@ManagedBean(name = "videogameBean")
@ViewScoped
public class VideogameBean {

	@EJB
	VideogameService videogameService;
	@EJB
	PlataformaService plataformaService;
	@Inject
	Videogame videogame;
	@Inject
	Plataforma plataforma;

	List<Plataforma> plataformas;
	List<Videogame> videogames;

	// //////////////////////////////////////////////////////////////

	/**
	 * M�todo respons�vel por inserir Videogame
	 * 
	 * @throws SQLException
	 */
	public void incluirVideogame() throws SQLException {
		getVideogame().setPlataforma(getPlataforma());
		videogameService.incluir(getVideogame());
		listarVideogame();
		limparVideogame();
	}

	/**
	 * M�todo respons�vel por excluir Videogame
	 */
	public void excluirVideogame() {
		videogameService.excluir(getVideogame());
		listarVideogame();
	}

	/**
	 * M�todo utilizado para listar Videogame
	 * 
	 * @return
	 */
	public void listarVideogame() {
		setVideogames(videogameService.listar((Class<Videogame>) videogame
				.getClass()));
	}

	/**
	 * M�todo utilizado para consultar Videogame
	 * 
	 * @return
	 */
	public void consultarVideogame() {
		setVideogame(videogameService
				.consultar((Class<Videogame>) videogame.getClass(),
						getVideogame().getId()));
	}

	/**
	 * M�todo utilizado para alterar Videogame
	 * 
	 * @throws SQLException
	 */
	public void atualizarVideogame() throws SQLException {
		videogameService.atualizar(getVideogame());
		listarVideogame();
	}

	/**
	 * M�todo utilizado para reiniciar formul�rio
	 */
	public void limparVideogame() {

		setVideogame(new Videogame());
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

	public Videogame getVideogame() {
		return videogame;
	}

	public void setVideogame(Videogame videogame) {
		this.videogame = videogame;
	}

	public List<Videogame> getVideogames() {
		return videogames;
	}

	public void setVideogames(List<Videogame> videogames) {
		this.videogames = videogames;
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
}
