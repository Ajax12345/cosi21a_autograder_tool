package main;

/**
 * Runs a simulation of the building and elevator system.
 * Known Bugs: None
 * 
 * @author Alicia Fang
 * aliciafang@brandeis.edu
 * September 7, 2025
 * COSI 21A PA0
 */
public class Simulation {

	public static void main(String[] args) {
		Building b1 = new Building(3);
        Person A = new Person("Alice", "A");
        Person B = new Person("Bob", "B");
		A.enterBuilding(b1, 2);
		B.enterBuilding(b1, 3);
        b1.startElevator();
		System.out.println();

        Person P1 = new Person("P", "1");
        Person P2 = new Person("P", "2");
        Person P3 = new Person("P", "3");
        Person P4 = new Person("P", "4");
		Person P5= new Person("P", "5");
		P1.enterBuilding(b1, 2);
		P2.enterBuilding(b1, 3);
		P3.enterBuilding(b1, 3);
		P4.enterBuilding(b1, 2);
		P5.enterBuilding(b1, 6);
		b1.startElevator();
	}

}
