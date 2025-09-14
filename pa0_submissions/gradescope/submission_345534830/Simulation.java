package main;

public class Simulation {

	public static void main(String[] args) {
		// Example 1
		Building b1 = new Building(3);
		Person a = new Person("A", "Alpha");
		Person c = new Person("B", "Beta");
		a.enterBuilding(b1, 2);
		c.enterBuilding(b1, 3);
		b1.startElevator();

		// Example 2: capacity test with 4 people, capacity = 3
		System.out.println();
		Elevator.maxOccupants = 3;
		Building b2 = new Building(3);
		Person pA = new Person("A", "X");
		Person pB = new Person("B", "Y");
		Person pC = new Person("C", "Z");
		Person pD = new Person("D", "W");
		pA.enterBuilding(b2, 2);
		pB.enterBuilding(b2, 3);
		pC.enterBuilding(b2, 3);
		pD.enterBuilding(b2, 2);
		b2.startElevator();
	}
}