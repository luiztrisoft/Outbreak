package projeto.armaseitens;

import java.util.LinkedList;

import projeto.atores.NPC;
import projeto.base.Scene;
import projeto.base.Sound;
import projeto.base.Window;
import projeto.configuracoes.GameConfig;

/**
 * Esta classe é responsável por realizar o controle e movimento dos tiros
 * disparados.
 * 
 * @author Luiz Alberto
 * 
 */
public class ShotControl {

	LinkedList<Shot> objects = new LinkedList<>();
	WeaponControl arsenal = new WeaponControl();

	/**
	 * Este método faz o controle do tiro adicionando-o a lista de tiros,
	 * mostrando-o na tela e efetuando seu disparo sonoro.
	 * 
	 * @param x
	 * @param y
	 * @param way
	 * @param scene
	 */
	public void addShot(double x, double y, int way, Scene scene) {
		Shot shot = new Shot(x, y, way);
		if (arsenal.shoot() == true) {
			objects.addFirst(shot);
			scene.addOverlay(shot);
			soundShooting();
		}
	}

	/**
	 * Método responsável pela ação do tiro.
	 * 
	 * @param window
	 * @param scene
	 * @param npc
	 */
	public void run(Window window, Scene scene, NPC[] npc) {
		for (int i = 0; i < objects.size(); i++) {
			Shot shot = objects.removeFirst();
			shot.move();
			shot.update();
			shot.reachesTile(scene, shot);

			for (int j = 0; j < npc.length; j++) {
				shot.reachesNPC(npc[j], scene, shot);
			}

			if (shot.x > 1 && shot.x + shot.width < GameConfig.WIDTH) {
				objects.addLast(shot);
			} else {
				scene.removeOverlay(shot);
			}
		}
	}

	/**
	 * Este método dispara o som do tiro da arma indexada.
	 */
	private void soundShooting() {
		new Sound(WeaponControl.weaponList.get(WeaponControl.getIndex())
				.getSound()).play();
	}
}
