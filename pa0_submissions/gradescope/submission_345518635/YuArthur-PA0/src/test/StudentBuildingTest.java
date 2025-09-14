package test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import main.Building;
import main.Person;

class StudentBuildingTest {

	@Test
	void testBuildingCreation() {
		Building building = new Building(5);
		assertEquals(5, building.getNumFloors());
		assertNotNull(building.getFloor(0)); // Lobby
		assertNotNull(building.getFloor(1));
		assertNotNull(building.getFloor(5));
	}
	
	@Test
	void testBuildingToString() {
		Building building = new Building(3);
		assertEquals("Building with 3 floors", building.toString());
	}
	
	@Test
	void testEnterElevatorRequestValidFloor() {
		Building building = new Building(3);
		Person person = new Person("Alice", "Smith");
		
		boolean result = building.enterElevatorRequest(person, 2);
		assertTrue(result);
	}
	
	@Test
	void testEnterElevatorRequestInvalidFloor() {
		Building building = new Building(3);
		Person person = new Person("Bob", "Johnson");
		
		boolean result = building.enterElevatorRequest(person, 5); // Invalid floor
		assertFalse(result);
	}
	
	@Test
	void testEnterFloor() {
		Building building = new Building(3);
		Person person = new Person("Charlie", "Brown");
		
		building.enterFloor(person, 2);
		assertEquals("In Floor 2", person.getLocation());
		assertEquals(2, person.getCurrentFloor());
		assertTrue(building.getFloor(2).toString().contains("Charlie Brown"));
	}
}
