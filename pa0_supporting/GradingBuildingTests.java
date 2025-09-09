package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Queue;
import java.util.Random;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import main.Building;
import main.Elevator;
import main.Person;

/**
 * Tests the functionality of a Building. Used for grading (13 tests). 
 * <p>These tests are based off of string comparisons (case-insensitive) so be sure to check the reason that tests failed.</p>
 * <p>e.g. A student may return "Elevator in lobby" instead of "Elevator at lobby"</p>
 * <p>These tests will generate text files of the students' output with names corresponding to the test names.</p>
 *  
 * @author cs21a 
 * @version 1.1 
 * 8/19/2023
 */
class GradingBuildingTests {
	
	/**
	 * Enum to hold the 3 different capacities used for elevators in the tests.
	 * 
	 * @author cs21a 
	 * @version 1.0
	 * 1/14/2020
	 */
	private static enum ElevatorCapacity {

		// 3 supported sizes: small, medium, large 
		SMALL(1),
		MEDIUM(5),
		LARGE(12);
		
		/**
		 * Value of this capacity
		 */
		private final int VALUE;
		
		/**
		 * Constructs a new capacity with a specific value. 
		 * @param value a value to initialize the capacity
		 */
		private ElevatorCapacity(int value) {
			this.VALUE = value;
		}
	}
	
	/**
	 * Testing utility used by these tests ===> output files will be generated
	 */
	private static GenericConsoleTester tester = new GenericConsoleTester(true);

	/**
	 * Will hold students' actual output for each test
	 */
	private static Queue<String> actual = null;

	/**
	 * Total no. of lines of students' actual output 
	 */
	private static int totalLinesOfActual = 0;

	/**
	 * Pseudorandom number generator used by the tests  
	 */
	private static Random rng;

	/**
	 * Person objects used in most of the tests. Other tests create their own "people" arrays
	 */
	private Person[] people;

	/**
	 * Building used in all tests 
	 */
	private Building building;

	/**
	 * Sets up testing utility 
	 */
	@BeforeAll
	static void setUp() {
		tester.storeOldStreams();
	}

	/**
	 * Cleans up testing utility 
	 */
	@AfterAll
	static void cleanUp() {
		tester.cleanUpStreamsAndFiles();
	}

	/**
	 * Sets up people before each test 
	 */
	@BeforeEach
	void preTest() {
		rng = new Random(1); // fix seed before each test
		people = new Person[20];
		for (int i = 0; i < people.length; i++) {
			people[i] = new Person("","");
		}
	}

	/**
	 * Checks if people[incMin...incMax] are all in the elevator by checking their getLocation() method 
	 * @param incMin index of first person to check
	 * @param incMax index of last person to check
	 * @see #people
	 * @see #checkInElevatorQueue(int, int, Person[])
	 */
	private void checkInElevatorQueue(int incMin, int incMax) {
		checkInElevatorQueue(incMin, incMax, people);
	}

	/**
	 * Checks if group[incMin...incMax] are all in the elevator by checking their getLocation() method 
	 * @param group a array of Person objects to test
	 * @param incMin index of first person to check
	 * @param incMax index of last person to check
	 * <p><b>Note:</b> If you wish to check if a single person is in the elevator set incMin equal to incMax.</p>
	 */
	private void checkInElevatorQueue(int incMin, int incMax, Person[] group) {
		for (int i = incMin; i <= incMax; i++) {
			assertEquals(Constants.PERSON_WAITING_TO_BE_SERVICED.toLowerCase(), group[i].getLocation().toLowerCase().trim(), "Person should be in elevator queue.");
		}
	}

	/**
	 * Checks if people[incMin] reached destinations[0], people[incMin + 1] reached destinations[1], ... ,
	 * people[incMax] reached destinations[destinations.length - 1]. 
	 * @param incMin index of first person to check
	 * @param incMax index of last person to check
	 * @param destinations destinations of people[incMin...incMax]
	 * @see #people
	 * @see #checkReachedFloor(int, int, Person[], int...)
	 */
	private void checkReachedFloor(int incMin, int incMax, int...destinations) {
		checkReachedFloor(incMin, incMax, people, destinations);
	}

