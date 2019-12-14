package com.osreboot.test.ridhvl2;

import static com.osreboot.ridhvl2.HvlStatics.hvlLoad;
import static com.osreboot.ridhvl2.HvlStatics.hvlTexture;

import org.newdawn.slick.opengl.Texture;

public final class TextureUtil {
	private TextureUtil(){}
	
	public static void loadTexture(){
		hvlLoad("OS_REBOOT.png");
		hvlLoad("CRAZY.png");
	}
	
	public static Texture getMysteriousTexture(){
		return hvlTexture(1);
	}
	
	public static Texture getCrazyTexture(){
		return hvlTexture(2);
	}
	
	public static float getOffset(){
		return Ridhvl2MenuTest.newest().getTimer().getTotalTime() % 1f;
	}
	
}