package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import main.Floor;
import main.Person;

class StudentFloorTest {

    @Test
    void testFloorConstruction() {
        Floor floor = new Floor(1); // Create a floor with floor number 1
        assertNotNull(floor, "Floor object should not be null.");
    }

    @Test
    void testEnterFloor() {
        Floor floor = new Floor(1);
        Person person = new Person("Ari", "Smith");
        assertDoesNotThrow(() -> floor.enterFloor(person), "Person should be able to enter the floor without exceptions.");
    }

    @Test
    void testExpandCapacity() {
        Floor floor = new Floor(1);
        for (int i = 0; i < 15; i++) {
            floor.enterFloor(new Person("Person" + i, "Test"));
        }
        assertTrue(floor.toString().contains("Person14"), "Floor should dynamically expand to accommodate more people.");
    }

    @Test
    void testToString() {
        Floor floor = new Floor(1);
        Person person1 = new Person("Ari", "Smith");
        Person person2 = new Person("Michelle", "Jones");
        floor.enterFloor(person1);
        floor.enterFloor(person2);
        String output = floor.toString();
        assertNotNull(output, "Floor toString() should return a non-null string.");
        assertTrue(output.contains("Floor 1"), "Floor toString() output should contain the floor number.");
        assertTrue(output.contains("Ari Smith"), "Floor toString() output should contain the first person's name.");
        assertTrue(output.contains("Michelle Jones"), "Floor toString() output should contain the second person's name.");
    }
}
