package particleSystem;

import java.util.ArrayList;

import processing.core.PApplet;
import processing.core.PVector;
import util.Style;

/**
 * The Particles
 * based on: <a href="http://www.shiffman.net/teaching/nature/" target="blanc">Daniel Shiffman's Nature of Code</a>
 * @author fabianthelbind
 *
 */
public class Particle {
	PApplet p;
	public PVector loc;
	public PVector vel;
	public PVector acc;

	public float  gravity = 0.0f;
	public float maxforce = 0.5f;    // Maximum steering force
	public float maxspeed =  2.0f;    // Maximum speed
	public float radius;// = 5f;    // radius
	public float lifeTime = 100000.0f;    // the lifetime of an Particle
    public float mass = 0.5f; // The higher the mass the lesser the particles get pushed by repellers
    public boolean affection;
    public boolean hidden;
	public int pathNum;


    public final PVector origin;

	
	
//	some graphical stuff
	
	public int col1;
	public int col2;
	private int col3;
		
	public Particle(PApplet p, PVector loc, PVector vel, float r,float ms, float mf,int pathNum, boolean affection,boolean hidden) {
		// TODO Auto-generated constructor stub
		this.p = p;
		this.loc = loc.get();	
		this.vel = vel;
		this.radius = r;
		this.maxspeed = ms;
		this.maxforce = mf;
		this.acc = new PVector(0,0);
		this.vel = new PVector(0,0);
		this.lifeTime = 100000.0f;
		this.col1 = p.color(Style.col1);
		this.col2 = p.color(Style.col2);
		this.col3 = p.color(Style.col3);
		this.affection = affection;
		this.hidden  = hidden;
		this.origin = new PVector(loc.x,loc.y);
		this.pathNum = pathNum;
		
	}
	
	public Particle(PApplet p, PVector loc, PVector vel, float r,float ms, float mf,boolean affection,boolean hidden) {
		// TODO Auto-generated constructor stub
		this.p = p;
		this.loc = loc.get();		
		this.vel = vel;
		this.radius = r;
		this.maxspeed = ms;
		this.maxforce = mf;
		this.acc = new PVector(0,0);
		this.vel = new PVector(0,0);
		this.lifeTime = 100000.0f;
		this.col1 = p.color(Style.col1);
		this.col2 = p.color(Style.col2);
		this.col3 = p.color(Style.col3);
		this.affection = affection;
		this.hidden  = hidden;
		this.origin = new PVector(loc.x,loc.y);
	}
	

	

	
	public Particle(PApplet p, PVector loc, PVector vel, float r,int pathNum, boolean affection,boolean hidden) {
		// TODO Auto-generated constructor stub
		this.p = p;
		this.loc = loc.get();
		this.vel = vel;
		this.radius = r;
		this.acc = new PVector(0,0);
		this.vel = new PVector(0,0);
		this.lifeTime = 100000.0f;
		this.col1 = p.color(Style.col1);
		this.col2 = p.color(Style.col2);
		this.col3 = p.color(Style.col3);
		this.affection = affection;
		this.origin = new PVector(loc.x,loc.y);
		this.hidden  = hidden;
		this.pathNum = pathNum;
	}
	
//	this is the particle for the ParticleSystem Emitter
	public Particle(PApplet p, PVector loc,boolean affection,boolean hidden) {
		this.p = p;
		this.acc = new PVector(0,0);
		this.vel = new PVector(0,0);
		this.loc = loc.get();
//		r = 10.0;
		this.lifeTime = 100000.0f;
//		maxspeed = 2;
		
		this.col1 = p.color(Style.col1);
		this.col2 = p.color(Style.col2);
		this.col3 = p.color(Style.col3);

		this.affection = affection;
		this.origin = new PVector(loc.x,loc.y);
		this.hidden  = hidden;


		}
	
	// Is the particle still useful?
	boolean dead() {
		if (lifeTime <= 0.0) {
			return true;
		} else {
			return false;
		}
	}
	
//	set the lifetime of the particle
	
