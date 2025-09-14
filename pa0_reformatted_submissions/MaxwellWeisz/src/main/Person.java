package main;

/**
 * Class to represent a single person who will be taken to a different floor. Holds a first and last name
 * as well as the floor they are currently on. Also holds a boolean so that the correct getLocation() 
 * string is returned if their request cannot be serviced or they are waiting to be serviced.
 * Known Bugs: None
 * @author Maxwell Weisz
 * maxwellweisz@brandeis.edu
 * Sept 9, 2025
 * COSI 21A PA0
 */
public class Person {
	String firstName;
	String lastName;
	int curFloor;
	boolean entered;

	/**
	 * Constructs a Person instance given a first and a last name as strings.
	 * @param firstName person's first name
	 * @param lastName person's last name
	 */	
	public Person(String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
		curFloor = 0;
		entered = false;
	}

	/**
	 * Enters this person into the buildings elevator if it is available. If their request is not possible
	 * then it returns false.
	 * @param building Building instance being entered
	 * @param floor floor number being requested
	 * @return true if the floor number is valid 
	 */
	public boolean enterBuilding(Building building, int floor) {
		boolean canEnter = building.enterElevatorRequest(this, floor);
		if(canEnter)
			entered = true;
		return canEnter;
	}
	
	/**
	 * Method to track this person's location in the building they are in as a string.
	 * @return person's current floor location
	 */
	public String getLocation() {
		String returnStr = "";
		if(entered == false && curFloor == 0) {
			returnStr = Constants.PERSON_IN_LOBBY;
		} else if (entered == true && curFloor == 0) {
			returnStr = Constants.PERSON_WAITING_TO_BE_SERVICED;
		} else if (curFloor > 0) {
			returnStr = Constants.PERSON_IN_FLOOR + curFloor;
		}
		return returnStr;
	}

	/**
	 * Returns a string representation of this Person instance as a first and last name.
	 */
	public String toString() {
		String returnStr = firstName + " " + lastName;
		return returnStr;
	}
}
