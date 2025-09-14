/**
 * The Building class constructs Building(int numFloors), and creates the enterElevatorRequest(Person person, int floor),
 * startElevator(), enterFloor(Person person, int floor), getNumFloors(), and getFloor(int floorNum) methods.
 * Known Bugs: None.
 * 
 * Liam Rittenburg
 * liamrittenburg@brandeis.edu
 * 01/24/2025
 * COSI 21A PA0
 */
package main;

public class Building {
	Floor[] floors;
	Elevator elevator;

	/**
	 * This constructor constructs accepts an integer representing the number of floors and constructs a Building object
	 * with a lobby plus numFloors amount of floors. An elevator is initialized as well.
	 * @param numFloors
	 */
	public Building(int numFloors) {
		floors = new Floor[numFloors + 1];
		elevator = new Elevator(numFloors, floors);
		for (int i = 0; i <= numFloors; i++)
		{
			floors[i] = new Floor(i);
		}
	}
	
	/**
	 * This method accepts a Person object and integer representing a floor number as an argument. If the floor
	 * is within the building bounds (is less than or equal to the number of floors) then a job is sent to the elevator class
	 * via the createJob method and a boolean true is returned. If the floor number is not within bounds, false is returned.
	 * @param person
	 * @param floor
	 * @return
	 */
	public boolean enterElevatorRequest(Person person, int floor) {
		if (floor < floors.length && floor > 0)
		{
			person.hasValidFloor();
			elevator.createJob(person, floor);
			return true;
		}
		else
		{
			return false;
		}
	}
	
	/**
	 * This method starts the elevator by calling the processAllJobs() method.
	 */
	public void startElevator() {
		elevator.occupantIndex = 0;
		elevator.processAllJobs();
	}
	
	/**
	 * This method accepts a Person object and a floor number as arguments, and enters the Person object into
	 * the desired floor.
	 * @param person
	 * @param floor
	 */
	public void enterFloor(Person person, int floor) {
		(floors[floor - 1]).enterFloor(person);
	}

	/**
	 * This method returns the number of floors in the building, plus the lobby
	 * @return
	 */
	public int getNumFloors() 
	{
		return floors.length;
	}

	/**
	 * This method accepts a floor number as an argument, and returns the given floor.
	 * @param floorNum
	 * @return
	 */
	public Floor getFloor(int floorNum)
	{
		return this.floors[floorNum];
	}
	

}
