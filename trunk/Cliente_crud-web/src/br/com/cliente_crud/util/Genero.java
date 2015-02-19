package br.com.cliente_crud.util;

public enum Genero {
	A��o(1), 
	Aventura(2), 
	Corrida(3), 
	Esporte(4), 
	Estrat�gia(5), 
	FPS(6), 
	Luta(7), 
	RPG(8);

	private final int valor;

	Genero(int valorGenero) {
		valor = valorGenero;
	}

	public int getValor() {
		return valor;
	}

}
