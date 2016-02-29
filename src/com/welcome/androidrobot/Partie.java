package com.welcome.androidrobot;

import com.welcome.framework.Graphics;
import com.welcome.framework.Input;
import com.welcome.framework.Input.TouchEvent;

public class Partie {
	static Robots main;
	int compteur ;
	long timer;
	Plateau plateau;
	Objectif objectifCourant;
	Pion pionSelectionne;
	Case caseSelectionne;
	
	public Partie(){
		plateau = new Plateau();
	}
	public void tirerObjectif(){
		
	}
	public void paint(Graphics g){
		
		//TODO : GIlles
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
}
