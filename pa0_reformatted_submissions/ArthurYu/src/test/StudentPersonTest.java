package test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import main.Person;
import main.Building;

class StudentPersonTest {

	@Test
	void testPersonCreation() {
		Person person = new Person("Alice", "Smith");
		assertEquals("Alice", person.getFirstName());
		assertEquals("Smith", person.getLastName());
		assertEquals("In Lobby", person.getLocation());
		assertEquals(0, person.getCurrentFloor());
	}
	
	@Test
	void testPersonToString() {
		Person person = new Person("Bob", "Johnson");
		assertEquals("Bob Johnson", person.toString());
	}
	
	@Test
	void testEnterBuildingValidFloor() {
		Building building = new Building(3);
		Person person = new Person("Charlie", "Brown");
		
		boolean result = person.enterBuilding(building, 2);
		assertTrue(result);
		assertEquals("Waiting to be serviced", person.getLocation());
	}
	
	@Test
	void testEnterBuildingInvalidFloor() {
		Building building = new Building(3);
		Person person = new Person("David", "Wilson");
		
		boolean result = person.enterBuilding(building, 5); // Invalid floor
		assertFalse(result);
		assertEquals("In Lobby", person.getLocation());
	}
}
