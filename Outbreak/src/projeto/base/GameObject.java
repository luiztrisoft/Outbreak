package projeto.base;

/**
 * A classe mais básica presente no projeto. Esta classe serve de base para
 * quase todas as classes.
 * 
 * @author Tiko
 * 
 */
public class GameObject {

	/**
	 * A posição da imagem na tela. Eixo x (horizontal). Quando usado para
	 * desenhar, ela é convertida para inteiro
	 */
	public double x;

	/**
	 * A posição da imagem na tela. Eixo y (vertical). Quando usado para
	 * desenhar, ela é convertida para inteiro
	 */
	public double y;

	/**
	 * A largura em pixels da imagem.
	 */
	public int width;

	/**
	 * A altura em pixels da imagem.
	 */
	public int height;

	/**
	 * cria um GameObject posicionado em x=0, y=0, com dimensão de largura =0 e
	 * altura =0.
	 */

	protected double rotation = 0;

	public GameObject() {
		this.x = 0;
		this.y = 0;
		this.width = 0;
		this.height = 0;
	}

	/**
	 * Método utilizado para saber se um GameObject colidiu com outro
	 * GameObject.
	 * 
	 * @param GameObject alvo para verificar se houve uma colisão
	 * @return boolean retorna true se colidiram e false se contrário
	 */
	public boolean collided(GameObject obj) {
		return Collision.collided(this, obj);
	}
}
