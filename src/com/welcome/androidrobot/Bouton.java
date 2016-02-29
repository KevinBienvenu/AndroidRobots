package com.welcome.androidrobot;

import java.util.List;
import java.util.Vector;

import com.welcome.framework.Graphics;
import com.welcome.framework.implementation.Event;

import android.graphics.Color;

public abstract class Bouton {
	
	public int x, y, sizeX, sizeY;
	public String string;
	
	public boolean touchDown;
	
	public int charge;
	public int animation;
	public static int maxCharge = 15;
	public static int maxAnimation = 5;
	

	public Bouton(int x, int y, int sizeX, int sizeY, String string) {
		this.x = x;
		this.y = y;
		this.sizeX = sizeX;
		this.sizeY = sizeY;
		this.string = string;
	}
	public abstract void callback();
	
	public void paint(Graphics g){
		//g.drawRect(x, y, sizeX, sizeY, Color.DKGRAY);
		if(touchDown){
			g.fillRect(x+(int)((1f-1f*charge/maxCharge)*(sizeX/2-5)), y+5, charge*(sizeX-10)/maxCharge, sizeY-10, Color.GRAY);
		} else if (animation>0){
			g.fillRect(x+5, y+5, sizeX-10, sizeY-10, Color.GRAY);
		}
		g.drawString(string, x+sizeX/2, y+2*sizeY/3, Assets.paint);
	}
	
	public void update(Vector<Event> events){
		if(animation>0){
			animation--;
		}
		for(Event e : events){
			if(e.isDown){
				//SELECTION
				if(e.x>x && e.x<x+sizeX && e.y>y && e.y<y+sizeY){
					touchDown = true;
				}
			}
			if(e.isUp){
				if(e.x>x && e.x<x+sizeX && e.y>y && e.y<y+sizeY){
					animation = maxAnimation;
				}
				charge = 0;
				touchDown = false;
			}
		}
		if(touchDown){
			charge = Math.min(charge+1, maxCharge);
		}
		if(animation==1){
			this.callback();
		}
	}
	
	public void maj(){
		
	}
	
}
