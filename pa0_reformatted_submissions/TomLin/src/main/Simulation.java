package main;

/**
* This class simulates how the Elevator works in a Building.
* Known Bugs: None
* @author Tom Lin
* delin28@brandeis.edu
* September 9, 2025
* COSI 21A PA0
*/
public class Simulation {
	public static void main(String[] args) {
		Building building1 = new Building(3);
		Person personA = new Person("Person", "A");
		Person personB = new Person("Person", "B");

		building1.enterElevatorRequest(personA, 2);
		building1.enterElevatorRequest(personB, 3);
		building1.startElevator();

		System.out.println("");
		
		Building building2 = new Building(3);
		Person personC = new Person("Person", "C");
		Person personD = new Person("Person", "D");
		Person personE = new Person("Person", "E");
		Person personF = new Person("Person", "F");

		building2.enterElevatorRequest(personC, 2);
		building2.enterElevatorRequest(personD, 3);
		building2.enterElevatorRequest(personE, 3);
		building2.enterElevatorRequest(personF, 2);
		building2.startElevator();
	}
}
