package com.supermario;

import com.supermario.entidade.Entidade;

public class Camera {
	public int x, y;

	public void tick(Entidade jogador) {
		setX(-jogador.getX() + Game.LARGURA / 2);
		setY(-jogador.getY() + Game.ALTURA / 2);
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

}
