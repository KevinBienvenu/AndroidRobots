package com.welcome.androidrobot;

import com.welcome.framework.Graphics;

import android.graphics.Color;

public class Case {
	public boolean[] murs = new boolean[4];
	public int i;
	public int j;
	public int symbole ;
	public int x,y,taille;
	public int couleur;
	public boolean active;
	public boolean estOccupe;
	
	public Case(int i, int j, boolean[] murs,int symbole){
		this.i = i;
		this.j = j;
		this.symbole = symbole;
		this.murs = murs;
		x = j*Assets.tailleCase + Assets.boardStartX;
		y = i*Assets.tailleCase + Assets.boardStartY;
		taille = Assets.tailleCase;
		if((i+j)%2==0){
			couleur = Color.rgb(110, 220, 240);
		} else {
			couleur = Color.rgb(110, 200, 250);
		}
		// Si symbole = 0 pas de symboles;
	}
	public boolean peutDeplacer(int dir){
		return !estOccupe && !murs[(dir+2)%4]; 
	}
	public void paint(Graphics g){
		g.fillRect(x, y, taille, taille, couleur);
		g.drawRect(x, y, taille, taille, Color.DKGRAY);
		if(murs[0]){
			g.fillRect(x+taille-(int)(taille*Assets.ratioMur), y, (int)(taille*Assets.ratioMur), taille, Color.BLACK);
		}
		if(murs[2]){
			g.fillRect(x, y, (int) (taille*Assets.ratioMur), taille, Color.BLACK);
		}
		if(murs[1]){
			g.fillRect(x, y, taille, (int) (taille*Assets.ratioMur), Color.BLACK);
		}
		if(murs[3]){
			g.fillRect(x, y+taille-(int) (taille*Assets.ratioMur), taille, (int) (taille*Assets.ratioMur), Color.BLACK);
		}
		if(symbole>0){
			g.drawImage(Assets.symboles.get(symbole-1), x, y, Assets.tailleCase);
		}
	}
	
	public boolean estDansCase(int a, int b){
		return a>x && a<x+taille && b>y && b<y+taille;
	}
	
	
	public void update(){
		
		
	}
	
	public void mettreMur(boolean[] murs){
		this.murs  = murs;
	}
}
