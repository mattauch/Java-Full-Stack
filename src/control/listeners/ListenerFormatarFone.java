package control.listeners;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TextField;

public class ListenerFormatarFone extends ListenerController implements ChangeListener<Boolean> {

	public ListenerFormatarFone(TextField textField) {
		super(textField);
	}
	// aceita até 11 dígitos, parenteses, espaço e traço
	// (42) 9 9999-9999

	@Override
	public void changed(ObservableValue<? extends Boolean> observable, Boolean saiu, Boolean entrou) {

		if (saiu) {
			// remove todos os caracteres que não são números (0...9)
			StringBuilder fone = new StringBuilder(getTextField().getText().replaceAll("[\\D]", ""));

			if (fone.length() < 8 || fone.length() > 11) {
				setMensagem("Fone deve conter pelo menos 8 dígitos");
			} else {
				switch (fone.length()) {
				case 8:
					fone.insert(4, '-');
					break;

				case 9:
					fone.insert(5, '-');
					break;

				case 10:
					fone.insert(0, '(');
					fone.insert(3, ')');
					fone.insert(4, ' ');
					fone.insert(9, '-');
					break;

				case 11:
					fone.insert(0, '(');
					fone.insert(3, ')');
					fone.insert(9, '-');
					break;

				}

				getTextField().setText(fone.toString());
				limparErro();
			}
		}
	}

}
