package com.teste.reserva.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public abstract class BaseModel<T> {

	public abstract T createObject(String[] attributes) throws Exception;

	 public static Date parseDate(String data) throws ParseException {
     SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
     sdf.setLenient(false);
     return sdf.parse(data);
 }
	
}
