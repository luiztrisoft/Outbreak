package projeto.telas;

import java.awt.Color;

import projeto.base.GameImage;
import projeto.base.Window;
import projeto.cenario.Scenario;
import projeto.controle.ScreenControl;
import projeto.controle.URL;

/**
 * Tela sem nada de especial que antecede a tela inicial "TRISOFT".
 * 
 * @author Luiz Alberto
 * 
 */
public class FirstScreen {
	private static ScreenControl control;

	public static void method(Window previousScreen) {
		control = new ScreenControl();
		Window window = previousScreen;
		GameImage teladeFundo = new GameImage(URL.sprite("aviso.png"));
		boolean exec = true;
		int wait = 0;

		while (exec) {
			Scenario.screenControl(control, window);
			window.clear(Color.BLACK);
			teladeFundo.draw();
			window.display();

			if (wait < 360) {
				window.display();
				wait++;
			} else {
				//MainScreen.mainScreen(window);
				Trisoft.trisoft(window);
			}

		}
		window.exit();
	}
}
