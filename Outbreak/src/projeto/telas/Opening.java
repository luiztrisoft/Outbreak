package projeto.telas;

import java.awt.Color;
import java.awt.Font;

import outbreak.Cabana;

import projeto.base.GameImage;
import projeto.base.Keyboard;
import projeto.base.Window;
import projeto.controle.URL;
import projeto.som.SoundController;
import exemplos.CenarioModelo;

/**
 * Esta classe é responsável pela abertura do jogo. Aqui é contada a história do
 * jogo e logo em seguida a história na visão do jogador.
 * 
 * @author Luiz Alberto
 * 
 */
public class Opening {

	private static Font font;

	public static void open(Window previousScreen) {
		font = new Font("Consolas", Font.TRUETYPE_FONT, 20);
		Window window = previousScreen;
		GameImage teladeFundo = new GameImage(URL.sprite("abertura.png"));
		Keyboard keyboard = window.getKeyboard();
		boolean exec = true;
		int i = 0;
		Color color = Color.WHITE;

		boolean music = false;

		while (exec) {
			window.clear(Color.BLACK);
			teladeFundo.draw();

			switch (i) {
			case 0:
				window.drawText(" Uma instalação de mineração na cidade de Oak Hill explodiu e  poucos", 10, 20, color, font);
				window.drawText(" trabalhadores sobreviveram aos escombros. Depois de um tempo", 10, 40, color, font);
				window.drawText(" conseguiram remover parte do entulho, mas os corpos que restaram", 10, 60, color, font);
				window.drawText(" não foram encontrados, acredita-se que sucumbiram muitos metros abaixo", 10, 80, color, font);
				window.drawText(" da mina em grandes fendas que se abriram no local.", 10, 100, color, font);

				window.drawText(" Após o incidente com a mina, os sobreviventes aparentemente contrairam", 10, 130, color, font);
				window.drawText(" algum tipo de infecção ainda não catalogada pela ciência. Estes", 10, 150, color, font);
				window.drawText(" sobreviventes foram então hospitalizados e a medida que iam melhorando,", 10, 170, color, font);
				window.drawText(" recebiam alta, afinal, ainda não havia um tratamento eficaz contra a", 10, 190, color, font);
				window.drawText(" nova doença. A alta destes pacientes pode ter culminado com o fim de", 10, 210, color, font);
				window.drawText(" Oak Hill. demorou cerca de três meses até os sintomas começarem a", 10, 230, color, font);
				window.drawText(" evoluir.", 10, 250, color, font);

				window.drawText(" Grande parte da cidade foi interditada e está em quarentena sob ordem", 10, 280, color, font);
				window.drawText(" do governo onde ninguém entra e ninguém sai. O exército construiu", 10, 300, color, font);
				window.drawText(" barricadas impedindo os infectados de escaparem. Eles adquiriram um", 10, 320, color, font);
				window.drawText(" comportamento extremamente hostil e violento. Quem não foi infectado ", 10, 340, color, font);
				window.drawText(" está escondido e ao mesmo tempo com medo de sair de seus lugares", 10, 360, color, font);
				window.drawText(" aparentemente seguros. A polícia local pouco ou nada pôde fazer contra", 10, 380, color, font);
				window.drawText(" os infectados que avançaram destruindo o que houvesse pela frente.", 10, 400, color, font);

				window.drawText(" O problema é que não existem somente pessoas infectadas. Há rumores de", 10, 430, color, font);
				window.drawText(" criaturas estranhas circulando pelos locais menos acessíveis como", 10, 450, color, font);
				window.drawText(" becos e vielas da cidade causando mais pânico, mas como foi dito, não", 10, 470, color, font);
				window.drawText(" passam de rumores, afinal, os que afirmam que viram estão sob forte", 10, 490, color, font);
				window.drawText(" situação de stress e medo.", 10, 510, color, font);
				break;

			case 1:
				window.drawText(" O agente causador do problema era facilmente dispersável e se espalhou", 10, 20, color, font);
				window.drawText(" ao entrar no sistema de tratamento de água municipal. A infecção era ", 10, 40, color, font);
				window.drawText(" generalizada e ao se darem conta, tudo que os cientistas e secretários", 10, 60, color, font);
				window.drawText(" fizeram foi discutir. Sabíamos da sua existência mas não do que era", 10, 80, color, font);
				window.drawText(" capaz ou como controlá-lo.", 10, 100, color, font);

				window.drawText(" Era tarde demais. Os antigos cidadãos da cidade estavam agora vagando", 10, 130, color, font);
				window.drawText(" sem consciência pelas ruas, procurando por carne, para saciar sua", 10, 150, color, font);
				window.drawText(" necessidade mais básica, já que não lhes restou quaisquer resquícios", 10, 170, color, font);
				window.drawText(" de inteligência. Não havia outra escolha senão deixar a cidade o mais", 10, 190, color, font);
				window.drawText(" rápido possível.", 10, 210, color, font);

				window.drawText(" Ou pelo menos tentar...", 10, 260, color, font);
				break;

			case 2:
				SoundController.stopMusic();
				// new CenarioModelo(10, 350, window);
				new Cabana(80, 160, window);
				break;
			}

			window.display();

			if (music == false) {
				music = true;
				try {
					Thread.sleep(2000);
				} catch (Exception e) {
				}
				SoundController.playMusic(URL.audio("herdetermination.mid"));
			}

			if (keyboard.keyDown(Keyboard.ENTER_KEY)) {
				i++;
			}
		}
	}

}
