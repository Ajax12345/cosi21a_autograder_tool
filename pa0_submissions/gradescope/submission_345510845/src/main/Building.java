package main;

public class Building {

	private Floor[] floors;
	private Elevator elevator;

	/**
	 * Constructs a building that has some number of floors. 
	 * @param numFloors - the number od floors in the building not inlcuding the lobby floor.
	 */
	public Building(int numFloors) {
		floors = new Floor[numFloors +1];  // index for lobby
		for(int i=0; i <= numFloors; i++){
			floors[i]= new Floor();
		}
		elevator= new Elevator(null);		// elevator starts in lobby

	}
	
	/**
	 * Handles the request made by a person to enter this building and be taken to some floor.
	 * @param person - person making request
	 * @param floor - floor person has requested
	 * @return true if the elevator can reach the persons desired floor.
	 */
	public boolean enterElevatorRequest(Person person, int floor) {
		if (floor < 1 || floor >= floors.length){		//for invalid floors
			return false;
		}
		elevator.createJob(person, floor);
		return true;
	}
	
	/**
	 * Starts the elevator and making it process all current jobs.
	 */
	public void startElevator() {
		elevator.processAllJobs();
	}
	
	/**
	 * Saves a reference of a person in the floor with provided floor number.
	 * 
	 * @param person - the person to add
	 * @param floor - the floor number
	 */
	public void enterFloor(Person person, int floor) {
		if (floor >= 0 && floor < floors.length) {			// saves person on correct number of their floor but not in lobby or invalid number
			floors[floor].enterFloor(person);
		}
	}
	/**
	 * @return a string that describes the building
	 */
	public String toString() {
		return "Building has " + (floors.length - 1) + " floors.";
	}
}
