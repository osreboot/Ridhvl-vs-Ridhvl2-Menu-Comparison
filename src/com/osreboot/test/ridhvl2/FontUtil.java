package com.osreboot.test.ridhvl2;

import static com.osreboot.ridhvl2.HvlStatics.hvlLoad;
import static com.osreboot.ridhvl2.HvlStatics.hvlTexture;

import com.osreboot.ridhvl2.HvlConfig;
import com.osreboot.ridhvl2.menu.HvlFont;

public final class FontUtil {
	private FontUtil(){}

	private static HvlFont font;
	
	public static void loadFont(){
		hvlLoad("INOF.png");
		font = HvlConfig.PJSON.load("res/INOF.hvlft");
		font.setLoadedTexture(hvlTexture(0));
		font.set(HvlFont.TAG_X_SPACING, 16f);
		font.set(HvlFont.TAG_SCALE, 0.25f);
	}
	
	public static HvlFont getFont(){
		return font;
	}
	
	public static float getHeight(){
		return font.getHeight("A", 1f);
	}
	
}
