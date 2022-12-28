package com.softgraf.farmacia.dominio;

public class Cnpj {
	final public static int MAX = 16;
	
	private static final int[] pesoCNPJ = { 6, 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2 };

	private static int calcularDigito(String str, int[] peso) {
		int soma = 0;
		for (int indice = str.length() - 1, digito; indice >= 0; indice--) {
			digito = Integer.parseInt(str.substring(indice, indice + 1));
			soma += digito * peso[peso.length - str.length() + indice];
		}
		soma = 11 - soma % 11;
		return soma > 9 ? 0 : soma;
	}

	public static boolean validar(String cnpj) {
		if ((cnpj == null) || (cnpj.length() != 14))
			return false;

		Integer digito1 = calcularDigito(cnpj.substring(0, 12), pesoCNPJ);
		Integer digito2 = calcularDigito(cnpj.substring(0, 12) + digito1, pesoCNPJ);
		return cnpj.equals(cnpj.substring(0, 12) + digito1.toString() + digito2.toString());
	}

	// inclui os caracteres '/' e '-' (barra e traço) no cnpj
	// 01105042/0001-50
	public static String formatar(String cpf) {
		StringBuilder sb = new StringBuilder();
		cpf.trim().chars().forEach(c -> {
			sb.append((char) c);
			// 12345678/0123-56
			if (sb.length() == 8)
				sb.append('/');
			else if (sb.length() == 13)
				sb.append('-');
		});

		return sb.toString();
	}
}
