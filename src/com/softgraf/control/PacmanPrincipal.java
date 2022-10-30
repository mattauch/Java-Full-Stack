package com.softgraf.control;

import com.softgraf.model.Direcao;
import com.softgraf.model.Pacman;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class PacmanPrincipal extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		// Classe para reprodução de áudio
		String AUDIO_URL = getClass().getResource("/com/softgraf/model/Pacman_Sound.mp3").toString();
		AudioClip clip = new AudioClip(AUDIO_URL);
		clip.setCycleCount(AudioClip.INDEFINITE);
		
		//Desenho do Pacman
		Arc arco = new Arc();
		arco.setFill(Color.YELLOW);
		arco.setCenterX(50.0f);
		arco.setCenterY(50.0f);
		arco.setRadiusX(25.0f);
		arco.setRadiusY(25.0f);
		arco.setStartAngle(45.0f);
		arco.setLength(270.0f);
		arco.setType(ArcType.ROUND);
		
		AnchorPane root = new AnchorPane();
		root.getChildren().add(arco);
		
		Scene cena = new Scene(root, 600, 400);
		cena.setFill(Color.BLACK);
		Pacman pacman = new Pacman(arco, root);
		
		stage.setTitle("The Pacman Game");
		stage.setScene(cena);
		
		//Executa a thread pacman após a tela ser mostrada
		stage.setOnShown(new EventHandler<WindowEvent>() {
			@Override
			public void handle (WindowEvent event) {
				pacman.start();
				clip.play();
			}
		});
	//Controla eventos do teclado
		stage.addEventFilter(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>(){
			@SuppressWarnings("incomplete-switch")
			@Override
			public void handle(KeyEvent evento) {
				switch (evento.getCode()) {
				case RIGHT :
					pacman.setDirecao(Direcao.DIREITA);
					break;
				case LEFT :
					pacman.setDirecao(Direcao.ESQUERDA);
					break;
				case UP :
					pacman.setDirecao(Direcao.ACIMA);
					break;	
				case DOWN :
					pacman.setDirecao(Direcao.ABAIXO);
					break;
							
				}
			}
		});
		
		//Mostra a tela do programa
		stage.show();
	}
}
