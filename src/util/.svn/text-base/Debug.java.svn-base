package util;

import java.util.ArrayList;

import TUIO.TuioCursor;

import particleSystem.Particle;
import particleSystem.ParticleSystem;
import particleSystem.Path;
import particleSystem.Property;
import particleSystem.Repeller;
import processing.core.PApplet;
import processing.core.PVector;

/**
 *  this is for debbuging only and has no effect on the Application
 * @author fabianthelbind
 * @author PDXIII
 *
 *
 */
public class Debug {

	
	
		/**
		 * for writing images if true
		 * @see #writeIMGs()
		 * 
		 */
		public static boolean writeImg = false;
		/**
		 * the PApplet
		 */
		private static PApplet p;
		
		/**
		 * a unique number for the image
		 * @see #writeIMGs()
		 */
		private static int imgNum = 0;



		
		/**
		 * to pass the PApplet to all Methods
		 * a static Class doesn't need a Constructor
		 * @param p_ the PApplet
		 */		
		public static void setPAppletDebug(PApplet p_) {
			p  = p_;

		}

		/**
		 * write some images (TIff Sequenzes for videos)
		 * @see #imgNum
		 * @see #writeImg
		 * @see <a href="../tmnuelaerm/TmnUelaerm.html#keyReleased()"><code>keyReleased</code></a>
		 */
		public static void writeIMGs(){
			if(writeImg){
				String sa = PApplet.nf(imgNum,6);
				 p.saveFrame("./bilder/ParticleSystem-"+sa+".tif");
				  imgNum++;
			}
		}
	
	
		/**
		 * the position of the watchAParticle() Method
		 * @see #watchAParticle(ArrayList, ParticleSystem)
		 */
		private static PVector WAP_position = new PVector (800,50);
		
		/**
		 *  this is for looking at one Particle  
		 * @param ptclsList
		 * @param ps instance of ParticleSystem
		 * @see <a href="../particleSystem/ParticleSystem.html"><code>ParticleSystem</code></a>
		 * @see <a href="../particleSystem/Particle.html"><code>Particle</code></a>
		 * @see #drawMyPtclForce(PVector, Particle)
		 */
		public static void watchAParticle(ArrayList <Particle> ptclsList, ParticleSystem ps){
	        

			Particle myPtcl = ptclsList.get(0);
//			float distToCenterPS = myPtcl.loc.dist(ps.origin);

			myPtcl.setColorCol1(150, 100, 100, 100);
			myPtcl.setColorCol2(150, 100, 100, 20);
			p.noFill();
			p.strokeWeight(1);
			p.stroke(Style.textColorWhite);
			p.line(WAP_position.x-7, WAP_position.y-3, myPtcl.loc.x-myPtcl.radius, myPtcl.loc.y-myPtcl.radius);
			p.stroke(150,100,100,100);
			p.ellipseMode(PApplet.CENTER);
			p.ellipse(myPtcl.loc.x, myPtcl.loc.y, 10, 10);
			p.noFill();
			drawMyPtclForce(WAP_position, myPtcl);
//			PApplet.println(myPtcl.maxforce);
//			myPtcl.setRadius(10);
			
//			println("MyPtkls Data -- Gravity: " +nf(myPtcl.gravity,7,7)
//					+" Mass: "+nf(myPtcl.mass,7,7)
//					+" Speed: "+nf(myPtcl.maxspeed,7,7)
//					+" Force: "+nf(myPtcl.maxforce,7,7)
//					+" vel.x: "+nf(myPtcl.vel.x,7,7)+" vel.y: "+nf(myPtcl.vel.y,7,7)
//					);
			
			
		}
		/**

		 * this is for drawing a specific float or integer value on the screen
		 * used in watchAParticle
		 * @param pos PVector the position for the Particles forces
		 * @param myPtcl Particle to analyze
		 * @see #watchAParticle(ArrayList, ParticleSystem)
		 */
		private static void drawMyPtclForce(PVector pos,Particle myPtcl){
			
			p.textFont(Style.MisoReg13);
			int lineheight = 14;
		
			makePtclTextWithShadow(pos, Style.textColorBlk, lineheight, myPtcl);
			makePtclTextWithShadow(new PVector(pos.x -1,pos.y -1), Style.textColorWhite, lineheight, myPtcl);

			
			p.noStroke();
		}
		
