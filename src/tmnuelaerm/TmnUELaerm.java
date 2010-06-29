package tmnuelaerm;

import TUIO.TuioListener;
import java.util.ArrayList;

import TUIO.TuioClient;
import TUIO.TuioCursor;
import TUIO.TuioObject;
import TUIO.TuioTime;

import processing.core.PApplet;
import processing.core.PFont;
import processing.core.PImage;
import processing.core.PVector;
import tmnuelaerm.ObstacleObject;
import util.Debug;


import particleSystem.PSUtil;
import particleSystem.Particle;
import particleSystem.ParticleSystem;
import particleSystem.Path;
import particleSystem.Repeller;



public class TmnUELaerm extends PApplet implements TuioListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1217858472488377688L;

public ArrayList<ObstacleObject> obstclObjList;
	
	public int obstclCounter;
	public boolean isPressed = false;
	
	public TuioClient tuioClient = new TuioClient();

	
	public ArrayList<TuioCursor> tuioCursorList;

	public PFont font;
	
//	Setup the Particles
	// A path object (series of connected points/particles)
//	Path path;
//	our particle System
	ParticleSystem ps;
//	Some Arraylists to store the objects
	ArrayList <Particle> ptclsList =  new ArrayList<Particle>();
	ArrayList<Repeller> repellers;
	ArrayList <Path> pathsList = new ArrayList <Path>();
	
//	This is for debugging and make some lose repellers
	ArrayList<Repeller> someRepellers = new ArrayList<Repeller>();
//	for the particles
	int numPtcls = 1005; // number of particles
	
//	every particle can have his own force / radius / speed
//	they can be chaged later
	float ptclRadius = 5; // standard radius for the particles
//	public float myForce = 0.5f; // std force for the particles 
//	public float mySpeed = 0.5f; // std speed for the particles

//	some repellers
	int numRepellers = 5;

	
//	to count the time
	public static int runtimeCounter;
//	just for unique filenames when saving a frame as .jpg in the folder data
	public static float time;

	



