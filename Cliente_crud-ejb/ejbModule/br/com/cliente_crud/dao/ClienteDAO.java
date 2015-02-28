package br.com.cliente_crud.dao;

import br.com.cliente_crud.entity.Cliente;
import br.com.cliente_crud.entity.Usuario;

public interface ClienteDAO {

	/**
	 * M�todo utilizado para consultar cliente atrav�s do cpf
	 * 
	 * @param campo
	 * @param valor
	 * @return
	 */
	public Cliente consultarCliente(String cpf);
	
	/**
	 * @param login
	 * @param senha
	 * @return
	 */
	public Usuario consultarUsuario(String login, String senha);
}
