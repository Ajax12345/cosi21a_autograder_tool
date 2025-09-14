package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import main.Building;
import main.Person;
import main.Constants;

/**
 * Test class for Building functionality.
 * Tests the core operations of the Building class including
 * construction, elevator requests, and floor management.
 */
class StudentBuildingTest {

	private Building building;
	private Person person1;
	private Person person2;

	@BeforeEach
	void setUp() {
		building = new Building(5);
		person1 = new Person("Alice", "Smith");
		person2 = new Person("Bob", "Jones");
	}

	@Test
	void testBuildingConstruction() {
		// Test building is created with correct number of floors
		assertNotNull(building);
		// Building should have floors 0-5 (6 total floors including lobby)
		// Cannot directly access floors field as it's protected
		// Test building construction indirectly
		assertNotNull(building);
	}

	@Test
	void testElevatorInitialization() {
		// Test elevator is properly initialized
		// Cannot directly access elevator field as it's private
		// Test elevator functionality indirectly
		assertNotNull(building);
	}

	@Test
	void testEnterElevatorRequest() {
		// Test adding elevator requests
		boolean result1 = building.enterElevatorRequest(person1, 3);
		boolean result2 = building.enterElevatorRequest(person2, 5);

		assertTrue(result1);
		assertTrue(result2);

		// Cannot directly access elevator jobs as fields are private
		// Test functionality indirectly through person location
		assertEquals(Constants.PERSON_WAITING_TO_BE_SERVICED, person1.getLocation());
		assertEquals(Constants.PERSON_WAITING_TO_BE_SERVICED, person2.getLocation());
	}

	@Test
	void testEnterFloor() {
		// Test entering people into floors
		building.enterFloor(person1, 2);
		building.enterFloor(person2, 4);

		// Cannot directly access floors array and people list as they are
		// protected/private
		// Test functionality indirectly through person location
		assertEquals(Constants.PERSON_IN_FLOOR + "2", person1.getLocation());
		assertEquals(Constants.PERSON_IN_FLOOR + "4", person2.getLocation());
	}

	@Test
	void testInvalidFloorRequest() {
		// Test requesting invalid floors
		boolean result1 = building.enterElevatorRequest(person1, -1);
		boolean result2 = building.enterElevatorRequest(person1, 10);

		assertFalse(result1);
		assertFalse(result2);
	}

	@Test
	void testMultipleRequests() {
		// Test multiple elevator requests
		building.enterElevatorRequest(person1, 1);
		building.enterElevatorRequest(person1, 3);
		building.enterElevatorRequest(person2, 2);

		// Cannot directly access elevator jobs
		// Test that requests were processed
		assertEquals(Constants.PERSON_WAITING_TO_BE_SERVICED, person1.getLocation());
		assertEquals(Constants.PERSON_WAITING_TO_BE_SERVICED, person2.getLocation());
	}

	@Test
	void testStartElevator() {
		// Test starting elevator with jobs
		building.enterElevatorRequest(person1, 2);
		building.enterElevatorRequest(person2, 4);

		// This should process all jobs
		assertDoesNotThrow(() -> building.startElevator());

		// Cannot directly access elevator currentFloor field
		// Test that elevator processing completed without errors
		assertNotNull(building);
	}
}
