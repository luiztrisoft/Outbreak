package outbreak;

import java.awt.Color;
import java.awt.Font;
import java.util.Random;

import projeto.atores.MonsterFactory;
import projeto.base.Window;
import projeto.cenario.Scenario;
import projeto.enums.Monster;
import projeto.interfaces.ScenarioInterface;
import projeto.som.SoundController;

@SuppressWarnings("serial")
public class Forest extends Scenario implements ScenarioInterface {

	public Forest(double x, double y, Window previousScreen) {
		super.start(x, y, previousScreen, "Forest.scn", "NightBegins.mid", TEMPO_NEVOEIRO);
	}

	private Font fonte = new Font("Consolas", 1, 30);

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

		int raioX = 7000, raioY = 2000;
		int qtd = 3;
		npcs = MonsterFactory.getMonstro(Monster.CACHORRO, qtd);

		for (int i = 0; i < qtd; i++) {
			npcs[i].x = player.x - (raioX / 2) + new Random().nextInt(raioX);
			npcs[i].y = player.y - new Random().nextInt(raioY);
			scene.addOverlay(npcs[i]);
		}
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

	@Override
	public void events() {
		information();
		runGraveyard();
		runCabin();
		runSewer();
	}

	private void runGraveyard() {
		if (tileCollision(507) == true) {
			SoundController.gate();
			new Graveyard(700, 480, window);
		}
	}

	private void runCabin() {
		if (tileCollision(503) == true) {
			SoundController.door();
			new Cabana(100, 360, window);
		}

	}

	private void runSewer() {
		if (tileCollision(512) == true) {
			new Sewer(0, 350, window);
		}
	}

	private void information() {
		if (tileCollision(513) == true) {
			window.drawText("<= Cemitério ", 300, 530, Color.WHITE, fonte);
			window.drawText("=> Poço", 300, 560, Color.WHITE, fonte);
		}
	}

}
