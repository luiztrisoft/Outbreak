package main;

import exemplos.CenarioModelo;
import exemplos.SegundoCenario;
import outbreak.AcessoCidadeCenarioFinal;
import outbreak.Cabana;
import outbreak.Forest;
import outbreak.Graveyard;
import outbreak.Hospital1F;
import outbreak.OakHillStreets;
import outbreak.OakHillStreets2;
import outbreak.ResidentEvil3;
import outbreak.Sewer;
import projeto.armaseitens.ItemControl;
import projeto.base.Window;
import projeto.configuracoes.GameConfig;
import projeto.telas.FirstScreen;
import testes.AmbienteTeste;

public class Outbreak {

	public static void main(String[] args) {
		new Outbreak();
		// Outbreak exe = new Outbreak();
		// exe.initialWeapon();
		// exe.telaPrincipal();
	}

	public Outbreak() {
		Window window = new Window(GameConfig.WIDTH, GameConfig.HEIGHT);
		ItemControl.addHandgun();
		//FirstScreen.method(window);
		
		//new Graveyard(300, 300, window);

		// new Cabana(200, 320, window);
		// new AcessoCidadeCenarioFinal(600, 630, window);
		
		//new AmbienteTeste(100,100, window);
		
		// new Sewer(0, 350, window);
		// new Forest(3400, 250, window);
		// new Sewer(0, 350, window);

		// criar um bueiro de acesso aqui
		// new OakHillStreets(400, 1400, window);
		 //new OakHillStreets(10900, 700, window);
		
		
		//ULTIMA PORTA DO CENARIO
		//new OakHillStreets(9_900, 400, window);
		//new AcessoCidadeCenarioFinal(100, 600, window);
		new OakHillStreets2(500, 500, window);


		// TODO execução principal
		// FirstScreen.method(window);

		 //new ResidentEvil3(100, 250, window);
		//new Hospital1F(550, 600, window);
	}

	// private void initialWeapon() {
	// ItemControl.addHandgun();
	// }

	// private void telaPrincipal() {
	// Window window = new Window(GameConfig.WIDTH, GameConfig.HEIGHT);
	//
	// Velocidade.player();
	//
	// FirstScreen.method(window);// Trisoft.trisoft(window);
	// new CenarioModelo(180, 460, window);
	// new Cabana(200, 320, window);
	// new OakHillStreets(600, 630, window);//
	// new Forest(6850, 1800, window); //porta da cabana
	// new OakHillStreets(1500, 1400, window);// 6440, 380
	// new LojaConveniencia(120, 420, window);// conveniência do posto
	// new Delegacia(600, 200, window);
	// new CorredorDelegacia(50, 300, window);
	// new CenarioTeste(200, 200, window);
	// new OakHillStreets(2250, 1150, window);
	// }
}
