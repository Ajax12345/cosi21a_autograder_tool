package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import main.Building;
import main.Person;


/**
 * Test cases for Building class
 * Known Bugs: None
 * 
 * @author Annika Bergstrom
 * annikabergstrom@brandeis.edu
 * September 09, 2025
 * COSI 21A PA0
 */
class StudentBuildingTest {

	/**
	 * If toString() works for a building with  + floor nums
	 */
	@Test
	void test_toString() {
		Building b1 = new Building(3);
		b1.startElevator();
		assertEquals("Building with 3 floors", b1.toString());
	}

	/**
	 * If toString() works for a building with 0 floor nums
	 */
	@Test
	void test_ZeroFloors() {
		Building b1 = new Building(0);
		b1.startElevator();
		assertEquals("Building with 0 floors", b1.toString());
	}

	/**
	 * If toString() works for a building with  -1 floor nums
	 */
	@Test
	void test_NegativeFloors() {
		Building b1 = new Building(-1);
		b1.startElevator();
		assertEquals("Building with -1 floors", b1.toString());
	}

	/**
	 * If enterElevator() works for a true input
	 */
	@Test
	void test_enterElevatorTrue() {
		Building b1 = new Building(10);
		Person p1 = new Person("Person", "One");
		
		assertEquals(true, b1.enterElevatorRequest(p1, 10));
	}

	/**
	 * If enterElevator() returns false for a too large input
	 */
	@Test
	void test_enterElevatorFalseTooLarge() {
		Building b1 = new Building(10);
		Person p1 = new Person("Person", "One");
		
		assertEquals(false, b1.enterElevatorRequest(p1, 11));
	}

	/**
	 * If enterElevator() returns false for a negative floor
	 */
	@Test
	void test_enterElevatorFalseNegative() {
		Building b1 = new Building(10);
		Person p1 = new Person("Person", "One");
		
		assertEquals(false, b1.enterElevatorRequest(p1, -1));
	}
}
