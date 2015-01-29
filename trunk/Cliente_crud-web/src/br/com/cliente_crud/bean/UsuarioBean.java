package br.com.cliente_crud.bean;

import java.sql.SQLException;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

import br.com.cliente_crud.entity.Usuario;
import br.com.cliente_crud.service.ClienteService;
import br.com.cliente_crud.service.UsuarioService;

@ManagedBean(name = "usuarioBean")
@ViewScoped
public class UsuarioBean {

	@EJB
	private ClienteService clienteService;
	@EJB
	private UsuarioService usuarioService;

	private List<Usuario> usuarios;

	@Inject
	private Usuario usuario;

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

}
