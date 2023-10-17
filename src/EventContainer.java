import java.io.Serializable;
import java.time.LocalDate;
import java.util.LinkedList;

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
        Date current = new Date(LocalDate.now());
        for (Event event : events){
            int difference = Date.daysBetween(current,event.getStart());
            if( difference <= days && difference >= 0){
                closeEvents.add(event);
            }
        }
        return closeEvents;
    }
    int size(){
        return events.size();
    }

    boolean contains(Date d) {
        for (Event e : events) {
            if (e.start == d) return true;
        }
        return false;
    }


}
