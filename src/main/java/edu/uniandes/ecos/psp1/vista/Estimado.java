package edu.uniandes.ecos.psp1.vista;

import java.util.ArrayList;

import edu.uniandes.ecos.psp1.modelo.Archivo;

/**
	* Esta clase permite imprimir en pantalla el resultado de la ejecucion del programa.
	*
	* @author  Daniel Melgarejo
**/

public class Estimado {
	private ArrayList<Archivo> estimados_totales;
	
	/**
	 * Constructor, recibe como parametro un listado de los estimados de cada archivo
	 * @param estimados_totales
	 */
	public Estimado(ArrayList<Archivo> estimados_totales){
		this.estimados_totales = estimados_totales;
	}
	
	/**
	 * Muestra los resultados de la estimacion del tamaño iterando sobre cada una de las estructuras usadas
	 */
	public void mostrarResutlados(){
		int tamaño_total_final = 0;
		int total_clases = 0;
		int total_metodos = 0;
		//Iterar archivos
		for (int i = 0; i < estimados_totales.size(); i++) {
			System.out.println("Archivo "+estimados_totales.get(i).getNombre()+":");
			System.out.println("Clases: ");
			//Iterar clases
			for (int j = 0; j < estimados_totales.get(i).getClases().size(); j++) {
				System.out.println("\tNombre: "+estimados_totales.get(i).getClases().get(j).getNombre());
				System.out.println("\tLineas: "+estimados_totales.get(i).getClases().get(j).getCantidad_lineas());
				System.out.println("\tMetodos: ");

				total_metodos += estimados_totales.get(i).getClases().get(j).getMetodos().size();
				//Iterar Metodos
				for (int k = 0; k < estimados_totales.get(i).getClases().get(j).getMetodos().size(); k++) {
					System.out.println("\t\tNombre: "+estimados_totales.get(i).getClases().get(j).getMetodos().get(k).getNombre());
					System.out.println("\t\tLineas: "+estimados_totales.get(i).getClases().get(j).getMetodos().get(k).getLineas());
				}
				System.out.println();
			}
			System.out.println("Lineas archivo: "+estimados_totales.get(i).getCantidadLineas());
			System.out.println("Tamaño total: "+estimados_totales.get(i).getCantidadLineasTotales());
			System.out.println("------------------------");
			System.out.println();
			total_clases += estimados_totales.get(i).getClases().size();
			tamaño_total_final += estimados_totales.get(i).getCantidadLineasTotales();
		}
		//Totales
		System.out.println("Resultado");
		System.out.println("Cantidad de clases totales: "+total_clases);
		System.out.println("Cantidad de metodos totales: "+total_metodos);
		System.out.println("Tamaño total: "+tamaño_total_final);
	}
}
