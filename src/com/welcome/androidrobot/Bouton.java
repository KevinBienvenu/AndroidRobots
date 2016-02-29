package com.welcome.androidrobot;

import java.util.List;
import java.util.Vector;

import com.welcome.framework.Graphics;
import com.welcome.framework.Input.TouchEvent;
import com.welcome.framework.implementation.Event;

import android.graphics.Color;

public abstract class Bouton {
	
	public int x, y, sizeX, sizeY;
	public String string;
	
	public boolean touchDown;
	

	public Bouton(int x, int y, int sizeX, int sizeY, String string) {
		this.x = x;
		this.y = y;
		this.sizeX = sizeX;
		this.sizeY = sizeY;
		this.string = string;
	}
	public abstract void callback();
	
	public void paint(Graphics g){
		g.fillRect(x, y, sizeX, sizeY, Color.WHITE);
		if(touchDown){
			g.fillRect(x+5, y+5, sizeX-10, sizeY-10, Color.GRAY);
		} else {
			g.fillRect(x+5, y+5, sizeX-10, sizeY-10, Color.DKGRAY);
		}
		g.drawString(string, x+sizeX/2, y+2*sizeY/3, Assets.paint);
	}
	
	public void update(Vector<Event> events){
		for(Event e : events){
			if(e.isDown){
				//SELECTION
				if(e.x>x && e.x<x+sizeX && e.y>y && e.y<y+sizeY){
					touchDown = true;
				}
			}
			if(e.isUp){
				if(e.x>x && e.x<x+sizeX && e.y>y && e.y<y+sizeY){
					this.callback();
				}
				touchDown = false;
			}
		}
	}
	
	public void maj(){
		
	}
	
}
