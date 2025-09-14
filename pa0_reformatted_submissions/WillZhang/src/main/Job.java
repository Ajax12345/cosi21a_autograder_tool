
/**
 * COSI 21A – PA0
 * Job
 *
 * A request that a specific Person be taken to a target floor,
 * @author Will Zhang
 * @version Fall 2025
 */
public class Job {
    public final Person person;   
    public final int targetFloor; 

    private Job(Person p, int floor) {
        this.person = p;
        this.targetFloor = floor;
    }

    public static Job lobbyCall() { return new Job(null, -1); }

    public static Job ride(Person p, int floor) { return new Job(p, floor); }

    @Override
    public String toString() {
        if (person == null) return "[LobbyCall]";
        return "[Ride " + person + " -> floor " + targetFloor + "]";
    }
}