package test;

import main.Building;
import main.Elevator;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
* This class tests Elevator class.
* Known Bugs: None
* @author Tom Lin
* delin28@brandeis.edu
* September 9, 2025
* COSI 21A PA0
*/
class StudentElevatorTest {

	@Test
	public void testToString() {
		Building building = new Building(10);
		Elevator elevator = new Elevator(building);
		assertEquals("This elevator is at Lobby.", elevator.toString());
	}

}
