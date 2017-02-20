package edu.uniandes.ecos.psp1;

import java.util.ArrayList;

public class Tamaño {
	private String nombre;
	private int cantidadLineas;
	public int cantidadLineasAux;
	private ArrayList<Clase> clases;
	private String clase_nombre_auxiliar;
	private String metodo_nombre_auxiliar;
	
	private final String[] controladoresAcceso = {"public", "private", "protected"};
	
	public Tamaño(ArrayList<String> lineas, String nombre){
		clases = new ArrayList<Clase>();
		cantidadLineasAux = 0;
		cantidadLineas = 0;
		clase_nombre_auxiliar = "";
		metodo_nombre_auxiliar = "";
		calcularTamaño(lineas);
		this.nombre = nombre;
	}
	
	private void calcularTamaño(ArrayList<String> lineas){
		String linea = "";
		for (int i = 0; i < lineas.size(); i++) {
			linea = lineas.get(i).trim();
			//System.out.println(linea);
			if(linea.equals("")) continue;
			
			if(esClase(linea)){
				//System.out.println("CLASE");
				clases.add(new Clase(clase_nombre_auxiliar));
			}else{
				if(esMetodo(linea)){
					clases.get(clases.size()-1).getMetodos().add(new Metodo(metodo_nombre_auxiliar));
					//System.out.println("METODO");
				}else{
					if(esLinea(linea)){
						//System.out.println("LINEA");
						if( ! clase_nombre_auxiliar.equals("") ){
							clases.get(clases.size()-1).setCantidad_lineas(clases.get(clases.size()-1).getCantidad_lineas() + 1); 
						}else{
							if(! metodo_nombre_auxiliar.equals("")){
								clases.get(clases.size()-1).getMetodos().get(clases.get(clases.size()-1).getMetodos().size()-1).setLineas(clases.get(clases.size()-1).getMetodos().get(clases.get(clases.size()-1).getMetodos().size()-1).getLineas() + 1);
							}else{
								cantidadLineas++;
							}
						}
						cantidadLineasAux++;
					}
				}
			}
		}
	}
	
	private boolean esClase(String linea){
		String[] palabras = linea.split("\\s+");
		// class algo{}
		if(palabras.length > 2){
			if(palabras[0].equals("class")){
				clase_nombre_auxiliar = palabras[1].charAt(palabras[1].length()-1) == '{' ? palabras[1].substring(0, palabras[1].length()-1) : palabras[1];
				return true;
			}
		}

		// public class algo{
		if(palabras.length > 3){
			if(palabras[1].equals("class")){
				clase_nombre_auxiliar = palabras[2].charAt(palabras[2].length()-1) == '{' ? palabras[2].substring(0, palabras[2].length()-1) : palabras[2];
				return true;
			}
		}

		// public final class algo{
		if(palabras.length > 4){
			if(palabras[2].equals("class")){
				clase_nombre_auxiliar = palabras[3].charAt(palabras[3].length()-1) == '{' ? palabras[3].substring(0, palabras[3].length()-1) : palabras[3];
				return true;
			}
		}
			
		return false;
	}
	private boolean esMetodo(String linea){
		//if(palabras.length > 2 && palabras[palabras.length-1].charAt(palabras[palabras.length-1].length()-1) == '{'){
		if(esComentario(linea))
			return false;
		if(linea.indexOf("(") != -1){
			String[] palabras = linea.substring(0, linea.indexOf("(")).split("\\s+");
			if(palabras.length > 1){
				if(tieneCaracteresEspeciales(linea) || esDeclaracion(linea))
					return false;
				boolean modificador = false;
				for (int i = 0; i < controladoresAcceso.length; i++){
					if(palabras[0].equals(controladoresAcceso[i]))
						modificador = true;
				}
				//System.out.println(modificador);
				if(modificador){
					try {
						int indice_parentecis = palabras[1].equals("static") ? 3 :  2;
						indice_parentecis = palabras.length == 3 ? 1 :  3;
						
						if(tieneParentesis(palabras[indice_parentecis]) || tieneParentesis(palabras[indice_parentecis+1])
							|| mayus(palabras[indice_parentecis]) || mayus(palabras[indice_parentecis+1])){
							metodo_nombre_auxiliar = palabras[palabras.length-1];
							clase_nombre_auxiliar = "";
							return true;
						}
					} catch (Exception e) {}
				}else{
					try {
						int indice_parentecis = palabras[0].equals("static") ? 1 :  0;
						if(tieneParentesis(palabras[indice_parentecis]) || tieneParentesis(palabras[indice_parentecis+1])
							|| mayus(palabras[indice_parentecis]) || mayus(palabras[indice_parentecis+1])){
							metodo_nombre_auxiliar = palabras[palabras.length-1];
							clase_nombre_auxiliar = "";
							return true;
						}
					} catch (Exception e) {}
				}
			}
		}
		return false;
		
	}
	private boolean esLinea(String linea){
		String[] palabras = linea.split("\\s+");
		if(palabras[0].length() > 2)
		if(esComentario(palabras[0]))
			return false;
		
		return true;
	}
	
	private boolean esComentario(String linea){
		if(linea.length() > 2)
			if(linea.substring(0, 2).equals("//") || linea.substring(0, 2).equals("/*") || linea.substring(0, 2).equals("*/"))
				return true;
		if(linea.length() > 1)
			if(linea.substring(0, 1).equals("*"))
				return true;
		
		return false;
	}
	
	private boolean tieneParentesis(String linea){
		if(linea.indexOf("(") != -1 )
			return true;
		return false;
	}
	
	private boolean tieneCaracteresEspeciales(String linea){
		if(linea.indexOf(".") != -1 )
			return true;
		if(linea.indexOf(";") != -1 )
			return true;
		if(linea.indexOf("=") != -1 )
			return true;
		if(linea.indexOf("==") != -1 )
			return true;
		return false;
	}
	
	private boolean mayus(String linea){
		if(linea.charAt(0) < 97)
			return true;
		return false;
	}
	
	private boolean esCondicional(String linea){
		if(linea.length() > 2)
			if(linea.substring(0, 2).equals("if"))
				return true;
		if(linea.length() > 5)
			if(linea.substring(0, 5).equals("switch"))
				return true;
		return false;
	}
	
	private boolean esBucle(String linea){
		if(linea.length() > 3)
			if(linea.substring(0, 3).equals("for"))
				return true;
		if(linea.length() > 2)
			if(linea.substring(0, 2).equals("do"))
				return true;
		if(linea.length() > 5)
			if(linea.substring(0, 5).equals("while"))
				return true;
		return false;
	}
	
	private boolean esPalabraReservada(String linea){
		if(linea.length() > 5)
			if(linea.substring(0, 5).equals("catch"))
				return true;
		return false;
	}
	
	private boolean esDeclaracion(String linea){
		if(esPalabraReservada(linea) || esBucle(linea) || esCondicional(linea))
			return true;
		return false;
	}
	
	public int getCantidadLineas() {
		return cantidadLineas;
	}

	public ArrayList<Clase> getClases() {
		return clases;
	}

	public String getNombre() {
		return nombre;
	}
	
	
}
