package projeto.controle;

import projeto.atores.Objects;
import projeto.base.Scene;

/**
 * Esta classe faz o controle da chuva que ocorre no game. O atributo isRainy
 * determina se est� chovendo ou n�o.
 * 
 * @author Luiz Alberto
 * 
 */
public class RainControl {

	public static boolean isRainy;

	/**
	 * Este m�todo � respons�vel pela execu��o da chuva do jogo. � necess�rio
	 * passar um valor inteiro para medir a for�a da tempestade, lembrando que
	 * quanto MENOR o n�mero mais ela ser� intensa.
	 * 
	 * @param rain
	 * @param rainIntensity
	 */
	public static void rain(Objects rain, int rainIntensity) {
		rain.setTotalDuration(rainIntensity);
		rain.update();
		rain.draw();
	}

	/**
	 * M�todo respons�vel por parar a chuva.
	 * 
	 * @param rain
	 * @param scene
	 */
	public static void stopRain(Objects rain, Scene scene) {
		scene.removeOverlay(rain);
	}
}
