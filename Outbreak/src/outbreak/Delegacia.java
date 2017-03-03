package outbreak;

import projeto.atores.MonsterFactory;
import projeto.base.Window;
import projeto.cenario.Scenario;
import projeto.enums.Monster;
import projeto.som.SoundController;

@SuppressWarnings("serial")
public class Delegacia extends Scenario {

	public Delegacia(double x, double y, Window previousScreen) {

		audio = "hallDelegacia.mid";
		scn = "delegacia.scn";
		start(x, y, previousScreen, scn, audio, TEMPO_LIMPO);
	}

	@Override
	public void createItem() {
		// TODO Auto-generated method stub

	}

	@Override
	public void createObjects() {
		// TODO Auto-generated method stub
	}

	@Override
	public void createEnemie() {

		int quantidadeZumbi = 4;
		npcs = MonsterFactory.getMonstro(Monster.ZUMBI, quantidadeZumbi);
		for (int i = 0; i < quantidadeZumbi; i++) {
			npcs[i].perceptionRay = 150;
			scene.addOverlay(npcs[i]);
		}

		/*
		 * é possível criar um novo npc misturado com os npcs de zumbis. dog =
		 * NPCZombie.getMonstro(Monstros.CACHORRO, quantidadeCachorro); Depois
		 * de criar você deve inserir no método characterControl as chamadas:
		 * 
		 * player.enemiePresence(dog, window, scene);
		 * 
		 * control.npcControl(dog,
		 * 
		 * 
		 * player, window, scene);
		 * 
		 * Terminado isso ainda deve ajustar o novo npc na tela:
		 * 
		 * getOffSetNPCZumbi(scene, dog);
		 */

	}

	@Override
	public void isRainy(boolean isRainy) {
		// TODO Auto-generated method stub
	}

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
		// TODO Auto-generated method stub
	}

	@Override
	public void collectedItem() {
		// TODO Auto-generated method stub
	}

	// ================================================//
	// EVENTOS PRÓPRIOS DO CENÁRIO
	// ================================================//
	int i = 0;

	@Override
	public void events() {
		runStreets();
		runCorredor();
	}

	private void runStreets() {
		if (tileCollision( 002) == true) {
			SoundController.stopMusic();
			new OakHillStreets(1650, 1400, window);
		}
	}

	private void runCorredor() {
		if (tileCollision( 006) == true) {
			SoundController.stopMusic();
			SoundController.ironDoor();
			new CorredorDelegacia(50, 400, window);
		}
	}

}
