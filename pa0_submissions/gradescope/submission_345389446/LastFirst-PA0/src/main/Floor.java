package main;

/**
* This class represents a floor that contains an array of Person objects that are currently on the Floor.
* Known Bugs: None
* @author Tom Lin
* delin28@brandeis.edu
* September 9, 2025
* COSI 21A PA0
*/
public class Floor {
	
	private Person[] persons;
	private int floorNum;

	/**
     * This constructor a new Floor with the specified floor number,
     * initializes the array to hold persons on this floor.
     * @param floorNum the number/identifier of this floor
     */
	public Floor(int floorNum) {
		persons = new Person[100];
		this.floorNum = floorNum;
	}
	
	/**
     * This method adds a person to this floor.
     * If the person is not null, ensures capacity and adds them to the first available position.
     * @param person the Person to be added to this floor
     */
	public void enterFloor(Person person) {
		if (person != null) {
			ensureCapacity();
			for (int i = 0; i < persons.length; i++) {
				if (persons[i] == null) {
					persons[i] = person; 
					return;
				}
			}
		}
	}

	/**
     * This helper method ensures the persons array has sufficient capacity,
     * expands the array by 100 elements if it is full.
     */

	private void ensureCapacity() {
		if (persons[persons.length-1] != null) {
			Person[] temp = new Person[persons.length + 100];
			for (int i = 0; i < persons.length; i++) {
				temp[i] = persons[i];
			}
			persons = temp;
		}
	}

    /**
     * This method returns a string representation of the floor.
     * @return a string indicating which floor this is, with special handling for floor 0 (lobby)
     */
	@Override
	public String toString() {
		if (floorNum == 0) {
			return "This is Lobby.";
		}
		else {
			return "This is Floor " + floorNum + ".";
		}
	}
}
