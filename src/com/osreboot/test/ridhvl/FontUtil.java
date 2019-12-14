package com.osreboot.test.ridhvl;

import com.osreboot.ridhvl.painter.painter2d.HvlFontPainter2D;
import com.osreboot.ridhvl.template.HvlTemplateInteg2D;

public final class FontUtil {
	private FontUtil(){}
	
	private static HvlFontPainter2D font;
	
	public static void loadFont(){
		((HvlTemplateInteg2D)RidhvlMenuTest.getNewestInstance()).getTextureLoader().loadResource("INOF");
		font = new HvlFontPainter2D(RidhvlMenuTest.getTexture(0), HvlFontPainter2D.Preset.FP_INOFFICIAL);
		font.setCharSpacing(16f);
		font.setScale(0.25f);
	}
	
	public static HvlFontPainter2D getFont(){
		return font;
	}
	
	public static float getHeight(){
		return font.getLineHeight("A");
	}

}
