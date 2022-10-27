package control.listeners;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TextField;

public class ListenerMininoDeCaracteres extends ListenerController implements ChangeListener<Boolean> {

	private int minLength;

	public ListenerMininoDeCaracteres(TextField textField, int minLength) {
		super(textField);
		this.minLength = minLength;
	}

	@Override
	public void changed(ObservableValue<? extends Boolean> observable, Boolean saiu, Boolean entrou) {

		if (saiu) {
			getTextField().setText(getTextField().getText().trim());

			if (getTextField().getText().length() < minLength)
				setMensagem("O campo deve conter no mínimo " + minLength + " caractered");

			else
				limparErro();
		}
	}

}
