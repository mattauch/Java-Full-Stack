package model.validadores;

public class Email {

//classe n�o poder� ser instanciada
	private Email() {

	}

	// deve conter o @
	// n�o pode conter espa�o
	// deve conter no m�nimo 8 caracteres: a@bc.com
	// deve conter pelo menos 1 caractere antes do @
	// deve conter pelo menos 1 .(ponto) depois do @
	// deve conter pelo menos 2 caracteres entre o ponto e o @
	// deve conter pelo menos 2 caracteres ap�s o �ltimo ponto

	public static boolean isValido(String email) {
		int posArroba = email.indexOf("@");
		int posPonto = email.lastIndexOf("."); // �ltimo ponto

		return (!email.contains(" ") && email.length() >= 8 && posArroba >= 1 && posPonto + 2 > posArroba
				&& email.length() + 1 > posPonto);
	}
}
