import javax.swing.*;
import java.awt.*;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.Year;
import java.util.ArrayList;

public class CalendarMonthly {
    CalendarMonthly() {
        Main.dateShown = LocalDateTime.now().minusDays(LocalDateTime.now().getDayOfMonth()-1);

        //Frame,panel,menu setup
        JFrame frame = new JFrame("Calendar");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(714, 800));
        frame.setJMenuBar(new JMenuBar());
        frame.setJMenuBar(Main.menu.menuBar);
        JPanel panel = new JPanel();
        panel.setLayout(null);


        //Day labels set, stored and put on panel
        ArrayList<JLabel> nameOfDays = new ArrayList<>();
        for(int i = 0; i < 7; i++){
            nameOfDays.add(new JLabel(DayOfWeek.of(i+1).name()));
            nameOfDays.get(i).setBounds(20 + 100*i, 40, 100,30);
        }
        for(JLabel label : nameOfDays){
            panel.add(label);
        }

        //Label for the year+month
        JLabel date = new JLabel(Main.dateShown.getYear() + " " + Main.dateShown.getMonth().name());
        date.setBounds(310,5,120,30);
        panel.add(date);


        //Getting the first Monday that is before the shown date
        LocalDateTime start = Main.dateShown;
        int offset = 0;
        if(DayOfWeek.from(start) != DayOfWeek.MONDAY){
            while (DayOfWeek.from(start) != DayOfWeek.MONDAY){
                start = start.minusDays(1);
                offset++;
            }
        }

        //Getting the first sunday after the end of the month
        LocalDateTime end = Main.dateShown.plusMonths(1).minusDays(1);
        int forwardOffset = 0;
        if(end.getDayOfWeek() != DayOfWeek.SUNDAY){
            while (DayOfWeek.from(end) != DayOfWeek.SUNDAY){
                end = end.plusDays(1);
                forwardOffset++;
            }
        }


        //Buttons for days with numbers on them first set
        ArrayList<JButton> days = new ArrayList<>();
        int lines = 0;
        int breakLine = 0;
        LocalDateTime first = start;
        for(int i = 0; i < Main.dateShown.getMonth().length(Year.of(Main.dateShown.getYear()).isLeap())+offset+forwardOffset; i++){
            String num = Integer.toString(start.plusDays(i).getDayOfMonth());
            days.add(new JButton(num));
            if(i % 7 == 0){
                lines++;
                breakLine = 0;
            }
            days.get(i).setBounds(breakLine*100,lines*100,100,100);
            int difference = i;
            days.get(i).addActionListener(e -> new OpenEvent(first.plusDays(difference)));
            breakLine++;
        }
        for(JButton b: days){
            panel.add(b);
        }

        //Buttons to go forward and backward in date set and added to panel
        JButton backDate = getBackButton(date, days, panel, false);
        JButton forwardDate = getBackButton(date, days, panel, true);
        panel.add(backDate);
        panel.add(forwardDate);

        //Frame visible
        frame.getContentPane().add(panel);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private static JButton getBackButton(JLabel date, ArrayList<JButton> days, JPanel panel, boolean forward) {
        Icon back = new ImageIcon();
        JButton moveButton = new JButton(back);
        if(!forward)moveButton.setBounds(15, 5, 50,35);
        else moveButton.setBounds(637, 5, 50,35);
        moveButton.addActionListener(e-> {
            if(!forward)Main.dateShown = Main.dateShown.minusMonths(1);
            else Main.dateShown = Main.dateShown.plusMonths(1);
            date.setText(Main.dateShown.getYear() + " " + Main.dateShown.getMonth().name());
            for(JButton button : days){
                panel.remove(button);
            }
            days.clear();

            //First monday pre
            LocalDateTime firstMonday = Main.dateShown;
            int off = 0;
            if(DayOfWeek.from(firstMonday) != DayOfWeek.MONDAY){
                while (DayOfWeek.from(firstMonday) != DayOfWeek.MONDAY){
                    firstMonday = firstMonday.minusDays(1);
                    off++;
                }
            }

            //First sunday post
            LocalDateTime end = Main.dateShown.plusMonths(1).minusDays(1);
            int forwardOffset = 0;
            if(end.getDayOfWeek() != DayOfWeek.SUNDAY){
                while (DayOfWeek.from(end) != DayOfWeek.SUNDAY){
                    end = end.plusDays(1);
                    forwardOffset++;
                }
            }

            int line = 0;
            int lineBreak = 0;
            LocalDateTime starter = firstMonday;
            for(int i = 0; i < Main.dateShown.getMonth().length(Year.of(Main.dateShown.getYear()).isLeap())+off+forwardOffset; i++){
                String num = Integer.toString(firstMonday.plusDays(i).getDayOfMonth());
                days.add(new JButton(num));
                if(i % 7 == 0){
                    line++;
                    lineBreak = 0;
                }
                days.get(i).setBounds(lineBreak*100,line*100,100,100);
                int difference = i;
                days.get(i).addActionListener(k -> new OpenEvent(starter.plusDays(difference)));
                lineBreak++;
            }
            for(JButton b: days){
                panel.add(b);
            }
            panel.repaint();
        }
        );
        return moveButton;
    }
}
