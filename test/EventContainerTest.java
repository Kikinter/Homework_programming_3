
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;

import static org.junit.Assert.*;

public class EventContainerTest {
    EventContainer container;
    Event event;
    @Before
    public void init(){
        container = new EventContainer();
        event = new Event(false,"test","desc",LocalDateTime.now(),LocalDateTime.now(),0,null);
    }
    @Test
    public void testConstructor(){
        assertEquals(1, container.getEvents().size());
    }
    @Test
    public void testAdd(){
        container.add(event);
        assertEquals(2,container.getEvents().size());
    }
    @Test
    public void testRemove(){
        container.add(event);
        container.remove(event);
        assertEquals(1,container.getEvents().size());
    }
    @Test
    public void testGet(){
        container.add(event);
        assertEquals(event,container.get(1));
    }
    @Test
    public void testDuplicate(){
        container.add(event);
        container.add(event);
        assertTrue(container.duplicate("test",LocalDateTime.now().getYear(), LocalDateTime.now().getMonthValue(), LocalDateTime.now().getDayOfMonth()));
    }

}
