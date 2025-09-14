package main;

/**
 * Represents a job for an elevator 
 * Transporting a person to a specific floor or  
 * repositioning the elevator to the lobby.
 * Known Bugs: None
 * 
 * @author Alicia Fang
 * <aliciafang@brandeis.edu>
 * <September 7, 2025>
 * COSI 21A PA0
 */
public class Job {
    private final Person person; 
    private final int floor;

    /**
     * Constructs a Job for the given person and floor.
     *
     * @param person the person to transport, or null if this is a repositioning job
     * @param floor  the index of the destination floor (0 = Lobby)
     */
    public Job(Person person, int floor) {
        this.person = person;
        this.floor = floor;
    }

    /**
     * Returns the person associated with this job.
     *
     * @return the Person, or null if this job is only to reposition the elevator
     */
    public Person getPerson() {
        return person;
    }

    /**
     * Returns the floor index for this job.
     *
     * @return the destination floor number
     */
    public int getFloor() {
        return floor;
    }

    /**
     * Returns a string representation of this job.
     * If the job involves a person, it shows "[Person -> Floor]".
     * If the job is a repositioning job, it shows "[LobbyCall -> 0]".
     *
     * @return a string describing this job
     */
    @Override
    public String toString(){
        if (person == null) {
            return "[LobbyCall -> 0]";
        }
        return String.format("[%s -> %d]", person, floor);
    }

}