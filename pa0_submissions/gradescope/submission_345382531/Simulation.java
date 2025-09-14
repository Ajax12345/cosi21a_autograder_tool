package main;

/**
 * Simulation class for testing my elevator system.
 * Has main method, runs various scenarios to test
 * No Known Bugs
 * @author Alex Fernandez-Kim
 * alexanderfk@brandeis.edu
 * September 9, 2025
 * COSI 21A PA0
 */

public class Simulation {

	public static void main(String[] args) {
		System.out.println("ELEVATOR SIMULATION\n");

		System.out.println("Test 1: 2 people, A to floor 2, B to floor 3");
		testBasicScenario();

		System.out.println("\n" + "=".repeat(50) + "\n");

		System.out.println("Test 2: 4 people, capacity limit at 3");
		testCapacityScenario();

		System.out.println("\n" + "=".repeat(50) + "\n");

		System.out.println("Test 3: Invalid floor request");
		testInvalidFloor();

		System.out.println("\n" + "=".repeat(50) + "\n");

		System.out.println("Test 4: Multiple batches");
		testMultipleBatches();
	}

	private static void testBasicScenario() {
		Building building = new Building(3);

		Person personA = new Person("Captain", "Hook");
		Person personB = new Person("Peter", "Pan");

		System.out.println("Initial Locations:");
		System.out.println(personA);
		System.out.println(personB);

		boolean aRight = personA.enterBuilding(building, 2);
		boolean bRight = personB.enterBuilding(building, 3);

		System.out.println("\nAfter Entering Building:");
		System.out.println("Person A request successful: " + aRight);
		System.out.println("Person B request successful: " + bRight);
		System.out.println(personA);
		System.out.println(personB);

		System.out.println("\nElevator Operation:");
		building.startElevator();

		System.out.println("\nFinal Locations:");
		System.out.println(personA);
		System.out.println(personB);
	}

	private static void testCapacityScenario() {
		Building building = new Building(3);

		Person personA = new Person("Goldi", "Locks");
		Person personB = new Person("Mama", "Bear");
		Person personC = new Person("Papa", "Bear");
		Person personD = new Person("Baby", "Bear");

		personA.enterBuilding(building, 2);
		personB.enterBuilding(building, 3);
		personC.enterBuilding(building, 3);
		personD.enterBuilding(building, 2);

		System.out.println("All people entered building, starting elevator:");
		building.startElevator();

		System.out.println(personA);
		System.out.println(personB);
		System.out.println(personC);
		System.out.println(personD);
	}

	private static void testInvalidFloor() {
		Building building = new Building(3);

		Person person = new Person("Jesus", "Christ");

		System.out.println("Attempting to go to floor 5 (invalid):");
		boolean ifTrue = person.enterBuilding(building, 5);
		System.out.println("Successful: " + ifTrue);
		System.out.println(person);

		System.out.println("\nAttempting to go to floor 0 (invalid):");
		ifTrue = person.enterBuilding(building, 0);
		System.out.println("Successful: " + ifTrue);
		System.out.println(person);

		System.out.println("\nAttempting to go to floor 2 (valid):");
		ifTrue = person.EnterBuilding(building, 2);
		System.out.println("Successful: " + ifTrue);
		System.out.println(person);

		if (ifTrue) {
			System.out.println("\nStarting Elevator:");
			building.startElevator();
			System.out.println("Final Location: " + person);
		}
	}

	private static void testMultipleBatches() {
		Building building = new Building(5);

		Person p1 = new Person("Donald", "Trump");
		Person p2 = new Person("Joe", "Biden");
		Person p3 = new Person("Barack", "Obama");

		p1.enterBuilding(building, 1);
		p2.enterBuilding(building, 2);
		p3.enterBuilding(building, 3);

		System.out.println("First batch added, starting elevator:");
		building.startElevator();

		System.out.println("\n--- Adding a second batch ---");

		Person p4 = new Person("George", "W. Bush");
		Person p5 = new Person("Bill", "Clinton");

		p4.enterBuilding(building, 4);
		p5.enterBuilding(building, 5);

		System.out.println("Second batch added, starting elevator:");
		building.startElevator();

		System.out.println("\nAll final locations:");
		System.out.println(p1);
		System.out.println(p2);
		System.out.println(p3);
		System.out.println(p4);
		System.out.println(p5);
	}

}