	/**
	 * Checks if group[incMin] reached destinations[0], group[incMin + 1] reached destinations[1], ... ,
	 * group[incMax] reached destinations[destinations.length - 1]. 
	 * @param incMin index of first person to check
	 * @param incMax index of last person to check
	 * @param group array of people to check
	 * @param destinations destinations of group[incMin...incMax]
	 */
	private void checkReachedFloor(int incMin, int incMax, Person[] group, int...destinations) { 
		if (destinations.length != incMax - incMin + 1) { // no. of destinations must correspond to range of indices being tested
			throw new IllegalArgumentException("Testing " + (incMax - incMin + 1) + " people, but only " + destinations.length + " destinations provided.");
		}
		for (int i = incMin; i <= incMax; i++) {
			assertEquals(Constants.PERSON_IN_FLOOR.toLowerCase() + destinations[i - incMin], group[i].getLocation().toLowerCase().trim(), "Person should be on floor " + destinations[i - incMin]);
		}
	}

	/**
	 * Checks to see if the element at the front of the actual queue is on the expected floor. 
	 * @param exp expected floor, 0 for the lobby, or -1 if it's the end of the output  
	 */
	private static void checkNext(int exp) {
		if (exp == -1) { // this means the actual output should be empty 
			assertTrue(actual.isEmpty(), "Actual output contained " +  actual.size() + " extra lines, check generated text file corresponding to this test.");
		}
		else if (actual.isEmpty()) { // if exp != -1 then the actual output shouldn't be empty 
			fail("Output ended too early.");
		}
		else { // actual isn't empty and exp != -1 ==> check if the elevator is at the correct location
			String floorStr = "";
			if (exp == 0) {
				floorStr = Constants.ELEVATOR_AT_LOBBY.toLowerCase();
			}
			else {
				floorStr = Constants.ELEVATOR_AT_FLOOR.toLowerCase() + exp;
			}
			// if they aren't equal, use totalLinesOfActual to provide an approximate line # of the problem (approximate b/c of ws being removed by GenericConsoleTester)
			assertEquals(floorStr, actual.poll().toLowerCase().trim(), "Contents differed at line ~ " + (totalLinesOfActual - actual.size()));
		}
	}

	/**
	 * Checks to see if the elevator hits some sequence of stops using checkNext() 
	 * @param stops variable integer holding a sequence of stops 
	 * @see #checkNext(int)
	 */
	private static void checkFloorVisits(int...stops) {
		for (int i = 0; i < stops.length; i++) { // for each stop: check if elevator was brought  up and brought down (except in case of last stop: see below)

			// if first stop, then we will move up from 0, otherwise move up from the previous stop
			int curr = 0;
			if (i > 0) {
				curr = stops[i - 1];
			}
			
			// check if location was printed at start of processJob() (at start of job processing)
			checkNext(curr);
			// loop until location is at the next stop desired whether it be higher or lower 
			while (curr < stops[i]) {
				curr++;
				checkNext(curr);
			}
			while (curr > stops[i]) {
				curr--;
				checkNext(curr);
			}
				
			/* Based on implementation,some students may leave the elevator at the last floor visited. FOr this reason, after visiting
			 * the last stop the actual queue should not contain entries for the elevator going from the last visited floor back to the 
			 * lobby. 
			 * To implement this: after bringing person to floor, check if actual is empty on the last stop. if it is, return (causes
			 * test to pass). 
			 * Otherwise, loop back down and check if elevator is brought down properly (this should be done in most cases)
			 */
			if (i == stops.length - 1) { 
				if (actual.isEmpty()) {
					return;
				}
				
				for (int j = stops[i]; j >= 0; j--) {
					checkNext(j);
				}
			}
		}
		checkNext(-1); // elevator should now be done (no more stops!) - see checkNext() JavaDoc
	}

