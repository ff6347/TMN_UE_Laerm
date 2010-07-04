package particleSystem;

import java.util.ArrayList;

import processing.core.PApplet;
import processing.core.PVector;


//Path Following
//Daniel Shiffman <http://www.shiffman.net>
//The Nature of Code, Spring 2009
/**
 * The Path to follow a Path is a series of connected particles. They interact eith the Repellers and so...
 * based on: <a href="http://www.shiffman.net/teaching/nature/" target="blanc">Daniel Shiffman's Nature of Code</a>
 * @author fabianthelbind
 *
 */
public class Path {

	
	
	/**
	 * A Path is an ArrayList of Particles (PVector objects)
	 * 
	 */
	public ArrayList<Particle> ptclPoints;

	  /**
	 * the PApplet
	 */
	PApplet p;
	 
	/**
	 *  A path has a radius, i.e how far is it ok for the particle to wander off
	 */
	private float radius;

	/**
	 * builds a basic Path
	 * @param p the PApplet
	 */
	public Path(PApplet p) {
	    // Arbitrary radius of 20
		  this.p = p;

//	    points = new ArrayList<PVector>();
	    ptclPoints = new ArrayList<Particle>();

	  }
	  
	/**
	 * builds a Path with an specific Radius around the pathline
	 * @param p the PApplet
	 * @param radius the radius around the path
	 */
	public Path(PApplet p,float radius) {
			  this.p = p;
		    this.radius = radius;
//		    points = new ArrayList<PVector>();
		    ptclPoints = new ArrayList<Particle>();

		  }

	  // Add a point to the path
//	  public void addPoint(float x, float y) {
//	    PVector point = new PVector(x,y);
//	    points.add(point);
//	  }
	  
	  /**
	   * Adds a point that behaves like a Particle to the path
	 * @param x
	 * @param y
	 * @see Class Particle Class
	 */
	public void addPointPtcl(float x, float y) {
		  PVector pos = new PVector(x,y);
//		  PVector vel = new PVector(0,0);
		  Particle ptcl = new Particle(p, pos,false,true);
		  ptcl.setMass(100);
		  ptcl.setGravity(0f);
		  ptcl.setMaxforce(100f);
		  ptcl.setMaxspeed(0.0001f);
		  ptcl.setRadius(0.01f);
		  ptclPoints.add(ptcl);
		 
//		    PVector point = new PVector(x,y);
//		    points.add(point);
		  }
	  
	  	/**
	  	 * makes the points of the path move back to their origin
	  	 * if this is not called within every loop of the draw()
	  	 * the points of the path behave like normal Particle
	  	 * 
	  	 */
	  	public void resetPointPtcls(){
	  	
	  		for(int i = 0; i < ptclPoints.size();i++){
	  			if(ptclPoints.get(i).affection==false){
		  			ptclPoints.get(i).seek(ptclPoints.get(i).origin);
		  			ptclPoints.get(i).setMass(0.5f);
		  			ptclPoints.get(i).setGravity(0f);
		  			ptclPoints.get(i).setMaxforce(100f);
		  			ptclPoints.get(i).setMaxspeed(0.11f);
		  			ptclPoints.get(i).setRadius(0.01f);
		  			ptclPoints.get(i).hidden = true;
	  				
	  			}		
	  		}
	
	
	  	}
	
	  	/**
	  	 * @return ArrayList of Particle
	  	 */
	  	public ArrayList <Particle> getPtclPointList(){
	  		return this.ptclPoints;
	  	}
	  

//	  public int pathPoinsListSize(){
//		  return points.size();
//	  }
//	  
//	  public PVector getPathPointVector(int i){
//		  return this.points.get(i);
//	  }
	  	
	  /**
	   * this returns the specific location of a Point of the Path
	 * @param i to choose the point of the path
	 * @return Particle
	 */
	public Particle getPathPtclPointVector(int i){
		  return this.ptclPoints.get(i);
	  }
	  
	  /**
	   * set the radius oarund the path
	 * @param radius
	 */
	public void setRadius(float radius){
		  this.radius = radius;	  
	  }
	  
