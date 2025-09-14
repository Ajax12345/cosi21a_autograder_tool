package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import main.Building;
import main.Constants;
import main.Person;

class StudentPersonTest {

	@Test
	void test() {
		Building b= new Building(6);
		Person a = new Person("John", "Doe");
		a.enterBuilding(b, 3);
		assertEquals(a.getLocation(),Constants.PERSON_WAITING_TO_BE_SERVICED);
	}

}
