import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class CalendarMonthly {
    CalendarMonthly() {
        JFrame frame = new JFrame("Calendar");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(1000, 800));
        frame.setJMenuBar(new JMenuBar());
        frame.setJMenuBar(Main.menu.menuBar);

        ArrayList<JButton> days = new ArrayList<>();
        for(int i = 0; i< Main.dateShown.getMonth().length(Main.dateShown.isLeapYear()); i++){
            String num = Integer.toString(i+1);
            days.add(new JButton(num));
            days.get(i).setBounds(i*100,0,100,100);
        }
        JPanel panel = new JPanel();
        panel.setLayout(null);
        for(JButton b : days){
            panel.add(b);
        }
        frame.getContentPane().add(panel);

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
