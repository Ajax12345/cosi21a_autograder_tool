package main;

/**
* This class represents an Elevator that contains an array of Jobs where each Job holds a Person and the number of the floor which they desire to go to. 
* Known Bugs: None
* @author Tom Lin
* delin28@brandeis.edu
* September 9, 2025
* COSI 21A PA0
*/
public class Elevator {
	
	private Building building;
	private Job[] jobs;
	private Person[] personsInElevator;
	private int floorAt = 0;
	
	/**
     * This constructor creates an Elevator for the specified building.
     * IT initializes the job queue and persons in elevator arrays.
     * @param building the Building that this elevator services
     */
	public Elevator(Building building) {
		this.building = building;
		this.jobs = new Job[100];
		this.personsInElevator = new Person[maxOccupants];
	}

	/**
	* This specifies the number of people that can be brought to their floors by an Elevator 
	* instance at any given time. 
	* <p>DO NOT REMOVE THIS FIELD</p>
	* <p>You may edit it. But keep it public.</p>
	*/
	public static int maxOccupants = 3;
	
	/**
     * This method creates a new job request for the elevator to transport a person to a specific floor,
     * and ensures capacity of the job queue and adds the job to the first available slot.
     * @param person the Person requesting elevator service
     * @param floor the destination floor number
     */
	public void createJob(Person person, int floor) {
		Job job = new Job(person, floor);
		ensureCapacity();
		for (int i = 0; i < jobs.length; i++) {
			if (jobs[i] == null) {
				jobs[i] = job;
				if (personsInElevator[i] == null) {
					personsInElevator[i] = person;
				}
				break;
			}
		}
	}
	
	/**
     * This method processes all jobs in the elevator's queue.
     * It first returns to lobby if not already there, then processes up to maxOccupants jobs,
     * and recursively processes remaining jobs until all are completed.
     */
	public void processAllJobs() {
		if (floorAt > 0) {
			Job returnToLobby = new Job(null, 0);
			processJob(returnToLobby);
		}
		for (int i = 0; i < maxOccupants; i++) {
			if (jobs[i] == null) {
				break;
			}
			else {
				processJob(jobs[i]);
				jobs[i] = null;
			}
		}
		proceedQueue();
		if (jobs[0] != null) {
			processAllJobs();
		}
	}
	
	/**
     * This methodd processes a single job by moving the elevator to the requested floor.
     * It prints elevator status at each floor change and exits the person at their destination.
     * @param job the Job to be processed
     */
	public void processJob(Job job) {
		printElevator();
		if (job.getFloor() > floorAt) {
			while (job.getFloor() > floorAt) {
				floorAt++;
				printElevator();
			}
		}
		else if (job.getFloor() < floorAt) {
			while (job.getFloor() < floorAt) {
				floorAt--;
				printElevator();
			}
		}
		if (job.getPerson() != null) {
			exit(job.getPerson(), job.getFloor());
		}
	}
    /**
     * This method exits a person from the elevator at the specified floor.
     * It updates the person's status, places them on the floor, and removes them from the elevator.
     * @param person the Person exiting the elevator
     * @param floor the floor number where the person is exiting
     */	
	public void exit(Person person, int floor) {
		person.changeWaitingStatus();
		person.enterFloor(floor);
		building.enterFloor(person, floor);
		for (int i = 0; i < maxOccupants; i++) {
			if (person == personsInElevator[i]) {
				personsInElevator[i] = null;
				break;
			}
		}
	}
	/**
     * This helper method ensures the job queue and persons arrays have sufficient capacity,
     * and expands arrays by 100 elements if they are full.
     */

	private void ensureCapacity() {
		if (jobs[jobs.length-1] != null) {
			Job[] temp = new Job[jobs.length + 100];
			for (int i = 0; i < jobs.length; i++) {
				temp[i] = jobs[i];
			}
			jobs = temp;
		}
		if (personsInElevator[personsInElevator.length-1] != null) {
			Person[] temp = new Person[personsInElevator.length + 100];
			for (int i = 0; i < personsInElevator.length; i++) {
				temp[i] = personsInElevator[i];
			}
			personsInElevator = temp;
		}
	}

    /**
     * This helper method prints the current elevator status (which floor it's on).
     * Uses Constants for standardized output messages.
     */
	private void printElevator() {
		if (floorAt == 0) {
			System.out.println(Constants.ELEVATOR_AT_LOBBY);
		}
		else {
			System.out.println(Constants.ELEVATOR_AT_FLOOR + floorAt);
		}
	}
	
	/**
     * This helper method advances the job queue after processing a batch of jobs,
     * and moves remaining jobs to the front of the queue and updates persons in elevator.
     */
	private void proceedQueue() {
		for (int i = 0; i < jobs.length - maxOccupants; i++) {
			if (jobs[i + maxOccupants] == null) {
				break;
			}
			else {
				jobs[i] = jobs[i + maxOccupants];
				jobs[i + maxOccupants] = null;
				if (i < maxOccupants && personsInElevator[i] == null) {
					personsInElevator[i] = jobs[i].getPerson();
				}
			}
		}
	}
	
	/**
     * This method returns a string representation of the elevator's current location.
     * @return a string indicating which floor the elevator is on
     */
	@Override
	public String toString() {
		if (floorAt == 0) {
			return "This elevator is at Lobby.";
		}
		else {
			return "This elevator is at Floor" + floorAt + ".";
		}
	}
}