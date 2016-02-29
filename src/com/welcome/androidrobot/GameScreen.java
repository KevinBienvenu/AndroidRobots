package com.welcome.androidrobot;


import android.graphics.Color;
import android.graphics.Paint;

import com.welcome.framework.Game;
import com.welcome.framework.Graphics;
import com.welcome.framework.Screen;

public class GameScreen extends Screen {
    
	Plateau plateau;
	Partie partie;
	
    Paint paint;

    public GameScreen(Game game) {
        super(game);

        // Initialize game objects here

        // Defining a paint object
        paint = new Paint();
        paint.setTextSize(30);
        paint.setTextAlign(Paint.Align.CENTER);
        paint.setAntiAlias(true);
        paint.setColor(Color.WHITE);

    }

    @Override
    public void update(float deltaTime) {

    }



    @Override
    public void paint(float deltaTime) {
        Graphics g = game.getGraphics();
        g.fillRect(0, 0, Assets.resX, Assets.resY, Color.DKGRAY);
        g.fillRect(0, Assets.barStartY, Assets.resX, Assets.barSizeY, Color.BLACK);
        g.drawRect(0, Assets.barStartY, Assets.resX, Assets.barSizeY, Color.WHITE);
        g.drawRect(0, Assets.optionStartY, Assets.resX+20, Assets.optionSizeY, Color.WHITE);
        plateau.paint(g);
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
        pause();
    }
}