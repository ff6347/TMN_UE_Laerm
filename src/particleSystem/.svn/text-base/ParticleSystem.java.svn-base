package particleSystem;

import interaction.TNObstacleObject;

import java.util.ArrayList;
import java.util.List;
import processing.core.PApplet;
import processing.core.PVector;

/**
 * the whole <code>ParticleSystem</code>
 * here the collision and stuff get worked out
 * based on: <a href="http://www.shiffman.net/teaching/nature/" target="blanc">Daniel Shiffman's Nature of Code</a>
 * @author fabianthelbind
 * @see Particle Class Particle
 * @see Path Class Path
 * @see Repeller Class Repeller
 *
 */
public class ParticleSystem {
	
		/**
		 * the PApplet
		 */
		PApplet p;

		/**
		 * An <code>ArrayList</code> for all the <code>Particle</code>'s
		 */
		ArrayList <Particle> particles;
		
	/**
	 * An origin point for where particles are birthed when using the emitter
	 * @see #addParticleEmitter(boolean)
	 * @see #setEmitterOrigin(PVector)
	 */
	public PVector origin;

	  /**
	   * this is the masterconstructor
	 * @param p  the PApplet
	 * @param num int create at startup a number of particles
	 * @param v the origin of the emitter
	 * @param particles an Arraylist of Particles
	 */
	public ParticleSystem(PApplet p, int num, PVector v, ArrayList<Particle> particles) {
	    this.particles = particles;              // Initialize the arraylist
	    this.p = p;
	    this.origin = v.get();                        // Store the origin point
	    for (int i = 0; i < num; i++) {
	      particles.add(new Particle(p,origin,true,false));    // Add "num" amount of particles to the arraylist
	    }
	  }


	/**
	 * A function to apply a force to all Particles
	 * @param f PVector
	 * @see Particle#applyRepellForce(PVector)
	 */
	void applyForce(PVector f) {
	    for (int i = 0; i < particles.size(); i++) {
	      Particle ptcl = (Particle) particles.get(i);
	      ptcl.applyRepellForce(f);
	    }
	  }

	/**
	 * A function for particles to interact with all Repellers
	 * this function is not used in the main programm but still is usefull to have
	 * @param repellers ArrayList
	 * @deprecated its an old thing
	 */
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

	  
	  // 
	/**
	 * A function for particles to interact with all <code>Repeller</code>'s that are near to the repeller
	 * @param repellers ArrayList
	 * @see Particle Class Particle
	 * @see #calcPtclReaction(Repeller, float, Particle, boolean, PVector)
	 * @see #myApplyObstcles(List, boolean)
	 */
	public void myApplyRepellers(ArrayList<Repeller> repellers,boolean day) {
	    // For every Particle
	    for (int i = 0; i < particles.size(); i++) {
	      Particle ptcl = (Particle) particles.get(i);
	      
	        float distToCenterPS = ptcl.loc.dist(origin);
	        float n = PApplet.norm(distToCenterPS,0,p.width/2f);
	        ptcl.setMass(n);
	      // For every Repeller
	     
	      for (int j = 0; j < repellers.size(); j++) {
	    	  
	        Repeller r = repellers.get(j);
	        // Calculate the distance from a Repeller to the particle
	        float d = ptcl.loc.dist(r.loc);
	        	
	        	PVector repel = new PVector(0,0);
	        	calcPtclReaction(r, d, ptcl, day, repel);
	        	 
	      }
	    }
	  }
	  
	   
	/**
	 * A function for particles to interact with all Repellers that are near to the repeller
	 * @param ObstclsList ArrayList
	 * @param dAY 
	 * @see #myApplyRepellers(ArrayList)
	 * @see #calcPtclReaction(Repeller, float, Particle, boolean, PVector)
	 * @see #myApplyRepellers(ArrayList, boolean)
	 * @see old.ObstacleObject#ObstclsRepellerList
	 */
	public void myApplyObstcles(List<TNObstacleObject> ObstclsList, boolean day) {
	    // For every Particle
	    for (int i = 0; i < particles.size(); i++) {
	      Particle ptcl = (Particle) particles.get(i);
	      
//	        for every Obstacle
	     for(int j = 0; j < ObstclsList.size();j++ ){
	        
	    	 if(ObstclsList.get(j).ObstclsRepellerList != null){
	    	 ArrayList<Repeller> repellersList = ObstclsList.get(j).ObstclsRepellerList;
	    	 
	    	 
		      // For every Repeller
	      for (int k = 0; k < repellersList.size(); k++) {
	        Repeller r = (Repeller) repellersList.get(k);
	        // Calculate and apply a force from Repeller to Particle
	        float d = ptcl.loc.dist(r.loc);       
	       	PVector repel = new PVector(0,0);
	        
	       	calcPtclReaction(r, d, ptcl, day, repel);

	      }
	     }
	     }
	    }
	  }
	  
