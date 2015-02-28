package br.com.cliente_crud.bean;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

@RequestScoped
@Named
public class Teste {
	public void testar(){
		String a = "b";
	}
}
