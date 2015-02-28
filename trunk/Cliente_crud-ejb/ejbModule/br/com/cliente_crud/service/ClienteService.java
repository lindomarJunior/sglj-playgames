package br.com.cliente_crud.service;

import java.sql.SQLException;
import java.util.List;

import javax.ejb.Local;

import br.com.cliente_crud.entity.Cliente;

@Local
public interface ClienteService {
	/**
	 * Inclui a entidade
	 * @param to
	 * @throws SQLException 
	 */
	public void incluir(Cliente to) throws SQLException;
	
	/**
	 * Exclui a entidade
	 * @param to
	 */
	public void excluir(Cliente to);
	
	/**
	 * Consulta a entidade
	 * @param classe
	 * @param pk
	 * @return
	 */
	public Cliente consultar(Class<Cliente> classe, Integer pk);
	
	/**
	 * Lista as entidades
	 * @param classe
	 * @return
	 */
	public List<Cliente> listar(Class<Cliente> classe);
	
	/**
	 * Atualiza as entidades
	 * @param to
	 */
	public void atualizar(Cliente to) throws SQLException;
	
	/**
	 * Consulta cliente pelo cpf
	 * @param cpf
	 * @return
	 */
	public Cliente consultarCliente(String cpf);
}
