package projeto.base;

/**
 * Classe usada para lidar com ações para os botões ou para as chaves.
 * 
 * @author Tiko
 * 
 */
public class InputBase {

	/**
	 * Este comportamento é renponsável por detectar quando uma tecla está sendo
	 * pressionada.
	 */
	public static final int DETECT_EVERY_PRESS = 0;

	/**
	 * Comportamento responsável por detectar o primeiro pressionamento do botão
	 * ou clique, depois disso, é necessária a liberação do botão ou tecla para
	 * a detecção seguinte.
	 */
	public static final int DETECT_INITIAL_PRESS_ONLY = 1;
}
