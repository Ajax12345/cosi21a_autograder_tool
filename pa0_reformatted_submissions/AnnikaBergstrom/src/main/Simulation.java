package main;

/**
 * This runs practice simulations of Building and Person objects
 * Known Bugs: None
 * 
 * @author Annika Bergstrom
 * annikabergstrom@brandeis.edu
 * September 09, 2025
 * COSI 21A PA0
 */
public class Simulation {

	public static void main(String[] args) {
		// providedExample1();
		// providedExample2();
		// getLocationTest();
		// startElevatorEmpty();
		// startElevatorStages();
		// startElevatorCase2();
		startElevatorCombination();
	}

	/**
	 * Tests the first provided example
	 */
	public static void providedExample1() {
		/** Desired Output
		 * Elevator at Lobby 
			Elevator at floor 1 
			Elevator at floor 2 
			Elevator at floor 2 
			Elevator at floor 3
		 */
		Building b1 = new Building(3);
		Person pa = new Person("Person", "A");
		Person pb = new Person("Person", "B");
		pa.enterBuilding(b1, 2);
		pb.enterBuilding(b1, 3);
		b1.startElevator();
	}
	
	/**
	 * Tests the second provided example
	 */
	public static void providedExample2() {
		/**
		 * Elevator at Lobby 
			Elevator at floor 1 
			Elevator at floor 2 
			Elevator at floor 2 
			Elevator at floor 3 
			Elevator at floor 3 
			Elevator at floor 3 
			Elevator at floor 2 
			Elevator at floor 1 
			Elevator at Lobby 
			Elevator at Lobby 
			Elevator at floor 1 
			Elevator at floor 2
		 */
		Building b1 = new Building(3);
		Person pa = new Person("Person", "A");
		Person pb = new Person("Person", "B");
		Person pc = new Person("Person", "C");
		Person pd = new Person("Person", "D");
		pa.enterBuilding(b1, 2);
		pb.enterBuilding(b1, 3);
		pc.enterBuilding(b1, 3);
		pd.enterBuilding(b1, 2);
		b1.startElevator();
	}

	/**
	 * Tests the getLocation Function
	 */
	public static void getLocationTest() {
		/** Desired Locations:
		 *  Person One : In Floor 1
			Person Two : In Floor 2
			Person Three : In Floor 3
			Person Four : In Floor 3
		 */
		Building b1 = new Building(3);
		Person p1 = new Person("Person", "One");
		Person p2 = new Person("Person", "Two");
		Person p3 = new Person("Person", "Three");
		Person p4 = new Person("Person", "Four");
		p1.enterBuilding(b1, 1);
		p2.enterBuilding(b1, 2);
		p3.enterBuilding(b1, 3);
		p4.enterBuilding(b1, 3);
		b1.startElevator();
		System.out.println(p1.toString() + " : " + p1.getLocation());
		System.out.println(p2.toString() + " : " + p2.getLocation());
		System.out.println(p3.toString() + " : " + p3.getLocation());
		System.out.println(p4.toString() + " : " + p4.getLocation());
	}

	/**
	 * If it works to start an empty elevator
	 */
	public static void startElevatorEmpty() {
		/**
		 * Should print nothing
		 */
		Building b1 = new Building(3);
		b1.startElevator();
		Person p1 = new Person("Person", "One");
		p1.enterBuilding(b1, 1);
	}

	/**
	 * If the elevator works with multiple starts
	 */
	public static void startElevatorStages() {
		/**
		 * Elevator at Lobby
			Elevator at floor 1
			Elevator at floor 1
			Elevator at Lobby
			Elevator at Lobby
			Elevator at floor 1
			Elevator at floor 2
			Elevator at floor 3
			Elevator at floor 4
			Elevator at floor 5
		 */
		Building b1 = new Building(5);
		Person p1 = new Person("Person", "A");
		p1.enterBuilding(b1, 1);
		b1.startElevator();
		Person p2 = new Person("Person", "B");
		p2.enterBuilding(b1, 5);
		b1.startElevator();
	}

