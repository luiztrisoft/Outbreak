package projeto.telas;

import java.awt.Color;
import java.awt.Font;

import projeto.base.GameImage;
import projeto.base.Keyboard;
import projeto.base.Sound;
import projeto.base.Window;
import projeto.cenario.Scenario;
import projeto.controle.URL;
import projeto.som.SoundController;

/**
 * Tela principal do jogo. A única coisa que o usuário faz nesta tela é apertar
 * ENTER para iniciar o jogo.
 * 
 * @author Luiz Alberto
 * 
 */
@SuppressWarnings("serial")
public class MainScreen {//extends Scenario {

	private static Font font;

	public static void mainScreen(Window previousScreen) {
		font = new Font("Courier New", Font.BOLD, 30);
		Window window = previousScreen;
		GameImage teladeFundo = new GameImage(URL.sprite("outbreak.png"));
		Keyboard keyboard = window.getKeyboard();
		boolean exec = true;

		SoundController.playMusic(URL.audio("RE0SaveRoomArranged.mid"));

		while (exec) {
			window.clear(Color.BLACK);
			teladeFundo.draw();
			window.drawText("APERTE ENTER", 300, 450, Color.green, font);
			window.display();

			if (keyboard.keyDown(Keyboard.ENTER_KEY)) {
				new Sound(URL.audio("guitar.wav")).play();
				new SelectLevelGame(window);
			}
		}
		window.exit();
	}
}
