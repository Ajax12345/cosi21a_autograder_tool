package test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import main.Elevator;
import main.Building;
import main.Person;

class StudentElevatorTest {

	@Test
	void testElevatorCreation() {
		Building building = new Building(3);
		Elevator elevator = new Elevator(building);
		
		assertEquals(0, elevator.toString().indexOf("Elevator at floor 0"));
		assertTrue(elevator.toString().contains("0 jobs"));
	}
	
	@Test
	void testMaxOccupants() {
		assertEquals(3, Elevator.maxOccupants);
	}
	
	@Test
	void testCreateJob() {
		Building building = new Building(3);
		Elevator elevator = new Elevator(building);
		Person person = new Person("Alice", "Smith");
		
		elevator.createJob(person, 2);
		assertTrue(elevator.toString().contains("1 jobs"));
	}
	
	@Test
	void testElevatorToString() {
		Building building = new Building(3);
		Elevator elevator = new Elevator(building);
		
		String result = elevator.toString();
		assertTrue(result.contains("Elevator at floor 0"));
		assertTrue(result.contains("jobs"));
	}
}
