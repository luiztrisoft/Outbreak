package outbreak;

import java.awt.Color;
import java.util.Random;

import projeto.armaseitens.ItemControl;
import projeto.atores.MonsterFactory;
import projeto.atores.Objects;
import projeto.base.Window;
import projeto.cenario.Scenario;
import projeto.configuracoes.DirConfig;
import projeto.configuracoes.EventConfig;
import projeto.enums.Monster;
import projeto.interfaces.ScenarioInterface;
import projeto.som.SoundController;

@SuppressWarnings("serial")
public class OakHillStreets extends Scenario {//implements ScenarioInterface {

	private Objects fogo;
	private ItemControl rocketLauncher;

	public OakHillStreets(double x, double y, Window previousScreen) {
		audio = "escapetogether.mid";
		scn = "OakHillStreets.scn";
		start(x, y, previousScreen, scn, null, TEMPO_NEVOEIRO);
	}

	@Override
	public void createObjects() {
		fogo = createObjects(DirConfig.FIRE, 100, 1400, false, 10, scene);
	}

	@Override
	public void createEnemie() {
		int raioX = 10000, raioY = 2000;
		int quantidade = 90;
		npcs = MonsterFactory.getMonstro(Monster.ZUMBI, quantidade);
		for (int i = 0; i < quantidade; i++) {
			// npcs[i].x = player.x - (raioX / 2) + new Random().nextInt(raioX);
			npcs[i].x = 1500 + new Random().nextInt(raioX);
			npcs[i].y = player.y - new Random().nextInt(raioY);
			// npcs[i].powerAttack = 0;
			scene.addOverlay(npcs[i]);
		}
	}

	@Override
	public void addObjects() {
		fogo.addObject(player, npcs, scene, window, true);
	}

	@Override
	public void createItem() {
		rocketLauncher = createItem(EventConfig.rocketLauncher, DirConfig.ROCKET_LAUNCHER, 350, 1400, scene);
	}

	@Override
	public void addItem() {
		addItem(rocketLauncher, EventConfig.rocketLauncher, scene);
	}

	@Override
	public void collectedItem() {
		collectedRocketLauncher(rocketLauncher, player, control, window);
	}

	@Override
	public void objectAnimation() {
		objectAnimation(fogo);
	}

	// ================================================//
	// EVENTOS PRÓPRIOS DO CENÁRIO
	// ================================================//

	@Override
	public void events() {
		goForest();
		goConveniencia();
		goPrecint();
		goPrecint2();
		goHospital();
		// controleTempo();
	}

	/**
	 * Este método leva para a parte interna do hospital
	 */
	private void goHospital() {
		if (tileCollision(531) == true || tileCollision(533) == true) {
			SoundController.stopMusic();
			SoundController.ironDoor();
			new Hospital1F(540, 620, window);
			//new Hospital1F(10, 220, window);
		}
	}

	/**
	 * Este método leva o player para a floresta.
	 */
	private void goForest() {
		if (tileCollision(507) == true) {
			SoundController.stopMusic();
			SoundController.gate();
			new Forest(3400, 120, window);
		}
	}

	/**
	 * Este método leva para o interior da loja de conveniência do posto
	 */
	private void goConveniencia() {
		if (tileCollision(517) == true) {
			SoundController.stopMusic();
			SoundController.ironDoor();
			new LojaConveniencia(120, 420, window);
		}
	}

	/**
	 * Este método leva para o interior da delegacia de polícia pela porta da
	 * frente
	 */
	private void goPrecint() {
		if (tileCollision(521) == true) {
			SoundController.stopMusic();
			new Delegacia(400, 520, window);
		}
	}

	/**
	 * Este método leva para o interior da delegacia de polícia pela porta do
	 * estacionamento
	 */
	private void goPrecint2() {
		if (tileCollision(526) == true) {
			SoundController.stopMusic();
			SoundController.ironDoor();
			new CorredorDelegacia(670, 320, window);
		}
	}

	String single = null;

	/**
	 * Controla o tempo neste cenário
	 */
	private void controleTempo() {
		if (EventConfig.rocketLauncher == true) {

			if (single == null) {
				time.setColor(Color.RED);
				time.setTime(10, 40, 20);

				single = "valor";
			}
		}

		time.draw();
	}

}
