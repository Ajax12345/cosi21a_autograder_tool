/**
 * The Floor class constructs Floor(int floorNum), and creates the enterFloor(Person person), resize(), getOccupants(),
 * and toString() methods.
 * Known Bugs: None.
 * 
 * Liam Rittenburg
 * liamrittenburg@brandeis.edu
 * 01/24/2025
 * COSI 21A PA0
 */
package main;

public class Floor {
	Person[] floorOccupants;
	int floorNum;
	int occupantIndex;

	/**
	 * This constructor accepts an integer representing a floor number as an argument. floorOccupants is initialized
	 * and the number of the floor is set to the argument.
	 * @param floorNum
	 */
	public Floor(int floorNum)
	{
		floorOccupants = new Person[1];
		this.floorNum = floorNum;
	}

	/**
	 * This method accepts a Person as an argument. The person is added to the array representing the people that have been
	 * brought via elevator to the floor.
	 * @param person
	 */
	public void enterFloor(Person person) {
		floorOccupants[occupantIndex] = person;
		occupantIndex++;
		resize();
		person.reachedDestination();
	}

	/**
	 * This method checks whether the floorOccupant array has room for more elements. If it does not,
	 * the size of the array is doubled.
	 */
	public void resize()
	{
		if(occupantIndex >= floorOccupants.length - 1)
		{
			Person[] tmp = new Person[floorOccupants.length * 2];
			for(int i = 0; i < floorOccupants.length; i++)
			{
				tmp[i] = floorOccupants[i];
			}
			floorOccupants = tmp;
		}
	}

	/**
	 * This method returns the array of Person objects currently on the floor.
	 * @return
	 */
	public Person[] getOccupants()
	{
		return this.floorOccupants;
	}

	/**
	 * This method overrides the toString method, and returns the Person objects on the floor.
	 */
	@Override
	public String toString()
	{
		String occ = "";
		for(int i = 0; i < occupantIndex - 1; i++)
		{
			occ = occ + floorOccupants[i].toString() + ", ";
		}
		if(occupantIndex > 0)
		{
			occ = occ + floorOccupants[occupantIndex - 1].toString();
		}
		return occ;
	}
}
