package com.welcome.androidrobot;

import java.util.Vector;

import com.welcome.framework.Graphics;
import com.welcome.framework.implementation.Event;

import android.graphics.Color;

public abstract class Bouton {
	
	public int x, y, sizeX, sizeY;
	public String string;
	
	public int charge = 0;
	public int animation = 0;
	
	public static int maxCharge = 5;
	public static int maxAnimation = 5;
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
		g.fillRect(x, y, sizeX, sizeY, Color.BLACK);
		if(touchDown){
			g.fillRect(x+(int)((1f-1f*charge/maxCharge)*(sizeX/2-5)), y, charge*(sizeX-10)/maxCharge, sizeY, Color.GRAY);
		} else if(animation>0){
			g.fillRect(x, y, sizeX, sizeY, Color.GRAY);
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
				if(touchDown && e.x>x && e.x<x+sizeX && e.y>y && e.y<y+sizeY){
					this.animation = maxAnimation;
					this.callback();
				}
				charge = 0;
				touchDown = false;
			}
		}
		if(touchDown){
			charge = Math.min(charge+1,maxCharge);
		}
		if(animation>0){
			animation -- ;
		}
	}
	
}
