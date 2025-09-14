package test;

import main.Building;
import main.Person;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
* This class tests Person class.
* Known Bugs: None
* @author Tom Lin
* delin28@brandeis.edu
* September 9, 2025
* COSI 21A PA0
*/
class StudentPersonTest {

	@Test
	public void testEnterBuilding() {
		Building building = new Building(10);
		Person person = new Person("Person", "Test");
		assertFalse(person.enterBuilding(building, 11));
		assertFalse(person.enterBuilding(building, 0));
		assertFalse(person.enterBuilding(building, -1));
		assertTrue(person.enterBuilding(building, 5));
	}

	@Test
	public void testGetLocation() {
		Building building = new Building(10);
		Person person = new Person("Person", "Test");
		person.enterBuilding(building, 5);
		assertEquals("In Lobby", person.getLocation());
		building.enterElevatorRequest(person, 5);
		assertEquals("Waiting to be serviced", person.getLocation());
		building.startElevator();
		assertEquals("In Floor 5", person.getLocation());
	}

	@Test 
	public void testToString() {
		Person person = new Person("Person", "Test");
		assertEquals("Person Test", person.toString());
	}

}
