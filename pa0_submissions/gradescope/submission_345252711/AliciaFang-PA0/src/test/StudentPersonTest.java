package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import main.Building;
import main.Person;
import main.Constants;

class StudentPersonTest {

	@Test
    public void testEnterBuildingInvalidFloor() {
        Building b = new Building(2);
        Person p = new Person("Alice", "Fang");

        boolean success = p.enterBuilding(b, 5); // invalid floor
        assertEquals(false,success);
        assertEquals(Constants.PERSON_IN_LOBBY, p.getLocation());
    }

	@Test
    public void testEnterBuildingValidFloorUpdatesLocation() {
        Building b = new Building(3);
        Person p = new Person("Charlie", "Brown");

        boolean success = p.enterBuilding(b, 2); // valid floor
        assertEquals(true, success);
        assertEquals(Constants.PERSON_WAITING_TO_BE_SERVICED, p.getLocation());
    }
}
