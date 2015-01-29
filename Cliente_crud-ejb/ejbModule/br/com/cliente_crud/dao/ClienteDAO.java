package br.com.cliente_crud.dao;

import br.com.cliente_crud.entity.Cliente;

public interface ClienteDAO {

	/**
	 * Método utilizado para consultar cliente através do cpf
	 * 
	 * @param campo
	 * @param valor
	 * @return
	 */
	public Cliente consultarCliente(Long cpf);
}
