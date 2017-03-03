package outbreak;

import projeto.atores.MonsterFactory;
import projeto.base.Window;
import projeto.cenario.Scenario;
import projeto.enums.Monster;
import projeto.interfaces.ScenarioInterface;
import projeto.som.SoundController;

@SuppressWarnings("serial")
public class Sewer extends Scenario implements ScenarioInterface {
	public Sewer(double x, double y, Window previousScreen) {
		start(x, y, previousScreen, "Sewer.scn", "wreckage2.mid", TEMPO_LIMPO);
	}

	@Override
	public void createEnemie() {

		int quantidade = 1;
		npcs = MonsterFactory.getMonstro(Monster.ZUMBI, quantidade);

		for (int i = 0; i < quantidade; i++) {
			npcs[i].HP = 50;
			npcs[i].powerAttack = 1;
			npcs[i].x = 700;
			npcs[i].y = 500;
			scene.addOverlay(npcs[i]);
		}
	}

	@Override
	public void events() {
		runCity();
	}

	private void runCity() {
		if (tileCollision(005) == true) {
			SoundController.stopMusic();
			new OakHillStreets(300, 1400, window);
		}
	}

}
