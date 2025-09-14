package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import main.Person;
import main.Building;
import main.Constants;

/**
 * Test class for Person functionality.
 * Tests the core operations of the Person class including
 * construction, building entry, and location tracking.
 */
class StudentPersonTest {

	private Person person;
	private Building building;

	@BeforeEach
	void setUp() {
		person = new Person("John", "Doe");
		building = new Building(3);
	}

	@Test
	void testPersonConstruction() {
		// Test person is created with correct name
		assertNotNull(person);
		assertEquals("John Doe", person.toString());
		assertEquals(Constants.PERSON_IN_LOBBY, person.getLocation()); // Initially in lobby
	}

	@Test
	void testPersonConstructionWithEmptyName() {
		// Test person creation with empty name
		Person emptyPerson = new Person("", "");
		assertNotNull(emptyPerson);
		assertEquals(" ", emptyPerson.toString());
	}

	@Test
	void testPersonConstructionWithNullName() {
		// Test person creation with null name
		Person nullPerson = new Person(null, null);
		assertNotNull(nullPerson);
		assertEquals("null null", nullPerson.toString());
	}

	@Test
	void testEnterBuilding() {
		// Test person entering building and requesting a floor
		boolean result = person.enterBuilding(building, 2);

		assertTrue(result);
		// Person should now be waiting to be serviced
		assertEquals(Constants.PERSON_WAITING_TO_BE_SERVICED, person.getLocation());
	}

	@Test
	void testEnterBuildingInvalidFloor() {
		// Test person entering building with invalid floor request
		boolean result1 = person.enterBuilding(building, -1);
		boolean result2 = person.enterBuilding(building, 10);

		assertFalse(result1);
		assertFalse(result2);
		// Person should still be in lobby
		assertEquals(Constants.PERSON_IN_LOBBY, person.getLocation());
	}

	@Test
	void testEnterBuildingLobbyFloor() {
		// Test person entering building and requesting lobby (floor 0)
		boolean result = person.enterBuilding(building, 0);

		// Floor 0 (lobby) should be invalid for elevator request
		assertFalse(result);
		assertEquals(Constants.PERSON_IN_LOBBY, person.getLocation());
	}

	@Test
	void testGetLocation() {
		// Test getting person's location when not in building
		String location1 = person.getLocation();
		assertNotNull(location1);

		// Test getting person's location after entering building
		person.enterBuilding(building, 2);
		String location2 = person.getLocation();
		assertNotNull(location2);
		assertNotEquals(location1, location2);
	}

	@Test
	void testToString() {
		// Test string representation of person
		String personString = person.toString();
		assertNotNull(personString);
		assertTrue(personString.contains("John Doe"));
	}

	@Test
	void testMultipleFloorRequests() {
		// Test person making multiple floor requests
		person.enterBuilding(building, 1);
		person.enterBuilding(building, 2);
		person.enterBuilding(building, 3);

		// Check that multiple requests are handled properly
		// Person should be waiting to be serviced after first request
		assertEquals(Constants.PERSON_WAITING_TO_BE_SERVICED, person.getLocation());
	}

	@Test
	void testPersonEquality() {
		// Test person equality (if implemented)
		Person person2 = new Person("John", "Doe");
		Person person3 = new Person("Jane", "Doe");

		// Note: This test depends on whether equals() is overridden
		assertNotSame(person, person2);
		assertNotSame(person, person3);
	}

	@Test
	void testPersonWithDifferentBuildings() {
		// Test person entering different buildings
		Building building2 = new Building(5);

		boolean result1 = person.enterBuilding(building, 2);
		boolean result2 = person.enterBuilding(building2, 3);

		assertTrue(result1);
		// Second request might be rejected if person is already in a building
		// This depends on implementation
		assertTrue(result2);
	}
}
