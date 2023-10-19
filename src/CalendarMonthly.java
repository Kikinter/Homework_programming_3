import javax.swing.*;
import java.awt.*;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;

public class CalendarMonthly {
    CalendarMonthly() {
        Main.dateShown = LocalDate.now().minusDays(LocalDate.now().getDayOfMonth()-1);

        //Frame,panel,menu setup
        JFrame frame = new JFrame("Calendar");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(714, 800));
        frame.setJMenuBar(new JMenuBar());
        frame.setJMenuBar(Main.menu.menuBar);
        JPanel panel = new JPanel();
        panel.setLayout(null);


        //Getting the first Monday that is before the shown date
        LocalDate start = Main.dateShown;
        int offset = 0;
        if(DayOfWeek.from(start) != DayOfWeek.MONDAY){
            while (DayOfWeek.from(start) != DayOfWeek.MONDAY){
                start = start.minusDays(1);
                offset++;
            }
        }

        //Day labels set, stored and put on panel
        ArrayList<JLabel> nameOfDays = new ArrayList<>();
        for(int i = 0; i < 7; i++){
            nameOfDays.add(new JLabel(DayOfWeek.from(start.plusDays(i)).name()));
            nameOfDays.get(i).setBounds(20 + 100*i, 40, 100,30);
        }
        for(JLabel label : nameOfDays){
            panel.add(label);
        }

        //Buttons for days with numbers on them, then added to panel
        ArrayList<JButton> days = new ArrayList<>();
        int lines = 0;
        int breakLine = 0;
        for(int i = 0; i < Main.dateShown.getMonth().length(Main.dateShown.isLeapYear())+offset; i++){
            String num = Integer.toString(start.plusDays(i).getDayOfMonth());
            days.add(new JButton(num));
            if(i % 7 == 0){
                lines++;
                breakLine = 0;
            }
            days.get(i).setBounds(breakLine*100,lines*100,100,100);
            int difference = i;
            days.get(i).addActionListener(e -> new OpenEvent(Main.dateShown.plusDays(difference)));
            breakLine++;
        }
        for(JButton b : days){
            panel.add(b);
        }

        //Buttons to go forward and backward set and added to panel
        Icon back = new ImageIcon();
        JButton backDate = new JButton(back);
        backDate.setBounds(15, 5, 50,35);
        backDate.addActionListener(e-> {
                Main.dateShown = Main.dateShown.minusMonths(1);
                frame.repaint();
            }
        );

        Icon forward = new ImageIcon();
        JButton forwardDate = new JButton(forward);
        forwardDate.setBounds(637, 5, 50,35);
        forwardDate.addActionListener(e -> {
                    Main.dateShown = Main.dateShown.plusMonths(1);
                    frame.repaint();
            }
        );
        panel.add(backDate);
        panel.add(forwardDate);

        //Frame visible
        frame.getContentPane().add(panel);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

}
