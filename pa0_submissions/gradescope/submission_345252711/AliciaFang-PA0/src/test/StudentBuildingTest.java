package test;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import main.Building;
import main.Person;

class StudentBuildingTest {
	@Test
	public void testEmptyBuildingToString() {
        Building b = new Building(3);
        String output = b.toString();

        assertTrue(output.contains("This building has 3 floors"));
        assertTrue(output.contains("Lobby :")); 
        assertTrue(output.contains("Floor 1 :")); 
        assertTrue(output.contains("Floor 2 :"));
        assertTrue(output.contains("Floor 3 : "));
    }

    @Test
    public void testEnterValidElevatorRequest() {
        Building b = new Building(2);
        Person p = new Person("Alice","Fang");

        boolean result = b.enterElevatorRequest(p, 2);
        assertEquals(true,result);

        b.startElevator(); // should process the job
        String output = b.toString();

        assertTrue(output.contains("Floor 2 : Alice Fang (In Floor 2)"));
    }
}
