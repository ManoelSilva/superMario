package com.supermario.entidade;

import java.awt.Graphics;
import java.awt.Rectangle;

import com.supermario.Empilhador;
import com.supermario.Id;

public abstract class Entidade {
	public int x, y;
	public int largura, altura;
	public int direcaoDoOlhar = 0; // 0 - Esquerda, 1 - Direita

	public boolean solido;
	public boolean pulando = false;
	public boolean caindo = true;

	public int velX, velY;

	public Id id;

	public double gravidade = 0.0;

	public Empilhador empilhador;

	public Entidade(int x, int y, int largura, int altura, boolean solido, Id id, Empilhador empilhador) {
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
		empilhador.removeEntidade(this);

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

	public Rectangle getLimitesDoTopo() {
		return new Rectangle(getX() + 10, getY(), largura - 20, 5);
	}

	public Rectangle getLimitesInferiores() {
		return new Rectangle(getX() + 10, getY() + altura - 5, largura - 20, 5);
	}

	public Rectangle getLimitesdaEsquerda() {
		return new Rectangle(getX(), getY() + 10, 5, altura - 20);
	}

	public Rectangle getLimitesdaDireita() {
		return new Rectangle(getX() + largura - 5, getY() + 10, 5, altura - 20);
	}
}
