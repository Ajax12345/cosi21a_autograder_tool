package test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import main.Job;
import main.Person;

public class StudentJobTest {

	@Test
	public void testJobStoresCorrectPersonAndFloor() {
		Person p = new Person("Test", "Job");
		Job j = new Job(p, 2);
		assertEquals(p, j.getPerson()); // 用 getter
		assertEquals(2, j.getTargetFloor()); // 用 getter
	}

	@Test
	public void testJobToStringContainsArrowAndFloor() {
		Person p = new Person("T", "J");
		Job j = new Job(p, 3);
		String s = j.toString();
		assertTrue(s.contains("Job"));
		assertTrue(s.contains("-> 3"));
	}
}
