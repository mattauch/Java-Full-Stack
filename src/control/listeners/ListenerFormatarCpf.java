package control.listeners;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TextField;
import model.validadores.Cpf;

public class ListenerFormatarCpf extends ListenerController implements ChangeListener<Boolean> {

	public ListenerFormatarCpf(TextField textField) {
		super(textField);
	}

	@Override
	public void changed(ObservableValue<? extends Boolean> observable, Boolean saiu, Boolean entrou) {

		if (saiu) {
			// remove todos os caracteres que não sejam números (0...9)
			StringBuilder cpf = new StringBuilder(getTextField().getText().replaceAll("[\\D]", ""));

			if (!Cpf.isValido(cpf.toString())) {
				setMensagem("CPF deve conter 11 dígitos válidos");
			} else {
				cpf.insert(3, '.').insert(7, '.').insert(11, '-');
				getTextField().setText(cpf.toString());
				limparErro();
			}
		}
	}

}
