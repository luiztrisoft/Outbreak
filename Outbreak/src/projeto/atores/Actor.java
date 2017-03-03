package projeto.atores;

import java.awt.Point;
import java.util.Vector;

import projeto.base.GameObject;
import projeto.base.Scene;
import projeto.base.Sprite;
import projeto.base.TileInfo;
import projeto.base.Window;
import projeto.configuracoes.GameConfig;
import projeto.controle.Control;

/**
 * Esta superclasse é o que dá vida aos personagens sejam eles o jogador, os
 * inimigos ou demais personagens através de suas respectivas subclasses.
 * 
 * @author Luiz Alberto
 * 
 */
public abstract class Actor extends Sprite {

	public static double HP = 100;
	
	public boolean isAlive = true;
	protected boolean keyPressed;
	protected boolean move = false;
	protected int direction = 3;
	protected int moviment = getMoviment();
	public double powerAttack = 1.0 + (GameConfig.level / 100);
	public static int velocity = 5;

	private Control control = new Control();

	/**
	 * Construtor da classe.
	 * 
	 * @param fileName
	 */
	public Actor(String fileName) {
		super(fileName);
	}

	/**
	 * Construtor da classe.
	 * 
	 * @param fileName
	 * @param i
	 */
	public Actor(String fileName, int i) {
		super(fileName, i);
		setTotalDuration(2000);
	}

	/*
	 * =================================================
	 * 
	 * METODOS
	 * 
	 * Estes scripts são métodos comuns a todos os atores do jogo.
	 * 
	 * =================================================
	 */
	/**
	 * Retorna velocidade de movimento.
	 * 
	 * @return int
	 */
	public int getMovementSpeed() {
		return (moviment);
	}

	/**
	 * Velocidade do ator.
	 * 
	 * @return int
	 */
	public int getMoviment() {
		return velocity;//7 velocidade normal
	}

	/**
	 * Retorna a direção do personagem.
	 * 
	 * @return
	 */
	public int getDirection() {
		return direction;
	}

	// ::::::::Verificação de colisão entre atores::::::::
	/**
	 * Verifica colisão entre sprites
	 * 
	 * @param object
	 * @param object2
	 * @return boolean
	 */
	boolean verticalCollision(GameObject object, GameObject object2) {
		if (object2.x + object2.width <= object.x)
			return false;
		if (object.x + object.width <= object2.x)
			return false;
		return true;
	}

	/**
	 * Verifica colisão entre sprites
	 * 
	 * @param object
	 * @param object2
	 * @return boolean
	 */
	boolean horizontalCollision(GameObject object, GameObject object2) {
		if (object2.y + object2.height <= object.y)
			return false;
		if (object.y + object.height <= object2.y)
			return false;
		return true;
	}

	/**
	 * Este método é responsável por impedir que qualquer personagem atravessa
	 * tiles que não façam parte do caminho como paredes, blocos, árvores, etc.
	 * 
	 * @param window
	 * @param scene
	 */
	public void pathControl(Window window, Scene scene) {
		Point min = new Point((int) this.x, (int) this.y);
		Point max = new Point((int) (this.x + this.width),
				(int) (this.y + this.height));

		Vector<?> tiles = scene.getTilesFromPosition(min, max);

		for (int i = 0; i < tiles.size(); i++) {
			TileInfo tile = (TileInfo) tiles.elementAt(i);

			if (control.tileCollision(this, tile) == true) {

				if (verticalCollision(this, tile)) {
					if (tile.y + tile.height + this.getVelocityY()
							- this.getMovementSpeed() - 1 < this.y) {
						this.y = tile.y + tile.height;
						this.setVelocityY(0.0);
					}

					else if (tile.y > this.y + this.height
							- this.getVelocityY() - this.getMovementSpeed() - 1) {
						this.setVelocityY(0.0);
						this.y = tile.y - this.height;
					}
				}

				if (horizontalCollision(this, tile)) {
					if (tile.x > this.x + this.width - this.getMovementSpeed()
							- 1)
						this.x = tile.x - this.width;
					else
						this.x = tile.x + tile.width;
				}
			}
		}
	}

	/**
	 * Este método reduz o valor do HP do ator.
	 * 
	 * @param attack
	 */
	public void decreaseHP(double attack) {
		HP -= attack;
	}

	

}
