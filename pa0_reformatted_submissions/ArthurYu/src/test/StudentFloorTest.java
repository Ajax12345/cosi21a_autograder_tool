package test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import main.Floor;
import main.Person;

class StudentFloorTest {

	@Test
	void testFloorCreation() {
		Floor floor = new Floor();
		assertEquals("No people", floor.toString());
	}
	
	@Test
	void testEnterFloorSinglePerson() {
		Floor floor = new Floor();
		Person person = new Person("Alice", "Smith");
		
		floor.enterFloor(person);
		assertEquals("Alice Smith", floor.toString());
	}
	
	@Test
	void testEnterFloorMultiplePeople() {
		Floor floor = new Floor();
		Person person1 = new Person("Alice", "Smith");
		Person person2 = new Person("Bob", "Johnson");
		
		floor.enterFloor(person1);
		floor.enterFloor(person2);
		assertEquals("Alice Smith, Bob Johnson", floor.toString());
	}
	
	@Test
	void testFloorToStringEmpty() {
		Floor floor = new Floor();
		assertEquals("No people", floor.toString());
	}
}
