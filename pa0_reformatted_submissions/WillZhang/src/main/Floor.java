/*
 * COSI 21A – PA0
 * Floor
 *
 * Represents a floor in the building. Stores references to people who arrived
 * at this floor. Floor 0 is the lobby.
 *
 * @author Will Zhang
 * @version Fall 2025
 */

public class Floor {
    private final int number;              
    private final String name;
    private Person[] people;               
    private int count;

    public Floor(int number, String name) {
        this.number = number;
        this.name = name;
        this.people = new Person[4];
        this.count = 0;
    }

    public void enterFloor(Person person) {
        if (person == null) return;
        if (count == people.length) grow();
        people[count++] = person;
    }

    private void grow() {
        Person[] next = new Person[people.length * 2];
        for (int i = 0; i < people.length; i++) next[i] = people[i];
        people = next;
    }

    public String toString() {
    String label = (number == 0) ? "Lobby" : ("Floor " + number);
    String result = label + " occupants: [";
    for (int i = 0; i < count; i++) {
        result += people[i];
        if (i < count - 1) {
            result += ", ";
        }
    }
    result += "]";
    return result;
}

}