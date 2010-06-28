package particleSystem;

import java.util.ArrayList;

import processing.core.PApplet;
import processing.core.PVector;


//Path Following
//Daniel Shiffman <http://www.shiffman.net>
//The Nature of Code, Spring 2009
public class Path {

	  // A Path is an arraylist of points (PVector objects)
	  public ArrayList<PVector> points;
	  public ArrayList<Particle> ptclPoints;

	  PApplet p;
	  // A path has a radius, i.e how far is it ok for the particle to wander off
	  public float radius;

	  public Path(PApplet p_) {
	    // Arbitrary radius of 20
		  p = p_;
//	    radius = 10;
	    points = new ArrayList<PVector>();
	    ptclPoints = new ArrayList<Particle>();

	  }
	  
	  public Path(PApplet p_,float r_) {
		    // Arbitrary radius of 20
			  p = p_;
		    r_ = radius;
		    points = new ArrayList<PVector>();
		    ptclPoints = new ArrayList<Particle>();

		  }

	  // Add a point to the path
	  public void addPoint(float x, float y) {
	    PVector point = new PVector(x,y);
	    points.add(point);
	  }
	  
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
	  
	  	public void resetPointPtcls(){
	  	
	  		for(int i = 0; i < ptclPoints.size();i++){
	  			if(ptclPoints.get(i).affection==false){
		  			ptclPoints.get(i).seek(ptclPoints.get(i).origin);
		  			ptclPoints.get(i).setMass(100);
		  			ptclPoints.get(i).setGravity(0f);
		  			ptclPoints.get(i).setMaxforce(100f);
		  			ptclPoints.get(i).setMaxspeed(0.0001f);
		  			ptclPoints.get(i).setRadius(0.01f);
		  			ptclPoints.get(i).hidden = true;
	  				
	  			}		
	  		}
	
	
	  	}
	
	  	public ArrayList <Particle> getPtclPointList(){
	
	  		return ptclPoints;
	  	}
	  

	  public int pathPoinsListSize(){
		  return points.size();
	  }
	  
	  public PVector getPathPointVector(int i){
		  return points.get(i);
	  }
	  public Particle getPathPtclPointVector(int i){
		  return ptclPoints.get(i);
	  }
	  
	  public void setRadius(float radius_){
		  radius = radius_;	  
	  }
	  
	  public void updatePath(float radius_){
		  
		  radius = radius_;
	  }
	  
	  // Draw the path
	  
	  public void display() {

	    // Draw the radius as thick lines and circles

	    // Draw end points
	    for (int i = 0; i < points.size(); i++) {
	      PVector point = (PVector) points.get(i);
	      p.fill(175);
	      p.noStroke();
	      p.ellipse(point.x,point.y,radius*2,radius*2);
	    }

	    // Draw Polygon around path
	    for (int i = 0; i < points.size(); i++) {
	      PVector start = (PVector) points.get(i);
	      // We're assuming path is a circle in this example
	      PVector end = (PVector) points.get((i+1)%points.size());
	      PVector line = PVector.sub(end,start);
	      PVector normal = new PVector(line.y,-line.x);
	      normal.normalize();
	      normal.mult(radius);

	      // Polygon has four vertices
	      PVector a = PVector.add(start, normal);
	      PVector b = PVector.add(end, normal);
	      PVector c = PVector.sub(end, normal);
	      PVector d = PVector.sub(start, normal);

	      p.fill(175);
	      p.noStroke();
	      p.beginShape();
	      p.vertex(a.x,a.y);
	      p.vertex(b.x,b.y);
	      p.vertex(c.x,c.y);
	      p.vertex(d.x,d.y);
	      p.endShape();
	    }

	    // Draw Regular Line
	    p.stroke(0);
	    p.noFill();
	    p.beginShape();
	    for (int i = 0; i < points.size(); i++) {
	      PVector loc = (PVector) points.get(i);
	      p.vertex(loc.x,loc.y);
	    }
	    p.endShape(p.CLOSE);

	  }
	  public void ptclPathDisplay() {

		    // Draw the radius as thick lines and circles

		    // Draw end points
		    for (int i = 0; i < ptclPoints.size(); i++) {
		      PVector point = (PVector) ptclPoints.get(i).loc;
		      p.fill(175);
		      p.noStroke();
		      p.ellipse(point.x,point.y,radius*2,radius*2);
		    }

		    // Draw Polygon around path
		    for (int i = 0; i < points.size(); i++) {
		      PVector start = (PVector) points.get(i);
		      // We're assuming path is a circle in this example
		      PVector end = (PVector) points.get((i+1)%points.size());
		      PVector line = PVector.sub(end,start);
		      PVector normal = new PVector(line.y,-line.x);
		      normal.normalize();
		      normal.mult(radius);

		      // Polygon has four vertices
		      PVector a = PVector.add(start, normal);
		      PVector b = PVector.add(end, normal);
		      PVector c = PVector.sub(end, normal);
		      PVector d = PVector.sub(start, normal);

		      p.fill(175);
		      p.noStroke();
		      p.beginShape();
		      p.vertex(a.x,a.y);
		      p.vertex(b.x,b.y);
		      p.vertex(c.x,c.y);
		      p.vertex(d.x,d.y);
		      p.endShape();
		    }

		    // Draw Regular Line
		    p.stroke(0);
		    p.noFill();
		    p.beginShape();
		    for (int i = 0; i < points.size(); i++) {
		      PVector loc = (PVector) points.get(i);
		      p.vertex(loc.x,loc.y);
		    }
		    p.endShape(p.CLOSE);

		  }
	  
//		public void newPath(Path path) {
//			  // A path is a series of connected points
//			  // A more sophisticated path might be a curve
//			  path = new Path(p);
//			  float offset = 60;
//			  path.addPoint(offset,offset);
//			  path.addPoint(p.width-offset,offset);
//			  path.addPoint(p.width-offset,p.height-offset);
//			  path.addPoint(p.width/2,p.height-offset*3);
//			  path.addPoint(offset,p.height-offset);
//			}
//			
//		public static void circlePath(PApplet p,int segments,Path path){
//				
//				path = new Path(p);
//				for(int i = 0; i <=360;i+=360/segments){
//					  path.addPoint(p.width / 2 + p.sin(p.radians(i))*100,p.height / 2 + p.cos(p.radians(i))*100);
//				}
//			}
			

}
