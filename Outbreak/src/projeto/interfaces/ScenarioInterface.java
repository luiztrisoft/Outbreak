package projeto.interfaces;

import projeto.base.Window;

/**
 * Esta interface define os métodos que todos os cenários deverão utilizar.
 * 
 * @author Luiz Alberto
 * 
 */
public interface ScenarioInterface {

	/*
	 * ========================================================
	 * 
	 * CRIAÇÃO
	 * 
	 * Os métodos abaixo vão dentro do construtor e servem para instanciação dos
	 * objetos principais como o player, NPCs, objetos, músicas, cenários e
	 * controle de classes.
	 * 
	 * Ainda dentro do construtor deverá haver um 'while' logo abaixo destes
	 * métodos que controlará o loop do jogo.
	 * 
	 * ========================================================
	 */

	/**
	 * Tema de fundo do cenário.
	 *
	 * @param audio
	 */
	public void scenarioMusic(String audio);

	/**
	 * Base de qualquer cenário.
	 * 
	 * @param previousScreen
	 */
	public void classControl(Window previousScreen);

	/**
	 * Adiciona arquivo de cenário.
	 *
	 * @param file
	 */
	public void scene(String file);

	/**
	 * Cria os itens
	 */
	public void createItem();

	/**
	 * instancia objetos como caixas, carros, fogo etc.
	 */
	public void createObjects();

	/*
	 * deve ser invocado pelo método <b>createObjects</b> para cada objeto
	 * instanciado.
	 * 
	 * @param fileName
	 * 
	 * @param x
	 * 
	 * @param y
	 * 
	 * @param isMobile
	 * 
	 * @param numFrames
	 */
	// public Objects createObjects( String fileName, int x, int y,
	// boolean isMobile, int numFrames);

	/**
	 * Cria o personagem.
	 * 
	 * @param x
	 * @param y
	 */
	public void createPlayer(double x, double y);

	/**
	 * Cria um grupo de inimigos.
	 * 
	 * @param npc
	 * @param numberOfEnemies
	 */
	public void createEnemie();

	/**
	 * Controla a chuva no cenário.
	 * 
	 * @param isRainy
	 */
	public void isRainy(boolean isRainy);

	/*
	 * ========================================================
	 * 
	 * LOOP CONTROLE
	 * 
	 * Todos os métodos abaixo vão dentro do loop do jogo no construor da
	 * classe.
	 * 
	 * ========================================================
	 */

	/**
	 * Pinta a tela e controla a câmera
	 */
	public void drawFrame();

	/**
	 * Pinta o mapa
	 */
	public void drawMap();

	/**
	 * Controle de personagens.
	 */
	public void characterControl();

	/**
	 * Controle de física. Obs.: portas devem ser <b>false</b>
	 */
	public void addObjects();

	/**
	 * Chama o método objectAnimation(Objects object).
	 */
	public void objectAnimation();

	/*
	 * animação de objetos. Ex.: fogo.
	 * 
	 * @param object
	 */
	// public void objectAnimation(Objects object);

	/**
	 * Controle de itens. Para cada item no cenário o método
	 * <b>itemControl(ItemControl itemControl, boolean item)</b> deve ser
	 * invocado para controle dos respectivos itens.
	 * 
	 * Ele irá manter o item fixo no mapa(sem deslocar junto com o movimento do
	 * jogador), removê-lo ao coletá-lo e deixar seu valor como 'true' na classe
	 * EventConfig
	 */
	public void addItem();

	/*
	 * <b>addItem(ItemControl itemControl, boolean item)</b> deve ser invocado
	 * pelo <b>addItem()</b>.
	 * 
	 * @param itemControl
	 * 
	 * @param item
	 */
	// public void addItem(ItemControl itemControl, boolean item);

	/**
	 * Controle de itens coletados
	 */
	public void collectedItem();

	/**
	 * Pinta a chuva na tela.
	 *
	 * @param i
	 */
	public void climaticControl(int i);

	/**
	 * Controle de eventos(lógica). Nas classes que implementam
	 * <b>ScenarioInterface</b> o método events deverá conter dentro dele outros
	 * métodos pertinetes aquele cenário.
	 * 
	 * Ex.: colidirPorta(); colidirCaixa();
	 */
	public void events();

}