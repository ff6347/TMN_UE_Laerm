package util;

import processing.core.PApplet;
import processing.xml.XMLElement;


/**
 * this is a class for importing .xml
 * 	 the XMLImporter Class in a nutshell	 
 * 	 u need to call first the function 
 * 	 XMLImporter.setPAppelt(PApplet p))
 * 	 for initilizing the class
 * 	 then use the function
 * 	 getRoot() returns the whole .xml file affectionPropertys.xml
 * 	 as a processing.XMLElement
 * 	 use this to see what happens
 * 	 PApplet.println(XMLImporter.getRoot());
 * 	 
 * 	 getObsctlObjects() returns the all ELements in the root node
 * 	 of the .xml file affectionPropertys.xml
 * 	 as a processing.XMLElement [] 
 * 	 use this to see what happens
 * 	 PApplet.println(XMLImporter.getObsctlObjects());
 * 	 
 * 	 getObsctlObjectByID(int id) returns a specific XMLELement in the root node
 * 	 of the .xml file affectionPropertys.xml
 * 	 by its int id
 * 	 as a processing.XMLElement
 * 	 if the element doesnt exist it returns null
 * 	 use this to see what happens
 * 	 PApplet.println(XMLImporter.getObsctlObjectByID(2));
 * 	  
 * 	 getObsctlObjectPropertys(int id String time, String space)
 * 	 returns a specific XMLELements value in the root/obstacleObject/ node
 * 	 of the .xml file affectionPropertys.xml
 * 	 by its int id
 * 	 then it selects the
 * 	 root/obstacleObject/day
 * 	 or
 * 	 root/obstacleObject/nite
 * 	 element
 * 	 then it selects within this element the space element by its name
 * 	 (public or private or work)
 * 	 then it returns the floatAttribute value
 * 	 as a float
 * 	 if the element doesnt exist it returns 0.0f 
 * 	 use this to see what happens
 * 	 PApplet.println(XMLImporter.getObsctlObjectPropertys(0, "day", "private"));
 *
 * @author fabiantheblind
 * 
 * 
 */
public class XMLImporter {
	
	private static PApplet p;
	public static XMLElement root ;
	
	
	/**
	 * this is for setting the PApplet for the whole class
	 * must be called in the setup()
	 * @param p_
	 */
	public static void setPAppelt(PApplet p_){
		
		p = p_;
	}
	
	/**
	 *  the XMLImporter Class in a nutshell all methods of this class in metood with some println() 
	 */
	public static void XML_IMPORTER_Class_inANutshell(){
 
//		 u need to call first the function 
//		 XMLImporter.setPAppelt(PApplet p))
//		 for initilizing the class
//		 then use the function
//		 getRoot() returns the whole .xml file affectionPropertys.xml
//		 as a processing.XMLElement
//		 use this to see what happens
		 PApplet.println(XMLImporter.getRoot());
		 
//		 getObsctlObjects() returns the all ELements in the root node
//		 of the .xml file affectionPropertys.xml
//		 as a processing.XMLElement [] 
//		 use this to see what happens
		 PApplet.println(XMLImporter.getObsctlObjects());
		 
//		 getObsctlObjectByID(int id) returns a specific XMLELement in the root node
//		 of the .xml file affectionPropertys.xml
//		 by its int id
//		 as a processing.XMLElement
//		 if the element doesnt exist it returns null
//		 use this to see what happens
		 PApplet.println(XMLImporter.getObsctlObjectByID(2));
		  
//		 getObsctlObjectPropertys(int id String time, String space)
//		 returns a specific XMLELements value in the root/obstacleObject/ node
//		 of the .xml file affectionPropertys.xml
//		 by its int id
//		 then it selects the
//		 root/obstacleObject/day
//		 or
//		 root/obstacleObject/nite
//		 element
//		 then it selects within this element the space element by its name
//		 (public or private or work)
//		 then it returns the floatAttribute value
//		 as a float
//		 if the element doesnt exist it returns 0.0f 
//		 use this to see what happens
		 PApplet.println(XMLImporter.getObsctlObjectPropertys(0, "day", "private"));
		
	}
	
	
//	/**
//	 * this is for fetching the root
//	 */
//	public static void initXML(){
//		root = new XMLElement(p,"./data/affectionPropertys.xml");
//	}
	
	/**
	 * this is for fetching the root of the .xml file
	 * @return XMLElement
	 */
	public static XMLElement getRoot(){
		root = new XMLElement(p,"./data/affectionPropertys.xml");
		return root;
	}
	
	
	/**
	 * this is for fetching all obstcl objects into an array of XMLElements
	 * @return XMLElement []
	 */
	public static XMLElement [] getObsctlObjects(){
		root = new XMLElement(p,"./data/affectionPropertys.xml");
		XMLElement obstcls [] = root.getChildren("obstclObject");
		return obstcls;
	}

	/**
	 * this is for fetching a Obstcl object by ID. If you want a specific object
	 * @param id int the index of the object/element in the .xml file
	 * @return XMLElement
	 */
	public static XMLElement getObsctlObjectByID(int id){
		root = new XMLElement(p,"./data/affectionPropertys.xml");
		XMLElement obstcls [] = root.getChildren("obstclObject");
		if (id <obstcls.length){
			return obstcls[id];
			}else {
			return null;
		}
	}
	

	/**
	 * this is for fetching a specific property
	 * @param id int the index of the object/element in the .xml file
	 * @param time  String name of a XMLElement the method accepts " day" or "nite"
	 * @param space  String name of a XMLElement the method accepts "work" "private" "public"
	 * @return float
	 */
	public static float getObsctlObjectPropertys(int id, String time, String space){
		root = new XMLElement(p,"./data/affectionPropertys.xml");
		XMLElement obstcls [] = root.getChildren("obstclObject");
		
//		this is for checking if there is an obstvl object in the xml
		if (id <obstcls.length){
			
//			this is for fetching the day time propertys
				if(time.equals("day")==true){

					
						  if(space.equals("private")==true){

						return obstcls[id].getChild("day/private").getFloatAttribute("value"); 
					}else if(space.equals("public")==true){
						return obstcls[id].getChild("day/public").getFloatAttribute("value");
					}else if(space.equals("work")==true){
						return obstcls[id].getChild("day/work").getFloatAttribute("value");
					}else{
						return 0.0f;
					}
					
//					this is for fetching the nite time propertys
				}else if(time.equals("nite")==true){
					
					if(space.equals("private")==true){
						return obstcls[id].getChild("nite/private").getFloatAttribute("value"); 
					}else if(space.equals("public")==true){
						return obstcls[id].getChild("nite/public").getFloatAttribute("value");
					}else if(space.equals("work")==true){
						return obstcls[id].getChild("nite/work").getFloatAttribute("value");
					}else{
						
//						if the element isn't there or the string doesent match
//						so it is not work, private or public it returns a null value
						return 0.0f;
					}
				}else{
//					if the object is neither day nor nite reutrn a null float value
					return 0.0f;
				}
		
			}else {
//				if there is no object like this (with the id)  in the xml return a null value
			return 0.0f;
		}
	}


}