//	DEbugging stuff
	private Debug debug;



	//PDXIII background Stuff
	public PImage fadingBG;
	public float tinter;
	public boolean tintBack = false;
    public float tintMax = 60;
    public float tintMin = 20;

	private int pathNum = 0;
    //end PDXIII background Stuff
    
    
	public void setup() {
		

		colorMode(HSB,360,100,100);
		background(0);
		size(500,400,OPENGL);
		frameRate(25);
		debug = new Debug(this);
		

		//PDXIII background Stuff
		fadingBG = loadImage("fadingBG.png");

		//PDXIII TUIO Stuff
		// enable on system installed fonts
		hint(ENABLE_NATIVE_FONTS);
		font = createFont("Gentium", 18);
		textFont(font,18);

		//init TUIO
		tuioClient.addTuioListener(this);
		tuioClient.connect();
		
		int [] pathsSize = {40,60,80,100,120,140,160,180,200};
		for(int p = 0;p<9;p++){
			pathsList.add(PSUtil.initCirclePath(this, 13, 50,pathsSize[p]));
			
		}
		obstclObjList = new ArrayList<ObstacleObject>();
		
		// making ObstacleObjects
		for (obstclCounter = 0; obstclCounter < 2; obstclCounter++){
			int obstclNo = obstclCounter + 1;
			float firstX = obstclNo*150;
			float firstY = height/2;
			PVector obstclPos = new PVector (firstX, firstY);
			obstclObjList.add(new ObstacleObject(this, obstclNo, obstclPos));
			
		}
		//end PDXIII TUIO Stuff
		
//		particle stuff
		  // Call a function to generate new Path object with 23 segments
		//path = PSUtil.initCirclePath(this, path, 23);
//		pathsList = PSUtil.initPaths(this, pathsList);
		
		  // We are now making random Particles and storing them in an ArrayList ptclsList
		ptclsList = PSUtil.initParticles(this, numPtcls, ptclRadius, ptclsList);
		
//		add the  Path ptclPoints ArrayList of Particles to the ptclsList
		for(int pl  =0; pl< pathsList.size();pl++){
		for(int pp = 0; pp < pathsList.get(pl).ptclPoints.size();pp++){
			ptclsList.add(pathsList.get(pl).ptclPoints.get(pp));
		}
		}
//		we need the particle system to interact with the repellers
		ps = new ParticleSystem(this,1,new PVector(width/2,height/2),ptclsList,pathsList.get(pathNum));
		
//		PSUtil.makeSomeRepellers(this, someRepellers);
		
	time = millis();
	runtimeCounter = 0;	
		
	}

	public void draw() {
		
		debug.watchAParticle(ptclsList, ps);
		
//		for(int pl2 = 0; pl2<pathsList.size();pl2++ ){
//		pathsList.get(pl2).ptclPathDisplay();
//		}
//		path.ptclPathDisplay();
		pathsList.get(pathNum).resetPointPtcls();
		
//		this is for setting all the time the radius
		for(int pl2 = 0; pl2<pathsList.size();pl2++ ){

		if(runtimeCounter==0)pathsList.get(pl2).radius = 50;
		}

//		background(125);
		
//		PDXBGStuff
//		drawBG();
//		theBackground();
		
//		just a clearScreen method
		clearScreen();
		smooth();
		
		
		for (int i = 0; i < ptclsList.size(); i++) {
				Particle ptkl =  ptclsList.get(i);
				if(ptkl.hidden!=true){
				// Path following and separation are worked on in this function
//					pathNum = floor(random(0,8));
//					println(pathNum);
				ptkl.applyForces(ptclsList,pathsList.get(ptkl.pathNum));
				// Call the generic run method (update, borders, display, etc.)
				}
				ptkl.run();
				
			}
		
//		must be rebuild at runtime so it doesent store all the time new repellers in the list
//		else it has to be deleted at runtime
//		does that matter?
		
		repellers = new ArrayList<Repeller>();

		
		for(int j = 0; j < obstclObjList.size(); j++){
			
			ObstacleObject obstclObject = (ObstacleObject) obstclObjList.get(j);
			if(obstclObject.ObstclsRepellerList != null){
				for(int k = 0; k< obstclObject.ObstclsRepellerList.size();k++){
					
					repellers.add(obstclObject.ObstclsRepellerList.get(k));
				}
			}
		}
		
		drawObstacleObjects();

		// Apply repeller objects to all Particles
//		ps.myApplyRepellers(repellers);
		ps.myApplyObstcles(obstclObjList);
		// Run the Particle System
		ps.run();
		
//		U need to call first the 
//		public static void PSUtil.makeSomeRepellers(PApplet p,ArrayList <Repeller>someRepellers)
//		in the setup
//		PSUtil.displaySomeRepellers(someRepellers);

		runtimeCounter++;
		
		//PDXIII TUIO Stuff

		
		tuioCursorList = new ArrayList<TuioCursor> (tuioClient.getTuioCursors());
		
		
//		//just for adjustment
//		debug.drawGrid();
		debug.drawCursors(tuioCursorList);
//		debug.drawCursorCount(tuioCursorList);
//		//end PDXIII TUIO Stuff
		debug.writeIMGs();

	}
	
