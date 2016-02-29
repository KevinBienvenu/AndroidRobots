package com.welcome.androidrobot;

import java.util.List;
import java.util.Vector;

import com.welcome.framework.Game;
import com.welcome.framework.Graphics;
import com.welcome.framework.Input.TouchEvent;
import com.welcome.framework.Screen;
import com.welcome.framework.implementation.Event;

import android.graphics.Color;

public class Partie extends Screen{
	int compteur ;
	long startTimer=System.nanoTime();
	int remainingTime;
	//mode reflexion (0) ou annonce (1) (2) = jouer (3) = compter Score reinit
	int mode  = 0;
	Joueur joueurCourant = null;
	Plateau plateau;
	TopBar topbar;
	BottomBar bottomBar;
	SelectionBar selectionBar;
	JouerBar jouerBar;
	Objectif objectifCourant;
	Pion pionSelectionne;
	Case caseSelectionne;
	int nbCoups = 0;
	int annonce = 0;
	Joueur[] joueurs = new Joueur[3];
	boolean firstTime=true;
	boolean succes = false;

	public Partie(Game game) {
		super(game);
		String[] nom = new String[]{"Florian","Kevin","Callebouille"};
		for(int i=0; i<3; i++){
			joueurs[i] = new Joueur(nom[i]);
		}
		plateau = new Plateau(this);
		topbar = new TopBar(this);
		bottomBar = new BottomBar(this);
		selectionBar = new SelectionBar(this);
		this.tirerObjectif();
		jouerBar = new JouerBar(this);
	}
	public void tirerObjectif(){
		Objectif o = new Objectif();
		if(this.objectifCourant!=null){
			while(o.idPion==objectifCourant.idPion || o.idSymbole == objectifCourant.idSymbole){
				o = new Objectif();
			}
		}
		this.objectifCourant = o;
	}
	
	public boolean objectifVerifie(){
		int i = plateau.pions[objectifCourant.idPion].i;
		int j = plateau.pions[objectifCourant.idPion].j;
		return plateau.cases[i][j].symbole==objectifCourant.idSymbole;
	}

	public boolean deplacer(Pion p , int dir){

		if(dir==Direction.EST){
			if(p.j+1<plateau.cases.length && plateau.cases[p.i][p.j+1].peutDeplacer(dir)){
				p.j++;
				return true;
			}
		}
		else if(dir == Direction.OUEST){
			if(p.j-1>=0 && plateau.cases[p.i][p.j-1].peutDeplacer(dir)){
				p.j--;
				return true;
			}
		}
		else if(dir == Direction.NORD){
			if(p.i-1>=0 && plateau.cases[p.i-1][p.j].peutDeplacer(dir)){
				p.i--;
				return true;
			}
		}
		else if(dir == Direction.SUD){
			if(p.i+1<plateau.cases[0].length && plateau.cases[p.i+1][p.j].peutDeplacer(dir)){
				p.i++;
				return true;
			}
		}
		plateau.cases[p.i][p.j].estOccupe = true;
		return false;
	}

	public void back(){
		if(nbCoups==0){
			return;
		}
		for(Case[] ca : plateau.cases){
			for(Case c : ca){
				c.estOccupe = false;
			}
		}
		for(Pion p : this.plateau.pions){
			p.revenirEnArriere();
			plateau.cases[p.i][p.j].estOccupe = true;
		}
		nbCoups--;
	}