	/**
	 * Puts everyone on the first floor of a building. 
	 */
	@ParameterizedTest
	@EnumSource(ElevatorCapacity.class)
	void testEveryOneOnFloor1(ElevatorCapacity cap) {
		Elevator.maxOccupants = cap.VALUE;
		tester.setUpOutStream("everyone_on_floor_1_" + cap.name() + "_test.txt");
		building = new Building(2); 
		// puts 10 people in elevator, processes them all onto floor 1 at once 
		for (int i = 0; i < people.length/2; i++) {  
			people[i].enterBuilding(building, 1);
		}
		checkInElevatorQueue(0, 9); // check that they're in elevator before processing them 
		building.startElevator();
		// puts 10 people in elevator 1 at a time and processes them. 
		for (int i = people.length/2; i < people.length; i++) {
			people[i].enterBuilding(building, 1);
			checkInElevatorQueue(i, i); // check person's in elevator 
			building.startElevator();
		}
		// all 20 people should have gone to floor 1 
		checkReachedFloor(0, 19, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1);
		actual = tester.getActual();
		totalLinesOfActual = actual.size();
		
		// check stops based on 3 possible elevator capacities 
		if (cap == ElevatorCapacity.SMALL) {
			checkFloorVisits(1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1);
		}
		else if (cap == ElevatorCapacity.MEDIUM) {
			checkFloorVisits(1,1,1,1,1,0,1,1,1,1,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1);
		}
		else {
			checkFloorVisits(1,1,1,1,1,1,1,1,1,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1);
		}
	}

	/**
	 * Puts everyone on a separate floor (to be processed all at once). 
	 */
	@ParameterizedTest
	@EnumSource(ElevatorCapacity.class)
	void test1OnEachFloor(ElevatorCapacity cap) {
		Elevator.maxOccupants = cap.VALUE;
		tester.setUpOutStream("1_on_each_floor_" + cap.name() + "_test.txt");
		building = new Building(10);
		// create jobs: 1st job is person to floor 10, 2nd job is person to floor 9, ... , 10th job is person to floor 1 
		for (int i = 9; i >= 0; i--) {
			people[i].enterBuilding(building, i + 1);
		}
		checkInElevatorQueue(0, 9); // test that all 10 people are in elevator first 
		building.startElevator();
		checkReachedFloor(0, 9, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10); // 1 person should be on each floor 
		actual = tester.getActual();
		totalLinesOfActual = actual.size();
		// check stops based on 3 possible elevator capacities 
		if (cap == ElevatorCapacity.SMALL) {
			checkFloorVisits(10,0,9,0,8,0,7,0,6,0,5,0,4,0,3,0,2,0,1);
		}
		else if (cap == ElevatorCapacity.MEDIUM) {
			checkFloorVisits(10,9,8,7,6,0,5,4,3,2,1);
		}
		else {
			checkFloorVisits(10,9,8,7,6,5,4,3,2,1);
		}
	}

