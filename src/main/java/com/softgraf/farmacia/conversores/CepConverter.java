package com.softgraf.farmacia.conversores;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import com.softgraf.farmacia.dominio.Cep;

//um conversor transforma strings em objetos e objetos em strings
@FacesConverter("cepConverter")
public class CepConverter implements Converter {

	//String para objeto. Pode lançar uma exceção do tipo ConverterException.
	//Devolve "12345-789"
	@Override
	public Object getAsObject(FacesContext context, UIComponent campo, String cep) {
		//faz a validação do cep
		boolean existeCep = !cep.trim().isEmpty(); //True or False
		String cepFormatado = Cep.formatar(cep);  //"12345-789"
		
		if (existeCep && cepFormatado.isEmpty()) {
			FacesMessage mensagem = new FacesMessage(FacesMessage.SEVERITY_WARN, "CEP:", "Cep deve conter 8 dígitos numéricos ou nenhum!");
			
			throw new ConverterException(mensagem);
		}else {
			return cepFormatado;
		}
		
	}

	//recebe objeto e devolve string
	@Override
	public String getAsString(FacesContext context, UIComponent campo, Object cep) {
		return (String) cep;
	}

}
