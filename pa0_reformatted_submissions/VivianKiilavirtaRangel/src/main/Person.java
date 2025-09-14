package main;

/**
* The Person class has a person constructor with a first and last name associated with it. A person can enter a building and get the location of a person using this class.
* Known Bugs: The enterBuilding calls the enterElevatorRequest but I had trouble finding how to get the person object to be part of the parameter.
*
* @author Vivian Kiilavirta Rangel
* akiilavirta@brandeis.edu
* <9-9-25>
* COSI 21A PA0
*/

public class Person {

	private String firstName;

	private String lastName;

	public int location = 0;
	
	public Person(String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
	}

	//@Param building and floor. @Returns a boolean whether or not a person can enter this building and access the floor
	public boolean enterBuilding(Building building, int floor) {

		building.enterElevatorRequest(null, floor);

		location = floor;

		//this method will enter a person into a buildings elevator

		//building.createJob(this.Person, floor);

		if(floor <= building.getFloor()){
			return true;
		}else{
			return false;
		}
		
	}

	//@Returns a string of the location. Location is updated whenever the elevator functions and depending on the location different messages are updated.
	public String getLocation() {
		
		if(location == 0){

			return "In Lobby";
			
		}else if(location > 0){

			return "In floor " + location;

		}else{

			return "Waiting to be serviced";

		}


	}

	@Override
	public String toString(){
		return "first name " + firstName + " last name " + lastName;
	}
}
