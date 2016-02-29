package com.welcome.androidrobot;

import java.util.Vector;

import com.welcome.framework.Graphics;

import android.graphics.Color;

public class Pion {
	int couleur;
	public int id;
	int i;
	int j;
	Vector<Integer>iPrecedent = new Vector<Integer>();
	Vector<Integer>jPrecedent = new Vector<Integer>();
	
	public Pion(int i , int j, int id){
		this.i = i;
		this.j = j;
		this.id = id;
		switch(id){
		case 0 : couleur = Color.BLACK; break;
		case 1 : couleur = Color.RED; break;
		case 2 : couleur = Color.GREEN; break;
		case 3 : couleur = Color.BLUE; break;
		case 4 : couleur = Color.YELLOW; break;
		default: 
		}
	}
	
	public void revenirEnArriere(){
		i = iPrecedent.lastElement();
		j = jPrecedent.lastElement();
		jPrecedent.remove(jPrecedent.size()-1);
		iPrecedent.remove(iPrecedent.size()-1);
		
	}
	public void reset(){
		if(iPrecedent.size()==0){
			return;
		}
		i = iPrecedent.firstElement();
		j = jPrecedent.firstElement();
		jPrecedent.clear();
		iPrecedent.clear();
	}
	public void paint(Graphics g){
		int x = Assets.tailleCase*j+(int)(Assets.tailleCase*(1f-Assets.ratioPion));
		int y = Assets.boardStartY + i*Assets.tailleCase+(int)(Assets.tailleCase*(1f-Assets.ratioPion));
		g.fillOval(x, y, (int)(Assets.tailleCase*Assets.ratioPion), (int)(Assets.tailleCase*Assets.ratioPion), couleur);
		g.drawOval(x, y, (int)(Assets.tailleCase*Assets.ratioPion), (int)(Assets.tailleCase*Assets.ratioPion), Color.BLACK);
	}
	
	public void update(){
		
		
	}
	
	public void deplacer(int dir){
		//TODO : Philippe
		
	}
}
