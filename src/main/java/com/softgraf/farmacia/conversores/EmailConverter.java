package com.softgraf.farmacia.conversores;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;
import com.softgraf.farmacia.dominio.Email;

@FacesConverter("emailConverter")
public class EmailConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext contexto, UIComponent campo, String email) {

		boolean existeEmail = !email.trim().isEmpty();
		String emailFormatado = Email.formatar(email);
		
		if (existeEmail && emailFormatado.isEmpty()) {
			FacesMessage mensagem = new FacesMessage(FacesMessage.SEVERITY_WARN,  "E-mail:", "O formato ou tamanho (8 a 60) do e-mail está incorreto!");		
			throw new ConverterException(mensagem);
		} else
			return emailFormatado;
	}
	
	@Override
	public String getAsString(FacesContext contexto, UIComponent campo, Object email) {
		return (String) email;
	}

}
