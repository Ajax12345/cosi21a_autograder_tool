package test;

import main.Building;
import main.Person;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
* This class tests Building class.
* Known Bugs: None
* @author Tom Lin
* delin28@brandeis.edu
* September 9, 2025
* COSI 21A PA0
*/
class StudentBuildingTest {

	@Test
	public void testEnterElevatorRequest() {
		Building building = new Building(10);
		Person person = new Person("Person", "Test");
		assertFalse(building.enterElevatorRequest(person, 11));
		assertFalse(building.enterElevatorRequest(person, 0));
		assertFalse(building.enterElevatorRequest(person, -1));
		assertTrue(building.enterElevatorRequest(person, 5));
	}

	@Test 
	public void testToString() {
		Building building = new Building(10);
		assertEquals("This building has 10 floors.", building.toString());
	}
}


