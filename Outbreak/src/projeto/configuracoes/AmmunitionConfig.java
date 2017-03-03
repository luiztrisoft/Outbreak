package projeto.configuracoes;

import projeto.armaseitens.WeaponControl;

/**
 * Esta classe guarda informações sobre as munições do jogo.
 * 
 * @author Luiz Alberto
 * 
 */
public class AmmunitionConfig {

	public static final String HANDGUN_AMMUNITION_NAME = "Balas de handgun";
	public static final int HANDGUN_AMMUNITION = 37;

	public static final String SHOTGUN_AMMUNITION_NAME = "Balas de Shotgun";
	public static final int SHOTGUN_AMMUNITION = 5;

	public static final String MAGNUM_AMMUNITION_NAME = "Balas de Magnum";
	public static final int MAGNUM_AMMUNITION = 0;

	public static final String MACHINEGUN_AMMUNITION_NAME = "Balas de Machinegun";
	public static final int MACHINEGUN_AMMUNITION = 0;

	public static final String GRENADE_NAME = "Granadas";
	public static final int GRENADES = 0;

	public static final String ROCKET_NAME = "Foguetes";
	public static final int ROCKETS = 0;

	public static void balasPorNivelDeDificuldade() {
		if (GameConfig.level == 5) {
			int municao = 50;
			WeaponControl.weaponList.get(0).setAmmunitionReserves(municao);

		} else if (GameConfig.level == 4) {
			int municao = 40;
			WeaponControl.weaponList.get(0).setAmmunitionReserves(municao);

		} else if (GameConfig.level == 3) {
			int municao = 30;
			WeaponControl.weaponList.get(0).setAmmunitionReserves(municao);

		} else if (GameConfig.level == 2) {
			int municao = 20;
			WeaponControl.weaponList.get(0).setAmmunitionReserves(municao);

		} else {
			int municao = 10;
			WeaponControl.weaponList.get(0).setAmmunitionReserves(municao);

		}
	}

}
