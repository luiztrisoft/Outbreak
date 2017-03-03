package outbreak;

import projeto.armaseitens.ItemControl;
import projeto.atores.MonsterFactory;
import projeto.base.Window;
import projeto.cenario.Scenario;
import projeto.configuracoes.DirConfig;
import projeto.configuracoes.EventConfig;
import projeto.configuracoes.ItemNameConfig;
import projeto.configuracoes.TypeItemConfig;
import projeto.enums.Monster;
import projeto.interfaces.ScenarioInterface;
import projeto.som.SoundController;

@SuppressWarnings("serial")
public class LojaConveniencia extends Scenario implements ScenarioInterface {

	private ItemControl machinegun, shotgun, chaveDelegacia;

	public LojaConveniencia(double x, double y, Window previousScreen) {
		start(x, y, previousScreen, "LojaConveniencia.scn", "conveniencia.wav", TEMPO_LIMPO);
	}

	@Override
	public void createItem() {
		shotgun = createItem(EventConfig.shotgun, DirConfig.SHOTGUN, 500, 180, scene);
		machinegun = createItem(EventConfig.machineGun, DirConfig.MACHINEGUN, 600, 180, scene);
		chaveDelegacia = createItem(EventConfig.precintKey, DirConfig.KEY, 700, 180, scene);

	}

	@Override
	public void createObjects() {
		// TODO Auto-generated method stub

	}

	@Override
	public void createEnemie() {
		npcs = MonsterFactory.getMonstro(Monster.CACHORRO, 1);
		if (EventConfig.zumbiConvenienciaEstaMorto == false) {
			npcs[0].x = 400;
			npcs[0].y = 120;
			npcs[0].pursuitVelocity = 2;
			npcs[0].powerAttack = 0.01;
			scene.addOverlay(npcs[0]);
		} else {
			npcs[0].dead(scene);
		}
	}

	@Override
	public void isRainy(boolean isRainy) {
		// TODO Auto-generated method stub

	}

	// @Override
	// public void drawFrame() {
	// gameControl(control, window, player, loop);
	// getOffSetPlayer(scene, player);
	//
	// // zumbi especial
	// if (EventConfig.zumbiConvenienciaEstaMorto == false) {
	// getOffSetNPCZumbi(scene, npcs);
	// }
	// }

	// @Override
	// public void drawMap() {
	// mapControl();
	//
	// }

	@Override
	public void characterControl() {
		player.playerControl(keyboard, window, scene);
		player.enemiePresence(npcs, window, scene);
		control.npcControl(npcs, player, window, scene);
	}

	@Override
	public void addObjects() {
		// TODO Auto-generated method stub
	}

	@Override
	public void objectAnimation() {
		// TODO Auto-generated method stub
	}

	@Override
	public void addItem() {
		addItem(machinegun, EventConfig.machineGun, scene);
		addItem(shotgun, EventConfig.shotgun, scene);
		addItem(chaveDelegacia, EventConfig.precintKey, scene);
	}

	public void collectedItem() {
		collectedMachinegun(machinegun, player, control, window);
		collectedShotgun(shotgun, player, control, window);

		EventConfig.precintKey = collectedItem(chaveDelegacia, ItemNameConfig.PRECINT_KEY, DirConfig.KEY,
				EventConfig.precintKey, TypeItemConfig.ITEM, player, control, window);
	}

	@Override
	public void events() {
		runCity();
		zombieIsAlive();
	}

	/**
	 * Verifica se o zumbi da conveniencia está vivo para poder então
	 * instanciá-lo
	 */
	private void zombieIsAlive() {
		if (EventConfig.zumbiConvenienciaEstaMorto == false && npcs[0].HP <= 0) {
			EventConfig.zumbiConvenienciaEstaMorto = true;
		}
	}

	/**
	 * Este método leva para a cidade.
	 */
	private void runCity() {
		if (tileCollision(03) == true) {
			SoundController.ironDoor();
			new OakHillStreets(6440, 380, window);
		}
	}

}
