package main;
/**
* <This class runs a similator of. what a building with people would look like>
*
* @author Shemarh Stewart
* shemarh@brandeis.edu
* 9/9/2025
* COSI 21A PA0
*/
public class Simulation {
/**
 * This method similates elevator usage using methods from other classes and prints statements 
 * @param args
 */
	public static void main(String[] args) {
		Building b= new Building(6);
		Person a = new Person("John", "Doe");
		 Person B = new Person("Jane", "Doe");
		Person c = new Person("Jim", "Doe");
		 Person h = new Person("Jane", "Doe");
		a.enterBuilding(b,6);
		B.enterBuilding(b,1);
		c.enterBuilding(b,6);
		h.enterBuilding(b,3);
		
		
		b.startElevator();
		System.out.println(c.toString());
		
	}

}
