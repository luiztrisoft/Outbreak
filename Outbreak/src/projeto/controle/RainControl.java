package projeto.controle;

import projeto.atores.Objects;
import projeto.base.Scene;

/**
 * Esta classe faz o controle da chuva que ocorre no game. O atributo isRainy
 * determina se está chovendo ou não.
 * 
 * @author Luiz Alberto
 * 
 */
public class RainControl {

	public static boolean isRainy;

	/**
	 * Este método é responsável pela execução da chuva do jogo. É necessário
	 * passar um valor inteiro para medir a força da tempestade, lembrando que
	 * quanto MENOR o número mais ela será intensa.
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
	 * Método responsável por parar a chuva.
	 * 
	 * @param rain
	 * @param scene
	 */
	public static void stopRain(Objects rain, Scene scene) {
		scene.removeOverlay(rain);
	}
}
