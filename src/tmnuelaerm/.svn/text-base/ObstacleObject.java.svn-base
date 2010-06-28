package tmnuelaerm;

import java.util.ArrayList;
/*
import TUIO.TuioClient;
import TUIO.TuioCursor;
//import TUIO.TuioProcessing;
*/
import particleSystem.Repeller;
import processing.core.*;


public class ObstacleObject {
	
	public String id;
	
	
	public int coursor01ID = 99;
	public PVector coursor01Pos = new PVector();
	public PVector newCoursor01Pos = new PVector();

	public int coursor02ID = 99;
	public PVector coursor02Pos = new PVector();
	public PVector newCoursor02Pos = new PVector();
	
	public PVector obstclSize;
	
	public PVector obstclPos;
	public float obstclXpos = 0;
	public float obstclYpos = 0;
	
	public PVector obstclTrans;
	
	public float obstclRotate = 0;
	
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
	public float radius = 30; 
	
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
		
		ObstclsRepellerList = new ArrayList<Repeller>();
		ObstclsRepellerList.add(new Repeller(pa, obstclPos));
//		repeller01 = new Repeller(pa, obstclTrans);
		ObstclsRepellerList.get(0).setG(pa.pow(10,3));
//		repeller01.setG(pa.pow(10,3));

		svg = pa.loadShape(obstclName);
		svg.disableStyle();
		pa.shapeMode(pa.CORNER);

		
		obstclSize = new PVector(svg.width, svg.height);

		
		doTheRepellers();
		
		for(int i = 0; i < ObstclsRepellerList.size(); i++ ){
			ObstclsRepellerList.get(i).setG(pa.pow(10,3));		
		}

		
		
		boundingBox();
		boundingBox.translate(obstclPos);
		boundingBox.rotate(obstclRotate);
	}
	
	public void draw(){
		
		pa.pushMatrix();
		
		
		if(coursor01ID < 99){
			
			pa.fill(255);
			
			if(coursor02ID <99){
				
				setScale();
			}
		}else{
			pa.fill(255);
		}
		

		pa.noStroke();
		//setRotation();


		pa.translate(obstclPos.x, obstclPos.y);

		
		setSize();
		//setRotation();

		pa.noStroke();
		pa.shape(svg, 0,0, obstclSize.x, obstclSize.y);
		pa.popMatrix();
		
		doTheRepellers();
		
	
		boundingBox();
		boundingBox.translate(obstclPos);
		//boundingBox.rotate(obstclRotate);

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
	
	public void setRotation(){
		
		if (coursor01Pos != null && coursor02Pos != null && newCoursor01Pos != null && newCoursor02Pos != null){

			PVector v01 = PVector.sub(coursor01Pos, coursor02Pos);
						
			float theta01 = PVector.angleBetween(v01,obstclPos);
			
			PVector v02 = PVector.sub(newCoursor01Pos, newCoursor02Pos);
			
			float theta02 = PVector.angleBetween(v02,obstclPos);
			
			obstclRotate += (theta01-theta02);
			
		}
		pa.rotate(obstclRotate);
	}
	
	public void setOffset(PVector nowPos){
		
		offSet = PVector.sub(nowPos,obstclPos);
	}
	
	public void setScale(){
		
		if (coursor01Pos != null && coursor02Pos != null && newCoursor01Pos != null && newCoursor02Pos != null){
			float s1 = PVector.dist(coursor01Pos, coursor02Pos);
			float s2 = PVector.dist(newCoursor01Pos, newCoursor02Pos);
			scale = s2 / s1;
			
		}
		
		if (scale > 1.5f){
			scale = 1.5f;
		}
		
		if (scale < 0.5f)
			scale = 0.5f;
	}
	
	public void setSize(){
		
		obstclSize = PVector.mult(obstclSize,scale);
		
		if (obstclSize.x > 700){
			
			obstclSize.y = 700 / svg.width * svg.height; 
			obstclSize.x = 700;
		}
		if (obstclSize.x < 100){
			
			obstclSize.y = 100 / svg.width * svg.height; 
			obstclSize.x = 100;
		}
		
		//grav *= obstclSize.x/100;
		radius = obstclSize.x/5;
	}
	
	public void move(PVector nowPos){

		obstclPos = PVector.sub(nowPos, offSet);
	}

	public void doTheRepellers(){
		
		ObstclsRepellerList = new ArrayList<Repeller>();
		
		PVector repellerPos01 = new PVector(obstclPos.x + radius, obstclPos.y + obstclSize.y/2);
		PVector repellerPos02 = new PVector(obstclPos.x + obstclSize.x/2, obstclPos.y + obstclSize.y/2);
		PVector repellerPos03 = new PVector(obstclPos.x - radius + obstclSize.x, obstclPos.y + obstclSize.y/2);
		ObstclsRepellerList.add(new Repeller(pa, repellerPos01.x, repellerPos01.y, grav, radius));
		ObstclsRepellerList.add(new Repeller(pa, repellerPos02.x, repellerPos02.y, grav, radius));
		ObstclsRepellerList.add(new Repeller(pa, repellerPos03.x, repellerPos03.y, grav, radius));

		
	}
	
	public void boundingBox(){
		
		bounds1 = new Point(0,0);
		bounds2 = new Point(0, obstclSize.y);
		bounds3 = new Point(obstclSize.x, 0);
		bounds4 = new Point(obstclSize.x, obstclSize.y);
		
		boundingBox = new BoundingBox(pa);
		
		boundingBox.addPoint(bounds1);
		boundingBox.addPoint(bounds2);
		boundingBox.addPoint(bounds3);
		boundingBox.addPoint(bounds4);
		
	}
}