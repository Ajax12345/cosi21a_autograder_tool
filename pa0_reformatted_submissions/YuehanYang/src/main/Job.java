package main;

/**
 * Job couples a Person and a destination floor.
 * Known Bugs: None
 *
 * @author Yuehan Yang
 *         <Yuehanyang@brandeis.edu>
 *         <September 9, 2025>
 *         COSI 21A PA0
 */
public class Job {
    private final Person person;
    private final int targetFloor;

    /**
     * @param person      person to move, may be null for special lobby call
     * @param targetFloor destination floor or lobby (0)
     */
    public Job(Person person, int targetFloor) {
        this.person = person;
        this.targetFloor = targetFloor;
    }

    // === Getters ===
    public Person getPerson() {
        return person;
    }

    public int getTargetFloor() {
        return targetFloor;
    }

    @Override
    public String toString() {
        String who = (person == null) ? "LobbyCall" : person.toString();
        return "Job(" + who + " -> " + targetFloor + ")";
    }
}