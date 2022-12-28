package com.softgraf.farmacia.dominio;

public class Cpf {
	final public static int MAX = 12;
	
	// não valida campos tipo: "111111111-11"
	public static boolean validar(String cpf) {
		boolean ret = false;
		
		if (cpf.length() == 11) {
			String digitos = cpf.substring(9, 11);
			int soma = 0, mult = 11;
			int[] var = new int[11];

			// Recebe os números e realiza a multiplicação e soma.
			for (int i = 0; i < 9; i++) {
				var[i] = Integer.parseInt("" + cpf.charAt(i));
				if (i < 9)
					soma += (var[i] * --mult);
			}

			// Cria o primeiro dígito verificador.
			int resto = soma % 11;
			if (resto < 2) {
				var[9] = 0;
			} else {
				var[9] = 11 - resto;
			}

			// Reinicia os valores.
			soma = 0;
			mult = 11;

			// Realiza a multiplicação e soma do segundo dígito.
			for (int i = 0; i < 10; i++)
				soma += var[i] * mult--;

			// Cria o segundo dígito verificador.
			resto = soma % 11;
			if (resto < 2) {
				var[10] = 0;
			} else {
				var[10] = 11 - resto;
			}

			if ((digitos.substring(0, 1).equalsIgnoreCase(Integer.valueOf(var[9]).toString()))
					&& (digitos.substring(1, 2).equalsIgnoreCase(Integer.valueOf(var[10]).toString()))) {
				ret = true;
			}
		}

		return ret;
	}
	
	// inclui o caractere '-' (traço) no cpf
	public static String formatar(String cpf) {
		StringBuilder sb = new StringBuilder();
		cpf.trim().chars().forEach(c -> {
			sb.append((char) c);
			// 123456789-00
			if (sb.length() == 9) {
				sb.append('-');
			}
		});
		
		return sb.toString();
	}
}
