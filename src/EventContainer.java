import java.time.LocalDate;
import java.util.LinkedList;

public class EventContainer {
    LinkedList<Event> events = new LinkedList<>();
    void add(Event e){
        events.add(e);
    }
    void remove(Event e){
        events.remove(e);
    }
    LinkedList<Event> getCloseElement(int days){
        LinkedList<Event> closeEvents= new LinkedList<>();
        Date current = new Date(LocalDate.now());
        for (Event event : events){
            int difference = Date.daysBetween(current,event.getStart());
            if( difference <= days && difference >= 0){
                closeEvents.add(event);
            }
        }
        return closeEvents;
    }
}
