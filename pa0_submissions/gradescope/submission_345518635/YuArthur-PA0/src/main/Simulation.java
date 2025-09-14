package main;

/**
 * Main simulation class that demonstrates the elevator system.
 * Creates a building with multiple floors and people, then runs the elevator simulation.
 * 
 * Known Bugs: None
 *
 * @author Arthur Yu
 * <ziyi.yu@brandeis.edu>
 * <September 9, 2025>
 * COSI 21A PA0
 */
public class Simulation {

	/**
	 * Main method that runs the elevator simulation.
	 * Creates a building with 5 floors, adds 5 people with different destinations,
	 * and runs the elevator to process all requests.
	 * 
	 * @param args command line arguments (not used)
	 */
	public static void main(String[] args) {
		// Create a building with 5 floors
		Building building = new Building(5);
		
		// Create 5 people
		Person person1 = new Person("Alice", "Smith");
		Person person2 = new Person("Bob", "Johnson");
		Person person3 = new Person("Charlie", "Brown");
		Person person4 = new Person("David", "Wilson");
		Person person5 = new Person("Eve", "Davis");
		
		// People enter building and request elevator service
		person1.enterBuilding(building, 3);
		person2.enterBuilding(building, 2);
		person3.enterBuilding(building, 4);
		person4.enterBuilding(building, 1);
		person5.enterBuilding(building, 5);
		
		// Start elevator to process tasks
		building.startElevator();
		
		// Print people list for each floor
		System.out.println("\n=== People on Each Floor ===");
		System.out.println("Lobby: " + building.getFloor(0).toString());
		for (int i = 1; i <= 5; i++) {
			System.out.println("Floor " + i + ": " + building.getFloor(i).toString());
		}
	}

}
