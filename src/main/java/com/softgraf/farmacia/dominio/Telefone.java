package com.softgraf.farmacia.dominio;

public class Telefone {
	final public static int MAX = 14;
	
	// fones válidos: "12345678", "912345678", "0012345678", "00912345678"
	// fomatos: 0123-4567, 90123-4678, (xx) 0123-4567, (xx) 90123-4567
	// número de caracteres válidos: 8, 9, 10, 11 -> 8 a 11
	// se o formato do telefone for inválido retorna ""
	public static String formatar(String fone) {
		StringBuilder sb = new StringBuilder();
		// remove caracteres não numéricos
		fone.trim().chars().forEach(c -> {
			if (c >= '0' && c <= '9') {
				sb.append((char) c);
			}
		});

		switch (sb.length()) {

			case 8:  // 0123-4567 -> acrescenta '-'
				return sb.insert(4, '-').toString();

			case 9:   // 90123-4567 -> acrescenta '-'
				return sb.insert(5, '-').toString();

			case 10:   // (xx)0123-4567 -> acrescenta (  ) e '-'
				return sb.insert(0, '(')
						 .insert(3, ')')
				         .insert(4, ' ')
				         .insert(9, '-')
				         .toString();

			case 11:   // (xx)90123-4567 -> acrescenta (  ) e '-'
				return sb.insert(0, '(')
				         .insert(3, ')')
				         .insert(9, '-')
				         .toString();
				
			default:
				return "";
			
		}
		
	}
}
