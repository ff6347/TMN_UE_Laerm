package particleSystem;

import java.awt.Component;
import java.util.ArrayList;

import old.ObstacleObject;

import processing.core.PApplet;
import processing.core.PVector;
import processing.xml.XMLElement;
import util.XMLImporter;

/**
 * This Class is for helping with the <code>ParticleSystem</code><br>
 * they don't affect the Core Application but are very usefull<br>
 * 
 * @author fabianthelbind
 * @see Particle Class Particle
 * @see Path Class Path
 * @see Repeller Class Repeller
 * @author fabianthelbind
 * 
 */
@SuppressWarnings("deprecation")
public class PSUtil {

	/**
	 * the PApplet
	 * 
	 */
	private static PApplet p;
	public static int numOfPaths = 9;

	/**
	 * 
	 * this sets the PApplet for all the functions it has to be called first
	 * 
	 * @param p_
	 */
	public static void setPApplet(PApplet p_) {

		p = p_;

	}

	// Particle stuff

	/**
	 * This a number of points circling around the center. for a smother path
	 * give him more segments
	 * 
	 * @param segments
	 *            the number of segments in a path
	 * @param radius
	 *            the radius around the path
	 * @param size
	 *            the diameter of the circle
	 * @return Path
	 * @see Path Class Path
	 * @see Path#addPointPtcl(float, float)
	 */
	public static Path initCirclePath(int segments, int radius, int size) {

		Path path = new Path(p, radius);
		for (int i = 0; i <= 360; i += 360 / segments) {

			path.addPointPtcl((p.width / 2) + (PApplet.sin(PApplet.radians(i)))
					* size, (p.height / 2) + (PApplet.cos(PApplet.radians(i)))
					* size);
		}
		return path;
	}

	/**
	 * for easier initalizing of particles
	 * 
	 * @param numPtkls
	 *            the number of particles
	 * @param ptclRadius
	 *            the radius of the particle for collision with others
	 * @param ptclsList
	 *            the List of all Particles
	 * @param ptcl
	 * @return ptclsList
	 * @see #newPtkl(float, float, ArrayList, float)
	 * @see Particle Class Particle
	 */
	public static ArrayList<Particle> initParticles(int numPtkls,
			float ptclRadius, ArrayList<Particle> ptclsList) {

		ptclsList = new ArrayList<Particle>();
		Particle ptcl = null;
		for (int i = 0; i < numPtkls; i++) {
			newPtkl(i, ptcl, p.random(p.width), p.random(p.height), ptclsList,
					ptclRadius);

		}
		return ptclsList;
	}

	/**
	 * builds a new PArticle and adds him to the list of Particles
	 * 
	 * @param x
	 * @param y
	 * @param ptclsList
	 * @param ptclRadius
	 * @see Particle Class Particle
	 * 
	 */
	public static void newPtkl(int counter, Particle ptcl, float x, float y,
			ArrayList<Particle> ptclsList, float ptclRadius) {

		int pathNum = counter % (PSUtil.numOfPaths);

		ptcl = new Particle(p, new PVector(x, y), new PVector(x, y),
				ptclRadius, pathNum, true, false);

		ptclsList.add(ptcl);

	}

	/**
	 * not used right now
	 * 
	 * @param obstclObjList
	 * @param ptclsList
	 * @deprecated also old stuff
	 */
	public static void ptclsReactOnObject(
			ArrayList<ObstacleObject> obstclObjList,
			ArrayList<Particle> ptclsList) {
		// println(obstclObjList.get(0).ObstclsRepellerList.get(0).radius);
		float mySize = obstclObjList.get(0).ObstclsRepellerList.get(0)
				.getRadius();
		float myNewForce = PApplet.map(mySize, 10, 100, 0.5f, 13f);

		float myNewSpeed = PApplet.map(mySize, 10, 100, 0.5f, 13f);
		for (int i = 0; i < ptclsList.size(); i++) {

			ptclsList.get(i).setMaxforce(myNewForce);
			ptclsList.get(i).setMaxspeed(myNewSpeed);

		}
	}

