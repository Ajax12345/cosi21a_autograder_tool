package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import main.Building;
import main.Elevator;
import main.Person;

class StudentElevatorTest {

    @Test
    void testElevatorConstruction() {
        Building building = new Building(3); // A building with 3 floors plus a lobby
        Elevator elevator = new Elevator(building);
        assertNotNull(elevator, "Elevator object should not be null.");
    }

    @Test
    void testCreateJob() {
        Building building = new Building(3);
        Elevator elevator = new Elevator(building);
        Person person = new Person("Alice", "Smith");
        elevator.createJob(person, 2);
        assertEquals(1, elevator.toString().split("Pending Jobs: ")[1].trim().charAt(0) - '0', "Elevator should have 1 pending job.");
    }

    @Test
    void testExpandJobCapacity() {
        Building building = new Building(3);
        Elevator elevator = new Elevator(building);
        for (int i = 0; i < 15; i++) {
            elevator.createJob(new Person("Person" + i, "Test"), i % 3 + 1);
        }
        assertTrue(elevator.toString().contains("Pending Jobs: 15"), "Elevator should have expanded its job capacity to accommodate 15 jobs.");
    }

    @Test
    void testProcessSingleJob() {
        Building building = new Building(3);
        Elevator elevator = new Elevator(building);
        Person person = new Person("Ari", "Jones");
        elevator.createJob(person, 2);
        assertDoesNotThrow(() -> elevator.processAllJobs(), "Processing a single job should not throw an exception.");
    }

    @Test
    void testProcessMultipleJobs() {
        Building building = new Building(3);
        Elevator elevator = new Elevator(building);
        Person person1 = new Person("Ari", "Smith");
        Person person2 = new Person("Michelle", "Jones");
        elevator.createJob(person1, 2);
        elevator.createJob(person2, 3);
        assertDoesNotThrow(() -> elevator.processAllJobs(), "Processing multiple jobs should not throw an exception.");
    }

    @Test
    void testToString() {
        Building building = new Building(3);
        Elevator elevator = new Elevator(building);
        String output = elevator.toString();
        assertNotNull(output, "Elevator toString() should return a non-null string.");
        assertTrue(output.contains("Elevator"), "Elevator toString() output should contain the word 'Elevator'.");
    }
}
