package projeto.base;

/**
 * Classe responsável por controlar todas as ações e comportamentos de sprite.
 * 
 * @author Tiko
 * 
 */
public class Sprite extends Animation {

	private double mass = 0.5;
	private double friction = 0.5;
	private double restitution = 0.5;
	@SuppressWarnings("unused")
	private float forceX = 0;
	@SuppressWarnings("unused")
	private float forceY = 0;

	private double velocityY = 0;

	/**
	 * Construtor da classe. Cria uma classe com um quadro.
	 * 
	 * @param fileName
	 *            caminho do arquivo
	 */
	public Sprite(String fileName) {
		this(fileName, 1);
	}

	/**
	 * 
	 * 
	 * Construtor da classe.
	 * 
	 * @param fileName
	 *            caminho do arquivo
	 * @param numFrames
	 *            numero de quadros
	 */
	public Sprite(String fileName, int numFrames) {
		super(fileName, numFrames);
		velocityY = 0;
	}

	/**
	 * Método utilizado para mover o sprite no eixo x. As chaves utilizadas para
	 * move-lo são LEFT_KEY e RIGHT_KEY.
	 * 
	 * @param velocity
	 *            velocidade de locomoção em pixels
	 */
	public void moveX(double velocity) {
		moveX(Keyboard.LEFT_KEY, Keyboard.RIGHT_KEY, velocity);
	}

	/**
	 * Método usado para mover o sprite pelo eixo x.
	 * 
	 * @param leftKey
	 *            chave para mover sprite para esquerda
	 * @param rightKey
	 *            chave para mover sprite para direita
	 * @param velocity
	 *            velocidade de locomoção em pixels
	 */
	public void moveX(int leftKey, int rightKey, double velocity) {
		Keyboard keyboard = Window.getInstance().getKeyboard();
		if (keyboard.keyDown(leftKey) && this.x > 1) {
			this.x -= velocity;
		}
		if (keyboard.keyDown(rightKey) && this.x + this.width < Window.getInstance().getWidth()) {
			this.x += velocity;
		}
	}

	/**
	 * Método usado para mover o sprite pelo eixo y. As chaves usadas para mover
	 * o sprite são UP_KEY e DOWN_KEY.
	 * 
	 * @param velocity
	 *            velocidade de locomoção em pixels
	 */
	public void moveY(double velocity) {
		this.moveY(Keyboard.UP_KEY, Keyboard.DOWN_KEY, velocity);
	}

	/**
	 * Método usado para mover o sprite pelo eixo y. As chaves usadas para mover
	 * o sprite são UP_KEY e DOWN_KEY.
	 * 
	 * @param upKey
	 *            chave da tecla para mover sprite para esquerda
	 * @param downKey
	 *            chave da tecla para mover sprite para direita
	 * @param velocity
	 *            velocidade de locomoção em pixels
	 */
	public void moveY(int upKey, int downKey, double velocity) {
		Keyboard keyboard = Window.getInstance().getKeyboard();
		if (keyboard.keyDown(upKey) && this.y > 1) {
			this.y -= velocity;
		}

		if (keyboard.keyDown(downKey) && this.y + this.height < Window.getInstance().getHeight()) {
			this.y += velocity;
		}
	}

	/**
	 * Move o sprite de um ponto para outro.
	 * 
	 * @param x
	 *            ponto de destino no eixo x
	 * @param y
	 *            ponto alvo y no eixo y velocidade de locomoção em pixels
	 * @param velocity
	 */
	public void moveTo(double x, double y, double velocity) {

		if (this.x < x && (this.x + this.width < Window.getInstance().getWidth())) {
			this.x += velocity;
		} else {
			if (this.x > x)
				this.x -= velocity;
		}

		if (this.y > y) {
			this.y -= velocity;
		} else {
			if (this.y < y)
				this.y += velocity;
		}

		// if (this.y < y){
		// this.y += velocity;
		// }else{
		// this.y -= velocity;
		// }
	}

	/**
	 * Retorna a velocidade de movimento no eixo Y.
	 * 
	 * @return double
	 */
	public void setVelocityY(double velocityY) {
		this.velocityY = velocityY;
	}

	/**
	 * Retorna a velocidade de movimento no eixo Y.
	 * 
	 * @return double
	 */
	public double getVelocityY() {
		return this.velocityY;
	}

