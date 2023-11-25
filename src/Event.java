import javax.swing.*;
import java.awt.*;
import java.io.Serializable;
import java.time.LocalDateTime;

public class Event implements Serializable{
    boolean favourite;
    String name;
    String description;
    LocalDateTime startDate;
    LocalDateTime endDate;
    int daysBetween;
    String iconPath;

    Event(boolean favourite, String name, String description, LocalDateTime startDate, LocalDateTime endDate, int daysBetween, String iconPath){
        this.favourite = favourite;
        this.name = name;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.daysBetween = daysBetween;
        this.iconPath = iconPath;
    }
}
