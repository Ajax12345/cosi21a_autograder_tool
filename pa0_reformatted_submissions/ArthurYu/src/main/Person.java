package main;

/**
 * Represents a person using the elevator system.
 * Tracks the person's name, current location status, and current floor.
 * 
 * Known Bugs: None
 *
 * @author Arthur Yu
 * <ziyi.yu@brandeis.edu>
 * <September 9, 2025>
 * COSI 21A PA0
 */
public class Person {
	private String firstName;
	private String lastName;
	private String location; // Track location status
	private int currentFloor; // Current floor location


	/**
	 * Constructs a new Person with the given first and last name.
	 * Initializes the person in the lobby (floor 0).
	 * 
	 * @param firstName the person's first name
	 * @param lastName the person's last name
	 */
	public Person(String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.location = "In Lobby"; // Initial state in lobby
		this.currentFloor = 0; // 0 represents lobby
	}

	/**
	 * Attempts to enter a building and request elevator service to a specific floor.
	 * 
	 * @param building the building to enter
	 * @param floor the target floor number
	 * @return true if the request is valid and accepted, false otherwise
	 */
	public boolean enterBuilding(Building building, int floor) {
		// Call Building's enterElevatorRequest method
		boolean canEnter = building.enterElevatorRequest(this, floor);
		if (canEnter) {
			this.location = "Waiting to be serviced";
		}
		return canEnter;
	}
	
	/**
	 * Returns the current location status of this person.
	 * 
	 * @return "In Lobby", "Waiting to be serviced", or "In Floor x"
	 */
	public String getLocation() {
		return location;
	}

	public String getFirstName() {
		return firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public int getCurrentFloor() {
		return currentFloor;
	}
	
	// Add setter methods for other classes to call
	public void setLocation(String location) {
		this.location = location;
	}
	
	public void setCurrentFloor(int floor) {
		this.currentFloor = floor;
	}
	
	@Override
	public String toString() {
		return firstName + " " + lastName;
	}
}
