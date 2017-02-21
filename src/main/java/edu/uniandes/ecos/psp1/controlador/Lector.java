package edu.uniandes.ecos.psp1.controlador;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import edu.uniandes.ecos.psp1.modelo.Archivo;

/**
	* Esta clase permite intrepretar un directorio buscando los archivos .java y organizandolos para ser procesados.
	*
	* @author  Daniel Melgarejo
**/
public class Lector {
	private ArrayList<ArrayList<String>> lineas_archivos;
	private ArrayList<String> nombres_archivos;
	private ArrayList<Archivo> estimados_totales;
	
	/**
	 * Constructor
	 * @param path
	 */
	public Lector(String path){
		estimados_totales = new ArrayList<Archivo>();
		lineas_archivos = new ArrayList<ArrayList<String>>();
		nombres_archivos = new ArrayList<String>();
		LeerDirectorio(path);
		for (int i = 0; i < lineas_archivos.size(); i++) {
			Archivo tamaño_archivo = new Archivo(lineas_archivos.get(i), nombres_archivos.get(i));
			estimados_totales.add(tamaño_archivo);
		}
	}
	
	/**
	 * Lee un directorio del sistema operativo
	 * @param path
	 */
	private void LeerDirectorio(String path){
		File folder = new File(path);
		File[] listOfFiles = folder.listFiles();
		if(listOfFiles != null){
		    for (int i = 0; i < listOfFiles.length; i++) {
			      if (listOfFiles[i].isFile()) {
			    	  String type = listOfFiles[i].getName().substring(listOfFiles[i].getName().indexOf('.'));
			    	  if(type.toLowerCase().equals(".java")){
			    		  nombres_archivos.add(listOfFiles[i].getName());
			    		  lineas_archivos.add(LeerLineas(listOfFiles[i]));
			    	  }
			      } else if (listOfFiles[i].isDirectory()) {
			    	  LeerDirectorio(listOfFiles[i].getAbsolutePath());
			      }
			 }
		}else{
		    if (folder.isFile()) {
		    	String type = folder.getName().substring(folder.getName().indexOf('.'));
		    	if(type.toLowerCase().equals(".java")){
		    		nombres_archivos.add(folder.getName());
		    		lineas_archivos.add(LeerLineas(folder));
		    	}
		    } 
		}
	}
	
	/**
	 * Procesa un archivo y retorna todas las lineas que no esten vacias
	 * @param file
	 * @return ArrayList<String> Estructura de datos de todas las lineas de un archivo .java
	 */
	public ArrayList<String> LeerLineas(File file){
		ArrayList<String> lineas = new ArrayList<String>();
		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
		    String line;
		    try {
				while ((line = br.readLine()) != null) {
					if( line.trim() != ""){
						lineas.add(line);
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		return lineas;
	}
	/**
	 * Gets
	 * @return
	 */
	public ArrayList<Archivo> getEstimados_totales() {
		return estimados_totales;
	}
	
}
