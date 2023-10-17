import java.time.LocalDate;

public class Event {
    boolean favourite = false;
    String name;
    String description;
    LocalDate day;
    int hour;
    int minute;

    Event(boolean favourite, String name, String description, LocalDate day, int hour, int minute){
        this.favourite = favourite;
        this.name = name;
        this.description = description;
        this.day = day;
        this.hour = hour;
        this.minute = minute;
    }
    @Override
    public String toString(){
        return this.name;
    }
}
