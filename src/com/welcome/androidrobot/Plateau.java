package com.welcome.androidrobot;

import com.welcome.framework.Graphics;

public class Plateau {
	
	public Case[][] cases = new Case[Assets.nLignes][Assets.nColonnes];
	public Pion[] pions = new Pion[4];
	public void paint(Graphics g){
		for(int i=0; i<Assets.nLignes; i++){
			for(int j=0; j<Assets.nColonnes; j++){
				cases[i][j].paint(g);
			}
		}
	}
	
	public void update(){
		
	}

}
