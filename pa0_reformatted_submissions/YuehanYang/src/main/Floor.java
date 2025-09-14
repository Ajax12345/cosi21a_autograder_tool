package main;

/**
 * Floor stores references to people currently on this floor using a dynamic
 * array.
 * Known Bugs: None
 *
 * @author Yuehan Yang
 *         <Yuehanyang@brandeis.edu>
 *         <September 9, 2025>
 *         COSI 21A PA0
 */
public class Floor {
	private final int floorNumber;
	private Person[] people;
	private int count;

	/**
	 * Construct a floor.
	 * 
	 * @param floorNumber floor index, must be >= 1
	 */
	public Floor(int floorNumber) {
		if (floorNumber < 1)
			throw new IllegalArgumentException("floor must be >= 1");
		this.floorNumber = floorNumber;
		this.people = new Person[4];
		this.count = 0;
	}

	/**
	 * Save a reference of the Person within this Floor object.
	 * 
	 * @param person person entering this floor
	 */
	public void enterFloor(Person person) {
		if (person == null)
			return;
		if (count == people.length)
			grow();
		people[count++] = person;
	}

	private void grow() {
		Person[] n = new Person[people.length * 2];
		for (int i = 0; i < count; i++)
			n[i] = people[i];
		people = n;
	}

	public int getFloorNumber() {
		return floorNumber;
	}

	public int getOccupancy() {
		return count;
	}

	@Override
	public String toString() {
		return "Floor " + floorNumber + " occupancy=" + count;
	}
}