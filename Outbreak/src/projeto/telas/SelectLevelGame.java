package projeto.telas;

import projeto.base.Keyboard;
import projeto.base.Sound;
import projeto.base.Sprite;
import projeto.base.Window;
import projeto.configuracoes.AmmunitionConfig;
import projeto.configuracoes.EventConfig;
import projeto.configuracoes.GameConfig;
import projeto.controle.URL;
import projeto.som.SoundController;

/**
 * Nesta tela o jogador escolhe o nível de dificuldade do jogo.
 * 
 * @author Luiz Alberto
 * 
 */
public class SelectLevelGame {

	Window window;
	Sprite background;
	Keyboard keyboard;
	int option = 0;

	/**
	 * construtor padrão da classe.
	 * 
	 * @param previousScreen
	 */
	public SelectLevelGame(Window previousScreen) {
		classControl(previousScreen);
		loop();
	}

	/**
	 * Carrega os objetos necessários e executa o loop.
	 * 
	 * @param previousScreen
	 */
	private void classControl(Window previousScreen) {
		window = previousScreen;
		background = new Sprite(URL.sprite("nivel.png"), 5);

		keyboard = window.getKeyboard();
		keyboard.setBehavior(Keyboard.UP_KEY,
				Keyboard.DETECT_INITIAL_PRESS_ONLY);
		keyboard.setBehavior(Keyboard.DOWN_KEY,
				Keyboard.DETECT_INITIAL_PRESS_ONLY);
	}

	/**
	 * Seleção de dificuldade
	 */
	private void choise() {
		boolean choise = true;
		if (keyboard.keyDown(Keyboard.UP_KEY)) {
			if (option > 0)
				option--;
		} else {
			if (keyboard.keyDown(Keyboard.DOWN_KEY)) {
				if (option < 4)
					option++;
			} else {
				choise = false;
			}
		}
		if (choise) {
			new Sound(URL.audio("guitar.wav")).play();
		}
		background.setCurrFrame(option);
	}

	/**
	 * Loop do jogo.
	 */
	private void loop() {
		while (true) {
			draw();
			choise();
			if (keyboard.keyDown(Keyboard.ENTER_KEY)) {
				playGame();
			}
		}
	}

	/**
	 * Após selecionar a dificuldade, o jogador vai para a apresentação do jogo.
	 */
	private void playGame() {
		GameConfig.setLevel(option + 1);
		EventConfig.firstAidKit = EventConfig.FirstAidKitQuantity();
		AmmunitionConfig.balasPorNivelDeDificuldade();

		keyboard.setBehavior(Keyboard.UP_KEY, Keyboard.DETECT_EVERY_PRESS);
		keyboard.setBehavior(Keyboard.DOWN_KEY, Keyboard.DETECT_EVERY_PRESS);
		SoundController.stopMusic();
		new Sound(URL.audio("rosnado.wav")).play();
		Opening.open(window);
	}

	/**
	 * Pinta os objetos e os atualiza na tela.
	 */
	private void draw() {
		background.draw();
		window.update();
	}
}
