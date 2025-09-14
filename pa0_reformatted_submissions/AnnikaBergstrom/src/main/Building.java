package main;

/**
 * a Building contains an Elevator, an array of Floor objects. Floor 0 is a Lobby and the number of Floors starts after
 * Known Bugs: Because modifying contructors is not allowed, this program assumes only positive numFloors will be passed in constructor
 * 
 * @author Annika Bergstrom
 * annikabergstrom@brandeis.edu
 * September 09, 2025
 * COSI 21A PA0
 */
public class Building {
	private int numFloors; // Excluding Lobby
	private Elevator elevator;
	private Floor[] floors;

	/**
	 * Building constructor
	 * @param numFloors - (int) Building's number of floors
	 */
	public Building(int numFloors) {
		this.numFloors = numFloors;
		elevator = new Elevator(this);
		floors = new Floor[numFloors + 1]; // to include lobby

		for (int i = 0; i <= numFloors; i++) {
			floors[i] = new Floor(i);
		}
	}
	
	/**
	 * Whether a person can take an elevator to a desired floor
	 * @param person - (Person)
	 * @param floor - (int) person's desired floor
	 * @return boolean on if floor is in building
	 */
	public boolean enterElevatorRequest(Person person, int floor) {
		return (floor > 0 && floor <= numFloors);
	}
	
	/**
	 * Causes Building's elevator to proccess its jobs
	 */
	public void startElevator() {
		elevator.processAllJobs();
	}
	
	/**
	 * Calls Floor's enterFloor(person) to log a person in a floor
	 * @param person - (Person) to save on floor
	 * @param floor - (int) used as index of Building's Floor[] to get Floor object
	 */
	public void enterFloor(Person person, int floor) {
		floors[floor].enterFloor(person);
	}

	/**
	 * Returns a String representing the Building
	 * @return String 
	 */
	public String toString() {
		return "Building with " + numFloors + " floors";
	}

	/**
	 * Returns building's elevator. Used in Person class to create a job for the elevator. 
	 * Public to allow for test cases
	 * @return elevator  - (Elevator)
	 */
	public Elevator getElevator() {
		return elevator;
	}

	/**
	 * Returns Floor. Used in Elevator's exit(Person person, int floor)
	 * @param floorNum - (int) used as index of Building's Floor[] to get Floor object
	 * @return Floor object
	 */
	protected Floor getFloor(int floorNum) {
		return floors[floorNum];
	}
}
