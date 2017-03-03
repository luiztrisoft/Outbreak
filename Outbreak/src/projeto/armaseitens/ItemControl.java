package projeto.armaseitens;

import java.util.ArrayList;
import java.util.List;

import projeto.atores.Actor;
import projeto.atores.Player;
import projeto.base.Scene;
import projeto.configuracoes.AmmunitionConfig;
import projeto.configuracoes.EventConfig;
import projeto.configuracoes.WeaponConfig;

/**
 * Esta classe é responsável pelo controle de itens como armas, munições e
 * medicamentos.
 * 
 * @author Luiz Alberto
 * 
 */
public class ItemControl extends Actor {

	// ::::::::lista de itens coletados::::::::
	public static List<Item> itemList = new ArrayList<>();

	/**
	 * Contrutor padrão da classe.
	 * 
	 * @param fileName
	 * @param x
	 * @param y
	 * @param scene
	 */
	public ItemControl(String fileName, int x, int y, Scene scene) {
		super(fileName);
		this.x = x;
		this.y = y;
		scene.addOverlay(this);
		this.ajustItem(scene);
	}

	/**
	 * Método que ajusta o objeto ao cenário para que ele não seja arrastado
	 * conforme a movimentação do player.
	 * 
	 * @param scene
	 */
	public void ajustItem(Scene scene) {
		this.x += scene.getXOffset();
		this.y += scene.getYOffset();
	}

	/**
	 * Este método deve ser chamado para verificar quando o player pegar um
	 * item.
	 * 
	 * @param player
	 * @return boolean
	 */
	public boolean isCaught(Player player) {
		if (player.collided(this)) {
			return true;
		}
		return false;
	}

	/**
	 * Remove o ítem do cenário modificando sua coordenada.
	 */
	public void removeItem() {
		this.x = -2000;
		this.y = -2000;
	}

	/**
	 * Método utilizado quando o player pega um medicamento no cenário.
	 * 
	 */
	public static void addMedKit() {
		EventConfig.firstAidKit += 1;
	}

	/*
	 * ================================================================
	 * 
	 * ADICIONA ARMAS
	 * 
	 * Métodos que adicionam as armas a weaponList caso ainda não as possua. Os
	 * atributos das armas são:
	 * 
	 * name, damage, bulletsQuantity, capacityAmmunition, ammunitionReserves,
	 * image e sound
	 * 
	 * ================================================================
	 */

	/**
	 * Adiciona a handgun.
	 */
	public static void addHandgun() {
		if (EventConfig.handgun == false) {
			new Weapon(WeaponConfig.HANDGUN, WeaponConfig.HANDGUN_DAMAGE,
					WeaponConfig.HANDGUN_BULLETS,
					WeaponConfig.HANDGUN_CAPACITY,
					AmmunitionConfig.HANDGUN_AMMUNITION,
					WeaponConfig.HANGUN_IMAGE, WeaponConfig.HANDGUN_AUDIO,
					"SIGPRO");
			EventConfig.handgun = true;
		}
	}

	/**
	 * Adiciona a shotgun.
	 */
	public static void addShotgun() {
		if (EventConfig.shotgun == false) {
			new Weapon(WeaponConfig.SHOTGUN, WeaponConfig.SHOTGUN_DAMAGE,
					WeaponConfig.SHOTGUN_BULLETS,
					WeaponConfig.SHOTGUN_CAPACITY,
					AmmunitionConfig.SHOTGUN_AMMUNITION,
					WeaponConfig.SHOTGUN_IMAGE, WeaponConfig.SHOTGUN_AUDIO,
					"M37 Bennelli");
			EventConfig.shotgun = true;
		}
	}

	/**
	 * Adiciona a machine gun.
	 */
	public static void addMachinegun() {
		if (EventConfig.machineGun == false) {
			new Weapon(WeaponConfig.MACHINEGUN, WeaponConfig.MACHINEGUN_DAMAGE,
					WeaponConfig.MACHINEGUN_BULLETS,
					WeaponConfig.MACHINEGUN_CAPACITY,
					AmmunitionConfig.MACHINEGUN_AMMUNITION,
					WeaponConfig.MACHINEGUN_IMAGE,
					WeaponConfig.MACHINEGUN_AUDIO, "Assault Rifle");
			EventConfig.machineGun = true;
		}
	}

