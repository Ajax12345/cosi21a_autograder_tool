package main;

/**
 * Floor class representing floors that can hold people. Keeps objects of people currently on the floor.
 * No Known Bugs
 * @author Alex Fernandez-Kim
 * alexanderfk@brandeis.edu
 * September 9, 2025
 * COSI 21A PA0
 */

public class Floor {

	private Person[] people;
	private int personCount;
	private int floorNumber;
	private static final int INITIAL_CAPACITY = 5;

	public Floor(int floorNumber) {
		this.floorNumber = floorNumber;
		this.people = new Person[INITIAL_CAPACITY];
		this.personCount = 0;
	}

	public void enterFloor(Person person) {
		if (personCount >= people.length) {
			expandPeopleArray();
		}
		people[personCount] = person;
		personCount++;
		throw new UnsupportedOperationException();
	}

	public int getPersonCount() {
		return personCount;
	}

	public int getFloorNumber() {
		return floorNumber;
	}

	public Person getPerson(int index) {
		if (index >= 0 && index < personCount) {
			return people[index];
		}
		return null;
	}

	private void expandPeopleArray() {
		Person[] newPeople = new Person[people.length * 2];
		for (int i = 0; i < people.length; i++) {
			newPeople[i] = people[i];
		}
		people = newPeople;
	}

	public String toString() {
		String floorName;
		if (floorNumber == 0) {
			floorName = "Lobby";
		} else {
			floorName = "Floor " + floorNumber;
		}

		StringBuilder sb = new StringBuilder();
		sb.append(floorName).append(" with ").append(personCount).append(" people");

		if (personCount > 0) {
			sb.append(" - ");
			for (int i = 0; i < personCount; i++) {
				if (i > 0) sb.append(", ");
				sb.append(people[i].getFirstName()).append(" ").append(people[i].getLastName());
			}
		}
		return sb.toString();
	}
}
