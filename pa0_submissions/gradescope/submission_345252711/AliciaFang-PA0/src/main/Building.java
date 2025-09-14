package main;

/**
 * Represents a building that contains multiple floors and an elevator.
 * A building manages elevator requests, places people on floors,
 * and coordinates the elevator’s job processing.
 * Known Bugs: None
 * 
 * author Alicia Fang
 * <aliciafang@brandeis.edu>
 * <September 7, 2025>
 * COSI 21A PA0
 */
public class Building {
	private final int numFloors;
	private final Floor[] floors;
	private final Elevator elevator;

	/**
     * Constructs a building with the given number of floors (excluding Lobby).
     * Initializes all floors (from 0 = Lobby to numFloors) and creates
     * a single elevator for the building.
     *
     * @param numFloors the number of floors in the building (excluding lobby)
     */
	public Building(int numFloors) {
		this.numFloors=numFloors;
		floors = new Floor[numFloors + 1];
		for (int i = 0; i <= numFloors; i++) {
			floors[i] = new Floor(i);
		}
		elevator = new Elevator(this);
	}

	/**
     * Request for a person to enter the elevator and go to a floor.
     * If the request is valid (floor exists and is greater than 0),
     * the job is added to the elevator. 
	 * Otherwise, the person is placed in the lobby (floor 0).
     *
     * @param person the person making the request
     * @param floor  the destination floor the person wants to go to
     * @return true if the request is valid, otherwise false
     */
	public boolean enterElevatorRequest(Person person, int floor) {
		boolean enter = (floor <= numFloors && floor > 0);
		if(enter){
			elevator.createJob(person, floor);
		}
		else{
			enterFloor(person, 0);
		}
		return enter;
	}
	
	/**
     * Starts the elevator and processes all pending jobs.
     */
	public void startElevator() {
		elevator.processAllJobs();
	}
	
	/**
     * Enter a person into the specified floor.
     *
     * @param person the person to enter the floor
     * @param floor  the floor index (0 = Lobby)
     */
	public void enterFloor(Person person, int floor) {
		floors[floor].enterFloor(person);
	}

	/**
     * Returns a string information of the building,
     * including the number of floors and the information of each floor.
     *
     * @return a string summary of the building and its floors
     */
	@Override
	public String toString() {
        String buildingInfo = "This building has " + numFloors+ " floors\n";
        for (int i = 0; i <= numFloors; i++){
			buildingInfo += floors[i].toString() + "\n";
		}
        return buildingInfo;
    }
}
