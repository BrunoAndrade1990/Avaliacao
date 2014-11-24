package br.conversor;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter(value="conversorServico")
public class ConversorServico implements Converter {

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent componente, String valor) {

		return null;
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent componente, Object valor) {
		
		return null;
	}

}
