package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import main.*;

/**
 * Test cases for Job class
 * Known Bugs: None
 * 
 * @author Annika Bergstrom
 * annikabergstrom@brandeis.edu
 * September 09, 2025
 * COSI 21A PA0
 */
class StudentJobTest {

	@Test
	void test_toString() {
		Job j1 = new Job(new Person("p", "1"),  2);
		assertEquals("(p 1 --> floor 2)", j1.toString());
	}

	@Test
	void test_toString_Null() {
		Job j1 = new Job(null, 2);
		assertEquals("(Nobody --> floor 2)", j1.toString());
	}
}
