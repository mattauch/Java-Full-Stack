package model;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import entidades.Editora;

@FacesConverter ("editoraConverter")
public class EditoraConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		if (value != null && !value.isEmpty()) {
			return (Editora) component.getAttributes().get(value);
		}
		return null;
	}

	@Override             //contexto do PrimeFaces
 	public String getAsString(FacesContext context, UIComponent component, Object value) {
			if(value instanceof Editora) {
				Editora editora = (Editora) value;
				if(editora != null && 
						editora instanceof Editora &&
						editora.getNomeEditora() != null &&
						!editora.getNomeEditora().isEmpty() &&
						editora.getId() != null) {
					component.getAttributes().put(editora.getId().toString(), editora);
					
					return editora.getId().toString();
				}
			}
		return null;
	}

	
	
}
