package edu.uniandes.ecos.psp1;

import java.util.ArrayList;

public class Clase {
	private String nombre;
	private ArrayList<Metodo> metodos;
	private int cantidad_lineas;
	
	public Clase(String nombre){
		this.nombre = nombre;
		metodos = new ArrayList<Metodo>();
		cantidad_lineas = 0;
	}

	public String getNombre() {
		return nombre;
	}

	public ArrayList<Metodo> getMetodos() {
		return metodos;
	}

	public int getCantidad_lineas() {
		return cantidad_lineas;
	}

	public void setCantidad_lineas(int cantidad_lineas) {
		this.cantidad_lineas = cantidad_lineas;
	}
	
}