package br.com.controller.bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 * @author Junior
 * Classe responsável por controlar a chamada de paginas
 *
 */
@ManagedBean(name = "controller")
@ViewScoped
public class Controller {
	String cabecalho = "";
	String diretorio = "";
	
	public void escolherDiretorio(String itemMenu){
		setCabecalho(itemMenu);
		setDiretorio("/"+itemMenu+"/"+itemMenu+".xhtml");
	}
	
	//////////////////////////////////////////////////////
	public String getCabecalho() {
		return cabecalho;
	}
	public void setCabecalho(String cabecalho) {
		this.cabecalho = cabecalho;
	}
	public String getDiretorio() {
		return diretorio;
	}
	public void setDiretorio(String diretorio) {
		this.diretorio = diretorio;
	}

}
