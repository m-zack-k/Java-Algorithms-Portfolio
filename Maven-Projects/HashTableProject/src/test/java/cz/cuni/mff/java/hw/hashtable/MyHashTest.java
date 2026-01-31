package cz.cuni.mff.java.hw.hashtable;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

/*References
checked Hashcode formula to verify that "Aa" and "BB" have the same hashcode
https://docs.oracle.com/javase/8/docs/api/java/lang/String.html#hashCode--
*/

public class MyHashTest {
    @Test
    void testSetGet() {
        MyHash table = new MyHash();
        table.set("Praha", "Krasna");
        table.set("Ostrava", "Hezka");
        table.set("Brno", 123); // Mixing types is allowed (Object value)

        assertEquals("Krasna", table.get("Praha"), "Praha should map to Krasna");
        assertEquals("Hezka", table.get("Ostrava"), "Ostrava should map to Hezka");
        assertEquals(123, table.get("Brno"), "Brno should map to 123");
    }

    @Test
    void testUpdateValue() {
        MyHash table = new MyHash();
        table.set("Svijany", 10);
        // Update the value
        table.set("Svijany", 20);

        assertEquals(20, table.get("Svijany"), "Svijany should be updated to 20");
    }

    @Test
    void testGetNonExistent() {
        MyHash table = new MyHash();
        assertNull(table.get("kozel"), "Getting a non-existent key should return null");
    }

    @Test
    void testCollisionHandling() {
        MyHash table = new MyHash();
        //// "Aa" and "BB" have the same hashcode, checking collision
        table.set("Aa", 1);
        table.set("BB", 2); 

        assertEquals(1, table.get("Aa"));
        assertEquals(2, table.get("BB"));
    }

    @Test
    void testIterator() {
        MyHash table = new MyHash();
        table.set("a", 1);
        table.set("b", 2);
        table.set("c", 3);

        Set<String> keys = new HashSet<>();
        for (String key : table) {
            keys.add(key);
        }

        assertTrue(keys.contains("a"));
        assertTrue(keys.contains("b"));
        assertTrue(keys.contains("c"));
        assertEquals(3, keys.size(), "Iterator should find exactly 3 keys");
    }

    @Test
    void testForEachValue() {
        MyHash table = new MyHash();
        table.set("a", 10);
        table.set("b", 20);
        table.set("c", 30);

        // We use a simple integer array to store the sum
        final int[] sum = {0};

        // Lambda expression: adds every value to the sum
        table.forEachValue((val) -> {
            if (val instanceof Integer) {
                sum[0] += (Integer) val;
            }
        });

        assertEquals(60, sum[0], "Sum of 10, 20, 30 should be 60");
    }
    
    @Test
    void testSetNullKey() {
        MyHash table = new MyHash();
        table.set(null, "Void"); 
        // Just ensuring it doesn't crash
        assertTrue(true); 
    }
}
