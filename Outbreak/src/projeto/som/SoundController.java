package projeto.som;

import projeto.base.Sound;
import projeto.controle.URL;

/**
 * Esta classe realiza o controle da trilha sonora de fundo do jogo.
 * 
 * @author Luiz Alberto
 * 
 */
public class SoundController {

	private static Sound music;

	/**
	 * Executa a música passada como parâmetro.
	 * 
	 * @param filename
	 */
	public static void playMusic(String filename) {
		stopMusic();
		music = new Sound(filename);
		SoundController.music.play();
		SoundController.music.setRepeat(true);
	}

	/**
	 * Encerra a musica de fundo.
	 */
	public static void stopMusic() {
		if (SoundController.music != null) {
			music.stop();
		}
	}

	/**
	 * Este método é executado quando um inimigo morre.
	 */
	public static void enemyDead() {
		new Sound(URL.audio("zombie_atack.wav")).play();
	}

	/**
	 * Porta de madeira.
	 */
	public static void door() {
		new Sound(URL.audio("door.wav")).play();
	}

	/**
	 * Porta de ferro
	 */
	public static void ironDoor() {
		new Sound(URL.audio("porta aco open.wav")).play();
		sleep(1000);
		new Sound(URL.audio("porta aco close.wav")).play();
	}

	/**
	 * Este método executa os sons de abrir e fechar o portão de ferro.
	 */
	public static void gate() {
		new Sound(URL.audio("open gate.wav")).play();
		sleep(2000);
		new Sound(URL.audio("close gate.wav")).play();
	}

	/**
	 * Realiza um som indicando a arma sendo recarregada.
	 */
	public static void reloading() {
		new Sound(URL.audio("cock.wav")).play();
	}

	/**
	 * Som executado ao abrir inventário
	 */
	public static void menu() {
		new Sound(URL.audio("menu itens.wav")).play();
	}

	/**
	 * Som executado ao sair do inventário
	 */
	public static void escMenu() {
		new Sound(URL.audio("cancel.wav")).play();
	}

	/**
	 * Grito quando a personagem morre
	 */
	public static void deathScream() {
		new Sound(URL.audio("gritodefilme.WAV")).play();
	}

	/**
	 * Usado quando o jogo vai reiniciar
	 */
	public static void gameStart() {
		new Sound(URL.audio("gamestart.wav")).play();
	}

	/**
	 * Som da companhia TRISOFT
	 */
	public static void trisoft() {
		new Sound(URL.audio("trisoft.wav")).play();
	}
	
	/**
	 * projeto 28 de setembro
	 */
	public static void daylight() {
		new Sound(URL.audio("daylight.wav")).play();
	}

	/**
	 * Thread que para o tempo
	 * 
	 * @param tempo
	 */
	private static void sleep(int tempo) {
		try {
			Thread.sleep(tempo);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
