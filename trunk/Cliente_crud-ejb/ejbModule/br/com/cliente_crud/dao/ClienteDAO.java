package br.com.cliente_crud.dao;

import br.com.cliente_crud.entity.Cliente;

public interface ClienteDAO {

	/**
	 * M�todo utilizado para consultar cliente atrav�s do cpf
	 * 
	 * @param campo
	 * @param valor
	 * @return
	 */
	public Cliente consultarCliente(Long cpf);
}
