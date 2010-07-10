package particleSystem;

import java.util.ArrayList;

import processing.core.PApplet;
import processing.core.PVector;
import util.Style;

//Path Following
//Daniel Shiffman <http://www.shiffman.net>
//The Nature of Code, Spring 2009
/**
 * The Path the Particles can follow.<br>
 * a Path is a series of connected particles. They interact with the Repellers
 * and so... based on: <a href="http://www.shiffman.net/teaching/nature/"
 * target="blanc">Daniel Shiffman's Nature of Code</a>
 * 
 * @author fabianthelbind
 * 
 */
public class Path {

	/**
	 * A Path is an ArrayList of Particles (PVector objects)
	 * 
	 */
	private ArrayList<Particle> ptclPoints;

	/**
	 * the PApplet
	 */
	private PApplet p;

	/**
	 * A path has a radius, i.e how far is it ok for the particle to wander off
	 */
	private float radius;

	/**
	 * builds a basic Path
	 * 
	 * @param p
	 *            the PApplet
	 */
	public Path(PApplet p) {
		// Arbitrary radius of 20
		this.p = p;

		// points = new ArrayList<PVector>();
		ptclPoints = new ArrayList<Particle>();

	}

	/**
	 * builds a Path with an specific Radius around the pathline
	 * 
	 * @param p
	 *            the PApplet
	 * @param radius
	 *            the radius around the path
	 */
	public Path(PApplet p, float radius) {
		this.p = p;
		this.radius = radius;
		// points = new ArrayList<PVector>();
		ptclPoints = new ArrayList<Particle>();

	}

	// Add a point to the path
	// public void addPoint(float x, float y) {
	// PVector point = new PVector(x,y);
	// points.add(point);
	// }

	/**
	 * Adds a point that behaves like a Particle to the path
	 * 
	 * @param x
	 * @param y
	 * @see Class Particle Class
	 */
	public void addPointPtcl(float x, float y) {
		PVector pos = new PVector(x, y);
		// PVector vel = new PVector(0,0);
		Particle ptcl = new Particle(p, pos, false, true);
		ptcl.setMass(0.5f);
		ptcl.setGravity(0f);
		ptcl.setMaxforce(0.1f);
		ptcl.setMaxspeed(1.5f);
		ptcl.setRadius(0.1f);
		// ptcl.setPathNum(10);
		ptclPoints.add(ptcl);

		// PVector point = new PVector(x,y);
		// points.add(point);
	}

	/**
	 * makes the points of the path move back to their origin if this is not
	 * called within every loop of the draw() the points of the path behave like
	 * normal Particle
	 * 
	 */
	public void resetPointPtcls() {


		// p.fill(255);
		// p.ellipse(testPtcl.loc.x,testPtcl.loc.y,10,10);
		// p.println(dist/1000);
		// p.println(testPtcl.mass);

		Particle ptcl = null;
		float d;
		for (int i = 0; i < this.ptclPoints.size(); i++) {
			ptcl = this.ptclPoints.get(i);

			d = ptcl.getLoc().dist(ptcl.getOrigin());

			if (ptcl.isHidden() == true) {
				ptcl.seek(ptcl.getOrigin());

				if (ptcl.getMass() < 0.8) {
					ptcl.setMass(ptcl.getMass() + (d / 500));
				}
				ptcl.setGravity(0f);
				if (ptcl.getMaxspeed() < 0.1f) {
					ptcl.setMaxforce(ptcl.getMaxforce() + (d / 1000));
				}
				if (ptcl.getMaxspeed() < 0.5f) {
					ptcl.setMaxspeed(ptcl.getMaxspeed() + (d / 100000));
				}
				ptcl.setRadius(0.01f);
				ptcl.setHidden(true);

			}
		}

	}

	/**
	 * @return ArrayList of Particle
	 */
	public ArrayList<Particle> getPtclPointList() {
		return this.ptclPoints;
	}

	// public int pathPoinsListSize(){
	// return points.size();
	// }
	//
	// public PVector getPathPointVector(int i){
	// return this.points.get(i);
	// }

