package com.welcome.androidrobot;

import com.welcome.framework.Graphics;

import android.graphics.Color;

public class Objectif {
	
	public int idPion; 
	public int idSymbole;
	public int couleur;
	
	public Objectif(int idPion, int idSymbole){
		this.idPion = idPion;
		this.idSymbole = idSymbole;
		switch(idPion){
		case 0 : couleur = Color.BLACK; break;
		case 1 : couleur = Color.RED; break;
		case 2 : couleur = Color.GREEN; break;
		case 3 : couleur = Color.BLUE; break;
		case 4 : couleur = Color.YELLOW; break;
		default: 
		}
	}
	
	public Objectif(){
		this.idPion = (int) (Math.random()*Assets.nbPions-1)+1;
		this.idSymbole = (int) (Math.random()*Assets.nbSymboles);
		switch(idPion){
		case 0 : couleur = Color.BLACK; break;
		case 1 : couleur = Color.RED; break;
		case 2 : couleur = Color.GREEN; break;
		case 3 : couleur = Color.BLUE; break;
		case 4 : couleur = Color.YELLOW; break;
		default: 
		}
	}
	
	public void paint(Graphics g){
		
		//TODO : GIlles
	}
	
	public void update(){
		
		
	}
}