	/**
	 * Puts multiple people on each floor (to be processed all at once). 
	 * <p>Floor placements: 1,4,3,4,5,5,5,2,4,4,5,4,3,4,3,5,3,3,2,5</p>
	 */
	@ParameterizedTest
	@EnumSource(ElevatorCapacity.class)
	void testMultiplePerFloor(ElevatorCapacity cap) {
		Elevator.maxOccupants = cap.VALUE;
		tester.setUpOutStream("multi_per_floor_" + cap.name() + "_test.txt");
		building = new Building(5);
		// create jobs: put people on random floors 
		for (int i = 0; i < people.length; i++) {
			int randFloor = rng.nextInt(5) + 1; 
			people[i].enterBuilding(building, randFloor);
		}
		checkInElevatorQueue(0, 9); // check everyone's in elevator before starting elevator 
		building.startElevator();
		actual = tester.getActual();
		totalLinesOfActual = actual.size();
		// based on placements in JavaDoc, put people on various floors in arrays except floor1 (only has 1 person = people[0])
		Person[] floor2 = {people[7], people[18]};
		Person[] floor3 = {people[2], people[12], people[14], people[16], people[17]};
		Person[] floor4 = {people[1], people[3], people[8], people[9], people[11], people[13]};
		Person[] floor5 = {people[4], people[5], people[6], people[10], people[15], people[19]};
		checkReachedFloor(0, 0, 1); // people[0] happens to be the first floor placement 
		// other checkReachedFloor() calls should be done based on the floor arrays 
		checkReachedFloor(0, 1, floor2, 2, 2);
		checkReachedFloor(0, 4, floor3, 3, 3, 3, 3, 3);
		checkReachedFloor(0, 5, floor4, 4, 4, 4, 4, 4, 4);
		checkReachedFloor(0, 5, floor5, 5, 5, 5, 5, 5, 5); 
		// check stops based on 3 possible elevator capacities 
		if (cap == ElevatorCapacity.SMALL) {
			checkFloorVisits(1,0,4,0,3,0,4,0,5,0,5,0,5,0,2,0,4,0,4,0,5,0,4,0,3,0,4,0,3,0,5,0,3,0,3,0,2,0,5);
		}
		else if (cap == ElevatorCapacity.MEDIUM) {
			checkFloorVisits(1,4,3,4,5,0,5,5,2,4,4,0,5,4,3,4,3,0,5,3,3,2,5);
		}
		else {
			checkFloorVisits(1,4,3,4,5,5,5,2,4,4,5,4,0,3,4,3,5,3,3,2,5);
		}
	}

	/**
	 * Runs an elevator on an empty building
	 */
	@Test
	void testEmptyBuilding() {
		Elevator.maxOccupants = ElevatorCapacity.MEDIUM.VALUE;
		tester.setUpOutStream("empty_building_test.txt");
		building = new Building(1);
		building.startElevator();
		actual = tester.getActual();
		totalLinesOfActual = actual.size();
		checkFloorVisits(); // output should be empty (elevator only prints things when it has jobs to process)
	}

	/**
	 * Tests to see if people can enter building on invalid floors
	 */
	@Test
	void testInvalidFloors() {
		Elevator.maxOccupants = ElevatorCapacity.MEDIUM.VALUE;
		building = new Building(2);
		assertFalse(people[0].enterBuilding(building, 0), "Person should not be boarding elevator if they are going to floor 0"); // can only go to floors 1, 2, ....
		assertEquals(Constants.PERSON_IN_LOBBY.toLowerCase(), people[0].getLocation().toLowerCase().trim(), "Person should not be in the Elevator.");
		assertFalse(people[0].enterBuilding(building, -2), "Person should not be going to a negative floor."); 
		assertEquals(Constants.PERSON_IN_LOBBY.toLowerCase(), people[0].getLocation().toLowerCase().trim(), "Person should not be in the Elevator.");
		assertFalse(people[0].enterBuilding(building, 3), "Person should not be going to a floor that the elevator can't service."); 
		assertEquals(Constants.PERSON_IN_LOBBY.toLowerCase(), people[0].getLocation().toLowerCase().trim(), "Person should not be in the Elevator.");
	}

