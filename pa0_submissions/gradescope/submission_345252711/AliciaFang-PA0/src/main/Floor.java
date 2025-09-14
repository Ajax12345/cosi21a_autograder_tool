package main;

/**
 * Represents a floor in a building that can hold people.
 * Each floor has an index (0 = Lobby) and list of the people currently on it.
 * Known Bugs: None
 * 
 * @author Alicia Fang
 * <aliciafang@brandeis.edu>
 * <September 7, 2025>
 * COSI 21A PA0
 */
public class Floor{
	private final int index;
	private Person[] riders;
    private int size;
	
	/**
     * Constructs a floor with its index and 
     * initializes storage for people on this floor.
     *
     * @param index the index of the floor (0 = Lobby)
     */
    public Floor(int index) {
        this.index = index;
        this.riders = new Person[1000];
        this.size = 0;
    }

    /**
     * Adds a person to this floor and updates their location 
     * if index is not 0 (floor is not the Lobby).
     * Expands storage if necessary.
     *
     * @param person the person arriving ones floor
     */
	public void enterFloor(Person person) {
        ensureCapacity(size + 1);
        if(index != 0){
            person.setLocation(Constants.PERSON_IN_FLOOR + index);
        }
        riders[size++] = person;
	}

    /**
     * Ensures that the array has enough capacity to store
     * the given number of riders. If not, the array is doubled in size.
     *
     * @param needed the minimum capacity required
     */
    private void ensureCapacity(int needed) {
        if (needed <= riders.length) {
            return;
        }
        Person[] bigger = new Person[riders.length * 2];
        for (int i = 0; i < size; i++) {
            bigger[i] = riders[i];
        }
        riders = bigger;
    }

    /**
     * Returns a string information of the floor, including its floor name 
     * and a list of people currently on it.
     *
     * @return a string in the format "Floor index (or Lobby): person1, person2, ..."
     */
    @Override
    public String toString() {
        String floorInfo = " : ";
        if(index == 0){
            floorInfo = "Lobby" + floorInfo;
        }
        else{
            floorInfo = "Floor " + index + floorInfo;
        }
        for (int i = 0; i < size; i++) {
            floorInfo += riders[i].toString();
            if (i + 1 < size){
                floorInfo += ", ";
            }
        }
        return floorInfo;
    }
}
