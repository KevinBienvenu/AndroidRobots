package com.welcome.androidrobot;


import android.graphics.Color;
import android.graphics.Paint;

import com.welcome.framework.Game;
import com.welcome.framework.Graphics;
import com.welcome.framework.Screen;

public class GameScreen extends Screen {
    enum GameState {
        Ready, Running, Paused, GameOver
    }

    GameState state = GameState.Ready;

    // Variable Setup
    // You would create game objects here.

    int livesLeft = 1;
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
//        List<TouchEvent> touchEvents = game.getInput().getTouchEvents();
//
//        // We have four separate update methods in this example.
//        // Depending on the state of the game, we call different update methods.
//        // Refer to Unit 3's code. We did a similar thing without separating the
//        // update methods.
//
//        if (state == GameState.Ready)
//            updateReady(touchEvents);
//        if (state == GameState.Running)
//            updateRunning(touchEvents, deltaTime);
//        if (state == GameState.Paused)
//            updatePaused(touchEvents);
//        if (state == GameState.GameOver)
//            updateGameOver(touchEvents);
    }



    @Override
    public void paint(float deltaTime) {
        Graphics g = game.getGraphics();

        // First draw the game elements.

        // Example:
        // g.drawImage(Assets.background, 0, 0);
        // g.drawImage(Assets.character, characterX, characterY);

        // Secondly, draw the UI above the game elements.
        if (state == GameState.Ready)
            drawReadyUI();

    }

   
    private void drawReadyUI() {
        Graphics g = game.getGraphics();

        g.drawARGB(155, 0, 0, 0);
        g.drawString("Tap each side of the screen to move in that direction.",
                640, 300, paint);

    }

    
    @Override
    public void pause() {
        if (state == GameState.Running)
            state = GameState.Paused;

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