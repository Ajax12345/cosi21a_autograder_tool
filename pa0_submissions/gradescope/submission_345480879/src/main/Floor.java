package main;

/**
 * Class to represent a floor in the Building class. Holds an array of Person instances to represent who
 * is on this floor, as well as a floor number as an integer.
 * Known Bugs: None
 * @author Maxwell Weisz
 * maxwellweisz@brandeis.edu
 * Sept 9, 2025
 * COSI 21A PA0
 */
public class Floor {
	public Person[] onFloor;//Person instances stored in this 'floor'
	int floorNumber;
	int curOccupants;//Number of occupants currently on this floor
	int maxOccupants;//Initial maximum number of occupants that can be stored on floor

	/**
	 * Constructs a Floor instance given a number of max occupants for the floor and the floor number it
	 * is.
	 * @param maxOccupants max number of Person instances stored on floor
	 * @param floorNumber int for the floor number 
	 */
	public Floor(int maxOccupants, int floorNumber) {
		this.maxOccupants = maxOccupants;
		this.onFloor = new Person[maxOccupants];
		this.floorNumber = floorNumber;
	}

	/**
	 * Helper method to ensure that the floor contains enough room in its array to store an additional
	 * person instance and expands it if necessary. 
	 */
	private void ensureCapacity() {
		if(curOccupants + 1 > maxOccupants) {
			int newLength = maxOccupants * 2;
			Person[] tempOccup = new Person[newLength];
			for(int i = 0; i < curOccupants; i++) {
				tempOccup[i] = onFloor[i];
			}
			onFloor = tempOccup;
		}
	}

	/**
	 * Enters a person onto this floor given a person instance. Checks first to make sure floor has 
	 * enough space to fit another person. 
	 * @param person Person instance entering this floor
	 */
	public void enterFloor(Person person) {
		ensureCapacity();
		onFloor[curOccupants] = person;
		curOccupants++;
	}

	/**
	 * Returns a string representation of this floor instance with its floor number and current 
	 * occupants on the floor if there are any.
	 */
	public String toString() {
		String retString = "Floor number " + floorNumber;
		if(curOccupants > 0) {
			retString += ". Current occupants [" + onFloor[0].toString();
			for(int i = 1; i < curOccupants; i++) {
				Person occup = onFloor[i];
				retString += ", " + occup.toString();
			}
			retString += "]";
		} else {
			retString += ". Empty";
		}
		return retString;
	}
}
