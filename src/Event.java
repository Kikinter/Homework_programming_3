import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * This class represents one event in the calendar
 */
public class Event implements Serializable{
    boolean favourite;
    String name;
    String description;
    LocalDateTime startDate;
    LocalDateTime endDate;
    int daysBetween;
    String iconPath;

    /**
     * @param favourite is the event a favourite
     * @param name the name of the event
     * @param description the description of the event
     * @param startDate the start of the event
     * @param endDate the end of the event
     * @param daysBetween if it repeats, how many days are between the two occurrence
     * @param iconPath the path of the icon
     *                 The constructor makes a new event based on these parameters
     */
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
