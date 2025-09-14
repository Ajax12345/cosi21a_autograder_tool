package main;

/**
* This class represents s building that holds an array of Floors and an instance of an Elevator.
* Known Bugs: None
* @author Tom Lin
* delin28@brandeis.edu
* September 9, 2025
* COSI 21A PA0
*/
public class Building {

	private int numFloors;
	private Floor[] floors;
	private Elevator elevator;

    /**
     * This constructor creates a new Building with the specified number of floors,
       and initializes the floors array and creates an Elevator instance for this building.
     * @param numFloors the number of floors in the building
	 * @precondition: numFloors must be positive
     */
	public Building(int numFloors) {
		this.numFloors = numFloors;
		this.floors = new Floor[numFloors + 1]; 
		this.elevator = new Elevator(this);
	}
	
	/**
     * This method requests an elevator for a person to a specified floor.
     * If the floor is invalid, the request is denied.
     * @param person the Person requesting the elevator
     * @param floor the target floor number
     * @return true if the request was accepted, false if the floor is invalid
     */
	public boolean enterElevatorRequest(Person person, int floor) {
		if (floor > numFloors || floor <= 0) {
			return false;
		}
		else {
			elevator.createJob(person, floor);
			person.changeWaitingStatus();
			return true;
		}
	}

	/**
     * This method starts the elevator to process all pending jobs.
     * The elevator will serve all requests in its queue.
     */   
	public void startElevator() {
		elevator.processAllJobs();
	}
	
	/**
     * This method places a person on a specified floor of the building.
     * It creates a new Floor instance if the floor does not exist.
     * @param person the Person to be placed on the floor
     * @param floor the floor number where the person should enter
     */
	public void enterFloor(Person person, int floor) {
		if (!(floor > numFloors || floor <= 0)) {
			if (floors[floor] == null) {
				Floor f = new Floor(floor);
				f.enterFloor(person);
				floors[floor] = f;
			}
			else {
				floors[floor].enterFloor(person);
			}
		}
	}

	/**
     * This method gets the total number of floors in the building.
     * @return the number of floors
     */
	protected int getNumFloors() {
		return numFloors;
	}

	/**
     * This method returns a string representation of the building.
     * @return a string indicating the number of floors
     */
	@Override
	public String toString() {
		return "This building has " + numFloors + " floors.";
	}
}
