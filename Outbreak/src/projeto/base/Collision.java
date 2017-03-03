package projeto.base;

import java.awt.Point;

/**
 * Classe utilizada para saber se duas GameObjects se colidiram.
 * 
 * @author Tiko
 * 
 */
public class Collision {

	/**
	 * Classe usada para saber se dois GameObjects estão se chocando na tela.
	 * @param min1 ponto superior esquerdo da primeira imagem
	 * @param max1 ponto inferior direito da primeira imagem
	 * @param min2 ponto superior esquerdo da segunda imagem
	 * @param max2 ponto inferior direito da segunda imagem
	 * @return boolean - returna true se chocarem e false caso contrário
	 */
	public static boolean collided(Point min1, Point max1, Point min2,
			Point max2) {
		if (min1.x >= max2.x || max1.x <= min2.x) {
			return false;
		}
		if (min1.y >= max2.y || max1.y <= min2.y) {
			return false;
		}
		return true;
	}
	
	/**
	 * Método estático usado para saber se duas GameObjects se chocaram.
	 * @param obj1 GameObject de origem
	 * @param obj2 GameObject alvo
	 * @return retorna true quando estão se chocando e false caso contrário
	 */
	public static boolean collided(GameObject obj1, GameObject obj2){
		Point minObj1 = new Point((int) obj1.x, (int)obj1.y);
		Point maxObj1 = new Point((int)(obj1.x + obj1.width), (int)(obj1.y + obj1.height));
		
		Point minObj2 = new Point((int) obj2.x, (int)obj2.y);
		Point maxObj2 = new Point((int) (obj2.x + obj2.width), (int)(obj2.y + obj2.height));
		
		return (Collision.collided(minObj1, maxObj1, minObj2, maxObj2));
	}
}
