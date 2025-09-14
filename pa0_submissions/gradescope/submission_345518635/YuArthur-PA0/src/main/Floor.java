package main;

/**
 * Represents a single floor in a building.
 * Manages the people currently located on this floor using an array.
 * 
 * Known Bugs: None
 *
 * @author Arthur Yu
 * <ziyi.yu@brandeis.edu>
 * <September 9, 2025>
 * COSI 21A PA0
 */
public class Floor {
	private Person[] people;
	private int count;
	private static final int MAX_CAPACITY = 100; // Reasonable limit
	
	/**
	 * Constructs a new Floor with no people.
	 * Initializes the people array with maximum capacity.
	 */
	public Floor() {
		people = new Person[MAX_CAPACITY];
		count = 0;
	}
	
	/**
	 * Adds a person to this floor.
	 * 
	 * @param person the person to add to the floor
	 */
	public void enterFloor(Person person) {
		if (count < MAX_CAPACITY) {
			people[count] = person;
			count++;
		}
	}
	
	@Override
	public String toString() {
		if (count == 0) {
			return "No people";
		}
		StringBuilder result = new StringBuilder();
		for (int i = 0; i < count; i++) {
			if (i > 0) result.append(", ");
			result.append(people[i].toString());
		}
		return result.toString();
	}
}
