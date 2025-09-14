package main;

/**
 * a Floor contains an array of Person objects who are currently on that floor
 * Known Bugs: None
 * 
 * @author Annika Bergstrom
 * annikabergstrom@brandeis.edu
 * September 09, 2025
 * COSI 21A PA0
 */
public class Floor {
	private int floorNum;
	private Person[] peopleHere;
	private int peopleHereIndex = 0;
	private int DEFAULT_SIZE = 10;

	/**
	 * Floor constructor. Creates array for people on it (peopleHere)
	 * @param floorNum - (int) what number floor it is
	 */
	public Floor(int floorNum) {
		this.floorNum = floorNum;
		peopleHere = new Person[DEFAULT_SIZE];
	}

	/**
	 * Places given person in Person[] array, expands if necessary
	 * @param person - (Person) who to store
	 */
	public void enterFloor(Person person) {
		if (peopleHereIndex == peopleHere.length) {
			makeLargerFloor();
		}
		peopleHere[peopleHereIndex] = person;
		peopleHereIndex++;
	}

	/**
	 * Returns String representation, which is the floor number and people on it
	 * @return String
	 */
	public String toString() {
		String people = "Floor " + floorNum + ": [";
		for (int i = 0; i < peopleHereIndex; i++) {
			if (peopleHere[i] != null) {
				people += peopleHere[i].toString();
			}
			if (i != peopleHereIndex-1) {
				people += ", ";
			}
		}
		people += "]";
		return people;
	}

	/**
	 * Removes a person from peopleHere array by looping through array. Used when a person changes
	 * floor. Called by Elevator's exit()
	 * @param person - (Person) who to remove
	 */
	protected void removeFromFloor(Person person) {
		for (int i = 0; i < peopleHereIndex; i++) {
			if (peopleHere[i] != null && peopleHere[i].equals(person)) {
				peopleHere[i] = null;
				break;
			}
		}
	}

	/**
	 * Helper function for enterFloor(person). Transfers people on floor to larger array so
	 * peopleHere array does not run out of space
	 */
	private void makeLargerFloor() {
		Person[] largerFloor = new Person[DEFAULT_SIZE * 2];
		DEFAULT_SIZE *= 2;
		int newPeopleHereIndex = 0;

		for (int i = 0; i < peopleHere.length; i++) {
			if (peopleHere[i] != null) {
				largerFloor[newPeopleHereIndex] = peopleHere[i];
				newPeopleHereIndex++;
			}
		}

		peopleHereIndex = newPeopleHereIndex;
		peopleHere = largerFloor;
	}
}
