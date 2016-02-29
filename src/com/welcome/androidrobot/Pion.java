package com.welcome.androidrobot;

import java.util.Vector;

import com.welcome.framework.Graphics;

import android.graphics.Color;

public class Pion {
	Color couleur;
	public int id;
	int i;
	int j;
	Vector<Integer>iPrecedent;
	Vector<Integer>jPrecedent;
	
	public Pion(int i , int j, int id){
		this.i = i;
		this.j = j;
		this.id = id;
	}
	
	public void revenirEnArriere(){
		i = iPrecedent.lastElement();
		j = jPrecedent.lastElement();
		jPrecedent.remove(jPrecedent.size()-1);
		iPrecedent.remove(iPrecedent.size()-1);
		
	}
	public void paint(Graphics g){
		
		//TODO : GIlles
	}
	
	public void update(){
		
		
	}
	
	public void deplacer(int dir){
		//TODO : Philippe
		
	}
}
