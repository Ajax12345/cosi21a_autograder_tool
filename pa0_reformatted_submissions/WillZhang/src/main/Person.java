/**
 * COSI 21A – PA0
 * Person
 * 
 * Represents a person with a first and last name and a location string that
 * the grader checks for exact values: "In Lobby", "Waiting to be serviced",
 * and "In Floor N".
 * A Person can request a destination floor, after which
 * the elevator will eventually deliver them and update their location.
 *
 * @author Will Zhang
 * @version Fall 2025
 */
public class Person {

	private String firstName;
    private String lastName;

    private Building building;     
    private String location;       
    private int desiredFloor;
	
	public Person(String firstName, String lastName) {
		if (firstName == null || lastName == null) throw new IllegalArgumentException();
		this.firstName = firstName;
        this.lastName = lastName;
		this.location = "In Lobby";  
        this.desiredFloor = -1;	
	}

	public boolean enterBuilding(Building building, int floor) {
		if (this.building != null && this.building != building) {
            throw new IllegalStateException("Person can only enter one building.");
        }
        this.building = building;
        return building.enterElevatorRequest(this, floor);
	}
	
	public String getLocation() {
		return location;
	}

	 public String toString() {
        return firstName + " " + lastName + " [" + location + "]";
    }

	void markArrived(int floor) {
        this.desiredFloor = -1;
        this.location = "In Floor " + floor;
    }

	void markWaiting(Building b, int floor) {
        this.building = b;
        this.desiredFloor = floor;
        this.location = "Waiting to be serviced";
    }

	void markInLobbyUnserviceable(Building b) {
        this.building = b;
        this.desiredFloor = -1;
        this.location = "In Lobby";
    }
}
