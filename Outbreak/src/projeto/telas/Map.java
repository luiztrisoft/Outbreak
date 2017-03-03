package projeto.telas;

import projeto.base.GameImage;
import projeto.base.Keyboard;
import projeto.base.Window;
import projeto.controle.URL;

/**
 * Esta classe é responsável por apresentar o mapa da cidade para o jogador.
 * 
 * @author Luiz Alberto
 * 
 */
public class Map {

	public static boolean map = false;
	private Keyboard keyboard;
	private GameImage screenMap = new GameImage(URL.sprite("map.png"));

	/**
	 * Método que pinta o mapa na tela caso o atributo map seja verdadeiro.
	 * 
	 * @param window
	 */
	public void control(Window window) {
		keyboard = window.getKeyboard();

		while (map == true) {
			screenMap.draw();
			window.display();
			if (keyboard.keyDown(Keyboard.M_KEY)) {
				if (map == true) {
					map = false;
				}
			}
		}
	}

}
