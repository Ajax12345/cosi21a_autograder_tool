package main;

/**
* The job class has a job constructor that accepts a person object and an integer to reprsent the floor. These act as requests to be used in later parts of the program
*Known Bugs: none
* @author Vivian Kiilavirta Rangel
* akiilavirta@brandeis.edu
* <9-9-25>
* COSI 21A PA0
*/

public class Job {

    private Person person;
    private int floor;

    public Job(Person person, int floor){   

        this.person = person;
        this.floor = floor;

    }
    //ENTER ELEVATOR REUQEST IN BUILDING ADDS TO THE JOBS

    //Acts as a getter for a person object
    public Person getPerson(){
        return person;
    }

    //Acts as a getter for the floor int 
    public int getFloor(){
        return floor;
    }
    
    @Override
    public String toString(){

        return "Person is "  + getPerson() +  " Floor is " + getFloor();

    }
}