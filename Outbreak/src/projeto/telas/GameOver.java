package projeto.telas;

import java.awt.Color;
import java.awt.Font;

import projeto.armaseitens.ItemControl;
import projeto.armaseitens.WeaponControl;
import projeto.atores.Player;
import projeto.base.GameImage;
import projeto.base.Keyboard;
import projeto.base.Sound;
import projeto.base.Window;
import projeto.configuracoes.EventConfig;
import projeto.controle.URL;
import projeto.som.SoundController;

public class GameOver {

	private static Font font;
	public static void gameOver(Window previousScreen) {
		font = new Font("Consolas", Font.TRUETYPE_FONT, 20);
		
		try {
			Window window = previousScreen;
			GameImage teladeFundo = new GameImage(URL.sprite("dead.png"));
			SoundController.stopMusic();
			
			SoundController.deathScream();
			
			window.clear(Color.BLACK);
			Keyboard keyboard = window.getKeyboard();

			WeaponControl arsenal = new WeaponControl();
			while (true) {
				teladeFundo.draw();
				window.drawText("Aperte ENTER para recomeçar", 280, 560, Color.red, font);
				window.update();
				window.display();

				while (WeaponControl.getIndex() != 0) {
					arsenal.change();
				}

				// ::::::::Limpa a lista de armas::::::::
				for (int i = 1; i < WeaponControl.weaponList.size(); i++) {
					WeaponControl.weaponList.remove(i);
				}

				// ::::::::Limpa a lista de itens::::::::
				for (int i = 0; i < ItemControl.itemList.size(); i++) {
					ItemControl.itemList.remove(i);
				}

				if (keyboard.keyDown(Keyboard.ENTER_KEY)) {
					SoundController.gameStart();
					Player.infection = 0;
					Player.HP = 100;
					EventConfig.restartGame();
					//Trisoft.trisoft(window);
					
					
					FirstScreen.method(window); //ORIGINAL
					
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
