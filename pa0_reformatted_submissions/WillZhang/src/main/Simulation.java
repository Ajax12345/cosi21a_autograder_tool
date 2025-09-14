
public class Simulation {
    public static void main(String[] args) {
        Elevator.maxOccupants = 2;

        Building building = new Building(6);

        Person p1 = new Person("Ada", "Lovelace");
        Person p2 = new Person("Alan", "Turing");
        Person p3 = new Person("Grace", "Hopper");

        p1.enterBuilding(building, 2);
        p2.enterBuilding(building, 5);
        p3.enterBuilding(building, 3);

        building.startElevator();

        Person p4 = new Person("Bjarne", "Stroustrup");
        Person p5 = new Person("Edsger", "Dijkstra");
        Person p6 = new Person("Barbara", "Liskov");

        p4.enterBuilding(building, 1);
        p5.enterBuilding(building, 9);
        p6.enterBuilding(building, 6);

        building.startElevator();

        Person p7  = new Person("Donald", "Knuth");
        Person p8  = new Person("Niklaus", "Wirth");
        Person p9  = new Person("Ken", "Thompson");
        Person p10 = new Person("Dennis", "Ritchie");

        p7.enterBuilding(building, 4);
        p8.enterBuilding(building, 2);
        building.startElevator();

        p9.enterBuilding(building, 5);
        p10.enterBuilding(building, 3);
        building.startElevator();

        System.out.println("\n=== Final Person Locations ===");
        printStatus(p1);
        printStatus(p2);
        printStatus(p3);
        printStatus(p4);
        printStatus(p5);
        printStatus(p6);
        printStatus(p7);
        printStatus(p8);
        printStatus(p9);
        printStatus(p10);

        System.out.println("\n=== Building Snapshot ===");
        System.out.println(building.toString());
    }

    private static void printStatus(Person p) {
        System.out.println(p.toString() + " -> " + p.getLocation());
    }
}
