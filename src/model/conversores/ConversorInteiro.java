package model.conversores;

import javafx.util.StringConverter;

// Converte uma String em um Inteiro e um Inteiro em uma String
public class ConversorInteiro extends StringConverter<Integer> {

	@Override
	public String toString(Integer objeto) {
		return objeto == null ? "0" : objeto.toString();
	}

	@Override
	public Integer fromString(String string) {
		return Integer.parseInt(string);
	}

}
