package test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import main.Floor;
import main.Person;

public class StudentFloorTest {

	@Test
	public void testEnterFloorIncreasesOccupancy() {
		Floor f = new Floor(1);
		Person p = new Person("Test", "Floor");
		f.enterFloor(p);
		assertEquals(1, f.getOccupancy());
	}

	@Test
	public void testMultiplePeopleOnFloor() {
		Floor f = new Floor(2);
		Person p1 = new Person("A", "B");
		Person p2 = new Person("C", "D");
		f.enterFloor(p1);
		f.enterFloor(p2);
		assertEquals(2, f.getOccupancy());
	}
}