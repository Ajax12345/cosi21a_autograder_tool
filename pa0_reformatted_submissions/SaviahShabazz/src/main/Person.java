package main;

public class Person {
	private String firstName;
	private String lastName;
	private String location;

	/**
	 * Constucts a person with a given first and last name 
	 * 
	 * @param firstName - the persons first name
	 * @param lastName - the persons last name
	 */
	public Person(String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.location = Constants.PERSON_IN_LOBBY;  //defaul starting locatin
	
	}

	/**
	 * Enters the person into a building elevator with a provided destination.
	 * 
	 * @param building - the building being entered by the person
	 * @param floor - floor the person would like to be on
	 */
	public boolean enterBuilding(Building building, int floor) {

		if (building.enterElevatorRequest(null, floor)) {
			location = Constants.PERSON_WAITING_TO_BE_SERVICED;
			return true;
		} 
			else{
			location = Constants.PERSON_IN_LOBBY;
			return false;
		}
	}
	
	/**
	 * WIll update the persons location at their floor
	 * 
	 * @param floor - the floor the person are now
	 */
	public void atFloor( int floor){		// did it make sense to add this method, I thought it would mkae it easier to organize 
		if (floor == 0) {
			location = Constants.PERSON_IN_LOBBY;
		} else {
			location = Constants.PERSON_IN_FLOOR + floor;
		}
	}

	

	/**
	 * @return the current location of the person as a string
	 */
	public String getLocation() {
		return location;
	}

	public String toString() {
		return firstName + " " + lastName + " " + location;
	}
}
