package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import main.Building;
import main.Person;

class StudentBuildingTest {

    @Test
    void testBuildingConstruction() {
        Building building = new Building(3); // A building with 3 floors plus a lobby
        assertNotNull(building, "Building object should not be null.");
    }

    @Test
    void testEnterElevatorRequestValid() {
        Building building = new Building(3);
        Person person = new Person("Ari", "Smith");
        boolean result = building.enterElevatorRequest(person, 2);
        assertTrue(result, "Person should be able to request the elevator to a valid floor.");
    }

    @Test
    void testEnterElevatorRequestInvalid() {
        Building building = new Building(3);
        Person person = new Person("Michelle", "Jones");
        boolean result = building.enterElevatorRequest(person, 5); // Invalid floor
        assertFalse(result, "Person should not be able to request the elevator to an invalid floor.");
    }

    @Test
    void testStartElevator() {
        Building building = new Building(3);
        Person person1 = new Person("Ari", "Smith");
        Person person2 = new Person("Michelle", "Jones");
        building.enterElevatorRequest(person1, 2);
        building.enterElevatorRequest(person2, 3);
        assertDoesNotThrow(() -> building.startElevator(), "Starting the elevator should not throw an exception.");
    }

    @Test
    void testEnterFloor() {
        Building building = new Building(3);
        Person person = new Person("Niam", "Brown");
        building.enterFloor(person, 2);
        // No exception should be thrown
        assertDoesNotThrow(() -> building.enterFloor(person, 2), "Person should be able to enter a valid floor.");
    }

    @Test
    void testToString() {
        Building building = new Building(3);
        String output = building.toString();
        assertNotNull(output, "Building toString() should return a non-null string.");
        assertTrue(output.contains("Building"), "Building toString() output should contain the word 'Building'.");
    }
}
