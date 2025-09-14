package main;

/**
 * Represents a person who can request to enter a building.
 * Tracks the person's name and current location.
 * Known Bugs: None
 *
 * @author Alicia Fang
 * <aliciafang@brandeis.edu>
 * <September 7, 2025>
 * COSI 21A PA0
 */
public class Person{
	private final String firstName;
	private final String lastName;
    private String location = Constants.PERSON_IN_LOBBY; 

    /**
     * Constructs a new Person with given names
     *
     * @param firstName the person’s first name
     * @param lastName  the person’s last name
     */
    public Person(String firstName, String lastName) { 
        this.firstName = firstName;
        this.lastName = lastName;
    }

    /**
     * Requests to enter the given building and go to the specified floor.
     * If the request is successful, this person’s location is updated to
     * "waiting to be serviced."
     *
     * @param building the building the person wants to enter
     * @param floor    the destination floor the person wants to go to
     * @return true if the person successfully requests to enter the elevator
     */
	public boolean enterBuilding(Building building, int floor) {
        boolean enter = building.enterElevatorRequest(this, floor);
        if(enter){
            this.location = Constants.PERSON_WAITING_TO_BE_SERVICED;
        }
        return enter;
	}

	/**
     * Updates this person’s location.
     *
     * @param loc the new location description
     */
    public void setLocation(String loc) {
        this.location = loc;
    }
	
    /**
     * Returns this person’s current location.
     *
     * @return the location description
     */
	public String getLocation() {
		return location;
	}

	/**
     * Returns a full name of the person.
     *
     * @return a string of format "firstName lastName"
     */
	@Override
	public String toString(){
		return String.format("%s %s (%s)", firstName, lastName, location);
	}
}
