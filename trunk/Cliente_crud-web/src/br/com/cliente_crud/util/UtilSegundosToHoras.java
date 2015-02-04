package br.com.cliente_crud.util;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter(value = "segundosToHorasConverter")
public class UtilSegundosToHoras implements Converter {

	@Override
	public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String objeto) {
		return objeto;
	}

	@Override
	public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object objeto) {
		long objetoLong = (Long) objeto;
		int segundos = (int) objetoLong;   
		int segundo = segundos % 60;   
		int minutos = segundos / 60;   
		int minuto = minutos % 60;   
		int hora = minutos / 60;   
		String hms = String.format ("%02d:%02d:%02d", hora, minuto, segundo);   
		return hms;
	}

}
