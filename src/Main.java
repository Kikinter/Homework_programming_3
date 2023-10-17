import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.time.LocalDate;

public class Main {
    static Menu menu = new Menu();
    static Date dateShown = new Date(LocalDate.now());
    static File lastChosen = new File("new.txt");
    static EventContainer events = new EventContainer();
    static boolean changed = false;
    public static void main(String[] args) {
        StartPage p = new StartPage();
    }
}
