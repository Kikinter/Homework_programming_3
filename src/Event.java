import javax.swing.*;
import java.awt.*;
import java.time.LocalDateTime;

public class Event {
    boolean favourite;
    String name;
    String description;
    LocalDateTime startDate;
    LocalDateTime endDate;
    int daysBetween;
    ImageIcon icon;

    Event(boolean favourite, String name, String description, LocalDateTime startDate, LocalDateTime endDate, int daysBetween, ImageIcon icon){
        this.favourite = favourite;
        this.name = name;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.daysBetween = daysBetween;
        if(icon != null) this.icon = new ImageIcon(icon.getImage().getScaledInstance(70, 70, Image.SCALE_DEFAULT));
        else this.icon = null;
    }
}
