package main;
/**
 * Main simulation class that demonstrates the elevator system functionality.
 * Creates a building with multiple floors, adds people, and processes elevator
 * requests to show the complete workflow of the elevator system.
 * Known Bugs: None
 * 
 * @author chenxi zou
 * chenxizou@brandeis.edu
 * 9/8/2025
 * COSI 21A PA0
 */
public class Simulation {
	/**
	 * Main method that runs the elevator simulation.
	 * Creates a building, adds people with floor requests, and processes
	 * all elevator jobs while displaying the system state at each step.
	 * 
	 * @param args command line arguments (not used)
	 */
	public static void main(String[] args) {
		// create a building with 5 floors (0 to 5)
		Building building = new Building(5);

		// create some people
		Person person1 = new Person("Alice", "Smith");
		Person person2 = new Person("Bob", "Johnson");
		Person person3 = new Person("Charlie", "Brown");

		System.out.println("starting the elevator simulation...");

		// show initial state
		System.out.println("initial state:");
		System.out.println(person1.getLocation());
		System.out.println(person2.getLocation());
		System.out.println(person3.getLocation());
		System.out.println(building.toString());
		System.out.println();

		// people enter the building and request the elevator to different floors
		System.out.println("persons entering the building and requesting the elevator:");
		person1.enterBuilding(building, 3);
		person2.enterBuilding(building, 2);
		person3.enterBuilding(building, 4);

		System.out.println("state after requests:");
		System.out.println(person1.getLocation());
		System.out.println(person2.getLocation());
		System.out.println(person3.getLocation());
		System.out.println(building.toString());
		System.out.println();

		// start the elevator to process all requests
		System.out.println("starting the elevator to process requests...");
		building.startElevator();

		System.out.println("\nfinal state after elevator processing:");
		System.out.println(person1.getLocation());
		System.out.println(person2.getLocation());
		System.out.println(person3.getLocation());
		System.out.println(building.toString());

		System.out.println("\nsimulation ended.");
	}

}
