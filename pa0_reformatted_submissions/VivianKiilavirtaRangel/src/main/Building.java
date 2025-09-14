package main;

/**
* The building class is a class that has an array of floors to represent a building. It has an elevator class to move people objects up and down the building. There is an elevator request to see if a person
can use the elevator in the building. The startElevator request takes moves the elevator up and down depending on the jobs in queue. 
* Known Bugs: None
*
* @author Vivian Kiilavirta Rangel
* akiilavirta@brandeis.edu
* <9-9-25>
* COSI 21A PA0
*/

public class Building {

	public int numFloors;

	private Elevator elevator1;

	private Floor floor1;

	//fields

	public Building(int numFloors) {

		elevator1 = new Elevator();

		this.numFloors = numFloors + 1;

		int[] building1 = new int[this.numFloors];

		for(int i = 1; i < this.numFloors; i++){

			//Each part of the array has a different value to represent a floor. The first index is 0 to represent the lobby

			building1[i] = i;

		}


		
	}
	
	/*
	 * @return returns a boolean if the person can enter this floor, and @param is the person and floor they wish to travel to
	 */
	public boolean enterElevatorRequest(Person person, int floor) {

		elevator1.createJob(person, floor);

		//System.out.println("created new job");
		
		if(floor > this.numFloors){
			return false;

		}else{
			return true;
		}

	}
	
	//Starts all jobs
	public void startElevator() {

		//call a method in the buildings elevator instance that should process all current jobs

		this.elevator1.processAllJobs();

	}
	
	//@Param this logs the floor the person is now on and puts them on the floor indefiently. 
	public void enterFloor(Person person, int floor) {

		//save a reference of the person and floor number

		this.floor1 = new Floor(floor);

		this.floor1.enterFloor(person);

	}

	//Acts as a getter for the floor int
	public int getFloor(){
		return this.numFloors;
	}

	@Override
	public String toString(){
		return "Building has " + numFloors;
	}
}
