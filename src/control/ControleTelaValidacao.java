package control;

import java.util.Arrays;
import java.util.List;

import control.filtros.FiltroCep;
import control.filtros.FiltroEmail;
import control.filtros.FiltroFlutuante;
import control.filtros.FiltroFone;
import control.filtros.FiltroInteiro;
import control.filtros.FiltroLetras;
import control.listeners.ListenerController;
import control.listeners.ListenerFormatarCep;
import control.listeners.ListenerFormatarCnpj;
import control.listeners.ListenerFormatarCpf;
import control.listeners.ListenerFormatarFone;
import control.listeners.ListenerFormatarMonetario;
import control.listeners.ListenerMininoDeCaracteres;
import control.listeners.ListenerParaMaiusculas;
import control.listeners.ListenerParaMinusculas;
import control.listeners.ListenerValidarEmail;
import control.listeners.ListenerValorMinMax;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import model.conversores.ConversorInteiro;

public class ControleTelaValidacao {

	@FXML
	private Button btnCancelar;

	@FXML
	private Button btnConfirmar;

	@FXML
	private Label labelMensagem;

	@FXML
	private TextField txtCep;

	@FXML
	private TextField txtCnpj;

	@FXML
	private TextField txtCpf;

	@FXML
	private TextField txtEmail;

	@FXML
	private TextField txtFlutuante;

	@FXML
	private TextField txtFone;

	@FXML
	private TextField txtInteiro;

	@FXML
	private TextField txtLetras;

	@FXML
	private TextField txtMonetario;

	public void initialize() {

		ConversorInteiro formatador = new ConversorInteiro();

		txtInteiro.setTextFormatter(new TextFormatter<Integer>(formatador));

		ListenerController.setCorErro("lightpink");
		ListenerController.setLabelMensagem(labelMensagem);

		txtLetras.addEventFilter(KeyEvent.KEY_TYPED, new FiltroLetras(20));
		txtLetras.textProperty().addListener(new ListenerParaMaiusculas(txtLetras));
		txtLetras.focusedProperty().addListener(new ListenerMininoDeCaracteres(txtLetras, 5));

		txtInteiro.addEventFilter(KeyEvent.KEY_TYPED, new FiltroInteiro(5, true));
		txtInteiro.focusedProperty().addListener(new ListenerValorMinMax(txtInteiro, -100, 5000));

		txtFlutuante.addEventFilter(KeyEvent.KEY_TYPED, new FiltroFlutuante(7, true));
		txtFlutuante.focusedProperty().addListener(new ListenerValorMinMax(txtFlutuante, -100, 5000));

		txtCpf.addEventFilter(KeyEvent.KEY_TYPED, new FiltroInteiro(11));
		txtCpf.focusedProperty().addListener(new ListenerFormatarCpf(txtCpf));

		txtCnpj.addEventFilter(KeyEvent.KEY_TYPED, new FiltroInteiro(14));
		txtCnpj.focusedProperty().addListener(new ListenerFormatarCnpj(txtCnpj));

		txtMonetario.addEventFilter(KeyEvent.KEY_TYPED, new FiltroFlutuante(12, true));
		txtMonetario.focusedProperty().addListener(new ListenerFormatarMonetario(txtMonetario, 3));
		txtMonetario.focusedProperty().addListener(new ListenerValorMinMax(txtMonetario, -2000.0, 99999.999));

		txtEmail.addEventFilter(KeyEvent.KEY_TYPED, new FiltroEmail());
		txtEmail.textProperty().addListener(new ListenerParaMinusculas(txtEmail));
		txtEmail.focusedProperty().addListener(new ListenerValidarEmail(txtEmail));

		txtFone.addEventFilter(KeyEvent.KEY_TYPED, new FiltroFone());
		txtFone.focusedProperty().addListener(new ListenerFormatarFone(txtFone));

		txtCep.addEventFilter(KeyEvent.KEY_TYPED, new FiltroCep());
		txtCep.focusedProperty().addListener(new ListenerFormatarCep(txtCep));

		List<TextField> campos = Arrays.asList(txtLetras, txtInteiro, txtFlutuante, txtMonetario, txtCpf, txtCnpj,
				txtEmail, txtFone, txtCep);

		btnConfirmar.setOnAction(e -> {
			if (ListenerController.validar(campos, btnConfirmar)) {
				((Stage) btnConfirmar.getScene().getWindow()).close();
			}
		});

		btnCancelar.setOnAction(e -> ((Stage) btnCancelar.getScene().getWindow()).close());

	}

}
