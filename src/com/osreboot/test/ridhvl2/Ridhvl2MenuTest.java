package com.osreboot.test.ridhvl2;

import static com.osreboot.ridhvl2.HvlStatics.hvlColor;
import static com.osreboot.ridhvl2.HvlStatics.hvlDraw;
import static com.osreboot.ridhvl2.HvlStatics.hvlQuad;

import org.newdawn.slick.Color;

import com.osreboot.ridhvl2.menu.HvlDefault;
import com.osreboot.ridhvl2.menu.HvlMenu;
import com.osreboot.ridhvl2.menu.component.HvlArranger;
import com.osreboot.ridhvl2.menu.component.HvlButton.HvlButtonState;
import com.osreboot.ridhvl2.menu.component.HvlButtonLabeled;
import com.osreboot.ridhvl2.menu.component.HvlLabel;
import com.osreboot.ridhvl2.menu.component.HvlSpacer;
import com.osreboot.ridhvl2.template.HvlDisplayWindowed;
import com.osreboot.ridhvl2.template.HvlTemplateI;

public class Ridhvl2MenuTest extends HvlTemplateI{

	public static void main(String[] args){
		new Ridhvl2MenuTest();
	}

	public Ridhvl2MenuTest(){
		super(new HvlDisplayWindowed(144, 768, 768, "Ridhvl2 Menu Test", false));
	}

	public HvlArranger menu0, menu1, menu2;
	public int menu2Number = 0;

	@Override
	public void initialize(){
		FontUtil.loadFont();
		TextureUtil.loadTexture();

		// ----- Creating Defaults -----------------------
		HvlDefault.put(new HvlArranger(false, 0.5f, 0.5f));
		HvlDefault.put(new HvlSpacer(16f));
		HvlDefault.put(new HvlLabel(FontUtil.getFont(), "DEFAULT TEXT", Color.green, 1f).align(0.5f, 0.5f)
				.overrideHeight(FontUtil.getHeight()));
		HvlDefault.put(new HvlButtonLabeled(FontUtil.getFont(), "DEFAULT TEXT", Color.green, 1f, (d, e, b, s) -> {
			if(s == HvlButtonState.OFF) hvlDraw(hvlQuad(e.getX(), e.getY(), e.getWidth(), e.getHeight()), hvlColor(0.4f, 1f));
			if(s == HvlButtonState.HOVER) hvlDraw(hvlQuad(e.getX(), e.getY(), e.getWidth(), e.getHeight()), hvlColor(0.5f, 1f));
			if(s == HvlButtonState.ON) hvlDraw(hvlQuad(e.getX(), e.getY(), e.getWidth(), e.getHeight()), hvlColor(0.2f, 1f));
		}).align(0.5f, 0.5f).overrideSize(256f, FontUtil.getHeight() + 4f));

		// ----- Assembling Menus -----------------------
		// Menu 0: FPS counter and long text
		menu0 = HvlArranger.fromDefault();
		menu0.add(HvlLabel.fromDefault().text("Menu Test!"));
		menu0.add(HvlSpacer.fromDefault());
		menu0.add(HvlLabel.fromDefault().scale(0.5f).autoSize().name("fps"));
		menu0.add(HvlSpacer.fromDefault());
		menu0.add(HvlLabel.fromDefault().text("A comparison between Ridhvl(1) and").scale(0.75f).autoSize());
		menu0.add(HvlLabel.fromDefault().text("Ridhvl2 menu systems.").scale(0.75f).autoSize());
		menu0.add(HvlSpacer.fromDefault());
		menu0.add(HvlButtonLabeled.fromDefault().text("Button?").clicked(b -> HvlMenu.set(menu1)));

		// Menu 1: Horizontal nested arranger, custom component draw function (image)
		menu1 = HvlArranger.fromDefault();
		menu1.add(HvlLabel.fromDefault().text("Wow what a menu..."));
		menu1.add(HvlSpacer.fromDefault());
		menu1.add(HvlArranger.fromDefault().set(HvlArranger.TAG_HORIZONTAL, true).overrideHeight(256f).name("os"));
		menu1.<HvlArranger>find("os").add(HvlLabel.fromDefault().text("Who the heck\\nis this guy? ->").autoSize());
		menu1.<HvlArranger>find("os").add(HvlSpacer.fromDefault());
		menu1.<HvlArranger>find("os").add(new HvlSpacer(256f).set(HvlSpacer.TAG_DRAW, (d, e, c) -> {
			hvlDraw(hvlQuad(e.getX(), e.getY(), e.getWidth(), e.getHeight()), Color.white);
			hvlDraw(hvlQuad(e.getX(), e.getY(), e.getWidth(), e.getHeight()), TextureUtil.getMysteriousTexture());
		}));
		menu1.add(HvlSpacer.fromDefault());
		menu1.add(HvlButtonLabeled.fromDefault().text("Button!").clicked(b -> HvlMenu.set(menu2)));

		// Menu 2: Custom label draw function (moving background) and label with variable-controlled text in nested arranger
		menu2 = HvlArranger.fromDefault();
		menu2 = HvlArranger.fromDefault();
		menu2.add(HvlLabel.fromDefault().text("Pretty cool menu test."));
		menu2.add(HvlSpacer.fromDefault());
		menu2.add(HvlLabel.fromDefault().text("This is a...").scale(0.5f).autoSize());
		menu2.add(HvlSpacer.fromDefault());
		menu2.add(HvlLabel.fromDefault().text("CRAZY LABEL").autoSize().set(HvlLabel.TAG_COLOR, Color.black)
				.set(HvlLabel.TAG_DRAW, (d, e, c) -> {
					hvlDraw(hvlQuad(e.getX() - 2f, e.getY() - 2f, e.getWidth() + 4f, e.getHeight() + 4f,
							TextureUtil.getOffset(), 0, TextureUtil.getOffset() + 1f, 0.8125f), TextureUtil.getCrazyTexture());
					HvlLabel.DEFAULT_DRAW.run(d, e, c);
				}));
		menu2.add(HvlSpacer.fromDefault());
		menu2.add(HvlLabel.fromDefault().text("...and a...").scale(0.5f).autoSize());
		menu2.add(HvlSpacer.fromDefault());
		menu2.add(HvlArranger.fromDefault().set(HvlArranger.TAG_HORIZONTAL, true).overrideHeight(64f).name("box"));
		menu2.<HvlArranger>find("box").add(HvlLabel.fromDefault().name("number"));
		menu2.<HvlArranger>find("box").add(new HvlSpacer(32f));
		menu2.<HvlArranger>find("box").add(HvlButtonLabeled.fromDefault().text("INCREASE")
				.clicked(b -> menu2Number++).set(HvlButtonLabeled.TAG_TEXT_COLOR, Color.red));
		menu2.add(HvlSpacer.fromDefault());
		menu2.add(HvlLabel.fromDefault().text("...or...").scale(0.5f).autoSize());
		menu2.add(HvlSpacer.fromDefault());
		menu2.add(HvlButtonLabeled.fromDefault().text("...Button").clicked(b -> HvlMenu.set(menu0)));

		HvlMenu.set(menu0);
	}

	@Override
	public void update(float delta){
		// ----- Updating FPS Counter --------------------
		menu0.<HvlLabel>find("fps").text("Current FPS: " + String.format("%.2f", getTimer().getTickRate()));

		// ----- Updating Menu 2 Number Label ------------
		menu2.<HvlLabel>find("number").text("Number: " + menu2Number).autoSize();
		
		// ----- Updating & Drawing Menus ----------------
		HvlMenu.operate(delta);
	}

}
