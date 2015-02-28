package br.com.cliente_crud.util;

import java.text.DecimalFormat;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter(value = "valorConverter")
public class ValorConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String objeto) {
		// TODO Auto-generated method stub
		return new Float(objeto.replaceAll(",", "."));
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object objeto) {
		DecimalFormat df = new DecimalFormat("00.00");
		
		return df.format(objeto);
	}

}
