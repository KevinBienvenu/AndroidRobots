package com.welcome.androidrobot;

import java.util.Vector;

import com.welcome.framework.Graphics;
import com.welcome.framework.implementation.Event;

public class BottomBar {
	
	public Bouton[] boutons = new Bouton[4];
	
	Partie partie;
	
	public BottomBar(Partie p){
		this.partie = p;
		boutons[0] = new Bouton(0,Assets.optionStartY,Assets.resX/4,Assets.optionSizeY/4,"Florian "+partie.joueurs[0].score){
			@Override
			public void callback() {
				partie.arreter(0);
			}
		};
		boutons[1] = new Bouton(0,Assets.optionStartY+Assets.optionSizeY/4,Assets.resX,Assets.optionSizeY/4,"Kevin "+partie.joueurs[1].score){
			@Override
			public void callback() {
				partie.arreter(1);
			}
		};
		boutons[2] = new Bouton(0,Assets.optionStartY+2*Assets.optionSizeY/4,Assets.resX,Assets.optionSizeY/4,"Callebouille "+partie.joueurs[2].score){
			@Override
			public void callback() {
				partie.arreter(2);
			}
		};
		boutons[4] = new Bouton(0,Assets.optionStartY+3*Assets.optionSizeY/4,Assets.resX,Assets.optionSizeY/4,"Timer"){
			@Override
			public void callback() {
				
			}
		};
		boutons[5] = new Bouton(0,Assets.optionStartY+3*Assets.optionSizeY/4,Assets.resX,Assets.optionSizeY/4,"Annonce: "+partie.annonce){
			@Override
			public void callback() {
				
			}
		};
	}
	
	public void maj(){
		boutons[0].string = "Florian "+partie.joueurs[0].score;
		boutons[0].string = "Kevin "+partie.joueurs[1].score;
		boutons[0].string = "Callebouille "+partie.joueurs[2].score;
		boutons[5].string = "Annonce: "+partie.annonce;
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
