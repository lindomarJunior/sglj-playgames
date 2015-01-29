package br.com.cliente_crud.bean;

import java.sql.SQLException;
import java.util.Calendar;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

import br.com.cliente_crud.entity.Plataforma;
import br.com.cliente_crud.service.PlataformaService;

@ManagedBean(name="plataformaBean")
@ViewScoped
public class PlataformaBean {
	@EJB
	private PlataformaService plataformaService;
	
	@Inject
	private Plataforma plataforma;
	private List<Plataforma> plataformas;
	private Double input6 = new Double(0);

////////////////////////////////////////////////////////////////	
	
	/**
	 * Método responsável por inserir Plataforma
	 * @throws SQLException
	 */
	public void incluirPlataforma() throws SQLException{
		plataformaService.incluir(getPlataforma());
		listarPlataforma();
		limparPlataforma();
	}
	
	/**
	 * Método responsável por excluir Plataforma
	 */
	public void excluirPlataforma(){
		plataformaService.excluir(getPlataforma());
		listarPlataforma();
	}
	
	/**
	 * Método utilizado para listar Plataforma
	 * @return
	 */
	public void listarPlataforma(){
		setPlataformas(plataformaService.listar((Class<Plataforma>) plataforma.getClass()));
	}
	
	/**
	 * Método utilizado para consultar Plataforma
	 * @return
	 */
	public void consultarPlataforma(){
		setPlataforma(plataformaService.consultar((Class<Plataforma>) plataforma.getClass(), getPlataforma().getId()));
	}
	
	/**
	 * Método utilizado para alterar Plataforma
	 * @throws SQLException
	 */
	public void atualizarPlataforma() throws SQLException{
		plataformaService.atualizar(getPlataforma());
	}
	
	public void limparPlataforma() {
		
		setPlataforma(new Plataforma());
		
	}
////////////////////////////////////////////////////////////////////////////////
	
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

	public Double getInput6() {
		return input6;
	}

	public void setInput6(Double input6) {
		this.input6 = input6;
	}

}
