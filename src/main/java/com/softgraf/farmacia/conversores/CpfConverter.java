package com.softgraf.farmacia.conversores;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import com.softgraf.farmacia.dominio.Cnpj;
import com.softgraf.farmacia.dominio.Cpf;

@FacesConverter("cpfCnpjConverter")
public class CpfConverter implements Converter {
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent campo, String cpfCnpj) {
		if(cpfCnpj.trim().isEmpty())
			return null;
		
		StringBuilder sb = new StringBuilder();
		//remove caracteres não numéricos através do forEach funcional
		cpfCnpj.trim().chars().forEach(c -> {
			if(c >='0' && c <='9') //'9' = um caracter (char)- letra (tabela Accis
				sb.append((char) c);
		});
		
		String sbStr = sb.toString();
		
		//tenta validar como CPF e formata
		if(Cpf.validar(sbStr)) {
			return Cpf.formatar(sbStr);
		
		//tenta validar como CNPJ e formata
		}else if (Cnpj.validar(sbStr))
			return Cnpj.formatar(sbStr);
		
		else {
			FacesMessage mensagem = new FacesMessage(FacesMessage.SEVERITY_WARN, "CPF/ CNPJ:", "O campo CPF/ CNPJ está incorreto!");
			throw new ConverterException(mensagem);
		}
	}

	@Override
	public String getAsString(FacesContext context, UIComponent campo, Object cpfCnpj) {
		
		return (String) cpfCnpj;
	}

}
