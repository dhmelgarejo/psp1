package edu.uniandes.ecos.psp1;

import java.util.ArrayList;

public class Estimado {
	private ArrayList<Tama�o> estimados_totales;
	
	public Estimado(ArrayList<ArrayList<String>> lineas_archivos, ArrayList<String> nombres){
		estimados_totales = new ArrayList<Tama�o>();
		for (int i = 0; i < lineas_archivos.size(); i++) {
			Tama�o tama�o_archivo = new Tama�o(lineas_archivos.get(i), nombres.get(i));
			estimados_totales.add(tama�o_archivo);
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
			System.out.println("Tama�o total: "+estimados_totales.get(i).cantidadLineasAux);
			System.out.println("------------------------");
		}
	}
}
