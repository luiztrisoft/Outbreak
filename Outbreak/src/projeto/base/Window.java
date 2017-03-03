package projeto.base;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.DisplayMode;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.image.BufferStrategy;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * A Função pricipal desta classe é desenhar as imagens na tela.Classe principal
 * do projeto.
 * 
 * @author Tiko
 * 
 */
@SuppressWarnings("serial")
public class Window extends JFrame {

	public static Window instance;
	private Keyboard keyboard;
	private BufferStrategy buffer;
	private Graphics graphics;
	private long currTime;
	private long lastTime;
	@SuppressWarnings("unused")
	private long totalTime;
	private DisplayMode displayMode;
	private GraphicsDevice device;

	/**
	 * Cria uma janela com largura e altura em pixels.
	 * 
	 * @param width
	 * @param height
	 */
	public Window(int width, int height) {
		device = GraphicsEnvironment.getLocalGraphicsEnvironment()
				.getDefaultScreenDevice();
		displayMode = new DisplayMode(width, height, 16,
				DisplayMode.REFRESH_RATE_UNKNOWN);
		keyboard = new Keyboard();

		addKeyListener(keyboard);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(width, height);
		setLocationRelativeTo(null);
		setUndecorated(true);
		setVisible(true);

		createBufferStrategy(2);
		buffer = getBufferStrategy();

		graphics = buffer.getDrawGraphics();
		currTime = System.currentTimeMillis();
		lastTime = 0;
		totalTime = 0;

		instance = this;
	}

	/**
	 * Retorna uma instância da janela atual.
	 * 
	 * @return Window
	 */
	static Window getInstance() {
		return instance;
	}

	/**
	 * Retorna uma instância de Keyboard.
	 * 
	 * @return Keyboard
	 */
	public Keyboard getKeyboard() {
		return keyboard;
	}

	/**
	 * Retorna uma instância de Graphics.
	 * 
	 * @return graphics
	 */
	public Graphics getGameGraphics() {
		return graphics;
	}

	/**
	 * Atualiza a janela com nova informação desenhando no buffer.
	 */
	public void update() {
		graphics.dispose();
		buffer.show();
		Toolkit.getDefaultToolkit().sync();
		graphics = buffer.getDrawGraphics();
		lastTime = currTime;
		currTime = System.currentTimeMillis();
		totalTime += currTime - lastTime;
	}

	/**
	 * Atrasa a execução do programa.
	 * 
	 * @param time
	 *            tempo em milisegundos
	 */
	public void delay(long time) {
		try {
			Thread.sleep(time);
		} catch (InterruptedException ex) {
			Logger.getLogger(Window.class.getName())
					.log(Level.SEVERE, null, ex);
		}
	}

	/**
	 * Retorna o tempo decorrido entre o quadro anterior e o atual.
	 * 
	 * @return long tempo de milisegundos
	 */
	public long deltaTime() {
		return currTime - lastTime;
	}

	/**
	 * Desenha uma mensagem na tela.
	 * 
	 * @param message
	 * @param x
	 *            valor no eixo x
	 * @param y
	 *            valor no eixo y
	 * @param color
	 */
	public void drawText(String message, int x, int y, Color color) {
		graphics.setColor(color);
		graphics.drawString(message, x, y);
	}

	/**
	 * Desenha uma mensagem na tela.
	 * 
	 * @param message
	 * @param x
	 *            valor no eixo x
	 * @param y
	 *            valor no eixo y
	 * @param color
	 * @param font
	 */
	public void drawText(String message, int x, int y, Color color, Font font) {
		Graphics2D g2 = (Graphics2D) graphics;
		g2.setFont(font);
		g2.setColor(color);
		g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
				RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		g2.drawString(message, x, y);
	}

	/**
	 * Fecha a janela e encerra o programa.
	 */
	public void exit() {
		dispose();
		System.exit(0);
	}

	/**
	 * Cria um cursor do mouse usando uma imagem.
	 * 
	 * @param imageName
	 *            caminho da imagem
	 * @return Cursor
	 */
	public Cursor createCustomCursor(String imageName) {
		Cursor cursor = Toolkit.getDefaultToolkit().createCustomCursor(
				Toolkit.getDefaultToolkit().getImage(imageName),
				new java.awt.Point(), "cursor");
		return cursor;
	}

	/**
	 * Limpa a janela
	 * 
	 * @param color
	 */
	public void clear(Color color) {
		graphics.setColor(color);
		graphics.fillRect(0, 0, getWidth(), getHeight());
	}

	/**
	 * Retorna um array com os modos de exibição que a tela pode trabalhar.
	 * 
	 * @returnDisplayMode[]
	 * @see DisplayMode
	 */
	public DisplayMode[] getDisplayMode() {
		return device.getDisplayModes();
	}

	/**
	 * Define o modo de exibição.
	 * 
	 * @param displayMode
	 * @see DisplayMode
	 */
	public void setDisplayMode(DisplayMode displayMode) {
		if (isDisplayModeCompatible(displayMode) == false) {
			JOptionPane.showMessageDialog(null,
					"Resolução não compatível com display");
		}
	}

	/**
	 * Retorna true se o display é capaz de trabalhar com este modo de
	 * visualizaçao, senão retorna false
	 * 
	 * @param displayMode2
	 * @return boolean
	 */
	public boolean isDisplayModeCompatible(DisplayMode displayMode2) {
		DisplayMode goodModes[] = device.getDisplayModes();
		int i = 0;
		boolean compatible = false;
		while (!compatible && i < goodModes.length) {
			if (goodModes[i].getWidth() == displayMode.getWidth()
					&& goodModes[i].getHeight() == displayMode.getHeight())
				compatible = true;
			i++;
		}
		return compatible;
	}

	/**
	 * Coloca a janela em modo full screen.
	 */
	public void setFullScreen() {
		DisplayMode old = device.getDisplayMode();
		super.setIgnoreRepaint(true);
		this.device.setFullScreenWindow(instance);
		try {
			device.setDisplayMode(displayMode);
		} catch (IllegalArgumentException ex) {
			device.setDisplayMode(old);
		}
	}

	/**
	 * Desabilita o modo full screen
	 */
	public void restoreScreen() {
		device.setFullScreenWindow(null);
		super.setLocationRelativeTo(null);
	}

	/**
	 * Insere o tamanho da tela
	 * 
	 * @param width
	 * @param height
	 */
	@Override
	public void setSize(int width, int height) {
		setResizable(true);
		super.setSize(width, height);
		setDisplayMode(new DisplayMode(width, height, 16,
				DisplayMode.REFRESH_RATE_UNKNOWN));
		super.setLocationRelativeTo(null);
		setResizable(false);
	}

	/**
	 * Insere a dimensão da tela
	 * 
	 * @param dimension
	 */
	@Override
	public void setSize(Dimension d) {
		this.setSize(d.width, d.height);
	}

	/**
	 * Método usado para pintar imagens fixas na tela como um wallpaper de tela
	 * principal.
	 */
	public void display() {
		graphics.dispose();
		buffer.show();
		Toolkit.getDefaultToolkit().sync();
		graphics = buffer.getDrawGraphics();
		lastTime = currTime;
		currTime = System.currentTimeMillis();
		// delay();
		
	}

	private long timeDelay;

	private void delay() {
		try {
			Thread.sleep(timeDelay);
		} catch (InterruptedException ex) {
			Logger.getLogger(Window.class.getName())
					.log(Level.SEVERE, null, ex);
		}
	}
}