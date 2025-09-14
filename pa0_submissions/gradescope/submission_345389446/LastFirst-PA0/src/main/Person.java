package main;

/**
* This class represents a person has a first and last name and information regarding their location in a Building. 
* Known Bugs: None
* @author Tom Lin
* delin28@brandeis.edu
* September 9, 2025
* COSI 21A PA0
*/
public class Person {
	
	private String firstName;
	private String lastName;
	private int floor;
	private boolean waiting = false;

	/**
     * This constructor creates a new Person with the specified first and last name.
     * @param firstName the person's first name
     * @param lastName the person's last name
     */
	public Person(String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
	}

    /**
     * This method attempts to enter a building and request service to a specific floor,
       validates that the requested floor exists in the building.
     * @param building the Building the person is trying to enter
     * @param floor the desired destination floor
     * @return true if the floor is valid, false otherwise
     */
	public boolean enterBuilding(Building building, int floor) {
		if (floor > building.getNumFloors() || floor <= 0) {
			return false;
		}
		else {
			return true;
		}
	}

	/**
     * This gets the current location status of the person.
     * @return a string indicating the person's current location or waiting status
     */
	public String getLocation() {
		if (waiting == true) {
			return Constants.PERSON_WAITING_TO_BE_SERVICED;
		}
		else if (floor == 0) {
			return Constants.PERSON_IN_LOBBY;
		}
		else {
			return Constants.PERSON_IN_FLOOR + floor;
		}
	}

    /**
     * This method updates the person's current floor location.
     * @param floor the floor number where the person is now located
     */
	protected void enterFloor(int floor) {
		this.floor = floor;
	}

    /**
     * This method toggles the person's waiting status.
     * Changes the waiting status from true to false or false to true.
     */
	protected void changeWaitingStatus() {
		if (waiting == false) {
			waiting = true;
		}
		else {
			waiting = false;
		}
	}

    /**
     * This method returns a string representation of the person.
     * @return the person's full name
     */
	@Override
	public String toString() {
		return firstName + " " + lastName;
	}
}