import javax.swing.*;
import java.awt.*;

public class CalendarMonthly {
    CalendarMonthly() {
        JFrame frame = new JFrame("Calendar");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(1000, 800));
        frame.setJMenuBar(new JMenuBar());
        frame.setJMenuBar(Main.menu.menuBar);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
