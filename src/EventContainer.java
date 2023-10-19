import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.LinkedList;
public class EventContainer implements Serializable{
    private LinkedList<Event> events = new LinkedList<>();
    boolean favouriteVisible = true;

    EventContainer(){
        events.add(new Event(false,"Christmas", "", LocalDateTime.of(2023,12,25,0,0), LocalDateTime.of(2023,12,25,23,59),null));
    }
    void add(Event e){
        events.add(e);
        Main.changed = true;
    }
    void remove(Event e){
        events.remove(e);
    }
    LinkedList<Event> getCloseElement(int days){
        LinkedList<Event> closeEvents= new LinkedList<>();
        LocalDateTime current = LocalDateTime.now();
        for (Event event : events){
            long difference = ChronoUnit.DAYS.between(event.startDate, current);
            if( difference <= days && event.startDate.isAfter(current)){
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
            if (d.equals(e.startDate)) return true;
        }
        return false;
    }
}
