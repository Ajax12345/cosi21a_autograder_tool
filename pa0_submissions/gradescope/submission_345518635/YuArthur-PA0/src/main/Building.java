package main;

/**
 * Represents a building with multiple floors and an elevator system.
 * Manages floor creation, elevator requests, and person movement between floors.
 * 
 * Known Bugs: None
 *
 * @author Arthur Yu
 * <ziyi.yu@brandeis.edu>
 * <September 9, 2025>
 * COSI 21A PA0
 */
public class Building {
	private Floor[] floors;
	private Elevator elevator;
	private int numFloors;
	
	/**
	 * Constructs a new Building with the specified number of floors.
	 * Creates floors 1 through numFloors plus a lobby (floor 0).
	 * 
	 * @param numFloors the number of floors in the building (excluding lobby)
	 */
	public Building(int numFloors) {
		this.numFloors = numFloors;
		// Create floor array, index 0 is lobby, 1 to numFloors are floors
		this.floors = new Floor[numFloors + 1];
		for (int i = 0; i <= numFloors; i++) {
			this.floors[i] = new Floor();
		}
		// Create elevator and pass Building reference
		this.elevator = new Elevator(this);
	}
	
	/**
	 * Handles a person's request to enter the building and be taken to a specific floor.
	 * Validates the floor number and creates an elevator job if valid.
	 * 
	 * @param person the person making the request
	 * @param floor the target floor number
	 * @return true if the floor is valid and request is accepted, false otherwise
	 */
	public boolean enterElevatorRequest(Person person, int floor) {
		// Validate if floor is valid
		if (floor < 1 || floor > numFloors) {
			return false; // Invalid floor
		}
		
		// Create Job and add to elevator
		Job job = new Job(person, floor, this);
		elevator.createJob(person, floor);
		return true;
	}
	
	/**
	 * Starts the elevator to process all current jobs.
	 */
	public void startElevator() {
		elevator.processAllJobs();
	}
	
	/**
	 * Moves a person to a specific floor in the building.
	 * Updates the person's location status and current floor.
	 * 
	 * @param person the person to move
	 * @param floor the target floor number
	 */
	public void enterFloor(Person person, int floor) {
		// Add Person to specified floor
		floors[floor].enterFloor(person);
		// Update Person's status
		person.setLocation("In Floor " + floor);
		person.setCurrentFloor(floor);
	}
	
	// Add getter methods for Elevator to use
	public Floor getFloor(int floorNumber) {
		return floors[floorNumber];
	}
	
	public int getNumFloors() {
		return numFloors;
	}
	
	@Override
	public String toString() {
		return "Building with " + numFloors + " floors";
	}
}
