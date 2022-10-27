package control;

import java.net.URL;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ValidacaoPrincipal extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	public void start(Stage stage) throws Exception {
		URL url = this.getClass().getResource("/view/TelaValidacao.fxml");
		Parent painel = FXMLLoader.load(url);
		Scene cena = new Scene(painel);
		stage.setTitle("Métodos para Validação de Campos");
		stage.setScene(cena);
		stage.setResizable(false);
		stage.show();
	}

}
