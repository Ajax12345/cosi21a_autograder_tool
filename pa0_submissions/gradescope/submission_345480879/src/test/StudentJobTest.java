package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import main.*;

class StudentJobTest {

	@Test
	void testJob() {
		Job testJobA = new Job(new Person("test", "A"), 10);
		Job testJobB = new Job(new Person("test", "B"), 23);
		Job testJobC = new Job(new Person("test", "C"), 32);
		Job testJobD = new Job(new Person("test", "D"), 17);
		Job testJobE = new Job(new Person("test", "E"), 100);
		assertEquals(testJobA.toString(), "test A to floor 10");
		assertEquals(testJobB.toString(), "test B to floor 23");
		assertEquals(testJobC.toString(), "test C to floor 32");
		assertEquals(testJobD.toString(), "test D to floor 17");
		assertEquals(testJobE.toString(), "test E to floor 100");
		Job testJobF = new Job(null, 29);
		assertEquals(testJobF.toString(), "Returning to lobby");//Null person so should return to lobby
	}

}
