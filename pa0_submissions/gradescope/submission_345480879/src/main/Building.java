package main;

/**
 * Simulates a building with a set number of floors that is passed when constructing a Building object
 * which then creates an instance of an elevator with the max number of floors passed to the constructor.
 * Known Bugs: None
 * @author Maxwell Weiszs
 * maxwellweisz@brandeis.edu
 * Sept 9, 2025
 * COSI 21A PA0
 */
public class Building {
	Floor[] floors;
	Elevator elevator;
	Person[] lobby;
	int waiting;

	/**
	 * Constructs a new Building instance with a given number of floors from an int parameter.
	 * @param numFloors number of floors in the building
	 */
	public Building(int numFloors) {
		floors = new Floor[numFloors];
		for(int i = 1; i <= numFloors; i++) {
			floors[i-1] = new Floor(10, i);
		}
		elevator = new Elevator(floors);
	}
	
	/**
	 * Enters a person into the elevator if their request is a valid floor number that can be reached and
	 * the elevator is not currently full. If it is full then it enters them into the array of people 
	 * waiting to enter the elevator. If their request is invalid then it returns false.
	 * @param person person requesting to use elevator
	 * @param floor floor number being requested
	 * @return true if request is a valid floor number
	 */
	public boolean enterElevatorRequest(Person person, int floor) {
		if(floor > floors.length || floor < 0){
			return false;
		}
		else {
			elevator.createJob(person, floor);
			return true;
		}
	}
	
	/**
	 * Calls the elevators method to process all current requests for the people in the elevator.
	 */
	public void startElevator() {
		elevator.processAllJobs();
	}
	
	/**
	 * Enters a person onto the floor they requested when their floor is reached.
	 * @param person person instance entering the floor
	 * @param floor floor number they are entering
	 */
	public void enterFloor(Person person, int floor) {
		Floor personsFloor = floors[floor-1];
		personsFloor.enterFloor(person);
	}

	/**
	 * Returns a string representation of this building, showing the number of floors in the building
	 * and the floors that are currently occupied.
	 */
	public String toString() {
		String returnStr = "Building with ";
		returnStr += floors.length + " floors. Occupied floors [";
		for(int i = 0; i < floors.length; i++) {
			if(floors[i].curOccupants != 0) {
				int j = i + 1;
				returnStr += j + " ";
			}
		}
		returnStr += "]";
		return returnStr;
	}
}
