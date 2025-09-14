package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import main.*;

class StudentPersonTest {

	@Test
	void testEnterBuilding() {
		Building testBuilding = new Building(10);
		Person testA = new Person("test", "A");
		Person testB = new Person("test", "B");
		Person testC = new Person("test", "C");
		Person testD = new Person("test", "D");
		Person testE = new Person("test", "E");
		Person testF = new Person("test", "F");
		Person testG = new Person("test", "G");
		testA.enterBuilding(testBuilding, 7);
		testB.enterBuilding(testBuilding, 3);
		testC.enterBuilding(testBuilding, 5);
		testD.enterBuilding(testBuilding, 2);
		testE.enterBuilding(testBuilding, 1);
		testF.enterBuilding(testBuilding, 10);
		testG.enterBuilding(testBuilding, 13);
		assertEquals(testA.getLocation(), Constants.PERSON_WAITING_TO_BE_SERVICED);
		assertEquals(testB.getLocation(), Constants.PERSON_WAITING_TO_BE_SERVICED);
		assertEquals(testC.getLocation(), Constants.PERSON_WAITING_TO_BE_SERVICED);
		assertEquals(testD.getLocation(), Constants.PERSON_WAITING_TO_BE_SERVICED);
		assertEquals(testE.getLocation(), Constants.PERSON_WAITING_TO_BE_SERVICED);
		assertEquals(testF.getLocation(), Constants.PERSON_WAITING_TO_BE_SERVICED);
		assertEquals(testG.getLocation(), Constants.PERSON_IN_LOBBY);//Wont be serviced 
		testBuilding.startElevator();//Start elevator and bring people to their floors
		assertEquals(testA.getLocation(), Constants.PERSON_IN_FLOOR + "7");
		assertEquals(testB.getLocation(), Constants.PERSON_IN_FLOOR + "3");
		assertEquals(testC.getLocation(), Constants.PERSON_IN_FLOOR + "5");
		assertEquals(testD.getLocation(), Constants.PERSON_IN_FLOOR + "2");
		assertEquals(testE.getLocation(), Constants.PERSON_IN_FLOOR + "1");
		assertEquals(testF.getLocation(), Constants.PERSON_IN_FLOOR + "10");
		assertEquals(testG.getLocation(), Constants.PERSON_IN_LOBBY);//Still not serviced
	}

}
