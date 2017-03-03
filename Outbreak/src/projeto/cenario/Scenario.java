package projeto.cenario;

import java.awt.Point;
import java.util.Random;
import java.util.Vector;

import javax.swing.JFrame;

import projeto.armaseitens.ItemControl;
import projeto.atores.MonsterFactory;
import projeto.atores.NPC;
import projeto.atores.Objects;
import projeto.atores.Player;
import projeto.base.GameObject;
import projeto.base.Keyboard;
import projeto.base.Scene;
import projeto.base.TileInfo;
import projeto.base.Time;
import projeto.base.Window;
import projeto.configuracoes.AmmunitionConfig;
import projeto.configuracoes.EventConfig;
import projeto.configuracoes.MedkitConfig;
import projeto.configuracoes.TypeItemConfig;
import projeto.configuracoes.WeaponConfig;
import projeto.controle.RainControl;
import projeto.controle.ScreenControl;
import projeto.controle.URL;
import projeto.enums.Monster;
import projeto.interfaces.ScenarioInterface;
import projeto.som.SoundController;
import projeto.telas.Map;

/**
 * Super classe de todas as classes que compõem os cenários e eventos do jogo.
 * 
 * @author Luiz Alberto
 * 
 */
@SuppressWarnings("serial")
public abstract class Scenario extends JFrame implements ScenarioInterface {

	protected ScreenControl control;
	protected Window window;
	protected Keyboard keyboard;
	protected Scene scene;
	protected Objects rain;
	protected Player player;
	protected boolean loop = true;
	protected MonsterFactory npcs[];
	protected Random random = new Random();
	protected Map map = new Map();
	protected boolean rainy;
	protected String audio = "#escapetogether.mid";
	protected String scn = "#OakHillStreets.scn";
	protected boolean estaChovendo = false;
	protected static Time time = new Time(110, 90, false);

	/**
	 * Controla todas as funções da tela como a mensagem de pause, os gadgets de
	 * munição e infecção e a atualização do frame.
	 * 
	 * *Observação: Este método deve ser utilizado quando <b>NÃO</b> houver NPCs
	 * no cenário.
	 * 
	 * @param control
	 * @param window
	 * @param player
	 * @param loop
	 */
	protected void gameControl(ScreenControl control, Window window, Player player, boolean loop) {
		control.gadgetControl(window, player);
		control.inventory(window, loop, player);
		control.screenControl(window);
	}

	/**
	 * Controla todas as funções da tela como a mensagem de pause, os gadgets de
	 * munição e infecção e a atualização do frame.
	 * 
	 * @param control
	 * @param window
	 * @param player
	 * @param loop
	 */
	protected void gameControl(ScreenControl control, Window window, Player player, boolean loop, NPC[] npc) {
		control.gadgetControl(window, player, npc);
		control.inventory(window, loop, player);
		control.screenControl(window);
	}

	/**
	 * Controle de tela para classes que não estendam desta mesma classe como as
	 * telas de apresentação.
	 * 
	 * @param control
	 * @param window
	 */
	public static void screenControl(ScreenControl control, Window window) {
		control.screenControl(window);
	}

	/**
	 * Apresenta o mapa da cidade caso aperte a tecla M.
	 */
	public void mapControl() {
		if (keyboard.keyDown(Keyboard.M_KEY)) {
			if (Map.map == false) {
				Map.map = true;
				map.control(window);
			}
		}
	}

	/*
	 * =================================================================
	 * 
	 * GET OFF SET
	 * 
	 * Estes métodos servem para ajustar os objetos das subclasses Actor para
	 * que não sejam arrastados pelo decorrer da tela.
	 * 
	 * =================================================================
	 */
	/**
	 * Ajusta o player a tela.
	 * 
	 * @param scene
	 * @param player
	 */
	protected void getOffSetPlayer(Scene scene, Player player) {
		scene.moveScene(player);
		player.x += scene.getXOffset();
		player.y += scene.getYOffset();
	}

