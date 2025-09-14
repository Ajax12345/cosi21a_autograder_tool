package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import main.Floor;
import main.Person; 

/**
 * Test cases for Floor class
 * Known Bugs: None
 * 
 * @author Annika Bergstrom
 * annikabergstrom@brandeis.edu
 * September 09, 2025
 * COSI 21A PA0
 */
class StudentFloorTest {

	@Test
	void test_toString_Empty() {
		Floor f1 = new Floor(1);
		assertEquals("Floor 1: []", f1.toString());
	}

	@Test
	void test_enterFloor_Few_People() {
		Floor f1 = new Floor(1);
		Person p1 = new Person("Person", "One");
		Person p2 = new Person("Person", "Two");
		Person p3 = new Person("Person", "Three");
		Person p4 = new Person("Person", "Four");
		f1.enterFloor(p1);
		f1.enterFloor(p2);
		f1.enterFloor(p3);
		f1.enterFloor(p4);
		assertEquals("Floor 1: [Person One, Person Two, Person Three, Person Four]", f1.toString());
	}

	@Test
	void test_enterFloor_makeLargerFloor() {
		Floor f1 = new Floor(99);
		Person p1 = new Person("p", "One");
		Person p2 = new Person("p", "Two");
		Person p3 = new Person("p", "Three");
		Person p4 = new Person("p", "Four");
		Person p5 = new Person("p", "Five");
		Person p6 = new Person("p", "Six");
		Person p7 = new Person("p", "Seven");
		Person p8 = new Person("p", "Eight");
		Person p9 = new Person("p", "Nine");
		Person p10 = new Person("p", "Ten");
		Person p11 = new Person("p", "Eleven");
		f1.enterFloor(p1);
		f1.enterFloor(p2);
		f1.enterFloor(p3);
		f1.enterFloor(p4);
		f1.enterFloor(p5);
		f1.enterFloor(p6);
		f1.enterFloor(p7);
		f1.enterFloor(p8);
		f1.enterFloor(p9);
		f1.enterFloor(p10);
		f1.enterFloor(p11);
		String expected = "Floor 99: [p One, p Two, p Three, p Four, p Five, p Six, p Seven, p Eight, p Nine, p Ten, p Eleven]";
		assertEquals(expected, f1.toString());
	}

}
