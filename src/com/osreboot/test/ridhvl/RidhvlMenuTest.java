package com.osreboot.test.ridhvl;

import static com.osreboot.ridhvl.painter.painter2d.HvlPainter2D.hvlDrawQuad;

import org.lwjgl.opengl.Display;
import org.newdawn.slick.Color;

import com.osreboot.ridhvl.action.HvlAction1;
import com.osreboot.ridhvl.action.HvlAction2;
import com.osreboot.ridhvl.display.collection.HvlDisplayModeDefault;
import com.osreboot.ridhvl.menu.HvlButtonMenuLink;
import com.osreboot.ridhvl.menu.HvlComponent;
import com.osreboot.ridhvl.menu.HvlComponentDefault;
import com.osreboot.ridhvl.menu.HvlMenu;
import com.osreboot.ridhvl.menu.component.HvlArrangerBox;
import com.osreboot.ridhvl.menu.component.HvlButton;
import com.osreboot.ridhvl.menu.component.HvlComponentDrawable;
import com.osreboot.ridhvl.menu.component.HvlDrawableComponent;
import com.osreboot.ridhvl.menu.component.HvlArrangerBox.ArrangementStyle;
import com.osreboot.ridhvl.menu.component.HvlLabel;
import com.osreboot.ridhvl.menu.component.HvlSpacer;
import com.osreboot.ridhvl.menu.component.collection.HvlLabeledButton;
import com.osreboot.ridhvl.template.HvlTemplateInteg2D;

public class RidhvlMenuTest extends HvlTemplateInteg2D{

	public static void main(String[] args){
		new RidhvlMenuTest();
	}

	public RidhvlMenuTest(){
		super(144, 768, 768, "Ridhvl(1) Menu Test", new HvlDisplayModeDefault());
	}

	public HvlMenu menu0, menu1, menu2;
	public int menu2Number = 0;