	/**
	 * Ajusta os zumbis a tela. IMPORTANTE: Use apenas se houver inimigos na
	 * tela.
	 * 
	 * @param scene
	 * @param npc
	 */
	protected void getOffSetNPCZumbi(Scene scene, MonsterFactory[] npc) {

		try {
			if (npc != null) {
				for (int i = 0; i < npc.length; i++) {
					npc[i].x += scene.getXOffset();
					npc[i].y += scene.getYOffset();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * Método usado para criação de monstros. A classe deve ser reescrita na
	 * subclasse para que o inimigo seja instanciado. O método foi adicionado
	 * aqui para que a subclasse não tenha obrigatoriedade de instanciar caso o
	 * cenário não deva ter inimigos.
	 */
	@Override
	public void createEnemie() {

		int quantidade = 0;
		npcs = MonsterFactory.getMonstro(Monster.ZUMBI, quantidade);

		for (int i = 0; i < quantidade; i++) {
			npcs[i].x = 0;
			npcs[i].y = 0;
			scene.addOverlay(npcs[i]);
		}
	}

	/**
	 * Através deste método nós obtemos uma nova instancia da classe Objects.
	 * Assim podemos criar objetos como portas, veículos, fogo etc.
	 * 
	 * @param fileName
	 * @param x
	 * @param y
	 * @param isMobile
	 * @param numFrames
	 * @param scene
	 * @return Objects
	 */
	public Objects createObjects(String fileName, int x, int y, boolean isMobile, int numFrames, Scene scene) {
		Objects object = new Objects(fileName, x, y, isMobile, scene, numFrames);
		return object;
	}

	/**
	 * Através deste método nós obtemos uma nova instancia da classe
	 * ItemControl. Assim podemos criar itens como armas, munições,
	 * medicamentos, chaves etc.
	 * 
	 * @param item
	 * @param fileName
	 * @param x
	 * @param y
	 * @param scene
	 * @return ItemControl
	 */
	public ItemControl createItem(boolean item, String fileName, int x, int y, Scene scene) {
		ItemControl itemControl = null;
		if (item == false) {
			itemControl = new ItemControl(fileName, x, y, scene);
		}
		return itemControl;
	}

	// public NPC[] especialZombie(boolean isDead, int npcLength, int x, int y)
	// {
	//
	// NPC[] npc = new NPC[npcLength];
	// npc[0] = new NPCZombie(1, x, y);
	//
	// return npc;
	//
	// }

	/**
	 * Este método realiza a animação de objetos através da troca de imagens
	 * como o fogo por exemplo.
	 * 
	 * @param object
	 */
	protected void objectAnimation(Objects object) {
		object.setTotalDuration(20);
		object.update();
		object.draw();
	}

	// @Override
	// public void drawRain(boolean b) {
	// rainyControl(b);
	// }

	@Override
	public void climaticControl(int tempo) {
		if (tempo == TEMPO_LIMPO) {

		} else if (tempo == TEMPO_CHUVOSO) {
			rainyControl(true);
			isRainy();
		} else if (tempo == TEMPO_NEVOEIRO) {
			isNevoa();
		}
	}

	/**
	 * Controle da chuva. Se o valor for igual a 'true' ele executa a chuva e
	 * seu som.
	 * 
	 */
	protected void rainyControl(boolean b) {
		RainControl.isRainy = b;
		if (RainControl.isRainy == true) {
			isRainy();
			RainControl.rain(rain, 5);
		} else {
			RainControl.stopRain(rain, scene);
			rain = null;
		}
	}

	/**
	 * Método usado para criar a chuva do jogo. Para que chova é necessário que
	 * se informe o valor <b>isRainy</b> como <b>true</b>.
	 * 
	 * @param isRainy
	 * @param rain
	 * @param scene
	 * @return
	 */
	// boolean isRainy, Objects rain, Scene scene;
	protected final int TEMPO_LIMPO = 0;
	protected final int TEMPO_CHUVOSO = 1;
	protected final int TEMPO_NEVOEIRO = 2;
	private int tempo = 0;

	protected Objects isRainy() {
		RainControl.isRainy = true;
		if (RainControl.isRainy == true) {
			if (rain == null) {
				rain = new Objects(URL.sprite("chuva.png"), 0, 0, false, scene, 11);
				SoundController.playMusic(URL.audio("rain.wav"));
			}
		}
		return rain;
	}

	protected Objects isNevoa() {
		// if (RainControl.isRainy == true) {
		if (rain == null) {
			rain = new Objects(URL.sprite("smoke.png"), 0, 0, false, scene, 1);
		}
		// }
		return rain;
	}

	/**
	 * Método usado para ajustar o item ao cenário para que ele não seja
	 * arrastado e também para removê-lo quando o jogador coletá-lo.
	 * 
	 * @param itemControl
	 * @param item
	 * @param scene
	 */
	protected void addItem(ItemControl itemControl, boolean item, Scene scene) {
		if (item == false) {
			itemControl.ajustItem(scene);
		} else {
			// itemControl.removeItem();
			scene.removeOverlay(itemControl);
		}
	}

	/**
	 * Este método serve para verificar a colisão com um tile específico que
	 * seja percorrível no cenário.
	 * 
	 * Exemplo: ao colidir com o tile de uma porta, muda de cenário.
	 * 
	 * @param object
	 * @param value
	 * @return boolean
	 */
	protected boolean tileCollision(int value) {
		Point min = new Point((int) player.x, (int) player.y);
		Point max = new Point((int) (player.x + player.width), (int) (player.y + player.height));
		Vector<?> tiles = scene.getTilesFromPosition(min, max);
		for (int i = 0; i < tiles.size(); i++) {
			TileInfo tile = (TileInfo) tiles.elementAt(i);
			if (tileCollision(player, tile, value) == true) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Método de verificação de tiles percorríveis.
	 * 
	 * Obs.: Este método é usado para verificação dos tiles percorríveis em
	 * cenários específicos.
	 * 
	 * @param object
	 * @param tile
	 * @param value
	 * @return boolean
	 */
	private boolean tileCollision(GameObject object, TileInfo tile, int value) {
		if ((tile.id == value) && object.collided(tile)) {
			return true;
		}
		return false;
	}

	/*
	 * ================================================
	 * 
	 * ARMAS COLETADAS
	 * 
	 * Estes métodos verificam se as armas declaradas foram coletadas ou não
	 * pelo jogador, por tanto devem ser invocados dentro do loop do jogo.
	 * 
	 * ================================================
	 */

	/**
	 * Método que verifica se a metralhadora foi coletada.
	 * 
	 * @param machinegun
	 * @param player
	 * @param control
	 * @param window
	 */
	protected void collectedMachinegun(ItemControl machinegun, Player player, ScreenControl control, Window window) {
		if (EventConfig.machineGun == false) {
			if (machinegun.isCaught(player)) {
				ItemControl.addMachinegun();
				control.obtainedItem(WeaponConfig.MACHINEGUN, TypeItemConfig.WEAPON, window);
			}
		}
	}

	/**
	 * Método que verifica se a escopeta foi coletada.
	 * 
	 * @param shotgun
	 * @param player
	 * @param control
	 * @param window
	 */
	protected void collectedShotgun(ItemControl shotgun, Player player, ScreenControl control, Window window) {
		if (EventConfig.shotgun == false) {
			if (shotgun.isCaught(player)) {
				ItemControl.addShotgun();
				control.obtainedItem(WeaponConfig.SHOTGUN, TypeItemConfig.WEAPON, window);
			}
		}
	}

	/**
	 * Método que verifica se a magnum foi coletada.
	 * 
	 * @param magnum
	 * @param player
	 * @param control
	 * @param window
	 */
	protected void collectedMagnum(ItemControl magnum, Player player, ScreenControl control, Window window) {
		if (EventConfig.magnum == false) {
			if (magnum.isCaught(player)) {
				ItemControl.addMagnum();
				control.obtainedItem(WeaponConfig.MAGNUM, TypeItemConfig.WEAPON, window);
			}
		}
	}

	/**
	 * Método que verifica se o lança granadas foi coletado.
	 * 
	 * @param grenadeLauncher
	 * @param player
	 * @param control
	 * @param window
	 */
	protected void collectedGrenadeLauncher(ItemControl grenadeLauncher, Player player, ScreenControl control, Window window) {
		if (EventConfig.grenadeLauncher == false) {
			if (grenadeLauncher.isCaught(player)) {
				ItemControl.addGrenadeLauncher();
				control.obtainedItem(WeaponConfig.GRENADE_LAUNCHER, TypeItemConfig.WEAPON, window);
			}
		}
	}

	/**
	 * Método que verifica se o lança foguetes foi coletado.
	 * 
	 * @param rocketLauncher
	 * @param player
	 * @param control
	 * @param window
	 */
	protected void collectedRocketLauncher(ItemControl rocketLauncher, Player player, ScreenControl control, Window window) {
		if (EventConfig.rocketLauncher == false) {
			if (rocketLauncher.isCaught(player)) {
				ItemControl.addRocketLauncher();
				control.obtainedItem(WeaponConfig.ROCKET_LAUNCHER, TypeItemConfig.WEAPON, window);
			}
		}
	}

	/*
	 * ================================================
	 * 
	 * MUNIÇÕES COLETADAS
	 * 
	 * Estes métodos verificam se as munições declaradas foram coletadas ou não
	 * pelo jogador, por tanto devem ser invocados dentro do loop do jogo.
	 * 
	 * ================================================
	 */
	/**
	 * Método que verifica se a munição declarada foi coletada.
	 * 
	 * @param itemControl
	 * @param eventConfig
	 * @param player
	 * @param control
	 * @param window
	 * @return boolean
	 */
	protected boolean collectedHandgunAmmo(ItemControl itemControl, boolean eventConfig, Player player, ScreenControl control,
			Window window) {
		if (eventConfig == false) {
			if (itemControl.isCaught(player)) {
				eventConfig = true;
				ItemControl.verifyWeapon(WeaponConfig.HANDGUN, 50);
				control.obtainedItem(AmmunitionConfig.HANDGUN_AMMUNITION_NAME, TypeItemConfig.AMMO, window);
				return eventConfig;
			}
		}
		return eventConfig;
	}

	/**
	 * Método que verifica se a munição declarada foi coletada.
	 * 
	 * @param itemControl
	 * @param eventConfig
	 * @param player
	 * @param control
	 * @param window
	 * @return boolean
	 */
	protected boolean collectedShotgunAmmo(ItemControl itemControl, boolean eventConfig, Player player, ScreenControl control,
			Window window) {
		if (eventConfig == false) {
			if (itemControl.isCaught(player)) {
				eventConfig = true;
				ItemControl.verifyWeapon(WeaponConfig.SHOTGUN, 7);
				control.obtainedItem(AmmunitionConfig.SHOTGUN_AMMUNITION_NAME, TypeItemConfig.AMMO, window);
				return eventConfig;
			}
		}
		return eventConfig;
	}

	/**
	 * Método que verifica se a munição declarada foi coletada.
	 * 
	 * @param itemControl
	 * @param eventConfig
	 * @param player
	 * @param control
	 * @param window
	 * @return boolean
	 */
	protected boolean collectedMagnumAmmo(ItemControl itemControl, boolean eventConfig, Player player, ScreenControl control,
			Window window) {
		if (eventConfig == false) {
			if (itemControl.isCaught(player)) {
				eventConfig = true;
				ItemControl.verifyWeapon(WeaponConfig.MAGNUM, 12);
				control.obtainedItem(AmmunitionConfig.MAGNUM_AMMUNITION_NAME, TypeItemConfig.AMMO, window);
				return eventConfig;
			}
		}
		return eventConfig;
	}

	/**
	 * Método que verifica se a munição declarada foi coletada.
	 * 
	 * @param itemControl
	 * @param eventConfig
	 * @param player
	 * @param control
	 * @param window
	 * @return boolean
	 */
	protected boolean collectedMachineAmmo(ItemControl itemControl, boolean eventConfig, Player player, ScreenControl control,
			Window window) {
		if (eventConfig == false) {
			if (itemControl.isCaught(player)) {
				eventConfig = true;
				ItemControl.verifyWeapon(WeaponConfig.MACHINEGUN, 100);
				control.obtainedItem(AmmunitionConfig.MACHINEGUN_AMMUNITION_NAME, TypeItemConfig.AMMO, window);
				return eventConfig;
			}
		}
		return eventConfig;
	}

	/**
	 * Método que verifica se a munição declarada foi coletada.
	 * 
	 * @param itemControl
	 * @param eventConfig
	 * @param player
	 * @param control
	 * @param window
	 * @return boolean
	 */
	protected boolean collectedGrenadeAmmo(ItemControl itemControl, boolean eventConfig, Player player, ScreenControl control,
			Window window) {
		if (eventConfig == false) {
			if (itemControl.isCaught(player)) {
				eventConfig = true;
				ItemControl.verifyWeapon(WeaponConfig.GRENADE_LAUNCHER, 4);
				control.obtainedItem(AmmunitionConfig.GRENADE_NAME, TypeItemConfig.AMMO, window);
				return eventConfig;
			}
		}
		return eventConfig;
	}

	/**
	 * Método que verifica se a munição declarada foi coletada.
	 * 
	 * @param itemControl
	 * @param eventConfig
	 * @param player
	 * @param control
	 * @param window
	 * @return boolean
	 */
	protected boolean collectedRocketAmmo(ItemControl itemControl, boolean eventConfig, Player player, ScreenControl control,
			Window window) {
		if (eventConfig == false) {
			if (itemControl.isCaught(player)) {
				eventConfig = true;
				ItemControl.verifyWeapon(WeaponConfig.ROCKET_LAUNCHER, 2);
				control.obtainedItem(AmmunitionConfig.ROCKET_NAME, TypeItemConfig.AMMO, window);
				return eventConfig;
			}
		}
		return eventConfig;
	}

	/*
	 * ================================================
	 * 
	 * ITENS COLETADOS
	 * 
	 * Estes métodos verificam se os itens declarados foram coletadas ou não
	 * pelo jogador, por tanto devem ser invocados dentro do loop do jogo.
	 * 
	 * ================================================
	 */

	/**
	 * Este método é utilizado para coletar os demais itens que <b>NÃO</b> sejam
	 * armas, munições e medicamentos.
	 * 
	 * @param itemControl
	 * @param name
	 * @param imageName
	 * @param item
	 * @param typeItemConfig
	 * @param player
	 * @param control
	 * @param window
	 * @return boolean
	 */
	protected boolean collectedItem(ItemControl itemControl, String name, String imageName, boolean eventConfig, int typeItemConfig,
			Player player, ScreenControl control, Window window) {
		if (eventConfig == false) {
			if (itemControl.isCaught(player)) {
				eventConfig = true;
				control.obtainedAndAddItem(name, typeItemConfig, window, imageName);
				return eventConfig;
			}
		}
		return eventConfig;
	}

	/*
	 * ================================================
	 * 
	 * MEDICAMENTOS COLETADOS
	 * 
	 * Estes métodos verificam se os medicamentos declarados foram coletadas ou
	 * não pelo jogador, por tanto devem ser invocados dentro do loop do jogo.
	 * 
	 * ================================================
	 */

	/**
	 * Verifica se os medicamentos declarados foram coletados ou não.
	 * 
	 * @param itemControl
	 * @param eventConfig
	 * @param player
	 * @param control
	 * @param window
	 * @return boolean
	 */
	protected boolean collectedMedkit(ItemControl itemControl, boolean eventConfig, Player player, ScreenControl control, Window window) {
		if (eventConfig == false) {
			if (itemControl.isCaught(player)) {
				eventConfig = true;
				ItemControl.addMedKit();
				control.obtainedItem(MedkitConfig.MEDKIT, TypeItemConfig.MED_KIT, window);
				return eventConfig;
			}
		}
		return eventConfig;
	}

	/*
	 * ######################################################
	 */

	@Override
	public void classControl(Window previousScreen) {
		control = new ScreenControl();
		window = previousScreen;
		keyboard = window.getKeyboard();
	}

	@Override
	public void scene(String scn) {
		scene = new Scene();
		scene.loadFromFile(URL.scenario(scn));
		scene.setDrawStartPos(0, 0);
	}

	@Override
	public void createPlayer(double x, double y) {
		// player = new PlayerCharacter(x, y);
		player = new Player(x, y);
		scene.addOverlay(player);
	}

	@Override
	public void scenarioMusic(String audio) {
		if (audio != null) {
			SoundController.playMusic(URL.audio(audio));
		}
	}

	@Override
	public void drawFrame() {
		gameControl(control, window, player, loop);
		getOffSetPlayer(scene, player);
		if (npcs != null) {
			getOffSetNPCZumbi(scene, npcs);
		}
	}

	@Override
	public void characterControl() {
		player.playerControl(keyboard, window, scene);
		// if (npcZombies != null) {
		player.enemiePresence(npcs, window, scene);
		control.npcControl(npcs, player, window, scene);
		// }
	}

	@Override
	public void drawMap() {
		mapControl();
	}

	@Override
	public void createItem() {
		// TODO Auto-generated method stub

	}

	@Override
	public void createObjects() {
		// TODO Auto-generated method stub

	}

	// @Override
	// public void createEnemie() {
	// int quantidadeInimigos = 1;
	//
	// npcs = new NPCZombie[quantidadeInimigos];
	// int raioX = 100, raioY = 20;
	//
	// int y = 00;
	// for (int i = 0; i < quantidadeInimigos; i++) {
	// // npcs[i] = new NPCZombie(random.nextInt(4), (player.x - (raioX /
	// // 2)) + new Random().nextInt(raioX), player.y
	// // - new Random().nextInt(raioY));
	// npcs[i] = new NPCZombie(random.nextInt(4));
	//
	// // npc[i] = new NPCZombie(random.nextInt(4),2600,y);
	// // y += 40;
	//
	// scene.addOverlay(npcs[i]);
	//
	// }
	// }

	@Override
	public void isRainy(boolean isRainy) {
		// TODO Auto-generated method stub

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

	// @OVERRIDE
	// PUBLIC VOID DRAWRAIN() {
	// IF (RAINCONTROL.ISRAINY == TRUE) {
	// RAINCONTROL.RAIN(RAIN, 500);
	// } ELSE {
	// RAINCONTROL.STOPRAIN(RAIN, SCENE);
	// RAIN = NULL;
	// }
	// }

	@Override
	public void events() {
		// TODO Auto-generated method stub

	}

	// ***************************************************
	private void getInstance(double x, double y, // NPC[] npc,
			Window previousScreen, String scn, String audio) {
		classControl(previousScreen);
		scene(scn);
		scenarioMusic(audio);
		createPlayer(x, y);
		createItem();
		createObjects();
		createEnemie();
		isRainy(false);
	}

	private void pauseGame() {
		loop = control.loopControl(loop);
		drawFrame();
	}

	private void runGame() {
		drawFrame();
		drawMap();
		characterControl();
		addObjects();
		objectAnimation();
		addItem();
		collectedItem();
		events();
		climaticControl(tempo);
	}

	/**
	 * Método que deve ser chamado dentro do construtor das classes de cenário.
	 * Ela controla as instâncias de objetos necessários e faz o controle de
	 * loop e pause do jogo.
	 * 
	 * @param x
	 * @param y
	 * @param previousScreen
	 * @param scn
	 * @param audio
	 * @param tempo
	 */
	public void start(double x, double y, Window previousScreen, String scn, String audio, int tempo) {

		this.tempo = tempo;
		getInstance(x, y, previousScreen, scn, audio);

		while (true) {
			if (keyboard.keyDown(Keyboard.SPACE_KEY)) {
				pauseGame();
			} else if (loop == true) {
				runGame();
			}
		}
	}

}