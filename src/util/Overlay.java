/**
 * 
 */
package util;

import processing.core.PApplet;
import processing.core.PVector;

/**
 * @author fabiantheblind
 * 
 */
public class Overlay {

	PApplet p;
	private PVector center;

	public Overlay(PApplet p) {
		super();
		this.p = p;
		this.center = new PVector(p.width / 2, p.height / 2);
	}

	/**
	 * @return the p
	 */
	public synchronized PApplet getP() {
		return p;
	}

	/**
	 * @param p
	 *            the p to set
	 */
	public synchronized void setP(PApplet p) {
		this.p = p;
	}

	public void initOverlay() {

	}

	public void display() {

		for (int i = 0; i < Style.pathsRadius9.length; i++) {
			int diam = Style.pathsSize9[i] * 2;
			int innerDiam = diam - Style.pathsRadius9[i] * 2;
			int outerDiam = diam + Style.pathsRadius9[i] * 2;
			p.fill(Style.superSoftGrey);
			p.ellipse(center.x, center.y, outerDiam, outerDiam);
			p.fill(Style.superSoftWhite);
			p.ellipse(center.x, center.y, innerDiam, innerDiam);
		}

		p.textFont(Style.MisoBold18);
		p.fill(Style.textColorBlk);
		p.textAlign(PApplet.CENTER);
		p.text("private", this.center.x + Style.pathsSize9[1] ,
				this.center.y);
		p.text("public", this.center.x + Style.pathsSize9[4],
				this.center.y);
		p.text("work", this.center.x + Style.pathsSize9[7],
				this.center.y);
		p.textAlign(PApplet.CORNER);
		
		
	}

	/**
	 * @return the center
	 */
	public synchronized PVector getCenter() {
		return center;
	}

	/**
	 * @param center the center to set
	 */
	public synchronized void setCenter(PVector center) {
		this.center = center;
	}

}
