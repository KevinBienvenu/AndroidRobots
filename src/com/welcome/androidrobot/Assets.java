package com.welcome.androidrobot;

import com.welcome.framework.Image;
import com.welcome.framework.Sound;

public class Assets {
	
	// Variables
	public static boolean verticalScreen;
	public static int resX;
	public static int resY;
	public static int tolerance=3;
	
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
	public static float probaMur = 0.01f;
   
	// Images

    public static Image menu;
    
    // Sounds
    
    public static Sound click;
}
