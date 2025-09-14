package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import main.Building;
import main.Constants;
import main.Person;

class StudentElevatorTest {

	@Test
	void test() {
		Building b= new Building(6);
		Person a = new Person("John", "Doe");
		a.enterBuilding(b, 3);
		b.startElevator();
		assertEquals(a.getLocation(),Constants.PERSON_IN_FLOOR+ "3");
	}
	}


