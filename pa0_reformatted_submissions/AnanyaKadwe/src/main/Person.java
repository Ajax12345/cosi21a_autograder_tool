/**
 * Represents a Person in the building, who can interact with the elevator.
 * Known Bugs: None
 * @author Ananya Kadwe
 * ananyakadwe@brandeis.edu
 * September 9, 2025
 * COSI 21A PA0
 */

package main;

public class Person {
	private String firstName;
    private String lastName;
    private String location;
	
	/**
     * Constructs a Person with the given first and last name.
     * @param firstName The first name of the person.
     * @param lastName  The last name of the person.
     */
	public Person(String firstName, String lastName) {
		this.firstName = firstName;
        this.lastName = lastName;
        this.location = "Waiting to be serviced";
	}

	 /**
     * Attempts to enter the building and requests an elevator to a specific floor.
     * @param building The building to enter.
     * @param floor    The desired floor to go to.
     * @return true if the request is valid, false otherwise.
     */
	public boolean enterBuilding(Building building, int floor) {
		if (building.enterElevatorRequest(this, floor)) {
            this.location = "Waiting to be serviced";
            return true;
        }
        this.location = "In Lobby"; 
        return false;
	}
	
    /**
     * Updates the location of the person.
     * @param newLocation The new location of the person.
     */
    public void setLocation(String newLocation) {
        this.location = newLocation;
    }

	/**
     * Gets the current location of the person.
     * @return A string representing the person's location.
     */
	public String getLocation() {
		return this.location;
	}

    /**
     * Returns a string representation of the person.
     * @return A string with the person's first and last name and current location.
     */
    @Override
    public String toString() {
        return firstName + " " + lastName + " is currently: " + location;
    }
    
}
