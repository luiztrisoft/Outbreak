package outbreak;

import java.awt.Color;
import java.util.Random;

import projeto.armaseitens.ItemControl;
import projeto.atores.MonsterFactory;
import projeto.atores.Objects;
import projeto.base.Keyboard;
import projeto.base.Sound;
import projeto.base.Window;
import projeto.cenario.Scenario;
import projeto.configuracoes.DirConfig;
import projeto.configuracoes.EventConfig;
import projeto.controle.URL;
import projeto.enums.Monster;
import projeto.interfaces.ScenarioInterface;
import projeto.telas.GameOver;
import projeto.telas.Sobreviveu;

@SuppressWarnings("serial")
public class ResidentEvil3 extends Scenario implements ScenarioInterface {

	private Random random = new Random();
	private boolean single = false;
	private int quantidadeMonstros = 30;
	private Objects fogo;
	private ItemControl shotgun;

	public ResidentEvil3(double x, double y, Window previousScreen) {
		String audio = "TheBeginningOfTheNightmare.mid";
		start(x, y, previousScreen, "ResidentEvil3BatalhaFinal.scn", audio, TEMPO_NEVOEIRO);
	}

	public void events() {

		if (npcs[0].isAlive) {
			nemesis();
		}

		time();
		control.setMessage("DERROTE NEMESIS ANTES DO TEMPO ACABAR!");

	}

	private void time() {

		if (single == false) {
			time.setColor(Color.WHITE);
			time.setTime(0, 1, 25);

			single = true;
		}

		time.draw();

		if (time.getMinute() == 0 && time.getSecond() == 59) {
			time.setColor(Color.RED);
		}

		if (time.timeEnded() && !npcs[0].isAlive) {
			sobreviveu();
		}

		if (time.timeEnded() && npcs[0].isAlive) {
			gameOver();
		}
	}

	private void nemesis() {

		incrementaVelocidade(0.01);
		gritos();

		if (npcs[0].collided(player)) {
			incrementaVelocidade(0.03);
			esquiva();
			velocidade();
		}

		if (tileCollision(005) == true) {
			defineVelocidade(0.5);
		}

	}

	private void velocidade() {
		if (npcs[0].pursuitVelocity < 0.5) {
			defineVelocidade(0.5);
			;
		}

		if (npcs[0].pursuitVelocity >= 5.7) {
			defineVelocidade(1.5);
			// stars();
		}
	}

	private void incrementaVelocidade(double valor) {
		npcs[0].pursuitVelocity += valor;
	}

	private void defineVelocidade(double valor) {
		npcs[0].pursuitVelocity = valor;
	}

	private void esquiva() {
		if (keyboard.keyDown(Keyboard.ESCAPE_KEY)) {
			ughh();
			npcs[0].pursuitVelocity = 0;
			// player.x += 10;
		}
	}

	private void gritos() {
		if (random.nextInt(10000) <= 5) {
			stars();
		}

		if (random.nextInt(10000) >= 20 && random.nextInt(10000) <= 25) {
			exclamation();
		}
	}

	private void stars() {
		new Sound(URL.audio("stars.wav")).play();
	}

	private void exclamation() {
		new Sound(URL.audio("exclamation.wav")).play();
	}

	private void ughh() {
		new Sound(URL.audio("ughh.wav")).play();
	}

	@Override
	public void createEnemie() {

		npcs = MonsterFactory.getMonstro(Monster.ZUMBI, quantidadeMonstros);

		npcs[0] = new MonsterFactory(6);
		npcs[0].HP = 200;
		npcs[0].powerAttack = 0.7;
		npcs[0].perceptionRay = 10000;
		npcs[0].pursuitVelocity = 1;
		scene.addOverlay(npcs[0]);
		npcs[0].x = 450;
		npcs[0].y = 500;

		for (int i = 1; i < quantidadeMonstros; i++) {
			npcs[i].x = 200 + new Random().nextInt(600);
			npcs[i].y = 100 + new Random().nextInt(1000);
			scene.addOverlay(npcs[i]);
		}

	}

	private void sobreviveu() {
		Sobreviveu.mainScreen(window);
	}

	@SuppressWarnings("static-access")
	private void gameOver() {
		new GameOver().gameOver(window);
	}

	@Override
	public void createObjects() {
		fogo = createObjects(DirConfig.FIRE, 640, 390, false, 10, scene);
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
