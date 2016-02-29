package com.welcome.androidrobot;

import com.welcome.framework.Game;
import com.welcome.framework.Graphics;
import com.welcome.framework.Input.TouchEvent;

import android.graphics.Color;

import com.welcome.framework.Screen;

public class Partie extends Screen{
	int compteur ;
	long timer;
	Plateau plateau;
	Objectif objectifCourant;
	Pion pionSelectionne;
	Case caseSelectionne;
	
	public Partie(Game game) {
        super(game);
		plateau = new Plateau();
	}
	public void tirerObjectif(){
		
	}
	
	
	public void deplacer(Pion p , int dir){
		plateau.cases[p.i][p.j].estOccupe = false;
		//Memorisation des positions précédentes
		for(Pion pion : plateau.pions){
			pion.iPrecedent.add(pion.i);
			pion.jPrecedent.add(pion.j);
		}
		int iter = 0;
		if(dir==Direction.EST){
			iter = p.j;
			while(p.j+1<plateau.cases.length && !plateau.cases[p.i][p.j+1].estOccupe){
				p.j++;
			}
		}
		else if(dir == Direction.OUEST){
			iter = p.j;
			while(p.j-1>=0 && !plateau.cases[p.i][p.j-1].estOccupe){
				p.j--;
			}
		}
		else if(dir == Direction.NORD){
			iter = p.i;
			while(p.i-1>=0 && !plateau.cases[p.i-1][p.j].estOccupe){
				p.i--;
			}
		}
		else if(dir == Direction.SUD){
			iter = p.i;
			while(p.i+1<plateau.cases[0].length && !plateau.cases[p.i+1][p.j].estOccupe){
				p.i++;
			}
		}
		plateau.cases[p.i][p.j].estOccupe = true;
		
	}
	public int calculerDirection(Case c){
		int I = c.i-pionSelectionne.i;
		int J = c.j-pionSelectionne.j;
		//Calculer direction
		int Y = -I;
		int X = J;
		
		if(Y>0 && Math.abs(Y)>Math.abs(X)){
			return Direction.NORD;
		}
		if(Y<=0 && Math.abs(Y)>Math.abs(X)){
			return Direction.SUD;
		}
		if(X>0 && Math.abs(Y)<=Math.abs(X)){
			return Direction.EST;
		}
		if(X<=0 && Math.abs(Y)<=Math.abs(X)){
			return Direction.OUEST;
		}
		return 0;
	}
	private Pion avoirPion(Case c){
		if(c==null){
			return null;
		}
		for(Pion p : plateau.pions){
			if(p.i==c.i && p.j == c.j){
				return p;
			}
		}
		return null;
	}
	private Case selection(int x, int y) {
		
		for(Case[] lignes : plateau.cases){
			for(Case c : lignes){
				//Si dans la collision box alors 8
				if(c.estDansCase(x, y)){
					return c;
				}
			}
		}
		return null;
	}
	@Override
	public void update(float deltaTime) {
		for(TouchEvent e : game.getInput().getTouchEvents()){
			if(e.type==TouchEvent.TOUCH_DRAGGED){
				
			}
			if(e.type==TouchEvent.TOUCH_DOWN){
				//SELECTION
				caseSelectionne = selection(e.x,e.y);
				if(this.pionSelectionne==null){
					pionSelectionne = avoirPion(caseSelectionne);
				}
				
			}
			if(e.type==TouchEvent.TOUCH_UP){
				if(pionSelectionne==null){
					return;
				}
				Case c = selection(e.x,e.y);
				int dir = calculerDirection(c);
				deplacer(this.pionSelectionne,dir);
				pionSelectionne = null;
				caseSelectionne = null;
			}
		}
	}
	@Override
	public void paint(float deltaTime) {
		Graphics g = game.getGraphics();
        g.fillRect(0, 0, Assets.resX, Assets.resY, Color.DKGRAY);
        g.fillRect(0, Assets.barStartY, Assets.resX, Assets.barSizeY, Color.BLACK);
        g.drawRect(0, Assets.barStartY, Assets.resX, Assets.barSizeY, Color.WHITE);
        g.drawRect(0, Assets.optionStartY, Assets.resX, Assets.optionSizeY, Color.WHITE);
        plateau.paint(g);
		
	}
	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void backButton() {
		// TODO Auto-generated method stub
		
	}
}
