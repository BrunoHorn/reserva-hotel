package com.teste.reserva.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import com.teste.reserva.model.BaseModel;

public class UploadCsvUtils<T extends BaseModel<T>> {
	
	public List<? extends BaseModel<?>> uploadCSVFile(T t, String url) {

		File csvFile = new File(url);	           
		List<T> results = new ArrayList<T>();
		
		try {		
			BufferedReader reader = new BufferedReader(new FileReader(csvFile));
    	   
			String line = reader.readLine();
		 
			int cont = 0;    			   
			while (line != null) { 
				if (cont != 0) { 
					String[] attributes = removeUndefinedCaracteres(line);
            		
					var obj = t.createObject(attributes);                	           	 
					results.add(obj);
				}
				line = reader.readLine(); 
				cont++;
			}               
			return results; 
		} catch (Exception e) {
			  new Exception(e.getMessage());
			  return null;
		}
	}
	
	private String[] removeUndefinedCaracteres(String line) {
		line = line.replaceAll("\"", "");
		String[] attributes = line.split(","); 
		
		for (int i = 0; i < attributes.length; i++) {
			attributes[i] = attributes[i].replaceAll("\\\\N","");
		}
		
		return attributes;
	}
	
}
