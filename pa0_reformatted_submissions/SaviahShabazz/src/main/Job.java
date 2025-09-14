package main;

public class Job {
	private Person person;
    private int floor;

    /**
     * Constructs a job for a person going to a floor
     * @param person - the pperson making the request
     * @param floor - the chosen floor
     */
    public Job(Person person,int floor){
        this.person = person;
        this.floor= floor;
    }

    /**
     * @return person for the job
     */
    public Person getPerson(){
        return person;
    }

    /**
     * @return the floor for the job
     * @return
     */
    public int  getFloor(){
        return floor;
    }

    /**
     * @return string description of job
     */
    public String toString(){
        return "Job: " + person + " Floor:" + floor;
    }


}