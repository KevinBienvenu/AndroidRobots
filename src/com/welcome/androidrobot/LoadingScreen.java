package com.welcome.androidrobot;

import java.util.Vector;

import com.welcome.framework.Game;
import com.welcome.framework.Graphics;
import com.welcome.framework.Graphics.ImageFormat;
import com.welcome.framework.Image;
import com.welcome.framework.Screen;




public class LoadingScreen extends Screen {
    public LoadingScreen(Game game) {
        super(game);
    }


    @Override
    public void update(float deltaTime) {
        Graphics g = game.getGraphics();
        Assets.menu = g.newImage("menu.jpg", ImageFormat.RGB565);
        Assets.symboles = new Vector<Image>();
        Assets.symboles.add(g.newImage("alpha.png", ImageFormat.ARGB4444));
        Assets.symboles.add(g.newImage("beta.png", ImageFormat.ARGB4444));
        Assets.symboles.add(g.newImage("gamma.png", ImageFormat.ARGB4444));
        Assets.symboles.add(g.newImage("delta.png", ImageFormat.ARGB4444));
        Assets.symboles.add(g.newImage("epsilon.png", ImageFormat.ARGB4444));
        Assets.symboles.add(g.newImage("eta.png", ImageFormat.ARGB4444));
        Assets.symboles.add(g.newImage("zeta.png", ImageFormat.ARGB4444));
        Assets.symboles.add(g.newImage("theta.png", ImageFormat.ARGB4444));
        Assets.nbSymboles = Assets.symboles.size();
        Assets.click = game.getAudio().createSound("explode.ogg");
        game.setScreen(new MainMenuScreen(game));
    }


    @Override
    public void paint(float deltaTime) {


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


    }
}