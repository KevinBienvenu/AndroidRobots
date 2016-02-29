package com.welcome.androidrobot;

import com.welcome.framework.Screen;
import com.welcome.framework.implementation.AndroidGame;

import android.content.pm.ActivityInfo;
import android.graphics.Point;
import android.os.Build;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;

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
    	WindowManager w = getWindowManager();
    	Display d = w.getDefaultDisplay();
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
        Assets.resX = Math.min(x,y);
        Assets.resY = Math.max(x,y);
    }
    
}
