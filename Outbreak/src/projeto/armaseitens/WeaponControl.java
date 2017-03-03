package projeto.armaseitens;

import java.util.ArrayList;
import java.util.List;

import projeto.som.SoundController;

/**
 * Esta classe controla uma lista est�tica que guarda objetos da classe Arma
 * mantendo todas as suas informa��es como dano, quantidade de muni��o, muni��o
 * reserva etc.
 * 
 * @author Luiz Alberto
 * 
 */
public class WeaponControl {

	private static byte index = 0;
	public static List<Weapon> weaponList = new ArrayList<>();

	public static int getIndex() {
		return index;
	}

	/**
	 * M�todo que verifica se a arma possui pelo menos uma bala para poder
	 * realizar um disparo.
	 * 
	 * @return boolean
	 */
	public boolean shoot() {
		if (weaponList.get(index).getBulletsQuantity() >= 1) {
			weaponList.get(index).setBulletsQuantity(
					weaponList.get(index).getBulletsQuantity() - 1);
			return true;
		} else {
			return false;
		}
	}

	/**
	 * M�todo que realiza o recarregamento da arma indexada caso possua muni��o
	 * reserva.
	 * 
	 */
	public void reload() {
		int difference;
		int bulletsQuantity = weaponList.get(index).getBulletsQuantity();
		int capacityAmmunition = weaponList.get(index).getCapacityAmmunition();
		int ammunitionReserves = weaponList.get(index).getAmmunitionReserves();
		if (bulletsQuantity < capacityAmmunition && bulletsQuantity >= 0) {
			difference = capacityAmmunition - bulletsQuantity;
			if (ammunitionReserves - difference >= 0) {
				weaponList.get(index).setAmmunitionReserves(
						ammunitionReserves - difference);
				weaponList.get(index).setBulletsQuantity(
						bulletsQuantity + difference);
				SoundController.reloading();
			} else {
				weaponList.get(index).setBulletsQuantity(
						bulletsQuantity + ammunitionReserves);
				weaponList.get(index).setAmmunitionReserves(0);
				SoundController.reloading();
			}
		}
	}

	/**
	 * M�todo que troca de armas modificando o indice que varia de acordo com o
	 * tamanho da lista de armas. Se o index for igual ao tamanho da lista, o
	 * index recebe 0 novamente. Assim, conforme o jogador aperta o bot�o pra
	 * trocar de arma, ele est� modificando o index usado na lista que volta ao
	 * primeiro item quando o tamanho da lista for alcan�ado.
	 */
	public void change() {
		if (index == -1) {
			index = 0;
		}
		if (index == weaponList.size() - 1) {
			index = 0;
		} else {
			index++;
		}
	}
}
