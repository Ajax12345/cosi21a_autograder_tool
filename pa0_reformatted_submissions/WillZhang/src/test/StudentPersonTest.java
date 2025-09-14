import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class StudentPersonTest {

    @Test
    void newPerson_startsInLobby() {
        Person p = new Person("Ada", "Lovelace");
        assertEquals("In Lobby", p.getLocation());
    }

    @Test
    void enterBuilding_thenElevator_runsToTargetFloor() {
        Building b = new Building(3);
        Person p = new Person("Alan", "Turing");
        p.enterBuilding(b, 2);
        assertEquals("Waiting to be serviced", p.getLocation());
        b.startElevator();
        assertEquals("In Floor 2", p.getLocation());
    }
}