	/**
	 * Tests adding many people to 1 floor to trigger resizing of Floor's internal people array.
	 * <p>Creates one job at a time to not trigger jobs array resizing</p>
	 */
	@Test
	void testManyPeopleOnAFloor() {
		Elevator.maxOccupants = ElevatorCapacity.MEDIUM.VALUE;
		tester.setUpOutStream("many_people_on_1_floor.txt");
		Person[] manyPeople = new Person[150]; // using more people to hopefully trigger FLoor's internal people array to resize 
		building = new Building(1);
		// create + process jobs one at a time: each person will be sent to floor 1 
		for (int i = 0; i < manyPeople.length; i++) { 
			manyPeople[i] = new Person("","");
			manyPeople[i].enterBuilding(building, 1);
			checkInElevatorQueue(i, i, manyPeople); // check that all of the people were put into elevator before being processed
			building.startElevator();
		}
		// check everyone was put onto the first floor 
		checkReachedFloor(0, 149, manyPeople, 1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1);
		actual = tester.getActual();
		totalLinesOfActual = actual.size();
		checkFloorVisits(1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1);
	}

	/**
	 * Tests adding many jobs to a building to trigger resizing of Building's internal jobs array.
	 * <p>Creates one job per floor to not trigger person array resizing</p>
	 */
	@Test
	void testManyJobsInBuilding() {
		Elevator.maxOccupants = ElevatorCapacity.MEDIUM.VALUE;
		tester.setUpOutStream("many_jobs_in_building_test.txt");
		Person[] manyPeople = new Person[150]; // using more people to hopefully create enough jobs to trigger Building's internal jobs array to resize
		building = new Building(150);
		// create jobs: put each person onto floor = person's index + 1 
		for (int i = 0; i < manyPeople.length; i++) { 
			manyPeople[i] = new Person("","");
			manyPeople[i].enterBuilding(building, i + 1);
		}
		checkInElevatorQueue(0, 149, manyPeople); // check that everyone's in the elevator before starting 
		building.startElevator(); 
		// check that each person has reached their destination floor (manyPeople[0] = floor 1, manyPeople[1] = floor 2, etc.)
		checkReachedFloor(0, 149, manyPeople, 1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,39,40,41,42,43,44,45,46,47,48,49,50,51,52,53,54,55,56,57,58,59,60,61,62,63,64,65,66,67,68,69,70,71,72,73,74,75,76,77,78,79,80,81,82,83,84,85,86,87,88,89,90,91,92,93,94,95,96,97,98,99,100,101,102,103,104,105,106,107,108,109,110,111,112,113,114,115,116,117,118,119,120,121,122,123,124,125,126,127,128,129,130,131,132,133,134,135,136,137,138,139,140,141,142,143,144,145,146,147,148,149,150);
		actual = tester.getActual();
		totalLinesOfActual = actual.size(); 
		checkFloorVisits(1,2,3,4,5,0,6,7,8,9,10,0,11,12,13,14,15,0,16,17,18,19,20,0,21,22,23,24,25,0,26,27,28,29,30,0,31,32,33,34,35,0,36,37,38,39,40,0,41,42,43,44,45,0,46,47,48,49,50,0,51,52,53,54,55,0,56,57,58,59,60,0,61,62,63,64,65,0,66,67,68,69,70,0,71,72,73,74,75,0,76,77,78,79,80,0,81,82,83,84,85,0,86,87,88,89,90,0,91,92,93,94,95,0,96,97,98,99,100,0,101,102,103,104,105,0,106,107,108,109,110,0,111,112,113,114,115,0,116,117,118,119,120,0,121,122,123,124,125,0,126,127,128,129,130,0,131,132,133,134,135,0,136,137,138,139,140,0,141,142,143,144,145,0,146,147,148,149,150);
	}

