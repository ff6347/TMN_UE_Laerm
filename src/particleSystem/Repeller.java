	package particleSystem;
	
import processing.core.PApplet;
import processing.core.PVector;
import tmnuelaerm.Point;
	
	//Particles + Forces
	//Daniel Shiffman <http://www.shiffman.net>
	
	//A very basic Repeller class
	public class Repeller {
	
	// Gravitational Constant
	public float G;
	
	// Location
	public PVector loc;
	PApplet p;
	
	public float radius = 10;
	
	// For mouse interaction
	boolean dragging = false; // Is the object being dragged?
	boolean rollover = false; // Is the mouse over the ellipse?
	PVector drag;  // holds the offset for when object is clicked on
	public int color1;
	public int color2;
	public int id;
	
	public String obstclID; 


	
	public Repeller(PApplet p_,float x, float y)  {
		p = p_;
	 loc = new PVector(x,y);
	 drag = new PVector(0,0);
	 G = p.pow(10,3);
	 
	 color1 = p.color(0,0, 100, 100);
	 color2 = p.color(0,0, 100, 70);
	}
	
	public Repeller(PApplet p_, PVector loc_)  {
		p = p_;
		loc = loc_;
		drag = new PVector(0,0);
		G = p.pow(10,3);
		
		color1 = p.color(0,0, 100, 100);
		color2 = p.color(0,0, 100, 70);
		}
	public Repeller(PApplet p_, PVector loc_,int ObjectID_)  {
		p = p_;
		loc = loc_;
		drag = new PVector(0,0);
		G = p.pow(10,3);
		
		color1 = p.color(0,0, 100, 100);
		color2 = p.color(0,0, 100, 70);
		id = ObjectID_;
		}
	
	
	public Repeller(PApplet p_, PVector loc_,float radius_)  {
		p = p_;
		loc = loc_;
		drag = new PVector(0,0);
		G = p.pow(10,3);
		 radius = radius_;
		color1 = p.color(0,0, 100, 100);
		color2 = p.color(0,0, 100, 70);
		}
	
	
	// Repellers get the ID of the ObstacleObjaect as a String, so we IDs looking like 01, 02, etc.
	public Repeller(PApplet p_,float x, float y, float G_,float radius_, String _id)  {
		p = p_;
	 loc = new PVector(x,y);
	 drag = new PVector(0,0);
	 G = G_;
	 radius = radius_;
	 obstclID = _id;

	 color1 = p.color(0,0, 100, 100);
	 color2 = p.color(0,0, 100, 70);
	}
	public Repeller(PApplet p_,float x, float y, float G_,float radius_)  {
		p = p_;
	 loc = new PVector(x,y);
	 drag = new PVector(0,0);
	 G = G_;
	 radius = radius_;
	 
	 color1 = p.color(0,0, 100, 100);
	 color2 = p.color(0,0, 100, 70);
	}
	
	public void display() {
	 p.stroke(color1);
	 if (dragging) p.fill (color1);
	 else if (rollover) p.fill(color2);
	 else p.noFill();
	 p.ellipse(loc.x,loc.y,radius*2,radius*2);
	}
	
	// Calculate a force to push particle away from repeller
	public PVector pushParticle(Particle ptcl) {
	 PVector dir = PVector.sub(loc, ptcl.loc);      // Calculate direction of force
	 float d = dir.mag();                       // Distance between objects
	 dir.normalize();                           // Normalize vector (distance doesn't matter here, we just want this vector for direction)
	 d = p.constrain(d,5,100);                     // Keep distance within a reasonable range
	 float force = -1 * G / (d * d);            // Repelling force is inversely proportional to distance
	 dir.mult(force);                           // Get force vector --> magnitude * direction
	 return dir;
	}  
	
	public void update(PVector loc_){
		
		loc = loc_;
	}
	
	public void translate(PVector _v) {
		PVector v = _v;
		
		Point point = new Point(loc.x, loc.y);
		point.translate(v.x, v.y);
	}
	
	
	public void setG(float G_in){
		G = G_in;
	}
	
	public void setRadius(float radiusIn){
		radius = radiusIn;
	}
	
	public float getG(){	
		return G;
	}
	public float getRadius(){	
		return radius;
	}
	
	public void setColor1(float h, float s, float b, float a){
		
		color1 = p.color(h,s,b,a);
	}
	
	public void setColor2(float h, float s, float b, float a){
		
		color2 = p.color(h,s,b,a);
	}
	
	// The methods below are for mouse interaction
	public void clicked(int mx, int my) {
		float d = p.dist(mx,my,loc.x,loc.y);
		if (d < radius) {
			dragging = true;
			drag.x = loc.x-mx;
			drag.y = loc.y-my;
	 }
	}
	
	void rollover(int mx, int my) {
		float d = p.dist(mx,my,loc.x,loc.y);
		if (d < radius) {
			rollover = true;
		}
		else {
			rollover = false;
		}
	}
	
	public void stopDragging() {
	 dragging = false;
	}
	
	public void drag() {
		if (dragging) {
			loc.x = p.mouseX + drag.x;
			loc.y = p.mouseY + drag.y;
		}
	}
}
	

