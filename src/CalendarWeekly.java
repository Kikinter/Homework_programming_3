import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class CalendarWeekly extends JFrame{
    CalendarWeekly() {
        Main.dateShown = LocalDateTime.now();
        if(Main.dateShown.getDayOfWeek() != Main.menu.daySelected){
            while (Main.dateShown.getDayOfWeek() != Main.menu.daySelected){
                Main.dateShown = Main.dateShown.minusDays(1);
            }
        }
        //Setup frame
        this.setTitle("Calendar");
        this.setResizable(false);
        this.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                if(Main.changed) {
                    UserFeedback userFeedback = new UserFeedback();
                    boolean confirm = userFeedback.ask();
                    if (confirm) System.exit(0);
                }
                else System.exit(0);
            }
        });
        this.setPreferredSize(new Dimension(714, 261));
        this.setJMenuBar(new JMenuBar());
        this.setJMenuBar(Main.menu);
        JPanel panel = new JPanel();
        panel.setLayout(null);

        //Day labels set, stored and put on panel
        ArrayList<JLabel> nameOfDays = new ArrayList<>();
        for(int i = 0; i < 7; i++){
            nameOfDays.add(new JLabel(Main.menu.daySelected.plus(i).name()));
            nameOfDays.get(i).setBounds(20 + 100*i, 40, 100,30);
        }
        for(JLabel label : nameOfDays){
            panel.add(label);
        }

        //Label for year+month+first day of the week
        JLabel date = new JLabel(Main.dateShown.getYear() + " " + Main.dateShown.getMonth().name() + " " + Main.dateShown.getDayOfMonth());
        date.setBounds(300,5,130,30);
        panel.add(date);

        //Set buttons
        ArrayList<JButton> days = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            days.add(new JButton(Integer.toString(Main.dateShown.plusDays(i).getDayOfMonth())));
            days.get(i).setBounds(i * 100, 100, 100, 100);
            int offset = i;
            days.get(i).addActionListener(e -> new OpenEvent(Main.dateShown.plusDays(offset)));
        }
        for (JButton b : days) {
            panel.add(b);
        }
        this.getContentPane().add(panel);

        //Forward and back buttons
        JButton backDate = getWayButton(date, days, panel, false);
        JButton forwardDate = getWayButton(date, days, panel, true);
        panel.add(backDate);
        panel.add(forwardDate);

        //Make frame visible
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        Main.currentFrame = this;
    }

    private static JButton getWayButton(JLabel date, ArrayList<JButton> days, JPanel panel, boolean forward){
        Icon back = new ImageIcon();
        JButton moveButton = new JButton(back);
        if(!forward)moveButton.setBounds(15, 5, 50,35);
        else moveButton.setBounds(637, 5, 50,35);
        moveButton.addActionListener(e -> {
            if(forward) Main.dateShown = Main.dateShown.plusDays(6);
            else Main.dateShown = Main.dateShown.minusDays(6);
            date.setText(Main.dateShown.getYear() + " " + Main.dateShown.getMonth().name() + " " + Main.dateShown.getDayOfMonth());
            for(JButton button : days){
                panel.remove(button);
            }
            days.clear();

            for(int i = 0; i<7; i++){
                days.add(new JButton(Integer.toString(Main.dateShown.plusDays(i).getDayOfMonth())));
                days.get(i).setBounds(i * 100, 100, 100, 100);
                int difference = i;
                days.get(i).addActionListener(k -> new OpenEvent(Main.dateShown.plusDays(difference)));
            }
            for(JButton b: days){
                panel.add(b);
            }
            panel.repaint();
        });
        return moveButton;
    }

}
