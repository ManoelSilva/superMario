package com.supermario.ladrilho;

import java.awt.Graphics;
import java.awt.Rectangle;

import com.supermario.Empilhador;
import com.supermario.Id;

public abstract class Ladrilho {
	public int x, y;
	public int largura, altura;

	public boolean solido;

	public int velX, velY;

	public Id id;

	public Empilhador empilhador;

	public Ladrilho(int x, int y, int largura, int altura, boolean solido, Id id, Empilhador empilhador) {
		this.x = x;
		this.y = y;
		this.largura = largura;
		this.altura = altura;
		this.solido = solido;
		this.id = id;
		this.empilhador = empilhador;
	}

	public abstract void render(Graphics g);

	public abstract void tick();

	public void morte() {
		empilhador.removeLadrilho(this);
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

	public Id getId() {
		return id;
	}

	public boolean isSolido() {
		return solido;
	}

	public void setVelX(int velX) {
		this.velX = velX;
	}

	public void setVelY(int velY) {
		this.velY = velY;
	}

	public Rectangle getLimites() {
		return new Rectangle(getX(), getY(), largura, altura);
	}
}
