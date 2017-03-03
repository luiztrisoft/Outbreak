package projeto.atores;

import java.awt.event.KeyEvent;

import projeto.armaseitens.ShotControl;
import projeto.armaseitens.WeaponControl;
import projeto.base.Animation;
import projeto.base.Keyboard;
import projeto.base.Scene;
import projeto.base.Window;
import projeto.configuracoes.EventConfig;
import projeto.controle.URL;
import projeto.telas.GameOver;

/**
 * Classe respons�vel pela modelagem do personagem jog�vel.
 * 
 * @author Luiz Alberto
 * 
 */
public/* abstract */class Player extends Actor {

	// ::::::::Sa�de vital do personagem::::::::
	public static double infection = 0;

	/**
	 * Construtor padr�o da classe.
	 * 
	 * @param fileName
	 * @param numberOfFrames
	 */
	// public Player(String fileName, int numberOfFrames) {
	// super(fileName, numberOfFrames);
	// }
	private final static String PLAYER = "player2.png";

	public Player(double x, double y) {
		super(URL.sprite(PLAYER), 16);
		velocity = 7;
		this.x = x;
		this.y = y;
	}

	private Keyboard keyboard;
	private WeaponControl arsenal = new WeaponControl();
	private ShotControl tiros = new ShotControl();
	private Animation blood = new Animation(URL.sprite("blood.png"), 10);

	/*
	 * =========================================================
	 * 
	 * METODOS
	 * 
	 * Aqui s�o implementadas todas as a��es pertinentes ao personagem
	 * controlado pelo jogador.
	 * 
	 * =========================================================
	 */

	/**
	 * Controle do personagem.
	 * 
	 * @param keyboard
	 * @param window
	 * @param scene
	 * @param npc
	 */
	public void playerControl(Keyboard keyboard, Window window, Scene scene) {
		this.move(keyboard, window);
		this.pathControl(window, scene);
		this.screenLimit();
		this.useMedKit(window);
	}

	/**
	 * Este m�todo s� deve ser chamado se houver algum NPC no cen�rio.
	 * 
	 * @param npc
	 * @param window
	 * @param scene
	 */
	public void enemiePresence(NPC[] npc, Window window, Scene scene) {
		this.isAttacked(npc, window);
		this.useWeapon(npc, window, scene);
	}

	/**
	 * Este m�todo � usado para o jogador controlar o personagem pelo cen�rio
	 * atrav�s das setas do teclado do computador.
	 * 
	 * @param keyboard
	 * @param window
	 */
	public void move(Keyboard keyboard, Window window) {
		keyPressed = true;

		if (keyboard.keyDown(Keyboard.LEFT_KEY) == true) {
			if (this.x - moviment > 0)
				this.x -= moviment;
			if (direction != 1) {
				setSequence(4, 8);
				direction = 1;
			}
			move = true;
		} else if (keyboard.keyDown(Keyboard.RIGHT_KEY) == true) {
			if (this.x + moviment < window.getWidth() - width)
				this.x += moviment;
			if (direction != 2) {
				setSequence(8, 12);
				direction = 2;
			}
			move = true;
		} else if (keyboard.keyDown(Keyboard.UP_KEY) == true) {
			if (this.y + moviment < window.getHeight() - height)
				this.y -= moviment;
			if (direction != 4) {
				setSequence(12, 16);
				direction = 4;
			}
			move = true;
		} else if (keyboard.keyDown(Keyboard.DOWN_KEY) == true) {
			if (this.y + moviment < window.getHeight() - height)
				this.y += moviment;
			if (direction != 5) {
				setSequence(0, 4);
				direction = 5;
			}
			move = true;
		} else
			keyPressed = false;
		if (move) {
			update();
			move = false;
		}
	}

	/**
	 * Este m�todo � serve para o jogador recuperar sua energia ao apertar a
	 * tecla D caso possua pelo menos um medicamento. Ele reduz a infec��o em
	 * 10%.
	 * 
	 * @param window
	 */
	public void useMedKit(Window window) {
		keyboard = window.getKeyboard();
		if (keyboard.keyDown(KeyEvent.VK_D)) {
			if (EventConfig.firstAidKit >= 1) {
				EventConfig.firstAidKit -= 1;
				recovery();
			}
		}
	}

	/**
	 * M�todo de baixa o n�vel de infec��o do personagem.
	 */
	private void recovery() {
		int recuperacao = 25;
		
		infection -= recuperacao;
		if (infection <= 0) {
			infection = 0;
		}
		HP += recuperacao;
		if (HP >= 100) {
			HP = 100;
		}
	}

	/**
	 * Este � o m�todo de manuseio das armas. Atrav�s deste procedimento o
	 * personagem pode atirar, recarregar e trocar de arma durante o jogo.
	 * 
	 * @param npc
	 * @param window
	 * @param scene
	 */
	public void useWeapon(NPC[] npc, Window window, Scene scene) {
		shoot(npc, window, scene);
		reload(window);
		exchangingGun(window);
	}

	/**
	 * M�todo privado respons�vel pelo disparo da arma.
	 * 
	 * @param npc
	 * @param window
	 * @param scene
	 */
	private void shoot(NPC[] npc, Window window, Scene scene) {
		keyboard = window.getKeyboard();

		double disparoV = this.x + this.width / 2 - 15;
		double disparoH = this.y + this.height / 2 - 7;

		if (keyboard.keyDown(KeyEvent.VK_A)) {
			tiros.addShot(disparoV, disparoH, this.getDirection(), scene);
		}
		tiros.run(window, scene, npc);
	}

	/**
	 * M�todo privado respons�vel por recarregar a arma.
	 * 
	 * @param window
	 */
	private void reload(Window window) {
		keyboard = window.getKeyboard();
		if (keyboard.keyDown(Keyboard.S_KEY)) {
			arsenal.reload();
		}
	}

	/**
	 * M�todo privado respons�vel por trocar de arma.
	 * 
	 * @param window
	 */
	private void exchangingGun(Window window) {
		keyboard = window.getKeyboard();
		if (keyboard.keyDown(Keyboard.ENTER_KEY)) {
			arsenal.change();
		}
	}

	/**
	 * Mant�m o personagem dentro da tela, n�o deixando ultrapassar as margens
	 * de cima e de baixo, mantendo-o sempre vis�vel na tela.
	 */
	public void screenLimit() {
		if (this.y <= 0) {
			this.y = 0;
		} else if (this.y >= 540) {
			this.y = 540;
		}
	}

	/**
	 * M�todo que retorna o valor da infec��o do player.
	 * 
	 * @return double
	 */
	public double getInfeccao() {
		return infection;
	}

	/**
	 * M�todo acionado quando o player � atingido por npc's causando-lhe dano.
	 * 
	 * @param npc
	 * @param window
	 * @return boolean
	 */

	public boolean isAttacked(NPC npc[], Window window) {
		for (int i = 0; i < npc.length; i++) {
			if (npc[i].collided(this) == true) {
				bloodAnimation();
				if (this.getInfeccao() >= 100) {
					this.infected(window);
				} else {
					this.setInfeccao(npc[i].powerAttack);
					this.decreaseHP(npc[i].powerAttack);
				}
				this.moviment = 3;
				return true;
			}
			this.moviment = getMoviment();
		}
		return false;
	}

	/**
	 * Este m�todo realiza a anima��o do sangue jorrando quando o personagem
	 * est� em contato com o inimigo.
	 */
	private void bloodAnimation() {
		blood.x = this.x + 10;
		blood.y = this.y - 20;
		blood.setTotalDuration(20);
		blood.update();
		blood.draw();
	}

	/**
	 * M�todo que adicina pontos de infec��o ao persongem. � usado quando o
	 * personagem sofre algum tipo de dano.
	 * 
	 * @param infeccao
	 */
	@SuppressWarnings("static-access")
	private void setInfeccao(double infection) {
		this.infection += infection;
	}

	/**
	 * M�todo invocado quando o personagem morre. Ele mostra a tela you are dead
	 * por alguns segundos e volta a tela de �nicio.
	 * 
	 * @param window
	 */
	private void infected(Window window) {
		GameOver.gameOver(window);
	}
}
