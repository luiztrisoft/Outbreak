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

public class Setembro28 {
	

	public static void mainScreen(Window previousScreen) {
		
		Window window = previousScreen;
		GameImage teladeFundo = new GameImage(URL.sprite("28 de setembro.png"));
		Keyboard keyboard = window.getKeyboard();
		boolean exec = true;

		boolean jaExecutouAudio = false;
		
		while (exec) {
			
				
			window.clear(Color.BLACK);
			
			
			
			teladeFundo.draw();
		//	window.drawText("APERTE ENTER", 300, 450, Color.green, font);
			window.display();
			
			if(jaExecutouAudio == false){
				SoundController.daylight();
				jaExecutouAudio = true;
			}
			

			if (keyboard.keyDown(Keyboard.ENTER_KEY)) {
				new ResidentEvil3(100, 250, window);
			}
			
			
		}
		window.exit();
	}
}
