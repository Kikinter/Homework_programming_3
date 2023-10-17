import java.io.Serializable;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.LinkedList;
import java.util.Objects;

public class EventContainer implements Serializable{
    private LinkedList<Event> events = new LinkedList<>();
    void add(Event e){
        events.add(e);
        Main.changed = true;
    }
    void remove(Event e){
        events.remove(e);
    }
    LinkedList<Event> getCloseElement(int days){
        LinkedList<Event> closeEvents= new LinkedList<>();
        LocalDate current = LocalDate.now();
        for (Event event : events){
            long difference = ChronoUnit.DAYS.between(event.day, current);
            if( difference <= days && event.day.isAfter(current)){
                closeEvents.add(event);
            }
        }
        return closeEvents;
    }
    int size(){
        return events.size();
    }

    boolean contains(LocalDate d) {
        for (Event e : events) {
            if (d.equals(e.day)) return true;
        }
        return false;
    }
}