	@Override
	public void initialize(){
		FontUtil.loadFont();
		TextureUtil.loadTexture();

		// ----- Creating Defaults -----------------------
		HvlComponentDefault.setDefault(new HvlArrangerBox(Display.getWidth(), Display.getHeight(), ArrangementStyle.VERTICAL));
		HvlComponentDefault.setDefault(new HvlLabel(FontUtil.getFont(), "DEFAULT TEXT", Color.green));
		HvlComponentDefault.setDefault(new HvlLabeledButton(256f, FontUtil.getHeight() + 4f, new HvlComponentDrawable(){
			@Override
			public void draw(float delta, float x, float y, float width, float height){
				hvlDrawQuad(x, y, width, height, new Color(0.4f, 0.4f, 0.4f));
			}
		}, new HvlComponentDrawable(){
			@Override
			public void draw(float delta, float x, float y, float width, float height){
				hvlDrawQuad(x, y, width, height, new Color(0.5f, 0.5f, 0.5f));
			}
		}, new HvlComponentDrawable(){
			@Override
			public void draw(float delta, float x, float y, float width, float height){
				hvlDrawQuad(x, y, width, height, new Color(0.2f, 0.2f, 0.2f));
			}
		}, FontUtil.getFont(), "DEFAULT TEXT", Color.green));

		// ----- Assembling Menus -----------------------
		menu0 = new HvlMenu();
		menu1 = new HvlMenu();
		menu2 = new HvlMenu();

		// Menu 0: FPS counter and long text
		menu0.add(new HvlArrangerBox.Builder().build());
		menu0.getFirstArrangerBox().add(new HvlLabel.Builder().setText("Menu Test!").build());
		menu0.getFirstArrangerBox().add(new HvlSpacer(16f, 16f));
		menu0.getFirstArrangerBox().add(new HvlLabel.Builder().setScale(0.5f).build());
		menu0.getFirstArrangerBox().add(new HvlSpacer(16f, 16f));
		menu0.getFirstArrangerBox().add(new HvlLabel.Builder().setScale(0.75f)
				.setText("A comparison between Ridhvl(1) and").build());
		menu0.getFirstArrangerBox().add(new HvlLabel.Builder().setScale(0.75f)
				.setText("Ridhvl2 menu systems.").build());
		menu0.getFirstArrangerBox().add(new HvlSpacer(16f, 16f));
		menu0.getFirstArrangerBox().add(new HvlLabeledButton.Builder().setText("Button?")
				.setClickedCommand(new HvlButtonMenuLink(menu1)).build());

		// Menu 1: Horizontal nested arranger, custom component draw function (image)
		menu1.add(new HvlArrangerBox.Builder().build());
		menu1.getFirstArrangerBox().add(new HvlLabel.Builder().setText("Wow what a menu...").build());
		menu1.getFirstArrangerBox().add(new HvlSpacer(16f, 16f));
		menu1.getFirstArrangerBox().add(new HvlArrangerBox.Builder().setStyle(ArrangementStyle.HORIZONTAL).setHeight(256f).build());
		menu1.getFirstArrangerBox().getFirstOfType(HvlArrangerBox.class)
		.add(new HvlLabel.Builder().setText("Who the heck\nis this guy? ->").build());
		menu1.getFirstArrangerBox().getFirstOfType(HvlArrangerBox.class).add(new HvlSpacer(16f, 16f));
		menu1.getFirstArrangerBox().getFirstOfType(HvlArrangerBox.class)
		.add(new HvlDrawableComponent(256f, 256f, new HvlComponentDrawable(){
			@Override
			public void draw(float delta, float x, float y, float width, float height){
				hvlDrawQuad(x, y, width, height, Color.white);
				hvlDrawQuad(x, y, width, height, TextureUtil.getMysteriousTexture());
			}
		}));
		menu1.getFirstArrangerBox().add(new HvlSpacer(16f, 16f));
		menu1.getFirstArrangerBox().add(new HvlLabeledButton.Builder().setText("Button!")
				.setClickedCommand(new HvlButtonMenuLink(menu2)).build());

		// Menu 2: Custom label draw function (moving background) and label with variable-controlled text in nested arranger
		menu2.add(new HvlArrangerBox.Builder().build());
		menu2.getFirstArrangerBox().add(new HvlLabel.Builder().setText("Pretty cool menu test.").build());
		menu2.getFirstArrangerBox().add(new HvlSpacer(16f, 16f));
		menu2.getFirstArrangerBox().add(new HvlLabel.Builder().setText("This is a...").setScale(0.5f).build());
		menu2.getFirstArrangerBox().add(new HvlSpacer(16f, 16f));
		menu2.getFirstArrangerBox().add(new HvlLabel.Builder().setText("CRAZY LABEL").setColor(Color.black)
				.setDrawOverride(new HvlAction2<HvlComponent, Float>(){
					@Override
					public void run(HvlComponent c, Float d){
						hvlDrawQuad(c.getX() - 2f, c.getY() - 2f, c.getWidth() + 4f, c.getHeight() + 4f,
								TextureUtil.getOffset(), 0, TextureUtil.getOffset() + 1f, 0.8125f, TextureUtil.getCrazyTexture());
						c.draw(d);
					}
				}).build());
		menu2.getFirstArrangerBox().add(new HvlSpacer(16f, 16f));
		menu2.getFirstArrangerBox().add(new HvlLabel.Builder().setText("...and a...").setScale(0.5f).build());
		menu2.getFirstArrangerBox().add(new HvlSpacer(16f, 16f));
		menu2.getFirstArrangerBox().add(new HvlArrangerBox.Builder().setStyle(ArrangementStyle.HORIZONTAL).setHeight(64f).build());
		menu2.getFirstArrangerBox().getFirstOfType(HvlArrangerBox.class).add(new HvlLabel.Builder().build());
		menu2.getFirstArrangerBox().getFirstOfType(HvlArrangerBox.class).add(new HvlSpacer(32f, 32f));
		menu2.getFirstArrangerBox().getFirstOfType(HvlArrangerBox.class)
		.add(new HvlLabeledButton.Builder().setText("INCREASE").setTextColor(Color.red)
				.setClickedCommand(new HvlAction1<HvlButton>(){
					@Override
					public void run(HvlButton a) {
						menu2Number++;
					}
				}).build());
		menu2.getFirstArrangerBox().add(new HvlSpacer(16f, 16f));
		menu2.getFirstArrangerBox().add(new HvlLabel.Builder().setText("...or...").setScale(0.5f).build());
		menu2.getFirstArrangerBox().add(new HvlSpacer(16f, 16f));
		menu2.getFirstArrangerBox().add(new HvlLabeledButton.Builder().setText("...Button")
				.setClickedCommand(new HvlButtonMenuLink(menu0)).build());

		HvlMenu.setCurrent(menu0);
	}

	@Override
	public void update(float delta){
		// ----- Updating FPS Counter --------------------
		menu0.getFirstArrangerBox().getChildOfType(HvlLabel.class, 1).setText("Current FPS: " + String.format("%.2f", getTimer().getUpdateRate()));

		// ----- Updating Menu 2 Number Label ------------
		menu2.getFirstArrangerBox().getFirstOfType(HvlArrangerBox.class).getFirstOfType(HvlLabel.class).setText("Number: " + menu2Number);

		// ----- Updating & Drawing Menus ----------------
		HvlMenu.updateMenus(delta);
	}

}
