package main;

/**
* This class represents a Job is used to represent a “request” to the elevator. 
* Known Bugs: None
* @author Tom Lin
* delin28@brandeis.edu
* September 9, 2025
* COSI 21A PA0
*/
public class Job {
	
    private Person person;
    private int floor;

    /**
     * This constructor creates a new Job with the specified person and destination floor.
     * If the person is null, the floor must be 0 (indicating a return to lobby).
     * @param person the Person making the elevator request, or null for return to lobby
     * @param floor the destination floor number (must be 0 if person is null)
     * @precondition if person is null, floor will always be 0
     */
    public Job(Person person, int floor) {
        this.person = person;
        this.floor = floor;
    }

    /**
     * This method gets the person for this job.
     * @return the Person making the request, or null for return to lobby jobs
     */
    public Person getPerson() {
        return person;
    }

    /**
     * This method gets the destination floor for this job.
     * @return the floor number this job is targeting
     */
    public int getFloor() {
        return floor;
    }

    /**
     * This method returns a string representation of the job.
     * @return a descriptive string indicating the job's purpose
     */
    @Override
    public String toString() {
        if (person == null) {
            return "Return to Lobby.";
        }
        else {
            return "Sending " + person + " to Floor " + floor + ".";
        }
    }
}