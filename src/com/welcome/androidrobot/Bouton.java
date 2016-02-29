package com.welcome.androidrobot;

import java.util.List;

import com.welcome.framework.Graphics;
import com.welcome.framework.Input.TouchEvent;

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
		if(touchDown){
			g.fillRect(x, y, sizeX, sizeY, Color.GRAY);
		} else {
			g.fillRect(x, y, sizeX, sizeY, Color.DKGRAY);
		}
		g.drawString(string, x+sizeX/2, y+2*sizeY/3, Assets.paint);
	}
	
	public void update(List<TouchEvent> events){
		for(TouchEvent e : events){
			if(e.type==TouchEvent.TOUCH_DOWN){
				//SELECTION
				if(e.x>x && e.x<x+sizeX && e.y>y && e.y<y+sizeY){
					touchDown = true;
				}
			}
			if(e.type==TouchEvent.TOUCH_UP){
				if(e.x>x && e.x<x+sizeX && e.y>y && e.y<y+sizeY){
					this.callback();
				}
				touchDown = false;
			}
		}
	}
	
}
