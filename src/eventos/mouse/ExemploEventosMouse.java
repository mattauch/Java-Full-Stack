package eventos.mouse;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class ExemploEventosMouse extends Application {

	private static int pontos = 0;

	public static void main(String[] args) {
		launch();
	}

	@Override
	public void start(Stage stage) throws Exception {
		stage.setTitle("Clique no alvo!");

		Group raiz = new Group();
		Scene cena = new Scene(raiz);
		stage.setScene(cena);

		Canvas canvas = new Canvas(500, 500);
		raiz.getChildren().add(canvas);

		Circle circulo = new Circle(100, 100, 32);

		cena.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent e) {
				if (circulo.contains(e.getX(), e.getY())) {
					double x = 50 + 400 * Math.random();
					double y = 50 + 400 * Math.random();
					circulo.setCenterX(x);
					circulo.setCenterY(y);
					pontos++;
				} else
					pontos = 0;
			}
		});

		GraphicsContext gc = canvas.getGraphicsContext2D();

		Font fonte = Font.font("Helvetica", FontWeight.BOLD, 24);
		gc.setFont(fonte);
		gc.setStroke(Color.BLACK);
		gc.setLineWidth(1);

		Image alvo = new Image("eventos/mouse/alvo.jpeg");

		new AnimationTimer() {

			@Override
			public void handle(long tempoNanoAtual) {
				// limpa o canvas
				gc.setFill(new Color(0.85, 0.85, 1.0, 1.0));
				gc.fillRect(0, 0, 512, 512);

				gc.drawImage(alvo, circulo.getCenterX() - circulo.getRadius(),
						circulo.getCenterY() - circulo.getRadius());

				gc.setFill(Color.BLUE);

				String texto = "Pontos: " + pontos;
				gc.fillText(texto, 360, 36);
				gc.strokeText(texto, 360, 36);

			}
		}.start();

		stage.show();
	}

}
