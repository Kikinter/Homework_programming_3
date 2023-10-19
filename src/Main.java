import java.io.File;
import java.time.LocalDate;
public class Main {
    static Menu menu = new Menu();
    static LocalDate dateShown = LocalDate.now();
    static File lastChosen = new File("new.txt");
    static EventContainer events = new EventContainer();
    static boolean changed = false;
    public static void main(String[] args) {
        new StartPage();
    }
}