	public void setLifeTime(float lifeTimeIn){
		
		this.lifeTime = lifeTimeIn;
	}
	
////	some graphical stuff in RGB
//	public void setColorCol1(int r, int g, int b, int a){
//		col1 = p.color(r,g,b,a);
//	}
//	
//	public void setColorCol1Grey(int greyVal, int a){
//		col1 = p.color(greyVal,a);
//	}
//	
//	public void setColorCol2(int r, int g, int b, int a){
//		col2 = p.color(r,g,b,a);
//	}
//	
//	public void setColorCol2Grey(int greyVal, int a){
//		col2 = p.color(greyVal,a);
//	}
	
//	some graphical stuff in HSB
	public void setColorCol1(int h, float s, float b, int a){
		this.col1 = p.color(h,s,b,a);
	}
	
	public void setColorCol1Grey(int greyVal, int a){
		this.col1 = p.color(360,0,greyVal,a);
	}
	
	public void setColorCol2(int h, float s, float b, int a){
		this.col2 = p.color(h,s,b,a);
	}
	
	public void setColorCol2Grey(int greyVal, int a){
		this.col2 = p.color(360,0,greyVal,a);
	}
	
	
	public void setGravity(float inGravity){
		this.gravity = inGravity;
	}
	public void resetGravity(){
		this.gravity = 0.0f;
	}
	public void setMaxforce(float inMaxforce){
		this.maxforce = inMaxforce;
		}
	public void resetMaxforce(){
		this.maxforce = 0.3f;
		}
	
		public void setMaxspeed(float inMaxspeed){
			this.maxspeed = inMaxspeed;
		}
		public void resetMaxspeed(){
			this.maxspeed = 2.0f;
		}
		
		public void setRadius(float inRadius) {
			this.radius = inRadius;
		}
		
		public void setMass(float massIn){
			this.mass = massIn;
			
		}
		public void resetMass(){
			this.mass = 0.5f;
			
		}
		
		public void setPathNum(int pathNumIn){
			this.pathNum = pathNumIn;
			
		}
		
		

//	this is for playing around with forces
	public void myForce(ArrayList<Particle> obstacles){
		
		for(int i = 0;i<obstacles.size();i++){
			Particle obstcl = obstacles.get(i);
			
			PVector force = new PVector(obstcl.loc.x,obstcl.loc.y); //obstcl.loc;

			force.normalize();
			
			loc.x = loc.x +force.x;
			loc.y = loc.y +force.y;
			
//			if( (loc.x > (obstcl.loc.x + obstcl.radius/2))){
//					vel.x = vel.x + 1;
//			}else if (loc.x < (obstcl.loc.x - obstcl.radius/2 )){
//				vel.x = vel.x - 1;	
//			}
//			if( (loc.y > (obstcl.loc.y + obstcl.radius/2))){
//				vel.y = vel.y + 1;
//		}else if (loc.y < (obstcl.loc.y - obstcl.radius/2 )){
//			vel.y = vel.y - 1;	
//		} else {
//			
//			vel.x = vel.x;
//			vel.y = vel.y;
//		}
		
		}
	}
	
	@SuppressWarnings("static-access")
	void limit (){
		if(loc.y> p.height-radius|| loc.y <radius){
			
			vel.y = -vel.y;
			loc.y= p.constrain(loc.y,-p.height*p.height,p.height-radius);
			
		}
		if((loc.x < radius)||(loc.x > p.width-radius)){
			vel.x = -vel.x;
			loc.x= p.constrain(loc.x, radius, p.width-radius);
			
			
		}
		
	}
	
	
	public void display(){
		
		if(hidden!=true){

		p.stroke(col3);
		p.point(p.random(loc.x-0.5f,loc.x+0.5f), p.random(loc.y-0.5f,loc.y+0.5f));
		p.stroke(col2);
		p.strokeWeight(2);
		p.point(p.random(loc.x-0.5f,loc.x+0.5f), p.random(loc.y-0.5f,loc.y+0.5f));
		p.point(p.random(loc.x-0.5f,loc.x+0.5f), p.random(loc.y-0.5f,loc.y+0.5f));

//		p.fill(col1);
//		p.ellipse(loc.x, loc.y, radius, radius);
//		p.noStroke();
//		p.fill(col2);
//		p.ellipse(loc.x,loc.y,radius*1.05f,radius*1.05f);
		
//		for(int i=0;i<2;i++){
//			p.strokeWeight(1);
//			p.stroke(col2);
//			p.beginShape(p.LINES);
//			p.vertex(loc.x+p.random(-radius*1.05f,radius*1.05f), loc.y+p.random(-radius*1.05f,radius*1.05f));
//			p.vertex(loc.x+p.random(-radius*1.05f,radius*1.05f), loc.y+p.random(-radius*1.05f,radius*1.05f));
//			p.endShape();
//		}
//		p.noStroke();
		}
		
	}
	