	/**
	 * Adiciona a magunm.
	 */
	public static void addMagnum() {
		if (EventConfig.magnum == false) {
			new Weapon(WeaponConfig.MAGNUM, WeaponConfig.MAGNUM_DAMAGE,
					WeaponConfig.MAGNUM_BULLETS, WeaponConfig.MAGNUM_CAPACITY,
					AmmunitionConfig.MAGNUM_AMMUNITION,
					WeaponConfig.MAGNUM_IMAGE, WeaponConfig.MAGNUM_AUDIO,
					"Colt");
			EventConfig.magnum = true;
		}
	}

	/**
	 * Adiciona a grenade launcher.
	 */
	public static void addGrenadeLauncher() {
		if (EventConfig.grenadeLauncher == false) {
			new Weapon(WeaponConfig.GRENADE_LAUNCHER,
					WeaponConfig.GRENADE_LAUNCHER_DAMAGE,
					WeaponConfig.GRENADE_LAUNCHER_BULLETS,
					WeaponConfig.GRENADE_LAUNCHER_CAPACITY,
					AmmunitionConfig.GRENADES,
					WeaponConfig.GRENADE_LAUNCHER_IMAGE,
					WeaponConfig.GRENADE_LAUNCHER_AUDIO, "Explosive Weapon");
			EventConfig.grenadeLauncher = true;
		}
	}

	/**
	 * Adiciona a rocket launcher.
	 */
	public static void addRocketLauncher() {
		if (EventConfig.rocketLauncher == false) {
			new Weapon(WeaponConfig.ROCKET_LAUNCHER,
					WeaponConfig.ROCKET_LAUNCHER_DAMAGE,
					WeaponConfig.ROCKET_LAUNCHER_BULLETS,
					WeaponConfig.ROCKET_LAUNCHER_CAPACITY,
					AmmunitionConfig.ROCKETS,
					WeaponConfig.ROCKET_LAUNCHER_IMAGE,
					WeaponConfig.ROCKET_LAUNCHER_AUDIO, "Rocket Launcher");
			EventConfig.rocketLauncher = true;
		}
	}

	/*
	 * ===================================================
	 * 
	 * ADICIONA A MUNIÇÃO
	 * 
	 * Os dois métodos a seguir adicionam a munição pertencente a arma do
	 * personagem.
	 * 
	 * ===================================================
	 */
	/**
	 * Método que incrementa munição caso possua a arma na WeaponList.
	 * 
	 * @param nomeArma
	 * @param quantidadeMunicao
	 * @param item
	 */
	public static void verifyWeapon(String nameWeapon, int AmmunitionQuantity) {
		if (nameWeapon.equals(WeaponConfig.HANDGUN)
				&& EventConfig.handgun == true) {
			addAmmo(nameWeapon, AmmunitionQuantity);
		} else if (nameWeapon.equals(WeaponConfig.SHOTGUN)
				&& EventConfig.shotgun == true) {
			addAmmo(nameWeapon, AmmunitionQuantity);
		} else if (nameWeapon.equals(WeaponConfig.MACHINEGUN)
				&& EventConfig.machineGun == true) {
			addAmmo(nameWeapon, AmmunitionQuantity);
		} else if (nameWeapon.equals(WeaponConfig.MAGNUM)
				&& EventConfig.magnum == true) {
			addAmmo(nameWeapon, AmmunitionQuantity);
		} else if (nameWeapon.equals(WeaponConfig.GRENADE_LAUNCHER)
				&& EventConfig.grenadeLauncher == true) {
			addAmmo(nameWeapon, AmmunitionQuantity);
		} else if (nameWeapon.equals(WeaponConfig.ROCKET_LAUNCHER)
				&& EventConfig.rocketLauncher == true) {
			addAmmo(nameWeapon, AmmunitionQuantity);
		}
	}

	/**
	 * Adiciona a munição a arma existente na weaponList.
	 * 
	 * @param nameWeapon
	 * @param AmmunitionQuantity
	 */
	private static void addAmmo(String nameWeapon, int AmmunitionQuantity) {
		for (int i = 0; i < WeaponControl.weaponList.size(); i++) {
			if (WeaponControl.weaponList.get(i).getName()
					.equalsIgnoreCase(nameWeapon)) {
				WeaponControl.weaponList.get(i).addAmmunition(
						AmmunitionQuantity);
			}
		}
	}
}