		private static void makePtclTextWithShadow(PVector pos,int color,int lineheight,Particle myPtcl){

			p.fill(color);
			p.text("The life of one Particle", (lineheight*0) + pos.x, 0 + pos.y);
			p.text("MaxForce: "+ myPtcl.maxforce, 0+ pos.x, (lineheight*1) + pos.y);
			p.text("MaxSpeed: "+ myPtcl.maxspeed, 0+ pos.x, (lineheight*2) + pos.y);
			p.text("Mass: "+ myPtcl.mass, 0+ pos.x, (lineheight*3) + pos.y);
			p.text("Gravity: "+ myPtcl.gravity, 0+ pos.x, (lineheight*4) + pos.y);		
			if (myPtcl.lifeTime< 100000.0f){
			p.text("Lifetime: "+ myPtcl.lifeTime, 0+ pos.x, (lineheight*5) + pos.y);	
			}
			p.noFill();
			
			
		}
		
		
		/**
		 * this is for drawing all the {@link Repeller#property} to the screen
		 * @param someRepellers ArrayList of {@link Repeller}
		 * @see #drawRepellerData(Repeller)
		 */
		public static void watchARepellers(ArrayList<Repeller> someRepellers){
			
			
			for (int j = 0; j < someRepellers.size(); j++) {
				Repeller myRepeller = someRepellers.get(j);
				myRepeller.setColor1(150, 100, 100, 100);
				myRepeller.setColor2(150, 100, 100, 50);
				drawRepellerData(myRepeller);
				
			}
			
			
			
		}
		
		
		/**
		 * this is for drawing all data near the {@code Repeller}
		 * @param myRep A {@link Repeller}
		 */
		private static void drawRepellerData(Repeller myRep) {
			// TODO Auto-generated method stub
			p.textFont(Style.MisoReg13);
			int lineheight = 16;
			p.noStroke();
			makeRepTextWithShadow(myRep.loc, Style.textColorBlk, lineheight, myRep.property);
			makeRepTextWithShadow(new PVector(myRep.loc.x -1,myRep.loc.y-1), Style.textColorWhite, lineheight, myRep.property);
			p.noFill();
			
			
		}

		private static void makeRepTextWithShadow(PVector loc,int color,int lineheight,Property property){
			p.fill(color);
//			p.text("Data of this Repeller",   loc.x +12, 0 + loc.y+12 +(lineheight*0));
			p.text("index: " +property.index,  loc.x +12, 0 + loc.y+12+(lineheight*0) );
			p.text("Name: " + property.name,   loc.x +12, 0 + loc.y+12+(lineheight*1));
			p.text("Propertys : " +"Day "
					+" prvt:( "+property.valueByIndex(0,0)+") "
					+" pblc:( "+property.valueByIndex(0,1)+") "
					+" work:( "+property.valueByIndex(0,2)+") "
					, loc.x +12, 0 + loc.y+11+(lineheight*2));
			p.text("Propertys : " +"Nite "
					+" prvt:( "+property.valueByIndex(1,0)+") "
					+" pblc:( "+property.valueByIndex(1,1)+") "
					+" work:( "+property.valueByIndex(1,2)+") "
					, loc.x +12, 0 + loc.y+12+(lineheight*3));
			
			
		}

		/**
		 * @param pathsList An {@code ArrayList} of {@link Path}
		 */
		public static void displayAllPaths(ArrayList<Path>pathsList){
			
			for(int i =0; i<pathsList.size();i++){
				
				pathsList.get(i).ptclPathDisplay();
				
			}
			
		}
		

		/**
		 * Draw a Grid for adjustment
		 */
		public static void drawGrid(){
			
			float gridSize = 100;
			
			for(int i = 0; i < 100; i++){
				p.strokeWeight(1);
				p.stroke(0);
				
				p.line(i*gridSize, 0, i*gridSize, p.height);
				p.line(0, i*gridSize, p.width, i*gridSize);
				
				p.noStroke();
			}
		}
		
		/**
		 * draw the tuio Cursors
		 * @param tuioCursorList
		 * @see java.util.ArrayList
		 * 
		 */
		public static void drawCursors(ArrayList <TuioCursor>tuioCursorList){
			
			for (int i=0; i<tuioCursorList.size(); i++) {
				TuioCursor tcur = (TuioCursor)tuioCursorList.get(i);
				p.stroke(100,255,255);
				p.noFill();
				p.ellipse( tcur.getScreenX(p.width), tcur.getScreenY(p.height),10,10);
			}
		}
		
		/**
		 * 
		 * this is an overlay for not using the console
		 * draws the number of active cursors on the screen
		 * @param tuioCursorList
		 * @see java.util.ArrayList
		 */
		public static void drawCursorCount(ArrayList <TuioCursor>tuioCursorList){
			
			p.noStroke();
			p.fill(Style.textColorWhite);
			p.text(tuioCursorList.size(), 50, 50);
			p.noFill();
		}
		
		/**
		 * this is an overlay for not using the console
		 * draws the processing.frameRate on the screen
		 */
		public static void drawFrameRate(){
			
			p.noStroke();
			p.fill(Style.textColorBlk);
			p.text("Framerate: "+ p.frameRate, 50, 50);
			p.noFill();
		}
		/**
		 * this is an overlay for not using the console
		 * draws the processing.frameCount on the screen
		 */
		public static void drawFrameCount(){
			
			p.noStroke();
			p.fill(Style.textColorBlk);
			p.text("Framecount: "+ p.frameCount, 50, 70);
			p.noFill();
		}
		





		/**
		 * for writing single images on keystroke
		 * @param time
		 */
		public static void saveFrame(float time) {
			// TODO Auto-generated method stub
			p.saveFrame("./bilder/MyImg"+time+".jpg");

		}

}
