package test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import main.Building;
import main.Person;
import main.Constants;

public class PersonTest {

	@Test
	public void testInitialEnterAndLocationChanges() {
		Building b = new Building(2);
		Person p = new Person("First", "Last");
		assertTrue(p.enterBuilding(b, 1));
		assertEquals(Constants.PERSON_WAITING_TO_BE_SERVICED, p.getLocation());
		b.startElevator();
		assertEquals(Constants.PERSON_IN_FLOOR + 1, p.getLocation());
	}

	@Test
	public void testUnserviceableStaysInLobby() {
		Building b = new Building(1);
		Person p = new Person("X", "Y");
		assertFalse(p.enterBuilding(b, 2));
		assertEquals(Constants.PERSON_IN_LOBBY, p.getLocation());
	}
}