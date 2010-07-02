package particleSystem;

import java.util.ArrayList;

import processing.core.PApplet;
import processing.core.PVector;
import tmnuelaerm.ObstacleObject;
import tmnuelaerm.TmnUELaerm;

public class ParticleSystem {
		PApplet p;
	  ArrayList <Particle> particles;    // An arraylist for all the particles
	  public PVector origin;        // An origin point for where particles are birthed
	  Path path;

	  public ParticleSystem(PApplet p_, int num, PVector v, ArrayList<Particle> particles_,Path path_) {
	    particles = particles_;              // Initialize the arraylist
	    p = p_;
	    path = path_;
	    origin = v.get();                        // Store the origin point
	    for (int i = 0; i < num; i++) {
	      particles.add(new Particle(p,origin,true,false));    // Add "num" amount of particles to the arraylist
	    }
	  }

	  

	  // A function to apply a force to all Particles
	  void applyForce(PVector f) {
	    for (int i = 0; i < particles.size(); i++) {
	      Particle ptcl = (Particle) particles.get(i);
	      ptcl.applyRepellForce(f);
	    }
	  }

	  // A function for particles to interact with all Repellers
	  public void applyRepellers(ArrayList<Repeller> repellers) {
	    // For every Particle
	    for (int i = 0; i < particles.size(); i++) {
	      Particle ptcl = (Particle) particles.get(i);
	      // For every Repeller
	      for (int j = 0; j < repellers.size(); j++) {
	        Repeller r = (Repeller) repellers.get(j);
	        // Calculate and apply a force from Repeller to Particle
	        
	        PVector repel = r.pushParticle(ptcl);        
	        ptcl.applyRepellForce(repel);
	      }
	    }
	  }

	  
	  // A function for particles to interact with all Repellers that are near to the repeller
	  public void myApplyRepellers(ArrayList<Repeller> repellers) {
	    // For every Particle
	    for (int i = 0; i < particles.size(); i++) {
	      Particle ptcl = (Particle) particles.get(i);
	      
	        float distToCenterPS = ptcl.loc.dist(origin);
	        float n = p.norm(distToCenterPS,0,p.width/2f);
	        ptcl.setMass(n);
	      // For every Repeller
	     

	      for (int j = 0; j < repellers.size(); j++) {
	        Repeller r = (Repeller) repellers.get(j);
	        // Calculate and apply a force from Repeller to Particle
	        
	        float d = ptcl.loc.dist(r.loc);


	        if(d < r.getRadius()+20 /*&& ptcl.affection == true*/){
	        	
	        	PVector repel = new PVector(0,0);
	        	 if(d < r.getRadius() /*&& ptcl.affection == true*/){ 
	        		 
	        		  repel = r.pushParticle(ptcl);
	        		 }
	        	
		    ptcl.applyRepellForce(repel);
	        ptcl.setMaxforce((d/100));
	        ptcl.setGravity((d/100)*-0.0001f);
	        ptcl.setMaxspeed((d/10));
	        ptcl.setMass(d/100);
	        if(j == 5){
	        	
	        	if(ptcl.pathNum==0 ||ptcl.pathNum==1 ||ptcl.pathNum==8){
	        ptcl.setColorCol2(200, 100, 100, 100);
	        ptcl.setMaxforce((20));
	        ptcl.setMaxspeed((10));
	        ptcl.setMass(1);
	        	}else{
	        		
	    	        ptcl.setColorCol2(100, 100, 100, 100);
	    	        ptcl.setMaxforce((0.5f));
	    	        ptcl.setMaxspeed((0.5f));
	    	        ptcl.setMass(0.1f);	
	        	}

	        
	        }else{
		        ptcl.setColorCol2(100, 100, 100, 100);

	        	
	        }
//	        ptcl.setMaxforce(r.getG()*n);
//	        }else if(d < r.getRadius()+5){	
//		        PVector repel = r.pushParticle(ptcl);        
//		        ptcl.applyRepellForce(repel);
	        }else{
//		        ptcl.setMass((n));
//		        ptcl.resetMass();
//		        ptcl.resetGravity();
//		        
//		        
//		        if(ptcl.maxforce>0.2f)ptcl.maxforce = ptcl.maxforce*0.5f;
//		        if(ptcl.maxspeed>1.5f)ptcl.maxspeed = ptcl.maxspeed*0.5f;
	        	
	        }
	      }
	    }
	  }
	  
