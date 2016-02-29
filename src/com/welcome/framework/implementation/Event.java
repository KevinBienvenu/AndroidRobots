package com.welcome.framework.implementation;

import com.welcome.framework.Input.TouchEvent;

public class Event {

	public boolean isUp, isDown;
	public int x,y;
	
	public Event(TouchEvent event) {
		this.isUp = event.type == TouchEvent.TOUCH_UP;
		this.isDown = event.type == TouchEvent.TOUCH_DOWN;
		this.x = event.x;
		this.y = event.y;
	}
	
	
}
