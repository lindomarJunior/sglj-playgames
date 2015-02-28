package br.com.cliente_crud.bean;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import br.com.cliente_crud.entity.Historico;
import br.com.cliente_crud.entity.Usuario;
import br.com.cliente_crud.service.ClienteService;
import br.com.cliente_crud.service.HistoricoService;
import br.com.cliente_crud.service.UsuarioService;
import br.com.cliente_crud.util.ConstanteHistorico;

@ManagedBean(name = "usuarioBean")
@ViewScoped
public class UsuarioBean {

	@EJB
	private ClienteService clienteService;
	@EJB
	private UsuarioService usuarioService;
	@EJB
	private HistoricoService historicoService;

	private List<Usuario> usuarios;

	@Inject
	private Usuario usuario;
	@Inject
	private Historico historico;
	private String permissao;
	private List<Historico> listaHistorico;
	private List<Historico> historicos;

	@PostConstruct
	void initialiseSession() {
		FacesContext.getCurrentInstance().getExternalContext().getSession(true);
	}

	// ////////////////////////////////////////////////////////////////////////////
	public void attrListener(ActionEvent event) {
		getUsuario();
	}

	/**
	 * Metodo usado para incluir usuario
	 * 
	 * @throws SQLException
	 */
	public void incluirUsuario() throws SQLException {

		usuarioService.incluir(getUsuario());
		usuario = new Usuario();
		listarUsuario();

	}

	/**
	 * Metodo usado para excluir usuario
	 */
	public void excluirUsuario() {

		usuarioService.excluir(getUsuario());
		listarUsuario();
	}

	/**
	 * Metodo usado para consultar usuario
	 */
	public void consultarUsuario() {

		usuarioService.consultar((Class<Usuario>) usuario.getClass(),
				getUsuario().getIdusuario());
	}

	/**
	 * Metodo usado para listar usuarios
	 */
	public void listarUsuario() {

		setUsuarios(usuarioService.listar((Class<Usuario>) usuario.getClass()));

	}

	/**
	 * @throws SQLException 
	 * 
	 */
	public void efetuarLogin() throws SQLException {
		setUsuario(usuarioService.consultarUsuario(getUsuario().getLogin(), getUsuario()
				.getSenha()));
		
		if(getUsuario() == null){
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, null, "Login ou Senha Incorreto."));
		}else {
			//Seta usuario na sessão
			FacesContext fc = FacesContext.getCurrentInstance();
			HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
			session.setAttribute("usuario", getUsuario());
			

			getHistorico().setEvento(ConstanteHistorico.efetuarLogin);
			getHistorico().setData(Calendar.getInstance());
			getHistorico().setIdUsuario(getUsuario().getIdusuario());
			
			historicoService.incluir(getHistorico());
			
			//Redireciona pagina
			ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
			if(getUsuario().getPermissao().equals("Atendente")){
				try {
					context.redirect("Utilizacao.jsf");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}else if (getUsuario().getPermissao().equals("Gerente")) {
				try {
					context.redirect("Gerenciamento.jsf");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}			
		}
	}
	
	public void gerarHistorico(){
		historicos = historicoService.listar((Class<Historico>)getHistorico().getClass());
		listaHistorico = new ArrayList<Historico>();
		for (Historico historico : historicos) {
			if(historico.getIdUsuario().equals(getUsuario().getIdusuario())){
				getListaHistorico().add(historico);
			}
		}
	}

	public void obterUsuario() {

		getUsuario();

	}

	public void limparUsuario() {

		setUsuario(new Usuario());

	}

	/**
	 * Metodo usado para alterar usuario
	 * 
	 * @throws SQLException
	 */
	public void atualizarUsuario() throws SQLException {

		usuarioService.atualizar(getUsuario());
	}

	// //////////////////////////////////////////////////////////////////////////////
	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Historico getHistorico() {
		return historico;
	}

	public void setHistorico(Historico historico) {
		this.historico = historico;
	}

	public String getPermissao() {
		return permissao;
	}

	public void setPermissao(String permissao) {
		this.permissao = permissao;
	}

	public List<Historico> getListaHistorico() {
		return listaHistorico;
	}

	public void setListaHistorico(List<Historico> listaHistorico) {
		this.listaHistorico = listaHistorico;
	}

}
