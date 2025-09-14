package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import main.Person;
import main.Building;

/**
 * Test cases for Person class
 * Known Bugs: None
 * 
 * @author Annika Bergstrom
 * annikabergstrom@brandeis.edu
 * September 09, 2025
 * COSI 21A PA0
 */
class StudentPersonTest {

	@Test
	void test_toString() {
		Person p1 = new Person("First", "Last");
		assertEquals("First Last", p1.toString());
	}

	@Test
	void test_enterBuilding_Valid_Max() {
		Person p1 = new Person("First", "Last");
		assertTrue(p1.enterBuilding(new Building(2), 2));
	}
	
	@Test
	void test_enterBuilding_Valid() {
		Person p1 = new Person("First", "Last");
		assertTrue(p1.enterBuilding(new Building(2),1));
	}

	@Test
	void test_enterBuilding_Lobby() {
		Person p1 = new Person("First", "Last");
		// Want false because don't need elevator. Already at desired floor
		assertFalse(p1.enterBuilding(new Building(2),0));
	}

	@Test
	void test_enterBuilding_Invalid_Too_Large() {
		Person p1 = new Person("First", "Last");
		assertFalse(p1.enterBuilding(new Building(2),3));
	}

	@Test
	void test_enterBuilding_Invalid_Negative() {
		Person p1 = new Person("First", "Last");
		assertFalse(p1.enterBuilding(new Building(2),-2));
	}

	@Test
	void test_getLocation_Null() {
		Person p1 = new Person("First", "Last");
		assertEquals(null, p1.getLocation());
	}

	@Test
	void test_getLocation_Waiting() {
		Person p1 = new Person("First", "Last");
		p1.enterBuilding(new Building(5), 3);
		assertEquals("Waiting to be Serviced", p1.getLocation());
	}

	@Test
	void test_getLocation_In_Lobby_0() {
		Person p1 = new Person("First", "Last");
		p1.enterBuilding(new Building(5), 0);
		assertEquals("In Lobby", p1.getLocation());
	}

	@Test
	void test_getLocation_In_Lobby() {
		Person p1 = new Person("First", "Last");
		p1.enterBuilding(new Building(5), -1);
		assertEquals("In Lobby", p1.getLocation());
	}

}
