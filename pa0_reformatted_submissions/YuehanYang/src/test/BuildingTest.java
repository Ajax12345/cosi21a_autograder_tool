package test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import main.Building;
import main.Person;
import main.Constants;

public class BuildingTest {

	@Test
	public void testInvalidFloorRequest() {
		Building b = new Building(3);
		Person p = new Person("T", "User");
		boolean ok = p.enterBuilding(b, 5);
		assertFalse(ok);
		assertEquals(Constants.PERSON_IN_LOBBY, p.getLocation());
	}

	@Test
	public void testValidFloorRequestQueued() {
		Building b = new Building(3);
		Person p = new Person("T", "User");
		boolean ok = p.enterBuilding(b, 2);
		assertTrue(ok);
		assertEquals(Constants.PERSON_WAITING_TO_BE_SERVICED, p.getLocation());
	}

	@Test
	public void testDeliveredLocation() {
		Building b = new Building(2);
		Person p = new Person("A", "B");
		assertTrue(p.enterBuilding(b, 2));
		b.startElevator();
		assertEquals(Constants.PERSON_IN_FLOOR + 2, p.getLocation());
	}
}