package com.welcome.androidrobot;

import com.welcome.framework.Graphics;

public class Plateau {

	public Case[][] cases = new Case[Assets.nLignes][Assets.nColonnes];
	public Pion[] pions = new Pion[5];
	public void paint(Graphics g){
		for(int i=0; i<Assets.nLignes; i++){
			for(int j=0; j<Assets.nColonnes; j++){
				cases[i][j].paint(g);
			}
		}
		for(int i=0; i<5; i++){
			pions[i].paint(g);
		}
	}

	public Plateau(){
		boolean[] murs = new boolean[4];
		boolean b = true;
		int k=0,l=0;
		for(int i=0; i<5; i++){
			b = true;
			while(b){
				b = false;
				k = (int) (Math.random()*Assets.nColonnes);
				l = (int) (Math.random()*Assets.nLignes);
				for(int j = 0; j<i; j++){
					b = b || (pions[j].i==k && pions[j].j==l);
				}
			}
			pions[i] = new Pion(k,l,i);
		}
		for(int i=0; i<Assets.nLignes; i++){
			for(int j=0; j<Assets.nColonnes; j++){
				murs = new boolean[4];
				murs[0] = j==Assets.nColonnes-1;
				murs[1] = i==0;
				murs[2] = j==0;
				murs[3] = i==Assets.nLignes-1;
				cases[i][j] = new Case(i,j,murs,0);
			}
		}
		for(int i=0; i<Assets.nbMur; i++){
			k = (int) (Math.random()*(Assets.nLignes));
			l = (int) (1+Math.random()*(Assets.nColonnes-1));
			cases[k][l].murs[2] = true;
			cases[k][l-1].murs[0] = true;
			k = (int) (1+Math.random()*(Assets.nLignes-1));
			l = (int) (Math.random()*(Assets.nColonnes));
			cases[k][l].murs[1] = true;
			cases[k-1][l].murs[3] = true;
		}
		for(Pion p : this.pions){
			p.reset();
			cases[p.i][p.j].estOccupe = true;
		}
	}

	public void update(){

	}

}
