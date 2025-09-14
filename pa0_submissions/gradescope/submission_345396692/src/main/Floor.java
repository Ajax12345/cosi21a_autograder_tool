package main;
/**
 * Represents a floor in the building that can accommodate multiple people.
 * Each floor maintains a list of people currently on that floor and can
 * dynamically resize to accommodate more people as needed.
 * Known Bugs: None
 * 
 * @author chenxi zou
 * chenxizou@brandeis.edu
 * 9/8/2025
 * COSI 21A PA0
 */
public class Floor {
	private int floorNumber;
	private Person[] people;
	private int peopleCount;
	/**
	 * Constructs a new Floor with the specified floor number.
	 * Initializes an empty array to hold people on this floor.
	 * 
	 * @param floorNumber the number identifying this floor
	 */
	public Floor(int floorNumber) {
		this.floorNumber = floorNumber;
		this.people = new Person[10];
		this.peopleCount = 0;
	}
	/**
	 * Adds a person to this floor.
	 * If the floor is at capacity, the people array will be resized
	 * to accommodate the new person.
	 * 
	 * @param person the person entering this floor
	 */
	public void enterFloor(Person person) {
		if (peopleCount >= people.length) {
			resize();
		}
		for (int i = 0; i < this.people.length; i++) {
			if (this.people[i] == null) {
				this.people[i] = person;
				peopleCount++;
				break;
			}
		}
	}
	/**
	 * Doubles the capacity of the people array when it becomes full.
	 * Copies all existing people to the new larger array.
	 */
	public void resize() {
		Person[] newPeople = new Person[this.people.length * 2];
		for (int i = 0; i < this.people.length; i++) {
			newPeople[i] = this.people[i];
		}
		this.people = newPeople;
	}

	@Override
	public String toString() {
		return "Floor " + floorNumber + " with " + peopleCount + " people";
	}
}
