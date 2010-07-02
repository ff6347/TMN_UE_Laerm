package util;

import java.util.ArrayList;

import TUIO.TuioCursor;

import particleSystem.Particle;
import particleSystem.ParticleSystem;
import processing.core.PApplet;

public class Debug {

	
	
		public static boolean writeImg = false;
		public static PApplet p;
		
		private static int imgNum = 0;



		public static void setPAppletDebug(PApplet p_) {
			p  = p_;

		}
	
		
		
		// just  writing TIff Sequenzes for videos
		public static void writeIMGs(){
			if(writeImg){
				String sa = PApplet.nf(imgNum,6);
				 p.saveFrame("./bilder/ParticleSystem-"+sa+".tif");
				  imgNum++;
			}
		}
	
	
		public static void watchAParticle(ArrayList <Particle> ptclsList, ParticleSystem ps){
	        

			Particle myPtcl = ptclsList.get(0);
//			float distToCenterPS = myPtcl.loc.dist(ps.origin);

			myPtcl.setColorCol1(150, 100, 100, 100);
			myPtcl.setColorCol2(150, 100, 100, 20);
			p.fill(150,100,100,100);
			p.ellipseMode(PApplet.CENTER);
			p.ellipse(myPtcl.loc.x, myPtcl.loc.y, 10, 10);
			p.noFill();
			drawMyPtclForce(myPtcl.maxforce);
//			PApplet.println(myPtcl.maxforce);
//			myPtcl.setRadius(10);
			
//			println("MyPtkls Data -- Gravity: " +nf(myPtcl.gravity,7,7)
//					+" Mass: "+nf(myPtcl.mass,7,7)
//					+" Speed: "+nf(myPtcl.maxspeed,7,7)
//					+" Force: "+nf(myPtcl.maxforce,7,7)
//					+" vel.x: "+nf(myPtcl.vel.x,7,7)+" vel.y: "+nf(myPtcl.vel.y,7,7)
//					);
			
			
		}
		
		//a grid just for adjustment
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
		//grid end
		
		public static void drawCursors(ArrayList <TuioCursor>tuioCursorList){
			
			for (int i=0; i<tuioCursorList.size(); i++) {
				TuioCursor tcur = (TuioCursor)tuioCursorList.get(i);
				p.stroke(100,255,255);
				p.noFill();
				p.ellipse( tcur.getScreenX(p.width), tcur.getScreenY(p.height),10,10);
			}
		}
		
		public static void drawCursorCount(ArrayList <TuioCursor>tuioCursorList){
			
			p.noStroke();
			p.fill(Style.textColor);
			p.text(tuioCursorList.size(), 50, 50);
			p.noFill();
		}
		
		public static void drawFrameRate(){
			
			p.noStroke();
			p.fill(Style.textColor);
			p.text("Framerate: "+ p.frameRate, 50, 50);
			p.noFill();
		}
		public static void drawFrameCount(){
			
			p.noStroke();
			p.fill(Style.textColor);
			p.text("Framecount: "+ p.frameCount, 50, 70);
			p.noFill();
		}
		
		 private static void drawMyPtclForce(float in){
			
			p.noStroke();
			p.fill(Style.textColor);
			p.text("MaxForce: "+ in, 50, 90);
			p.noFill();
		}



		public static void saveFrame(float time) {
			// TODO Auto-generated method stub
			p.saveFrame("./bilder/MyImg"+time+".jpg");

		}

}
