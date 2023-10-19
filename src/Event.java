import javax.swing.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class Event {
    boolean favourite;
    String name;
    String description;
    LocalDateTime startDate;
    LocalDateTime endDate;
    Icon icon;

    Event(boolean favourite, String name, String description, LocalDateTime startDate, LocalDateTime endDate ,Icon icon){
        this.favourite = favourite;
        this.name = name;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.icon = icon;
    }
    void change(boolean favourite, String name, String description, LocalDateTime startDate, LocalDateTime endDate ,Icon icon){
        this.favourite = favourite;
        this.name = name;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.icon = icon;
    }
}
