package projeto.armaseitens;

import projeto.configuracoes.TypeItemConfig;
import projeto.controle.URL;

/**
 * Classe responsável por controlar os itens coletados pelo jogador.
 * 
 * @author Luiz Alberto
 * 
 */
public class Item {

	private String name;
	private String image;

	/**
	 * Contrutor padrão da classe
	 * 
	 * @param name
	 * @param image
	 */
	public Item(String name, String image) {
		this.name = name;
		this.image = image;
		ItemControl.itemList.add(this);
	}

	/**
	 * Este método retorna o endereço de imagem da categoria do item.
	 * 
	 * 1 - Arma
	 * 
	 * 2 - Munição
	 * 
	 * 3 - Medicamento
	 * 
	 * 4 - Item
	 * 
	 * @param value
	 * @return String
	 */
	public static String type(int value) {
		if (value == TypeItemConfig.WEAPON) {
			return URL.sprite("categoriaArma.png");
		} else if (value == TypeItemConfig.AMMO) {
			return URL.sprite("categoriaMunicao.png");
		} else if (value == TypeItemConfig.MED_KIT) {
			return URL.sprite("categoriaMedicamento.png");
		} else {
			return URL.sprite("categoriaItem.png");
		}
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

}
