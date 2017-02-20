package edu.uniandes.ecos.psp1.modelo;

/**
 * Clase para almacenar informacion relacionada a un metodo de un archivo .java
 * @author Melga
 *
 */
public class Metodo {
	private String nombre;
	private int lineas;
	
	public Metodo(String nombre){
		this.nombre = nombre;
		this.lineas = 0;
	}

	public String getNombre() {
		return nombre;
	}

	public int getLineas() {
		return lineas;
	}

	public void setLineas(int lineas) {
		this.lineas = lineas;
	}
	
	
}