	// A function to deal with path following and separation
	public void applyForces(ArrayList<Particle> ptkls, Path path) {
	
	// Follow path force
		PVector f = follow(path);
		// Separate from other boids force
		
		
		PVector s = separate(ptkls);
		// Arbitrary weighting
		f.mult(3);
		s.mult(1);
		// Accumulate in acceleration
		acc.add(f);
		acc.add(s);
	}

	public void applyRepellForce(PVector force){
		
		//float mass = 0.1f; // We aren't bothering with mass here
		force.div(mass);
		acc.add(force);
		
	}

	// Main "run" function
	public void run() {
		update();
		display();
		limit();

	}

	// This function implements Craig Reynolds' path following algorithm
	// http://www.red3d.com/cwr/steer/PathFollow.html
	private PVector follow(Path pt) {
		
	// Predict location 25 (arbitrary choice) frames ahead
		PVector predict = vel.get();
		predict.normalize();
		predict.mult(25);
		PVector predictLoc = PVector.add(loc, predict);
		
//	// Draw the predicted location
//		if (debug) {
//			p.fill(0);
//			p.stroke(0);
//			p.line(loc.x,loc.y,predictLoc.x, predictLoc.y);
//			p.ellipse(predictLoc.x, predictLoc.y,4,4);
//		}
		
// 	Now we must find the normal to the path from the predicted location
// 	We look at the normal for each line segment and pick out the closest one
		PVector target = null;
		PVector dir = null;
		float record = 1000000;  // Start with a very high record distance that can easily be beaten
		
// 	Loop through all points of the path
		for (int i = 0; i < pt.ptclPoints.size(); i++) {

// 	Look at a line segment
			PVector a = (PVector) pt.ptclPoints.get(i).loc;
			PVector b = (PVector) pt.ptclPoints.get((i+1)%pt.ptclPoints.size()).loc;  // Path wraps around

// Get the normal point to that line
			PVector normal = getNormalPoint(predictLoc,a,b);

			// Check if normal is on line segment
			float da = PVector.dist(normal,a);
			float db = PVector.dist(normal,b);
			PVector line = PVector.sub(b,a);
// If it's not within the line segment, consider the normal to just be the end of the line segment (point b)
			if (da + db > line.mag()+1) {
				normal = b.get();
// If we're at the end we really want the next line segment for looking ahead
				a = (PVector) pt.ptclPoints.get((i+1)%pt.ptclPoints.size()).loc;
				b = (PVector) pt.ptclPoints.get((i+2)%pt.ptclPoints.size()).loc;  // Path wraps around
				line = PVector.sub(b,a);
			}

// How far away are we from the path?
			float d = PVector.dist(predictLoc,normal);
// Did we beat the record and find the closest line segment?
			if (d < record) {
				record = d;
// If so the target we want to steer towards is the normal
				target = normal;

// Look at the direction of the line segment so we can seek a little bit ahead of the normal
				dir = line;
				dir.normalize();
// This is an oversimplification
// Should be based on distance to path & velocity
				dir.mult(25);
			}
		}

//	    // Draw the debugging stuff
//		if (debug) {
//	// Draw normal location
//			p.fill(0);
//			p.noStroke();
//			p.line(predictLoc.x,predictLoc.y,target.x,target.y);
//			p.ellipse(target.x,target.y,4,4);
//			p.stroke(0);
//	// Draw actual target (red if steering towards it)
//			p.line(predictLoc.x,predictLoc.y,target.x,target.y);
//			if (record > pt.radius) p.fill(255,0,0);
//				p.noStroke();
//				p.ellipse(target.x+dir.x, target.y+dir.y, 8, 8);
//			}

		// Only if the distance is greater than the path's radius do we bother to steer
		if (record > pt.getRadius() || vel.mag() < 0.1) {
			target.add(dir);
			return steer(target,false);		
		} 
		else {
			return new PVector(0,0);
		}
	}

