package main;
/**
* <This class initializes the building and allows for use of the elevator and correct location analysis>
* Known Bugs: < “None”>
*
* @author Shemarh Stewart
* shemarh@brandeis.edu
* 9/9/2025
* COSI 21A PA0
*/

public class Building {
private int numFloors;
private Floor[] floors;
private Elevator elevator;
	
/**
 * Initializes a Building with a specific number of floors
 * @param numFloors
 */
public Building(int numFloors) {
		this.numFloors= numFloors+1;
		this.floors= new Floor[this.numFloors];
		for(int i = 0; i < this.numFloors; i++){
			floors[i]= new Floor(i);
		}
	     this.elevator = new Elevator(this);
		
	}

	/**
	 * This method using a Person method determines wheter a job request can be created and if so creates a request
	 * @param person
	 * @param floor
	 * @return
	 */
	public boolean enterElevatorRequest(Person person, int floor) {
		
		if(floor <= numFloors){
		elevator.createJob(person, floor);	
		return true;
			
		}
		return false;
	}
	/**
	 * This Method uses a elevator method to process all the jobs in queue
	 */
	public void startElevator() {
		elevator.processAllJobs();
	}
	/**
	 * This person uses a method from Floor to change location of a Person
	 * @param person
	 * @param floor
	 */
	public void enterFloor(Person person, int floor) {
		if(floor <= numFloors && floor >=0 ){
			floors[floor].enterFloor(person);
		}
	}
	/**
	 * This method tells how many floors the building has
	 * @return String 
	 */

	public String toString(){
		return "Building has " + numFloors+1 +" floors";
	}}