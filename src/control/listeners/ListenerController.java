package control.listeners;

import java.util.List;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class ListenerController {

	private TextField textField;
	static private Label LabelMensagem = null;
	static private String corErro = "lightpink";

	static public void setLabelMensagem(Label labelMensagem) {
		ListenerController.LabelMensagem = labelMensagem;
	}

	// Construtor
	public ListenerController(TextField textField) {
		if (ListenerController.LabelMensagem == null) {
			String erro = "Faltou executar o método estático\nsetLabelMensagem(Label labelMensagen)";
			Alert alerta = new Alert(AlertType.ERROR);
			alerta.setTitle("Erro Fatal");
			alerta.setHeaderText("Classe ListenerController");
			alerta.setContentText(erro);
			alerta.showAndWait();
			throw new RuntimeException("\nClasse ListenerController\n" + erro + "\n");
		}
		this.textField = textField;
	}

	protected void setMensagem(String msg) {
		LabelMensagem.setText(msg);
		getTextField().setStyle("-fx-background-color: " + corErro); // Erro
	}

	protected TextField getTextField() {
		return textField;
	}

	protected void limparErro() {
		getTextField().setStyle(""); // Ok
	}

	static public boolean validar(List<TextField> listaCampos, Button botao) {
		for (TextField campo : listaCampos) {
			campo.requestFocus();
			botao.requestFocus();
			if (campo.getStyle().length() > 0) {
				campo.requestFocus();
				return false;
			}
		}
		return true;
	}

	static public void setCorErro(String cor) {
		corErro = cor;
	}
}
