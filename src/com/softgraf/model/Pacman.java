package com.softgraf.model;

import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Arc;

//Classe Sprite
public class Pacman extends Thread {

	private Arc arco;
	private Direcao direcao;
	private Scene cena;
	private boolean fecharBoca = true;
	
	//Construtor
	public Pacman(Arc arco, AnchorPane painel) {
		this.arco = arco;
		this.direcao = direcao.DIREITA;
		this.cena = painel.getScene();
		setDaemon(true);
	}
	
	//método run() da Thread
	@Override
	public void run() {
		
		try {
			while (true) {
				
				switch (direcao) {
				case DIREITA:
					arco.setCenterX(getPosX () + 5);
					arco.setRotate(0.0);
					break;
				case ESQUERDA:
					arco.setCenterX(getPosX () - 5);
					arco.setRotate(180.0);
					break;
				case ACIMA:
					arco.setCenterY(getPosY () - 5);
					arco.setRotate(270.0);
					break;
				case ABAIXO:
					arco.setCenterY(getPosY () + 5);
					arco.setRotate(90.0);
					break;
				}
				if (fecharBoca) {
					arco.setStartAngle(arco.getStartAngle() -5);
					arco.setLength(arco.getLength() + 10);
					if (arco.getStartAngle() == 0)
						fecharBoca= false;
				}else {
					arco.setStartAngle(arco.getStartAngle() +5);
					arco.setLength(arco.getLength() - 10);
					if (arco.getStartAngle() == 45)
						fecharBoca= true;
				}
				
				if(getPosX() < 0.0)
					setPosX(cena.getWidth());
				
				else if (getPosX() > cena.getWidth())
					setPosX(0.0);
				
				else if(getPosY()< 0.0)
					setPosY(cena.getHeight());
				
				else if(getPosY() > cena.getHeight())
					setPosY(0.0);
				
				Thread.sleep(50);
			}
			
		}catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	public void setDirecao(Direcao direcao) {
		this.direcao = direcao;
	}
	private double getPosX() {
		return arco.getCenterX();
	}
	private void setPosX(double posX) {
		arco.setCenterX(posX);
	}
	private double getPosY() {
		return arco.getCenterY();
	}
	private void setPosY (double posY) {
		arco.setCenterY(posY);
	}
}
