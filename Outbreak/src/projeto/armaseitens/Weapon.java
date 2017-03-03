package projeto.armaseitens;

/**
 * Esta classe cria uma nova arma e imediatamente a adiciona a weaponList da
 * classe Arsenal.
 * 
 * @author Tiko
 * 
 */
public final class Weapon {

	private int damage;
	private int bulletsQuantity;
	private int capacityAmmunition;
	private int ammunitionReserves;
	private String name;
	private String image;
	private String sound;

	/**
	 * Contrutor padrão da classe.
	 * 
	 * @param name
	 * @param damage
	 * @param bulletsQuantity
	 * @param capacityAmmunition
	 * @param ammunitionReserves
	 * @param image
	 * @param sound
	 */
	public Weapon(String name, int damage, int bulletsQuantity,
			int capacityAmmunition, int ammunitionReserves, String image,
			String sound, String description) {
		if (bulletsQuantity <= capacityAmmunition && bulletsQuantity >= 0) {
			this.name = name;
			this.damage = damage;
			this.bulletsQuantity = bulletsQuantity;
			this.capacityAmmunition = capacityAmmunition;
			this.ammunitionReserves = ammunitionReserves;
			this.image = image;
			this.sound = sound;
			WeaponControl.weaponList.add(this);		
		}
	}

	/**
	 * Adiciona munição ao pente de reserva.
	 * 
	 * @param amount
	 */
	public void addAmmunition(int amount) {
		this.ammunitionReserves += amount;
	}

	public int getDamage() {
		return damage;
	}

	public void setDamage(int damage) {
		this.damage = damage;
	}

	public int getBulletsQuantity() {
		return bulletsQuantity;
	}

	public void setBulletsQuantity(int bulletsQuantity) {
		this.bulletsQuantity = bulletsQuantity;
	}

	public int getCapacityAmmunition() {
		return capacityAmmunition;
	}

	public void setCapacityAmmunition(int capacityAmmunition) {
		this.capacityAmmunition = capacityAmmunition;
	}

	public int getAmmunitionReserves() {
		return ammunitionReserves;
	}

	public void setAmmunitionReserves(int ammunitionReserves) {
		this.ammunitionReserves = ammunitionReserves;
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

	public String getSound() {
		return sound;
	}

	public void setSound(String sound) {
		this.sound = sound;
	}

}
