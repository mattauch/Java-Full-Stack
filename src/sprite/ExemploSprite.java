package sprite;

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

public class ExemploSprite extends Application {

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
		stage.setTitle("Colete as sacolas com dinheiro!");

		Canvas canvas = new Canvas(512, 512);
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
		gc.setFill(Color.GREEN);
		gc.setStroke(Color.BLACK);
		gc.setLineWidth(1);

		Sprite maleta = new Sprite();

		maleta.setImagem(new Image("sprite/maleta.png"));
		maleta.setPosicaoXY(200, 0);

		ArrayList<Sprite> listaSacolas = new ArrayList<Sprite>();

		Image dinheiro = new Image("sprite/sacola.png");

		for (int i = 0; i < 15; i++) {
			Sprite sacolaDinheiro = new Sprite();
			sacolaDinheiro.setImagem(dinheiro);
			double px = 350 * Math.random() + 50;
			double py = 350 * Math.random() + 50;
			sacolaDinheiro.setPosicaoXY(px, py);
			listaSacolas.add(sacolaDinheiro);
		}

		LastNanoTime = System.nanoTime();

		new AnimationTimer() {

			@Override
			public void handle(long tempoNanoAtual) {
				// Calcula o tempo desde a última atualização

				double tempoDecorrido = (tempoNanoAtual - LastNanoTime) / 1000000000.0;
				LastNanoTime = tempoNanoAtual;

				// lógica do jogo
				maleta.setVelocidade(0, 0);

				if (entradas.contains("LEFT"))
					maleta.addVelocidade(-50, 0);

				if (entradas.contains("RIGHT"))
					maleta.addVelocidade(50, 0);

				if (entradas.contains("UP"))
					maleta.addVelocidade(0, -50);

				if (entradas.contains("DOWN"))
					maleta.addVelocidade(0, 50);

				maleta.atualizar(tempoDecorrido);

				// Detecção de colisão
				Iterator<Sprite> iterator = listaSacolas.iterator();
				while (iterator.hasNext()) {
					Sprite sacola = iterator.next();
					if (maleta.intersecao(sacola)) {
						iterator.remove();
						pontos++;
					}
				}

				// renderização
				gc.clearRect(0, 0, 512, 512);
				maleta.renderizar(gc);

				for (Sprite sacola : listaSacolas) {
					sacola.renderizar(gc);
				}

				String texto = String.format("Dinheiro: R$ %.2f", (100.0 * pontos));
				gc.fillText(texto, 270, 36);
				gc.strokeText(texto, 270, 36);

				gc.fillText(maleta.toString(), 0, 500);
				gc.strokeText(maleta.toString(), 0, 500);
			}
		}.start();

		stage.show();
	}

}
