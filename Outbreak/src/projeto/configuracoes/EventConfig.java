package projeto.configuracoes;

import projeto.armaseitens.WeaponControl;
import projeto.controle.RainControl;

/**
 * Esta classe serve como um controle de eventos definindo o que já aconteceu,
 * quais itens ou armas já foram pegos etc.
 * 
 * É de vital importância para controlar os eventos do jogo.
 * 
 * @author Luiz Alberto
 * 
 */
public abstract class EventConfig {

	/*
	 * ============================================
	 * 
	 * ITENS
	 * 
	 * Aqui se definem os pertences do personagem que informam se ele possui
	 * determinada arma e a quantidade de medicamentos que ele possui
	 * disponível.
	 * 
	 * ============================================
	 */

	public static int firstAidKit = FirstAidKitQuantity();
	public static boolean handgun;
	public static boolean shotgun;
	public static boolean magnum;
	public static boolean machineGun;
	public static boolean grenadeLauncher;
	public static boolean rocketLauncher;

	public static boolean firstAidKit1;
	public static boolean firstAidKit2;
	public static boolean firstAidKit3;
	public static boolean firstAidKit4;

	public static boolean handgunAmmo1;
	public static boolean handgunAmmo2;
	public static boolean handgunAmmo3;
	public static boolean handgunAmmo4;

	/*
	 * ============================================
	 * 
	 * EVENTOS
	 * 
	 * Aqui se definem os inimigos que o personagem encontrou ou derrotou, cenas
	 * que ocorreram, itens que pegou (que não sejam armas, munições ou
	 * medicamentos), etc.
	 * 
	 * ============================================
	 */

	// ::::::::chaves::::::::
	public static boolean churchKey;
	public static boolean precintKey;

	// ::::::::cenas::::::::
	public static boolean firstZombie;
	
	public static boolean zumbiConvenienciaEstaMorto;
	

	/**
	 * Este método faz com que todos os itens e eventos do jogo recebam valor
	 * null. Normalmente é usado quando o jogador morre e é necessário então
	 * resetar todos os valores a sua condição inicial.
	 */
	public static void restartGame() {

		RainControl.isRainy = false;

		firstAidKit = FirstAidKitQuantity();

		handgun = true;
		shotgun = false;
		magnum = false;
		machineGun = false;
		grenadeLauncher = false;
		rocketLauncher = false;

		firstAidKit1 = false;
		firstAidKit2 = false;
		firstAidKit3 = false;
		firstAidKit4 = false;

		handgunAmmo1 = false;
		handgunAmmo2 = false;
		handgunAmmo3 = false;
		handgunAmmo4 = false;

		churchKey = false;
		precintKey = false;

		firstZombie = false;
		
		zumbiConvenienciaEstaMorto = false;

		handgunDefault();
	}

	/**
	 * Retorna a condição inicial da handgun com 15 balas no pente e 15 de
	 * reserva. Geralmente é utilizado quando o personagem morre.
	 */
	private static void handgunDefault() {
		WeaponControl.weaponList.get(0).setBulletsQuantity(
				WeaponConfig.HANDGUN_BULLETS);
		WeaponControl.weaponList.get(0).setAmmunitionReserves(
				AmmunitionConfig.HANDGUN_AMMUNITION);
	}

	/**
	 * Este método retorna a quantidade de medicamentos de acordo com o nível do
	 * jogo.
	 * 
	 * @return int
	 */
	public static int FirstAidKitQuantity() {
		if (GameConfig.level == 5) {
			return 1;
		} else if (GameConfig.level == 4) {
			return 2;
		} else if (GameConfig.level == 3) {
			return 3;
		} else if (GameConfig.level == 2) {
			return 4;
		} else {
			return 5;
		}
	}
}
