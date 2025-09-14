package main;

/*
* Job class representing a request for an elevator service. Holds the person at the desired floor.
* No Known Bugs
* @author Alex Fernandez-Kim
* alexanderfk@brandeis.edu
* September 9, 2025
* COSI 21A PA0
*/

public class Job {
	private Person person;
    private int destinationFloor;

    public Job (Person person, int destinationFloor) {
        this.person = person;
        this.destinationFloor = destinationFloor;
    }

    public Person getPerson() {
        return person;
    }

    public int getDestinationFloor() {
        return destinationFloor;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public void setDestinationFloor(int destinationFloor) {
        this.destinationFloor = destinationFloor;
    }

    public String toString() {
        if (person == null) {
            return "Job: Move elevator to floor: " + destinationFloor;
        } else {
            return "Job: " + person.getFirstName() + " " + person.getLastName() + " to floor " + destinationFloor;
        }
    }
}