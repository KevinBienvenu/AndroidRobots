package com.welcome.androidrobot;

import java.util.Vector;

import com.welcome.framework.Graphics;
import com.welcome.framework.implementation.Event;

public class TopBar {
	
	public Bouton[] boutons = new Bouton[3];
	
	Partie partie;
	
	
	public TopBar(Partie p){
		this.partie = p;
		boutons[0] = new Bouton(0,0,Assets.resX/4,Assets.barSizeY,"BACK"){
			@Override
			public void callback() {
				partie.back();
			}
		};
		boutons[1] = new Bouton(Assets.resX/4,0,Assets.resX/4,Assets.barSizeY,"RESET"){
			@Override
			public void callback() {
				partie.reset();
			}
		};
		boutons[2] = new Bouton(3*Assets.resX/4,0,Assets.resX/4,Assets.barSizeY,"MENU"){
			@Override
			public void callback() {
				
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
