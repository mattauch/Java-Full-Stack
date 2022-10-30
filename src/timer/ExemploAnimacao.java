package timer;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class ExemploAnimacao extends Application {

	public static void main(String[] args) {
		launch();
	}

	@Override
	public void start(Stage stage) throws Exception {
		stage.setTitle("Exemplo Timeline");

		Group raiz = new Group();
		Scene cena = new Scene(raiz);
		stage.setScene(cena);

		Canvas canvas = new Canvas(512, 512);
		raiz.getChildren().add(canvas);

		GraphicsContext gc = canvas.getGraphicsContext2D();

		Image terra = new Image("timer/terra.png");
		Image sol = new Image("timer/sol.png");
		Image espaco = new Image("timer/espaco.png");

		// 1 nanosegundo = 1 segundo / 10e9
		final long tempoNanoInicial = System.nanoTime();

		// AnimationTimer é uma classe abstrata, por isso precisa ser estendida ou
		// podemos criar uma classe anônima.
		// É uma thread em execução permanente que cria um loop
		// O loop pode ser interrompido chamando o método stop().

		new AnimationTimer() {

			// handle executa a uma taxa de 60 frames por segundo (60fps)
			@Override
			public void handle(long tempoNanoAtual) {
				// t = tempo atual em segundos
				double t = (tempoNanoAtual - tempoNanoInicial) / 1000000000.0;

				// centro do sol = 232,232
				// 128 é a distância entre a terra e o sol
				// Os métodos sin e cos calcualm o ângulo produzindo o deslocamento aparebte
				// inverta cos com sin para girar no sentido anti-horário
				double x = 232 + 128 * Math.sin(t);
				double y = 232 + 128 * Math.cos(t);

				// Redesenha imagens a cada execução
				gc.drawImage(espaco, 0, 0);
				gc.drawImage(terra, x, y);
				gc.drawImage(sol, 196, 196);

				// remova os comentários para ver quadro a quadro

				
				 // try { Thread.sleep(100); } catch (InterruptedException e ){
				//  e.printStackTrace(); }
				 
			}
		}.start();

		stage.show();
	}

}