	/**
	 * Define o velor da restituição.
	 * 
	 * @param restitution
	 */
	public void setRestitution(double restitution) {
		this.restitution = restitution;
	}

	/**
	 * Retorna o valor usado para restituição.
	 * 
	 * @return double
	 */
	public double getRestitution() {
		return restitution;
	}

	/**
	 * Define o valor para rotação.
	 * 
	 * @param rotation
	 */
	public void setRotation(double rotation) {
		this.rotation = -rotation;
	}

	/**
	 * Retorna o valor usado para rotação.
	 * 
	 * @return double
	 */
	public double getRotation() {
		return rotation;
	}

	/**
	 * Define o valor de atrito.
	 * 
	 * @param friction
	 */
	public void setFriction(double friction) {
		this.friction = friction;
	}

	/**
	 * Retorna o valor usado para atrito.
	 * 
	 * @return double
	 */
	public double getFriction() {
		return friction;
	}

	/**
	 * Define o valor para massa.
	 * 
	 * @param mass
	 */
	public void setMass(double mass) {
		this.mass = mass;
	}

	/**
	 * retorna o valor usado para massa.
	 * 
	 * @return double
	 */
	public double getMass() {
		return mass;
	}

	/**
	 * Define corpo.
	 * 
	 * @param b
	 */
	// public void setBody(Body b) {
	// body = b;
	// }

	/**
	 * Retorna corpo.
	 * 
	 * @return Body
	 */
	// public Body getBody() {
	// return body;
	// }

	// private void setX(double x,float vel){
	//
	// float newX = (float) Physics.pixelsToMeterX(x+this.width/2);
	// float newY = (float) Physics.pixelsToMeterY(y+this.height/2);
	//
	// Vec2 vec = new Vec2(newX+vel,newY);
	// body.setXForm(vec, (float)this.rotation);
	// this.x=x;
	// }
	// private void setY(double y,float vel){
	//
	// float newX = (float) Physics.pixelsToMeterX(x+this.width/2);
	// float newY = (float) Physics.pixelsToMeterY(y+this.height/2);
	//
	// Vec2 vec = new Vec2(newX,newY+vel);
	// body.setXForm(vec, (float)this.rotation);
	// this.y=y;
	// }
	// private void setXY(double x, double y, float velX, float velY){
	//
	// float newX = (float) Physics.pixelsToMeterX(x+this.width/2);
	// float newY = (float) Physics.pixelsToMeterY(y+this.height/2);
	//
	// Vec2 vec = new Vec2(newX+velX,newY+velY);
	// body.setXForm(vec, (float)this.rotation);
	// this.x=x;
	// this.y=y;
	//
	// }

	/**
	 * Define a posição do eixo X.
	 * 
	 * @param x
	 */
	// public void setX(double x) {
	// if (body == null) {
	// this.x = x;
	// } else {
	// float newX = (float) Physics.pixelsToMeterX(x + this.width / 2);// centro
	// // do
	// // sprite(this.width/2)
	// float newY = (float) Physics.pixelsToMeterY(y + this.height / 2);
	//
	// Vec2 vec = new Vec2(newX, newY);
	// body.setXForm(vec, (float) this.rotation);
	// this.x = x;
	// }
	// }

	/**
	 * Retorna a posição do eixo X
	 * 
	 * @return double
	 */
	public double getX() {
		return this.x;
	}

	/**
	 * Define a posição do eixo Y
	 * 
	 * @param y
	 */
	// public void setY(double y) {
	// if (body == null)
	//
	// this.y = y;
	//
	// else {
	//
	// float newX = (float) Physics.pixelsToMeterX(x + this.width / 2);
	// float newY = (float) Physics.pixelsToMeterY(y + this.height / 2);
	//
	// Vec2 vec = new Vec2(newX, newY);
	// body.setXForm(vec, (float) this.rotation);
	// this.y = y;
	//
	// }
	// }
	//
	/**
	 * retorna a posição do eixo Y
	 * 
	 * @return double
	 */
	public double getY() {
		return this.y;
	}

	/**
	 * Define todos os atributos.
	 * 
	 * @param mass
	 * @param friction
	 * @param restituion
	 * @param rotation
	 */
	public void setAllAttributes(double mass, double friction, double restituion, double rotation) {
		this.mass = mass;
		this.friction = friction;
		this.restitution = restituion;
		this.rotation = rotation;
	}

}