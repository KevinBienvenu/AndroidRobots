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
				selection(e.x,e.y);
			}
		}
		
		
	}
	private Pion selection(int x, int y) {
		
		for(Case[] lignes : plateau.cases){
			for(Case c : lignes){
				
			}
		}
		return null;
	}
}
