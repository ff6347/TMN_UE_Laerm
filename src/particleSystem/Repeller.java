	package particleSystem;
	
import processing.core.PApplet;
import processing.core.PVector;
import tmnuelaerm.Point;
import util.Style;
	
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
	private boolean dragging = false; // Is the object being dragged?
	private boolean rollover = false; // Is the mouse over the ellipse?
	PVector drag;  // holds the offset for when object is clicked on
	private int color1;
	private int color2;
	public int id;
	
	public String obstclID; 


	
	public Repeller(PApplet p,float x, float y)  {
		this.p = p;
		this.loc = new PVector(x,y);
		this.drag = new PVector(0,0);
		this.G = PApplet.pow(10,3);
	 
		this.color1 = p.color(Style.textColor);
		this.color2 = p.color(Style.col3);
	}
	
	public Repeller(PApplet p, PVector loc_)  {
		this.p = p;
		this.loc = loc_;
		this.drag = new PVector(0,0);
		this.G = PApplet.pow(10,3);
		
		this.color1 = p.color(Style.textColor);
		this.color2 = p.color(Style.col3);
		}
	public Repeller(PApplet p, PVector loc_,int ObjectID_)  {
		this.p = p;
		this.loc = loc_;
		this.drag = new PVector(0,0);
		this.G = PApplet.pow(10,3);
		
		this.color1 = p.color(Style.textColor);
		this.color2 = p.color(Style.col3);
		this.id = ObjectID_;
		}
	
	
	public Repeller(PApplet p, PVector loc_,float radius)  {
		this.p = p;
		this.loc = loc_;
		this.drag = new PVector(0,0);
		this.G = PApplet.pow(10,3);
		this.radius = radius;
		this.color1 = p.color(Style.textColor);
		this.color2 = p.color(Style.col3);
		}
	
	
	// Repellers get the ID of the ObstacleObjaect as a String, so we IDs looking like 01, 02, etc.
	public Repeller(PApplet p,float x, float y, float G,float radius, String _id)  {
		p = p;
		this.loc = new PVector(x,y);
		this.drag = new PVector(0,0);
		this.G = G;
		this.radius = radius;
		this.obstclID = _id;

	 color1 = p.color(Style.textColor);
	 color2 = p.color(Style.col3);
	}
	public Repeller(PApplet p,float x, float y, float G,float radius)  {
		p = p;
	 loc = new PVector(x,y);
	 drag = new PVector(0,0);
	 G = G;
	 radius = radius;
	 
	 color1 = p.color(Style.textColor);
	 color2 = p.color(Style.col3);
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
	 d = PApplet.constrain(d,5,100);                     // Keep distance within a reasonable range
	 float force = -1 * G / (d * d);            // Repelling force is inversely proportional to distance
	 dir.mult(force);                           // Get force vector --> magnitude * direction
	 return dir;
	}  
	
	public void update(PVector loc_){
		
		this.loc = loc_;
	}
	
//	public void translate(PVector _v) {
//		PVector v = _v;
//		
//		Point point = new Point(loc.x, loc.y);
//		point.translate(v.x, v.y);
//	}
	
	
	public void setG(float G_in){
		this.G = G_in;
	}
	
	public void setRadius(float radiusIn){
		this.radius = radiusIn;
	}
	
	public float getG(){	
		return this.G;
	}
	public float getRadius(){	
		return this.radius;
	}
	
	public void setColor1(float h, float s, float b, float a){
		
		this.color1 = p.color(h,s,b,a);
	}
	
	public void setColor2(float h, float s, float b, float a){
		
		this.color2 = p.color(h,s,b,a);
	}
	
	// The methods below are for mouse interaction
	public void clicked(int mx, int my) {
		float d = PApplet.dist(mx,my,loc.x,loc.y);
		if (d < radius) {
			dragging = true;
			this.drag.x = loc.x-mx;
			this.drag.y = loc.y-my;
	 }
	}
	
	void rollover(int mx, int my) {
		float d = PApplet.dist(mx,my,loc.x,loc.y);
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
			this.loc.x = p.mouseX + drag.x;
			this.loc.y = p.mouseY + drag.y;
		}
	}
}
	

