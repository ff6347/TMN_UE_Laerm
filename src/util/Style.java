package util;

import processing.core.PApplet;
import processing.core.PFont;
import processing.core.PImage;

/**
 * Here we make all the color and text stuff centralized. Its like a CSS<br>
 * 
 * @author fabiantheblind
 * 
 */
public class Style {

	/**
	 * the PApplet
	 */
	private static PApplet p;

	/**
	 * Color 1
	 */
	public static int ptclCol1;
	/**
	 * Color 2
	 */
	public static int ptclCol2;
	/**
	 * Color 3
	 */
	public static int ptclCol3;

	public static int tmn_green;

	public static int tmn_lightBlue;

	public static int tmn_blue;

	public static int tmn_darkBlue;

	public static int tmn_lightRed;

	public static int tmn_red;

	public static int tmn_darkRed;

	public static int tmn_lightLilac;

	public static int tmn_lilac;

	public static int tmn_darkLilac;

	public static int superSoftWhite;

	public static int activeColor;
	public static int inactiveCol;
	public static int tintingH;

	/**
	 * PDXIII background Stuff
	 */
	public static float tinter;
	/**
	 * PDXIII background Stuff
	 */
	public static boolean tintBack = false;
	/**
	 * PDXIII background Stuff
	 */
	public static float tintMax = 60;
	/**
	 * PDXIII background Stuff
	 */
	public static float tintMin = 20;

	/**
	 * PDXIII background Stuff
	 */
	public static float tintSize = tintMax - tintMin;
	/**
	 * PDXIII background Stuff
	 */
	public static PImage fadingBG;
	/**
	 * If you wan't the Text white
	 */
	public static int textColorWhite;

	/**
	 * if u want it black
	 */
	public static int textColorBlk;

	/**
	 * the color of the clearscreen
	 * 
	 * @see <a
	 *      href="../tmnuelaerm/TmnUELaerm.html#clearScreen()"><code>TMN_UE_Laerm.clearScreen()</code></a>
	 */
	public static int clsColor;

	/**
	 * to set the PApplet for the whole Class
	 * 
	 * @param _p
	 */
	public static void setPApplet(PApplet _p) {
		p = _p;
	}

	public static PFont MisoBold11;
	public static PFont MisoReg13;
	public static PFont MisoBold18;
	public static PFont MisoBold36;
	public static PFont MisoBold72;
	public static PFont MisoBold144;

	/**
	 * Create all colors and stuff
	 */
	public static void create() {

		// colors
		ptclCol1 = p.color(0, 0, 100, 100);
		ptclCol2 = p.color(0, 0, 100, 100);
		ptclCol3 = p.color(360, 0, 0, 100);

		tmn_green = p.color(107, 100, 100);

		tmn_lightBlue = p.color(210, 10, 100, 80);
		tmn_blue = p.color(210, 50, 100, 80);
		tmn_darkBlue = p.color(210, 90, 100, 80);

		tmn_lightRed = p.color(20, 10, 100, 80);
		tmn_red = p.color(20, 50, 100, 80);
		tmn_darkRed = p.color(20, 90, 100, 80);

		tmn_lightLilac = p.color(280, 10, 100, 80);
		tmn_lilac = p.color(280, 50, 100, 80);
		tmn_darkLilac = p.color(280, 90, 100, 80);

		textColorWhite = p.color(255, 0, 100, 100);
		textColorBlk = p.color(255, 0, 0, 100);

		superSoftWhite = p.color(0, 0, 100, 5);

		activeColor = p.color(0);
		inactiveCol = p.color(0, 23);

		tintingH = 220;

		clsColor = p.color(255, 100, 0, 100);

		// PDXIII TUIO Stuff
		// enable on system installed fonts
		p.hint(PApplet.ENABLE_NATIVE_FONTS);
		MisoBold11 = p.createFont("Miso-Bold", 11);
		MisoReg13 = p.createFont("Miso", 13);
		MisoBold18 = p.createFont("Miso-Bold", 18);
		MisoBold36 = p.createFont("Miso-Bold", 36);
		MisoBold72 = p.createFont("Miso-Bold", 72);
		// MisoBold144 = p.createFont("Miso-Bold", 144);

		// PDXIII background Stuff
		fadingBG = p.loadImage("fadingBG.png");
	}

	public static boolean switchTime(boolean day) {

		if (Style.tinter > Style.tintSize / 2 + Style.tintMin) {
			day = true;
		} else {
			day = false;
		}
		return day;

	}

	public static boolean switchPath(boolean day, boolean switchPath) {

		
			if (p.frameCount % (Style.tintSize) == 0){
				switchPath = true;
			return switchPath;
		}else{
			switchPath = false;
			return switchPath;
		}
	}

	public static void theBackground() {

//		if (Style.tinter > Style.tintSize / 2 + Style.tintMin) {
//			DAY = true;
//			switchPath = true;
//		} else {
//			DAY = false;
//			switchPath = true;
//		}

		p.colorMode(PApplet.HSB, 360, 100, 100);
		p.tint(220, 40 + tinter, 40 + tinter, 100);
		p.image(fadingBG, 0, 0);
		if (tinter >= tintMax) {
			tintBack = true;
		}

		if (tinter <= tintMin) {
			tintBack = false;
		}

		if (!tintBack) {
			tinter += 0.2f;
		} else {
			tinter -= 0.2f;
		}
	}

	/**
	 * A Clear Screen Method writes a
	 * {@code processing.core.PApplet.rect(float, float, float, float)} every
	 * Frame
	 * 
	 * @deprecated
	 */
	public static void clearScreen() {
		p.noStroke();
		p.fill(Style.clsColor);
		p.rect(0, 0, p.width, p.height);

	}

	/**
	 * PDXIII background Stuff
	 * 
	 * @param tinter
	 * @deprecated
	 */
	void drawBG(float tinter) {

		// PDXIII background Stuff
		p.tint(tinter, 255, 255, 100);
		p.image(Style.fadingBG, 0, 0);
		tinter += 0.5f;
		if (tinter > 360) {
			tinter = 0;
		}
		// end PDXIII background Stuff

	}
}
