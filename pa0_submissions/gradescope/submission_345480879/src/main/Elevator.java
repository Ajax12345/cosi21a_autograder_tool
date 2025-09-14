package main;

/**
 * Represents an elevator for the Building class to use taking people up and down the buildings floors
 * with an array of floors being passed to it.
 * Known Bugs: None
 * @author Maxwell Weisz
 * maxwellweisz@brandeis.edu
 * Sept 9, 2025
 * COSI 21A PA0
 */
public class Elevator {
	public static int maxOccupants = 3;
	Floor[] buildingFloors;
	Job[] jobs;
	Person[] curOccupants;
	int numOccup;
	public int curFloor;
	int jobsLeft;

	/**
	 * Constructs an Elevator instance starting on the "lobby" floor, with an occupants array the length
	 * of maxOccupants and a jobs array the length of maxOccupants for each occupants request. Also sets
	 * the buildingFloors array to the floors array getting passed from the Building class.
	 * @param floors array of Floor objects from Building class
	 */
	public Elevator(Floor[] floors) {
		curOccupants = new Person[maxOccupants];
		curFloor = 0;
		numOccup = 0;
		jobsLeft = 0;
		jobs = new Job[maxOccupants];
		buildingFloors = floors;
	}
	
	/**
	 * Creates a Job instance given a Person instance and floor number as parameters. Then adds the new 
	 * Job instance to the jobs array to get processed next.
	 * @param person a Person instance requesting a ride up
	 * @param floor the floor number being requested
	 */
	public void createJob(Person person, int floor) {
		Job job = new Job(person, floor);
		ensureCapacity();
		if(person != null) {	
			curOccupants[numOccup] = person;
			jobs[jobsLeft] = job;
			jobsLeft++;
			numOccup++;
		}
	}
	
	/**
	 * Processes jobs one by one using the processJob method, and removes them from the array after
	 * being processed. Then returns the elevator to the lobby for the next group of people to have their
	 * requests be processed.
	 */
	public void processAllJobs() {
		int j = 0;
		Job returnToLobby = new Job(null, 0);
		while(jobsLeft > 0) {
			if(curFloor > 0) { //returns the elevator to the lobby if not there already before starting jobs
				processJob(returnToLobby);
			}
			for(int i = 0; i < maxOccupants; i++) {//Processes jobs of all current occupants of elevator 
				if(jobsLeft > 0) {
					processJob(jobs[j]);
					jobs[j] = null;
					j++;
				}
			}
			if(jobsLeft > 0) { //If more jobs to process after taking one group to their floors return to lobby first
				processJob(returnToLobby);
			}
		}
	}
	
	/**
	 * Method to process a single job after being passed a job instance. Checks if the requested floor is
	 * lower or higher, then goes up or down printing out the floors as it goes. Once it reaches the floor
	 * requested it calls the exit method on the person to drop them off on the floor.
	 * @param job the job representing the person and floor requested
	 */
	public void processJob(Job job) {
		if(job.noPerson) {//If the job has null for the person then returns to lobby
			if(curFloor > 0){
				while(curFloor > 0) {
					System.out.println(Constants.ELEVATOR_AT_FLOOR + curFloor);
					curFloor--;
				}
				System.out.println(Constants.ELEVATOR_AT_LOBBY);
			}
		}
		else if(curFloor < job.floor) {//If below the requested floor, goes up one floor at a time to reach requested floor
			for(int i = curFloor; i <= job.floor; i++) {
				curFloor = i;
				job.person.curFloor = i; //Makes both the person's curFloor and elevator's curFloor match
				if(i == 0) {
					System.out.println(Constants.ELEVATOR_AT_LOBBY);
				}
				else {
					System.out.println(Constants.ELEVATOR_AT_FLOOR + curFloor);
				}
				if(curFloor == job.floor && !job.noPerson) {
					exit(job.person, job.floor);
					jobsLeft--;
				}
			}
		}
		else if(curFloor == job.floor && curFloor != 0) {//If at floor already lets them out there
			job.person.curFloor = curFloor;
			System.out.println(Constants.ELEVATOR_AT_FLOOR + curFloor);
			exit(job.person, job.floor);
			jobsLeft--;
		}
		else if(curFloor > job.floor) {
			for(int i = curFloor; i >= job.floor; i--) {
				curFloor = i;
				if(job.person != null) {
					job.person.curFloor = i;
				}
				if(i==0) {
					System.out.println(Constants.ELEVATOR_AT_LOBBY);
				}
				else {
					System.out.println(Constants.ELEVATOR_AT_FLOOR + curFloor);
				}
				if(curFloor == job.floor && !job.noPerson) {
					exit(job.person, job.floor);
					jobsLeft--;
				}
			}
		}
	}
	
	/**
	 * Takes the person off of the elevator and onto their floor. 
	 * @param person Person instance exiting the elevator
	 * @param floor floor number the person is getting dropped off on
	 */
	public void exit(Person person, int floor) {
		Floor exitFloor = buildingFloors[floor-1];
		exitFloor.enterFloor(person);
		int count = 0;
		for(Person occup : curOccupants) { //Goes through occupants until finding the requested one
			if(occup != null && occup.equals(person)) {
				curOccupants[count] = null;//Gets rid of the occupant from elevator array that is now exiting 
				numOccup--;
			}
			count++;
		}
	}

	/**
	 * Helper method to make the array for jobs and current people in line to use the elevator larger if
	 * adding another job or person would make the arrays run out of space.
	 */
	private void ensureCapacity() {
		if(jobs.length < jobsLeft + 1) {
			int newLength = jobs.length * 2;
			Job[] temp = new Job[newLength];
			Person[] tempPersons = new Person[newLength];
			for(int i = 0; i < jobs.length; i++) {//After creating longer array, copies everyone to the new one
				temp[i] = jobs[i];
				tempPersons[i] = curOccupants[i];
			}
			jobs = temp;
			curOccupants = tempPersons;
		}
	}

	/**
	 * Returns a string representation of this elevator instance, tells how many floors this elevator
	 * can reach and how many jobs it currently has to complete.
	 */
	public String toString() {
		String retString = "Elevator going up to " + buildingFloors.length + " floors with " + jobsLeft 
		+ " requests to complete";
		return retString;
	}
}