package projeto.base;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

/**
 * Classe respons�vel por animar um GameImage usando pe�as da imagem, como
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
	 * O construtor cria uma anima��o de objetos de classe. A sequ�ncia
	 * configurada para iniciar o quadro vai de uma at� o lastFrame.
	 * 
	 * Por exemplo: setSequence(0, totalFrames).
	 * 
	 * 0 = initial frame. lastFrame = totalFrames.
	 * 
	 * @param fileName
	 *            Caminho e nome da imagem.
	 * @param totalFrames
	 *            N�mero de quadro que formam a imagem.
	 * @param Ele
	 *            diz se a anima��o � executada repetidamente. Se o valor for
	 *            verdadeiro, quando o �ltimo quadro � mostrado, ele retorna at�
	 *            o primeiro. Se for falso, a anima��o mostrar� o �ltimo quadro
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
	 * boolean loop). O par�metro boolean 'loop' recebe o valor verdadeiro.
	 */
	public Animation(String fileName, int totalFrames) {
		this(fileName, totalFrames, true);
	}

	/**
	 * Sobrecarga do construtor Animation(String fileName, int totalFrames,
	 * boolean loop). O par�metro boolean 'loop' recebe o valor verdadeiro. O
	 * valor do par�metro int 'totalFrames' � igual a 1.
	 */
	public Animation(String fileName) {
		this(fileName, 1, true);
	}

	/**
	 * Configure o tempo que o quadro ser� mostrado na tela.
	 * 
	 * @param frame
	 *            Numero do quadro
	 * @param time
	 *            Milisegundos de tempo que o quadro ser� mostrado na tela.
	 */
	public void setDuration(int frame, long time) {
		frameDuration[frame] = time;
	}

	/**
	 * Retorna o tempo em que a estrutura � mostrada na tela.
	 * 
	 * @param frame
	 *            Numero do quadro
	 * @return long - Tempo em milisegundos
	 */
	public long getDuration(int frame) {
		return frameDuration[frame];
	}

	/**
	 * Definir o quadro inicial e final da sequ�ncia de anima��o. A sequ�ncia
	 * ser� executada indefinidamente
	 * 
	 * @param initialFrame
	 * @param finalFrame
	 */
	public void setSequence(int initialFrame, int finalFrame) {
		setSequence(initialFrame, finalFrame, true);
	}

	/**
	 * Definir o quadro inicial e fianl na sequ�ncia de anima��o. E se a
	 * anima��o ser� executada indefinidamente.
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
	 * Definir o quadro inicial e final na sequ�ncia de anima��o e o tempo de
	 * execu��o.
	 * 
	 * @param initialFrame
	 * @param finalFrame
	 * @param time
	 */
	public void setSequenceTime(int initialFrame, int finalFrame, long time) {
		setSequenceTime(initialFrame, finalFrame, true, time);
	}

	/**
	 * Definir o quadro inicial e final na sequ�ncia de anima��o, o tempo de
	 * execu��o e se ele ser� executado indefinidamente.
	 * 
	 * @param initialFrame
	 * @param finalFrame
	 * @param time
	 * @param loop
	 *            True para indefinidamente, false caso contr�rio.
	 */

	public void setSequenceTime(int initialFrame, int finalFrame, boolean loop,
			long time) {
		setSequence(initialFrame, finalFrame, loop);
		time = time / (finalFrame - initialFrame + 1);
		for (int i = initialFrame; i <= finalFrame; i++)
			this.frameDuration[i] = time;
	}

	/**
	 * Este m�todo informa se a anima��o for repetida.
	 * 
	 * @return boolean
	 */
	public boolean isLooping() {
		return loop;
	}

	/**
	 * Define o tempo de todos os quadros. Quando o tempo � passado, a divis�o
	 * entre dura��o total e totalFrames deixaria algum descanso:
	 * 
	 * Exemplo: totalDuration = 100 totalFrames = 11 timeFrame = 100/11 = 9 rest
	 * = 100 - 11 * 9 = 1
	 * 
	 * Assim, a dura��o total real �(time / numberFrames) * numberFrames
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
	 * M�todo respons�vel por executar a mudan�a de frames.
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
	 * Para a execu��o e coloca o quadro inicial como quadro atual.
	 */
	public void stop() {
		this.currFrame = initialFrame;
		this.playing = false;
	}

	/**
	 * M�todo respons�vel por iniciar a execu��o da anima��o.
	 */
	public void play() {
		this.playing = true;
	}

	/**
	 * M�todo respons�vel por interromper a anima��o.
	 */
	public void pause() {
		this.playing = false;
	}

	/**
	 * Define o quadro inicial da sequ�ncia de quadros.
	 * 
	 * @param frame
	 *            n�mero do quadro
	 */
	public void setInitialFrame(int frame) {
		this.initialFrame = frame;
	}

	/**
	 * Retirna o n�mero do quadro inicial.
	 * 
	 * @return int
	 */
	public int getInitialFrame() {
		return initialFrame;
	}

	/**
	 * Define o quadro final da sequ�ncia de quadros.
	 * 
	 * @param frame
	 *            n�mero do quadro.
	 */
	public void setFinalFrame(int frame) {
		this.finalFrame = frame;
	}

	/**
	 * Retorna o n�mero de quadro final da sequ�ncia de quadros.
	 * 
	 * @return int
	 */
	public int getFinalFrame() {
		return finalFrame;
	}

	/**
	 * Define o quadro atual que ser� desenhado
	 * 
	 * @param frame
	 *            n�mero do quadro.
	 */
	public void setCurrFrame(int frame) {
		currFrame = frame;
	}

	/**
	 * Retorna o n�mero do quadro atual.
	 * 
	 * @return int
	 */
	public int getCurrFrame() {
		return currFrame;
	}

	/**
	 * Retorna true se a anima��o est� sendo executada e false caso contr�rio.
	 * 
	 * @return boolean
	 */
	public boolean isPlaying() {
		return playing;
	}

	/**
	 * Este m�todo � respons�vel por n�o permitir a chamada de anima��o na tela.
	 */
	public void hide() {
		this.drawable = false;
	}

	/**
	 * M�todo respons�vel por permitir a chamada de anima��o na tela.
	 */
	public void unhide() {
		this.drawable = true;
	}

	/**
	 * M�todo respons�vel por informar a classe que a anima��o n�o ser�
	 * executada indefinidamente. true funciona indefinidamente e false caso
	 * contr�rio.
	 */

	public void setLoop(boolean value) {
		this.loop = value;
	}

	/**
	 * Desenha a anima��o na tela.
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
