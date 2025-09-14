package main;

/**
 * Building holds Floors and a single Elevator. Supports requests and starting
 * the elevator.
 * Known Bugs: None
 *
 * @author Yuehan Yang
 *         <Yuehanyang@brandeis.edu>
 *         <September 9, 2025>
 *         COSI 21A PA0
 */
public class Building {
	// Lobby is floor 0, actual accessible floors are 1..numFloors
	private final int numFloors;
	private final Floor[] floors; // floors[1..numFloors] are valid
	private final Elevator elevator;

	/**
	 * Construct a building with floors 1..numFloors and a lobby at 0.
	 * 
	 * @param numFloors number of floors excluding lobby
	 */
	public Building(int numFloors) {
		if (numFloors < 1) {
			throw new IllegalArgumentException("numFloors must be >= 1");
		}
		this.numFloors = numFloors;
		this.floors = new Floor[numFloors + 1]; // index 0 not used
		for (int i = 1; i <= numFloors; i++) {
			floors[i] = new Floor(i);
		}
		this.elevator = new Elevator(this);
	}

	/**
	 * Handle a person's request to enter this building and go to a floor.
	 * Validate the floor is reachable. Queue the job if valid.
	 * 
	 * @param person the person
	 * @param floor  destination floor number
	 * @return true if request is valid and queued, false otherwise
	 */
	public boolean enterElevatorRequest(Person person, int floor) {
		if (person == null)
			return false;
		if (floor < 1 || floor > numFloors) {
			// Cannot be serviced, person remains in lobby
			person.setInLobbyUnserviced();
			return false;
		}
		// Mark person as waiting and add to elevator job queue
		person.setWaiting();
		elevator.createJob(person, floor);
		return true;
	}

	/**
	 * Start the elevator to process all current jobs in FCFS with capacity batches.
	 */
	public void startElevator() {
		elevator.processAllJobs();
	}

	/**
	 * Save a reference of a person on a floor.
	 * 
	 * @param person person to place
	 * @param floor  floor number
	 */
	public void enterFloor(Person person, int floor) {
		if (floor < 1 || floor > numFloors)
			return;
		floors[floor].enterFloor(person);
	}

	public Elevator getElevator() {
		return elevator;
	}

	public int getNumFloors() {
		return numFloors;
	}

	@Override
	public String toString() {
		return "Building with " + numFloors + " floors";
	}
}