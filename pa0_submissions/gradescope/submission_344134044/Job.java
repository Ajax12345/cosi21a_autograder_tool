/**
 * This class constructs Job(Person person, int floor), and creates the getJobFloor() and toString() methods.
 * Known Bugs: None.
 * 
 * Liam Rittenburg
 * liamrittenburg@brandeis.edu
 * 01/24/2025
 * COSI 21A PA0
 */
package main;

public class Job {
    boolean isComplete;
    int floor;
    Person person;

    /**
     * This constructor accepts a Person object(intended for who is associated with the job) and an integer representing
     * their desired floor.
     * @param person
     * @param floor
     */
	public Job(Person person, int floor)
    {
        this.floor = floor;
        this.person = person;
    }

    /**
     * This method returns the floor of the given job.
     * @return
     */
    public int getJobFloor()
    {
        return floor;
    }

    /**
     * This method overrides the toString() method and returns a string representing the Person object and
     * floor number associated with the job.
     */
    @Override
    public String toString()
    {
        return person.toString() + " " + "requests floor " + floor;
    }

}