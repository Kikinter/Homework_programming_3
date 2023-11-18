import java.lang.reflect.GenericDeclaration;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class EventContainer{
    private ArrayList<Event> events = new ArrayList<>();
    private HashMap<Event,Event> repeats = new HashMap<>();
    boolean favouriteVisible = true;

    EventContainer(){
        Event e = new Event(false,"Christmas", "Merry Christmas!", LocalDateTime.of(LocalDateTime.now().getYear(),12,25,0,0), LocalDateTime.of(LocalDateTime.now().getYear(), 12,25,23,59),365,null);
        events.add(e);
        repeatUpdate(e);
    }
    void add(Event e){
        events.add(e);
        Main.changed = true;
        if(e.daysBetween != 0){
            repeatUpdate(e);
        }
    }
    ArrayList<Event> getEvents(){return events;}
    void remove(Event e){
        events.remove(e);
        ArrayList<Event> delete = new ArrayList<>();
        for(Event event : repeats.keySet()){
            if(repeats.get(event).equals(e)) delete.add(event);
        }
        for(Event event : delete){
            repeats.remove(event);
        }
    }
    void removeAll(ArrayList<Event> e){
        events.removeAll(e);
        ArrayList<Event> rep = new ArrayList<>();
        for(Event event : repeats.keySet()){
            if(e.contains(repeats.get(event))){
                rep.add(event);
            }
        }
        for(Event event : rep){
            repeats.remove(event);
        }
    }
    Event get(int i){return events.get(i);}
    int getIndex(Event e){
        for(int i = 0; i < Main.events.size(); i++){
            if(Main.events.get(i) == e) return i;
        }
        return 0;
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
            if(dateTime.getYear() == e.startDate.getYear() && dateTime.getMonth() == e.startDate.getMonth() && dateTime.getDayOfMonth() == e.startDate.getDayOfMonth()) elements.add(e);
        }
        for(Event e : repeats.keySet()){
            if(dateTime.getYear() == e.startDate.getYear() && dateTime.getMonth() == e.startDate.getMonth() && dateTime.getDayOfMonth() == e.startDate.getDayOfMonth()) elements.add(e);
        }
        return elements;
    }
    private void repeatUpdate(Event event){
        LocalDateTime startDay = event.startDate;
        LocalDateTime endDay = event.endDate;
        LocalDateTime end = event.startDate.plusYears(5);
        while (startDay.isBefore(end)){
            startDay = startDay.plusDays(event.daysBetween);
            endDay = endDay.plusDays(event.daysBetween);
            Event newEvent = new Event(event.favourite,event.name, event.description, startDay,endDay,event.daysBetween,event.icon);
            repeats.put(newEvent,event);
        }
    }
}