	/**
	 * If startElevator() works with multiple starts and max occupancy
	 */
	public static void startElevatorCase2() {
		/** Expected: 
		 * Elevator at Lobby
			Elevator at floor 1
			Elevator at floor 2
			Elevator at floor 3
			Elevator at floor 4
			Elevator at floor 5
			Elevator at floor 5
			Elevator at floor 4
			Elevator at floor 3
			Elevator at floor 3
			Elevator at floor 4
			Elevator at floor 4
			Elevator at floor 3
			Elevator at floor 2
			Elevator at floor 1
			Elevator at Lobby
			Elevator at Lobby
			Elevator at floor 1
		 */
		Building b1 = new Building(5);
		Person pa = new Person("Person", "A");
		Person pb = new Person("Person", "B");
		Person pc = new Person("Person", "C");
		pa.enterBuilding(b1, 5);
		pb.enterBuilding(b1, 3);
		pc.enterBuilding(b1, 4);
		b1.startElevator();
		Person pd = new Person("Person", "D");
		pd.enterBuilding(b1, 1);
		b1.startElevator();
	}

	/**
	 * If program works with resizing arrays, multiple calls to startElevator(), getLocation()
	 */
	public static void startElevatorCombination() {
		/** Expected Output
		 * Elevator at Lobby
			Elevator at floor 1
			Elevator at floor 1
			Elevator at floor 2
			Elevator at floor 3
			Elevator at floor 4
			Elevator at floor 5
			Elevator at floor 5
			Elevator at floor 4
			Elevator at floor 3
			Elevator at floor 2
			Elevator at floor 1
			Elevator at Lobby
			Elevator at Lobby
			Elevator at floor 1
			Elevator at floor 1
			Elevator at Lobby 
			Elevator at Lobby
			Elevator at Floor 1
			Elevator at Floor 1
			Elevator at Floor 1
			Elevator at Lobby
			Elevator at Lobby
			Elevator at Floor 1
		 */
		Building b1 = new Building(5);
		Person pa = new Person("Person", "A");
		Person pb = new Person("Person", "B");
		Person pc = new Person("Person", "C");
		Person pd = new Person("Person", "D");
		Person pe = new Person("Person", "E");
		Person pf = new Person("Person", "F");
		pa.enterBuilding(b1, 1);
		pb.enterBuilding(b1, 5);
		pc.enterBuilding(b1, 0);
		pd.enterBuilding(b1, 6);
		pe.enterBuilding(b1, 2);
		pf.enterBuilding(b1, 1);
		b1.startElevator();
		Person pg = new Person("Person", "G");
		Person ph = new Person("Person", "H");
		pg.enterBuilding(b1, 1);
		ph.enterBuilding(b1, 1);
		b1.startElevator();
		Person pi = new Person("Person", "I");
		pi.enterBuilding(b1, 1);
		b1.startElevator();
		Person pj = new Person("Person", "J");
		pj.enterBuilding(b1, 1);
		System.out.println(pa.toString() + " : " + pa.getLocation()); // In Floor 1
		System.out.println(pb.toString() + " : " + pb.getLocation()); // In Floor 5
		System.out.println(pc.toString() + " : " + pc.getLocation()); // In Lobby
		System.out.println(pd.toString() + " : " + pd.getLocation()); // In Lobby
		System.out.println(pe.toString() + " : " + pe.getLocation()); // In Floor 2
		System.out.println(pf.toString() + " : " + pf.getLocation()); // In Floor 1
		System.out.println(pg.toString() + " : " + pg.getLocation()); // In Floor 1
		System.out.println(ph.toString() + " : " + ph.getLocation()); // In Floor 1
		System.out.println(pi.toString() + " : " + pi.getLocation()); // In Floor 1
		System.out.println(pj.toString() + " : " + pj.getLocation()); // Waiting to be Serviced
	}
	

}
