package frames;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class ExemploAnimacaoFrames extends Application {

	private static double posX = -10.0;
	private static double posY = (Math.random() * 500) + 10;
	private static double velocidade = 2;

	public static void main(String[] args) {
		launch();
	}

	@Override
	public void start(Stage stage) throws Exception {
		stage.setTitle("Exemplo de animação com frames");

		Group raiz = new Group();
		Scene cena = new Scene(raiz);
		stage.setScene(cena);

		Canvas canvas = new Canvas(500, 500);
		GraphicsContext gc = canvas.getGraphicsContext2D();
		raiz.getChildren().add(canvas);

		Image[] vetorImagem = new Image[6];
		for (int i = 0; i < 6; i++) {
			vetorImagem[i] = new Image("frames/ufo" + i + ".png");
		}

		ImagemAnimada ufo = new ImagemAnimada(vetorImagem, 0.1);

		Image terra = new Image("frames/terra.png");
		Image sol = new Image("frames/sol.png");
		Image espaco = new Image("frames/espaco.png");

		final long tempoNanoInicial = System.nanoTime();

		new AnimationTimer() {

			@Override
			public void handle(long tempoNanoAtual) {
				double t = (tempoNanoAtual - tempoNanoInicial) / 1000000000.0;
				double x = 232 + 128 * Math.cos(t);
				double y = 232 + 128 * Math.sin(t);

				gc.drawImage(espaco, 0, 0);
				gc.drawImage(terra, x, y);
				gc.drawImage(sol, 196, 196);
				gc.drawImage(ufo.getFrame(t), posX, posY);

				if (posX < canvas.getWidth() + 10) {
					posX += velocidade;
					posY += Math.sin(t);
				} else {
					posX = 0;
					posY = (Math.random() * canvas.getHeight()) + 10;
					velocidade = (Math.random() * 10) + 1;
				}
			}
		}.start();

		stage.show();
	}

}
