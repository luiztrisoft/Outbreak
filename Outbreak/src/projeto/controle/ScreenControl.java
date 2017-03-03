package projeto.controle;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.text.DecimalFormat;

import projeto.armaseitens.Item;
import projeto.armaseitens.ItemControl;
import projeto.armaseitens.WeaponControl;
import projeto.atores.NPC;
import projeto.atores.Player;
import projeto.base.GameImage;
import projeto.base.Keyboard;
import projeto.base.Sound;
import projeto.base.Window;
import projeto.configuracoes.EventConfig;
import projeto.configuracoes.GameConfig;

/**
 * Esta classe controla os itens da tela como os gadgets de armas, medicamentos,
 * nível de infecção etc.
 * 
 * @author Luiz Alberto
 * 
 */

public class ScreenControl extends Control {

	private static Color infectionColor;
	private int gadgetX = 510;
	private int gadgetY = 50;
	private GameImage medkit = new GameImage(URL.sprite("medkit.png"));
	private DecimalFormat df = new DecimalFormat("0.00");
	private final Font FONT = new Font("Consolas", 1, 30);
	public static final Font COURIER_NEW = new Font("Courier New", 1, 20);
	private GameImage weapon;
	private GameImage image;
	private GameImage face;
	// private GameImage handgun = new GameImage(DirConfig.HANDGUN);
	private static final String CLEAN = "";
	private static String message = CLEAN;

	/**
	 * Contrutor padrão da classe.
	 */
	public ScreenControl() {
		face = new GameImage(URL.sprite("facejill.png"));
		face.x = 0;
		face.y = 0;
	}

	/**
	 * Atualiza e deixa a tela em modo full screen. Controla o time delay do
	 * game.
	 * 
	 * @param window
	 */
	public void screenControl(Window window) {
		// window.setFullScreen();
		window.delay(10);
		window.update();
	}

	/*
	 * =======================================================
	 * 
	 * CONTROLE DE MENSAGENS
	 * 
	 * =======================================================
	 */
	/**
	 * Este método é usado para escrever mensagem no rodapé da tela. Normalmente
	 * para dar algum aviso ao jogador ou então serve para realização de
	 * diálogos.
	 * 
	 * @param text
	 */
	public void setMessage(String text) {
		message = text;
	}

	/**
	 * Este método limpa o que estiver escrito na mensagem
	 */
	public void cleanMessage() {
		message = CLEAN;
	}

	/**
	 * Este método além de mostrar ao jogador que ele coletou um item, ele
	 * adiciona a lista de itens.
	 * 
	 * OBERVAÇÃO: Este método deve ser utilizado quando for para adicionar itens
	 * a lista. Para medicamentos, armas e munições invoque o método
	 * obtainedItens diretamente.
	 * 
	 * @param itemName
	 * @param typeItem
	 * @param window
	 * @param microImageName
	 */
	public void obtainedAndAddItem(String itemName, int typeItem, Window window, String microImageName) {
		obtainedItem(itemName, typeItem, window);
		new Item(itemName, microImageName);
	}

	/**
	 * Exibe uma mensagem que informa ao jogador que ele coletou um item.
	 * 
	 * @param itemName
	 * @param typeItem
	 * @param window
	 */
	public void obtainedItem(String itemName, int typeItem, Window window) {
		GameImage image;
		Keyboard keyboard = window.getKeyboard();
		keyboard.addKey(KeyEvent.VK_ESCAPE);

		boolean esc = false;
		new Sound(URL.audio("guitar.wav")).play();

		while (esc == false) {
			new GameImage(URL.sprite("screen.png")).draw();
			image = new GameImage(Item.type(typeItem));
			image.x = 300;
			image.y = 220;
			image.draw();

			window.drawText("Novo item: ", 140, 400, Color.BLACK, FONT);
			window.drawText(itemName, 330, 400, Color.BLUE, FONT);
			window.display();

			if (keyboard.keyDown(Keyboard.ESCAPE_KEY)) {
				esc = true;
			}
		}

	}

	/*
	 * =======================================================
	 * 
	 * CONTROLE DO INVENTÁRIO DE ITENS
	 * 
	 * =======================================================
	 */

	/**
	 * Abre o inventário do personagem.
	 * 
	 * @param window
	 * @param loop
	 * @param player
	 */
	public void inventory(Window window, boolean loop, Player player) {
		if (loop == false) {

			window.clear(Color.BLACK);
			new GameImage(URL.sprite("menu.png")).draw();

			drawHandgun(window);
			gadgetControl(window, player);
			int y = 115;

			for (int i = 0; i < ItemControl.itemList.size(); i++) {
				image = new GameImage(ItemControl.itemList.get(i).getImage());
				image.x = 30;
				image.y = y;
				image.draw((float) 1.0);
				window.drawText(ItemControl.itemList.get(i).getName().toString(), 70, y + 15, Color.WHITE, COURIER_NEW);
				y += 25;
			}
		}
	}

	/**
	 * Pinta a handgun no inventário de itens.
	 * 
	 * @param window
	 */
	private void drawHandgun(Window window) {
		int y = 115;
		int x = 400;

		int list = WeaponControl.weaponList.size();

		for (int i = 0; i < list; i++) {
			weapon = new GameImage(WeaponControl.weaponList.get(i).getImage());
			weapon.x = x;
			weapon.y = y;
			weapon.draw();

			window.drawText(WeaponControl.weaponList.get(i).getName(), x + 120, y + 25, Color.WHITE, COURIER_NEW);
			window.drawText(WeaponControl.weaponList.get(i).getBulletsQuantity() + "", x + 120, y + 45, Color.GREEN, COURIER_NEW);
			window.drawText(WeaponControl.weaponList.get(i).getAmmunitionReserves() + "", x + 170, y + 45, Color.RED, COURIER_NEW);
			y += 70;
		}
	}

