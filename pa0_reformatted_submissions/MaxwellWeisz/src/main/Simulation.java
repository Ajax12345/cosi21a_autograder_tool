package main;

/**
 * Class using the Building class and Person class to create instances of both and having the building's
 * elevator run.
 * Known Bugs: None
 * @author Maxwell Weisz
 * maxwellweisz@brandeis.edu
 * Sept 9, 2025
 * COSI 21A PA0
 */
public class Simulation {

	public static void main(String[] args) {
		Building buildingOne = new Building(30);
		Person guestA = new Person("Guest", "A");
		Person guestB = new Person("Guest", "B");
		Person guestC = new Person("Guest", "C");
		Person guestD = new Person("Guest", "D");
		Person guestE = new Person("Invalid", "Floor");
		Person guestF = new Person("Guest", "F");
		Person guestG = new Person("Guest", "G");
		guestA.enterBuilding(buildingOne, 22);
		guestB.enterBuilding(buildingOne, 15);
		guestE.enterBuilding(buildingOne, 39);//Should be skipped and not entered in elevators jobs
		guestC.enterBuilding(buildingOne, 27);//Shouldn't go to lobby before C since E was skipped
		guestD.enterBuilding(buildingOne, 19);//Should go to lobby for D
		System.out.println(guestC.getLocation());//Should be waiting to be serviced
		System.out.println(guestD.getLocation());
		System.out.println(guestE.getLocation());//Should be in lobby
		buildingOne.startElevator();
		System.out.println(guestE.getLocation());//Still in lobby
		System.out.println(guestC.getLocation());//A,B,C,D should all be on their floors now
		System.out.println(guestD.getLocation());
		guestF.enterBuilding(buildingOne, 18);
		guestG.enterBuilding(buildingOne, 3);
		buildingOne.startElevator();//elevator should go back to lobby now and pick up the new guests
		System.out.println(guestF.getLocation());
		System.out.println(guestG.getLocation());


		System.out.println();
		Building sampleOutputOneBuilding = new Building(3);
		Person testSampleA = new Person("test", "A");
		Person testSampleB = new Person("test", "B");
		testSampleA.enterBuilding(sampleOutputOneBuilding, 2);
		testSampleB.enterBuilding(sampleOutputOneBuilding, 3);
		sampleOutputOneBuilding.startElevator();

		System.out.println();
		Building sampleOutputTwoBuilding = new Building(3);
		Person testA = new Person("sample2","A");
		Person testB = new Person("sample2", "B");
		Person testC = new Person("sample2", "C");
		Person testD = new Person("sample2", "D");
		testA.enterBuilding(sampleOutputTwoBuilding, 2);
		testB.enterBuilding(sampleOutputTwoBuilding, 3);
		testC.enterBuilding(sampleOutputTwoBuilding, 3);
		testD.enterBuilding(sampleOutputTwoBuilding, 2);
		sampleOutputTwoBuilding.startElevator();

		System.out.println();
		Building testBuildingTwo = new Building(15);
		guestA = new Person("guest", "A");
		guestB = new Person("guest", "B");
		guestC = new Person("guest", "C");
		guestD = new Person("guest", "D");
		guestE = new Person("guest", "E");
		guestF = new Person("guest", "F");
		guestG = new Person("guest", "G");
		guestA.enterBuilding(testBuildingTwo, 10);
		guestB.enterBuilding(testBuildingTwo, 7);
		guestC.enterBuilding(testBuildingTwo, 20);//Should not allow in elevator skips this person
		guestD.enterBuilding(testBuildingTwo, 15);//Doesn't go down to lobby for D since C skipped
		guestE.enterBuilding(testBuildingTwo, 3);//Goes back to lobby to get E
		guestF.enterBuilding(testBuildingTwo, -2);//skip for negatives as well
		guestG.enterBuilding(testBuildingTwo, 12);
		testBuildingTwo.startElevator();
		}

}
