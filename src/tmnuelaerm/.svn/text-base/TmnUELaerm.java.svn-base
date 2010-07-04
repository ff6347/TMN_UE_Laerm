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
import util.PSUtil;
import util.Style;
import util.XMLImporter;


import particleSystem.Particle;
import particleSystem.ParticleSystem;
import particleSystem.Path;
import particleSystem.Repeller;



/**
 * @author PDXIII 
 * @author fabianthelbind
 *
 */
public class TmnUELaerm extends PApplet implements TuioListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1217858472488377688L;

	public PFont font;

	public ArrayList<ObstacleObject> obstclObjList;
	public int obstclCounter;
	public boolean isPressed = false;
	public TuioClient tuioClient = new TuioClient();
	public ArrayList<TuioCursor> tuioCursorList;
	
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
//	this is for the collision of particles
	float ptclRadius = 2; // standard radius for the particles


//	ThePath Number
	private int pathNum = 0;
//	a boolean for switching the path
	private boolean switchPath = false;
//	to count the time
	public static int runtimeCounter;
//	just for unique filenames when saving a frame as .jpg in the folder data
	public static float time;

	
	//PDXIII background Stuff
	public PImage fadingBG;
	public float tinter;
	public boolean tintBack = false;
    public float tintMax = 60;
    public float tintMin = 20;

    //end PDXIII background Stuff
    

