package control.listeners;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TextField;

public class ListenerFormatarCep extends ListenerController implements ChangeListener<Boolean> {

	public ListenerFormatarCep(TextField textField) {
		super(textField);
	}

	@Override
	public void changed(ObservableValue<? extends Boolean> observable, Boolean saiu, Boolean entrou) {

		if (saiu) {
			// remove todos os caracteres que não são números (0 ... 9)
			StringBuilder cep = new StringBuilder(getTextField().getText().replaceAll("[\\D]", ""));

			if (cep.length() != 8) {
				setMensagem("Cep deve conter 8 dígitos");
			} else {
				cep.insert(5, '-');
				getTextField().setText(cep.toString());
				limparErro();
			}
		}
	}

}
