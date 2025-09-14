/**
 * The Elevator class constructs an Elevator with an integer representing the number of floors in the building and an array of Floors.
 * This method constructs the createJob(Person person, int floor), processAllJobs(), processJob(Job job), exit(Person person, int floor),
 * callElevator(), elevatorCalled(), resize(), getOccupants(), getCurrentFloor(), and toString() methods. 
 * Known Bugs: None.
 * 
 * Liam Rittenburg
 * liamrittenburg@brandeis.edu
 * 01/24/2025
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
	Person[] personQue = new Person[10];
	int occupantIndex = 0;
	int QueIndex = 0;
	int jobIndex = 0;
	int currentFloor;
	Person[] currentOccupants = new Person[maxOccupants];
	Floor[] floors;
	boolean elevatorCalled;

	/**
	 * This constructor constructs an elevator with a current floor of 0 (lobby), and sets floors equals to the floors passed in as an argument.
	 * @param numFloors
	 * @param floors
	 */
	public Elevator(int numFloors, Floor[] floors)
	{
		currentFloor = 0;
		this.floors = floors;
	}

	/**
	 * This method delegates whether a person enters the elevator, is waiting to enter the elevator because it was full in the
	 * lobby, or if the elevator is empty but not in the lobby (needs to be called).
	 * @param person
	 * @param floor
	 */
	public void createJob(Person person, int floor) {
		if(person.isEligible() && currentFloor == 0 && occupantIndex < maxOccupants)
		{
			currentOccupants[occupantIndex] = person;
			occupantIndex++;
		}
		else if((person.isEligible() && occupantIndex >= maxOccupants))
		{
			personQue[QueIndex] = person;
			QueIndex++;
			resize();
		}
		else if(currentOccupants[occupantIndex] == null && personQue[QueIndex] == null && currentFloor > 0)
		{
			callElevator();
			currentOccupants[occupantIndex] = person;
			occupantIndex++;
		}
	}
	
	/**
	 * This method delegates the order jobs are processed in, ensuring first come first serve service.
	 */
	public void processAllJobs() {
		QueIndex = 0;
		while(!(personQue[QueIndex] == null) || (occupantIndex < maxOccupants && (!(currentOccupants[occupantIndex] == null))))
		{
			if ((jobIndex < maxOccupants && (currentOccupants[jobIndex] == null)) && personQue[QueIndex] != null)
			{
				int i = 0;
				while(i <= QueIndex && personQue[QueIndex] != null && jobIndex < maxOccupants)
				{
					currentOccupants[i] = personQue[i];
					i++;
					QueIndex++;
					resize();
				}
			}
			if (occupantIndex < maxOccupants && (currentOccupants[occupantIndex] != null))
			{
				processJob((currentOccupants[jobIndex]).getJob());

			}
			else if(jobIndex == maxOccupants)
			{
				processJob(null); // calls elevator to lobby

				jobIndex = 0;
				occupantIndex = 0;
				while(personQue[QueIndex] != null && occupantIndex < maxOccupants)
				{
					currentOccupants[occupantIndex] = personQue[QueIndex];
					QueIndex++;
					occupantIndex++;
					resize();
				}
				occupantIndex = 0;
			}
		}
		personQue = new Person[10];
		occupantIndex = 0;
		QueIndex = 0;
		jobIndex = 0;
	}
	
	/**
	 * This method processes a singular job. If a null job is passed in, the elevator is returned to the lobby.
	 * @param job
	 */
	public void processJob(Job job)
	{
		if (job != null)
		{
			if(!elevatorCalled())
			{
				jobIndex++;
				int destination = job.getJobFloor();
				while (currentFloor != destination)
				{
					if(currentFloor > 0)
					{
						System.out.println("Elevator at floor " + currentFloor);
					}
					else if (currentFloor == 0)
					{
						System.out.println("Elevator at Lobby");
					}
					if(currentFloor < destination)
					{
						currentFloor++;
					}
					else if (currentFloor > destination)
					{
						currentFloor--;
					}
				}
				if (currentFloor == destination)
				{
					System.out.println("Elevator at floor " + currentFloor);
					exit(currentOccupants[occupantIndex], currentFloor); // double check this person call
				}
			}
			else
			{
				while (currentFloor > 0)
				{
					System.out.println("Elevator at floor " + currentFloor);
					currentFloor--;
				}
				System.out.println("Elevator at Lobby");
				elevatorCalled = false;
				jobIndex = 0;
			}
		}
		else if(job == null)
		{
			while (currentFloor > 0)
			{
				System.out.println("Elevator at floor " + currentFloor);
				currentFloor--;
			}
			System.out.println("Elevator at Lobby");
		}
	}
	
	/**
	 * This method calls the enterFloor(Person person) method to move the Person object onto their desired floor, and
	 * removes that person from the elevator.
	 * @param person
	 * @param floor
	 */
	public void exit(Person person, int floor) {
		(floors[floor]).enterFloor(person);
		currentOccupants[occupantIndex] = null;
		occupantIndex++;
	}

	/**
	 * This method changes the elevatorCalled variable to true, representing there is an active call of the elevator
	 * to the lobby.
	 */
	public void callElevator()
	{
		elevatorCalled = true;
	}

	/**
	 * This method returns whether the elevatorCalled variable, representing whether the elevator is currently being called
	 * to the lobby.
	 * @return
	 */
	public boolean elevatorCalled()
	{
		return elevatorCalled;
	}

	/**
	 * This method checks if there is room to add more elements to the array representing
	 * the queue of jobs. If there is not enough room, the size of the array is doubled.
	 */
	public void resize()
	{
		if(QueIndex >= personQue.length - 1)
		{
			Person[] tmp = new Person[personQue.length * 2];
			for(int i = 0; i < personQue.length; i++)
			{
				tmp[i] = personQue[i];
			}
			personQue = tmp;
		}
	}

	/**
	 * This method returns an array representing the Person objects currently being serviced in the elevator.
	 * @return
	 */
	public Person[] getOccupants()
	{
		return this.currentOccupants;
	}

	/**
	 * This method returns the current floor of the elevator.
	 * @return
	 */
	public int getCurrentFloor()
	{
		return this.currentFloor;
	}

	/**
	 * This method overrides the toString() method, and returns a string indicating which floor the elevator is on.
	 */
	@Override
	public String toString()
	{
		return "This Elevator is at floor " + currentFloor; 
	}

}