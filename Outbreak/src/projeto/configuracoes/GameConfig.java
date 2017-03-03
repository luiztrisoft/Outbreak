package projeto.configuracoes;

/**
 * Esta classe cont�m os atributos de configura��o geral do jogo.
 * 
 * @author Luiz Alberto
 * 
 */
public class GameConfig {

	public static final int WIDTH = 800;
	public static final int HEIGHT = 600;

	public static double level = 1;
	
	public static void setLevel(int level){
		GameConfig.level = level;
	}

}
