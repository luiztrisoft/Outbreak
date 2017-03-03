package projeto.atores;

import projeto.base.GameObject;
import projeto.base.Scene;
import projeto.base.Window;

/**
 * Esta classe representa objetos físicos do cenário como carros, caixas, e
 * demais obstáculos. Ao instanciar esta classe você passa um parâmetro booleano
 * que informa que se for 'true' o objeto pode ser empurrado(uma caixa por
 * exemplo, servindo de obstáculo ao avanço dos inimigos). Caso seja 'false' o
 * player não conseguirá empurrá-lo por se tratar de um objeto fixo ou pesado(um
 * caminhão por exemplo).
 * 
 * @author Tiko
 * 
 */
public class Objects extends Actor {

	/**
	 * Contrutor padrão da classe. Ao instanciar um objeto desta classe é
	 * necessário informar o diretório do arquivo, seu posicionamento nos
	 * atributos x e y, verificar se é um objeto móvel(que pode ser empurrado
	 * pelo personagem) ou não e o atributo scene para que ele seja sobreposto
	 * ao cenário.
	 * 
	 * @param fileName
	 * @param x
	 * @param y
	 * @param isMobile
	 * @param scene
	 */
	public Objects(String fileName, int x, int y, boolean isMobile, Scene scene) {
		super(fileName);
		this.x = x;
		this.y = y;
		this.isMobile = isMobile;
		scene.addOverlay(this);
	}

	/**
	 * Funciona como o construtor anterior, porém deve se informar o número de
	 * frames da imagem para realizar a animação. O fogo é um exemplo que pode
	 * ser implementado com este método.
	 * 
	 * *Observação: O fogo deve ter valor <b>false</b> para o atributo isMobile.
	 * 
	 * @param fileName
	 * @param x
	 * @param y
	 * @param isMobile
	 * @param scene
	 * @param numFrames
	 */
	public Objects(String fileName, int x, int y, boolean isMobile,
			Scene scene, int numFrames) {
		super(fileName, numFrames);
		this.x = x;
		this.y = y;
		this.isMobile = isMobile;
		scene.addOverlay(this);
	}

	public boolean isMobile;

	/**
	 * Este método é IMPORTANTÍSSIMO. Ele ajusta o objeto na tela, define a
	 * fisica com npc's e player, física com tiles do cenário e movimentação
	 * caso ele seja "empurrável". Ao instanciar um objeto desta classe, é
	 * necessário usar este método dentro do loop do game para as constantes
	 * verificações que são necessárias.
	 * 
	 * Observação: deve ser usado quando houver inimigos no cenário. Caso
	 * contrário, use o método addObjects que não possui npc como parâmetro.
	 * 
	 * @param player
	 * @param npc
	 * @param scene
	 * @param window
	 * @param pathControl
	 */
	public void addObject(Player player, NPC[] npc, Scene scene, Window window,
			boolean pathControl) {
		this.x += scene.getXOffset();
		this.y += scene.getYOffset();
		if (pathControl == true) {
			addPhysics(player, npc, scene, window);
		}
	}

	/**
	 * Este método é IMPORTANTÍSSIMO. Ele ajusta o objeto na tela, define a
	 * fisica com players, física com tiles do cenário e movimentação caso ele
	 * seja "empurrável". Ao instanciar um objeto desta classe, é necessário
	 * usar este método dentro do loop do game para as constantes verificações
	 * que são necessárias.
	 * 
	 * @param player
	 * @param scene
	 * @param window
	 * @param pathControl
	 */
	public void addObject(Player player, Scene scene, Window window,
			boolean pathControl) {
		this.x += scene.getXOffset();
		this.y += scene.getYOffset();
		if (pathControl == true) {
			addPhysics(player, scene, window);
		}
	}

	/**
	 * Este método adiciona a física entre os objetos físicos(this) e os atores
	 * com os npc's.
	 * 
	 * @param player
	 * @param npc
	 * @param scene
	 * @param window
	 */
	private void addPhysics(Player player, NPC[] npc, Scene scene, Window window) {
		addPhysics(player, scene, window);
		for (int i = 0; i < npc.length; i++) {
			this.collision(npc[i]);
		}
	}

	/**
	 * Este método adiciona a física entre os objetos físicos(this) e os atores
	 * sem os npc's.
	 * 
	 * @param player
	 * @param scene
	 * @param window
	 */
	private void addPhysics(Player player, Scene scene, Window window) {
		this.pathControl(window, scene);
		this.collision(player);
	}

	/**
	 * Invoca os métodos 'physicalHorizontal' e 'physicalVertical' e verifica se
	 * o objeto é móvel ou fixo.
	 * 
	 * @param ator
	 */
	private void collision(Actor ator) {
		byte movement = 0;
		if (isMobile == true) {
			movement = 1;
		} else if (isMobile == false) {
			movement = 0;
		}
		physicalHorizontal(ator, movement);
		physicalVertical(ator, movement);
	}

	/**
	 * Faz com que os npc's e players não "atravessem" este objeto no sentido
	 * vertical.
	 * 
	 * @param ator
	 * @param movement
	 */
	private void physicalVertical(Actor ator, byte movement) {
		if (ator.collided(this)) {
			if (verticalCollision(ator, this)) {
				if (this.y + this.height + ator.getVelocityY()
						- ator.getMovementSpeed() - 1 < ator.y) {
					ator.y = this.y + this.height;
					if (compare(ator) == true) {
						this.y -= movement;
					}
				} else if (this.y > ator.y + ator.height - ator.getVelocityY()
						- ator.getMovementSpeed() - 1) {
					ator.y = this.y - ator.height;
					if (compare(ator) == true) {
						this.y += movement;
					}
				}
			}
		}
	}

	/**
	 * Faz com que os npc's e players não "atravessem" este objeto no sentido
	 * horizontal.
	 * 
	 * @param ator
	 * @param movement
	 */
	private void physicalHorizontal(Actor ator, byte movement) {
		if (ator.collided(this)) {
			if (horizontalCollision(ator, this)) {
				if (this.x + this.width + ator.getVelocityY()
						- ator.getMovementSpeed() - 1 < ator.x) {
					ator.x = this.x + this.width;
					if (compare(ator) == true) {
						this.x -= movement;
					}
				} else if (this.x > ator.x + ator.width
						- ator.getMovementSpeed() - 1) {
					ator.x = this.x - ator.width;
					if (compare(ator) == true) {
						this.x += movement;
					}
				}
			}
		}
	}

	/**
	 * Verifica se o ator é um objeto instanciado pela classe Player ou não para
	 * o jogador poder empurrar os objetos.
	 * 
	 * @param ator
	 * @return boolean
	 */
	private boolean compare(Actor ator) {
		if (ator instanceof Player) {
			return true;
		} else {
			return false;
		}
	}

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

}
