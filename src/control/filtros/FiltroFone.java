package control.filtros;

import javafx.event.EventHandler;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

public class FiltroFone implements EventHandler<KeyEvent> {

	// aceita at� 11 digitos, (), espa�o e -
	// (42) 98765-4321

	@Override
	public void handle(KeyEvent e) {

		if (e.getSource() instanceof TextField) {
			TextField tf = (TextField) e.getSource();
			String fone = tf.getText();
			char c = e.getCharacter().charAt(0);

			// determina quantos carcateres num�ricos existem
			int digitos = fone.replaceAll("[\\D]", "").length();

			if ((Character.isDigit(c) && digitos >= 11) || (!Character.isDigit(c)
					&& (fone.contains(Character.toString(c)) || !"()-".contains(Character.toString(c)))))
				e.consume();
		}
	}

}
