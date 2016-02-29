package com.welcome.framework;


import android.graphics.Paint;

public interface Graphics {
	public static enum ImageFormat {
		ARGB8888, ARGB4444, RGB565
	}

	public Image newImage(String fileName, ImageFormat format);

	public void clearScreen(int color);

	public void drawLine(int x, int y, int x2, int y2, int color);

	public void drawRect(int x, int y, int width, int height, int color);
	
	public void fillRect(int x, int y, int width, int height, int color);
	
	public void drawOval(int x, int y, int width, int height, int color);
	
	public void fillOval(int x, int y, int width, int height, int color);
	
	public void fillOval(int x, int y, int width, int height, int color, int alpha);
	
	public void fillArc(int x, int y, int width, int height, int startAngle, int angle, int color, int alpha);

	public void drawImage(Image image, int x, int y, int srcX, int srcY,
			int srcWidth, int srcHeight);

	public void drawImage(Image Image, int x, int y);
	
	public void drawImage(Image Image, int x, int y, int finalSize);
	
	void drawString(String text, int x, int y, Paint paint);

	public int getWidth();

	public int getHeight();

	public void drawARGB(int i, int j, int k, int l);

}