	  // A function to get the normal point from a point (p) to a line segment (a-b)
	  // This function could be optimized to make fewer new Vector objects
	public PVector getNormalPoint(PVector p, PVector a, PVector b) {
	    // Vector from a to p
		PVector ap = PVector.sub(p,a);
	    // Vector from a to b
		PVector ab = PVector.sub(b,a);
		ab.normalize(); // Normalize the line
	    // Project vector "diff" onto line by using the dot product
		ab.mult(ap.dot(ab));
		PVector normalPoint = PVector.add(a,ab);
		return normalPoint;
	}

	  // Separation
	  // Method checks for nearby boids and steers away
	public PVector separate (ArrayList<Particle> ptkls) {

		float desiredseparation = radius*2;
		PVector steer = new PVector(0,0);
		int count = 0;

	// For every boid in the system, check if it's too close
		for (int i = 0 ; i < ptkls.size(); i++) {
			if(ptkls.get(i).hidden!=true){
			Particle other = (Particle) ptkls.get(i);
			float d = PVector.dist(loc,other.loc);
	// If the distance is greater than 0 and less than an arbitrary amount (0 when you are yourself)
			if ((d > 0) && (d < desiredseparation)) {
	// Calculate vector pointing away from neighbor
				PVector diff = PVector.sub(loc,other.loc);
				diff.normalize();
				diff.div(d);        // Weight by distance
				steer.add(diff);
				count++;   
				}// Keep track of how many
			}
		}
	// Average -- divide by how many
		if (count > 0) {
			steer.div((float)count);
		}

	    // As long as the vector is greater than 0
		if (steer.mag() > 0) {
	// Implement Reynolds: Steering = Desired - Velocity
			steer.normalize();
			steer.mult(maxspeed);
			steer.sub(vel);
			steer.limit(maxforce);
		}
		
		
		return steer;
	}


	  // Method to update location
	public void update() {
	    // Update velocity
		vel.add(acc);
	    // Limit speed
		vel.limit(maxspeed);
		loc.add(vel);
		// Reset accelertion to 0 each cycle
		acc.mult(0);
		lifeTime -= 0.5;

	}

	public void seek(PVector target) {
		acc.add(steer(target,false));
	}

	public void arrive(PVector target) {
		acc.add(steer(target,true));
	}

	  // A method that calculates a steering vector towards a target
	  // Takes a second argument, if true, it slows down as it approaches the target
	  public PVector steer(PVector target, boolean slowdown) {
	    PVector steer;  // The steering vector
	    PVector desired = PVector.sub(target,loc);  // A vector pointing from the location to the target
	    float d = desired.mag(); // Distance from the target is the magnitude of the vector
	    // If the distance is greater than 0, calc steering (otherwise return zero vector)
	    if (d > 0) {
	      // Normalize desired
	      desired.normalize();
	      // Two options for desired vector magnitude (1 -- based on distance, 2 -- maxspeed)
	      if ((slowdown) && (d < 100.0f)) desired.mult(maxspeed*(d/100.0f)); // This damping is somewhat arbitrary
	      else desired.mult(maxspeed);
	      // Steering = Desired minus Velocity
	      steer = PVector.sub(desired,vel);
	      steer.limit(maxforce);  // Limit to maximum steering force
	    } 
	    else {
	      steer = new PVector(0,0);
	    }
	    return steer;
	  }


	  


}
