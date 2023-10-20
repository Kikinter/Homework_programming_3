import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.LinkedList;
public class EventContainer implements Serializable{
    private ArrayList<Event> events = new ArrayList<>();
    boolean favouriteVisible = true;

    EventContainer(){
        events.add(new Event(false,"Christmas", "", LocalDateTime.of(LocalDateTime.now().getYear(),12,25,0,0), LocalDateTime.of(LocalDateTime.now().getYear(), 12,25,23,59),null));
    }
    void add(Event e){
        events.add(e);
        Main.changed = true;
    }
    void remove(Event e){
        events.remove(e);
    }
    ArrayList<Event> getCloseElement(int days){
        ArrayList<Event> closeEvents= new ArrayList<>();
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
    ArrayList<Event> contains(LocalDateTime dateTime) {
        ArrayList<Event> elements = new ArrayList<>();
        for (Event e : events) {
            if(dateTime.getYear() == e.startDate.getYear() && dateTime.getMonth() == e.startDate.getMonth() && dateTime.getDayOfMonth() == dateTime.getDayOfMonth()) elements.add(e);
            System.out.println(e.startDate);
        }
        return elements;
    }
}
