package outbreak;

import java.util.Random;

import projeto.atores.MonsterFactory;
import projeto.atores.Objects;
import projeto.base.Window;
import projeto.cenario.Scenario;
import projeto.configuracoes.DirConfig;
import projeto.enums.Monster;
import projeto.interfaces.ScenarioInterface;

@SuppressWarnings("serial")
public class AcessoCidadeCenarioFinal extends Scenario implements ScenarioInterface {

	private Objects truck;

	public AcessoCidadeCenarioFinal(int x, int y, Window window) {
		String scn = "acessoCidade.scn";
		String audio = null;
		start(x, y, window, scn, audio, TEMPO_LIMPO);
	}

	@Override
	public void createEnemie() {
		int quantidadeZumbi = 20;
		npcs = MonsterFactory.getMonstro(Monster.ZUMBI, quantidadeZumbi);
		for (int i = 0; i < quantidadeZumbi; i++) {
			npcs[i].perceptionRay = 1150;
			scene.addOverlay(npcs[i]);
			npcs[i].pursuitVelocity = 0;
			
			if (i % 2 == 0) {
				npcs[i].x = 2500 + new Random().nextInt(100);
				npcs[i].y = 550 + new Random().nextInt(130);
			}else{
				npcs[i].x = 00 + new Random().nextInt(500);
				npcs[i].y = 550 + new Random().nextInt(130);
			}
			//npcs[i].powerAttack = 00;
		}
	}

	@Override
	public void createObjects() {
		truck = createObjects(DirConfig.TRUCK, 1800, 570, false, 1, scene);
	}

	@Override
	public void addObjects() {
		truck.addObject(player, npcs, scene, window, true);
	}

	@Override
	public void events() {
		// TODO Auto-generated method stub
		
	}

}
