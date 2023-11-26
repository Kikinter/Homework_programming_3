
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;

import static org.junit.Assert.*;

/**
 * Test class for event container testing
 */
public class EventContainerTest {
    /**
     * An event container for tests
     */
    EventContainer container;
    /**
     * An event for tests
     */
    Event event;

    /**
     * This sets up a new container and a new event before every test
     */
    @Before
    public void init(){
        container = new EventContainer();
        event = new Event(false,"test","desc",LocalDateTime.now(),LocalDateTime.now(),0,null);
    }

    /**
     * This tests the container's constructor
     */
    @Test
    public void testConstructor(){
        assertEquals(1, container.getEvents().size());
    }
    /**
     * This tests the container's add method
     */
    @Test
    public void testAdd(){
        container.add(event);
        assertEquals(2,container.getEvents().size());
    }
    /**
     * This tests the container's remove method
     */
    @Test
    public void testRemove(){
        container.add(event);
        container.remove(event);
        assertEquals(1,container.getEvents().size());
    }
    /**
     * This tests the container's get method
     */
    @Test
    public void testGet(){
        container.add(event);
        assertEquals(event,container.get(1));
    }
    /**
     * This tests the container's duplicate method
     */
    @Test
    public void testDuplicate(){
        container.add(event);
        container.add(event);
        assertTrue(container.duplicate("test",LocalDateTime.now().getYear(), LocalDateTime.now().getMonthValue(), LocalDateTime.now().getDayOfMonth()));
    }

}
