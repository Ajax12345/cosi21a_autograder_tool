package main;

/**
 * Class used by Elevator class to hold details of the person and floor number that they have requested.
 * Known Bugs: None
 * @author Maxwell Weisz
 * maxwellweisz@brandeis.edu
 * Sept 9, 2025
 * COSI 21A PA0
 */
public class Job {
	Person person;
    int floor;
    boolean noPerson;

    /**
     * Constructs a Job instance that holds a person and floor number to represent the person in the 
     * elevator requesting a floor number.
     * @param person person requesting a floor destination
     * @param floor floor number being requested
     */
    public Job(Person person, int floor) {
        this.person = person;
        this.floor = floor;
        if(person == null) 
            noPerson = true;
        else
            noPerson = false;
    }

    /**
     * Returns a string representation of this Job with the persons toString method and this jobs
     * requested floor number.
     */
    public String toString() {
        String retString = "";
        if(person == null) {
            retString += "Returning to lobby";
            return retString;
        }
        retString += person.toString() + " to floor " + floor;
        return retString;
    }
}