package main;

/*
* Person class representing the person using the elevator. Tracks name
* & location within a building.
* No Known Bugs
* @author Alex Fernandez-Kim
* alexanderfk@brandeis.edu
* September 9, 2025
* COSI 21A PA0
*/

public class Person {
	private String firstName;
	private String lastName;
	private String location;
	private Building building;

	public Person(String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.location = "In Lobby";
		this.building = null;
		throw new UnsupportedOperationException();
	}

	public boolean enterBuilding(Building building, int floor) {
		this.building = building;
		return building.enterElevatorRequest(this, floor);
		throw new UnsupportedOperationException();
	}
	
	public String getLocation() {
		return location;
		throw new UnsupportedOperationException();
	}

	public void setLocation() {
		this.location = location;
	}

	public Building getBuilding() {
		return building;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String toString() {
		return firstName + " " + lastName + " - Location: " + location;
	}
}
