package main;

public class Elevator {

	/**
	* The elevator class creates an array of Job objects that act as orders for the elevator class. Only 3 people can be in an eelvator at one time before it goes down. CreateJob creates jobs
	and processJob and processAllJobs do all the jobs in a queue.
	* Known Bugs: Sometimes starting the elevator after 
	*
	* @author Vivian Kiilavirta Rangel
	* akiilavirta@brandeis.edu
	* 9-9-2025
	* COSI 21A PA0
	*/


	/**
	 * This specifies the number of people that can be brought to their floors by an Elevator 
	 * instance at any given time. 
	 * <p>DO NOT REMOVE THIS FIELD</p>
	 * <p>You may edit it. But keep it public.</p>
	 */
	public static int maxOccupants = 3;

	public int takenOccupants;

	public int currentElevatorFloor = 0;

	public Job[] jobArray;
	public int jobCounter;

	public Elevator(){

		jobArray = new Job[10];
		jobCounter = 0;
		takenOccupants = 0;

	}
	
	public void createJob(Person person, int floor) {

		//This creates a new job by using a person and floor. It adds to the job Counter to move the position of the Array to the left

		Job newJob = new Job(person, floor);

		jobArray[jobCounter] = newJob;

		jobCounter = jobCounter + 1;

		
	}
	
	public void processAllJobs() {
		

		//Runs a loop to process all jobs using the processJob()

		for(int i = 0; i < jobArray.length; i++){

			if(jobArray[i] != null){

				//only processes a job if it exists

				processJob(jobArray[i]);

			}

		}

	}
	
	public void processJob(Job job) {


		int desiredFloor = job.getFloor();
	
		Person person = job.getPerson();

		int distanceToTravel;

		if(currentElevatorFloor > desiredFloor){
			//determines whether elevator goes down

			distanceToTravel = currentElevatorFloor - desiredFloor;

			for(int i = currentElevatorFloor; i > desiredFloor-1; i--){

			System.out.println("Elevator at floor " + i);

			}

			takenOccupants = takenOccupants + 1;

			currentElevatorFloor = desiredFloor;



		}else{

			distanceToTravel = desiredFloor - currentElevatorFloor;
			//If desired floor is greater than current floor it is going UP

			for(int i = currentElevatorFloor; i < desiredFloor+1; i++){

				if(i == 0){
					System.out.println("Elevator at Lobby");
				}else{
					System.out.println("Elevator at floor " + i);
				}

			}

			takenOccupants = takenOccupants + 1;

			currentElevatorFloor = desiredFloor;
		



		}

		if(takenOccupants == 3){


			//resets the elevator after 3 ppl have used it

			for(int i = currentElevatorFloor; i > -1; i--){

				if(i == 0){
					System.out.println("Elevator at Lobby");
				}else{
					System.out.println("Elevator at floor " + i);
				}

			

			}
			
			currentElevatorFloor = 0;

			takenOccupants = 0;

		}

		

		



	}
	
	public void exit(Person person, int floor) {
		
		//adds the person to the Floor object which holds an array of Floors

		Floor newFloor = new Floor(floor);
		
		newFloor.enterFloor(person);

	}

	@Override
	public String toString(){

		return (jobCounter +"");

	}

}