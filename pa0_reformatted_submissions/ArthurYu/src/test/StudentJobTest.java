package test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import main.Job;
import main.Person;
import main.Building;

class StudentJobTest {

	@Test
	void testJobCreation() {
		Building building = new Building(3);
		Person person = new Person("Alice", "Smith");
		Job job = new Job(person, 2, building);
		
		assertEquals(person, job.getPerson());
		assertEquals(2, job.getTargetFloor());
		assertEquals(building, job.getBuilding());
	}
	
	@Test
	void testJobToString() {
		Building building = new Building(3);
		Person person = new Person("Bob", "Johnson");
		Job job = new Job(person, 3, building);
		
		assertEquals("Bob Johnson wants to go to floor 3", job.toString());
	}
	
	@Test
	void testJobGetters() {
		Building building = new Building(5);
		Person person = new Person("Charlie", "Brown");
		Job job = new Job(person, 4, building);
		
		assertEquals("Charlie", job.getPerson().getFirstName());
		assertEquals("Brown", job.getPerson().getLastName());
		assertEquals(4, job.getTargetFloor());
		assertEquals(5, job.getBuilding().getNumFloors());
	}
}
