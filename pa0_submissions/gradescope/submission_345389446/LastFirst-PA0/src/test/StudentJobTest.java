package test;

import main.Person;
import main.Job;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
* This class tests Job class.
* Known Bugs: None
* @author Tom Lin
* delin28@brandeis.edu
* September 9, 2025
* COSI 21A PA0
*/
class StudentJobTest {

	@Test 
	public void testGetPerson() {
		Person person = new Person("Person", "Test");
		Job job = new Job(person, 5);
		assertEquals(person, job.getPerson());
		assertEquals("Person Test", job.getPerson().toString());
	}

	@Test 
	public void testGetFloor() {
		Person person = new Person("Person", "Test");
		Job job = new Job(person, 5);
		assertEquals(5, job.getFloor());
	}

	@Test
	public void testToString() {
		Person person = new Person("Person", "Test");
		Job jobA = new Job(person, 5);
		assertEquals("Sending Person Test to Floor 5.", jobA.toString());
		Job jobB = new Job(null, 0);
		assertEquals("Return to Lobby.", jobB.toString());
	}

}
