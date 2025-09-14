/**
 * This class runs the main method.
 * Known Bugs: None.
 * 
 * Liam Rittenburg
 * liamrittenburg@brandeis.edu
 * 01/24/2025
 * COSI 21A PA0
 */
package main;

public class Simulation {

	public static void main(String[] args) {
		Building building = new Building(5);
		Person p1 = new Person("A", "First");
		Person p2 = new Person("B", "Second");
		Person p3 = new Person("C", "Third");
		Person p4 = new Person("D", "Fourth");
		Person p5 = new Person("E", "Fifth");
		Person p6 = new Person("F", "sixth");

		p1.enterBuilding(building, 2);
		p2.enterBuilding(building, 3);
		p3.enterBuilding(building, 3);
		p4.enterBuilding(building, 2);
		// p5.enterBuilding(building, 4);

		building.startElevator();

		p6.enterBuilding(building, 3);
		building.startElevator();
	}

}
