package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import main.Floor;
import main.Person;

class StudentFloorTest {

	@Test
	void testEnterFloor() {
		Floor floor = new Floor();
		Person p = new Person("Tony", "Hawk");
		floor.enterFloor(p);

		String result = floor.toString();
		assertTrue(result.contains("Floor has 1 people on it."));
	}

}