	  /**
	   * get the radius around the path
	 * @return the radius around the path
	 */
	public float getRadius(){
		  return this.radius;
	  }
	  
//	  public void updatePath(float radius_){
//		  
//		  radius = radius_;
//	  }
	  
	  // Draw the path
	  
//	  /**
//	   * this is the old way to build a path
//	 * 
//	 */
//	public void display() {
//
//	    // Draw the radius as thick lines and circles
//
//	    // Draw end points
//	    for (int i = 0; i < points.size(); i++) {
//	      PVector point = (PVector) points.get(i);
//	      p.fill(175);
//	      p.noStroke();
//	      p.ellipse(point.x,point.y,radius*2,radius*2);
//	    }
//
//	    // Draw Polygon around path
//	    for (int i = 0; i < points.size(); i++) {
//	      PVector start = (PVector) points.get(i);
//	      // We're assuming path is a circle in this example
//	      PVector end = (PVector) points.get((i+1)%points.size());
//	      PVector line = PVector.sub(end,start);
//	      PVector normal = new PVector(line.y,-line.x);
//	      normal.normalize();
//	      normal.mult(radius);
//
//	      // Polygon has four vertices
//	      PVector a = PVector.add(start, normal);
//	      PVector b = PVector.add(end, normal);
//	      PVector c = PVector.sub(end, normal);
//	      PVector d = PVector.sub(start, normal);
//
//	      p.fill(175);
//	      p.noStroke();
//	      p.beginShape();
//	      p.vertex(a.x,a.y);
//	      p.vertex(b.x,b.y);
//	      p.vertex(c.x,c.y);
//	      p.vertex(d.x,d.y);
//	      p.endShape();
//	    }
//
//	    // Draw Regular Line
//	    p.stroke(255);
//	    p.strokeWeight(3);
//	    p.noFill();
//	    p.beginShape();
//	    for (int i = 0; i < points.size(); i++) {
//	      PVector loc = (PVector) points.get(i);
//	      p.vertex(loc.x,loc.y);
//	    }
//	    p.endShape(p.CLOSE);
//
//	  }
	  /**
	   * This is for showing the Path DEBUGGING
	 * 
	 */
	public void ptclPathDisplay() {

		    // Draw the radius as thick lines and circles

		    // Draw end points
		    for (int i = 0; i < ptclPoints.size(); i++) {
		      PVector point =  ptclPoints.get(i).loc;
		      p.fill(0,0,100,20);
		      p.noStroke();
		      p.ellipse(point.x,point.y,this.radius*2,this.radius*2);
		    }

		    // Draw Polygon around path
		    for (int i = 0; i < ptclPoints.size(); i++) {
		      PVector start = ptclPoints.get(i).loc;
		      // We're assuming path is a circle in this example
		      PVector end =  ptclPoints.get((i+1)%ptclPoints.size()).loc;
		      PVector line = PVector.sub(end,start);
		      PVector normal = new PVector(line.y,-line.x);
		      normal.normalize();
		      normal.mult(this.radius);

		      // Polygon has four vertices
		      PVector a = PVector.add(start, normal);
		      PVector b = PVector.add(end, normal);
		      PVector c = PVector.sub(end, normal);
		      PVector d = PVector.sub(start, normal);

		      p.fill(0,0,100,20);
		      p.noStroke();
		      p.beginShape();
		      p.vertex(a.x,a.y);
		      p.vertex(b.x,b.y);
		      p.vertex(c.x,c.y);
		      p.vertex(d.x,d.y);
		      p.endShape();
		    }

		    // Draw Regular Line
		    p.stroke(150,100,100,50);
		    p.strokeWeight(2);
		    p.noFill();
		    p.beginShape();
		    for (int i = 0; i < ptclPoints.size(); i++) {
		      PVector loc =  ptclPoints.get(i).loc;
		      p.vertex(loc.x,loc.y);
		    }
		    p.endShape(p.CLOSE);

		  }
	  

			

}
