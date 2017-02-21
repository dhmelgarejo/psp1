package edu.uniandes.ecos.psp1.modelo;

import java.util.ArrayList;

/**
 * Clase para almacenar los archivos .java procesados
 * @author  Daniel Melgarejo
 */
public class Archivo {
	private String nombre;
	private int cantidadLineas;
	private int cantidadLineasTotales;
	private ArrayList<Clase> clases;
	private String clase_nombre_auxiliar;
	private String metodo_nombre_auxiliar;
	
	public Archivo(ArrayList<String> lineas, String nombre){
		clases = new ArrayList<Clase>();
		cantidadLineasTotales = 0;
		cantidadLineas = 0;
		clase_nombre_auxiliar = "";
		metodo_nombre_auxiliar = "";
		this.nombre = nombre;
		calcularTamaño(lineas);
	}
	
	/**
	 * Procesa la informacion de una linea del archivo para determinar el tipo de la misma
	 * @param lineas
	 */
	private void calcularTamaño(ArrayList<String> lineas){
		String linea = "";
		for (int i = 0; i < lineas.size(); i++) {
			linea = lineas.get(i).trim();
			if(linea.equals("")) continue; // Ignora lineas vacias
			
			if(esClase(linea)){
				clases.add(new Clase(clase_nombre_auxiliar));
			}else{
				if(esMetodo(linea)){
					clases.get(clases.size()-1).getMetodos().add(new Metodo(metodo_nombre_auxiliar));
				}else{
					if(esLinea(linea)){
						//Agrupar con la ultima clase o metodo registrado para identificar a que parte pertenece
						if( ! clase_nombre_auxiliar.equals("") ){
							clases.get(clases.size()-1).setCantidad_lineas(clases.get(clases.size()-1).getCantidad_lineas() + 1); 
						}else{
							if(! metodo_nombre_auxiliar.equals("")){
								clases.get(clases.size()-1).getMetodos().get(clases.get(clases.size()-1).getMetodos().size()-1).setLineas(clases.get(clases.size()-1).getMetodos().get(clases.get(clases.size()-1).getMetodos().size()-1).getLineas() + 1);
							}else{
								cantidadLineas++;
							}
						}
						cantidadLineasTotales++;
					}
				}
			}
		}
	}
	
	/**
	 * Determina si una lina de codigo es una clase
	 * @param linea
	 * @return
	 */
	private boolean esClase(String linea){
		String[] palabras = linea.split("\\s+");
		
		if(palabras.length > 2){
			if(palabras[0].equals("class")){
				clase_nombre_auxiliar = palabras[1].charAt(palabras[1].length()-1) == '{' ? palabras[1].substring(0, palabras[1].length()-1) : palabras[1];
				return true;
			}
		}

		if(palabras.length > 3){
			if(palabras[1].equals("class")){
				clase_nombre_auxiliar = palabras[2].charAt(palabras[2].length()-1) == '{' ? palabras[2].substring(0, palabras[2].length()-1) : palabras[2];
				return true;
			}
		}

		if(palabras.length > 4){
			if(palabras[2].equals("class")){
				clase_nombre_auxiliar = palabras[3].charAt(palabras[3].length()-1) == '{' ? palabras[3].substring(0, palabras[3].length()-1) : palabras[3];
				return true;
			}
		}
			
		return false;
	}
	
	/**
	 * Determina si una lina de codigo es un metodo
	 * @param linea
	 * @return
	 */
	private boolean esMetodo(String linea){
		if(esComentario(linea))
			return false;
		if(linea.indexOf("(") != -1){
			String[] palabras = linea.substring(0, linea.indexOf("(")).split("\\s+"); //Palabras antes  de (
			if(palabras.length > 1){
				if(tieneCaracteresEspeciales(linea) || esDeclaracion(linea))
					return false;
				metodo_nombre_auxiliar = palabras[palabras.length-1];
				clase_nombre_auxiliar = "";
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Determina si una lina de codigo es una linea de codigo, ignora comentarios
	 * @param linea
	 * @return
	 */
	private boolean esLinea(String linea){
		String[] palabras = linea.split("\\s+");
		if(palabras[0].length() > 2)
		if(esComentario(palabras[0]))
			return false;
		
		return true;
	}

	/**
	 * Determina si una lina de codigo es un comentario
	 * @param linea
	 * @return
	 */
	private boolean esComentario(String linea){
		if(linea.length() > 2)
			if(linea.substring(0, 2).equals("//") || linea.substring(0, 2).equals("/*") || linea.substring(0, 2).equals("*/"))
				return true;
		if(linea.length() > 1)
			if(linea.substring(0, 1).equals("*"))
				return true;
		
		return false;
	}


	/**
	 * Determina si una linea del archivo tiene caracteres especiales para establecerla como LOC
	 * @param linea
	 * @return
	 */
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

	/**
	 * Determina si una linea del archivo es una condicion
	 * @param linea
	 * @return
	 */
	private boolean esCondicional(String linea){
		if(linea.length() > 2)
			if(linea.substring(0, 2).equals("if"))
				return true;
		if(linea.length() > 5)
			if(linea.substring(0, 5).equals("switch"))
				return true;
		return false;
	}


	/**
	 * Determina si una linea del archivo es un bucle
	 * @param linea
	 * @return
	 */
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

	/**
	 * Determina si una linea del archivo empieza con una palabra reservada
	 * @param linea
	 * @return
	 */
	private boolean esPalabraReservada(String linea){
		if(linea.length() > 5)
			if(linea.contains("catch"))
				return true;
		return false;
	}

	/**
	 * Agrupa ejecucion de declaraciones para determinar el estado de una linea
	 * @param linea
	 * @return
	 */
	private boolean esDeclaracion(String linea){
		if(esPalabraReservada(linea) || esBucle(linea) || esCondicional(linea))
			return true;
		return false;
	}
	
	/*
	 * GETS
	 */
	public int getCantidadLineas() {
		return cantidadLineas;
	}

	public ArrayList<Clase> getClases() {
		return clases;
	}

	public String getNombre() {
		return nombre;
	}

	public int getCantidadLineasTotales() {
		return cantidadLineasTotales;
	}
	
	
}
