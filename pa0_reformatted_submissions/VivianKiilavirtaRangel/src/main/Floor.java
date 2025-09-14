package main;

/**
* The Floor class has a Floor constructor that takes in a floor number and creates a floor based off that number. Each floor can hold 10 people
Known Bugs: none i am aware of.
* @author Vivian Kiilavirta Rangel
* akiilavirta@brandeis.edu
* <9-9-25>
* COSI 21A PA0
*/


public class Floor {

	public Person[] personArray;
	public int personCounter = 0;
	public int floorNumber;

	public Floor(int floorNumber){

		this.floorNumber = floorNumber;

		personArray = new Person[10];

	}

	/*
	 * Enter Floor takes in a person as a parameter and then adds them to the array. It increases the person counter to the right by 1 to act as a makeshift queue. 
	 */
	public void enterFloor(Person person) {
		personArray[personCounter] = person;
		personCounter = personCounter + 1;
	}

	@Override
	public String toString(){

		String word = "";

		for(int i = 0; i < personArray.length; i++){

			word = word + personArray[i];

		}

		return word;

	}

}
