package main;

public class Simulation {

	public static void main(String[] args) {

		

		Building newBuilding = new Building(10);

		Person Bob = new Person("Bob", "Smith");

		Person Rusty = new Person("Rusty", "Shackelford");

		Person Biden = new Person("Joey", "B");

		Person Ralph = new Person("Ralph", "GUy");

		

		//Job job1 = new Job(Bob, 5);

		//elevator.createJob(Bob, 5);

		//enterBuilding() and startElevator() are the only methods can use here

		Bob.enterBuilding(newBuilding, 5);

		newBuilding.startElevator();

		Rusty.enterBuilding(newBuilding, 2);

		Biden.enterBuilding(newBuilding, 8);

		Ralph.enterBuilding(newBuilding, 5);

		//newBuilding.enterElevatorRequest(Bob, 2);

		newBuilding.startElevator();

		


		

	}

}
