package com.osreboot.test.ridhvl;

import org.newdawn.slick.opengl.Texture;

import com.osreboot.ridhvl.template.HvlTemplateInteg2D;

public final class TextureUtil {
	private TextureUtil(){}
	
	public static void loadTexture(){
		((HvlTemplateInteg2D)RidhvlMenuTest.getNewestInstance()).getTextureLoader().loadResource("OS_REBOOT");
		((HvlTemplateInteg2D)RidhvlMenuTest.getNewestInstance()).getTextureLoader().loadResource("CRAZY_RESIZED");
	}
	
	public static Texture getMysteriousTexture(){
		return RidhvlMenuTest.getTexture(1);
	}
	
	public static Texture getCrazyTexture(){
		return RidhvlMenuTest.getTexture(2);
	}
	
	public static float getOffset(){
		return RidhvlMenuTest.getNewestInstance().getTimer().getTotalTime() % 1f;
	}

}