	/**
	 * here all the {@link Property} stuff gets worked out
	 * @param r the {@link Repeller}
	 * @param d the distance to the <code>epeller</code>
	 * @param ptcl the {@link Particle}
	 * @param day the day or nite time if <code>true</code> it is day
	 * @param repel a <code>PVector</code> for repelling
	 */
	private void calcPtclReaction(Repeller r, float d, Particle ptcl, boolean day, PVector repel){
		
		
		  if(d <= r.getRadius()){
 
//    		 this is in private space
    		 if((ptcl.pathNum == 0)|| (ptcl.pathNum == 1) ||(ptcl.pathNum == 2)){
	 
    			 if(day){
//		        		at daytime
        			reactOnPropValues(r, 0, 0, repel, ptcl); 	 	
    			 }else{
//    				 at nite
         			reactOnPropValues(r, 1, 0, repel, ptcl); 
 
    			 }
//    			 this is in public space
    		 }else if ((ptcl.pathNum == 3)|| (ptcl.pathNum == 4) ||(ptcl.pathNum == 5)){
    			 
//    			 at Daytime
    			 
    			 if(day){
          			reactOnPropValues(r, 0, 1, repel, ptcl); 

    			 }else{
//    				 at nite
           			reactOnPropValues(r, 1, 1, repel, ptcl); 


    			 }
//    			 this is in work space
    		 }else if((ptcl.pathNum == 6)|| (ptcl.pathNum == 7) ||(ptcl.pathNum == 8)){
//    			 at Daytime
    			 if(day){
            			reactOnPropValues(r, 0, 2, repel, ptcl); 
    				 
    			 }else{
//    				 at nite
            			reactOnPropValues(r, 1, 2, repel, ptcl); 

    			 }
	 
    		 }else{
    			 
				 	repel = r.pushParticle(ptcl);
	      		    ptcl.applyRepellForce(repel);
	      		    ptcl.setMaxspeed(0.3f);
	      		    ptcl.setMaxforce(2.3f);
	      		    ptcl.setMass(0.01f);
	      		    

    		 }
    		 
    	 
    	 
    	 }else{
//    		 if the distance his higher than the Repeller's radius
//    		do nothing 
    		 
//			 if(ptcl.hidden){	 
//				 	repel = r.pushParticle(ptcl);
//	      		    ptcl.applyRepellForce(repel);
//	      		    ptcl.maxspeed = 10;
// 			 }
    		 
    }
		
	}
	
	/**
	 * @param r the Repeller
	 * @param time day or nite represented by 0 and 1
	 * @param space private / public or /workspace represented by 0, 1 and 2
	 * @param repel the replling force
	 * @param ptcl the Particle
	 * @see #myApplyObstcles(List, boolean)
	 * @see #myApplyRepellers(ArrayList, boolean)
	 * @see Property#affectionProps
	 * 
	 */
	private void reactOnPropValues(Repeller r,int time, int space, PVector repel, Particle ptcl){

			 switch(r.property.valueByIndex(time,space)){
			 	case -2: 	
				 	repel = r.pushParticle(ptcl);
	      		    ptcl.applyRepellForce(repel);
	    	        ptcl.setColorCol2(80, 100, 100, 100); 			 				    	      
//	    	        ptcl.setMaxforce(ptcl.maxforce + r.property.valueByIndex(0, 0));
//	    	        ptcl.setMaxspeed(ptcl.maxspeed + r.property.valueByIndex(0, 0));
			 	break;
			 	case -1:
				 	repel = r.pushParticle(ptcl);
	      		    ptcl.applyRepellForce(repel);
	    	        ptcl.setColorCol2(40, 100, 100, 100); 			 				    	      
//	    	        ptcl.setMaxforce(ptcl.maxforce + r.property.valueByIndex(0, 0));
//	    	        ptcl.setMaxspeed(ptcl.maxspeed + r.property.valueByIndex(0, 0));
			 	break;
			 	case 0:
//			 		do nothing
	    	        ptcl.setColorCol2(120, 100, 100, 100); 	
			 	break;
			 	case 1:
//				 	repel = r.pushParticle(ptcl);
//	      		    ptcl.applyRepellForce(repel);
	    	        ptcl.setColorCol2(40, 100, 100, 100); 			 				    	      
//	    	        ptcl.setMaxforce(ptcl.maxforce + r.property.valueByIndex(0, 0));
//	    	        ptcl.setMaxspeed(ptcl.maxspeed + r.property.valueByIndex(0, 0));
			 	break;
			 	case 2:
//				 	repel = r.pushParticle(ptcl);
//	      		    ptcl.applyRepellForce(repel);
	    	        ptcl.setColorCol2(80, 100, 100, 100); 			 				    	      
//	    	        ptcl.setMaxforce(ptcl.maxforce + r.property.valueByIndex(0, 0));
//	    	        ptcl.setMaxspeed(ptcl.maxspeed + r.property.valueByIndex(0, 0));
			 	break;
			 }
		
	}
	  
	  /**
	   * this runs the ParticleSystem
	 * 
	 */
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

	  /**
	   * this is an basic Particle Emitter it is not worked out
	   * if u set the pointOrigin to true it will emit Particles from  the center the window
	   * 
	 * @param pointOrigin boolean
	 * @see Particle#Particle(PApplet, PVector, boolean, boolean)
	 */
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
	  
	  
  /**
   * sets a new origin for the Emitter
 * @param newOrigin
 * @return PVector
 */
public PVector setEmitterOrigin(PVector newOrigin){
		  
	  origin = newOrigin;
	  return origin;
	  }


	  /**
	   * adds a particle to the ArrayList particles
	 * @param ptcl
	 * @see #particles
	 */
	void addParticle(Particle ptcl) {
	    particles.add(ptcl);
	  }

	  /**
	   * A method to test if the particle system still has particles
	 * @return boolean
	 */
	boolean dead() {
	    if (particles.isEmpty()) {
	      return true;
	    } 
	    else {
	      return false;
	    }
	  }

}
