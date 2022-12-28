package com.softgraf.farmacia.dominio;

//**** COM ERRO! Aceita apenas e-mails .com (.xxx), não aceita .xxx.yy) 
public class Email {
	final public static int MAX = 60;
	final public static int MIN = 8;   // a@bc.com
	
	public static boolean validar(String email) {
		email = email.trim();
		int tamanho = email.length();
		boolean isValido = false;
		boolean temEspaco = false;

		if (tamanho >= MIN && tamanho <= MAX) { 
			char[] vetor = email.toCharArray();
			int posArroba = 0;
			int posPonto = 0;
			char c;

			for (int i = 0; i < vetor.length; i++) {
				c = vetor[i];
				
				switch (c) {
				case '@':
					posArroba = i; break;
				case '.':  // ultimo ponto
					posPonto = i; break;
				case ' ':
					temEspaco = true; break;
				}
			}

			// não inicia por arroba E o ponto está há 3 posições depois do arroba E
			// depois do ponto com pelo menos 3 caracteres
			if (posArroba > 0 && posPonto > posArroba + 2 && vetor.length - posPonto - 1 >= 3 && !temEspaco)
				isValido = true;
		}

		return isValido;
	}

	public static String formatar(String email) {
		String em = email.trim().toLowerCase();
		return  validar(em) ? em : "";
	}
}