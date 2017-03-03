package projeto.atores;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import projeto.base.Scene;
import projeto.base.Window;
import projeto.configuracoes.GameConfig;
import projeto.som.SoundController;

/**
 * Classe responsável pela modelagem dos inimigos do jogo.
 * 
 * @author Luiz Alberto
 * 
 */
public abstract class NPC extends Actor {

	private int maxHP;

	/**
	 * Contrutor padrão da classe.
	 * 
	 * @param fileName
	 * @param numberOfFrames
	 */
	public NPC(String fileName, int numberOfFrames) {
		super(fileName, numberOfFrames);
		this.x = 0;
		this.y = 0;
		maxHP = (int) HP;
	}

	public double HP = 20 + (GameConfig.level * 2);
	public int perceptionRay = 300;
	public double pursuitVelocity = 0.7;

	/*
	 * =========================================================
	 * 
	 * METODOS
	 * 
	 * Aqui são implementadas todas as ações pertinentes ao personagem NÃO
	 * controlado pelo jogador.
	 * 
	 * =========================================================
	 */
	/**
	 * Método que verifica se o NPC está vivo ou não.
	 * 
	 * @return
	 */
	protected boolean isAlive() {
		if (this.isAlive == true) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Este método é utilizado para realizar a perseguição dos inimigos ao
	 * jogador.
	 * 
	 * @param x
	 * @param y
	 */
	public void pursuitPlayer(double x, double y) {

		keyPressed = true;
		if (isAlive() == true) {
			if (x - this.x >= -perceptionRay && x - this.x <= perceptionRay && y - this.y >= -perceptionRay
					&& y - this.y <= perceptionRay) {

				// AUMENTAR O RAIO DE PERCEPÇÃO TESTE
				if (this.perceptionRay < 800) {
					this.perceptionRay = 800;
				}

				// MOVER PARA ESQUERDA
				if (this.x > x && this.y <= y + 50 && this.y >= y - 50) {
					moveTo(x - 100, y, pursuitVelocity);
					if (direction != 1) {
						setSequence(5, 8);
						direction = 1;
					}
					move = true;
				}
				// MOVER PARA DIREITA
				else if (this.x < x && this.y <= y + 50 && this.y >= y - 50) {
					moveTo(x + 100, y, pursuitVelocity);
					if (direction != 2) {
						setSequence(9, 12);
						direction = 2;
					}
					move = true;
				}
				// MOVER PARA CIMA
				else if (this.y > y) {

					// BEGIN:::::
					if (this.x <= x + 250 && this.x >= x - 250) {
						this.y -= pursuitVelocity;
					} else {
						moveTo(x, y, pursuitVelocity);
					}

					// if (this.x > x) {this.x -= pursuitVelocity; }
					// if (this.x < x) {this.x += pursuitVelocity; }

					// END::::

					// moveTo(x, y, pursuitVelocity);
					if (direction != 4) {
						setSequence(13, 16);
						direction = 4;
					}
					move = true;
				}
				// MOVER PARA BAIXO
				else if (this.y < y) {
					moveTo(x, y, pursuitVelocity);
					if (direction != 5) {
						setSequence(1, 4);
						direction = 5;
					}
					move = true;
				} else
					keyPressed = false;
				if (move) {
					update();
					move = false;
				}
			} else {
				// CASO O PLAYER ESTEJA FORA DO RAIO DE PERCEPÇÃO
				if (direction == 1) {
					moveTo(0, getY(), moviment);
					if (direction != 1) {
						setSequence(5, 8);
						direction = 1;
					}
					move = true;
				}
				// MOVER PARA DIREITA
				else if (direction == 2) {
					moveTo(1000, getY(), moviment);
					if (direction != 2) {
						setSequence(9, 12);
						direction = 2;
					}
					move = true;
				}
				// MOVER PARA CIMA
				else if (direction == 4) {
					moveTo(getX(), 0, moviment);
					if (direction != 4) {
						setSequence(13, 16);
						direction = 4;
					}
					move = true;
				}
				// MOVER PARA BAIXO
				else if (direction == 5) {
					moveTo(getX(), 1000, moviment);
					if (direction != 5) {
						setSequence(1, 4);
						direction = 5;
					}
					move = true;
				} else
					keyPressed = false;
				if (move) {
					update();
					move = false;
				}
			}
		}
	}

	public void pursuitPlayer(Player player) {
		
		double x = player.x, y = player.y;

		keyPressed = true;
		if (isAlive() == true) {
			if (x - this.x >= -perceptionRay && x - this.x <= perceptionRay && y - this.y >= -perceptionRay
					&& y - this.y <= perceptionRay) {

				// AUMENTAR O RAIO DE PERCEPÇÃO TESTE
				if (this.perceptionRay < 100) {
					this.perceptionRay = 100;
				}

				// MOVER PARA ESQUERDA
				if (this.x > x && this.y <= y + 50 && this.y >= y - 50) {
					moveTo(x - 100, y, pursuitVelocity);
					if (direction != 1) {
						setSequence(5, 8);
						direction = 1;
					}
					move = true;
				}
				// MOVER PARA DIREITA
				else if (this.x < x && this.y <= y + 50 && this.y >= y - 50) {
					moveTo(x + 100, y, pursuitVelocity);
					if (direction != 2) {
						setSequence(9, 12);
						direction = 2;
					}
					move = true;
				}
				// MOVER PARA CIMA
				else if (this.y > y) {

					// BEGIN:::::
					if (this.x <= x + 250 && this.x >= x - 250) {
						this.y -= pursuitVelocity;
					} else {
						moveTo(x, y, pursuitVelocity);
					}

					// if (this.x > x) {this.x -= pursuitVelocity; }
					// if (this.x < x) {this.x += pursuitVelocity; }

					// END::::

					// moveTo(x, y, pursuitVelocity);
					if (direction != 4) {
						setSequence(13, 16);
						direction = 4;
					}
					move = true;
				}
				// MOVER PARA BAIXO
				else if (this.y < y) {
					moveTo(x, y, pursuitVelocity);
					if (direction != 5) {
						setSequence(1, 4);
						direction = 5;
					}
					move = true;
				} else
					keyPressed = false;
				if (move) {
					update();
					move = false;
				}
			} else {
				// CASO O PLAYER ESTEJA FORA DO RAIO DE PERCEPÇÃO
				
				
				if (direction == 1) {
					//moveTo(0, getY(), 0.1);
					if (direction != 1) {
						setSequence(5, 8);
						direction = 1;
					}
					move = false;
				}
				// MOVER PARA DIREITA
				else if (direction == 2) {
					//moveTo(1000, getY(), 0.1);
					if (direction != 2) {
						setSequence(9, 12);
						direction = 2;
					}
					move = false;
				}
				// MOVER PARA CIMA
				else if (direction == 4) {
					//moveTo(getX(), 0, 0.1);
					if (direction != 4) {
						setSequence(13, 13);
						direction = 4;
					}
					move = false;
				}
				// MOVER PARA BAIXO
				else if (direction == 5) {
					//moveTo(getX(), 1000, 0.1);
					if (direction != 5) {
						setSequence(1, 1);
						direction = 5;
					}
					move = false;
				} else
					keyPressed = false;
				if (move) {
					update();
					move = false;
				}
			}
		}
	}

	/**
	 * Este método verifica quando o NPC possui 0 ou menos lifePoints e mata o
	 * inimigo.
	 * 
	 * @param scene
	 */
	public void dead(Scene scene) {
		if (this.HP <= 0) {
			SoundController.enemyDead();
			this.HP = 0;
			this.powerAttack = 0;
			this.moviment = 0;
			this.direction = 0;
			this.isAlive = false;
			this.move = false;
			this.keyPressed = false;
			this.x = -2000;
			this.y = -2000;
			scene.removeOverlay(this);
		}
	}

	/**
	 * Invoca dois métodos que irão verificar a colisão com outro npc na
	 * horizontal e na vertical.
	 * 
	 * @param npc
	 */
	public void collidedOtherNPC(NPC[] npc) {
		npcCollisionHorizontal(npc);
		npcCollisionVertical(npc);
	}

	/**
	 * Trata a colisão entre npc's na vertical.
	 * 
	 * @param npc
	 */
	private void npcCollisionVertical(NPC[] npc) {
		if (isAlive() == true) {
			for (int two = 0; two < npc.length; two++) {
				if (this.collided(npc[two])) {
					if (verticalCollision(this, npc[two])) {

						if (npc[two].y + npc[two].height + this.getVelocityY() - this.getMovementSpeed() - 1 < this.y) {
							this.y = npc[two].y + npc[two].height;
						} else if (npc[two].y > this.y + this.height - this.getVelocityY() - this.getMovementSpeed() - 1) {
							this.y = npc[two].y - this.height;
						}
					}
				}
			}
		}
	}

	/**
	 * Trata a colisão entre npc's na horizontal.
	 * 
	 * @param npc
	 * @param npc2
	 */
	private void npcCollisionHorizontal(NPC[] npc) {
		if (this.isAlive() == true) {
			for (int two = 0; two < npc.length; two++) {
				if (this.collided(npc[two])) {
					if (horizontalCollision(this, npc[two])) {
						if (npc[two].x + npc[two].width < this.x - this.getMovementSpeed() - 1) {
							this.x = npc[two].x + npc[two].width;
						}
						if (npc[two].x > this.x + this.width - this.getMovementSpeed() - 1) {
							this.x = npc[two].x - this.width;
						}
					}
				}
			}
		}
	}

	/**
	 * Este método pinta a barra de HP do NPC na tela.
	 */
	public void HpBar() {
		BufferedImage backBufferedImage = new BufferedImage(GameConfig.WIDTH, GameConfig.HEIGHT, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2d = (Graphics2D) Window.instance.getGameGraphics();
		g2d.drawImage(backBufferedImage, null, 0, 0);
		drawHpBar(g2d);
	}

	/**
	 * Este método faz a diminuição da barra de HP conforme o inimigo é atacado
	 * pelo jogador.
	 * 
	 * @param g
	 */
	private void drawHpBar(Graphics2D g) {
		Graphics2D g2d = (Graphics2D) g.create();
		int percentGood = (int) ((HP * 100) / maxHP);
		int percentBad = -(percentGood - 100);
		g2d.setColor(Color.GREEN);
		g2d.fillRect((int) this.x + 10, (int) this.y - 5, percentGood / 5, 3);
		g2d.setColor(Color.RED);
		g2d.fillRect((int) (this.x + 10) + (percentGood / 5), (int) this.y - 5, percentBad / 5, 3);
		g2d.dispose();
	}
}
