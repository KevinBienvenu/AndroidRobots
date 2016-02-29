package com.welcome.androidrobot;

import com.welcome.framework.Graphics;

public class Case {
	public boolean[] murs = new boolean[4];
	public int i;
	public int j;
	public int symbole ;
	public Case(int i, int j, boolean[] murs,int symbole){
		this.i = i;
		this.j = j;
		this.symbole = symbole;
		this.murs = murs;
		// Si symbole = 0 pas de symboles;
	}
	public void paint(Graphics g){
		
		//TODO : GIlles
	}
	
	public void update(){
		
		
	}
	
	public void mettreMur(boolean[] murs){
		this.murs  = murs;
	}
}
