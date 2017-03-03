package exemplos;

import projeto.atores.MonsterFactory;
import projeto.atores.Objects;
import projeto.base.Window;
import projeto.cenario.Scenario;
import projeto.configuracoes.DirConfig;
import projeto.enums.Monster;
import projeto.som.SoundController;

@SuppressWarnings("serial")
public class SegundoCenario extends Scenario {

	private Objects porta;

	public SegundoCenario(double x, double y, Window previousScreen) {

		start(x, y, previousScreen, "CenarioModelo.scn", null, TEMPO_NEVOEIRO);

	}

	@Override
	public void createObjects() {
		porta = createObjects(DirConfig.DOOR, 500, 700, false, 1, scene);
	}

	@Override
	public void characterControl() {
		player.playerControl(keyboard, window, scene);
	}

	@Override
	public void createEnemie() {
		int quantidade = 1;
		npcs = MonsterFactory.getMonstro(Monster.ZUMBI, quantidade);
		for (int i = 0; i < quantidade; i++) {
			scene.addOverlay(npcs[i]);
		}
	}

	@Override
	public void addObjects() {
		porta.addObject(player, npcs, scene, window, false);
	}

	@Override
	public void events() {
		voltar();
	}

	private void voltar() {
		if (player.collided(porta) == true) {
			try {
				SoundController.door();
				new CenarioModelo(300, 170, window);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
