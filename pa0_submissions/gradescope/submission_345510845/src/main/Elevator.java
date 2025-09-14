package main;

public class Elevator {

	/**
	 * This specifies the number of people that can be brought to their floors by an Elevator 
	 * instance at any given time. 
	 * <p>DO NOT REMOVE THIS FIELD</p>
	 * <p>You may edit it. But keep it public.</p>
	 */
	public static int maxOccupants = 3;
	
	private Job[] jobs;
	private int activeJob;
	private int currentFloor;
	private Building building;

	/**
	 * Constructs an Elavator with in the lobby with no current jobs.
	 * @param building - building elevator that jobs are being done in.
	 */
	public Elevator (Building building) {
		this.building = building;
		this.jobs = new Job[100];		//fixed array size to organize beter
		this.activeJob = 0;
		this.currentFloor = 0;		//starts at lobby
	}

	/**
	 * Adds a new job to be completed by this eleavtor given a person and a floor number.
	 * @param person - given person 
	 * @param - persons desired floor number
	 */
	public void createJob(Person person, int floor) {
		if (activeJob < jobs.length){
			jobs[activeJob] = new Job(person,floor);
			activeJob++;
		}
	}
	
	/**
	 * Removes jobs one by one and processes them.
	 */
	public void processAllJobs() {
		int index = 0;
		while (index<activeJob) {
			int people = 0;
			while (index < activeJob && people < maxOccupants){		//should process up to max (3) in one trip
				processJob(jobs[index]);
				index++;
				people++;
			}
			if (index < activeJob){
				putElevator(0);		//this should bring elevator back to the lobby if there are still some jobs through another method
			}

		}
		
	}
	
	/**
	 * Puts elevator at desired floor (floor by floor) if there is a job active
	 * @param wantedFloor - floor desired by the given person
	 */
	private void putElevator (int wantedFloor){
		while (currentFloor< wantedFloor){
			currentFloor++;
			printLocation();
		}
		while (currentFloor > wantedFloor){
			currentFloor--;
			printLocation();
		}
	}

	/**
	 * Prints current location of the elevator.
	 */
	private void printLocation(){
		if (currentFloor == 0){
			System.out.println(Constants.ELEVATOR_AT_LOBBY);
		} else{
			System.out.println(Constants.ELEVATOR_AT_FLOOR + currentFloor);
		}
	}

	/**
	 * Processes a given job, moving the levator floor to floor till at the desired floor.
	 * @param job - the job to process
	 */
	public void processJob(Job job) {
		int wantedFloor = job.getFloor();

		printLocation();			//will print first position

		putElevator(currentFloor);			//calling extra method that will move elevator floor by floot

		if(job.getPerson() != null){					//person should be dropped off at their wanted floor
			exit(job.getPerson(), wantedFloor);
		}
	}
	
	/** 
	* Removes a person from the elevator and onto their floor in the building the person is in. 
	* 
	* @param person - given person on the elevator
	* @param floor - desired floor in the building
	*/
	public void exit(Person person, int floor) {
		person.atFloor(floor);
		building.enterFloor(person,floor);
	}

	/**
	 * @return a string description of the elevator.
	 */
	public String toString(){
		if (currentFloor==0){
			return "Elevator at Lobby";
		}
		return "Elavtor at floor " + currentFloor + " with " + activeJob + " job count.";
	}
}