	/**
	 * this returns the specific location of a Point of the Path
	 * 
	 * @param i
	 *            to choose the point of the path
	 * @return Particle
	 */
	public Particle getPathPtclPointVector(int i) {
		return this.ptclPoints.get(i);
	}

	/**
	 * @return the ptclPoints
	 */
	public synchronized ArrayList<Particle> getPtclPoints() {
		return ptclPoints;
	}

	/**
	 * set the radius oarund the path
	 * 
	 * @param radius
	 */
	public void setRadius(float radius) {
		this.radius = radius;
	}

	/**
	 * get the radius around the path
	 * 
	 * @return the radius around the path
	 */
	public float getRadius() {
		return this.radius;
	}

	// public void updatePath(float radius_){
	//
	// radius = radius_;
	// }

	// Draw the path

	// /**
	// * this is the old way to build a path
	// *
	// */
	// public void display() {
	//
	// // Draw the radius as thick lines and circles
	//
	// // Draw end points
	// for (int i = 0; i < points.size(); i++) {
	// PVector point = (PVector) points.get(i);
	// p.fill(175);
	// p.noStroke();
	// p.ellipse(point.x,point.y,radius*2,radius*2);
	// }
	//
	// // Draw Polygon around path
	// for (int i = 0; i < points.size(); i++) {
	// PVector start = (PVector) points.get(i);
	// // We're assuming path is a circle in this example
	// PVector end = (PVector) points.get((i+1)%points.size());
	// PVector line = PVector.sub(end,start);
	// PVector normal = new PVector(line.y,-line.x);
	// normal.normalize();
	// normal.mult(radius);
	//
	// // Polygon has four vertices
	// PVector a = PVector.add(start, normal);
	// PVector b = PVector.add(end, normal);
	// PVector c = PVector.sub(end, normal);
	// PVector d = PVector.sub(start, normal);
	//
	// p.fill(175);
	// p.noStroke();
	// p.beginShape();
	// p.vertex(a.x,a.y);
	// p.vertex(b.x,b.y);
	// p.vertex(c.x,c.y);
	// p.vertex(d.x,d.y);
	// p.endShape();
	// }
	//
	// // Draw Regular Line
	// p.stroke(255);
	// p.strokeWeight(3);
	// p.noFill();
	// p.beginShape();
	// for (int i = 0; i < points.size(); i++) {
	// PVector loc = (PVector) points.get(i);
	// p.vertex(loc.x,loc.y);
	// }
	// p.endShape(p.CLOSE);
	//
	// }
	/**
	 * This is for showing the Path DEBUGGING
	 * 
	 */
	public void ptclPathDisplay() {

		// Draw the radius as thick lines and circles

		// Draw end points
		for (int i = 0; i < ptclPoints.size(); i++) {
			PVector point = ptclPoints.get(i).getLoc();
			p.fill(Style.superSoftWhite);
			p.noStroke();
			p.ellipse(point.x, point.y, this.radius * 2, this.radius * 2);
		}

		// Draw Polygon around path
		for (int i = 0; i < ptclPoints.size(); i++) {
			PVector start = ptclPoints.get(i).getLoc();
			// We're assuming path is a circle in this example
			PVector end = ptclPoints.get((i + 1) % ptclPoints.size()).getLoc();
			PVector line = PVector.sub(end, start);
			PVector normal = new PVector(line.y, -line.x);
			normal.normalize();
			normal.mult(this.radius);

			// Polygon has four vertices
			PVector a = PVector.add(start, normal);
			PVector b = PVector.add(end, normal);
			PVector c = PVector.sub(end, normal);
			PVector d = PVector.sub(start, normal);

			p.fill(Style.superSoftWhite);
			p.noStroke();
			p.beginShape();
			p.vertex(a.x, a.y);
			p.vertex(b.x, b.y);
			p.vertex(c.x, c.y);
			p.vertex(d.x, d.y);
			p.endShape();
		}

		// Draw Regular Line
		p.stroke(Style.superSoftWhite);
		p.strokeWeight(2);
		p.noFill();
		p.beginShape();
		for (int i = 0; i < ptclPoints.size(); i++) {
			PVector loc = ptclPoints.get(i).getLoc();
			p.vertex(loc.x, loc.y);
		}
		p.endShape(PApplet.CLOSE);

	}

}
