package tmnuelaerm;

import TUIO.TuioListener;
import interaction.TNObstacleObject;
import interaction.TNTransformableObject;

import java.util.ArrayList;
import java.util.List;

import old.ObstacleObject;

import TUIO.TuioClient;
import TUIO.TuioCursor;
import TUIO.TuioObject;
import TUIO.TuioTime;

import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PVector;


import util.Debug;
import util.PSUtil;
import util.Style;
import util.XMLImporter;


import particleSystem.Particle;
import particleSystem.ParticleSystem;
import particleSystem.Path;
import particleSystem.Property;
import particleSystem.Repeller;



/**
 * This is the main Class of the 
 * Multitouch project by the-moron.net 
 * represented by fabiantheblind and PDXIII 
 * students at the University of Applied Sciences Potsdam (FHP) 
 * during the class of Till Nagel "Urbane Ebenen" (urban layers).
 * the {@code code} is available here: <a href="http://code.google.com/p/tmn-ue-learm/" target="blanc">Google Code</a>
 * or here: <a href="http://github.com/fabiantheblind/TMN_UE_Laerm.git" target="blanc"> GitHub</a>
 * @author PDXIII 
 * @author fabianthelbind
 * @version 0.79
 *
 *
 */
public class TmnUELaerm extends PApplet implements TuioListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1217858472488377688L;

	/**
	 * the Font Object
	 */

	/**
	 * An List of TNObstacleObject
	 * @see Class TNObstacleObject Class
	 */
	List<TNObstacleObject> transObjects = new ArrayList<TNObstacleObject>();
	
	/**
	 * controls the amount of TNObstleOjects
	 */
	public int howManyObstacles = 4;

//	public ArrayList<ObstacleObject> obstclObjList;
	
	/**
	 * the number of Obstacles
	 * @see Class ObstacleObject Class
	 */
	public int obstclCounter;
	
	/**
	 * if something is pressed... Ask PDXIII
	 */
	public boolean isPressed = false;
	
	/**
	 * The Tuio Client
	 */
	public TuioClient tuioClient = new TuioClient();
	
	/**
	 * a ArrayList of tuio cursors
	 */
	public ArrayList<TuioCursor> tuioCursorList;
	

	/**
	 * our particle System
	 * @see Class ParticleSystem Class
	 */
	public ParticleSystem ps;
	
//	Some Arraylists to store the objects
	
	/**
	 * An ArrayList of Particles
	 * @see Class Particle Class
	 */
	public ArrayList <Particle> ptclsList =  new ArrayList<Particle>();
	
	/**
	 * An ArrayList of Repelers
	 * @see Class Repeller Class
	 */
	public ArrayList<Repeller> repellers;
	
	/**
	 * An ArrayList of Paths
	 * @see Class Path Class
	 */
	public ArrayList <Path> pathsList = new ArrayList <Path>();
		
	/**
	 * This is for debugging and make some lose repellers
	 * @see Class Repeller CLass
	 * @see PSUtil#makeSomeRepellers(ArrayList)
	 * @see PSUtil#displaySomeRepellers(ArrayList)
	 */
	public ArrayList<Repeller> someRepellers = new ArrayList<Repeller>();
	
	/**
	 *  number of particles
	 */
	int numPtcls = 1005;
	
	/**
	 * An ArrayList of {@link PSUtil#initPropertysList()} for all the Objects
	 */
	public ArrayList<Property>propertysList;
	
//	every particle can have his own force / radius / speed
//	they can be changed later
//	this is for the collision of particles
	/**
	 * standard radius for the particles
	 */
	float ptclRadius = 2; // 

	/**
	 * a boolean for switching the path
	 */
	private boolean switchPath = false;
	
	public boolean DAY = true;

	/**
	 * to count the time
	 */
	public static int runtimeCounter;
//	
	/**
	 * just for unique filenames when saving a frame as .jpg in the folder data
	 * @see Debug#saveFrame(float)
	 */
	public static float time;
	
	/**
	 * display the debug Stuff
	 */
	private boolean showDebug = false;
	private boolean showDebugPath = false;


	
//	start PDXIII background stuff
	/**
	 * PDXIII background Stuff
	 */
	public PImage fadingBG;
	/**
	 * PDXIII background Stuff
	 */
	public float tinter;
	/**
	 * PDXIII background Stuff
	 */
	public boolean tintBack = false;
	/**
	 * PDXIII background Stuff
	 */
    public float tintMax = 60;
	/**
	 * PDXIII background Stuff
	 */
    public float tintMin = 20;


//    end PDXIII background stuff
    

