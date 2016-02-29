package com.welcome.androidrobot;

import java.util.Vector;

import com.welcome.framework.Graphics;
import com.welcome.framework.implementation.Event;

import android.graphics.Color;

public class JouerBar {
	
	public Bouton[] boutons = new Bouton[2];
	int nbCoups;
	Partie partie;
	
	public JouerBar(Partie p){
		this.partie = p;
		boutons[0] = new Bouton(0,Assets.optionStartY,Assets.resX,Assets.optionSizeY/2,"Jean-Marcel "){
			@Override
			public void callback() {
				
			}
		};
		boutons[1] = new Bouton(0,Assets.optionStartY+Assets.optionSizeY/2,Assets.resX,Assets.optionSizeY/2,"Restant : "+(partie.annonce-partie.nbCoups)){
			@Override
			public void callback() {
				
			}
		};
	}
	
	public void maj(){
		boutons[0].string = "Joueur : "+partie.joueurCourant.nom;
		boutons[1].string = "Restant : "+(partie.annonce-partie.nbCoups);
	}
	public void paint(Graphics g){
		g.fillRect(0, Assets.optionStartY, Assets.resX, Assets.optionSizeY,Color.BLACK);
		for(int i=0; i<boutons.length; i++){
			boutons[i].paint(g);
		}
	}
	
	public void update(Vector<Event> events){
		for(int i=0; i<boutons.length; i++){
			boutons[i].update(events);
		}
	}
}
