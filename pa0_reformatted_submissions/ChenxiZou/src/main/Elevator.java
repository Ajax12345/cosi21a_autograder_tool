package main;
/**
 * Represents an elevator system that manages transportation of people between floors.
 * The elevator processes jobs sequentially and can handle multiple passengers up to
 * its maximum capacity before returning to the lobby.
 * Known Bugs: None
 * 
 * @author Chenxi Zou
 * chenxizou@brandeis.edu
 * 9/8/2025
 * COSI 21A PA0
 */
public class Elevator {

	/**
	 * This specifies the number of people that can be brought to their floors by an
	 * Elevator
	 * instance at any given time.
	 * <p>
	 * DO NOT REMOVE THIS FIELD
	 * </p>
	 * <p>
	 * You may edit it. But keep it public.
	 * </p>
	 */
	public static int maxOccupants = 3;
	Job[] jobs;
	int currentFloor;
	Building building;
	int jobCount;
	/**
	 * Constructs a new Elevator for the specified building.
	 * Initializes the elevator at the lobby (floor 0) with an empty job queue.
	 * 
	 * @param building the building this elevator serves
	 */
	public Elevator(Building building) {
		this.jobs = new Job[10];
		this.currentFloor = 0;// start at lobby
		this.building = building;
		this.jobCount = 0;
	}
	/**
	 * Creates a new job for transporting a person to a specific floor.
	 * If the job queue is full, it will be resized to accommodate more jobs.
	 * 
	 * @param person the person to be transported
	 * @param floor the target floor number
	 */
	public void createJob(Person person, int floor) {
		if (jobCount >= jobs.length) {
			resize();
		}
		for (int i = 0; i < this.jobs.length; i++) {
			if (this.jobs[i] == null) {
				this.jobs[i] = new Job(person, floor);
				jobCount++;
				break;
			}
		}
	}
	/**
	 * Doubles the size of the job queue when it becomes full.
	 * Copies all existing jobs to the new larger array.
	 */
	public void resize() {
		Job[] newJobs = new Job[this.jobs.length * 2];
		for (int i = 0; i < this.jobs.length; i++) {
			newJobs[i] = this.jobs[i];
		}
		this.jobs = newJobs;
	}
	/**
	 * Processes all pending jobs in the elevator queue.
	 * After processing maxOccupants number of jobs, the elevator returns to the lobby.
	 * Jobs are processed sequentially and removed from the queue upon completion.
	 */
	public void processAllJobs() {
		int count = 0;
		for (int i = 0; i < this.jobs.length; i++) {
			if (this.jobs[i] != null) {
				processJob(this.jobs[i]);
				this.jobs[i] = null;
				count++;
				jobCount--;
				// every time when count reaches maxOccupants, process a dummy job to simulate
				// the elevator going back to lobby
				if (count % maxOccupants == 0) {
					moveToFloor(0);
				}
			}
		}
	}
	/**
	 * Processes a single job by moving the elevator to the target floor.
	 * If the job has no person (dummy job), moves to lobby only.
	 * Otherwise, transports the person to their destination floor.
	 * 
	 * @param job the job to be processed
	 */
	public void processJob(Job job) {
		if (job.person == null) {
			moveToFloor(0);
		} else {
			moveToFloor(job.targetFloor);
			exit(job.person, job.targetFloor);
		}
	}
	/**
	 * Moves the elevator from its current floor to the target floor.
	 * Prints the elevator's position at each floor during movement.
	 * 
	 * @param targetFloor the destination floor number
	 */
	public void moveToFloor(int targetFloor) {
		if (currentFloor == 0) {
			System.out.println(Constants.ELEVATOR_AT_LOBBY);
		} else {
			System.out.println(Constants.ELEVATOR_AT_FLOOR + currentFloor);
		}
		while (currentFloor != targetFloor) {
			if (currentFloor < targetFloor) {
				currentFloor++;
			} else {
				currentFloor--;
			}

			if (currentFloor == 0) {
				System.out.println(Constants.ELEVATOR_AT_LOBBY);
			} else {
				System.out.println(Constants.ELEVATOR_AT_FLOOR + currentFloor);
			}
		}
	}
	/**
	 * Allows a person to exit the elevator at the specified floor.
	 * Updates the person's location in the building.
	 * 
	 * @param person the person exiting the elevator
	 * @param floor the floor where the person exits
	 */
	public void exit(Person person, int floor) {
		building.enterFloor(person, floor);
	}

	@Override
	public String toString() {
		return "Elevator at floor " + currentFloor + " with " + jobCount + " jobs";
	}
}