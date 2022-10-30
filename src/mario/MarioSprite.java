package mario;

import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import sprite.Sprite;

public class MarioSprite {
	
	private Image imagem;
	private double posicaoX;
	private double posicaoY;
	private double velocidadeX;
	private double velocidadeY;
	private double largura;
	private double altura;

	public Image getImagem() {
		return imagem;
	}

	public void setImagem(Image imagem) {
		this.imagem = imagem;
		this.largura = imagem.getWidth();
		this.altura = imagem.getHeight();
	}

	public void setPosicaoXY(double posicaoX, double posicaoY) {
		this.posicaoX = posicaoX;
		this.posicaoY = posicaoY;
	}

	public void setVelocidade(double velocidadeX, double velocidadeY) {
		this.velocidadeX = velocidadeX;
		this.velocidadeY = velocidadeY;
	}

	public void addVelocidade(double velocidadeX, double velocidadeY) {
		this.velocidadeX += velocidadeX;
		this.velocidadeY += velocidadeY;
	}

	public double getLargura() {
		return largura;
	}

	public double getAltura() {
		return altura;
	}

	public void atualizar(double tempo) {
		posicaoX += velocidadeX * tempo;
		posicaoY += velocidadeY * tempo;
	}

	public void renderizar(GraphicsContext gc) {
		gc.drawImage(imagem, posicaoX, posicaoY);
	}

	public Rectangle2D getLimites() {
		return new Rectangle2D(posicaoX, posicaoY, largura, altura);

	}

	public boolean intersecao(Sprite s) {
		return s.getLimites().intersects(this.getLimites());
	}

	public String toString() {
		return String.format("Posição: [%.2f,%.2f] Velocidade: [%.2f,%.2f]", posicaoX, posicaoY, velocidadeX,
				velocidadeY);
	}


}
