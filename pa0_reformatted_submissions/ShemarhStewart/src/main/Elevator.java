package main;
/**
* <This class initializes the Elevator and allows for movement of the people from lobby to designated floor>
* Known Bugs: < Sometimes Elevator in lobby doesnt print out>
*
* @author Shemarh Stewart
* shemarh@brandeis.edu
* 9/9/2025
* COSI 21A PA0
*/
public class Elevator {

	/**
	 * This specifies the number of people that can be brought to their floors by an Elevator 
	 * instance at any given time. 
	 * <p>DO NOT REMOVE THIS FIELD</p>
	 * <p>You may edit it. But keep it public.</p>
	 */
	private Building building;
	private Job[] jobqueue;
	public static int maxOccupants = 3;
	private int floor;
	private int jobcounter;
	
	/**
	 * This method initializes a Elevator for the Building Class
	 * @param building
	 */
	public Elevator(Building building){
		this.building= building;
		this.floor =0;
		jobqueue= new Job[10];
		jobcounter=0;
}

/**
 * This method creates and adds a Job class to an array using the paramaters
 * @param person
 * @param floor
 */

	public void createJob(Person person, int floor) {
		
		if (jobcounter == jobqueue.length) {
            
            Job[] newQueue = new Job[jobqueue.length * 2];
            for (int i = 0; i < jobcounter; i++) {
                newQueue[i] = jobqueue[i];
            }
            jobqueue = newQueue;
        }
        jobqueue[jobcounter] = new Job(person, floor);
        jobcounter++;
    }
	
	

/**
 * This method uses the following method to loop through the job array and clear it entirely this array is used through the Building class
 */

	public void processAllJobs() {
		
		while(jobcounter>0){
			if(floor!=0 ){
				processJob(new Job(null, 0));
		}
		int activejobs=0;
		while(activejobs <= maxOccupants && jobcounter> 0){
			Job jobinprogress= jobqueue[0];
			processJob(jobinprogress);
			for(int i =1; i< jobcounter;i++){
				jobqueue[i-1]= jobqueue[i];
			}
			
			jobcounter--;
			activejobs++;
	}}
		}
		
	
		/**
		 * This method uses the Job class and takes its components to process said job and move elevator and Person location
		 * @param job
		 */

	public void processJob(Job job) {
		 int targetfloor = job.getLocation();
    

    if (floor == 0) {
        System.out.println("Elevator at Lobby");
    } else {
        System.out.println("Elevator at floor " + floor);
    }
    
    while (floor != targetfloor) {
      
        if (floor < targetfloor) {
            floor++;
        } else {
            floor--;
        }
        
       
        if (floor == 0) {
            System.out.println("Elevator at Lobby");
        } else {
            System.out.println("Elevator at floor " + floor);
        }
    }
    
     if (job.getperson() != null) {
        exit(job.getperson(), targetfloor);
    
   
    }

  
}



	/**
	 * This method takes the parameters to change the location of Person to given floor
	 * @param person
	 * @param floor
	 */
	
	public void exit(Person person, int floor) {
		building.enterFloor(person,floor);

	}

	public String toString(){
		return "Elevator at floor " + floor;
	}



}