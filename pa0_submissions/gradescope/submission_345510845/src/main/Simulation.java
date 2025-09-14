package main;

public class Simulation {

	public static void main(String[] args) {
		
		Building b1 = new Building(3);  //lobyy and floors 1-3
		
		Person one = new Person("Tony", "Hawk");			//instantiating a few people for the buidling

		Person two = new Person("Lebron", "James");

		one.enterBuilding(b1, 2);		//tony wants floor 2
		two.enterBuilding(b1, 3);		// lebron wants floor 3

		b1.startElevator(); 		//should process jobs



		Building b3 = new Building(4);

		Person three = new Person("Walter", "White");

		Person four = new Person ("Louis", "Brandeis");

		Person five = new Person ("Abby","Miller");

		three.enterBuilding(b3, 3);
		b3.startElevator();

		four.enterBuilding(b3,1);
		five.enterBuilding(b3, 4);
		b3.startElevator();




	}

}
