package projeto.base;

/**
 * Classe usada para lidar com a��es: release, pressionar ou manter pressionado
 * um bot�o ou chave. Esta classe n�o est� associada a uma chave ou bot�o.
 * 
 * @see Keyboard e Mouse.
 * @author Tiko
 * 
 */
public class InputAction {

	private static final int STATE_RELEASED = 0;
	private static final int STATE_PRESSED = 1;
	private static final int STATE_WAITING_FOR_RELEASE = 2;
	private int behavior;
	private int quantity;
	private int state;

	/**
	 * Criar um novo InputAction com comportamento espec�fico, com o estado =
	 * release quantidade de cliques iguais a 0.
	 * 
	 * @param behavior
	 *            - � ou pode ser DETECT_EVERY_PRESS ou
	 *            DETECT_INITIAL_PRESS_ONLY
	 * @version 1.0
	 */
	public InputAction(int behavior) {
		this.behavior = behavior;
		state = STATE_RELEASED;
		quantity = 0;
	}

	/**
	 * Definir comportamento ou chave do bot�o.
	 * 
	 * @param behavior
	 *            - � ou pode ser DETECT_EVERY_PRESS ou
	 *            DETECT_INITIAL_PRESS_ONLY
	 * @see InputBase
	 * @version 1.0
	 */
	public synchronized void setBehavior(int behavior) {
		this.behavior = behavior;
	}

	/**
	 * � um m�todo sobrecarregado do metodo press(int amounth). Seu par�metro �
	 * =1.
	 * 
	 * @see InputBase
	 * @version 1.0
	 */
	public synchronized void press() {
		press(1);
	}

	/**
	 * Colocar o estado do bot�o ou da tecla pressionada e uma quantidade
	 * pressionada.
	 * 
	 * @param amout
	 *            - How many times the key went pressed.
	 * @version 1.0
	 */
	public synchronized void press(int amount) {
		if (state != STATE_WAITING_FOR_RELEASE) {
			this.quantity += amount;
			state = STATE_PRESSED;
		}
	}

	/**
	 * Colocar o estado do bot�o ou tecla como liberado.
	 * 
	 * @version 1.0
	 */
	public synchronized void release() {
		state = STATE_RELEASED;
	}

	/**
	 * M�todo usado para saber se uma tecla � pressionada
	 * 
	 * @return boolean - true quando � pressionada, false caso contr�rio.
	 * @version 1.0
	 */
	public synchronized boolean isPressed() {
		return (getAmount() != 0);
	}

	/**
	 * Retorna uma quantidade de cliques do mouse e/ou do teclado pressionados.
	 * Se o comportamento for DETECT_INITAL_PRESS_ONLY, esse m�todo ir� retornar
	 * apenas o clique inicial. Para o retorno do o mouse clicado novamente o
	 * usu�rio precisa de libera��o. Se o comportamento � DETECT_EVERY_PRESS,
	 * este m�todo ir� devolver a quantidade de clique ou quantas vezes a tecla
	 * foi pressionada.
	 * 
	 * @return int - Quantidade pressionada.
	 * @see void press(int amount);
	 * @version 1.0
	 * */
	public synchronized int getAmount() {
		int quant = quantity;
		if (quant != 0) {
			if (state == STATE_RELEASED) {
				quantity = 0;
			} else if (behavior == InputBase.DETECT_INITIAL_PRESS_ONLY) {
				state = STATE_WAITING_FOR_RELEASE;
				quantity = 0;
			}
		}
		return quant;
	}
}
