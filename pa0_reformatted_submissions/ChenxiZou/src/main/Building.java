package main;

/**
 * Represents a building with multiple floors and an elevator system.
 * The building manages elevator requests and coordinates the movement of people
 * between floors using a single elevator.
 * Known Bugs: None
 * 
 * @author Chenxi Zou
 *         chenxizou@brandeis.edu
 *         9/8/2025
 *         COSI 21A PA0
 */
public class Building {
	protected Floor[] floors;
	private Elevator elevator;

	/**
	 * Constructs a new Building with the specified number of floors.
	 * Creates an array of floors including a lobby (floor 0) and initializes
	 * an elevator for the building.
	 * 
	 * @param numFloors the number of floors above the lobby
	 */
	public Building(int numFloors) {
		this.floors = new Floor[numFloors + 1];// +1 for the lobby
		this.elevator = new Elevator(this);
		for (int i = 0; i <= numFloors; i++) {
			this.floors[i] = new Floor(i);
		}

	}

	/**
	 * Processes an elevator request for a person to go to a specific floor.
	 * Updates the person's location status and creates a job for the elevator.
	 * 
	 * @param person the person making the elevator request
	 * @param floor  the target floor number (must be valid)
	 * @return true if the request was successfully processed, false otherwise
	 */
	public boolean enterElevatorRequest(Person person, int floor) {
		if (floor > 0 && floor < floors.length) {
			person.location = Constants.PERSON_WAITING_TO_BE_SERVICED;
			this.elevator.createJob(person, floor);
			return true;
		}
		return false;
	}

	/**
	 * Starts the elevator to process all pending jobs.
	 * The elevator will handle all queued requests in sequence.
	 */
	public void startElevator() {
		this.elevator.processAllJobs();
	}

	/**
	 * Places a person on a specific floor and updates their location.
	 * 
	 * @param person the person to be placed on the floor
	 * @param floor  the floor number where the person will be placed
	 */
	public void enterFloor(Person person, int floor) {
		if (floor >= 0 && floor < floors.length) {
			person.location = Constants.PERSON_IN_FLOOR + floor;
			this.floors[floor].enterFloor(person);
		}
	}

	@Override
	public String toString() {
		return "Building with " + (floors.length - 1) + " floors";
	}
}