	/**
	 * this is just for debugging
	 * 
	 * @param someRepellers
	 *            a list of some repellers
	 * @see Repeller Class Repeller
	 */
	public static void makeSomeRepellers(ArrayList<Repeller> someRepellers) {

		int numRepellers = 12;
		// make some repellers
		for (int i = 0; i <= 360; i += 360 / numRepellers) {

			Repeller rep = new Repeller(p, p.width / 2
					+ PApplet.sin(PApplet.radians(i)) * 180, p.height / 2
					+ PApplet.cos(PApplet.radians(i)) * 180);
			rep.setG(PApplet.pow(10, 3));
			someRepellers.add(rep);
		}

	}

	public static void makeSomeRepellersWithPropertys(
			ArrayList<Repeller> someRepellers, ArrayList<Property> propertysList) {

		int numRepellers = 7;
		int index = 0;
		// make some repellers
		for (int i = 0; i <= 360; i += 360 / numRepellers) {
			PVector loc = new PVector(p.width / 2
					+ PApplet.sin(PApplet.radians(i)) * 180, p.height / 2
					+ PApplet.cos(PApplet.radians(i)) * 180);
			Repeller rep = new Repeller(p, loc, ((10 * 10) * 10), 10,
					propertysList.get(index));// Repeller(p,p.width / 2 +
												// PApplet.sin(PApplet.radians(i))*180,p.height
												// / 2 +
												// PApplet.cos(PApplet.radians(i))*180);
			rep.setG(PApplet.pow(10, 3));
			someRepellers.add(rep);
			index++;
		}

	}

	/**
	 * this is for displaying the repellers for debugging
	 * 
	 * @param someRepellers
	 * @see Repeller Class Repeller
	 * @see #makeSomeRepellers(ArrayList)
	 * 
	 */
	public static void displaySomeRepellers(ArrayList<Repeller> someRepellers) {

		// Display all repellers
		for (int i = 0; i < someRepellers.size(); i++) {
			Repeller r = someRepellers.get(i);
			r.display();
			r.drag();
		}
	}

	public static ArrayList<Property> initPropertysList() {
		
		ArrayList<Property> propertysList = new ArrayList<Property>();
		
		XMLElement[] myObstaclObjcts = XMLImporter.getObsctlObjects();
		String theName = null;
		int[][] theValues = null;
		for (int i = 0; i < myObstaclObjcts.length; i++) {
			theName = myObstaclObjcts[i].getStringAttribute("name");
			
			theValues = XMLImporter.ObjectPropertys(i,
					myObstaclObjcts[i].getParent());
			propertysList.add(new Property(i, theName, theValues));
		}
		return propertysList;

	}

	public static void resetPath(ArrayList<Path> pathsList) {

		// TODO Auto-generated method stub
		for (int i = 0; i < pathsList.size(); i++) {

			pathsList.get(i).resetPointPtcls();
		}
	}

	public static void makeSpaces(ArrayList<Path> pathsList) {

		setNumOfPaths(9);
		int[] pathsSize = { 80, 100, 120, 230, 250, 270, 340, 360, 380 };
		int[] pathsRadius = { 20, 60, 30, 20, 60, 30, 20, 60, 30 };

		// int[] pathsSize = { 100, 250, 360};//, 230, 250, 270, 340, 360, 380
		// };
		// int[] pathsRadius = { 60, 70, 60};//, 20, 60, 30, 20, 60, 30 };

		for (int p = 0; p < numOfPaths; p++) {
			pathsList.add(initCirclePath(13, pathsRadius[p], pathsSize[p]));
		}

	}

	/**
	 * @return the numOfPaths
	 */
	public static synchronized int getNumOfPaths() {
		return numOfPaths;
	}

	/**
	 * @param numOfPaths
	 *            the numOfPaths to set
	 */
	public static synchronized void setNumOfPaths(int numOfPaths) {
		PSUtil.numOfPaths = numOfPaths;
	}

	public static void applyPaths(ArrayList<Particle> ptclsList,
			boolean switchPath, ArrayList<Path> pathsList) {

		for (int i = 0; i < ptclsList.size(); i++) {
			Particle ptcl = ptclsList.get(i);
			// if the particle is not part of a path
			if (ptcl.isHidden() != true) {
				// use the switchPath variable for random path selection
				if (switchPath) {
					ptcl.setPathNum(PApplet.floor(p
							.random(0, PSUtil.numOfPaths)));// myPathNum;
				}
				ptcl.applyForces(ptclsList, pathsList.get(ptcl.getPathNum()));
				switchPath = false;

			}
			// Call the generic run method (update, borders, display, etc.)
			ptcl.run();

		}
	}

}
