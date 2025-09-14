package main;

/**
 * Person has first and last name and tracks location for getLocation().
 * Known Bugs: None
 *
 * @author Yuehan Yang
 * <Yuehanyang@brandeis.edu>
 * <September 9, 2025>
 * COSI 21A PA0
 */
import main.Constants;

public class Person {

	private final String firstName;
	private final String lastName;

	// Location states: 0=In Lobby (not serviced), 1=Waiting, 2=On Floor x
	private int state = 0;
	private int atFloor = 0;
	private Building inBuilding = null;

	public Person(String firstName, String lastName) {
		if (firstName == null)
			firstName = "";
		if (lastName == null)
			lastName = "";
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public boolean enterBuilding(Building building, int floor) {
		if (building == null)
			return false;
		this.inBuilding = building;
		boolean ok = building.enterElevatorRequest(this, floor);
		if (!ok) {
			setInLobbyUnserviced();
		}
		return ok;
	}

	public String getLocation() {
		if (state == 1) {
			return Constants.PERSON_WAITING_TO_BE_SERVICED;
		} else if (state == 0) {
			return Constants.PERSON_IN_LOBBY;
		} else {
			return Constants.PERSON_IN_FLOOR + atFloor;
		}
	}

	void setWaiting() {
		this.state = 1;
		this.atFloor = 0;
	}

	void setInLobbyUnserviced() {
		this.state = 0;
		this.atFloor = 0;
	}

	void setOnFloor(int floor) {
		this.state = 2;
		this.atFloor = floor;
	}

	Building getBuilding() {
		return inBuilding;
	}

	@Override
	public String toString() {
		return "Person(" + firstName + " " + lastName + ", " + getLocation() + ")";
	}
}