//    this is for selecting paths not randomly
//	private int myPathNum;
//	private int myDirection;
//	private int myRange;
//	private int myCounter;
	
	public void setup() {
		

		colorMode(HSB,360,100,100);
		//passing the PApplet thru to all static methods
//		make the Styling
		Style.setPApplet(this);
		Style.create();
//		this is for overlays and Stuff
		Debug.setPAppletDebug(this);
//		these are some methods that help with the ParticleSystem
		PSUtil.setPApplet(this);
//		this is for importing the Propertys of the objects from an .xml file
		XMLImporter.setPAppelt(this);
		
		background(0);
		size(1024, 768,OPENGL);
		frameRate(25);
		

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
		

		obstclObjList = new ArrayList<ObstacleObject>();
		
		// making ObstacleObjects
		for (obstclCounter = 0; obstclCounter < 4; obstclCounter++){
			int obstclNo = obstclCounter + 1;
			float firstX = obstclNo*150;
			float firstY = height/2;
			PVector obstclPos = new PVector (firstX, firstY);
			obstclObjList.add(new ObstacleObject(this, obstclNo, obstclPos));
			
		}
		//end PDXIII TUIO Stuff
		
//		particle stuff
		int [] pathsSize = {80,100,120,230,250,270,380,400,420};
		int [] pathsRadius = {20,60,30,20,60,30,20,60,30};		
		for(int p = 0;p<9;p++){
			pathsList.add(PSUtil.initCirclePath( 13, pathsRadius[p],pathsSize[p]));
			
		}
		
		 // Call a function to generate new Path object with 23 segments
		//path = PSUtil.initCirclePath(this, path, 23);
		
		  // We are now making random Particles and storing them in an ArrayList ptclsList
		ptclsList = PSUtil.initParticles( numPtcls, ptclRadius, ptclsList);
		
//		add the  Path ptclPoints ArrayList of Particles to the ptclsList
		for(int pl  =0; pl< pathsList.size();pl++){
		for(int pp = 0; pp < pathsList.get(pl).ptclPoints.size();pp++){
			ptclsList.add(pathsList.get(pl).ptclPoints.get(pp));
		}
		}
//		we need the particle system to interact with the repellers
		ps = new ParticleSystem(this,0,new PVector(width/2,height/2),ptclsList,pathsList.get(pathNum));
		
		
	time = millis();
	runtimeCounter = 0;	
		
//	 this is for a constant change in the path choosing.
//	right now it is an random that sets the particle.pathNum
//	 myPathNum = 0;
//	 myDirection = 1;
//	 myRange = pathsList.size();
//	 myCounter  = 1;
	 
	 //DEBUGGING
	 PSUtil.makeSomeRepellers( someRepellers);
	 
//	 the XMLImporter Class in a nutshell
//	 for more explanation see the function
//	 XML_IMPORTER_Class_inANutshell()
	 XMLImporter.XML_IMPORTER_Class_inANutshell();
	 
	}

	public void draw() {
//		PDXBGStuff
//		drawBG();
//		theBackground();
//		just a clearScreen method
		clearScreen();
		smooth();
//			
//		this is for the particles that make the paths
//		to get them back into ther original position we have to reset them
//		in the function Path.resetPointPtcls() you can set
//		how fast and strong the want to get back 
		for(int pl2 = 0; pl2<pathsList.size();pl2++ ){
//		pathsList.get(pl2).ptclPathDisplay();
		pathsList.get(pl2).resetPointPtcls(); 
		}

//		DEBUGGING START
		Debug.watchAParticle(ptclsList, ps);

		PSUtil.displaySomeRepellers(someRepellers);
//		DEBUGGING END
		
//		this is for switching every 300 frames the path to follow
		if(runtimeCounter%300 == 0){
//			println("Range: "+myRange +"   Pathnum: "+myPathNum +"   Dir: "+myDirection);
			switchPath = true;
//			myPathNum += 1*myDirection;
//			if((myPathNum > myRange-2)||(myPathNum == 0)){
//				myDirection = -myDirection;
//			}
			
			
			}
		for (int i = 0; i < ptclsList.size(); i++) {
				Particle ptcl =  ptclsList.get(i);
//				if the particle is not part of a path
				if(ptcl.hidden!=true){

				// Path following and separation are worked on in this function
//					pathNum = floor(random(0,8));
//					println(pathNum);
//					use the myPathNum variable for constant switching
//					use the switchPath stuff for random path selection
					
					if(switchPath){
						ptcl.pathNum =floor(random(0,8));//myPathNum;
					}
				ptcl.applyForces(ptclsList,pathsList.get(ptcl.pathNum));
				switchPath = false;

				}
				// Call the generic run method (update, borders, display, etc.)
				ptcl.run();
				
			}
		
//		must be rebuild at runtime so it doesn't store all the time new
//		repellers in the list
//		else it has to be deleted at runtime
//		does that matter to the performace?
		
//		DEBUGGING START
//		storing the loose repellers in the repellersList to make them affect
//		the particles in the particlesystem
		repellers = new ArrayList<Repeller>();
		for(int j = 0; j < someRepellers.size(); j++){	
			Repeller rep  = someRepellers.get(j);
			repellers.add(rep);
		}
//		DEBUGGING END
		
//		get all repellers in all objects into one list
//		to use them in the ParticleSystem Class
		for(int j = 0; j < obstclObjList.size(); j++){
			
			ObstacleObject obstclObject = (ObstacleObject) obstclObjList.get(j);
			if(obstclObject.ObstclsRepellerList != null){
				for(int k = 0; k< obstclObject.ObstclsRepellerList.size();k++){
					
					repellers.add(obstclObject.ObstclsRepellerList.get(k));
				}
			}
		}
//		display all objects
		drawObstacleObjects();

//		DEBUGGING START
		// Apply repeller objects to all Particles
		ps.myApplyRepellers(someRepellers);
//		DEBUGGING END
		
//		pass all Objects over to the ParticleSystem
		ps.myApplyObstcles(obstclObjList);
		// Run the Particle System
		ps.run();
		
//		DEBBUNG START
//		U need to call first the 
//		public static void PSUtil.makeSomeRepellers(PApplet p,ArrayList <Repeller>someRepellers)
//		in the setup
//		PSUtil.displaySomeRepellers(someRepellers);
//		DEBUGGING END
		runtimeCounter++;
		
		//PDXIII TUIO Stuff
		tuioCursorList = new ArrayList<TuioCursor> (tuioClient.getTuioCursors());
//		//end PDXIII TUIO Stuff

		
//		DEBUGGING START	
//		//just for adjustment
//		debug.drawGrid();
		Debug.drawCursors(tuioCursorList);
//		Debug.drawCursorCount(tuioCursorList);
		Debug.drawFrameRate();
		Debug.drawFrameCount();
//		Debug.writeIMGs();
//		DEBUGGING END

	}
	
//	write an rect at everyframe
	void clearScreen(){
		noStroke();
		fill(Style.clsColor);
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
				obstclObject.setTime_01();

				obstclObject.newCoursor01Pos = nowPos;
				
			
			}else if(obstclObject.boundingBox.contains(nowX, nowY)){
				
				
				if(obstclObject.coursor01ID < 99 && obstclObject.coursor01ID != nowID){
					
					obstclObject.coursor02ID = nowID;
					obstclObject.coursor02Pos = nowPos;
					
				}else{
					
					obstclObject.coursor01ID = nowID;
					obstclObject.coursor01Pos = nowPos;
					obstclObject.setTime_01();

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
				obstclObject.coursor01Pos = null;
				obstclObject.setTime_02();
			}
			
			if(obstclObject.coursor02ID == nowID){
				
				obstclObject.coursor02ID = 99;
				obstclObject.coursor02Pos = null;

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
					Debug.saveFrame(time);
					
				}	
				if (key == 'e' || key == 'E') {
				exit();			
				}
				if (key == 'i' || key == 'I') {
					Debug.writeImg = true;
				}
				if (key == 'o' || key == 'O') {
					Debug.writeImg = false;
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
