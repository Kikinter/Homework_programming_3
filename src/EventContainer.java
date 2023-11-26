import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * This class is to store instances of the Event class
 */
public class EventContainer implements Serializable {
    /**
     * The events
     */
    private ArrayList<Event> events = new ArrayList<>();
    /**
     * The repeating events
     */
    private HashMap<Event,Event> repeats = new HashMap<>();

    /**
     * The constructor makes a container with only Christmas in it
     */
    EventContainer(){
        Event e = new Event(false,"Christmas", "Merry Christmas!", LocalDateTime.of(LocalDateTime.now().getYear(),12,25,0,0), LocalDateTime.of(LocalDateTime.now().getYear(), 12,25,23,59),365,"resources/images/tree_icon.png");
        events.add(e);
        repeatUpdate(e);
    }

    /**
     * The function adds a new event to the container
     * @param e the event to add
     */
    void add(Event e){
        events.add(e);
        Main.changed = true;
        if(e.daysBetween != 0){
            repeatUpdate(e);
        }
    }

    /**
     * This function gives back every event in the container
     * @return the events in the container
     */
    ArrayList<Event> getEvents(){return events;}

    /**
     * This function removes an event from the container
     * @param e the event to remove
     */
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

    /**
     * This function removes every event given from the container
     * @param e the events to be removed
     */
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

    /**
     * This function gives back the element on the specific index
     * @param i index of the event
     * @return the event on that index
     */
    Event get(int i){return events.get(i);}

    /**
     * This function gives back a list that contain every event that is close to today
     * @param days number of days considered close
     * @return the list of events close to today
     */
    ArrayList<Event> getCloseElement(int days){
        ArrayList<Event> closeEvents= new ArrayList<>();
        LocalDateTime current = LocalDateTime.now();
        for (Event event : events){
            long difference = Math.abs(ChronoUnit.DAYS.between(event.startDate, current));
            if( difference <= days && event.startDate.isAfter(current)){
                closeEvents.add(event);
            }
        }
        return closeEvents;
    }

    /**
     * THis function gives back, how many events are in the calendar
     * @return the size of the container
     */
    int size(){
        return events.size();
    }

    /**
     * This function gives back every event that are on a certain day
     * @param dateTime the date of the event
     * @return the event on that date
     */
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

    /**
     * This function refreshes the repeat list, based on the change that happened in the events list
     * @param event the main event in the events list
     */
    private void repeatUpdate(Event event){
        LocalDateTime startDay = event.startDate;
        LocalDateTime endDay = event.endDate;
        LocalDateTime end = event.startDate.plusYears(5);
        while (startDay.isBefore(end)){
            startDay = startDay.plusDays(event.daysBetween);
            endDay = endDay.plusDays(event.daysBetween);
            Event newEvent = new Event(event.favourite,event.name, event.description, startDay,endDay,event.daysBetween,event.iconPath);
            repeats.put(newEvent,event);
        }
    }

    /**
     * This function is to search for duplicates in the container
     * @param name name of the event
     * @param year start year of the event
     * @param month start month of the event
     * @param day start day of the event
     * @return true if there is already an event with these parameters in the container
     */
    boolean duplicate(String name, int year, int month, int day){
        for(Event e : events){
            if(e.name.equals(name) && e.startDate.getYear() == year && e.startDate.getMonthValue() == month && e.startDate.getDayOfMonth() == day) return true;
        }
        return false;
    }

    /**
     * This function clears the whole container
     */
    void clear(){
        events.clear();
        repeats.clear();
    }
}
