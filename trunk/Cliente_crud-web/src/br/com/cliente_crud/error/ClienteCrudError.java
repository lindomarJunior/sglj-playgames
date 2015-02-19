package br.com.cliente_crud.error;

public class ClienteCrudError extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = -222511906455857879L;

	public ClienteCrudError(String message){
		super(message);
	}
}
