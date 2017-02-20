package edu.uniandes.ecos.psp1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Lector {
	private ArrayList<ArrayList<String>> lineas_archivos;
	private Estimado estimado_total;
	private ArrayList<String> nombres_archivos;
	
	public Lector(String path){
		lineas_archivos = new ArrayList<ArrayList<String>>();
		nombres_archivos = new ArrayList<String>();
		LeerDirectorio(path);
		estimado_total = new Estimado(lineas_archivos, nombres_archivos);
	}
	
	private void LeerDirectorio(String path){
		File folder = new File(path);
		File[] listOfFiles = folder.listFiles();
		if(listOfFiles != null){
		    for (int i = 0; i < listOfFiles.length; i++) {
			      if (listOfFiles[i].isFile()) {
			    	  String type = listOfFiles[i].getName().substring(listOfFiles[i].getName().indexOf('.'));
			    	  if(type.toLowerCase().equals(".java")){
			    		  System.out.println(listOfFiles[i].getName());
			    		  nombres_archivos.add(listOfFiles[i].getName());
			    		  lineas_archivos.add(LeerLineas(listOfFiles[i]));
			    		  
					      System.out.println("File " + listOfFiles[i].getName());
			    	  }
			      } else if (listOfFiles[i].isDirectory()) {
			    	  LeerDirectorio(listOfFiles[i].getAbsolutePath());
			      }
			 }
		}else{
			System.out.println(folder.getName());
				String type = folder.getName().substring(folder.getName().indexOf('.'));
				if(type.toLowerCase().equals(".java")){
					lineas_archivos.add(LeerLineas(folder));
	    		  
					System.out.println("File " + folder.getName());
				}
			
		}
	}
	
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

	public Estimado getEstimado_total() {
		return estimado_total;
	}
	
	
}
