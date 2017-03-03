package projeto.atores;

import java.util.Random;

import projeto.controle.URL;
import projeto.enums.Monster;

/**
 * Sub classe de NPC responsável por criar os zumbis.
 * 
 * @author Luiz Alberto
 * 
 */
public class MonsterFactory extends NPC {

	private static String[] npc = { URL.sprite("zumbi 00.png"), URL.sprite("zumbi 01.png"), URL.sprite("zumbi 02.png"),
			URL.sprite("zumbi 03.png"), URL.sprite("zombie.png"), URL.sprite("dog.png"), URL.sprite("nemesis.png"),
			URL.sprite("old zombie.png"), URL.sprite("gray zombie.png") };

	/**
	 * Contrutor da classe.
	 * 
	 * @param index
	 */
	public MonsterFactory(int index) {
		super(npc[index], 16);
	}

	static int qtd;

	/**
	 * Define que tipo de monstro irá retornar
	 * 
	 * @param monstro
	 * @param quantidade
	 * @return MonsterFactory
	 */
	public static MonsterFactory[] getMonstro(Monster monstro, int quantidade) {

		qtd = quantidade;
		switch (monstro) {

		case ZUMBI:
			return criaZumbi();

		case CACHORRO:
			return criaCachorro();

		case NEMESIS:
			return criaNemesis();

		case OLD_ZOMBIE:
			return criaOldZombie();

		case GRAY_ZOMBIE:
			return criaGrayZombie();

		default:
			break;
		}

		return null;

	}

	/**
	 * Retorna um zumbi
	 * 
	 * @return
	 */
	private static MonsterFactory[] criaZumbi() {
		MonsterFactory mf[] = new MonsterFactory[qtd];
		for (int i = 0; i < qtd; i++) {
			mf[i] = new MonsterFactory(new Random().nextInt(4));
			mf[i].HP = 15;
			mf[i].powerAttack = 1;// 0.3;
			mf[i].x = new Random().nextInt(800);
			mf[i].y = new Random().nextInt(600);
			mf[i].perceptionRay = 300;
			mf[i].pursuitVelocity = 0.3;
		}
		return mf;
	}

	/**
	 * Retorna um cachorro
	 * 
	 * @return
	 */
	private static MonsterFactory[] criaCachorro() {
		MonsterFactory mf[] = new MonsterFactory[qtd];
		for (int i = 0; i < qtd; i++) {
			mf[i] = new MonsterFactory(5);
			mf[i].HP = 5;
			mf[i].powerAttack = 5;
			mf[i].x = 100 + new Random().nextInt(700);
			mf[i].y = 100 + new Random().nextInt(500);
			mf[i].perceptionRay = 120;
			mf[i].pursuitVelocity = 6;
		}
		return mf;
	}

	/**
	 * Retorna um nemesis
	 * 
	 * @return
	 */
	private static MonsterFactory[] criaNemesis() {
		MonsterFactory mf[] = new MonsterFactory[qtd];
		for (int i = 0; i < qtd; i++) {
			mf[i] = new MonsterFactory(6);
			mf[i].HP = 150;
			mf[i].powerAttack = 0;
			mf[i].x = new Random().nextInt(800);
			mf[i].y = new Random().nextInt(600);
			mf[i].perceptionRay = 500;
			mf[i].pursuitVelocity = 1;
		}
		return mf;
	}

	private static MonsterFactory[] criaOldZombie() {
		MonsterFactory mf[] = new MonsterFactory[qtd];
		for (int i = 0; i < qtd; i++) {
			mf[i] = new MonsterFactory(7);
			mf[i].HP = 2;
			mf[i].powerAttack = 5;
			mf[i].x = new Random().nextInt(800);
			mf[i].y = new Random().nextInt(600);
			mf[i].perceptionRay = 500;
			mf[i].pursuitVelocity = 1;
		}
		return mf;
	}

	private static MonsterFactory[] criaGrayZombie() {
		MonsterFactory mf[] = new MonsterFactory[qtd];
		for (int i = 0; i < qtd; i++) {
			mf[i] = new MonsterFactory(8);
			mf[i].HP = 2;
			mf[i].powerAttack = 0.1;
			mf[i].x = new Random().nextInt(800);
			mf[i].y = new Random().nextInt(600);
			mf[i].perceptionRay = 500;
			mf[i].pursuitVelocity = 2;
		}
		return mf;
	}

}
