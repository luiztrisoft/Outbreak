package projeto.base;

/**
 * Classe usada para lidar com a��es para os bot�es ou para as chaves.
 * 
 * @author Tiko
 * 
 */
public class InputBase {

	/**
	 * Este comportamento � renpons�vel por detectar quando uma tecla est� sendo
	 * pressionada.
	 */
	public static final int DETECT_EVERY_PRESS = 0;

	/**
	 * Comportamento respons�vel por detectar o primeiro pressionamento do bot�o
	 * ou clique, depois disso, � necess�ria a libera��o do bot�o ou tecla para
	 * a detec��o seguinte.
	 */
	public static final int DETECT_INITIAL_PRESS_ONLY = 1;
}
