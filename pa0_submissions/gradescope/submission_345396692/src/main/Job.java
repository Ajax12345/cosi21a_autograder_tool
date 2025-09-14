package main;
/**
 * Represents a job for the elevator system, containing information about
 * a person and their destination floor. Jobs are processed by the elevator
 * to transport people to their desired floors.
 * Known Bugs: None
 * 
 * @author Chenxi Zou
 * chenxizou@brandeis.edu
 * 9/8/2025
 * COSI 21A PA0
 */
public class Job {
    Person person;
    int targetFloor;
    /**
     * Constructs a new Job with the specified person and target floor.
     * 
     * @param person the person to be transported (can be null for lobby jobs)
     * @param targetFloor the destination floor number
     */
    public Job(Person person, int targetFloor) {
        this.person = person;
        this.targetFloor = targetFloor;
    }

    @Override
    public String toString() {
        if (person == null) {
            return "Job: Return to lobby";
        }
        return "Job: " + person.toString() + " to floor " + targetFloor;
    }
}