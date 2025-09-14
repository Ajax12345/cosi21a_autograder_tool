package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import main.Building;
import main.Elevator;
import main.Person;

class StudentElevatorTest {

	 @Test
    public void testProcessAllJobsMultiplePeople() {
        Building b = new Building(3);
        Elevator e = new Elevator(b);

        Person p1 = new Person("Bob", "Smith");
        Person p2 = new Person("Charlie", "Brown");
        Person p3 = new Person("Dana", "Jones");

        e.createJob(p1, 1);
        e.createJob(p2, 2);
        e.createJob(p3, 3);

        e.processAllJobs();

        String output = b.toString();
        assertTrue(output.contains("Floor 1 : Bob Smith (In Floor 1)"));
        assertTrue(output.contains("Floor 2 : Charlie Brown (In Floor 2)"));
        assertTrue(output.contains("Floor 3 : Dana Jones (In Floor 3)"));
    }

}