	/*
	 * =======================================================
	 * 
	 * CONTROLE DE GADGETS DA TELA
	 * 
	 * Os códigos abaixo criam os objetos estáticos na tela que são o nível de
	 * infecção, a arma e sua munição equivalente e os medicamentos disponíveis.
	 * 
	 * 
	 * =======================================================
	 */
	/**
	 * Este é um dos métodos principais da classe Controle. Ele realiza chamada
	 * a métodos que pintam as armas, munições, pontos de vida(infecção) do
	 * Player e outros atributos que sejam necessários. Deve ser usado pelo
	 * método do inventário pois não mostra o HP do inimigo.
	 * 
	 * @param window
	 * @param player
	 */
	@SuppressWarnings("static-access")
	public void gadgetControl(Window window, Player player) {
		colorControl(player);
		drawPlayer(player, window);
		drawWeapon(window);
		drawMedkit(window);
		drawMessage(window);
		HPBar(100, player.HP, 110, 45, 200, 15, (float) 0.5);

	}

	/**
	 * Este é um dos métodos principais da classe Controle. Ele realiza chamada
	 * a métodos que pintam as armas, munições, pontos de vida(infecção) do
	 * Player e outros atributos que sejam necessários. Deve ser usado pelo
	 * método do cenário pois mostra HP do inimigo.
	 * 
	 * @param window
	 * @param player
	 * @param npc
	 */
	public void gadgetControl(Window window, Player player, NPC[] npc) {
		gadgetControl(window, player);
		for (int i = 0; i < npc.length; i++) {
			npc[i].HpBar();
		}
	}

	/**
	 * Controla a cor do life de acordo com o nível de infecção. Até 50%: verde,
	 * mais de 50% até 80%: amarelo, acima de 80%: vermelho.
	 * 
	 * @param player
	 */
	private void colorControl(Player player) {
		if (player.getInfeccao() <= 50) {
			infectionColor = Color.GREEN;
		} else if (player.getInfeccao() > 50 && player.getInfeccao() <= 80) {
			infectionColor = Color.ORANGE;
		} else {
			infectionColor = Color.RED;
		}
	}

	/**
	 * Este método pinta a arma e a quantidade de munição. Ele ainda verifica
	 * qual arma está sendo manuseada pelo Player.
	 * 
	 * @param window
	 */
	private void drawWeapon(Window window) {
		weapon = new GameImage(WeaponControl.weaponList.get(WeaponControl.getIndex()).getImage());
		weapon.x = 380;
		weapon.y = 10;
		weapon.draw();
		window.drawText("" + WeaponControl.weaponList.get(WeaponControl.getIndex()).getBulletsQuantity(), gadgetX, gadgetY, Color.YELLOW,
				FONT);
		window.drawText("" + WeaponControl.weaponList.get(WeaponControl.getIndex()).getAmmunitionReserves(), gadgetX + 60, gadgetY,
				Color.YELLOW, new Font("Arial", 1, 15));
	}

	/**
	 * Este método pinta o firstAidKit e sua quantidade na tela.
	 * 
	 * @param window
	 */
	private void drawMedkit(Window window) {
		medkit.x = 650;
		medkit.y = 10;
		medkit.draw((float) 0.8);
		window.drawText("X " + EventConfig.firstAidKit, 730, gadgetY, Color.WHITE, FONT);
	}

	/**
	 * Método que pinta as informações do player na tela. Estas informações são
	 * sua foto e o nível de infecção do player.
	 * 
	 * @param player
	 * @param window
	 */
	private void drawPlayer(Player player, Window window) {
		//face.draw();
		// window.drawText("" + df.format(player.getInfeccao()) + " %", 20,
		// gadgetY, infectionColor, FONT);
		window.drawText("" + df.format(player.getInfeccao()) + " %", 110, 25, infectionColor, FONT);
	}

	/**
	 * Pinta mensagens de texto na tela. Esta mensagem pode ser uma conversa
	 * entre personagens, mensagens do jogo para o jogador etc.
	 * 
	 * @param window
	 */
	private void drawMessage(Window window) {
		window.drawText(message, 20, 580, Color.GREEN, COURIER_NEW);
	}

	/**
	 * Este método pinta uma barra semelhante a uma barra de HP na tela. É
	 * necessário informar o valor máximo da barra, o valor restante(HP do
	 * personagem se for o caso, por exemplo) e as coordenadas de posicionamento
	 * X, Y, W e H e por fim a translucidez q ue varia de 0.1 a 1.0.
	 * 
	 * @param maxValue
	 * @param restantValue
	 * @param x
	 * @param y
	 * @param w
	 * @param h
	 * @param f
	 */
	public void HPBar(int maxValue, double restantValue, int x, int y, int w, int h, float f) {
		BufferedImage backBufferedImage = new BufferedImage(GameConfig.WIDTH, GameConfig.HEIGHT, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g = (Graphics2D) Window.instance.getGameGraphics();

		AlphaComposite alpha = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, f);
		g.setComposite(alpha);

		g.drawImage(backBufferedImage, null, 0, 0);

		Graphics2D g2d = (Graphics2D) g.create();
		Graphics2D g2d2 = (Graphics2D) g.create();

		int percentGood = (int) ((restantValue * 100) / maxValue);

		g2d2.setColor(Color.RED);
		g2d2.fillRect(x, y, w, h);

		g2d.setColor(Color.GREEN);
		g2d.fillRect(x, y, (percentGood * 2) / 1, h);

		g2d.dispose();

		alpha = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, (float) 1.0);
		g.setComposite(alpha);
	}
}