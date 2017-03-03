package projeto.base;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

/**
 * Classe responsável por animar um GameImage usando peças da imagem, como
 * quadros.
 * 
 * @author Tiko
 * 
 */
public class Animation extends GameImage {
	private int initialFrame;
	private int currFrame;
	private int finalFrame;

	private int totalFrames;

	private boolean playing;
	private boolean loop;
	private boolean drawable;

	private long frameDuration[];
	private long totalDuration;

	private long lastTime;

	@SuppressWarnings("unused")
	private boolean repeatAnimation;
	@SuppressWarnings("unused")
	private int finalFram;
	@SuppressWarnings("unused")
	private int initialFram;
	@SuppressWarnings("unused")
	private int currAnimFrame;
	@SuppressWarnings("unused")
	private int currAnimFram;
	private long timeEachFrame[];
	@SuppressWarnings("unused")
	private boolean canDraw;
	@SuppressWarnings("unused")
	private boolean animationFinished;

	/**
	 * O construtor cria uma animação de objetos de classe. A sequência
	 * configurada para iniciar o quadro vai de uma até o lastFrame.
	 * 
	 * Por exemplo: setSequence(0, totalFrames).
	 * 
	 * 0 = initial frame. lastFrame = totalFrames.
	 * 
	 * @param fileName
	 *            Caminho e nome da imagem.
	 * @param totalFrames
	 *            Número de quadro que formam a imagem.
	 * @param Ele
	 *            diz se a animação é executada repetidamente. Se o valor for
	 *            verdadeiro, quando o último quadro é mostrado, ele retorna até
	 *            o primeiro. Se for falso, a animação mostrará o último quadro
	 *            indefinidamente.
	 */
	public Animation(String fileName, int totalFrames, boolean loop) {
		super(fileName);
		this.totalFrames = totalFrames;
		this.width = super.image.getWidth(null) / totalFrames;
		this.height = super.image.getHeight(null);
		this.lastTime = System.currentTimeMillis();
		this.playing = true;
		this.drawable = true;
		this.frameDuration = new long[totalFrames];
		setSequence(0, totalFrames, loop);
	}

	public Animation(String fileName, int numFrames, int num) {
		super(fileName);
		this.width = image.getWidth(null) / numFrames;
		this.height = image.getHeight(null) / num;

		this.canDraw = true;
		this.repeatAnimation = true;
		this.finalFrame = numFrames;
		this.finalFram = num;
		this.initialFrame = 0;
		this.initialFram = 0;
		this.currAnimFrame = 0;
		this.currAnimFram = 0;
		timeEachFrame = new long[numFrames];
		setTimeChangeFrame(70);
		lastTime = System.currentTimeMillis();
		this.animationFinished = false;
	}

	// IMPLEMENTEI DO JOGO DO NINJA
	public void setTimeChangeFrame(long timeChangeFrame) {
		for (int i = 0; i < timeEachFrame.length; i++)
			timeEachFrame[i] = timeChangeFrame;
	}

	/**
	 * Sobrecarga do construtor Animation(String fileName, int totalFrames,
	 * boolean loop). O parâmetro boolean 'loop' recebe o valor verdadeiro.
	 */
	public Animation(String fileName, int totalFrames) {
		this(fileName, totalFrames, true);
	}

	/**
	 * Sobrecarga do construtor Animation(String fileName, int totalFrames,
	 * boolean loop). O parâmetro boolean 'loop' recebe o valor verdadeiro. O
	 * valor do parâmetro int 'totalFrames' é igual a 1.
	 */
	public Animation(String fileName) {
		this(fileName, 1, true);
	}

	/**
	 * Configure o tempo que o quadro será mostrado na tela.
	 * 
	 * @param frame
	 *            Numero do quadro
	 * @param time
	 *            Milisegundos de tempo que o quadro será mostrado na tela.
	 */
	public void setDuration(int frame, long time) {
		frameDuration[frame] = time;
	}

	/**
	 * Retorna o tempo em que a estrutura é mostrada na tela.
	 * 
	 * @param frame
	 *            Numero do quadro
	 * @return long - Tempo em milisegundos
	 */
	public long getDuration(int frame) {
		return frameDuration[frame];
	}

	/**
	 * Definir o quadro inicial e final da sequência de animação. A sequência
	 * será executada indefinidamente
	 * 
	 * @param initialFrame
	 * @param finalFrame
	 */
	public void setSequence(int initialFrame, int finalFrame) {
		setSequence(initialFrame, finalFrame, true);
	}

	/**
	 * Definir o quadro inicial e fianl na sequância de animação. E se a
	 * animação será executada indefinidamente.
	 * 
	 * @param initialFrame
	 * @param finalFrame
	 * @param loop
	 */
	public void setSequence(int initialFrame, int finalFrame, boolean loop) {
		setInitialFrame(initialFrame);
		setCurrFrame(initialFrame);
		setFinalFrame(finalFrame);
		setLoop(loop);
	}

	/**
	 * Definir o quadro inicial e final na sequência de animação e o tempo de
	 * execução.
	 * 
	 * @param initialFrame
	 * @param finalFrame
	 * @param time
	 */
	public void setSequenceTime(int initialFrame, int finalFrame, long time) {
		setSequenceTime(initialFrame, finalFrame, true, time);
	}

