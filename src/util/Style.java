package util;

import processing.core.PApplet;

public class Style {
	
	public static PApplet p;

	public static int col1;
	public static int col2; 
	public static int col3;
	
	
	public static int textColor;
	
	public static void setPApplet(PApplet _p){
		p = _p;
	 }
	
public static void create(){
		
//		colors
	col1 = p.color(0,100,0,100);
	col2 = p.color(0,100,0,23); 
	col3 = p.color(360,0,100,100);

	textColor = p.color(255,0,100,100);



	
	
}

}
