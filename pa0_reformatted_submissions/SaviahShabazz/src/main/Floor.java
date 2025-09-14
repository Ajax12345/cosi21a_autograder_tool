package main;

public class Floor {
	private Person[] people;
	private int peopleCount;

	/** 
	 * Constructs a floor
	 */
	public Floor() {
		this.people = new Person[80];		// fixed array, (I put 80 as a random number)
		peopleCount = 0;			//new floor so I started it at 0
	}

	/**
	 * Saves a refrence of the person within this floor object.
	 * @param person - saved person in this floor object.
	 */
	public void enterFloor(Person person) {
		if (peopleCount < people.length) {
			people[peopleCount] = person;
			peopleCount++;					//increments the amount of people on said floor
		}
	}

	/**
	 * @return string representation of the floor.
	 */
	public String toString() {
		return "Floor has " + peopleCount + " people on it.";
	}
}