	/**
	 * Puts people on top 2 and bottom 2 floors (to be processed all at once).
	 */
	@ParameterizedTest
	@EnumSource(ElevatorCapacity.class)
	void testUpAndDownAlot(ElevatorCapacity cap) {
		Elevator.maxOccupants = cap.VALUE;
		tester.setUpOutStream("up_and_down_" + cap.name() + "_test.txt");
		building = new Building(10);
		// alternate placing people on floors: 1, 10, 2, 9 (5 times for 20 people)
		for (int i = 0; i < people.length; i++) {
			if (i % 4 == 0) {
				people[i].enterBuilding(building, 1);
			}
			else if (i % 4 == 1) {
				people[i].enterBuilding(building, 10);
			}
			else if (i % 4 == 2) {
				people[i].enterBuilding(building, 2);
			}
			else {
				people[i].enterBuilding(building, 9);
			}
		}
		checkInElevatorQueue(0, 19); // check that everyone's in elevator and then start 
		building.startElevator();
		// collect people placement per floor based on alternating placement 
		Person[] floor1 = {people[0], people[4], people[8], people[12], people[16]};
		Person[] floor2 = {people[2], people[6], people[10], people[14], people[18]};
		Person[] floor9 = {people[3], people[7], people[11], people[15], people[19]};
		Person[] floor10 = {people[1], people[5], people[9], people[13], people[17]};
		// check that people reached their floors 
		checkReachedFloor(0, 3, floor1, 1, 1, 1, 1);
		checkReachedFloor(0, 3, floor2, 2, 2, 2, 2);
		checkReachedFloor(0, 3, floor9, 9, 9, 9, 9);
		checkReachedFloor(0, 3, floor10, 10, 10, 10, 10);
		actual = tester.getActual();
		totalLinesOfActual = actual.size();
		// check stops based on 3 possible elevator capacities 
		if (cap == ElevatorCapacity.SMALL) {
			checkFloorVisits(1,0,10,0,2,0,9,0,1,0,10,0,2,0,9,0,1,0,10,0,2,0,9,0,1,0,10,0,2,0,9,0,1,0,10,0,2,0,9);
		}
		else if (cap == ElevatorCapacity.MEDIUM) {
			checkFloorVisits(1,10,2,9,1,0,10,2,9,1,10,0,2,9,1,10,2,0,9,1,10,2,9);
		}
		else {
			checkFloorVisits(1,10,2,9,1,10,2,9,1,10,2,9,0,1,10,2,9,1,10,2,9);
		}
	}

