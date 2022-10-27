package control.listeners;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TextField;
import model.validadores.Cnpj;

public class ListenerFormatarCnpj extends ListenerController implements ChangeListener<Boolean> {

	public ListenerFormatarCnpj(TextField textField) {
		super(textField);
	}

	@Override
	public void changed(ObservableValue<? extends Boolean> observable, Boolean saiu, Boolean entrou) {

		if (saiu) {
			// remove todos os caracteres que não sejam números (0...9)
			StringBuilder cnpj = new StringBuilder(getTextField().getText().replaceAll("[\\D]", ""));

			if (!Cnpj.isValido(cnpj.toString())) {
				setMensagem("CNPJ deve conter 14 dígitos válidos");
			} else {
				cnpj.insert(2, '.').insert(6, '.').insert(10, '/').insert(15, '-');
				getTextField().setText(cnpj.toString());
				limparErro();
			}
		}
	}
}
