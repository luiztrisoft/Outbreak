package outbreak;

import projeto.armaseitens.ItemControl;
import projeto.armaseitens.WeaponControl;
import projeto.atores.MonsterFactory;
import projeto.base.Window;
import projeto.cenario.Scenario;
import projeto.configuracoes.AmmunitionConfig;
import projeto.configuracoes.DirConfig;
import projeto.configuracoes.GameConfig;
import projeto.configuracoes.TypeItemConfig;
import projeto.enums.Monster;
import projeto.interfaces.ScenarioInterface;
import projeto.som.SoundController;

@SuppressWarnings("serial")
public class Cabana extends Scenario implements ScenarioInterface {

	private ItemControl ammo;

	public Cabana(double x, double y, Window previousScreen) {

		audio = "AMomentOfRelief.mid";
		scn = "Cabana.scn";
		start(x, y, previousScreen, scn, audio, TEMPO_LIMPO);

	}

	public void createItem() {
		ammo = createItem(false, DirConfig.INFINITE_AMMO, 235, 150, scene);
	}

	@Override
	public void createObjects() {
		// TODO Auto-generated method stub
	}

	@Override
	public void isRainy(boolean isRainy) {
		// TODO Auto-generated method stub

	}

	@Override
	public void drawFrame() {
		gameControl(control, window, player, loop);
		getOffSetPlayer(scene, player);

	}

	@Override
	public void characterControl() {
		player.playerControl(keyboard, window, scene);
		player.enemiePresence(npcs, window, scene);
		control.npcControl(npcs, player, window, scene);
	}

	@Override
	public void createEnemie() {
		int quantidadeZumbi = 0;
		npcs = MonsterFactory.getMonstro(Monster.ZUMBI, quantidadeZumbi);
		for (int i = 0; i < quantidadeZumbi; i++) {
			npcs[i].perceptionRay = 150;
			scene.addOverlay(npcs[i]);
		}
	}

	@Override
	public void addObjects() {
		ammo.pathControl(window, scene);
	}

	@Override
	public void objectAnimation() {
		// TODO Auto-generated method stub
	}

	@Override
	public void addItem() {
		addItem(ammo, false, scene);
	}

	@Override
	public void collectedItem() {
		// TODO Auto-generated method stub
	}

	@Override
	public void events() {
		colidirPorta();
		municaoHandgun();
	}

	/**
	 * Este método é acionado quando o personagem colide com a porta sendo
	 * levado ao ambiente externo da cabana.
	 */
	private void colidirPorta() {
		if (tileCollision(20) == true) {
			SoundController.stopMusic();
			SoundController.door();
			new Forest(6850, 1800, window);
		}

	}

	/**
	 * Método executado ao coletar a munição infinita da cabana.
	 */
	@SuppressWarnings("unused")
	@Deprecated
	private void municaoHandgunInfinity() {
		int quantidade = 50;
		if (player.collided(ammo) == true) {
			if (WeaponControl.weaponList.get(0).getAmmunitionReserves() < (quantidade * GameConfig.level)
					|| WeaponControl.weaponList.get(0).getBulletsQuantity() < 15) {
				WeaponControl.weaponList.get(0).setAmmunitionReserves((int) (quantidade * GameConfig.level));
				WeaponControl.weaponList.get(0).setBulletsQuantity(15);
				control.obtainedItem(AmmunitionConfig.HANDGUN_AMMUNITION_NAME, TypeItemConfig.AMMO, window);
				ammo.removeItem();
			}
		}
	}

	private void municaoHandgun() {

		int municao = WeaponControl.weaponList.get(0).getAmmunitionReserves();

		if (player.collided(ammo) == true) {
			municao += 20;
			WeaponControl.weaponList.get(0).setAmmunitionReserves(municao);
			ammo.removeItem();
		}
	}

}
