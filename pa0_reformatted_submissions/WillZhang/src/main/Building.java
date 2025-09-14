/*
 * COSI 21A – PA0
 * Building
 *
 * Represents a building that contains a lobby (floor 0), a fixed number of upper floors,
 * and a single Elevator. 
 *
 * Weida Zhang
 * @version Fall 2025
 */

public class Building {
	private Floor[] floors;     
    private int numFloors;      
    private Elevator elevator;

	public Building(int numFloors) {
		if (numFloors < 1) throw new IllegalArgumentException("numFloors must be >= 1");
        this.numFloors = numFloors;
        this.floors = new Floor[numFloors + 1]; 
        floors[0] = new Floor(0, "Lobby");
        for (int f = 1; f <= numFloors; f++) {
            floors[f] = new Floor(f, "Floor " + f);
        }
        this.elevator = new Elevator(this);
	}
	
	public boolean enterElevatorRequest(Person person, int floor) {
		if (person == null) return false;
        if (floor < 1 || floor > numFloors) {
            person.markInLobbyUnserviceable(this);
            return false;
        }
        person.markWaiting(this, floor);
        elevator.createLobbyCall();
        elevator.createJob(person, floor);
        return true;
	}
	
	public void startElevator() {
		elevator.processAllJobs();
	}
	
	public void enterFloor(Person person, int floor) {
		if (floor < 1 || floor > numFloors) throw new IllegalArgumentException();
        floors[floor].enterFloor(person);
	}

	public int getNumFloors() { return numFloors; }
}
