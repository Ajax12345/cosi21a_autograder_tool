package main;
/**
* <This class creates a person to operate within a building>
* Known Bugs: < “None”>
*
* @author Shemarh Stewart
* shemarh@brandeis.edu
* 9/9/2025
* COSI 21A PA0
*/
public class Person {
	private String firstName;
	private String lastName;
	 String location;
	/**
	 * This method initializes a person
	 * @param firstName
	 * @param lastName
	 */


	public Person(String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
	}

/**
 * This method determines wheter a destination for a person is valid
 * @param building
 * @param floor
 * @return boolean to determine validity
 */
	public boolean enterBuilding(Building building, int floor) {
		boolean ValidRequest= building.enterElevatorRequest(this, floor);
		if(ValidRequest){
			this.location= Constants.PERSON_WAITING_TO_BE_SERVICED;
		}else{
			this.location=Constants.PERSON_IN_LOBBY;
		}
		 return ValidRequest;
	}
	/**
	 * A method to get location
	 * @return String (with location of person)
	 */
	public String getLocation() {
		return this.location;
	}
/**
 * @return String contain full name and location of person
 */
	public String toString(){
		return firstName +" "+ lastName + " is "+ location;
	}
}
