package projeto.armaseitens;

import java.awt.Point;
import java.util.Vector;

import projeto.atores.NPC;
import projeto.base.Scene;
import projeto.base.Sprite;
import projeto.base.TileInfo;
import projeto.controle.Control;
import projeto.controle.SteadyShot;
import projeto.controle.URL;

/**
 * Esta classe é responsável pela implementação do tiro do personagem principal
 * do jogo.
 * 
 * @author Luiz Alberto
 * 
 */
public class Shot extends Sprite {

	/**
	 * Contrutor padrão da classe.
	 * 
	 * @param x
	 * @param y
	 * @param way
	 */
	public Shot(double x, double y, int way) {
		//super(URL.sprite("tiro.png"), 7);
		super(URL.sprite("tiro.png"), 16);
		setTotalDuration(600);
		update();
		this.way = way;
		this.x = x;
		this.y = y;
	}

	protected static final short SHOOTING_SPEED = 50;
	protected int way = SteadyShot.STOP;
	protected boolean move = false;
	protected int direction = 3;

	/*
	 * =========================================================
	 * 
	 * METODOS
	 * 
	 * Aqui são implementadas todas as ações pertinentes ao tiro efetuado por
	 * qualquer arma do jogo.
	 * 
	 * =========================================================
	 */

	/**
	 * Método que pega a posição do tiro passada no construtor e realiza o
	 * disparo a partir daquela posição.
	 */
	@SuppressWarnings("unused")
	private void move2() {
		if (way == SteadyShot.LEFT) {
			this.x -= SHOOTING_SPEED;
			if (direction != 3) {
				setSequence(1, 7);
			}
			move = true;
		}
		if (way == SteadyShot.RIGHT) {
			this.x += SHOOTING_SPEED;
			if (direction != 3) {
				setSequence(1, 7);
			}
			move = true;
		}
		if (way == SteadyShot.UP) {
			this.y -= SHOOTING_SPEED;
			if (direction != 3) {
				setSequence(1, 7);
			}
			move = true;
		}
		if (way == SteadyShot.DOWN || way == SteadyShot.STOP) {
			this.y += SHOOTING_SPEED;
			if (direction != 3) {
				setSequence(1, 7);
			}
			move = true;
		}
		if (move) {
			update();
			move = false;
		}
	}
	
	public void move() {
			
		if (way == SteadyShot.LEFT) {
			this.x -= SHOOTING_SPEED;
			if (direction != 1) {
				setSequence(4, 8);
			}
			move = true;
		}
		if (way == SteadyShot.RIGHT) {
			this.x += SHOOTING_SPEED;
			if (direction != 2) {
				setSequence(8, 12);
			}
			move = true;
		}
		if (way == SteadyShot.UP) {
			this.y -= SHOOTING_SPEED;
			if (direction != 4) {
				setSequence(12, 16);
			}
			move = true;
		}
		if (way == SteadyShot.DOWN || way == SteadyShot.STOP) {
			this.y += SHOOTING_SPEED;
			if (direction != 5) {
				setSequence(0, 4);
			}
			move = true;
		}
		if (move) {
			update();
			move = false;
		}
	}


	/**
	 * Método invocado ao atingir um inimigo com um tiro.
	 * 
	 * @param npc
	 * @param scene
	 * @param shot
	 */
	public void reachesNPC(NPC npc, Scene scene, Shot shot) {
		if (npc.isAlive == true) {
			if (shot.collided(npc) == true) {
				decreaseHP(npc);
				npc.perceptionRay = 1000;
				// npc.pursuitVelocity = 0.7;
				npc.dead(scene);
				shotDispersion(shot);
			}
		}
	}

	/**
	 * Método invocado ao atingir um tile de passagem proibida.
	 * 
	 * @param scene
	 * @param shot
	 */
	public void reachesTile(Scene scene, Shot shot) {

		Control control = new Control();
		Point min = new Point((int) this.x, (int) this.y);
		Point max = new Point((int) (this.x + this.width), (int) (this.y + this.height));

		Vector<?> tiles = scene.getTilesFromPosition(min, max);
		for (int i = 0; i < tiles.size(); i++) {
			TileInfo tile = (TileInfo) tiles.elementAt(i);

			if (control.tileCollision(this, tile) == true) {
				shotDispersion(shot);
			}
		}
	}

	/**
	 * Quando um tiro atinge um inimigo ou objeto ele é removido da area da
	 * tela.
	 * 
	 * @param shot
	 */
	private void shotDispersion(Shot shot) {
		shot.x = -3000;
		shot.y = -3000;
	}

	/**
	 * Ao atingir um NPC, o HP é reduzido de acordo com a arma utilizada.
	 * 
	 * @param npc
	 */
	private void decreaseHP(NPC npc) {
		npc.HP -= WeaponControl.weaponList.get(WeaponControl.getIndex()).getDamage();
	}
}
