package main;

/**
 * Represents an elevator that transports people between floors in a building.
 * The elevator starts at the lobby (floor 0), can hold up to maxOccupants = 3
 * riders at a time, and processes jobs (in the order of first in first out).
 * Known Bugs: None
 * 
 * @author Alicia Fang
 * <aliciafang@brandeis.edu>
 * <September 7, 2025>
 * COSI 21A PA0
 */
public class Elevator{
	/**
	 * This specifies the number of people that can be brought to their floors by an Elevator 
	 * instance at any given time. 
	 * <p>DO NOT REMOVE THIS FIELD</p>
	 * <p>You may edit it. But keep it public.</p>
	 */
	public static int maxOccupants = 3;
	private final Building building;
	private int currentFloor = 0; // Original at Lobby
	private Job[] jobs = new Job[1000];
	private int jobsSize = 0;
	private final Job[] onboard = new Job[maxOccupants];
	private int onboardSize = 0;

	/**
     * Constructs an elevator associated with a given building.
     *
     * @param building the building of this elevator 
     */
	public Elevator(Building building){
		this.building=building;
	}

	/**
     * Creates a new job for the elevator to take a person to a given floor.
     *
     * @param person the person who use the elevator
     * @param floor  the floor where the person should be taken
     */
	public void createJob(Person person, int floor) {
		ensureCapacity(jobsSize + 1);
        jobs[jobsSize++] = new Job(person, floor);
	}

	/**
     * Ensures that the jobs array has enough capacity to hold a given number
     * of jobs. If not, the array size is doubled.
     *
     * @param needed the minimum required capacity
     */
	private void ensureCapacity(int needed) {
        if (needed <= jobs.length) {
            return;
        }
        Job[] bigger = new Job[jobs.length * 2];
        for (int i = 0; i < jobsSize; i++) {
            bigger[i] = jobs[i];
        }
        jobs = bigger;
    }
	
	/**
     * Processes all pending jobs by:
     * Returning to the lobby when no one in the elevator and 
	 * there is people waiting to be served.
     * Loading passengers up to maxOccupants.
     * Moving them to their requested floors and let them exit.
     * Repeating until all jobs are complete.
     */
	public void processAllJobs() {
		int iterator = 0;
		while(jobsSize > 0){
			while (currentFloor > 0) {
				processJob(new Job(null,0));
			}

			while (jobsSize > 0 && onboardSize < maxOccupants) {
				onboard[onboardSize++] = jobs[iterator];
				jobs[iterator++] = null;
				jobsSize--;
			} 

			for (int i = 0; i < onboardSize; i++) {
                Job j = onboard[i];
                processJob(j);
                exit(j.getPerson(), currentFloor);
            }
			onboardSize = 0;
		}
    }
	
	/**
     * Moves the elevator to the destination floor of a job,
     * printing location updates along the way.
     *
     * @param job the job containing a floor destination
     */
	public void processJob(Job job) {
		printLocation();
		int dest = job.getFloor();

		while(dest != currentFloor){
			if(dest > currentFloor){
				currentFloor++;
			}
			else{
				currentFloor--;
			}
			printLocation();
		}
	}
	
	/**
     * Drops off a person at the given floor
     *
     * @param person the person exiting the elevator
     * @param floor  the floor where the person exits
     */
	public void exit(Person person, int floor) {
		building.enterFloor(person, floor);
	}

	/**
     * Prints the elevator’s current location (lobby or floor).
     */
	private void printLocation() {
        if (currentFloor == 0) {
            System.out.println(Constants.ELEVATOR_AT_LOBBY);
        } 
		else {
            System.out.println(Constants.ELEVATOR_AT_FLOOR + currentFloor);
        }
    }

} 