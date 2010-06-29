package tmnuelaerm;

import java.util.ArrayList;

import TUIO.TuioCursor;
/*
import TUIO.TuioClient;
import TUIO.TuioCursor;
//import TUIO.TuioProcessing;
*/
import particleSystem.Repeller;
import processing.core.*;


public class ObstacleObject {
	
	public String id;
	
	public boolean obstclActive = true;
	
	public int coursor01ID = 99;
	public PVector coursor01Pos = new PVector();
	public PVector newCoursor01Pos = new PVector();

	public int coursor02ID = 99;
	public PVector coursor02Pos = new PVector();
	public PVector newCoursor02Pos = new PVector();
	
	public float newDist;
	public float oldDist;
	public PVector obstclSize;
	
	public PVector obstclPos;
	public float obstclXpos = 0;
	public float obstclYpos = 0;
	
	public PVector obstclTrans;
	
	public float oldAngle;
	public float newAngle;
	public float angle;
	
	public BoundingBox boundingBox;
	
	public Point bounds1;
	public Point bounds2;
	public Point bounds3;
	public Point bounds4;
	
	public float boundsX1;
	public float boundsX2;
	public float boundsY1;
	public float boundsY2;
	
//	public Repeller repeller01;
	public ArrayList <Repeller> ObstclsRepellerList;
	
	public float grav = PApplet.pow(10,3);
	public float radius; 
	
	public float scale = 1;
	public PShape svg;
	public PApplet pa;
	public String obstclName;
	
	public boolean active = false;

	public PVector offSet;
	public float offsetX = 0;
	public float offsetY = 0;
	
	public ObstacleObject(PApplet _pa, int _id, PVector _trans) {
		
		obstclTrans = _trans;
		obstclPos = _trans;
		
		pa = _pa;
		id = PApplet.nf(_id,2);
		obstclName = "Object" + id + ".svg";
		


		svg = pa.loadShape(obstclName);
		svg.disableStyle();
		pa.shapeMode(PConstants.CENTER);

		
		obstclSize = new PVector(svg.width, svg.height);

		
		doTheRepellers();
		
		
		boundingBox();
		boundingBox.translate(obstclPos);
	}
	
	public void draw(){
		
		
		
		if(coursor01ID < 99){
			
			pa.fill(0);
			
			if(coursor02ID < 99){
				
			}
		}else{
			pa.fill(255);
		}
		

		pa.noStroke();

		pa.noStroke();
		pa.shape(svg, obstclPos.x, obstclPos.y, obstclSize.x, obstclSize.y);
		doTheRepellers();
		
	
		boundingBox();
		boundingBox.translate(obstclPos);

		boundingBox.display();

		pa.stroke(255);
		pa.noFill();
		for(int j = 0; j < ObstclsRepellerList.size(); j++){
			
			ObstclsRepellerList.get(j).display();
		}

		scale = 1;
		coursor01Pos = newCoursor01Pos;
		coursor02Pos = newCoursor02Pos;
	}
	

	public void setOffset(PVector nowPos){
		
		offSet = PVector.sub(nowPos,obstclPos);
	}
	

	public void move(PVector nowPos){

		obstclPos = PVector.sub(nowPos, offSet);
	}

	public void doTheRepellers(){
		
		radius = obstclSize.y/2;
		
		int howManyRep = PApplet.ceil((obstclSize.x / radius) +1);
		float howMuchSpace = obstclSize.x / howManyRep;
		
		ObstclsRepellerList = new ArrayList<Repeller>();
		
		for(int i = 1; i < howManyRep; i++){
			
			float repXpos = obstclPos.x - obstclSize.x/2 + i*howMuchSpace;
			float repYpos = obstclPos.y; 
			ObstclsRepellerList.add(new Repeller(pa, repXpos , repYpos, grav, radius));
			
		}
		
	}
	
	public void boundingBox(){
		
		bounds1 = new Point(-obstclSize.x/2, -obstclSize.y/2);
		bounds2 = new Point(obstclSize.x/2,-obstclSize.y/2);
		bounds3 = new Point(obstclSize.x/2, obstclSize.y/2);
		bounds4 = new Point(-obstclSize.x/2, obstclSize.y/2);
		
		boundingBox = new BoundingBox(pa);
		
		boundingBox.addCenter(obstclPos);
		boundingBox.addPoint(bounds1);
		boundingBox.addPoint(bounds2);
		boundingBox.addPoint(bounds3);
		boundingBox.addPoint(bounds4);
		
	}
}