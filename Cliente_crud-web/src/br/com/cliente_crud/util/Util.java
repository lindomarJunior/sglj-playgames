package br.com.cliente_crud.util;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter(value = "tempoConverter")
public class Util implements Converter {

	@Override
	public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String objeto) {
		if (objeto.trim() != "") {
			Integer resultSegundos = 0;

			// obtem posição
			int posicao = objeto.indexOf(":") - 2;

			String h = objeto.substring(posicao, posicao + 2);
			String m = objeto.substring(posicao + 3, posicao + 5);

			int hora = Integer.parseInt(h) * 3600;
			int minutos = Integer.parseInt(m) * 60;

			resultSegundos = hora + minutos;
			Long numero = resultSegundos.longValue();

			return numero;
		}
		return objeto;
	}

	@Override
	public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object objeto) {
		return objeto.toString();
	}

}