	/**
	 * First test that runs different numbers of jobs through the building. Only 4 job "phases" with the maximum number
	 * of jobs to process at a time being 15. 
	 */
	@ParameterizedTest
	@EnumSource(ElevatorCapacity.class)
	void testDiffJobCounts1(ElevatorCapacity cap) {
		Elevator.maxOccupants = cap.VALUE;
		tester.setUpOutStream("diff_job_counts_1_" + cap.name() + "_test.txt");
		Person[] somePeople = new Person[30]; // create a slightly larger set of 30 people 
		for (int i = 0; i < somePeople.length; i++) {
			somePeople[i] = new Person("","");
		}
		building = new Building(5);
		// phase 1: 5 single jobs 
		// process people one at a time onto floors 5, 4, 3, 2, 1
		for (int i = 0; i < 5; i++) {
			somePeople[i].enterBuilding(building, 5 - i);
			checkInElevatorQueue(i, i, somePeople); // check each person in elevator before starting it
			building.startElevator();
		}
		// phase 2: 5 jobs to be processed together 
		// process people onto floors 1,4,3,4,5
		int randFloor = -1;
		for (int i = 5; i < 10; i++) {
			randFloor  = rng.nextInt(5) + 1;
			somePeople[i].enterBuilding(building, randFloor);
		}
		checkInElevatorQueue(5, 9, somePeople); // check all people were in elevator before starting it 
		building.startElevator();
		// phase 3: 5 single jobs 
		// process people one at a time onto floors 
		for (int i = 10; i < 15; i++) {
			somePeople[i].enterBuilding(building, i - 9);
			checkInElevatorQueue(i, i, somePeople); // check each person in elevator before starting it 
			building.startElevator();
		}
		// phase 4: 15 jobs to be processed together
		// process people onto floors 5,5,2,4,4,5,4,3,4,3,5,3,3,2,5
		for (int i = 15; i < 30; i++) {
			randFloor  = rng.nextInt(5) + 1;
			somePeople[i].enterBuilding(building, randFloor);
		}
		checkInElevatorQueue(15, 29, somePeople); // check all people were in elevator before starting it 
		building.startElevator();

		// collect people by floor based on job creation loops above
		Person[] floor1 = {somePeople[4], somePeople[5], somePeople[10]};
		Person[] floor2 = {somePeople[3], somePeople[11], somePeople[17], somePeople[28]};
		Person[] floor3 = {somePeople[2], somePeople[7], somePeople[12], somePeople[22], somePeople[24], somePeople[26], somePeople[27]};
		Person[] floor4 = {somePeople[1], somePeople[6], somePeople[8], somePeople[13], somePeople[18], somePeople[19], somePeople[21], somePeople[23]};
		Person[] floor5 = {somePeople[0], somePeople[9], somePeople[14], somePeople[15], somePeople[16], somePeople[20], somePeople[25], somePeople[29]};
		// check that everyone reached their desired floor 
		checkReachedFloor(0, 2, floor1, 1, 1, 1);
		checkReachedFloor(0, 3, floor2, 2, 2, 2, 2);
		checkReachedFloor(0, 6, floor3, 3, 3, 3, 3, 3, 3, 3);
		checkReachedFloor(0, 7, floor4, 4, 4, 4, 4, 4, 4, 4, 4);
		checkReachedFloor(0, 7, floor5, 5, 5, 5, 5, 5, 5, 5, 5);
		actual = tester.getActual();
		totalLinesOfActual = actual.size();
		// check stops based on 3 possible elevator capacities 
		if (cap == ElevatorCapacity.SMALL) {
			checkFloorVisits(5,0,4,0,3,0,2,0,1,0,1,0,4,0,3,0,4,0,5,0,1,0,2,0,3,0,4,0,5,0,5,0,5,0,2,0,4,0,4,0,5,0,4,0,3,0,4,0,3,0,5,0,3,0,3,0,2,0,5);
		}
		else if (cap == ElevatorCapacity.MEDIUM) {
			checkFloorVisits(5, 0, 4, 0, 3, 0, 2, 0, 1, 0, 1, 4, 3, 4, 5, 0, 1, 0, 2, 0, 3, 0, 4, 0, 5, 0, 5,5,2,4,4, 0,5,4,3,4,3,0, 5,3,3,2,5);
		}
		else {
			checkFloorVisits(5, 0, 4, 0, 3, 0, 2, 0, 1, 0, 1, 4, 3, 4, 5, 0, 1, 0, 2, 0, 3, 0, 4, 0, 5, 0,5,5,2,4,4,5,4,3,4,3,5,3,0,3,2,5);
		}
	}

