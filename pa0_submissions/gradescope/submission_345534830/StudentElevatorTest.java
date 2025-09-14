package test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import main.Building;
import main.Elevator;
import main.Person;
import main.Constants;

public class StudentElevatorTest {

	@Test
	public void testSingleJobProcessing() {
		Building b = new Building(3);
		Elevator e = b.getElevator();
		Person p = new Person("Test", "Elevator");
		e.createJob(p, 2);
		e.processAllJobs();
		assertEquals(Constants.PERSON_IN_FLOOR + 2, p.getLocation());
	}

	@Test
	public void testCapacityLimit() {
		Elevator.maxOccupants = 2;
		Building b = new Building(3);
		Person p1 = new Person("P1", "X");
		Person p2 = new Person("P2", "X");
		Person p3 = new Person("P3", "X");

		p1.enterBuilding(b, 2);
		p2.enterBuilding(b, 3);
		p3.enterBuilding(b, 2);

		b.startElevator();

		assertEquals(Constants.PERSON_IN_FLOOR + 2, p1.getLocation());
		assertEquals(Constants.PERSON_IN_FLOOR + 3, p2.getLocation());
		assertEquals(Constants.PERSON_IN_FLOOR + 2, p3.getLocation());
	}
}