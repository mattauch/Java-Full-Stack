package control.listeners;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TextField;
import model.validadores.Email;

public class ListenerValidarEmail extends ListenerController implements ChangeListener<Boolean>{
	
	public ListenerValidarEmail(TextField textField) {
		super(textField);
	}
	
	@Override
	public void changed(ObservableValue<? extends Boolean> observable, Boolean saiu, Boolean entrou) {
		if (saiu) {
			if(!Email.isValido(getTextField().getText()))
				setMensagem("E-mal não está correto!");
			else
				limparErro();
		}
	}

}