	  // A function for particles to interact with all Repellers that are near to the repeller
	  public void myApplyObstcles(ArrayList<ObstacleObject> ObstclsList) {
	    // For every Particle
	    for (int i = 0; i < particles.size(); i++) {
	      Particle ptcl = (Particle) particles.get(i);
	      
	        float distToCenterPS = ptcl.loc.dist(origin);
	        float n = p.norm(distToCenterPS,0,p.width/2f);
	        ptcl.setMass(n);

	
//	        for every Obstacle
	     for(int j = 0; j < ObstclsList.size();j++ ){
	        
	    	 if(ObstclsList.get(j).ObstclsRepellerList != null){
	    	 ArrayList<Repeller> repellersList = ObstclsList.get(j).ObstclsRepellerList;
	    	 
		      // For every Repeller

	      for (int k = 0; k < repellersList.size(); k++) {
	        Repeller r = (Repeller) repellersList.get(k);
	        // Calculate and apply a force from Repeller to Particle
	        
	        float d = ptcl.loc.dist(r.loc);


	        
	        if(d < r.getRadius() /*&& ptcl.affection == true*/){
	        	
	       
	        PVector repel = r.pushParticle(ptcl);
	        	
		    ptcl.applyRepellForce(repel);
	        ptcl.setMaxforce((d/100));
	        ptcl.setGravity((d/100)*0.0001f);
	        ptcl.setMaxspeed((d/10));
	        ptcl.setMass(d/100);
//	        ptcl.setMaxforce(r.getG()*n);
//	        }else if(d < r.getRadius()+5){	
//		        PVector repel = r.pushParticle(ptcl);        
//		        ptcl.applyRepellForce(repel);
	        }else{
		        ptcl.setMass((n));
		        ptcl.resetMass();
		        ptcl.resetGravity();
		        
		        
		        if(ptcl.maxforce>0.2f)ptcl.maxforce = ptcl.maxforce*0.5f;
		        if(ptcl.maxspeed>1.5f)ptcl.maxspeed = ptcl.maxspeed*0.5f;
	        	
	        }
	      }
	     }
	     }
	    }
	  }
	  
	  
	  public void run() {
	    // Cycle through the ArrayList backwards b/c we are deleting
	    for (int i = particles.size()-1; i >= 0; i--) {
	      Particle ptcl = (Particle) particles.get(i);
	      ptcl.run();
	      if (ptcl.dead()) {
	        particles.remove(i);
	      }
	    }
	  }

	  public void addParticleEmitter(boolean pointOrigin) {
		  
		  Particle ptcl;
		  
	  if(pointOrigin){
		   ptcl = new Particle(p,origin,true,false);

		  }else {
		
			  PVector myOrigin = new PVector(p.random(p.width),p.random(p.height));

		  ptcl = new Particle(p,myOrigin,true,false);
		  ptcl.setMaxspeed(0.03f);
		  ptcl.setRadius(p.random(2));
		  
		  }
		  ptcl.setLifeTime(p.random(23,42));
		  ptcl.setMaxspeed(0.7f);
		  ptcl.setRadius(p.random(2));
		  particles.add(ptcl);
	  }
	  
	  
  public PVector setEmitterOrigin(PVector newOrigin){
		  
	  origin = newOrigin;
	  return origin;
	  }

	  void addParticle(Particle ptcl) {
	    particles.add(ptcl);
	  }

	  // A method to test if the particle system still has particles
	  boolean dead() {
	    if (particles.isEmpty()) {
	      return true;
	    } 
	    else {
	      return false;
	    }
	  }

}
