package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import main.Building;
import main.Person;

class StudentPersonTest {

    @Test
    void testPersonConstruction() {
        Person person = new Person("Ari", "Smith");
        assertNotNull(person, "Person object should not be null.");
        assertEquals("Waiting to be serviced", person.getLocation(), "Person's initial location should be 'Waiting to be serviced'.");
    }

    @Test
    void testEnterBuildingValid() {
        Building building = new Building(3);
        Person person = new Person("Michelle", "Jones");
        boolean entered = person.enterBuilding(building, 2);
        assertTrue(entered, "Person should be able to request the elevator for a valid floor.");
        assertEquals("Waiting to be serviced", person.getLocation(), "Person's location should be updated to 'Waiting to be serviced'.");
    }

    @Test
    void testEnterBuildingInvalid() {
        Building building = new Building(3);
        Person person = new Person("Paul", "Brown");
        boolean entered = person.enterBuilding(building, 5); // Invalid floor
        assertFalse(entered, "Person should not be able to request the elevator for an invalid floor.");
        assertEquals("In Lobby", person.getLocation(), "Person's location should be updated to 'In Lobby' if the floor is invalid.");
    }

    @Test
    void testSetLocation() {
        Person person = new Person("Niam", "Johnson");
        person.setLocation("In Floor 2");
        assertEquals("In Floor 2", person.getLocation(), "setLocation should correctly update the person's location.");
    }

    @Test
    void testToString() {
        Person person = new Person("Eve", "Davis");
        String output = person.toString();
        assertNotNull(output, "toString() should return a non-null string.");
        assertTrue(output.contains("Eve Davis"), "toString() output should contain the person's full name.");
        assertTrue(output.contains("Waiting to be serviced"), "toString() output should contain the person's initial location.");
    }
}
