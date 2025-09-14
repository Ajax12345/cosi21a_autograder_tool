/**
 * Represents a Building with Floors and an Elevator.
 * Known Bugs: None
 * @author Ananya Kadwe
 * ananyakadwe@brandeis.edu
 * September 9, 2025
 * COSI 21A PA0
 */

package main;

public class Building {

	private Floor[] floors;
    private Elevator elevator;

	/**
     * Constructs a Building with the given number of floors, and the elevator starts at the Lobby (Floor 0)
     * @param numFloors The number of floors (not including the lobby).
     */
	public Building(int numFloors) {
		floors = new Floor[numFloors + 1];
        for (int i = 0; i <= numFloors; i++) {
            floors[i] = new Floor(i); 
        }
		elevator = new Elevator(this);
	}
	
	/**
     * Handles a request for a person to use the elevator to reach a specific floor.
     *
     * @param person The person making the request.
     * @param floor  The destination floor.
     * @return true if the elevator can reach the floor, false otherwise.
     */
	public boolean enterElevatorRequest(Person person, int floor) {
		if (floor < 0 || floor >= floors.length) {
            return false;
        }
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
     * Places a person on a specific floor.
     *
     * @param person The person to be placed on the floor.
     * @param floor  The floor number.
     */
	public void enterFloor(Person person, int floor) {
		if (floor >= 0 && floor < floors.length) {
            floors[floor].enterFloor(person);
        }
	}

    /**
     * Returns a string representation of the building.
     * @return A string representation of the building.
     */
	@Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Building:\n");
        for (Floor floor : floors) {
            sb.append(floor.toString()).append("\n");
        }
        return sb.toString();
    }
}
