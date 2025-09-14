package main;

/**
 * An Elevator contains a Job array and transports Persons to desired floors. Prints out what floor it is at
 * Known Bugs: None
 * 
 * @author Annika Bergstrom
 * annikabergstrom@brandeis.edu
 * September 09, 2025
 * COSI 21A PA0
 */
public class Elevator {

	/**
	 * This specifies the number of people that can be brought to their floors by an Elevator 
	 * instance at any given time. 
	 * <p>DO NOT REMOVE THIS FIELD</p>
	 * <p>You may edit it. But keep it public.</p>
	 */
	public static int maxOccupants = 3;
	private Building building;
	private Job[] jobs;
	private int jobs_index = 0;
	private int completedJobs = 0; // So don't proccess null/completed jobs
	private boolean doneAllTasks = true;
	private int peopleInElevator = 0;;
	private int factor_larger = 2;
	private int currentFloor = 0; // Elevator starts @ Lobby

	/**
	 * Elevator constructor
	 * @param building - (Building) What building this elevator is in
	 */
	public Elevator(Building building) {
		this.building = building;
		jobs = new Job[factor_larger * maxOccupants];
	}
	
	/**
	 * Creates a Job, saves to Job array. Seperates every maxOccupancy with a 
	 * job to return to the lobby to pick up more people
	 * @param person - (Person) Who to create the job for
	 * @param floor - (int) person's desired floor
	 */
	public void createJob(Person person, int floor) {
		Job job = new Job(person, floor);

		if (jobs_index + 1 >= jobs.length) { // If Array is full or could be full
			makeLongerJobList();
		}

		if ((doneAllTasks == true && currentFloor != 0) || peopleInElevator >= maxOccupants) { // Elevator needs to go back to lobby
			// Go to Lobby
			Job lobby = new Job(null, 0);
			jobs[jobs_index] = lobby;
			jobs_index++;

			// Add person's job
			jobs[jobs_index] = job;
		} else {
			// Add person's job
			jobs[jobs_index] = job;
		}
		jobs_index++;
		peopleInElevator++;
		doneAllTasks = false;
	}
	
	/**
	 * Goes through job array, calls processJob for each one
	 */
	public void processAllJobs() {
		for (int i = completedJobs; i < jobs_index; i++) {
			processJob(jobs[i]);
			jobs[i] = null;
			completedJobs++;
		}
		doneAllTasks = true;
	}
	
	/**
	 * Prints elevator's location as brings person to their desired floor.
	 * Calls exit(person, floor) to handle transfer logistics
	 * @param job (Job) what job to process
	 */
	public void processJob(Job job) {
		int floor = job.getFloor();

		if (currentFloor == 0) {
			System.out.println("Elevator at Lobby");
		} else {
			System.out.println("Elevator at floor " + currentFloor);
		}

		if (currentFloor < floor) { // Elevator goes up
			for (int i = currentFloor + 1; i <= floor; i++) {
				if (i == 0) {
					System.out.println("Elevator at Lobby");
				} else {
					System.out.println("Elevator at floor " + i);
				}
			}
		} else if (currentFloor > floor) { // Elevator goes down
			for (int i = currentFloor -1; i >= floor; i--) {
				if (i == 0) {
					System.out.println("Elevator at Lobby");
				} else {
					System.out.println("Elevator at floor " + i);
				}
			}
		}
		currentFloor = floor;		
		exit(job.getPerson(), job.getFloor());
	}

	/**
	 * Removes person from lobby, enters them onto desired floor
	 * @param person - (Person) to remove specific Person instance from Lobby
	 * @param floor - (int) to call Building's enterFloor() to put on desired Floor
	 */
	public void exit(Person person, int floor) {
		if (person == null) {
			return;
		}
		building.getFloor(0).removeFromFloor(person); // Remove from Lobby
		building.enterFloor(person, floor); // Enter desired Floor
		person.isOnDesiredFloor();
		peopleInElevator--;
	}

	/**
	 * Returns a String representing elevator
	 * @return String 
	 */
	public String toString() {
		return "Elevator in " + building.toString();
	}

	/**
	 * Helper function for createJob(person, floor) that transfers Jobs into a 
	 * larger array. Gets rid of completed (i.e. null) Jobs
	 */
	private void makeLongerJobList() {
		Job[] jobs_longer = new Job[jobs.length * 2];
		factor_larger *= 2;
		int jobs_longer_index = 0;

		for (int i = 0; i < jobs.length; i++) {
			if (jobs[i] != null) {
				jobs_longer[jobs_longer_index] = jobs[i];
				jobs_longer_index++;
			}
		}

		jobs_index = jobs_longer_index;
		jobs = jobs_longer;
		completedJobs = 0;
	}
}