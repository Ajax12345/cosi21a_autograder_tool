package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import main.*;

class StudentBuildingTest {

	@Test
	void testEnterElevatorRequest() {
		Building testBuilding = new Building(25);
		Person testA = new Person("First", "Here");
		Person testB = new Person("Test","B");
		Person testC = new Person("Test", "C");
		Person testD = new Person("Test", "D");
		Person testE = new Person("Test", "E");
		Person testF = new Person("Test", "F");
		Person testG = new Person("Test", "G");
		Person testH = new Person("Test", "H");
		assertEquals(testA.enterBuilding(testBuilding, 3), true);
		assertEquals(testB.enterBuilding(testBuilding, 25), true);
		assertEquals(testC.enterBuilding(testBuilding, 15), true);
		assertEquals(testD.enterBuilding(testBuilding, 20), true);
		assertEquals(testE.enterBuilding(testBuilding, 8), true);
		assertEquals(testF.enterBuilding(testBuilding, 15), true);
		assertEquals(testG.enterBuilding(testBuilding, 23), true);
		testBuilding.startElevator();
		assertEquals(testBuilding.toString(), "Building with 25 floors. Occupied floors [3 8 15 20 23 25 ]");
		assertEquals(testA.getLocation(), Constants.PERSON_IN_FLOOR + "3");
		assertEquals(testB.getLocation(), Constants.PERSON_IN_FLOOR + "25");
		assertEquals(testC.getLocation(), Constants.PERSON_IN_FLOOR + "15");
		assertEquals(testD.getLocation(), Constants.PERSON_IN_FLOOR + "20");	
		assertEquals(testE.getLocation(), Constants.PERSON_IN_FLOOR + "8");
		assertEquals(testF.getLocation(), Constants.PERSON_IN_FLOOR + "15");
		assertEquals(testG.getLocation(), Constants.PERSON_IN_FLOOR + "23");
		assertEquals(testH.getLocation(), "In Lobby");
		assertEquals(testH.enterBuilding(testBuilding, 27), false);
		assertEquals(testH.getLocation(), "In Lobby");
	}
}
