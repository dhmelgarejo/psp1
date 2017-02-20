package edu.uniandes.ecos.psp1;

import java.util.ArrayList;

public class Estimado {
	private ArrayList<Tamaño> estimados_totales;
	
	public Estimado(ArrayList<ArrayList<String>> lineas_archivos, ArrayList<String> nombres){
		estimados_totales = new ArrayList<Tamaño>();
		for (int i = 0; i < lineas_archivos.size(); i++) {
			Tamaño tamaño_archivo = new Tamaño(lineas_archivos.get(i), nombres.get(i));
			estimados_totales.add(tamaño_archivo);
		}
	}
	
	public void mostrarResutlados(){
		for (int i = 0; i < estimados_totales.size(); i++) {
			System.out.println("Archivo "+estimados_totales.get(i).getNombre()+":");
			//System.out.println("Clases: "+estimados_totales.get(i).getCantidadClases());
			//System.out.println("Metodos: "+estimados_totales.get(i).getCantidadMetodos());
			System.out.println("Clases: ");
			for (int j = 0; j < estimados_totales.get(i).getClases().size(); j++) {
				System.out.println("\t"+estimados_totales.get(i).getClases().get(j).getNombre());
				System.out.println("\t"+estimados_totales.get(i).getClases().get(j).getCantidad_lineas());
				for (int k = 0; k < estimados_totales.get(i).getClases().get(j).getMetodos().size(); k++) {
					System.out.println("\t\t"+estimados_totales.get(i).getClases().get(j).getMetodos().get(k).getNombre());
					System.out.println("\t\t"+estimados_totales.get(i).getClases().get(j).getMetodos().get(k).getLineas());
				}
			}
			System.out.println("Lineas archivo: "+estimados_totales.get(i).getCantidadLineas());
			System.out.println("Tamaño total: "+estimados_totales.get(i).cantidadLineasAux);
			System.out.println("------------------------");
		}
	}
}
