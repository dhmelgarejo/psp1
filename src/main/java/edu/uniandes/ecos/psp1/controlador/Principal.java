package edu.uniandes.ecos.psp1.controlador;

import java.util.Scanner;

import edu.uniandes.ecos.psp1.vista.Estimado;

/**
	* Programa desarrollado para contar lineas de codigo Java, identificar clases sus respectivos metodo y tamaños
	* ECOS Primer Semestre de 2017
	*
	* @author  Daniel Melgarejo
**/

public class Principal {
	public static void main(String[] args) {
		
		System.out.println("Por favor ingrese una ruta de un proyecto de Java");
		Scanner in = new Scanner(System.in);
		String path = in.nextLine();
		
		Lector lector = new Lector(path);
		Estimado resultados = new Estimado(lector.getEstimados_totales());
		resultados.mostrarResutlados();
		
	}
}
