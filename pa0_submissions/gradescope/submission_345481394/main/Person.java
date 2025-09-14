package main;

/**
 * Person has a first, last name and information on their desired floor and where they are in the building
 * Known Bugs: None
 * 
 * @author Annika Bergstrom
 * annikabergstrom@brandeis.edu
 * September 09, 2025
 * COSI 21A PA0
 */
public class Person {
	private String firstName;
	private String lastName;
	private int floor;
	private String status;
	
	/**
	 * Constructor for Person
	 * @param firstName - (String) person's first name
	 * @param lastName - (String) person's last name
	 */
	public Person(String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
	}

	/**
	 * Places person into building. Creates job and returns true if person can go to desired floor
	 * @param building - (Building) Building person enters
	 * @param floor - (int) Desired floor number
	 * @return boolean -  if person can go to floor (within Building's floor range)
	 */
	public boolean enterBuilding(Building building, int floor) {
		this.floor = floor;
		building.enterFloor(this, 0); // Puts person in lobby

		if (building.enterElevatorRequest(this, floor)) { 
			status = "Waiting to be Serviced";

			if (floor != 0) { 
				building.getElevator().createJob(this, floor); 
			} else { // The person is already on the floor they want
				isOnDesiredFloor();
				status = "In Lobby";
			}
			return true; // Person can enter building
		} else {
			status = "In Lobby";
			return false;
		}
	}
	
	/**
	 * Returns Person's location
	 * @return (String) "In Lobby" if Person won't be serviced. 
	 * "Waiting to be Serviced" if waiting for elevator, "In Floor X" when on desired floor
	 */
	public String getLocation() {
		return status;
	}

	/**
	 * Returns a String representation of Person
	 * @return (String) FirstName LastName
	 */
	public String toString() {
		return firstName + " " + lastName;
	}

	/**
	 * Sets that Person is on desired floor. Used for getting information for getLocation()
	 */
	protected void isOnDesiredFloor() {
		status = "In Floor " + floor;
	}

}
