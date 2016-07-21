package com.supermario.ladrilho;

import java.awt.Graphics;

import com.supermario.Empilhador;
import com.supermario.Game;
import com.supermario.Id;

public class Parede extends Ladrilho {

	public Parede(int x, int y, int largura, int altura, boolean solido, Id id, Empilhador empilhador) {
		super(x, y, largura, altura, solido, id, empilhador);

	}

	public void render(Graphics g) {
		g.drawImage(Game.grama.getBufferedImage(), x, y, largura, altura, null);
	}

	public void tick() {

	}

}
