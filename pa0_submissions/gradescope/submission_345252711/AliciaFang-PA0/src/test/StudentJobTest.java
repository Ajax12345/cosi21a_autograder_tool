package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import main.Floor;
import main.Job;
import main.Person;

class StudentJobTest {
	@Test
    public void testRepositioningJobToString() {
        Job j = new Job(null, 0);
        assertEquals("[LobbyCall -> 0]", j.toString());
        assertNull(j.getPerson());
        assertEquals(0, j.getFloor());
    }



}
