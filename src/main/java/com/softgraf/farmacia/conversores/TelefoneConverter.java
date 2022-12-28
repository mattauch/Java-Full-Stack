package com.softgraf.farmacia.conversores;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import com.softgraf.farmacia.dominio.Telefone;
 
@FacesConverter("telefoneConverter")
public class TelefoneConverter implements Converter {
	
	// String para objeto
	@Override
	public Object getAsObject(FacesContext contexto, UIComponent campo, String fone) {

		boolean existeFone = !fone.trim().isEmpty();
		String foneFormatado = Telefone.formatar(fone);
		
		if (existeFone && foneFormatado.isEmpty()) {
			FacesMessage mensagem = new FacesMessage(FacesMessage.SEVERITY_WARN,  "Telefone:", "O formato do telefone digitado está incorreto!");		
			throw new ConverterException(mensagem);
		} else
			return foneFormatado;
	}
	
	// Objeto para string
	@Override
	public String getAsString(FacesContext contexto, UIComponent campo, Object fone) {
		return (String) fone;
	}

}
