package model.validadores;

public class Cnpj {

	// Classe não poderá ser instanciada
	private Cnpj() {
	}

	private static final int[] pesoCNPJ = { 6, 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2 };

	// Exemplo: "17726829000179"
	public static boolean isValido(String cnpj) {
		if (cnpj == null)
			return false;

		// remove todos os caracteres que não são números (0...9)
		cnpj = cnpj.replaceAll("[\\D]", "");
		if (cnpj.length() != 14)
			return false;

		Integer digito1 = calcularDigito(cnpj.substring(0, 12), pesoCNPJ);
		Integer digito2 = calcularDigito(cnpj.substring(0, 12) + digito1, pesoCNPJ);
		return cnpj.equals(cnpj.substring(0, 12) + digito1.toString() + digito2.toString());
	}

	private static int calcularDigito(String str, int[] peso) {
		int soma = 0;
		for (int indice = str.length() - 1, digito; indice >= 0; indice--) {
			digito = Integer.parseInt(str.substring(indice, indice + 1));
			soma += digito * peso[peso.length - str.length() + indice];
		}
		soma = 11 - soma % 11;
		return soma > 9 ? 0 : soma;
	}
}
