package com.welcome.androidrobot;

import com.welcome.framework.Screen;
import com.welcome.framework.implementation.AndroidGame;

import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.os.Build;
import android.util.DisplayMetrics;
import android.view.Display;

public class Robots extends AndroidGame {
    @Override
    public Screen getInitScreen() {
    	setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    	this.initScreenSize();
        return new LoadingScreen(this);
    }
   
    @Override
    public void onBackPressed() {
    getCurrentScreen().backButton();
    }
    
    public void initScreenSize(){
    	Display d = getWindowManager().getDefaultDisplay();
    	DisplayMetrics metrics = new DisplayMetrics();
    	int x, y;
    	d.getMetrics(metrics);
    	// since SDK_INT = 1;
    	x = metrics.widthPixels;
    	y = metrics.heightPixels;
    	// includes window decorations (statusbar bar/menu bar)
    	if (Build.VERSION.SDK_INT >= 14 && Build.VERSION.SDK_INT < 17)
    	try {
    	    x = (Integer) Display.class.getMethod("getRawWidth").invoke(d);
    	    y = (Integer) Display.class.getMethod("getRawHeight").invoke(d);
    	} catch (Exception ignored) {
    	}
    	// includes window decorations (statusbar bar/menu bar)
    	if (Build.VERSION.SDK_INT >= 17)
    	try {
    	    Point realSize = new Point();
    	    Display.class.getMethod("getRealSize", Point.class).invoke(d, realSize);
    	    x = realSize.x;
    	    y = realSize.y;
    	} catch (Exception ignored) {
    	}
//        Assets.resY = Math.min(x,y);
//        Assets.resX = Math.max(x,y);
        Assets.resX = Math.min(x,y);
        Assets.resY = Math.max(x,y);
        
        Assets.tailleCase = Assets.resX / Assets.nColonnes;
        Assets.boardStartX = (Assets.resX-Assets.tailleCase*Assets.nColonnes)/2;
    	Assets.barSizeY = Assets.resY/15;
    	Assets.barStartY = 0;
    	Assets.boardStartY = Assets.barSizeY+Assets.boardStartX;
    	Assets.boardSizeY = Assets.resX;
    	Assets.optionStartY = Assets.boardStartY + Assets.boardSizeY;
    	Assets.optionSizeY = Assets.resY - Assets.optionStartY;

        Assets.paint.setTextSize(40);
        Assets.paint.setTextAlign(Paint.Align.CENTER);
        Assets.paint.setAntiAlias(true);
        Assets.paint.setColor(Color.WHITE);
    }
    
}
