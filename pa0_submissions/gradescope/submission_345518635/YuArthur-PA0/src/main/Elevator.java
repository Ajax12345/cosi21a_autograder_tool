package main;

/**
 * Represents an elevator that processes jobs and moves people between floors.
 * Handles job creation, processing, and elevator movement with FIFO job processing.
 * 
 * Known Bugs: None
 *
 * @author Arthur Yu
 * <ziyi.yu@brandeis.edu>
 * <September 9, 2025>
 * COSI 21A PA0
 */
public class Elevator {

	/**
	 * This specifies the number of people that can be brought to their floors by an Elevator 
	 * instance at any given time. 
	 * <p>DO NOT REMOVE THIS FIELD</p>
	 * <p>You may edit it. But keep it public.</p>
	 */
	public static final int maxOccupants = 3;
	private Job[] jobs;
	private int jobCount;
	private Building building;
	private int currentFloor; // 0 = lobby
	private static final int MAX_JOBS = 100; 
	
	/**
	 * Constructs a new Elevator for the given building.
	 * Initializes the elevator in the lobby with no jobs.
	 * 
	 * @param building the building this elevator belongs to
	 */
	public Elevator(Building building) {
		this.building = building;
		this.jobs = new Job[MAX_JOBS];
		this.jobCount = 0;
		this.currentFloor = 0; // Elevator starts in lobby
	}
	
	/**
	 * Creates a new job for the elevator to process.
	 * 
	 * @param person the person requesting service
	 * @param floor the target floor number
	 */
	public void createJob(Person person, int floor) {
		if (jobCount < MAX_JOBS) {
			Job job = new Job(person, floor, building);
			jobs[jobCount] = job;
			jobCount++;
		}
	}
	
	/**
	 * Processes all current jobs in FIFO (First-In-First-Out) order.
	 * Each job is processed individually by moving to the target floor.
	 */
	public void processAllJobs() {
		// Process jobs one by one in FIFO order
		while (jobCount > 0) {
			Job job = jobs[0]; // Get first job (FIFO)
			processJob(job);
			removeJob(0); // Remove processed job
		}
	}
	
	private void removeJob(int index) {
		for (int i = index; i < jobCount - 1; i++) {
			jobs[i] = jobs[i + 1];
		}
		jobs[jobCount - 1] = null;
		jobCount--;
	}
	
	/**
	 * Processes a single job by moving the elevator to the target floor
	 * and dropping off the person.
	 * 
	 * @param job the job to process
	 */
	public void processJob(Job job) {
		Person person = job.getPerson();
		int targetFloor = job.getTargetFloor();
		
		// Move elevator to target floor
		moveToFloor(targetFloor);
		
		// Remove Person from elevator to target floor
		exit(person, targetFloor);
	}
	
	/**
	 * Drops off a person at the specified floor.
	 * 
	 * @param person the person to drop off
	 * @param floor the floor to drop off at
	 */
	public void exit(Person person, int floor) {
		// Remove Person from elevator to specified floor
		building.enterFloor(person, floor);
	}
	
	// Add method to move elevator
	private void moveToFloor(int targetFloor) {
		// Print location when starting to process job
		printCurrentLocation();
		
		// If already at target floor, no need to move
		if (currentFloor == targetFloor) {
			return;
		}
		
		// Move elevator to target floor
		if (currentFloor < targetFloor) {
			// Move up
			for (int floor = currentFloor + 1; floor <= targetFloor; floor++) {
				currentFloor = floor;
				printCurrentLocation();
			}
		} else {
			// Move down
			for (int floor = currentFloor - 1; floor >= targetFloor; floor--) {
				currentFloor = floor;
				printCurrentLocation();
			}
		}
	}
	
	// Print elevator's current location
	private void printCurrentLocation() {
		if (currentFloor == 0) {
			System.out.println("Elevator at Lobby");
		} else {
			System.out.println("Elevator at floor " + currentFloor);
		}
	}
	
	@Override
	public String toString() {
		return "Elevator at floor " + currentFloor + " with " + jobCount + " jobs";
	}
}