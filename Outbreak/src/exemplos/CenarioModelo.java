package exemplos;

import outbreak.OakHillStreets;
import projeto.armaseitens.ItemControl;
import projeto.atores.MonsterFactory;
import projeto.atores.Objects;
import projeto.base.Window;
import projeto.cenario.Scenario;
import projeto.configuracoes.DirConfig;
import projeto.configuracoes.EventConfig;
import projeto.configuracoes.ItemNameConfig;
import projeto.configuracoes.TypeItemConfig;
import projeto.enums.Monster;
import projeto.som.SoundController;

/**
 * Esta classe serve como um modelo de refer�ncia para todas as outras classes
 * cen�rio, pois � aqui o n�cleo do game onde s�o adicionados os personagens, os
 * inimigos, os objetos, as m�sicas, os eventos, os combates, a leitura dos
 * arquivos "scn", etc. Note que ela extende de JFrame, ou seja � a tela do jogo
 * em si.
 * 
 * @author Luiz Alberto
 * 
 */
@SuppressWarnings("serial")
public class CenarioModelo extends Scenario {

	private Objects fogo, caixa, caminhao, porta;
	private ItemControl machinegun, shotgun, magnum, grenadeLauncher, rocketLauncher, handgunAmmo1, firstAidKit1, chaveDelegacia;

	/**
	 * Contrutor padr�o da classe. No construtor devem constar os m�todos de
	 * cria��o de objetos da classe e um loop que faz o jogo rodar.
	 * 
	 * @param x
	 * @param y
	 * @param previousScreen
	 */
	public CenarioModelo(double x, double y, Window previousScreen) {

		start(x, y, previousScreen, "CenarioModelo.scn", null, TEMPO_LIMPO);
		// colidirPorta();
	}

	/*
	 * COLOCAR NOVA DESCRI��O
	 * ================================================================
	 * 
	 * IMPLEMENTS SCENARIO_INTERFACE
	 * 
	 * Os c�digo abaixo s�o as implementa��es necess�rias da classe
	 * ScenarioInterface. O construtor da classe deve controlar quais s�o os
	 * m�todos que devem ficar dentro ou fora do loop principal.
	 * 
	 * 
	 * Fora:
	 * 
	 * scenarioMusic(); classControl(previousScreen); scene(); createItem();
	 * createObjects(); createPlayer(x, y); createEnemie(npc, npc.length);
	 * isRainy(boolean);
	 * 
	 * Dentro:
	 * 
	 * drawFrame(); drawMap(); characterControl(); addObjects();
	 * objectAnimation(); addItem(); collectedItem(); drawRain(); events();
	 * 
	 * 
	 * *Observa��o: � importante manter a ordem dos m�todos do jeito que est�o,
	 * pois cada um tem o momento certo de ser executado.
	 * 
	 * ================================================================
	 */

	// ::::::::cria os objetos::::::::
	public void createObjects() {
		fogo = createObjects(DirConfig.FIRE, 100, 100, false, 10, scene);
		porta = createObjects(DirConfig.DOOR, 300, 50, false, 1, scene);
		caixa = createObjects(DirConfig.WOODEN_CASE, 500, 300, true, 1, scene);
		caminhao = createObjects(DirConfig.TRUCK, 70, 500, false, 1, scene);
	}

	// ::::::::cria os itens::::::::
	public void createItem() {
		machinegun = createItem(EventConfig.machineGun, DirConfig.MACHINEGUN, 100, 170, scene);
		shotgun = createItem(EventConfig.shotgun, DirConfig.SHOTGUN, 100, 200, scene);
		magnum = createItem(EventConfig.magnum, DirConfig.MAGNUM, 400, 260, scene);
		grenadeLauncher = createItem(EventConfig.grenadeLauncher, DirConfig.GRENADE_LAUNCHER, 500, 260, scene);
		rocketLauncher = createItem(EventConfig.rocketLauncher, DirConfig.ROCKET_LAUNCHER, 600, 260, scene);
		handgunAmmo1 = createItem(EventConfig.handgunAmmo1, DirConfig.HANDGUN_AMMO, 140, 70, scene);
		firstAidKit1 = createItem(EventConfig.firstAidKit1, DirConfig.FIRST_AID_KIT, 190, 170, scene);
		chaveDelegacia = createItem(EventConfig.precintKey, DirConfig.KEY, 500, 100, scene);
	}

	/*
	 * ========================================================
	 * 
	 * RUN
	 * 
	 * Aqui v�o os m�todos de loop do jogo.
	 * 
	 * ========================================================
	 */

	// ::::::::controle de personagens::::::::
	public void characterControl() {
		player.playerControl(keyboard, window, scene);
		player.enemiePresence(npcs, window, scene);
		control.npcControl(npcs, player, window, scene);
	}

	// ::::::::controle de fisica(portas devem ser 'false')::::::::
	public void addObjects() {
		caixa.addObject(player, npcs, scene, window, true);
		fogo.addObject(player, npcs, scene, window, true);
		caminhao.addObject(player, npcs, scene, window, true);
		porta.addObject(player, npcs, scene, window, false);
		porta.pathControl(window, scene);
		// porta.addObject(player, scene, window, false);
	}

	// ::::::::anima��o de objetos(ex.: fogo)::::::::
	public void objectAnimation() {
		objectAnimation(fogo);
	}

	// // ::::::::controle de itens::::::::
	public void addItem() {
		addItem(firstAidKit1, EventConfig.firstAidKit1, scene);
		addItem(machinegun, EventConfig.machineGun, scene);
		addItem(shotgun, EventConfig.shotgun, scene);
		addItem(magnum, EventConfig.magnum, scene);
		addItem(grenadeLauncher, EventConfig.grenadeLauncher, scene);
		addItem(rocketLauncher, EventConfig.rocketLauncher, scene);
		addItem(handgunAmmo1, EventConfig.handgunAmmo1, scene);
		addItem(chaveDelegacia, EventConfig.precintKey, scene);
	}

