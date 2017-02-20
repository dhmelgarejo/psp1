package edu.uniandes.ecos.psp1;

//import java.util.Scanner;

public class Principal {
	public static void main(String[] args) {
		//Scanner in = new Scanner(System.in);
		// String path = in.nextLine();
		//String path = "C:\\Users\\Melga\\Downloads\\Envio";
		String path = "C:\\Users\\Melga\\Downloads\\n8_dh.melgarejo";
		//String path = "C:\\Users\\Melga\\Downloads\\n8_dh.melgarejo\\source\\uniandes\\cupi2\\inscripcionCedulas\\interfaz\\Nueva";
		
		//String path = "‪C:\\Users\\Melga\\Downloads\\n8_dh.melgarejo\\source\\uniandes\\cupi2\\inscripcionCedulas\\interfaz\\DialogoEstadisticas.java";
		
		Lector lector = new Lector(path);
		lector.getEstimado_total().mostrarResutlados();
	}
}
