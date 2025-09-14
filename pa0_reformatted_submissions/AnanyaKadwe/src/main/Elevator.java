/**
 * Represents an Elevator in the building, processing jobs and transporting people.
 * Known Bugs: None
 * @author Ananya Kadwe
 * ananyakadwe@brandeis.edu
 * September 9, 2025
 * COSI 21A PA0
 */

 package main;

 public class Elevator {
 
     /**
      * This specifies the number of people that can be brought to their floors by an Elevator 
      * instance at any given time. 
      * <p>DO NOT REMOVE THIS FIELD</p>
      * <p>You may edit it. But keep it public.</p>
      */
     public static int maxOccupants = 3;
     private Job[] jobs; 
     private int jobCount; 
     private int currentFloor; 
     private Building building;
     
     /**
      * Constructs an Elevator starting at the lobby.
      */
     public Elevator(Building building) {
         this.jobs = new Job[10]; 
         this.jobCount = 0;
         this.currentFloor = 0;
         this.building = building;
     }
 
     /**
      * Creates a new job and adds it to the queue.
      * @param person      The person requesting the elevator.
      * @param floor       The floor they wish to go to.
      */
     public void createJob(Person person, int floor) {
         if (jobCount >= jobs.length) {
             expandJobCapacity();
         }
         jobs[jobCount++] = new Job(person, floor);
     }
 
     /**
      * Expands the capacity of the jobs array when it is full.
      */
     private void expandJobCapacity() {
         Job[] newJobs = new Job[jobs.length * 2];
         System.arraycopy(jobs, 0, newJobs, 0, jobs.length);
         jobs = newJobs;
     }
     
     /**
      * Processes all jobs in the queue.
      */
     public void processAllJobs() {
         while (jobCount > 0) {
             for (int i = 0; i < maxOccupants && jobCount > 0; i++) {
                 Job job = jobs[0]; 
                 processJob(job);
                 removeJob();
             }
 
             if (jobCount > 0) {
                 moveToFloor(0); 
             }
         }
     }
     
     /**
      * Processes a single job, moving the elevator and updating the person’s location.
      * @param job The job to process.
      */
     public void processJob(Job job) {
         moveToFloor(job.getTargetFloor());
         exit(job.getPerson(), currentFloor);
     }
     
     /**
      * Removes the first job from the queue.
      */
     private void removeJob() {
         for (int i = 1; i < jobCount; i++) {
             jobs[i - 1] = jobs[i];
         }
         jobs[--jobCount] = null; 
     }
 
     /**
      * Moves the elevator to a specific floor, printing its movement.
      * @param targetFloor The floor to move to.
      */
     private void moveToFloor(int targetFloor) {
         if (currentFloor == 0) {
             System.out.println("Elevator at Lobby");
         } else {
             System.out.println("Elevator at floor " + currentFloor);
         }
         while (currentFloor != targetFloor) { 
             if (currentFloor < targetFloor) {
                 currentFloor++;
             } else {
                 currentFloor--;
             }
             if (currentFloor == 0) {
                 System.out.println("Elevator at Lobby");
             } else {
                 System.out.println("Elevator at floor " + currentFloor);
             }
         }
     }
 
     public void exit(Person person, int floor)  {
         building.enterFloor(person, floor);
     }
 
     /**
      * Provides a string representation of the Elevator's current state.
     * Includes the current floor, maximum occupants, number of pending jobs,
     * and a list of all pending jobs (if any).
     * @return A string describing the current state of the Elevator.
      */
     @Override
     public String toString() {
         String result = "Elevator:\n";
         result += "Current Floor: " + (currentFloor == 0 ? "Lobby" : currentFloor) + "\n";
         result += "Max Occupants: " + maxOccupants + "\n";
         result += "Pending Jobs: " + jobCount + "\n";
         return result;
     }
 } 