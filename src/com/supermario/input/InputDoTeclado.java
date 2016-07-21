package com.supermario.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import com.supermario.Game;
import com.supermario.entidade.Entidade;

public class InputDoTeclado implements KeyListener {

	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		for (Entidade ent : Game.empilhador.entidade) {
			switch (key) {
			case KeyEvent.VK_W:
				if (!ent.pulando) {
					ent.pulando = true;
					ent.gravidade = 10.0;
				}
				break;
			case KeyEvent.VK_A:
				ent.setVelX(-5);
				ent.direcaoDoOlhar = 0;
				break;
			case KeyEvent.VK_D:
				ent.setVelX(5);
				ent.direcaoDoOlhar = 1;
				break;
			case KeyEvent.VK_UP:
				if (!ent.pulando) {
					ent.pulando = true;
					ent.gravidade = 10.0;
				}
				break;
			case KeyEvent.VK_LEFT:
				ent.setVelX(-5);
				ent.direcaoDoOlhar = 0;
				break;
			case KeyEvent.VK_RIGHT:
				ent.setVelX(5);
				ent.direcaoDoOlhar = 1;
				break;

			}
		}

	}

	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		for (Entidade ent : Game.empilhador.entidade) {
			switch (key) {
			case KeyEvent.VK_W:
				ent.setVelY(0);
				break;
			case KeyEvent.VK_S:
				ent.setVelY(0);
				break;
			case KeyEvent.VK_A:
				ent.setVelX(0);
				break;
			case KeyEvent.VK_D:
				ent.setVelX(0);
				break;
			case KeyEvent.VK_UP:
				ent.setVelY(0);
				break;
			case KeyEvent.VK_DOWN:
				ent.setVelY(0);
				break;
			case KeyEvent.VK_LEFT:
				ent.setVelX(0);
				break;
			case KeyEvent.VK_RIGHT:
				ent.setVelX(0);
				break;
			}
		}
	}

	public void keyTyped(KeyEvent e) {

	}

}
