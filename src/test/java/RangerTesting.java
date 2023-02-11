import heroes.Mage;
import heroes.Ranger;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RangerTesting {

    //add failing test to see if CI works
    @Test
    void testLevel() {
        var ranger = new Ranger("someRanger");
        assertEquals(2, ranger.getLevel());
    }
}
