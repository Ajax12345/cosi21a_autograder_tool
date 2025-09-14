package main;

/**
 * Represents a single elevator job request.
 * Stores a person, their target floor, and the building they are in.
 * 
 * Known Bugs: None
 *
 * @author Arthur Yu
 * <ziyi.yu@brandeis.edu>
 * <September 9, 2025>
 * COSI 21A PA0
 */
public class Job {
	private Person person;
	private int floor;
    private Building building;

    //person contains the information(firstName, lastName)
    //floor is the target floor
	/**
	 * Constructs a new Job with the given person, target floor, and building.
	 * 
	 * @param person the person requesting elevator service
	 * @param floor the target floor number
	 * @param building the building this job belongs to
	 */
	public Job(Person person, int floor, Building building) {
		this.person = person;
		this.floor = floor;
        this.building = building;
        
	}

	/**
	 * Returns the person associated with this job.
	 * 
	 * @return the person requesting service
	 */
	public Person getPerson() {
		return person;
	}

	/**
	 * Returns the target floor for this job.
	 * 
	 * @return the target floor number
	 */
	public int getTargetFloor() {
		return floor;
	}
	
	/**
	 * Returns the building this job belongs to.
	 * 
	 * @return the building reference
	 */
    public Building getBuilding() {
        return building;
    }
    public String toString() {
        return person.getFirstName() + " " + person.getLastName() + " wants to go to floor " + floor;
    }
}