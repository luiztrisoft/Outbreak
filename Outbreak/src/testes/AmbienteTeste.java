package testes;

import projeto.atores.MonsterFactory;
import projeto.base.Window;
import projeto.cenario.Scenario;
import projeto.enums.Monster;
import projeto.interfaces.ScenarioInterface;

@SuppressWarnings("serial")
public class AmbienteTeste extends Scenario implements ScenarioInterface {

	public AmbienteTeste(double x, double y, Window previousScreen) {

		audio = "AMomentOfRelief.mid";
		scn = "segundoCenario.scn";
		start(x, y, previousScreen, scn, null, TEMPO_LIMPO);
	}

	@Override
	public void createEnemie() {
		int quantidade = 10;
		npcs = MonsterFactory.getMonstro(Monster.ZUMBI, quantidade);
		for (int i = 0; i < quantidade; i++) {
			scene.addOverlay(npcs[i]);
			npcs[i].powerAttack = 0;
		}
	}

}
