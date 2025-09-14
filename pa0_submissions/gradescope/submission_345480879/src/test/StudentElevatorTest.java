package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import main.Floor;
import main.Elevator;
import main.*;

class StudentElevatorTest {

	/**
	 * Tests the elevators main task to process all the jobs, including when there are more people than 
	 * the maximum occupancy of the elevator. 
	 */
	@Test
	void testProcessJobs() {
		Floor[] floors = new Floor[20];
		for(int i = 1; i <= 20; i++) {
			floors[i-1] = new Floor(10, i);
		}
		Elevator elevator = new Elevator(floors);
		Person testOne = new Person("Jeff", "Jefferson");
		Person testTwo = new Person("John", "Johnson");
		Person testThree = new Person("ABC", "DEF");
		Person testFour = new Person("GHI", "JKL");
		Person testFive = new Person("Going", "Backwards");
		elevator.createJob(testOne, 4);
		elevator.createJob(testTwo, 6);
		elevator.createJob(testFive, 2);
		elevator.createJob(testThree, 13);
		elevator.createJob(testFour, 18);
		elevator.createJob(null, 0);
		assertEquals(elevator.toString(), "Elevator going up to 20 floors with 5 requests to complete");
		elevator.processAllJobs();
		assertEquals(elevator.toString(), "Elevator going up to 20 floors with 0 requests to complete");
		assertEquals(floors[5].onFloor[0], testTwo);
		assertEquals(floors[1].onFloor[0], testFive);
		assertEquals(floors[3].onFloor[0], testOne);
		assertEquals(floors[12].onFloor[0], testThree);
		assertEquals(floors[17].onFloor[0], testFour);
	}



}
