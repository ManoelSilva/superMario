package com.supermario.entidade;

import java.awt.Graphics;

import com.supermario.Empilhador;
import com.supermario.Game;
import com.supermario.Id;
import com.supermario.ladrilho.Ladrilho;

public class Jogador extends Entidade {

	private int frame = 0;
	private int frameDelay = 0;

	private boolean animacao = false;

	public Jogador(int x, int y, int largura, int altura, boolean solido, Id id, Empilhador empilhador) {
		super(x, y, largura, altura, solido, id, empilhador);
	}

	public void render(Graphics g) {
		if (direcaoDoOlhar == 0) {
			g.drawImage(Game.jogador[frame + 4].getBufferedImage(), x, y, largura, altura, null);
		} else if (direcaoDoOlhar == 1) {
			g.drawImage(Game.jogador[frame].getBufferedImage(), x, y, largura, altura, null);
		}
	}

	public void tick() {
		x += velX;
		y += velY;
		/*if (x <= 0)
			x = 0;
		if (y <= 0)
			y = 0;
		if (x + largura >= 1080)
			x = 1080 - largura;*/
		if (y + altura >= 772)
			y = 772 - altura;
		if (velX != 0)
			animacao = true;
		else
			animacao = false;
		for (Ladrilho l : empilhador.ladrilho) {
			if (!l.solido)
				break;
			if (l.getId() == Id.parede) {
				if (getLimitesDoTopo().intersects(l.getLimites())) {
					setVelY(0);
					if (pulando) {
						pulando = false;
						gravidade = 0.8;
						caindo = true;
					}
				}
				if (getLimitesInferiores().intersects(l.getLimites())) {
					setVelY(0);
					if (caindo)
						caindo = false;
				} else {
					if (!caindo && !pulando) {
						gravidade = 0.8;
						caindo = true;
					}
				}
				if (getLimitesdaEsquerda().intersects(l.getLimites())) {
					setVelX(0);
					x = l.getX() + l.largura;
				}
				if (getLimitesdaDireita().intersects(l.getLimites())) {
					setVelX(0);
					x = l.getX() - l.altura;
				}
			}
		}

		if (pulando) {
			gravidade -= 0.1;
			setVelY((int) -gravidade);
			if (gravidade <= 0.0) {
				pulando = false;
				caindo = true;
			}
		}
		if (caindo) {
			gravidade += 0.1;
			setVelY((int) gravidade);
		}
		if (animacao) {
			frameDelay++;
			if (frameDelay >= 3) {
				frame++;
				if (frame >= 4) {
					frame = 0;
				}
				frameDelay = 0;
			}
		}

	}
}
