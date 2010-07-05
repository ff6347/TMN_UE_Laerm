package interaction;

import java.util.ArrayList;

import particleSystem.Property;
import particleSystem.Repeller;
import processing.core.PApplet;
import processing.core.PMatrix3D;
import processing.core.PVector;
import util.Style;
import TUIO.TuioCursor;

/**
 * Object can be transformed by fingers / TuioCursors.
 * 
 */

public class TNObstacleObject extends TNTransformableObject{


	TuioCursor tuioCursor1 = null;
	TuioCursor tuioCursor2 = null;
	
	public Property property;
	
	public ArrayList <Repeller> ObstclsRepellerList;
	public float grav = PApplet.pow(10,3);
	public float radius; 

	
	float oldAngle;
	float oldDist;

	float oldX;
	float oldY;

	public TNObstacleObject(PApplet p, float offsetX, float offsetY, float width,
			float height) {
		super(p, offsetX, offsetY, width, height);
	}
	
	public TNObstacleObject(PApplet p, float offsetX, float offsetY, float width, float height, Property property) {
		super(p, offsetX, offsetY, width, height);
		this.property = property;

	}
	
	public void internalDraw(){
		
		p.fill(Style.textColorWhite);
		p.textFont(Style.MisoBold72);
		p.text(property.name, 0,p.textAscent());
		float ascent = p.textAscent();
		p.noFill();
		p.stroke(Style.tmn_green);
		p.rect(0, 0, width, height);
		super.width = p.textWidth(property.name);
		super.height = ascent;
		p.rect(0, 0, width, height);
		
		doTheRepellers();

	}
	
	public void doTheRepellers(){
		
		radius = height/2;
		
		int howManyRep = PApplet.ceil((width/ radius) +1);
		float howMuchSpace = width / howManyRep;
		
		ObstclsRepellerList = new ArrayList<Repeller>();
		
		for(int i = 1; i < howManyRep; i++){
			
			float repXpos = offsetX + i*howMuchSpace*scale;
			float repYpos = offsetY+ height/2;
			
//			getTransformedPositionWithoutOffset(repXpos, repYpos, true);
			float [] preXY = getTransformedPositionOfRepellers(repXpos, repYpos);; 
			PVector loc = new PVector(preXY[0]*scale, preXY[1]);
//			loc.div(scale);
			ObstclsRepellerList.add(new Repeller(p, loc, grav, radius*scale, property));
			
		}
	}
	
	public float[] getTransformedPositionOfRepellers(float x , float y) {
		float[] preXY = new float[3];
		PMatrix3D m = new PMatrix3D();
		m.apply(matrix);
		
		m.mult(new float[] { x, y, 0 }, preXY);
		return preXY;
	}


	protected float getDistance(TuioCursor tuioCursor1, TuioCursor tuioCursor2) {
		return PApplet.dist(tuioCursor1.getScreenX(p.width), tuioCursor1.getScreenY(p.height),
				tuioCursor2.getScreenX(p.width), tuioCursor2.getScreenY(p.height));
	}

	protected float getAngleBetween(TuioCursor tuioCursor1, TuioCursor tuioCursor2) {
		return getAngleBetween(tuioCursor1.getScreenX(p.width), tuioCursor1.getScreenY(p.height),
				tuioCursor2.getScreenX(p.width), tuioCursor2.getScreenY(p.height));
	}

	public void addTuioCursor(TuioCursor tuioCursor) {
		if (tuioCursor1 == null) {
			tuioCursor1 = tuioCursor;

			oldX = tuioCursor1.getScreenX(p.width);
			oldY = tuioCursor1.getScreenY(p.height);

		} else if (tuioCursor2 == null) {
			tuioCursor2 = tuioCursor;

			oldAngle = getAngleBetween(tuioCursor1, tuioCursor2);
			oldDist = getDistance(tuioCursor1, tuioCursor2);
		} else {
			PApplet.println("Already 2 cursors in use for rotation. Omitting further ones.");
		}
	}

	public void removeTuioCursor(TuioCursor tuioCursor) {
		if (tuioCursor2 != null && tuioCursor2.getCursorID() == tuioCursor.getCursorID()) {
			tuioCursor2 = null;
		}

		if (tuioCursor1 != null && tuioCursor1.getCursorID() == tuioCursor.getCursorID()) {
			tuioCursor1 = null;
			// If second still is on object, switch cursors
			if (tuioCursor2 != null) {
				tuioCursor1 = tuioCursor2;
				tuioCursor2 = null;
				// Shall not jump after switching, so a "new" oldPos is stored for diff calc.
				oldX = tuioCursor1.getScreenX(p.width);
				oldY = tuioCursor1.getScreenY(p.height);
			}
		}
	}

	public void updateTuioCursor(TuioCursor tcur) {
		if (tuioCursor1 != null && tuioCursor2 != null) {
			// Two fingers: rotate and scale

			if (tuioCursor2.getCursorID() == tcur.getCursorID()) {
				centerX = tuioCursor1.getScreenX(p.width) - offsetX;
				centerY = tuioCursor1.getScreenY(p.height) - offsetY;
			} else {
				centerX = tuioCursor2.getScreenX(p.width) - offsetX;
				centerY = tuioCursor2.getScreenY(p.height) - offsetY;
			}

			float newAngle = getAngleBetween(tuioCursor1, tuioCursor2);
			float angle = newAngle - oldAngle;
			oldAngle = newAngle;
			rotate(angle);

			float newDist = getDistance(tuioCursor1, tuioCursor2);
			float newScale = newDist / oldDist;
			oldDist = newDist;
			scale(newScale);

		} else if (tuioCursor1 != null) {
			// One finger: move
			float x = tuioCursor1.getScreenX(p.width);
			float y = tuioCursor1.getScreenY(p.height);
			float dx = x - oldX;
			float dy = y - oldY;

			addOffset(dx, dy);

			oldX = x;
			oldY = y;
		}
	}

}