	// // ::::::::controle de itens coletados::::::::
	public void collectedItem() {
		collectedMachinegun(machinegun, player, control, window);
		collectedShotgun(shotgun, player, control, window);
		collectedGrenadeLauncher(grenadeLauncher, player, control, window);
		collectedMagnum(magnum, player, control, window);
		collectedRocketLauncher(rocketLauncher, player, control, window);
		EventConfig.handgunAmmo1 = collectedHandgunAmmo(handgunAmmo1, EventConfig.handgunAmmo1, player, control, window);
		EventConfig.firstAidKit1 = collectedMedkit(firstAidKit1, EventConfig.firstAidKit1, player, control, window);
		EventConfig.precintKey = collectedItem(chaveDelegacia, ItemNameConfig.PRECINT_KEY, DirConfig.KEY, EventConfig.precintKey,
				TypeItemConfig.ITEM, player, control, window);
	}

	// ::::::::pinta a chuva na tela::::::::
	// public void drawRain() {
	// if (RainControl.isRainy == true) {
	// RainControl.rain(rain, 500);
	// } else {
	// RainControl.stopRain(rain, scene);
	// rain = null;
	// }
	// }

	/*
	 * ==========================================================
	 * 
	 * EVENTOS DO CEN�RIO
	 * 
	 * Toda a l�gica e controle do que acontece no cen�rio ser� implementada
	 * atrav�s do m�todo events.
	 * 
	 * ==========================================================
	 */

	public void events() {
		// control.setMessage("" + loop);
		// colidirPorta();
		// dancadaChuva();
		// enemieHP();
		// colidirCaixa();
		// printItemValues();
		// velocidadeInimigos();
		colidirPorta();
	}

	/*
	 * ==========================================================
	 * 
	 * M�TODOS DE EVENTOS
	 * 
	 * Todos os m�todos invocados pelo m�todo events ser�o implementados abaixo.
	 * Como exemplo de m�todos de eventos podemos ter: colidirPorta();
	 * derrotarChefe(); dialogoComNpc(); coletarChave(); etc.
	 * 
	 * NOTA: Nenhum dos m�todos abaixo � uma implementa��o da classe
	 * ScenarioInterface sendo de responsabilidade do programador cri�-las.
	 * 
	 * ==========================================================
	 */

	/**
	 * Este m�todo � invocado caso o jogador colida com a porta e possua a
	 * chave.
	 * 
	 * Ao colidir com a porta temos os efeitos
	 * 
	 * 1 - a chuva para | 2 - A musica para | 3 - Troca de cen�rio
	 * 
	 */
	private void colidirPorta() {
		if (player.collided(porta) == true && EventConfig.precintKey == true) {
			SoundController.stopMusic();
			SoundController.door();
			new SegundoCenario(500, 130, window);

		} else {

		}
	}

	// private void colidirCaixa() {
	// if (player.collided(caixa) == true) {
	// System.out.println("empurrando a caixa");
	// }
	// }

	// private void dancadaChuva() {
	// try {
	// isRainy(true);
	// drawRain();
	// } catch (Exception e) {
	// System.out.println("===================================");
	// System.out.println("chuva");
	// System.out.println("===================================");
	// e.printStackTrace();
	// System.out.println("===================================");
	// System.out.println();
	// }
	// }

	private void printItemValues() {
		System.out.println("===================================");
		System.out.println("itens booleanos");
		System.out.println("===================================");
		System.out.println("medicamento..: " + EventConfig.firstAidKit1);
		System.out.println("magnum.......: " + EventConfig.magnum);
		System.out.println("grenade......: " + EventConfig.grenadeLauncher);
		System.out.println("rocket.......: " + EventConfig.rocketLauncher);
		System.out.println("metralhadora.: " + EventConfig.machineGun);
		System.out.println("shotgun......: " + EventConfig.shotgun);
		System.out.println("muni��o......: " + EventConfig.handgunAmmo1);
		System.out.println("chave........: " + EventConfig.precintKey);
		System.out.println("===================================");
		System.out.println();
	}

	private void velocidadeInimigos() {
		System.out.println("===================================");
		System.out.println("velocidade dos inimigos");
		System.out.println("===================================");
		for (int i = 0; i < npcs.length; i++) {
			System.out.println(npcs[i].pursuitVelocity);
		}
		System.out.println("===================================");
		System.out.println();
	}

	private void enemieHP() {
		System.out.println("===================================");
		System.out.println("HP dos inimigos");
		System.out.println("===================================");
		for (int i = 0; i < npcs.length; i++) {
			System.out.println(i + ": " + npcs[i].HP);
		}
		System.out.println("===================================");
		System.out.println();
	}

	/**
	 * Este modelo funciona de forma que podemos descartar a cria��o do objeto
	 * porta. Para isso devemos utilizar o m�todo de controle de tiles da classe
	 * Scenario e especificar qual tile representa a porta.
	 * 
	 * Obs.: O tile deve ser percorr�vel, sen�o o contato com a "porta" ser�
	 * impedido.
	 */
	private void colidirTilePorta() {
		if (tileCollision(1) == true && EventConfig.precintKey == true) {
			SoundController.door();
			new OakHillStreets(12990, 2850, window);
		}
	}

	@Override
	public void createEnemie() {
		int quantidade = 1;
		npcs = MonsterFactory.getMonstro(Monster.ZUMBI, quantidade);
		for (int i = 0; i < quantidade; i++) {
			scene.addOverlay(npcs[i]);
		}
	}
}
