package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import main.Building;
import main.Person;

class StudentBuildingTest {

	@Test
	void testElevatorRequest() {
		Building b = new Building(3);
		Person p = new Person("Lebron","James");
		assertTrue(b.enterElevatorRequest(p,3));
	}

	@Test
	void testEnterFloor(){
		Building b = new Building(4);
		Person p = new Person("Abby", "Miller");
		b.enterFloor(p, 4);
	}
}
