package main;

/**
 * a Job represents a request to an elevator. Holds information on person and their desired floor
 * Known Bugs: None
 * 
 * @author Annika Bergstrom
 * annikabergstrom@brandeis.edu
 * September 09, 2025
 * COSI 21A PA0
 */
public class Job {
    private Person person;
    private int floor;

    /**
     * Creates a Job for a person to go to a certain floor
     * @param person - (Person) who to create a Job for
     * @param floor - (int) desired floor number
     */
	public Job(Person person, int floor) {
        this.person = person;
        this.floor = floor;
    }

    /**
     * Returns String representation of Job. Checks for null person for case when returning to lobby
     * @return String
     */
    public String toString() {
        if (person != null) {
            return "(" + person.toString() + " --> floor " + floor + ")";
        }
        return "(Nobody --> floor " + floor + ")";
    }

    /**
     * Returns the Job's person's desired floor. Used in Elevator's processJob(Job job)
     * @return - (int) of desired floor  
     */
    protected int getFloor() {
        return floor;
    }

    /**
     * Returns Person who the Job is for. 
     * @return - Person 
     */
    protected Person getPerson() {
        return person;
    }
}