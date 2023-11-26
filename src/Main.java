import java.awt.*;
import java.io.File;
import java.time.LocalDateTime;

/**
 * Create an instance of start page
 */
public class Main {
    /**
     * The menu
     */
    static Menu menu = new Menu();
    /**
     * Date shown on current frame
     */
    static LocalDateTime dateShown = LocalDateTime.now();
    /**
     * Last chosen file
     */
    static File lastChosen = new File("");
    /**
     * Events in calendar
     */
    static EventContainer events = new EventContainer();
    /**
     * Events were modified or not
     */
    static boolean changed = false;
    /**
     * Current frame
     */
    static Frame currentFrame;

    /**
     * @param args arguments
     * Creates a new instance of start page, which starts the program
     */
    public static void main(String[] args) {
        new StartPage();
    }
}
