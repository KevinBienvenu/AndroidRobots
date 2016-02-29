package com.welcome.androidrobot;

import com.welcome.framework.Image;
import com.welcome.framework.Sound;

import android.graphics.Color;
import android.graphics.Paint;

public class Assets {
	
	// Variables
	public static boolean verticalScreen;
	public static int resX;
	public static int resY;
	
	// GameScreen
	public static int barSizeY;
	public static int barStartY;
	public static int boardStartY;
	public static int boardSizeY;
	public static int optionStartY;
	public static int optionSizeY;
	
	// Plateau et Cases
	public static int nColonnes = 13, nLignes = 13;
	public static int tailleCase;
	public static float ratioPion = 0.8f;
	public static float ratioMur = 0.1f;
	public static int nbMur = 12;
	public static float probaMur = 0.01f;
	
	// Police et paint
	public static Paint paint = new Paint();
   
	// Images

    public static Image menu;
    
    // Sounds
    
    public static Sound click;
}
