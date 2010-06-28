package particleSystem;

import java.awt.Container;
import java.util.ArrayList;

import processing.core.PApplet;
import processing.core.PVector;
import tmnuelaerm.ObstacleObject;

public class PSUtil {




	public PSUtil() {
		// TODO Auto-generated constructor stub
	}
	
	
//		Particle stuff
	
//	This a number of points circling  around the center. for a smother path 
//	give him more segments
	public static Path initCirclePath(PApplet p,Path path,int segments){
		
		path = new Path(p,100);
		for(int i = 0; i <=360;i+=360/segments){
//				path.addPoint(width / 2 + sin(radians(i))*100,height / 2 + cos(radians(i))*100);
			  path.addPointPtcl(p.width / 2 + p.sin(p.radians(i))*150,p.height / 2 + p.cos(p.radians(i))*150);
		}
		return path;
	}
	
//	for easier initalizing of particles
	public static ArrayList<Particle> initParticles(PApplet p, int numPtkls,float ptclRadius,ArrayList <Particle> ptclsList){
		
		  ptclsList =  new ArrayList<Particle>();
		  for (int i = 0; i < numPtkls; i++) {
		    newPtkl(p, p.random(p.width),p.random(p.height),ptclsList, ptclRadius);
		    
//		    Set some random force and speed
//		    ptclsList.get(i).setMaxforce(random(-5,5));
//		    ptclsList.get(i).setMaxspeed(random(-2,2));

		  }
		return ptclsList;
	}
		  
//	Create new Particles	
	public static void newPtkl(PApplet p, float x, float y,ArrayList<Particle> ptclsList, float ptclRadius) {
				
//				  float maxforce = 0.3f;    // Maximum steering force
//				  float maxspeed =  0.3f;    // Maximum speed
//				  float myMaxspeed = Particle.maxspeed;
//				  float myMaxforce = Particle.maxforce;//+random(-1f,1f);
				Particle ptcl = new Particle(p,new PVector(x,y),new PVector(x,y), ptclRadius,true,false);
//				ptcl.setMaxforce(10f);
//				ptcl.setMaxforce(5f);
//				ptcl.setMaxspeed(2f);

				  ptclsList.add(ptcl);
//				or use:
//				  ptclsList.add(new Particle(this,new PVector(x,y),new PVector(x,y), Particle.radius));

			}
			
	
	public static void ptclsReactOnObject(PApplet p, ArrayList <ObstacleObject> obstclObjList, ArrayList <Particle>ptclsList){
		//println(obstclObjList.get(0).ObstclsRepellerList.get(0).radius);
		float mySize = obstclObjList.get(0).ObstclsRepellerList.get(0).radius;
		float myNewForce = p.map(mySize,10,100,0.5f,13f);
		
		float myNewSpeed =p. map(mySize,10,100,0.5f,13f);
		for(int i= 0;i < ptclsList.size();i++){
			
			ptclsList.get(i).setMaxforce(myNewForce);
			ptclsList.get(i).setMaxspeed(myNewSpeed);
			
			}
		}



	public static void makeSomeRepellers(PApplet p,ArrayList <Repeller>someRepellers){
		
		
		
		int numRepellers  = 12;
		//make some repellers
		for(int i = 0; i <=360;i+=360/numRepellers){
			
			Repeller rep = new Repeller(p,p.width / 2 + p.sin(p.radians(i))*100,p.height / 2 + p.cos(p.radians(i))*100);
			rep.setG(p.pow(10,3));
			someRepellers.add(rep);
		}
				
	}
	
	public static void displaySomeRepellers(ArrayList<Repeller>someRepellers){
		
		// Display all repellers
		for (int i = 0; i < someRepellers.size(); i++) {
			Repeller r = someRepellers.get(i); 
			r.display();
			r.drag();
		}
		
	}
}
