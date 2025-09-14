package main;

import main.Constants;

public class Elevator {

	/**
	 * This specifies the number of people that can be brought to their floors by an
	 * Elevator
	 * instance at any given time.
	 * <p>
	 * DO NOT REMOVE THIS FIELD
	 * </p>
	 * <p>
	 * You may edit it. But keep it public.
	 * </p>
	 */
	/**
	 * Elevator processes Jobs in FCFS order with a capacity limit per batch.
	 * Only this class prints during processJob(), per spec.
	 * Known Bugs: None
	 *
	 * @author Yuehan Yang
	 *         <Yuehanyang@brandeis.edu>
	 *         <September 9, 2025>
	 *         COSI 21A PA0
	 */
	public static int maxOccupants = 3;

	private final Building building;

	private Job[] queue;
	private int head;
	private int tail;
	private int size;

	private int currentFloor;

	/**
	 * Constructs an elevator attached to a specific building.
	 * Initially starts at the lobby (floor 0).
	 */
	public Elevator(Building building) {
		this.building = building;
		this.queue = new Job[8];
		this.head = 0;
		this.tail = 0;
		this.size = 0;
		this.currentFloor = 0; // Lobby
	}

	/**
	 * Creates a new job for a person to go to a target floor
	 * and enqueues it for future processing.
	 */
	public void createJob(Person person, int floor) {
		enqueue(new Job(person, floor));
	}

	/**
	 * Processes all jobs currently in the queue until empty.
	 * Jobs are executed in FCFS order, in batches of at most maxOccupants.
	 * Elevator always returns to lobby between batches if not already there.
	 */
	public void processAllJobs() {
		while (size > 0) {
			// Return to lobby before picking up a new batch
			if (currentFloor != 0) {
				processJob(new Job(null, 0));
			}

			int batchCount = Math.min(maxOccupants, size);
			Job[] batch = new Job[batchCount];
			for (int i = 0; i < batchCount; i++) {
				batch[i] = dequeue();
			}

			for (int i = 0; i < batchCount; i++) {
				processJob(batch[i]);
			}

			// Return to lobby again if more jobs remain
			if (size > 0 && currentFloor != 0) {
				processJob(new Job(null, 0));
			}
		}
	}

	/**
	 * Processes a single job:
	 * - Prints elevator movement
	 * - Moves to the target floor
	 * - Drops off the person (if any) at the floor
	 */
	public void processJob(Job job) {
		printLocation();

		int target = job.getTargetFloor();

		// Move upwards
		while (currentFloor < target) {
			currentFloor++;
			printLocation();
		}
		// Move downwards
		while (currentFloor > target) {
			currentFloor--;
			printLocation();
		}

		// Drop off the person if this is a real request
		if (job.getPerson() != null && target > 0) {
			exit(job.getPerson(), target);
		}
	}

	/**
	 * Removes a person from the elevator and places them on the given floor.
	 */
	public void exit(Person person, int floor) {
		if (person == null)
			return;
		person.setOnFloor(floor);
		building.enterFloor(person, floor);
	}

	/**
	 * Prints the elevator’s current location (lobby or floor number).
	 * Uses Constants for grading consistency.
	 */
	private void printLocation() {
		if (currentFloor == 0) {
			System.out.println(Constants.ELEVATOR_AT_LOBBY);
		} else {
			System.out.println(Constants.ELEVATOR_AT_FLOOR + currentFloor);
		}
	}

	/** Adds a job to the queue */
	private void enqueue(Job job) {
		if (size == queue.length)
			grow();
		queue[tail] = job;
		tail = (tail + 1) % queue.length;
		size++;
	}

	/** Removes and returns the next job in the queue */
	private Job dequeue() {
		if (size == 0)
			return null;
		Job j = queue[head];
		queue[head] = null;
		head = (head + 1) % queue.length;
		size--;
		return j;
	}

	/** Doubles the size of the job queue when capacity is reached */
	private void grow() {
		Job[] n = new Job[queue.length * 2];
		for (int i = 0; i < size; i++) {
			n[i] = queue[(head + i) % queue.length];
		}
		queue = n;
		head = 0;
		tail = size;
	}

	/** Returns the elevator’s current floor */
	public int getCurrentFloor() {
		return currentFloor;
	}

	@Override
	public String toString() {
		return "Elevator at " + (currentFloor == 0 ? "Lobby" : ("floor " + currentFloor))
				+ " with " + size + " job(s) queued";
	}
}