//	write an rect at everyframe
	void clearScreen(){
		noStroke();
		fill(360,0,0,23);
		rect(0,0,width,height);
	}
	
	void drawBG(){
		
		//PDXIII background Stuff
		tint(tinter, 255, 255,100);
		image(fadingBG,0,0);	
		tinter += 0.5f;	
		if(tinter > 360){ tinter = 0;}
		//end PDXIII background Stuff
		
	}
	
    public void theBackground(){
        
        tint(220, 40+tinter, 40+tinter);
        image(fadingBG,0,0);
        if (tinter >= tintMax){tintBack = true;}
        
        if (tinter <= tintMin){tintBack = false;}
        
        if(!tintBack){
                tinter += 0.2f;
        }else{
                tinter -= 0.2f;
        }
    }
        
        
	
	//PDXIII TUIO Stuff
	public void drawObstacleObjects(){
		
		for(int i = 0; i < obstclObjList.size(); i++){
			
			ObstacleObject obstclObject = (ObstacleObject) obstclObjList.get(i);
			obstclObject.draw();
		}
	}
	

	
	//end PDXIII TUIO Stuff


	// TUIO methods
	@Override
	public void addTuioCursor(TuioCursor arg0) {
		
		float nowX = arg0.getScreenX(width);
		float nowY = arg0.getScreenY(height);
		int nowID = arg0.getCursorID();
		PVector nowPos = new PVector(nowX, nowY);
		
		for(int j = 0; j < obstclObjList.size(); j++){
			
			ObstacleObject obstclObject = (ObstacleObject) obstclObjList.get(j);
				
			if(obstclObject.boundingBox.contains(nowX, nowY)){
				
				if(obstclObject.coursor01ID < 99){
					
					obstclObject.coursor02ID = nowID;
					obstclObject.coursor02Pos = nowPos;
					obstclObject.newCoursor02Pos = nowPos;

					
				}else{
				
					obstclObject.coursor01ID = nowID;
					obstclObject.coursor01Pos = nowPos;
					obstclObject.newCoursor01Pos = nowPos;

					obstclObject.setOffset(nowPos);
					
					obstclObject.setTime_01();
				}
			}
		}
	}

	@Override
	public void updateTuioCursor(TuioCursor arg0) {
		
		float nowX = arg0.getScreenX(width);
		float nowY = arg0.getScreenY(height);
		int nowID = arg0.getCursorID();

		
		PVector nowPos = new PVector(nowX, nowY);

		
		for(int j = 0; j < obstclObjList.size(); j++){
			
			ObstacleObject obstclObject = (ObstacleObject) obstclObjList.get(j);
				
			
			if(obstclObject.coursor01ID == nowID && obstclObject.coursor02ID == 99){
				
				obstclObject.move(nowPos);
				obstclObject.newCoursor01Pos = nowPos;
				
			
			}else if(obstclObject.boundingBox.contains(nowX, nowY)){
				
				if(obstclObject.coursor01ID < 99 && obstclObject.coursor01ID != nowID){
					
					obstclObject.coursor02ID = nowID;
					obstclObject.coursor02Pos = nowPos;
					
				}else{
					
					obstclObject.coursor01ID = nowID;
					obstclObject.coursor01Pos = nowPos;

					obstclObject.setOffset(nowPos);
					
				}
			}
		}
		
//		PARTICLE STUFF
//		this method sets the Force and Speed if the Particles depending on the Radius
//		of the Repellers in the Obstacle Object
//		bigger repeller means more force and speed
//		ptclsReactOnObject();
		
//		this method sets the radius of the path depending on the radius 
//		of the Repellers in the Obstacle Object
//		bigger repellers means wider path

	}
	
	@Override
	public void removeTuioCursor(TuioCursor arg0) {
		
		int nowID = arg0.getCursorID();

		for(int j = 0; j < obstclObjList.size(); j++){
			
			ObstacleObject obstclObject = (ObstacleObject) obstclObjList.get(j);
			
			if(obstclObject.coursor01ID == nowID){
				
				obstclObject.coursor01ID = 99;
				obstclObject.setTime_02();
			}
			
			if(obstclObject.coursor02ID == nowID){
				
				obstclObject.coursor02ID = 99;
			}
		}
	}
	
	@Override
	public void addTuioObject(TuioObject arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void refresh(TuioTime arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeTuioObject(TuioObject arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateTuioObject(TuioObject arg0) {
		// TODO Auto-generated method stub
		
	}
	// TUIO methods end
	

	
		public void keyPressed() {
			  if (key == 'd') {
//				do something fancy
			  }
			  
//			    if( key==CODED ){
//			        if( keyCode == UP ){ 
//			        }
//			        if( keyCode == DOWN ){ 
//			        }
//			        if( keyCode == LEFT ){ 
//			        }
//			        if( keyCode == RIGHT ){ 
//			        }
//			    }
			}

			
		
			public void keyReleased(){
				
//				 not important for the main programm
				if (key == 's' || key == 'S') {
					debug.saveFrame(time);
					
				}	
				if (key == 'e' || key == 'E') {
				exit();			
				}
				if (key == 'i' || key == 'I') {
					debug.writeImg = true;
				}
				if (key == 'o' || key == 'O') {
					debug.writeImg = false;
				}
				
				
			}
			public void mousePressed() {
//			  PSUtil.newPtkl(this, mouseX, mouseY, ptclsList, ptclRadius);
				
				for (int i = 0; i < someRepellers.size(); i++) {
				    Repeller r = someRepellers.get(i); 
				    r.clicked(mouseX,mouseY);
				  }
			}
			
			public void mouseReleased() {
				
				  for (int i = 0; i < someRepellers.size(); i++) {
				    Repeller r = someRepellers.get(i); 
				    r.stopDragging();
				  }
				}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		PApplet.main(new String[] { tmnuelaerm.TmnUELaerm.class.getName() });
	}
}
