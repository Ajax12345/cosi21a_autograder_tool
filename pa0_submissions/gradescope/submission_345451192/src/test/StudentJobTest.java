package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import main.Job;
import main.Person;

class StudentJobTest {

    @Test
    void testJobConstruction() {
        Person person = new Person("Ari", "Smith");
        Job job = new Job(person, 3);
        assertNotNull(job, "Job object should not be null.");
        assertEquals(person, job.getPerson(), "Job should correctly store the associated person.");
        assertEquals(3, job.getTargetFloor(), "Job should correctly store the target floor.");
    }

    @Test
    void testGetPerson() {
        Person person = new Person("Michelle", "Jones");
        Job job = new Job(person, 2);
        assertEquals(person, job.getPerson(), "getPerson() should return the correct person associated with the job.");
    }

    @Test
    void testGetTargetFloor() {
        Person person = new Person("Naim", "Brown");
        Job job = new Job(person, 4);
        assertEquals(4, job.getTargetFloor(), "getTargetFloor() should return the correct target floor associated with the job.");
    }

    @Test
    void testToString() {
        Person person = new Person("Paul", "Johnson");
        Job job = new Job(person, 5);
        String output = job.toString();
        assertNotNull(output, "toString() should return a non-null string.");
        assertTrue(output.contains("Paul Johnson"), "toString() output should contain the person's name.");
        assertTrue(output.contains("Floor 5"), "toString() output should contain the target floor.");
    }
}