	/**
	 * Second test that runs different numbers of jobs through the building. Test phase 1: 1 job, Test Phase 2: 2 jobs,
	 * etc. up to Test Phase 10: 10 jobs processed at once. 
	 */
	@ParameterizedTest
	@EnumSource(ElevatorCapacity.class)
	void testDiffJobCounts2(ElevatorCapacity cap) {
		Elevator.maxOccupants = cap.VALUE;
		tester.setUpOutStream("diff_job_counts_2_" + cap.name() + "_test.txt");
		Person[] manyPeople = new Person[55]; // 1 + ... + 10 = 55
		for (int i = 0; i < manyPeople.length; i++) {
			manyPeople[i] = new Person("","");
		}
		building = new Building(5);

		// random floors: 1,4,3,4,5,5,5,2,4,4,5,4,3,4,3,5,3,3,2,5,2,3,1,5,5,5,4,4,3,3,1,5,1,2,4,1,5,1,1,5,1,1,3,3,3,3,1,4,1,3,3,4,2,1,2
		// First going to run 1 random job, then 2 random jobs together, then 3 together, ... , up to 10 together. 
		int p = 0; // will track index of current person entering the building  
		int randFloor = -1;
		for (int i = 1; i <= 10; i++) { // 10 job sets 
			for (int j = 0; j < i; j++) { // job set will be 1 job, 2 jobs, ..., 10 jobs 
				randFloor = rng.nextInt(5) + 1; 
				// generate random floor + increment to next person to be assigned
				manyPeople[p].enterBuilding(building, randFloor);
				p++;
			}
			// before starting elevator, check that all of the created jobs result in corr. people being in elevator
			checkInElevatorQueue(p - i, p - 1, manyPeople);  
			building.startElevator();
		}

		// collect people by floor (done with Python mapping script) 
		Person[] floor1 = {manyPeople[0], manyPeople[22], manyPeople[30], manyPeople[32], manyPeople[35], manyPeople[37], manyPeople[38], manyPeople[40], manyPeople[41], manyPeople[46], manyPeople[48], manyPeople[53]};
		Person[] floor2 = {manyPeople[7], manyPeople[18], manyPeople[20], manyPeople[33], manyPeople[52], manyPeople[54]};
		Person[] floor3 = {manyPeople[2], manyPeople[12], manyPeople[14], manyPeople[16], manyPeople[17], manyPeople[21], manyPeople[28], manyPeople[29], manyPeople[42], manyPeople[43], manyPeople[44], manyPeople[45], manyPeople[49], manyPeople[50]};
		Person[] floor4 = {manyPeople[1], manyPeople[3], manyPeople[8], manyPeople[9], manyPeople[11], manyPeople[13], manyPeople[26], manyPeople[27], manyPeople[34], manyPeople[47], manyPeople[51]};
		Person[] floor5 = {manyPeople[4], manyPeople[5], manyPeople[6], manyPeople[10], manyPeople[15], manyPeople[19], manyPeople[23], manyPeople[24], manyPeople[25], manyPeople[31], manyPeople[36], manyPeople[39]};
		// check that everyone reached their desired floor 
		checkReachedFloor(0, 11, floor1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1);
		checkReachedFloor(0, 5, floor2, 2, 2, 2, 2, 2, 2);
		checkReachedFloor(0, 13, floor3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3);
		checkReachedFloor(0, 10, floor4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4);
		checkReachedFloor(0, 11, floor5, 5, 5, 5, 5, 5, 5, 5, 5,5, 5, 5, 5);
		actual = tester.getActual();
		totalLinesOfActual = actual.size();
		// check stops based on 3 possible elevator capacities 
		if (cap == ElevatorCapacity.SMALL) {
			checkFloorVisits(1,0,4,0,3,0,4,0,5,0,5,0,5,0,2,0,4,0,4,0,5,0,4,0,3,0,4,0,3,0,5,0,3,0,3,0,2,0,5,0,2,0,3,0,1,0,5,0,5,0,5,0,4,0,4,0,3,0,3,0,1,0,5,0,1,0,2,0,4,0,1,0,5,0,1,0,1,0,5,0,1,0,1,0,3,0,3,0,3,0,3,0,1,0,4,0,1,0,3,0,3,0,4,0,2,0,1,0,2);
		}
		else if (cap == ElevatorCapacity.MEDIUM) {
			checkFloorVisits(1,0,4,3,0,4,5,5,0,5,2,4,4,0,5,4,3,4,3,0,5,3,3,2,5,0,2,0,3,1,5,5,5,0,4,4,0,3,3,1,5,1,0,2,4,1,0,5,1,1,5,1,0,1,3,3,3,0,3,1,4,1,3,0,3,4,2,1,2);
		}
		else {
			checkFloorVisits(1,0,4,3,0,4,5,5,0,5,2,4,4,0,5,4,3,4,3,0,5,3,3,2,5,2,0,3,1,5,5,5,4,4,0,3,3,1,5,1,2,4,1,0,5,1,1,5,1,1,3,3,3,0,3,1,4,1,3,3,4,2,1,2);
		}
	}
}
