package projeto.telas;

import java.awt.Color;

import projeto.base.GameImage;
import projeto.base.Window;
import projeto.cenario.Scenario;
import projeto.controle.ScreenControl;
import projeto.controle.URL;
import projeto.som.SoundController;

/**
 * Apresentação do logo da TRISOFT.
 * 
 * @author Luiz Alberto
 * 
 */
public class Trisoft {
	private static ScreenControl control;

	public static void trisoft(Window previousScreen) {
		control = new ScreenControl();
		Window window = previousScreen;
		GameImage teladeFundo = new GameImage(URL.sprite("trisoft.png"));
		// boolean exec = true;
		// int wait = 0;

		// while (exec) {
		Scenario.screenControl(control, window);
		window.clear(Color.BLACK);
		teladeFundo.draw();
		window.display();

		SoundController.trisoft();

		// if (wait < 60) {
		// window.display();
		// wait++;
		// } else {
		// MainScreen.mainScreen(window);
		// }

		try {
			Thread.sleep(4000);
		} catch (Exception e) {
		}
		//MenuPrincipalRE3.mainScreen(window);
		MainScreen.mainScreen(window);
		// }
		window.exit();
	}
}
