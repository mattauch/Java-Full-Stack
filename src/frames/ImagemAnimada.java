package frames;

import javafx.scene.image.Image;

public class ImagemAnimada {

	private Image[] frames;
	private double duracao;

	public ImagemAnimada(Image[] frames, double duracao) {
		this.frames = frames;
		this.duracao = duracao;
	}

	public Image getFrame(double tempo) {
		int indice = (int) ((tempo % (frames.length * duracao)) / duracao);
		return frames[indice];
	}

}
