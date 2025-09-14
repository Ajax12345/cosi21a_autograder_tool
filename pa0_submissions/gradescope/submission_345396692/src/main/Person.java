package main;
/**
 * Represents a person in the building elevator system.
 * Each person has a name and a current location within the building.
 * Known Bugs: None
 * 
 * @author chenxi zou
 * chenxizou@brandeis.edu
 * 9/8/2025
 * COSI 21A PA0
 */
public class Person {
	private String firstName;
	private String lastName;
	String location;
	/**
	 * Constructs a new Person with the specified first and last name.
	 * The person is initially located in the lobby.
	 * 
	 * @param firstName the person's first name
	 * @param lastName the person's last name
	 */
	public Person(String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.location = Constants.PERSON_IN_LOBBY;

	}
	/**
	 * Requests to enter the building and go to a specific floor.
	 * This method delegates the elevator request to the building.
	 * 
	 * @param building the building to enter
	 * @param floor the target floor number
	 * @return true if the elevator request was successful, false otherwise
	 */
	public boolean enterBuilding(Building building, int floor) {
		return building.enterElevatorRequest(this, floor);

	}
	/**
	 * Gets the current location of the person.
	 * 
	 * @return a string describing the person's current location
	 */
	public String getLocation() {
		return this.location;
	}

	@Override
	public String toString() {
		return firstName + " " + lastName;
	}
}
