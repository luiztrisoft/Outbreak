package projeto.base;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;

import javax.swing.ImageIcon;

/**
 * Classe responsável por modelar uma imagem.
 * 
 * @author Tiko
 * 
 */
public class GameImage extends GameObject {

	/**
	 * Referência para uma imagem.
	 * 
	 * @see java.awt.Image
	 */
	public Image image;

	/**
	 * Construtor da classe. Ele carrega uma imagem;
	 * 
	 * @param fileName
	 *            caminho da imagem e seu nome.
	 * @see #loadImage(java.lang.String)
	 */
	public GameImage(String fileName) {
		loadImage(fileName);
	}

	/**
	 * Este método carrega ma imagem
	 * 
	 * @param fileName
	 *            Caminho da imagem e seu nome.
	 */
	public void loadImage(String fileName) {
		ImageIcon icon = new ImageIcon(fileName);
		this.image = icon.getImage();
		this.width = image.getWidth(null);
		this.height = image.getHeight(null);
	}

	/**
	 * Desenha uma imagem no ecrã.
	 */
	public void draw() {
		// Window.instance.getGameGraphics().drawImage(image, (int)x, (int)y,
		// width, height, null);

		Graphics2D g2d = (Graphics2D) Window.getInstance().getGameGraphics();
		AffineTransform tx = new AffineTransform();

		double rot = rotation;
		tx.rotate(-rot, width / 2, height / 2);

		int newy = (int) (x * Math.sin(rot) + y * Math.cos(rot));
		int newx = (int) (x * Math.cos(rot) - y * Math.sin(rot));

		g2d.setTransform(tx);

		g2d.drawImage(image, newx, newy, width, height, null);
	}

	/**
	 * desenha uma imagem com transparencia no ecrã.
	 * 
	 * @author Luiz Alberto
	 * 
	 * @param f
	 */
	public void draw(float f) {
		Graphics2D g2d = (Graphics2D) Window.getInstance().getGameGraphics();
		AffineTransform tx = new AffineTransform();

		// teste de translucidez - minha contribuição ao projeto
		AlphaComposite alpha = AlphaComposite.getInstance(
				AlphaComposite.SRC_OVER, (float) f);
		g2d.setComposite(alpha);

		double rot = rotation;
		tx.rotate(-rot, width / 2, height / 2);

		int newy = (int) (x * Math.sin(rot) + y * Math.cos(rot));
		int newx = (int) (x * Math.cos(rot) - y * Math.sin(rot));

		g2d.setTransform(tx);

		g2d.drawImage(image, newx, newy, width, height, null);
		
		//retorna a cor natural
		alpha = AlphaComposite.getInstance(
				AlphaComposite.SRC_OVER, (float) 1.0);
		g2d.setComposite(alpha);
	}

	/** Desenha uma imagem no ecrã. */
	// public void draw()
	// {
	// Window.getInstance().getGameGraphics().drawImage(image, (int) x, (int) y,
	// width, height, null);
	// }

	public void drawPartially(int dx1, int dy1, int dx2, int dy2, int sx1,
			int sy1, int sx2, int sy2) {
		Window.getInstance().getGameGraphics()
				.drawImage(image, dx1, dy1, dx2, dy2, sx1, sy1, sx2, sy2, null);
	}
}
