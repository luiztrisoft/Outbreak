package projeto.interfaces;

import projeto.base.Window;

/**
 * Esta interface define os m�todos que todos os cen�rios dever�o utilizar.
 * 
 * @author Luiz Alberto
 * 
 */
public interface ScenarioInterface {

	/*
	 * ========================================================
	 * 
	 * CRIA��O
	 * 
	 * Os m�todos abaixo v�o dentro do construtor e servem para instancia��o dos
	 * objetos principais como o player, NPCs, objetos, m�sicas, cen�rios e
	 * controle de classes.
	 * 
	 * Ainda dentro do construtor dever� haver um 'while' logo abaixo destes
	 * m�todos que controlar� o loop do jogo.
	 * 
	 * ========================================================
	 */

	/**
	 * Tema de fundo do cen�rio.
	 *
	 * @param audio
	 */
	public void scenarioMusic(String audio);

	/**
	 * Base de qualquer cen�rio.
	 * 
	 * @param previousScreen
	 */
	public void classControl(Window previousScreen);

	/**
	 * Adiciona arquivo de cen�rio.
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
	 * deve ser invocado pelo m�todo <b>createObjects</b> para cada objeto
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
	 * Controla a chuva no cen�rio.
	 * 
	 * @param isRainy
	 */
	public void isRainy(boolean isRainy);

	/*
	 * ========================================================
	 * 
	 * LOOP CONTROLE
	 * 
	 * Todos os m�todos abaixo v�o dentro do loop do jogo no construor da
	 * classe.
	 * 
	 * ========================================================
	 */

	/**
	 * Pinta a tela e controla a c�mera
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
	 * Controle de f�sica. Obs.: portas devem ser <b>false</b>
	 */
	public void addObjects();

	/**
	 * Chama o m�todo objectAnimation(Objects object).
	 */
	public void objectAnimation();

	/*
	 * anima��o de objetos. Ex.: fogo.
	 * 
	 * @param object
	 */
	// public void objectAnimation(Objects object);

	/**
	 * Controle de itens. Para cada item no cen�rio o m�todo
	 * <b>itemControl(ItemControl itemControl, boolean item)</b> deve ser
	 * invocado para controle dos respectivos itens.
	 * 
	 * Ele ir� manter o item fixo no mapa(sem deslocar junto com o movimento do
	 * jogador), remov�-lo ao colet�-lo e deixar seu valor como 'true' na classe
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
	 * Controle de eventos(l�gica). Nas classes que implementam
	 * <b>ScenarioInterface</b> o m�todo events dever� conter dentro dele outros
	 * m�todos pertinetes aquele cen�rio.
	 * 
	 * Ex.: colidirPorta(); colidirCaixa();
	 */
	public void events();

}