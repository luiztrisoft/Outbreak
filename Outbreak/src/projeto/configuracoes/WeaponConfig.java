package projeto.configuracoes;

import projeto.controle.URL;

/**
 * Aqui estão armazenados os atributos referentes a todas as armas que serão
 * encontradas no jogo
 * 
 * @author Luiz Alberto
 * 
 */
public class WeaponConfig {

	// ::::::::Pistola::::::::
	public static final String HANDGUN = "Handgun";
	public static final int HANDGUN_DAMAGE = 2;
	public static final int HANDGUN_CAPACITY = 10;
	public static final int HANDGUN_BULLETS = 0;
//	public static final int HANDGUN_AMMUNITION = 15;
	public static final String HANGUN_IMAGE = URL.sprite("handgun.png");
	public static final String HANDGUN_AUDIO = URL.audio("tiro handgun.wav");

	// ::::::::Escopeta::::::::
	public static final String SHOTGUN = "Shotgun";
	public static final int SHOTGUN_DAMAGE = 15;
	public static final int SHOTGUN_CAPACITY = 5;
	public static final int SHOTGUN_BULLETS = SHOTGUN_CAPACITY;
//	public static final int SHOTGUN_AMMUNITION = 0;
	public static final String SHOTGUN_IMAGE = URL.sprite("shotgun.png");
	public static final String SHOTGUN_AUDIO = URL.audio("tiro shotgun.wav");

	// ::::::::Revolver::::::::
	public static final String MAGNUM = "Magnum";
	public static final int MAGNUM_DAMAGE = 6;
	public static final int MAGNUM_CAPACITY = 6;
	public static final int MAGNUM_BULLETS = MAGNUM_CAPACITY;
//	public static final int MAGNUM_AMMUNITION = 0;
	public static final String MAGNUM_IMAGE = URL.sprite("magnum.png");
	public static final String MAGNUM_AUDIO = URL.audio("tiro magnum.wav");

	// ::::::::Metralhadora::::::::
	public static final String MACHINEGUN = "Machinegun";
	public static final int MACHINEGUN_DAMAGE = 1;
	public static final int MACHINEGUN_CAPACITY = 30;
	public static final int MACHINEGUN_BULLETS = MACHINEGUN_CAPACITY;
//	public static final int MACHINEGUN_AMMUNITION = 0;
	public static final String MACHINEGUN_IMAGE = URL.sprite("machinegun.png");
	public static final String MACHINEGUN_AUDIO = URL.audio("tiro machinegun.wav");

	// ::::::::lança granadas::::::::
	public static final String GRENADE_LAUNCHER = "Grenade Launcher";
	public static final int GRENADE_LAUNCHER_DAMAGE = 30;
	public static final int GRENADE_LAUNCHER_CAPACITY = 1;
	public static final int GRENADE_LAUNCHER_BULLETS = GRENADE_LAUNCHER_CAPACITY;
//	public static final int GRENADE_LAUNCHER_AMMUNITION = 0;
	public static final String GRENADE_LAUNCHER_IMAGE = URL.sprite("grenadelauncher.png");
	public static final String GRENADE_LAUNCHER_AUDIO = URL.audio("tiro grenade.wav");

	// ::::::::Lança foguetes::::::::
	public static final String ROCKET_LAUNCHER = "Rocket Launcher";
	public static final int ROCKET_LAUNCHER_DAMAGE = 80;
	public static final int ROCKET_LAUNCHER_CAPACITY = 2;
	public static final int ROCKET_LAUNCHER_BULLETS = ROCKET_LAUNCHER_CAPACITY;
	public static final int ROCKET_LAUNCHER_AMMUNITION = 0;
	public static final String ROCKET_LAUNCHER_IMAGE = URL.sprite("rocketlauncher.png");
	public static final String ROCKET_LAUNCHER_AUDIO = URL.audio("tiro rocket.wav");

}
