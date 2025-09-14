package main;
/**
* <This class initializes the floor and assigns locations after movement>
* Known Bugs: < When i intiallly was writing i assumed you could move floor to floor so some code might have remnants of that>
*
* @author Shemarh Stewart
* shemarh@brandeis.edu
* 9/9/2025
* COSI 21A PA0
*/
public class Floor {
	private int floornumber;

/**
 * This method initializes a floor with a given number
 * @param floornumber
 */
	public Floor(int floornumber){
		this.floornumber= floornumber;

}
/**
 * This method changes the location of given Person to floornumber
 * @param person
 */
	public void enterFloor(Person person) {
	if(floornumber> 0){
		
		person.location = Constants.PERSON_IN_FLOOR + floornumber;
	}
	if(floornumber== 0){
		
		person.location = Constants.PERSON_IN_LOBBY;
	}}
	/**
	 * This metthod tells what floornumber floor is
	 * @return String
	 */
public String toString(){
		if(floornumber== 0){
			return "This is the Lobby";
		}

		return "This is Floor "+ floornumber;
	}
}