	public void reset(){
		for(Case[] ca : plateau.cases){
			for(Case c : ca){
				c.estOccupe = false;
			}
		}
		for(Pion p : this.plateau.pions){
			p.reset();
			plateau.cases[p.i][p.j].estOccupe = true;
		}
		nbCoups = 0;
	}
	public void arreter(int idJoueur){
		mode = Etat.ANNONCE;
		joueurCourant = joueurs[idJoueur];
	}
	public void reprendre(){
		mode= Etat.REFLEXION;
		annonce = selectionBar.nbCoups;
		if(annonce==1){
			mode = Etat.JOUER;
		}
		firstTime = false;
		startTimer = System.nanoTime();
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
		if(Y<0 && Math.abs(Y)>Math.abs(X)){
			return Direction.SUD;
		}
		if(X>0 && Math.abs(Y)<=Math.abs(X)){
			return Direction.EST;
		}
		if(X<0 && Math.abs(Y)<=Math.abs(X)){
			return Direction.OUEST;
		}
		return -1;

	}
	Pion avoirPion(Case c){
		if(c==null){
			return null;
		}
		Pion closest = null;
		int distMin = Assets.tolerance;
		for(Pion p : plateau.pions){
			double dist = Math.abs(p.i-c.i)+ Math.abs(p.j -c.j);
			if(dist<=distMin){
				closest= p;
				distMin = (int) dist;
			}
		}
		return closest;
	}
	Case selection(int x, int y) {

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
		List<TouchEvent> listEvents = game.getInput().getTouchEvents();
		Vector<Event> events = new Vector<Event>();
		for(TouchEvent t : listEvents){
			events.add(new Event(t));
		}
		//CALCULATE TIMER
		if(!firstTime){
			long time = System.nanoTime();
			remainingTime = (int)((time-startTimer)*1e-9);
			if(remainingTime>=Assets.remainingTime){
				mode = Etat.JOUER;
			} 
		}

		//Update bars
		this.topbar.update(events);
		if(mode!=Etat.ANNONCE && mode!=Etat.JOUER && mode!=Etat.REINIT){
			bottomBar.maj();
			bottomBar.update(events);
		}else if(mode==Etat.ANNONCE){
			selectionBar.update(events);
		}else if(mode==Etat.JOUER){
			jouerBar.maj();
			jouerBar.update(events);
		}else if(mode == Etat.REINIT){
			
			reinit();
		}
		this.plateau.update(events);
	}
	public void reinit(){
		startTimer = System.nanoTime();
		mode = Etat.REFLEXION;
		firstTime = true;
		annonce = 0;
		nbCoups = 0;
		if(succes){
			this.joueurCourant.score++;
		}else{
			this.joueurCourant.score--;
		}
		joueurCourant = null;
		this.remainingTime = 0;
		tirerObjectif();
	}

	@Override
	public void paint(float deltaTime) {
		Graphics g = game.getGraphics();
		g.fillRect(0, 0, Assets.resX, Assets.resY, Color.DKGRAY);
		g.drawLine(0, Assets.barSizeY, Assets.resX, Assets.barSizeY, Color.WHITE);
		g.drawRect(0, Assets.optionStartY, Assets.resX,Assets.optionSizeY, Color.WHITE);
		//g.drawString(""+nbCoups, Assets.resX/2,Assets.optionSizeY/2+Assets.optionStartY, Assets.paint);
		plateau.paint(g);
		topbar.paint(g);
		if(mode==Etat.ANNONCE){
			selectionBar.paint(g);
		} else if(mode==Etat.JOUER) {
			jouerBar.paint(g);
		}else{
			bottomBar.paint(g);
		}
		this.paintObjectif(g);
		plateau.paintSymbole(g);
	}
	
	
	public void paintObjectif(Graphics g){
		Case c = plateau.cases[Assets.nColonnes/2-1][Assets.nColonnes/2-1];
		g.fillRect(c.x, c.y, Assets.tailleCase*3, Assets.tailleCase*3, Color.BLACK);
		g.drawRect(c.x, c.y, Assets.tailleCase*3, Assets.tailleCase*3, Color.DKGRAY);
		g.fillOval(c.x+Assets.tailleCase/2, c.y+Assets.tailleCase/2, Assets.tailleCase*2, Assets.tailleCase*2, objectifCourant.couleur);
		g.drawImage(Assets.symboles.get(objectifCourant.idSymbole-1), c.x+Assets.tailleCase/2, c.y+Assets.tailleCase/2, Assets.tailleCase*2);
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