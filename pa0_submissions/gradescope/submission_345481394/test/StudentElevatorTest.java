package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import main.Elevator;
import main.Building; // So can create Elevator objects

/**
 * Test cases for Elevator class
 * Known Bugs: None
 * 
 * @author Annika Bergstrom
 * annikabergstrom@brandeis.edu
 * September 09, 2025
 * COSI 21A PA0
 */
class StudentElevatorTest {
	/**
	 * if toString() works for an elevator
	 */
	@Test
	void test_toString() {
		Building b1 = new Building(10);
		Elevator elevator1 = new Elevator(b1);
		assertEquals(elevator1.toString(), "Elevator in Building with 10 floors");
	}

	/**
	 * If toString() works for an elevator with a different way of acccessing it
	 */
	@Test
	void test_getElevator() {
		Building b1 = new Building(10);
		Elevator e1 = b1.getElevator();
		assertEquals(e1.toString(), "Elevator in Building with 10 floors");
	}

}
