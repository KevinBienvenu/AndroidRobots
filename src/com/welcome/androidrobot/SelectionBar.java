package com.welcome.androidrobot;

import java.util.Vector;

import com.welcome.framework.Graphics;
import com.welcome.framework.implementation.Event;

public class SelectionBar {
	
	public Bouton[] boutons = new Bouton[3];
	int nbCoups;
	Partie partie;
	
	public SelectionBar(Partie p){
		this.partie = p;
		boutons[0] = new Bouton(0,Assets.optionStartY,Assets.resX,Assets.optionSizeY/3,"Coups : "+nbCoups){
			@Override
			public void callback() {
				
			}
		};
		boutons[1] = new Bouton(0,Assets.optionStartY+Assets.optionSizeY/3,Assets.resX/2,Assets.optionSizeY/3,"-"){
			@Override
			public void callback() {
				nbCoups--;
				if(nbCoups<0){
					nbCoups=0;
				}
				boutons[0].string ="Coups : "+nbCoups;
			}
		};
		boutons[2] = new Bouton(Assets.resX/2,Assets.optionStartY+Assets.optionSizeY/3,Assets.resX/2,Assets.optionSizeY/3,"+"){
			@Override
			public void callback() {
				nbCoups++;
				boutons[0].string ="Coups : "+nbCoups;
			}
		};
		boutons[3] = new Bouton(0,Assets.optionStartY+2*Assets.optionSizeY/3,Assets.resX/4,Assets.optionSizeY/3,"OK"){
			@Override
			public void callback() {
				partie.reprendre();
			}
		};

	}

	public void paint(Graphics g){
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
