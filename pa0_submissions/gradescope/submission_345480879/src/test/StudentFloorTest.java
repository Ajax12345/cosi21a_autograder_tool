package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import main.Floor;
import main.Person;

class StudentFloorTest {

	@Test
	void testEnterFloor() {
		Floor testFloor = new Floor(4, 20);
		testFloor.enterFloor(new Person("test", "A"));
		testFloor.enterFloor(new Person("test", "B"));
		testFloor.enterFloor(new Person("test", "C"));
		testFloor.enterFloor(new Person("test", "D"));
		assertEquals(testFloor.toString(), "Floor number 20. Current occupants [test A, test B, test C, test D]");
		testFloor.enterFloor((new Person("test", "past capacity"))); //Should expand the floor array
		assertEquals(testFloor.toString(), "Floor number 20. Current occupants [test A, test B, test C, test D, test past capacity]");
		testFloor = new Floor(4, 20);
		assertEquals(testFloor.toString(), "Floor number 20. Empty");
	}

}
