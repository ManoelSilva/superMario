package com.supermario;

import java.awt.Graphics;
import java.util.LinkedList;

import com.supermario.entidade.Entidade;
import com.supermario.ladrilho.Ladrilho;
import com.supermario.ladrilho.Parede;

public class Empilhador {

	public LinkedList<Entidade> entidade = new LinkedList<Entidade>();
	public LinkedList<Ladrilho> ladrilho = new LinkedList<Ladrilho>();
	
	public Empilhador(){
		criarNivel(); 
	}

	public void render(Graphics g) {
		for (Entidade ent : entidade) {
			ent.render(g);
		}
		for (Ladrilho la : ladrilho) {
			la.render(g);
		}
	}

	public void tick() {
		for (Entidade ent : entidade) {
			ent.tick();
		}
		for (Ladrilho la : ladrilho) {
			la.tick();
		}
	}

	public void addEntidade(Entidade ent) {
		entidade.add(ent);
	}

	public void removeEntidade(Entidade ent) {
		entidade.remove(ent);
	}

	public void addLadrilho(Ladrilho la) {
		ladrilho.add(la);
	}

	public void removeLadrilho(Ladrilho la) {
		ladrilho.remove(la);
	}

	public void criarNivel(){
		for(int i = 0; i<Game.LARGURA*Game.ESCALA/64+1; i++){
			addLadrilho(new Parede(i*64, Game.ALTURA*Game.ESCALA-64, 64, 64, true, Id.parede, this));
			if(i!=0&&i!=1&&i!=15&&i!=16&&i!=17) addLadrilho(new Parede(i*64, 300, 64, 64, true, Id.parede, this));
		}
	}

}
