package com.softgraf.farmacia.dominio;

/*
 * 123456789
 * 84051-290 = 9
 */
public class Cep {
	
	final public static int MAX =9;

	public static String formatar(String cep) {
		StringBuilder sb = new StringBuilder();
		//trim() - cortar os espaços do início e do final
		//chars() - retorna um fluxo de inteiros => stream de inteiros (programação funcional)
		//processa cada caracter do cep
		cep.trim().chars().forEach(c ->{
			//se for um número, junta em sb (StringBuilder)
			if(c >= '0' && c <= '9') {
				sb.append((char)c);
			}
			
			if(sb.length() == 5) {
				sb.append('-');
			}
		});
		//operador ternário: validação = ? se : senão
 		//                       ?=if           :=else 
		return (sb.length() == 9) ? sb.toString() : "";
	}
}
