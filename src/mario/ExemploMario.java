package mario;

import java.util.ArrayList;
import java.util.Iterator;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import sprite.Sprite;

public class ExemploMario extends Application{

	private static long LastNanoTime = 0;
	private static int pontos = 0;

	public static void main(String[] args) {
		launch();
	}

	@Override
	public void start(Stage stage) throws Exception {

		ArrayList<String> entradas = new ArrayList<>();

		Group raiz = new Group();
		Scene cena = new Scene(raiz);
		stage.setScene(cena);
		stage.setTitle("Colete as vidas");

		Canvas canvas = new Canvas(530, 530);
		GraphicsContext gc = canvas.getGraphicsContext2D();
		raiz.getChildren().add(canvas);

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

		Font fonte = Font.font("Helvetica", FontWeight.BOLD, 24);
		gc.setFont(fonte);
		gc.setFill(Color.RED);
		gc.setStroke(Color.BLACK);
		gc.setLineWidth(1);

		Sprite mario = new Sprite();

		mario.setImagem(new Image("mario/mario.png"));
		mario.setPosicaoXY(200, 0);

		ArrayList<Sprite> listaVidas = new ArrayList<Sprite>();

		Image up = new Image("mario/vida.png");

		for (int i = 0; i < 15; i++) {
			Sprite vida = new Sprite();
			vida.setImagem(up);
			double px = 350 * Math.random() + 50;
			double py = 350 * Math.random() + 50;
			vida.setPosicaoXY(px, py);
			listaVidas.add(vida);
		}

		LastNanoTime = System.nanoTime();

		new AnimationTimer() {

			@Override
			public void handle(long tempoNanoAtual) {
				// Calcula o tempo desde a última atualização

				double tempoDecorrido = (tempoNanoAtual - LastNanoTime) / 500000000.0;
				LastNanoTime = tempoNanoAtual;

				// lógica do jogo
				mario.setVelocidade(0, 0);

				if (entradas.contains("LEFT"))
					mario.addVelocidade(-50, 0);

				if (entradas.contains("RIGHT"))
					mario.addVelocidade(50, 0);

				if (entradas.contains("UP"))
					mario.addVelocidade(0, -50);

				if (entradas.contains("DOWN"))
					mario.addVelocidade(0, 50);

				mario.atualizar(tempoDecorrido);

				// Detecção de colisão
				Iterator<Sprite> iterator = listaVidas.iterator();
				while (iterator.hasNext()) {
					Sprite ups = iterator.next();
					if (mario.intersecao(ups)) {
						iterator.remove();
						pontos++;
					}
				}

				// renderização
				gc.clearRect(0, 0, 512, 512);
				mario.renderizar(gc);

				for (Sprite ups : listaVidas) {
					ups.renderizar(gc);
				}

				String texto = String.format("Vidas: %.2s", (1 * pontos));
				gc.fillText(texto, 270, 36);
				gc.strokeText(texto, 270, 36);

				gc.fillText(mario.toString(), 0, 500);
				gc.strokeText(mario.toString(), 0, 500);
			}
		}.start();

		stage.show();
	}
}
