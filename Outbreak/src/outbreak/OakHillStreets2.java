package outbreak;

import java.util.Random;

import projeto.armaseitens.ItemControl;
import projeto.atores.MonsterFactory;
import projeto.atores.Objects;
import projeto.base.Window;
import projeto.cenario.Scenario;
import projeto.configuracoes.DirConfig;
import projeto.configuracoes.EventConfig;
import projeto.enums.Monster;
import projeto.som.SoundController;

@SuppressWarnings("serial")
public class OakHillStreets2 extends Scenario {// implements ScenarioInterface {

	private int quantidadeMonstros = 3;
	private Objects fogo;
	private ItemControl shotgun;

	public OakHillStreets2(double x, double y, Window previousScreen) {

		audio = "TheBeginningOfTheNightmare.mid";
		scn = "OakHillStreets2.scn";

		start(x, y, previousScreen, scn, null, TEMPO_NEVOEIRO);
	}

	@Override
	public void events() {

		goBridge();
		goHospital();

	}

	public void goBridge() {
		
		if(tileCollision(007)){
			SoundController.stopMusic();
			SoundController.ironDoor();
			new Hospital1F(540, 620, window);
		}
	}

	public void goHospital() {

	}

	@Override
	public void createEnemie() {

		npcs = MonsterFactory.getMonstro(Monster.ZUMBI, quantidadeMonstros);

		for (int i = 0; i < npcs.length; i++) {

//			npcs[i].powerAttack = 0;
			npcs[i].perceptionRay = 200;
			npcs[i].pursuitVelocity = 0.1;
			npcs[i].x = 40 + new Random().nextInt(60);
			npcs[i].y = 100 + new Random().nextInt(200);
			scene.addOverlay(npcs[i]);
		}

	}

	@Override
	public void createObjects() {
		fogo = createObjects(DirConfig.FIRE, 400, 430, false, 10, scene);
	}

	@Override
	public void addObjects() {
		fogo.addObject(player, npcs, scene, window, true);
	}

	@Override
	public void objectAnimation() {
		objectAnimation(fogo);
	}

	@Override
	public void createItem() {
		shotgun = createItem(EventConfig.shotgun, DirConfig.SHOTGUN, 500, 250, scene);
	}

	@Override
	public void addItem() {
		addItem(shotgun, EventConfig.shotgun, scene);
	}

	public void collectedItem() {
		collectedShotgun(shotgun, player, control, window);
	}

}