//    this is for selecting paths not randomly
//	private int myPathNum;
//	private int myDirection;
//	private int myRange;
//	private int myCounter;
	
	/* (non-Javadoc)
	 * @see processing.core.PApplet#setup()
	 */
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
//		this is for importing the Property of the objects from an .xml file
		XMLImporter.setPAppelt(this);
		propertysList = PSUtil.initPropertysList();
//		println(propertysList.get(1).name +" "+propertysList.get(1).getDayValues()[1]);
//		exit();
		
		
		background(0);
		size(1024, 768,OPENGL);
		frameRate(25);
		

		//PDXIII background Stuff
		fadingBG = loadImage("fadingBG.png");

		//PDXIII TUIO Stuff
		// enable on system installed fonts
		hint(ENABLE_NATIVE_FONTS);


		//init TUIO
		tuioClient.addTuioListener(this);
		tuioClient.connect();
		
//		obstclObjList = new ArrayList<ObstacleObject>();
		
		// making ObstacleObjects
		
//		transObjects.add(new TNObstacleObject(this, 0, 0, 200, 200));
//		transObjects.add(new TNObstacleObject(this, 400, 400, 200, 200));
		for(int i = 0; i < howManyObstacles; i++){
			Property property = propertysList.get(i);
			transObjects.add(new TNObstacleObject(this, 50*i, 50*i,0, 0, property));
			
		}
		
//		for(int i = 0; i < propertysList.size(); i++){
//			Property property = propertysList.get(i);
//			transObjects.add(new TNObstacleObject(this, 50*i, 50*i, 100, 100, property));
//			
//		}
//		transObjects.add(new TNObstacleObject(this, 0, 0, 200, 200));
//		transObjects.add(new TNObstacleObject(this, 400, 400, 200, 200));

//		
//		for (obstclCounter = 0; obstclCounter < 4; obstclCounter++){
//			int obstclNo = obstclCounter + 1;
//			float firstX = obstclNo*150;
//			float firstY = height/2;
//			PVector obstclPos = new PVector (firstX, firstY);
//			obstclObjList.add(new ObstacleObject(this, obstclNo, obstclPos));
//			
//		}
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
		ps = new ParticleSystem(this,0,new PVector(width/2,height/2),ptclsList);
		
		
	time = millis();
	runtimeCounter = 0;	
		
//	 this is for a constant change in the path choosing.
//	right now it is an random that sets the particle.pathNum
//	 myPathNum = 0;
//	 myDirection = 1;
//	 myRange = pathsList.size();
//	 myCounter  = 1;
	 
	 //DEBUGGING
//	 PSUtil.makeSomeRepellers( someRepellers);
	 PSUtil.makeSomeRepellersWithPropertys(someRepellers, propertysList);
