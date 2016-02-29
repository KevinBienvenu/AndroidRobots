package com.welcome.androidrobot;

import java.util.List;

import com.welcome.framework.Game;
import com.welcome.framework.Graphics;
import com.welcome.framework.Screen;

import android.R.color;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

import com.welcome.framework.Input.TouchEvent;


public class MainMenuScreen extends Screen {
	
	private Paint p;
	public MainMenuScreen(Game game) {
		super(game);

        // Defining a paint object
        p = new Paint();
        p.setTextSize(60);
        p.setTextAlign(Paint.Align.CENTER);
        p.setAntiAlias(true);
        p.setColor(Color.WHITE);
	}


	@Override
	public void update(float deltaTime) {
		List<TouchEvent> touchEvents = game.getInput().getTouchEvents();
		int len = touchEvents.size();
		for (int i = 0; i < len; i++) {
			TouchEvent event = touchEvents.get(i);
			if (event.type == TouchEvent.TOUCH_UP) {
				if (inBounds(event, 0, 0, 250, 250)) {
					//START GAME
					Assets.click.play(1);
					game.setScreen(new GameScreen(game));              
				}
			}
		}
	}


	private boolean inBounds(TouchEvent event, int x, int y, int width,
			int height) {
		if (event.x > x && event.x < x + width - 1 && event.y > y
				&& event.y < y + height - 1)
			return true;
		else
			return false;
	}


	@Override
	public void paint(float deltaTime) {
		Graphics g = game.getGraphics();
		g.drawARGB(155, 0, 0, 0);

		// draw rec
		g.drawRect(0, 0, Assets.resX, Assets.resY, Color.YELLOW);
		// write title
		g.drawString("Android Robots", Assets.resX/2, Assets.resY/3, p);
		g.drawString("Play", Assets.resX/2, 2*Assets.resY/3, p);
		// create play button
	}


	@Override
	public void pause() {
	}


	@Override
	public void resume() {


	}


	@Override
	public void dispose() {


	}


	@Override
	public void backButton() {
		//Display "Exit Game?" Box


	}
}