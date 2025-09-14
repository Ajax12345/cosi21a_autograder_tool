/**
 * Represents a Job for the Elevator, containing a Person and their target floor.
 * Known Bugs: None
 * @author Ananya Kadwe
 * ananyakadwe@brandeis.edu
 * September 9, 2025
 * COSI 21A PA0
 */
package main;

public class Job {
    private Person person; 
    private int targetFloor;
	
    /**
     * Constructs a Job with a given Person and target floor.
     * @param person      The person making the request.
     * @param targetFloor The floor the person wants to go to.
     */
    public Job(Person person, int targetFloor) {
        this.person = person;
        this.targetFloor = targetFloor;
    }

    /**
     * Gets the person associated with this job.
     * @return The person in the job.
     */
    public Person getPerson() {
        return person;
    }

    /**
     * Gets the target floor of this job.
     * @return The target floor.
     */
    public int getTargetFloor() {
        return targetFloor;
    }

    /**
     * Returns a string representation of the job.
     * @return A string describing the job.
     */
    @Override
    public String toString() {
        return "Job for " + person.toString() + " to Floor " + targetFloor;
    }
}