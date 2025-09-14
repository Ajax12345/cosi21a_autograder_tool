package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import main.Floor;
import main.Person;

class StudentFloorTest {
	
	@Test
    public void testEnterFloorAddsPerson() {
        Floor f = new Floor(2);
        Person p = new Person("Charlie", "Puth");

        f.enterFloor(p);
        String output = f.toString();

        assertEquals("Floor 2 : Charlie Puth (In Floor 2)", output);
    }

}
