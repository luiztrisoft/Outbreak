package outbreak;

import projeto.atores.MonsterFactory;
import projeto.base.Window;
import projeto.cenario.Scenario;
import projeto.controle.ScreenControl;
import projeto.enums.Monster;
import projeto.interfaces.ScenarioInterface;
import projeto.som.SoundController;

@SuppressWarnings("serial")
public class CorredorDelegacia extends Scenario implements ScenarioInterface {

	public CorredorDelegacia(double x, double y, Window previousScreen) {

		npcs = new MonsterFactory[1];
		audio = "hallDelegacia.mid";
		scn = "corredorDelegacia.scn";
		start(x, y, previousScreen, scn, audio, TEMPO_LIMPO);

	}

	@Override
	public void classControl(Window previousScreen) {
		control = new ScreenControl();
		window = previousScreen;
		keyboard = window.getKeyboard();
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
		int quantidadeInimigos = 0;
		npcs = MonsterFactory.getMonstro(Monster.ZUMBI, quantidadeInimigos);

		for (int i = 0; i < quantidadeInimigos; i++) {
			npcs[i].perceptionRay = 100;
			scene.addOverlay(npcs[i]);
		}
	}

	@Override
	public void isRainy(boolean isRainy) {
		// TODO Auto-generated method stub
	}

	@Override
	public void drawFrame() {
		gameControl(control, window, player, loop);
		getOffSetPlayer(scene, player);
		getOffSetNPCZumbi(scene, npcs);
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
		runMainHall();
		runStreets();
	}

	private void runMainHall() {
		if (tileCollision( 004) == true) {
			SoundController.stopMusic();
			SoundController.ironDoor();
			new Delegacia(660, 150, window);
		}
	}

	private void runStreets() {
		if (tileCollision( 25) == true) {
			SoundController.stopMusic();
			SoundController.ironDoor();
			new OakHillStreets(2250, 1150, window);
		}
	}

}
