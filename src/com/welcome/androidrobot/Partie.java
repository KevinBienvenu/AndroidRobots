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
    Plateau plateau;
    TopBar topbar;
    BottomBar bottomBar;
    SelectionBar selectionBar;
    Objectif objectifCourant;
    Pion pionSelectionne;
    Case caseSelectionne;
    int nbCoups = 0;
    int annonce = 0;
    Joueur[] joueurs = new Joueur[3];
    
    public Partie(Game game) {
        super(game);
        plateau = new Plateau(this);
        topbar = new TopBar(this);
        bottomBar = new BottomBar(this);
        selectionBar = new SelectionBar(this);
    }
    public void tirerObjectif(){

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
    }
    public void reprendre(){
    	mode= Etat.REFLEXION;
    	annonce = selectionBar.nbCoups;
    }
    
    public void jouer(){
    	mode = Etat.JOUER;
    	nbCoups = 0;
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
        long time = System.nanoTime();
        remainingTime = (int)((time-startTimer)*1e-9);
        if(remainingTime>=Assets.remainingTime){
        	annonce = 
        	mode = Etat.JOUER;
        }
        //Update bars
        bottomBar.maj();
        
        this.topbar.update(events);
        if(mode!=1){
        	bottomBar.update(events);
        }else{
        	selectionBar.update(events);
        }
        this.plateau.update(events);
       
    }
    public void reinit(){
    	startTimer = System.nanoTime();
    	mode = Etat.REFLEXION;
    }

    @Override
    public void paint(float deltaTime) {
        Graphics g = game.getGraphics();
        g.fillRect(0, 0, Assets.resX, Assets.resY, Color.DKGRAY);
        g.fillRect(0, Assets.barStartY, Assets.resX, Assets.barSizeY,
Color.BLACK);
        g.drawRect(0, Assets.barStartY, Assets.resX, Assets.barSizeY,
Color.WHITE);
        g.drawRect(0, Assets.optionStartY, Assets.resX,
Assets.optionSizeY, Color.WHITE);
        g.drawString(""+nbCoups, Assets.resX/2,
Assets.optionSizeY/2+Assets.optionStartY, Assets.paint);
        plateau.paint(g);
        topbar.paint(g);
        bottomBar.paint(g);
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