	/**
	 * Definir o quadro inicial e final na sequência de animação, o tempo de
	 * execução e se ele será executado indefinidamente.
	 * 
	 * @param initialFrame
	 * @param finalFrame
	 * @param time
	 * @param loop
	 *            True para indefinidamente, false caso contrário.
	 */

	public void setSequenceTime(int initialFrame, int finalFrame, boolean loop,
			long time) {
		setSequence(initialFrame, finalFrame, loop);
		time = time / (finalFrame - initialFrame + 1);
		for (int i = initialFrame; i <= finalFrame; i++)
			this.frameDuration[i] = time;
	}

	/**
	 * Este método informa se a animação for repetida.
	 * 
	 * @return boolean
	 */
	public boolean isLooping() {
		return loop;
	}

	/**
	 * Define o tempo de todos os quadros. Quando o tempo é passado, a divisão
	 * entre duração total e totalFrames deixaria algum descanso:
	 * 
	 * Exemplo: totalDuration = 100 totalFrames = 11 timeFrame = 100/11 = 9 rest
	 * = 100 - 11 * 9 = 1
	 * 
	 * Assim, a duração total real é(time / numberFrames) * numberFrames
	 * 
	 * @param time
	 *            milisecond time
	 */

	public void setTotalDuration(long time) {
		long timeFrame = time / totalFrames;
		totalDuration = timeFrame * totalFrames;
		for (int i = 0; i < frameDuration.length; i++)
			frameDuration[i] = timeFrame;
	}

	/**
	 * Retorna a soma de todos os intervalos de tempo.
	 * 
	 * @return long
	 */

	public long getTotalDuration() {
		return totalDuration;
	}

	/**
	 * Método responsável por executar a mudança de frames.
	 */
	public void update() {
		if (playing) {
			long time = System.currentTimeMillis();
			if (time - lastTime > frameDuration[currFrame] && finalFrame != 0) {
				currFrame++;
				lastTime = time;
			}

			if (currFrame == finalFrame && loop) {
				currFrame = initialFrame;
			} else if ((!loop) && (currFrame + 1 >= finalFrame)) {
				currFrame = finalFrame - 1;
				playing = false;
			}
		}
	}

	/**
	 * Para a execução e coloca o quadro inicial como quadro atual.
	 */
	public void stop() {
		this.currFrame = initialFrame;
		this.playing = false;
	}

	/**
	 * Método responsável por iniciar a execução da animação.
	 */
	public void play() {
		this.playing = true;
	}

	/**
	 * Método responsável por interromper a animação.
	 */
	public void pause() {
		this.playing = false;
	}

	/**
	 * Define o quadro inicial da sequência de quadros.
	 * 
	 * @param frame
	 *            número do quadro
	 */
	public void setInitialFrame(int frame) {
		this.initialFrame = frame;
	}

	/**
	 * Retirna o número do quadro inicial.
	 * 
	 * @return int
	 */
	public int getInitialFrame() {
		return initialFrame;
	}

	/**
	 * Define o quadro final da sequência de quadros.
	 * 
	 * @param frame
	 *            número do quadro.
	 */
	public void setFinalFrame(int frame) {
		this.finalFrame = frame;
	}

	/**
	 * Retorna o número de quadro final da sequência de quadros.
	 * 
	 * @return int
	 */
	public int getFinalFrame() {
		return finalFrame;
	}

	/**
	 * Define o quadro atual que será desenhado
	 * 
	 * @param frame
	 *            número do quadro.
	 */
	public void setCurrFrame(int frame) {
		currFrame = frame;
	}

	/**
	 * Retorna o número do quadro atual.
	 * 
	 * @return int
	 */
	public int getCurrFrame() {
		return currFrame;
	}

	/**
	 * Retorna true se a animação está sendo executada e false caso contrário.
	 * 
	 * @return boolean
	 */
	public boolean isPlaying() {
		return playing;
	}

	/**
	 * Este método é responsável por não permitir a chamada de animação na tela.
	 */
	public void hide() {
		this.drawable = false;
	}

	/**
	 * Método responsável por permitir a chamada de animação na tela.
	 */
	public void unhide() {
		this.drawable = true;
	}

	/**
	 * Método responsável por informar a classe que a animação não será
	 * executada indefinidamente. true funciona indefinidamente e false caso
	 * contrário.
	 */

	public void setLoop(boolean value) {
		this.loop = value;
	}

	/**
	 * Desenha a animação na tela.
	 */
	@Override
	public void draw() {
		if (drawable) {
			// Window.instance.getGameGraphics()
			// .drawImage(image, (int)x, (int)y, (int)x + width, (int)y +
			// height,
			// currFrame * width, 0, (currFrame +1) * width, height, null);
			double rot = rotation;

			Graphics2D g2d = (Graphics2D) Window.getInstance()
					.getGameGraphics();
			AffineTransform tx = new AffineTransform();

			tx.setToRotation(-rot, width / 2, height / 2);

			int newy = (int) (x * Math.sin(rot) + y * Math.cos(rot));
			int newx = (int) (x * Math.cos(rot) - y * Math.sin(rot));

			g2d.setTransform(tx);
			g2d.drawImage(image, newx, newy, newx + width, newy + height,
					currFrame * width, 0, (currFrame + 1) * width, height, null);
		}
	}

	// @Override
	// public void draw()
	// {
	// if (drawable)
	// Window.getInstance().getGameGraphics().drawImage(image, (int) x,
	// (int) y, (int) x + width, (int) y + height, currFrame * width,
	// 0, (currFrame + 1) * width, height, null);
	// }
}
