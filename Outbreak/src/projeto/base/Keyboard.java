package projeto.base;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Hashtable;

/**
 * Classe responsável pela manipulação das teclas do teclado e seu
 * comportamento.
 * 
 * @author Luiz Alberto
 * 
 */
public final class Keyboard extends InputBase implements KeyListener {

	public static final int ENTER_KEY = 10;
	public static final int ESCAPE_KEY = 27;
	public static final int SPACE_KEY = 32;
	public static final int LEFT_KEY = 37;
	public static final int UP_KEY = 38;
	public static final int RIGHT_KEY = 39;
	public static final int DOWN_KEY = 40;
	public static final int A_KEY = 65;
	public static final int D_KEY = 68;
	public static final int M_KEY = 77;
	public static final int S_KEY = 83;

	@SuppressWarnings("rawtypes")
	private Hashtable keysPressed;

	/**
	 * Cria uma instância do teclado com as seguintes chaves e seu
	 * comportamento:
	 * 
	 * As teclas UP_KEY, LEFT_KEY, RIGHT_KEY e DOWN_KEY tem comportamento
	 * DETECT_EVERY_PRESS. As teclas ESCAPE_KEY, SPACE_KEY e ENTER_KEY tem
	 * comportamento DETECT_INITIAL_PRESS_ONLY.
	 */
	@SuppressWarnings("rawtypes")
	public Keyboard() {
		keysPressed = new Hashtable();

		addKey(UP_KEY, Keyboard.DETECT_EVERY_PRESS);
		addKey(LEFT_KEY, Keyboard.DETECT_EVERY_PRESS);
		addKey(RIGHT_KEY, Keyboard.DETECT_EVERY_PRESS);
		addKey(DOWN_KEY, Keyboard.DETECT_EVERY_PRESS);
		addKey(ESCAPE_KEY, Keyboard.DETECT_INITIAL_PRESS_ONLY);
		addKey(SPACE_KEY, Keyboard.DETECT_INITIAL_PRESS_ONLY);
		addKey(ENTER_KEY, Keyboard.DETECT_INITIAL_PRESS_ONLY);
		addKey(A_KEY, Keyboard.DETECT_INITIAL_PRESS_ONLY);
		addKey(S_KEY, Keyboard.DETECT_INITIAL_PRESS_ONLY);
		addKey(D_KEY, Keyboard.DETECT_INITIAL_PRESS_ONLY);
		addKey(M_KEY, Keyboard.DETECT_INITIAL_PRESS_ONLY);
	}

	/**
	 * Método usado para saber se uma tecla é pressionada
	 * 
	 * @param key
	 *            O código apresenta em 'teclado' ou 'keyEvent'.
	 * @return boolean - true quando é pressionado e false caso contrário
	 * @see KeyEvent
	 * @see Keyboard
	 */
	public boolean keyDown(int key) {
		if (keysPressed.containsKey(key)) {
			InputAction temp = (InputAction) keysPressed.get(key);
			return temp.isPressed();
		}
		return false;
	}

	/**
	 * Sobrecarga do método addKey(int key, int behavior), o comportamento será
	 * DETECT_INITIAL_PRESS_ONLY
	 * 
	 * @param key
	 * 
	 *            O código da chave, qua pode ser encontrado em 'KeyEvent'
	 */
	public void addKey(int key) {
		addKey(key, Keyboard.DETECT_INITIAL_PRESS_ONLY);
	}

	/**
	 * Método usado para adicionar uma chave para uma instância do teclado e seu
	 * comportamento.
	 * 
	 * @param key
	 *            O código da chave, qua pode ser encontrado em 'KeyEvent'
	 * @param behavior
	 *            O comportamento da chave pode ser encontrado em 'KeyBoard' ou
	 *            em 'InputBase'
	 * @see KeyEvent
	 * @see InputBase
	 */
	@SuppressWarnings("unchecked")
	public void addKey(int key, int behavior) {
		removeKey(key);
		keysPressed.put(key, new InputAction(behavior));
	}

	/**
	 * Remove a chave da instância do teclado. Se a chave não existir na
	 * instância, não gera nenhum erro.
	 * 
	 * @param key
	 *            O código da chave, qua pode ser encontrado em 'KeyEvent' ou
	 *            'Keyboard'
	 * @see KeyEvent
	 * @see Keyboard
	 */
	public void removeKey(int key) {
		keysPressed.remove(key);
	}

	/**
	 * Definir um novo comportamento para uma chave. Se a chave não existir no
	 * caso de um teclado, não gera nenhum erro.
	 * 
	 * @param key
	 *            O código da chave, qua pode ser encontrado em 'KeyEvent' ou
	 *            'Keyboard'
	 * @param behavior
	 * @see KeyEvent
	 * @see Keyboard
	 */
	public void setBehavior(int key, int behavior) {
		if (keysPressed.containsKey(key))
			addKey(key, behavior);
	}

	public void keyTyped(KeyEvent e) {
		// e.consume();
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		if (keysPressed.containsKey(key)) {
			InputAction temp = (InputAction) keysPressed.get(key);
			temp.press();
		}
		// e.consume();
	}

	@Override
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		if (keysPressed.containsKey(key)) {
			InputAction temp = (InputAction) keysPressed.get(key);
			temp.release();
		}
		// e.consume();
	}

}
