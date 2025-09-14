package main;
/**
* <This class initializes an array of jobs and allows for movement through job processing>
* Known Bugs: < “None”>
*
* @author Shemarh Stewart
* shemarh@brandeis.edu
* 9/9/2025
* COSI 21A PA0
*/
public class Job {
    private int floor;
    private Person person;
/**
 * This method initializes a job
 * @param person
 * @param floor
 */
	public Job(Person person,int floor){
	    this.floor= floor;
        this.person= person;

}
/**
 * This method returns what floor the job should leave said Job intended person on
 * @return int floor
 */
public int getLocation(){
    return floor;
}
/**
 * This method returns Jobs intended person
 * @return Person
 */
public Person getperson(){
    return person;
}

/**
 * This method Prints out who is calling for a job and to where
 * @return String
 */
public String toString(){
		return person.toString() + " going to floor"+ floor;
}}