//	 the XMLImporter Class in a nutshell
//	 for more explanation see the function
//	 XML_IMPORTER_Class_inANutshell()
//	 XMLImporter.XML_IMPORTER_Class_inANutshell();
	 
	}

	/* (non-Javadoc)
	 * @see processing.core.PApplet#draw()
	 */
	public void draw() {
//		PDXBGStuff
//		drawBG();
		theBackground();
//		just a clearScreen method
//		clearScreen();
		smooth();
//			
//		this is for the particles that make the paths
//		to get them back into ther original position we have to reset them
//		in the function Path.resetPointPtcls() you can set
//		how fast and strong the want to get back 

		if(runtimeCounter%42==1){
		for(int pl2 = 0; pl2<pathsList.size();pl2++ ){
//		pathsList.get(pl2).ptclPathDisplay();
		pathsList.get(pl2).resetPointPtcls(); 
		}
		}
//		this is for switching every 300 frames the path to follow
		if(runtimeCounter%432 == 0){
//			println("Range: "+myRange +"   Pathnum: "+myPathNum +"   Dir: "+myDirection);
			switchPath = true;
//			myPathNum += 1*myDirection;
//			if((myPathNum > myRange-2)||(myPathNum == 0)){
//				myDirection = -myDirection;
//			}
			
			if(DAY){
				DAY=false;
			}else{
				DAY=true;
			}
			
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
		
		for(int j = 0; j < transObjects.size(); j++){
			
			TNObstacleObject obstclObject = (TNObstacleObject) transObjects.get(j);
			if(obstclObject.ObstclsRepellerList != null){
				for(int k = 0; k< obstclObject.ObstclsRepellerList.size();k++){
					
					repellers.add(obstclObject.ObstclsRepellerList.get(k));
				}
			}
		}
		
		for(int i = 0; i< repellers.size(); i++){
			
			Repeller rep = (Repeller) repellers.get(i);
			rep.display();
			
		}
//		display all objects
//		drawObstacleObjects();
		for (TNTransformableObject transformableObject : transObjects) {
			transformableObject.draw();
		}
//		DEBUGGING START
		// Apply repeller objects to all Particles
		ps.myApplyRepellers(someRepellers,DAY);
//		DEBUGGING END
		
//		pass all Objects over to the ParticleSystem
		ps.myApplyObstcles(transObjects, DAY);
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

//		DEBUGGING START press "d"
		if(showDebug){
		Debug.watchAParticle(ptclsList, ps);
		Debug.watchARepellers(someRepellers);
		PSUtil.displaySomeRepellers(someRepellers);
		
		if (showDebugPath){Debug.displayAllPaths(pathsList);}
		Debug.drawFrameRate();
		Debug.drawFrameCount();
		}
//		DEBUGGING END
		
//		DEBUGGING START	
//		//just for adjustment
//		debug.drawGrid();
		Debug.drawCursors(tuioCursorList);
//		Debug.drawCursorCount(tuioCursorList);

//		Debug.writeIMGs();
//		DEBUGGING END

	}
	
//	write an rect at everyframe
	/**
	 * A Clear Screen Method
	 * writes a {@code processing.core.PApplet.rect(float, float, float, float)} every Frame
	 * 
	 */
	void clearScreen(){
		noStroke();
		fill(Style.clsColor);
		rect(0,0,width,height);

	}
	
	/**
	 * PDXIII background Stuff
	 */
	void drawBG(){
		
		//PDXIII background Stuff
		tint(tinter, 255, 255,100);
		image(fadingBG,0,0);	
		tinter += 0.5f;	
		if(tinter > 360){ tinter = 0;}
		//end PDXIII background Stuff
		
	}
	
	/**
	 * PDXIII background Stuff
	 */
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
	/**
	 * PDXIII TUIO Stuff
	 */
//	public void drawObstacleObjects(){
//		
//		for(int i = 0; i < obstclObjList.size(); i++){
//			
//			ObstacleObject obstclObject = (ObstacleObject) obstclObjList.get(i);
//			obstclObject.draw();
//		}
//	}
	

	
	//end PDXIII TUIO Stuff


	// TUIO methods
	/* (non-Javadoc)
	 * @see TUIO.TuioListener#addTuioCursor(TUIO.TuioCursor)
	 */
	@Override
//	public void addTuioCursor(TuioCursor arg0) {
//		
//		float nowX = arg0.getScreenX(width);
//		float nowY = arg0.getScreenY(height);
//		int nowID = arg0.getCursorID();
//		PVector nowPos = new PVector(nowX, nowY);
//		
//		for(int j = 0; j < obstclObjList.size(); j++){
//			
//			ObstacleObject obstclObject = (ObstacleObject) obstclObjList.get(j);
//				
//			if(obstclObject.boundingBox.contains(nowX, nowY)){
//				
//				if(obstclObject.coursor01ID < 99){
//					
//					obstclObject.coursor02ID = nowID;
//					obstclObject.coursor02Pos = nowPos;
//					obstclObject.newCoursor02Pos = nowPos;
//
//					
//				}else{
//				
//					obstclObject.coursor01ID = nowID;
//					obstclObject.coursor01Pos = nowPos;
//					obstclObject.newCoursor01Pos = nowPos;
//
//					obstclObject.setOffset(nowPos);
//					
//					obstclObject.setTime_01();
//				}
//			}
//		}
//	}
	
	public void addTuioCursor(TuioCursor tcur) {
		// Hit test for all objects: first gets the hit, ordered by creation.
		// TODO Order by z-index, updated by last activation/usage
		for (TNObstacleObject ttObj : transObjects) {
			if (ttObj.isHit(tcur.getScreenX(width), tcur.getScreenY(height))) {
				ttObj.addTuioCursor(tcur);
				break;
			}
		}
	}

	/* (non-Javadoc)
	 * @see TUIO.TuioListener#updateTuioCursor(TUIO.TuioCursor)
	 */
	@Override
//	public void updateTuioCursor(TuioCursor arg0) {
//		
//		float nowX = arg0.getScreenX(width);
//		float nowY = arg0.getScreenY(height);
//		int nowID = arg0.getCursorID();
//
//		
//		PVector nowPos = new PVector(nowX, nowY);
//
//		
//		for(int j = 0; j < obstclObjList.size(); j++){
//			
//			ObstacleObject obstclObject = (ObstacleObject) obstclObjList.get(j);
//				
//			
//			if(obstclObject.coursor01ID == nowID && obstclObject.coursor02ID == 99){
//				
//				obstclObject.move(nowPos);
//				obstclObject.setTime_01();
//
//				obstclObject.newCoursor01Pos = nowPos;
//				
//			
//			}else if(obstclObject.boundingBox.contains(nowX, nowY)){
//				
//				
//				if(obstclObject.coursor01ID < 99 && obstclObject.coursor01ID != nowID){
//					
//					obstclObject.coursor02ID = nowID;
//					obstclObject.coursor02Pos = nowPos;
//					
//				}else{
//					
//					obstclObject.coursor01ID = nowID;
//					obstclObject.coursor01Pos = nowPos;
//					obstclObject.setTime_01();
//
//					obstclObject.setOffset(nowPos);
//					
//				}
//			}
//		}
	
	public void updateTuioCursor(TuioCursor tcur) {
		for (TNObstacleObject ttObj : transObjects) {
			if (ttObj.isHit(tcur.getScreenX(width), tcur.getScreenY(height))) {
				ttObj.updateTuioCursor(tcur);
				break;
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

	
	
	/* (non-Javadoc)
	 * @see TUIO.TuioListener#removeTuioCursor(TUIO.TuioCursor)
	 */
	@Override
//	public void removeTuioCursor(TuioCursor arg0) {
//		
//		int nowID = arg0.getCursorID();
//
//		for(int j = 0; j < obstclObjList.size(); j++){
//			
//			ObstacleObject obstclObject = (ObstacleObject) obstclObjList.get(j);
//			
//			if(obstclObject.coursor01ID == nowID){
//				
//				obstclObject.coursor01ID = 99;
//				obstclObject.coursor01Pos = null;
//				obstclObject.setTime_02();
//			}
//			
//			if(obstclObject.coursor02ID == nowID){
//				
//				obstclObject.coursor02ID = 99;
//				obstclObject.coursor02Pos = null;
//
//			}
//		}
//	}
	
	public void removeTuioCursor(TuioCursor tcur) {
		for (TNObstacleObject ttObj : transObjects) {
			// Pass trough remove-event to all objects, to allow fingerUp also out of boundaries,
			// as objects decide themselves (via cursor-id) whether cursor belongs to it.

			ttObj.removeTuioCursor(tcur);
		}
	}
	
	/* (non-Javadoc)
	 * @see TUIO.TuioListener#addTuioObject(TUIO.TuioObject)
	 */
	@Override
	public void addTuioObject(TuioObject arg0) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see TUIO.TuioListener#refresh(TUIO.TuioTime)
	 */
	@Override
	public void refresh(TuioTime arg0) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see TUIO.TuioListener#removeTuioObject(TUIO.TuioObject)
	 */
	@Override
	public void removeTuioObject(TuioObject arg0) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see TUIO.TuioListener#updateTuioObject(TUIO.TuioObject)
	 */
	@Override
	public void updateTuioObject(TuioObject arg0) {
		// TODO Auto-generated method stub
		
	}
	// TUIO methods end
	

	
		/* (non-Javadoc)
		 * @see processing.core.PApplet#keyPressed()
		 */
		public void keyPressed() {
			  if (key == 'd') {
//				do something fancy
				  if(showDebug == true){
					  showDebug = false;
					  
				  }else{
					  showDebug = true;
					  
				  }
			  }
			  if (key == 'p') {
//					do something fancy
					  if(showDebugPath == true){
						  showDebugPath = false;
						  
					  }else{
						  showDebugPath = true;
						  
					  }
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

			
		
			/* (non-Javadoc)
			 * @see processing.core.PApplet#keyReleased()
			 */
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
			/* (non-Javadoc)
			 * @see processing.core.PApplet#mousePressed()
			 */
			public void mousePressed() {
//			  PSUtil.newPtkl(this, mouseX, mouseY, ptclsList, ptclRadius);
				
				for (int i = 0; i < someRepellers.size(); i++) {
				    Repeller r = someRepellers.get(i); 
				    r.clicked(mouseX,mouseY);
				  }
			}
			
			/* (non-Javadoc)
			 * @see processing.core.PApplet#mouseReleased()
			 */
			public void mouseReleased() {
				
				  for (int i = 0; i < someRepellers.size(); i++) {
				    Repeller r = someRepellers.get(i); 
				    r.stopDragging();
				  }
				}

	/**
	 *
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		PApplet.main(new String[] { tmnuelaerm.TmnUELaerm.class.getName() });
	}
}
