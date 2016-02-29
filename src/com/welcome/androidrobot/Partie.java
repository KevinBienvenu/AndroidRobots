package com.welcome.androidrobot;

import com.welcome.framework.Game;
import com.welcome.framework.Graphics;
import com.welcome.framework.Input.TouchEvent;

import android.graphics.Color;

import com.welcome.framework.Screen;

public class Partie extends Screen{
	static Robots main;
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
	
	public void update(){
		for(TouchEvent e : main.getInput().getTouchEvents()){
			if(e.type==TouchEvent.TOUCH_DRAGGED){
				
			}
			if(e.type==TouchEvent.TOUCH_UP){
				//SELECTION
				caseSelectionne = selection(e.x,e.y);
				if(this.pionSelectionne==null){
					pionSelectionne = avoirPion(caseSelectionne);
				}
				
			}
		}
		
		
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
		// TODO Auto-generated method stub
		
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
