package br.com.cliente_crud.dao;

import java.util.List;

import br.com.cliente_crud.entity.Videogame;

public interface VideogameDAO {
	
	/**
	 * 
	 */
	public List<Videogame> listarVideogameDisponivel();
}
