package projeto.controle;

import projeto.atores.NPC;
import projeto.atores.Player;
import projeto.base.GameObject;
import projeto.base.Scene;
import projeto.base.TileInfo;
import projeto.base.Window;
import projeto.som.SoundController;

/**
 * Esta classe é responsável por controlar as funções principais no cenário de
 * jogo.
 * 
 * @author Luiz Alberto
 * 
 */
public class Control {

	/**
	 * Este método trata a colisão de um GameObject que pode ser um player ou
	 * npc com os tiles determinados a serem físicos no cenário.
	 * 
	 * @param object
	 * @param tile
	 * @return boolean
	 */
	public boolean tileCollision(GameObject object, TileInfo tile) {
		if ((tile.id >= 50 && tile.id < 500) && object.collided(tile)) {
			return true;
		}
		return false;
	}

	/**
	 * Controle do loop principal do game. Serve para realizar a função de
	 * pause.
	 * 
	 * @param loop
	 * @return boolean
	 */
	public boolean loopControl(boolean loop) {
		if (loop == false) {
			loop = true;
			SoundController.escMenu();
			return loop;
		} else {
			loop = false;
			SoundController.menu();
			return loop;
		}
	}

	/**
	 * Método que é responsável por realizar as ações dos NPC's inimigos como
	 * mover-se, colidir com outro sem se sobreporem, receber dano por arma e
	 * colisão com elementos do cenário.
	 * 
	 * @param npc
	 * @param player
	 * @param window
	 * @param scene
	 */
	public void npcControl(NPC npc[], Player player, Window window, Scene scene) {
		for (int i = 0; i < npc.length; i++) {
			
			npc[i].pursuitPlayer(player);
			npc[i].collidedOtherNPC(npc);
			npc[i].pathControl(window, scene);
		}
	}
}
