/**
 * The person class constructs Person(String firstName, String lastName), and the enterBuilding(Building building, int floor),
 * hasValidFloor(), getDestination(), reachedDestination(), getLocation(), isEligible(), getJob(), and toString() methods.
 * Known Bugs: None.
 * 
 * Liam Rittenburg
 * liamrittenburg@brandeis.edu
 * 01/24/2025
 * COSI 21A PA0
 */
package main;

public class Person {
	String firstName;
	String lastName;
	int desiredFloor;
	Building building;
	int currentFloor;
	boolean hasValidFloor;
	boolean atDestination;
	boolean elevatorEligible;
	Job job;
	
	/**
	 * This constructor accepts two strings representing first and last name respectively, and assigns the Person
	 * object's first and last name variables to those strings respectively.
	 * @param firstName
	 * @param lastName
	 */
	public Person(String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
	}

	/**
	 * This method accepts a Building object and an integer representing a floor as arguments. This method decides
	 * whether the Person's desired floor is eligible to ride the elevator or not (whether their floor number is
	 * within the building.) This method returns true if the floor is valid, and false otherwise.
	 * @param building
	 * @param floor
	 * @return
	 */
	public boolean enterBuilding(Building building, int floor) {
		if(floor < building.getNumFloors())
		{
			hasValidFloor();
			desiredFloor = floor;
			elevatorEligible = true;
			job = new Job(this, floor);
			building.enterElevatorRequest(this, floor);
			return true;
		}
		else
		{
			hasValidFloor = false;
			return false;
		}
	}

	/**
	 * This method accepts no arguments, and changes the hasValidFloor variable to true if called.
	 */
	public void hasValidFloor()
	{
		hasValidFloor = true;
	}
	/**
	 * This method returns the atDestination variable (which represents whether the Person object has reached their desired floor)
	 */
	public boolean getDestination()
	{
		return atDestination;
	}

	/**
	 * This method changes the atDestination variable to true when called.
	 */
	public void reachedDestination()
	{
		atDestination = true;
	}
	
	/**
	 * This method returns "Waiting to be serviced" if the person object has an accessible floor but has not reached
	 * that floor, "In Floor" if the person object has reached their desired floor, and "In Lobby" otherwise.
	 * @return
	 */
	public String getLocation() {
		if (hasValidFloor == true && getDestination() == false)
		{
			return "Waiting to be serviced";
		}
		else if (hasValidFloor == true && getDestination() == true)
		{
			return "In Floor " + desiredFloor;
		}
		else
		{
			return "In Lobby";
		}
	}

	/**
	 * This method returns whether the person object is eligible for the elevator (if their floor is within the building limits)
	 * @return
	 */
	public boolean isEligible()
	{
		return elevatorEligible;
	}

	/**
	 * This method returns the Job object associated with the Person object
	 * @return
	 */
	public Job getJob()
	{
		return job;
	}

	/**
	 * This method overrides the toString() method and returns the Person object's first and last name.
	 */
	@Override
	public String toString()
	{
		return this.firstName + " " + this.lastName;
	}
}
