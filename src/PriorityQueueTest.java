import static org.junit.jupiter.api.Assertions.*;

class PriorityQueueTest {

    @org.junit.jupiter.api.Test
    void add() {
        PriorityQueue<Integer> pq = new PriorityQueue<>();

        pq.add(5);
        pq.add(3);
        pq.add(8);
        pq.add(1);

        // smallest should be at root
        assertEquals(1, pq.peek());

        // size should match
        assertEquals(4, pq.size());
    }

    @org.junit.jupiter.api.Test
    void contains() {
        PriorityQueue<String> pq = new PriorityQueue<>();

        pq.add("apple");
        pq.add("banana");

        assertTrue(pq.contains("apple"));
        assertTrue(pq.contains("banana"));
        assertFalse(pq.contains("cherry"));

        // catches reference vs equals issues if still broken
        assertTrue(pq.contains(new String("apple")));
    }

    @org.junit.jupiter.api.Test
    void peek() {
        PriorityQueue<Integer> pq = new PriorityQueue<>();

        assertNull(pq.peek());

        pq.add(10);
        pq.add(2);
        pq.add(7);

        assertEquals(2, pq.peek());

        // peek should not remove
        assertEquals(3, pq.size());
    }

    @org.junit.jupiter.api.Test
    void poll() {
        PriorityQueue<Integer> pq = new PriorityQueue<>();

        assertNull(pq.poll());

        pq.add(5);
        pq.add(1);
        pq.add(3);

        assertEquals(1, pq.poll());
        assertEquals(2, pq.size());

        assertEquals(3, pq.poll());
        assertEquals(1, pq.size());

        assertEquals(5, pq.poll());
        assertEquals(0, pq.size());

        assertNull(pq.poll());
    }

    @org.junit.jupiter.api.Test
    void remove() {
        PriorityQueue<Integer> pq = new PriorityQueue<>();

        pq.add(10);
        pq.add(5);
        pq.add(1);
        pq.add(7);

        assertTrue(pq.remove(5));
        assertEquals(3, pq.size());
        assertFalse(pq.contains(5));

        // removing root
        assertTrue(pq.remove(1));
        assertEquals(2, pq.size());
        assertEquals(7, pq.peek());

        // removing non-existent
        assertFalse(pq.remove(999));
    }

    @org.junit.jupiter.api.Test
    void size() {
        PriorityQueue<Integer> pq = new PriorityQueue<>();

        assertEquals(0, pq.size());

        pq.add(1);
        pq.add(2);
        pq.add(3);

        assertEquals(3, pq.size());

        pq.poll();

        assertEquals(2, pq.size());
    }

    @org.junit.jupiter.api.Test
    void testToString() {
        PriorityQueue<Integer> pq = new PriorityQueue<>();

        assertEquals("", pq.toString().trim());

        pq.add(5);
        pq.add(1);
        pq.add(3);

        String output = pq.toString();

        // must contain all elements
        assertTrue(output.contains("1"));
        assertTrue(output.contains("3"));
        assertTrue(output.contains("5"));

        // correct size formatting
        assertEquals(3, output.split(" ").length);
    }
}