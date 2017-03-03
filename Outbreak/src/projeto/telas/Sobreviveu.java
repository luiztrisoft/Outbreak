package projeto.telas;

import java.awt.Color;
import java.awt.Font;

import outbreak.ResidentEvil3;
import projeto.base.GameImage;
import projeto.base.Keyboard;
import projeto.base.Sound;
import projeto.base.Window;
import projeto.controle.URL;
import projeto.som.SoundController;

public class Sobreviveu {
	private static Font font;
	public static void mainScreen(Window previousScreen) {
		font = new Font("Courier New", Font.BOLD, 40);
		Window window = previousScreen;
		GameImage teladeFundo = new GameImage(URL.sprite("parabens.png"));
		Keyboard keyboard = window.getKeyboard();
		boolean exec = true;

		SoundController.playMusic(URL.audio("end.mid"));

	
		while (exec) {
			window.clear(Color.BLACK);
			teladeFundo.draw();
//			window.drawText("Esta foi minha homenagem a", 100, 350, Color.YELLOW, font);
//			window.drawText("este game que marcou gerações", 70, 400, Color.yellow, font);
//
//			window.drawText("Feliz 28 de Setembro", 180, 470, Color.green, font);
			
			window.drawText("ESTA FOI MINHA HOMENAGEM A", 100, 350, Color.GREEN, font);
			window.drawText("ESTE GAME QUE MARCOU GERAÇÕES", 70, 400, Color.GREEN, font);

			window.drawText("FELIZ 28 DE SETEMBRO", 180, 470, Color.RED, font);

			window.display();
			
			
		}
		window.exit();
	}
}
