package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import main.Constants;
import main.Person;

class StudentPersonTest {

	@Test
	void testLocation() {
		Person p = new Person ("Tony", "Hawk");
		assertEquals(Constants.PERSON_IN_LOBBY,p.getLocation());
	}

	@Test
	void testGetToFloor(){
		Person p= new Person("Walter", "White");
		p.atFloor(3);
		assertEquals(Constants.PERSON_IN_FLOOR + "3", p.getLocation());
	}

}
