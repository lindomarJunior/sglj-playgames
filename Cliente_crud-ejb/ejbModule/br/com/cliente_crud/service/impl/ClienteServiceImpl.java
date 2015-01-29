package br.com.cliente_crud.service.impl;

import javax.ejb.Stateless;

import br.com.cliente_crud.service.ClienteService;

@Stateless
public class ClienteServiceImpl implements ClienteService {

	@Override
	public String retornaNome() {
		// TODO Auto-generated method stub
		return "Lindomar";
	}

	

}
