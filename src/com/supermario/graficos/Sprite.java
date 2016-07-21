package com.supermario.graficos;

import java.awt.image.BufferedImage;

public class Sprite {
	public SpriteSheet sheet;

	public BufferedImage imagem;

	public Sprite(SpriteSheet sheet, int x, int y) {
		imagem = sheet.getSprite(x, y);
	}

	public BufferedImage getBufferedImage() {
		return imagem;
	}
}
