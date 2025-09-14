/**
 * Main simulation class to test the building, elevator, and person interactions.
 * Known Bugs: None
 * @author Ananya Kadwe
 * ananyakadwe@brandeis.edu
 * September 9, 2025
 * COSI 21A PA0
 */

package main;

public class Simulation {

	public static void main(String[] args) {
        Building building = new Building(3);

        Person alice = new Person("Alia", "Smith");
        Person bob = new Person("Tommy", "Jones");
        Person carol = new Person("Khimaya", "Brown");
        Person dave = new Person("Harper", "Johnson");

        alice.enterBuilding(building, 2);
        bob.enterBuilding(building, 3);  
        carol.enterBuilding(building, 3); 
        dave.enterBuilding(building, 2);  

        building.startElevator();
    }

}
