package eventos.teclado;

import java.util.ArrayList;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

public class ExemploEventosTeclado extends Application {

	public static void main(String[] args) {
		launch();
	}

	@Override
	public void start(Stage stage) throws Exception {
		stage.setTitle("Eventos do Teclado");

		Group raiz = new Group();
		Scene cena = new Scene(raiz);
		stage.setScene(cena);

		Canvas canvas = new Canvas(512 - 64, 256);
		raiz.getChildren().add(canvas);

		ArrayList<String> entradas = new ArrayList<>();

		cena.setOnKeyPressed(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent e) {
				String codigo = e.getCode().toString();
				if (!entradas.contains(codigo))
					entradas.add(codigo);
			}

		});

		cena.setOnKeyReleased(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent e) {
				String codigo = e.getCode().toString();
				entradas.remove(codigo);
			}

		});

		GraphicsContext gc = canvas.getGraphicsContext2D();

		Image left = new Image("eventos/teclado/left.jpeg");
		Image leftG = new Image("eventos/teclado/leftG.jpeg");

		Image right = new Image("eventos/teclado/right.jpeg");
		Image rightG = new Image("eventos/teclado/rightG.png.jpeg");

		new AnimationTimer() {

			@Override
			public void handle(long tempoNanoAtual) {

				// limpa o canvas
				gc.clearRect(0, 0, 512, 512);

				if (entradas.contains("LEFT"))
					gc.drawImage(leftG, 64, 64);
				else
					gc.drawImage(left, 64, 64);

				if (entradas.contains("RIGHT"))
					gc.drawImage(rightG, 256, 64);
				else
					gc.drawImage(right, 256, 64);

			}
		}.start();

		stage.show();

	}

}
