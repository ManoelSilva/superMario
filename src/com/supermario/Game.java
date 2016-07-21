package com.supermario;

//Canvas permite alterar o frame e mostrar algo nele
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;

import com.supermario.entidade.Entidade;
import com.supermario.entidade.Jogador;
import com.supermario.graficos.Sprite;
import com.supermario.graficos.SpriteSheet;
import com.supermario.input.InputDoTeclado;
import com.supermario.ladrilho.Parede;

public class Game extends Canvas implements Runnable {
	public static final int LARGURA = 270;
	public static final int ALTURA = LARGURA / 14 * 10;
	public static final int ESCALA = 4;
	public static String TITULO = "Super Mario";

	private Thread thread;
	private boolean executando = false;

	public static Empilhador empilhador;
	public static SpriteSheet sheet;
	public static Camera cam;

	public static Sprite grama;
	public static Sprite jogador[] = new Sprite[12];

	public Game() {
		Dimension tamanho = new Dimension(LARGURA * ESCALA, ALTURA * ESCALA);
		setPreferredSize(tamanho);
		setMaximumSize(tamanho);
		setMinimumSize(tamanho);
	}

	private void inicializador() {
		empilhador = new Empilhador();
		sheet = new SpriteSheet("/spritesheet.png");
		cam = new Camera();

		addKeyListener(new InputDoTeclado());

		grama = new Sprite(sheet, 1, 1);

		for (int i = 0; i < jogador.length; i++) {
			jogador[i] = new Sprite(sheet, i + 1, 16);
		}

		empilhador.addEntidade(new Jogador(200, 512, 64, 64, true, Id.jogador, empilhador));

	}

	private synchronized void iniciar() {
		if (executando)
			return;
		executando = true;
		thread = new Thread(this, "ThreadIniciar");
		thread.start();

	}

	private synchronized void parar() {
		if (!executando)
			return;
		executando = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void run() {
		inicializador();
		requestFocus();
		long lastTime = System.nanoTime();
		long timer = System.currentTimeMillis();
		double delta = 0.0;
		double ns = 1000000000.0 / 60.0;
		int frames = 0;
		int ticks = 0;
		while (executando) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while (delta >= 1) {
				tick();
				ticks++;
				delta--;
			}
			render();
			frames++;
			if (System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				System.out.println(frames + " Frames por segundo " + ticks + " Updates por segundo");
				frames = 0;
				ticks = 0;
			}
		}
		parar();
	}

	public void render() {
		BufferStrategy bs = getBufferStrategy();
		if (bs == null) {
			createBufferStrategy(3);
			return;
		}
		Graphics g = bs.getDrawGraphics();
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, getWidth(), getHeight());
		g.translate(cam.getX(), cam.getY());
		empilhador.render(g);
		g.dispose();
		bs.show();
	}

	public void tick() {
		empilhador.tick();

		for (Entidade e : empilhador.entidade) {
			if (e.getId() == Id.jogador) {
				cam.tick(e);
			}
		}
	}

	public static void main(String[] args) {
		Game game = new Game();
		JFrame frame = new JFrame(TITULO);
		frame.add(game);
		frame.pack();
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		game.iniciar();
	}
}
