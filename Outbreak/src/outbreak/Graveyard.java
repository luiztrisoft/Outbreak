package outbreak;

import projeto.armaseitens.ItemControl;
import projeto.atores.MonsterFactory;
import projeto.base.Window;
import projeto.cenario.Scenario;
import projeto.configuracoes.DirConfig;
import projeto.configuracoes.EventConfig;
import projeto.enums.Monster;
import projeto.interfaces.ScenarioInterface;
import projeto.som.SoundController;

@SuppressWarnings("serial")
public class Graveyard extends Scenario implements ScenarioInterface {

	private ItemControl magnum;

	public Graveyard(double x, double y, Window previousScreen) {
		audio = "guardhouse.mid";
		scn = "Graveyard.scn";
		start(x, y, previousScreen, scn, audio, TEMPO_NEVOEIRO);
	}

	@Override
	public void createEnemie() {

		int qtd = 13;
		npcs = MonsterFactory.getMonstro(Monster.ZUMBI, qtd);

		for (int i = 0; i < qtd; i++) {
			scene.addOverlay(npcs[i]);
		}
	}

	@Override
	public void createItem() {
		magnum = createItem(EventConfig.magnum, DirConfig.MAGNUM, 250, 150, scene);
	}

	@Override
	public void addItem() {
		addItem(magnum, EventConfig.magnum, scene);
	}

	public void collectedItem() {
		collectedMagnum(magnum, player, control, window);
	}

	public void events() {
		runForest();
	}

	private void runForest() {
		if (tileCollision(507) == true) {
			SoundController.gate();
			new Forest(3400, 250, window);
		}
	}

}
