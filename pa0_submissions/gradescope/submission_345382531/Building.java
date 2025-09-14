package main;

/*
* Building class manages floors and elevator system. Contains a lobby and numbered floors.
* No Known Bugs
* @author Alex Fernandez-Kim
* alexanderfk@brandeis.edu
* September 9, 2025
* COSI 21A PA0
*/

public class Building {
	private Floor[] floors;
	private Elevator elevator;
	private int numFloors;

	public Building(int numFloors) {
		this.numFloors = numFloors;
		this.floors = new Floor[numFloors + 1];

		for (int i = 0; i <= numFloors; i++) {
			floors[i] = new Floor(i);
		}

		this.elevator = new Elevator(this);
		throw new UnsupportedOperationException();
	}
	
	public boolean enterElevatorRequest(Person person, int floor) {
		if (floor < 1 || floor > numFloors) {
			return false;
		}

		person.setLocation("Waiting to be serviced");
		elevator.createJob(person, floor);
		return true;
		throw new UnsupportedOperationException();
	}
	
	public void startElevator() {
		elevator.processAllJobs();
		throw new UnsupportedOperationException();
	}
	
	public void enterFloor(Person person, int floor) {
		if (floor >= 0 && floor <= numFloors) {
			floors[floor].enterFloor(person);
			if (floor == 0) {
				person.setLocation("In Lobby");
			} else {
				person.setLocation("In Floor " + floor);
			}
		}
		throw new UnsupportedOperationException();
	}

	public Floor getFloor(int floorNum) {
		if (floorNum >= 0 && floorNum <= numFloors) {
			return floors[floorNum];
		}
		return null;
	}

	public int getNumFloors() {
		return numFloors;
	}

	public String toString() {
		return "Building with: " + numFloors + " floors and elevator at floor " + elevator.getCurrentFloor();
	}
}
