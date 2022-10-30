package canvas;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class ExemploCanvas extends Application {

	public static void main(String[] args) {
		launch();
	}

	@Override
	public void start(Stage stage) throws Exception {
		stage.setTitle("Exemplo Canvas");

		Group raiz = new Group();
		Scene cena = new Scene(raiz);
		stage.setScene(cena);

		Canvas canvas = new Canvas(400, 400);
		raiz.getChildren().add(canvas);

		GraphicsContext gc = canvas.getGraphicsContext2D();

		gc.setFill(Color.RED);
		gc.setStroke(Color.BLACK);
		gc.setLineWidth(2);
		Font fonte = Font.font("Times New Roman", FontWeight.BOLD, 48);
		gc.setFont(fonte);
		gc.fillText("SOFTGRAF", 60, 50);
		gc.strokeText("SOFTGRAF", 60, 50);

		//
		Image planeta = new Image("canvas/planeta.jpeg");
		gc.drawImage(planeta, 60, 80);

		stage.show();
	}

}
