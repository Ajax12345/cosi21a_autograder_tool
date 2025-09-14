/**
 * Represents a Floor in the building.
 * Known Bugs: None
 * @author Ananya Kadwe
 * ananyakadwe@brandeis.edu
 * September 9, 2025
 * COSI 21A PA0
 */
package main;

public class Floor {

	private int floorNumber;
    private Person[] people; 
    private int count; 

    /**
     * Constructs a Floor with the given floor number.
     * @param floorNumber The number of the floor.
     */
    public Floor(int floorNumber) {
        this.floorNumber = floorNumber;
        this.people = new Person[10]; 
        this.count = 0;
    }

	/**
     * Adds a person to the floor.
     * @param person The person to add.
     */
	public void enterFloor(Person person) {
		if (count >= people.length) {
            expandCapacity();
        }
        people[count++] = person;
        person.setLocation("In Floor " + floorNumber);
	}

	/**
     * Expands the capacity of the people array when it is full.
     */
    private void expandCapacity() {
        Person[] newPeople = new Person[people.length * 2];
        System.arraycopy(people, 0, newPeople, 0, people.length);
        people = newPeople;
    }

    /**
     * Returns a string representation of the floor and its occupants.
     * @return A string describing the floor and its occupants.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Floor " + floorNumber + ": ");
        for (int i = 0; i < count; i++) {
            sb.append(people[i].toString());
            if (i < count - 1) {
                sb.append(", ");
            }
        }
        return sb.toString();
    }
}
