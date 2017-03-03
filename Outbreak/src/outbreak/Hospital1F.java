package outbreak;

import projeto.atores.MonsterFactory;
import projeto.base.Window;
import projeto.cenario.Scenario;
import projeto.enums.Monster;
import projeto.interfaces.ScenarioInterface;
import projeto.som.SoundController;

@SuppressWarnings("serial")
public class Hospital1F extends Scenario implements ScenarioInterface {

	public Hospital1F(double x, double y, Window previousScreen) {

		audio = "AMomentOfRelief.mid";
		scn = "hospital1F.scn";
		start(x, y, previousScreen, scn, null, TEMPO_LIMPO);

	}

	@Override
	public void createEnemie() {
		int quantidade = 45;
		npcs = MonsterFactory.getMonstro(Monster.ZUMBI, quantidade);
		for (int i = 0; i < quantidade; i++) {
			scene.addOverlay(npcs[i]);
			//npcs[i].powerAttack = 0;
		}
	}

	@Override
	public void events() {
		goStreets();
		goStreets2();
	}

	/**
	 * Leva a frente do hospital
	 */
	private void goStreets() {
		if (tileCollision(20) == true) {
			SoundController.stopMusic();
			SoundController.ironDoor();
			new OakHillStreets(9_900, 400, window);
		}
	}

	/**
	 * Leva para os fundos do hospital
	 */
	private void goStreets2() {
		if(tileCollision(25) == true){
			SoundController.stopMusic();
		}
	}

}
