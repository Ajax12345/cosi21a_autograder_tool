package test;

import main.Floor;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
* This class tests Floor class.
* Known Bugs: None
* @author Tom Lin
* delin28@brandeis.edu
* September 9, 2025
* COSI 21A PA0
*/
class StudentFloorTest {

	@Test
	public void testToString() {
		Floor lobby = new Floor(0);
		assertEquals("This is Lobby.", lobby.toString());

		Floor floor = new Floor(5);
		assertEquals("This is Floor 5.", floor.toString());
	}

}
