package util;

import java.util.ArrayList;

import TUIO.TuioCursor;

import particleSystem.Particle;
import particleSystem.ParticleSystem;
import processing.core.PApplet;

public class Debug {

	
	
		public boolean writeImg = false;
		PApplet p;
		private int imgNum = 0;



		public Debug(PApplet p_) {
			p  = p_;

		}
	
		
		
		// just  writing TIff Sequenzes for videos
		public void writeIMGs(){
			if(writeImg){
				String sa = p.nf(imgNum,6);
				 p.saveFrame("./bilder/ParticleSystem-"+sa+".tif");
				  imgNum++;
			}
		}
	
	
		public void watchAParticle(ArrayList <Particle> ptclsList, ParticleSystem ps){
	        

			Particle myPtcl = ptclsList.get(0);
			float distToCenterPS = myPtcl.loc.dist(ps.origin);

			myPtcl.setColorCol1(200, 50, 50, 100);
			myPtcl.setColorCol2(200, 50, 50, 20);
			myPtcl.setRadius(10);
			
//			println("MyPtkls Data -- Gravity: " +nf(myPtcl.gravity,7,7)
//					+" Mass: "+nf(myPtcl.mass,7,7)
//					+" Speed: "+nf(myPtcl.maxspeed,7,7)
//					+" Force: "+nf(myPtcl.maxforce,7,7)
//					+" vel.x: "+nf(myPtcl.vel.x,7,7)+" vel.y: "+nf(myPtcl.vel.y,7,7)
//					);
			
			
		}
		
		//a grid just for adjustment
		public void drawGrid(){
			
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
		
		public void drawCursors(ArrayList <TuioCursor>tuioCursorList){
			
			for (int i=0; i<tuioCursorList.size(); i++) {
				TuioCursor tcur = (TuioCursor)tuioCursorList.get(i);
				p.stroke(100,255,255);
				p.noFill();
				p.ellipse( tcur.getScreenX(p.width), tcur.getScreenY(p.height),10,10);
			}
		}
		
		public void drawCursorCount(ArrayList <TuioCursor>tuioCursorList){
			
			p.noStroke();
			p.fill(255);
			p.text(tuioCursorList.size(), 50, 50);
			p.noFill();
		}



		public void saveFrame(float time) {
			// TODO Auto-generated method stub
			p.saveFrame("./bilder/MyImg"+time+".jpg");

		}

}
