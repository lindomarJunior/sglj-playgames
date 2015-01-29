package br.com.cliente_crud.bean;

import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

import br.com.cliente_crud.entity.Cliente;
import br.com.cliente_crud.service.ClienteService;

@ManagedBean(name = "clienteBean")
@ViewScoped
public class ClienteBean {

	@EJB
	private ClienteService clienteService;
	@Inject
	private Cliente cliente;
	private List<Cliente> clientes;
	private Calendar dataCalendar = Calendar.getInstance();

	// //////////////////////////////////////////////////////////////

	/**
	 * Método responsável por inserir cliente
	 * 
	 * @throws SQLException
	 */
	public void incluirCliente() throws SQLException {
		clienteService.incluir(getCliente());
		listarCliente();
		limparCliente();
	}

	/**
	 * Método responsável por excluir cliente
	 */
	public void excluirCliente() {
		clienteService.excluir(getCliente());
		listarCliente();
	}

	/**
	 * Método utilizado para listar cliente
	 * 
	 * @return
	 */
	public void listarCliente() {
		setClientes(clienteService.listar((Class<Cliente>) cliente.getClass()));
	}

	/**
	 * Método utilizado para consultar cliente
	 * 
	 * @return
	 */
	public void consultarCliente() {
		setCliente(clienteService.consultar(
				(Class<Cliente>) cliente.getClass(), getCliente().getId()));
	}

	/**
	 * Método utilizado para alterar cliente
	 * 
	 * @throws SQLException
	 */
	public void atualizarCliente() throws SQLException {
		clienteService.atualizar(getCliente());
		listarCliente();
	}

	/**
	 * Método utilizado para limpar formulario
	 */
	public void limparCliente() {

		setCliente(new Cliente());

	}

	// //////////////////////////////////////////////////////////////////////////////

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public List<Cliente> getClientes() {
		return clientes;
	}

	public void setClientes(List<Cliente> clientes) {
		this.clientes = clientes;
	}

	public Date getDataNasc() {
		try {
			return cliente.getDataNasc().getTime();
		} catch (Exception e) {
			return null;
		}
	}

	public void setDataNasc(Date dataNasc) {

		dataCalendar.setTime(dataNasc);
		cliente.setDataNasc(dataCalendar);
	}
}
