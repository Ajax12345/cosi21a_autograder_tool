package main;

public class Elevator {

	/**
	 * This specifies the number of people that can be brought to their floors by an Elevator 
	 * instance at any given time. 
	 * <p>DO NOT REMOVE THIS FIELD</p>
	 * <p>You may edit it. But keep it public.</p>
	 */
	/**
	 * Elevator class that manages transport of people between floors.
	 * Processes jobs in first-come-first-serve order with capacity caps.
	 * @author Alex Fernandez-Kim
	 * alexanderfk@brandeis.edu
	 * September 9, 2025
	 * COSI 21A PA0
	 */
	public static int maxOccupants = 3;

	private Job[] jobs;
	private int jobCount;
	private int currentFloor;
	private Building building;
	private static final int INITIAL_CAPACITY = 10;

	public Elevator(Building building) {
		this.building = building;
		this.currentFloor = 0;
		this.jobs = new Job[INITIAL_CAPACITY];
		this.jobCount = 0;
	}
	
	public void createJob(Person person, int floor) {
		if (jobCount >= jobs.length) {
			expandJobsArray();
		}

		jobs[jobCount] = new Job(person, floor);
		jobCount++;
		throw new UnsupportedOperationException();
	}
	
	public void processAllJobs() {
		while (jobCount > 0) {
			if (currentFloor != 0) {
				Job lobbyJob = new Job(null, 0);
				processJob(lobbyJob);
			}
			int jobsToProcess = Math.min(maxOccupants, jobCount);
			Job[] currentBatch = new Job[jobsToProcess];

			for (int i = 0; i < jobsToProcess; i++) {
				currentBatch[i] = jobs[i];
			}

			for (int i = 0; i < jobCount - jobsToProcess; i++) {
				jobs[i] = jobs[i + jobsToProcess];
			}
			jobCount -= jobsToProcess;

			for (int i = 0; i < jobsToProcess; i++) {
				processJob(currentBatch[i]);
			}
		}
		throw new UnsupportedOperationException();
	}
	
	public void processJob(Job job) {
		int destination = job.getDestinationFloor();
		printCurrentLocation();

		while (currentFloor != destination) {
			if (currentFloor < destination) {
				currentFloor++;
			} else {
				currentFloor--;
			}
			printCurrentLocation();
		}

		if (job.getPerson() != null) {
			exit(job.getPerson(), destination);
		}
		throw new UnsupportedOperationException();
	}
	
	public void exit(Person person, int floor) {
		building.enterFloor(person, floor);
		throw new UnsupportedOperationException();
	}

	private void printCurrentLocation() {
		if (currentFloor == 0) {
			System.out.println("Elevator at Lobby");
		} else {
			System.out.println("Elevator at Floor: " + currentFloor);
		}
	}
	private void expandJobsArray() {
		Job[] newJobs = new Job[jobs.length * 2];
		for (int i = 0; i < jobs.length; i++) {
			newJobs[i] = jobs[i];
		}
		jobs = newJobs;
	}

	public String toString() {
		return "Elevator at Floor: " + currentFloor + " with " + jobCount + " pending jobs";
	}
}