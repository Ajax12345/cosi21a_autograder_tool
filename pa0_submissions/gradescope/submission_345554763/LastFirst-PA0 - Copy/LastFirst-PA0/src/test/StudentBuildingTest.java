import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class StudentBuildingTest {

    @Test
    void ctor_invalidFloorCount_throws() {
        boolean thrown1 = false;
        try {
            new Building(0);
        } catch (IllegalArgumentException e) {
            thrown1 = true;
        }
        assertTrue(thrown1);

        boolean thrown2 = false;
        try {
            new Building(-5);
        } catch (IllegalArgumentException e) {
            thrown2 = true;
        }
        assertTrue(thrown2);
    }

    @Test
    void getNumFloors_matchesCtor() {
        Building b = new Building(5);
        assertEquals(5, b.getNumFloors());
    }

    @Test
    void startElevator_servicesAllQueuedPeople_respectingCapacity() {
        Elevator.maxOccupants = 2;
        Building b = new Building(4);
        Person p1 = new Person("A", "One");
        Person p2 = new Person("B", "Two");
        Person p3 = new Person("C", "Three");

        p1.enterBuilding(b, 3);
        p2.enterBuilding(b, 3);
        p3.enterBuilding(b, 3);

        b.startElevator();

        assertEquals("In Floor 3", p1.getLocation());
        assertEquals("In Floor 3", p2.getLocation());
        assertEquals("In Floor 3", p3.getLocation());
    }
}
